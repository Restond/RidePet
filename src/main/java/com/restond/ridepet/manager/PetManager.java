/*
 * RidePet - A Minecraft mount/ride pet plugin
 * Copyright (C) 2026  Restond
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.restond.ridepet.manager;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.attribute.AttributeBridge;
import com.restond.ridepet.attribute.SXAttributeBridge;
import com.restond.ridepet.listener.CombatListener;
import com.restond.ridepet.pet.PetData;
import com.restond.ridepet.pet.PetType;
import com.restond.ridepet.util.MessageUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.*;

public class PetManager {
    private final RidePet plugin;
    private final ConfigManager configManager;
    private final AttributeBridge attributeBridge;

    private final Map<UUID, List<PetData>> playerPets = new HashMap<>();
    private final Map<UUID, Long> lastActionTime = new HashMap<>();
    private final Map<UUID, PetData> activePetEntities = new HashMap<>();

    public PetManager(RidePet plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;

        AttributeBridge bridge = null;
        List<String> priority = configManager.getConfig().getStringList("attribute_priority");
        for (String name : priority) {
            if (name.equalsIgnoreCase("sx")) {
                bridge = new SXAttributeBridge();
                if (bridge.isEnabled()) break;
                bridge = null;
            }
        }
        this.attributeBridge = bridge;

        if (attributeBridge != null) {
            plugin.getLogger().info("已启用属性插件: " + attributeBridge.getName());
        } else {
            plugin.getLogger().info("未检测到属性插件，坐骑将不提供属性加成");
        }
    }

    public boolean summonPet(Player player, PetData petData) {
        if (!checkCooldown(player)) {
            return false;
        }

        CombatListener combatListener = plugin.getCombatListener();
        if (combatListener != null && combatListener.isInCombat(player.getUniqueId())) {
            int remaining = combatListener.getCombatCooldown(player.getUniqueId());
            player.sendActionBar("§c还需 " + remaining + " 秒脱离战斗后，脱离后才能召唤坐骑！");
            return false;
        }

        if (!petData.canRevive()) {
            int remaining = petData.getRemainingReviveTime();
            MessageUtil.send(player, "pet_reviving", "time", String.valueOf(remaining));
            return false;
        }

        if (!petData.isAlive()) {
            petData.setAlive(true);
            petData.setCurrentHealth(petData.getMaxHealth());
        }

        for (PetData pet : getPlayerPets(player.getUniqueId())) {
            if (pet.isActive()) {
                player.sendMessage("§c你已有一只坐骑在外，请先收回当前坐骑！");
                return false;
            }
        }

        updatePetDataFromConfig(petData);

        Location spawnLoc = player.getLocation().add(player.getLocation().getDirection().multiply(2));
        Horse horse = (Horse) player.getWorld().spawnEntity(spawnLoc, org.bukkit.entity.EntityType.HORSE);

        horse.setAdult();
        horse.setAgeLock(true);
        horse.setMetadata("ridepet_owner", new org.bukkit.metadata.FixedMetadataValue(plugin, player.getUniqueId().toString()));
        horse.setColor(petData.getHorseColor());
        horse.setStyle(petData.getHorseStyle());
        horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(petData.getMaxHealth());
        horse.setHealth(petData.getCurrentHealth());
        horse.setTamed(true);
        horse.setOwner(player);
        horse.setCustomName(MessageUtil.color(petData.getCustomName()));
        horse.setCustomNameVisible(true);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));

        try {
            double speed = petData.getHorseSpeed();
            horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)
                    .setBaseValue(speed);
            double jump = petData.getHorseJump();
            horse.getAttribute(Attribute.HORSE_JUMP_STRENGTH)
                    .setBaseValue(jump);
        } catch (Exception e) {
            plugin.getLogger().warning("设置马属性失败: " + e.getMessage());
        }

        petData.setActive(true);
        petData.setEntityUuid(horse.getUniqueId());
        activePetEntities.put(horse.getUniqueId(), petData);
        lastActionTime.put(player.getUniqueId(), System.currentTimeMillis());
        applyAttributes(player, petData);

        MessageUtil.send(player, "pet_summoned");
        return true;
    }

    public boolean removePet(Player player, PetData petData) {
        if (!petData.isActive() || petData.getEntityUuid() == null) {
            return false;
        }

        if (!checkCooldown(player)) {
            return false;
        }

        Entity entity = plugin.getServer().getEntity(petData.getEntityUuid());
        UUID oldEntityUuid = petData.getEntityUuid();

        if (entity != null && entity.isValid()) {
            petData.setCurrentHealth(((LivingEntity)entity).getHealth());
            entity.remove();
        }

        petData.setActive(false);
        petData.setEntityUuid(null);
        activePetEntities.remove(oldEntityUuid);

        if (player.isInsideVehicle()) {
            removeAttributes(player);
        }

        lastActionTime.put(player.getUniqueId(), System.currentTimeMillis());

        MessageUtil.send(player, "pet_removed");
        return true;
    }

    public void handlePetDeath(UUID entityUuid) {
        PetData petData = activePetEntities.get(entityUuid);
        if (petData == null) return;

        petData.markDeath();
        activePetEntities.remove(entityUuid);

        Entity entity = plugin.getServer().getEntity(entityUuid);
        if (entity instanceof Horse) {
            Horse horse = (Horse) entity;
            for (Entity passenger : horse.getPassengers()) {
                if (passenger instanceof Player) {
                    removeAttributes((Player) passenger);
                    break;
                }
            }
        }

        for (Map.Entry<UUID, List<PetData>> entry : playerPets.entrySet()) {
            for (PetData pet : entry.getValue()) {
                if (pet.getPetUuid().equals(petData.getPetUuid())) {
                    Player owner = plugin.getServer().getPlayer(entry.getKey());
                    if (owner != null && owner.isOnline()) {
                        int reviveTime = petData.getReviveTime();
                        MessageUtil.send(owner, "pet_dead", "time", String.valueOf(reviveTime));
                    }
                    plugin.getDataManager().savePlayerDataAsync(entry.getKey());
                    return;
                }
            }
        }
    }

    public void onPlayerMount(Player player, UUID entityUuid) {
        PetData petData = activePetEntities.get(entityUuid);
        if (petData != null) {
            applyAttributes(player, petData);
            syncHorseSpeed(player, entityUuid, petData);
        }
    }

    public void onPlayerDismount(Player player, UUID entityUuid) {
        PetData petData = activePetEntities.get(entityUuid);
        if (petData != null && petData.isActive()) {
            Entity entity = plugin.getServer().getEntity(entityUuid);
            if (entity instanceof Horse) {
                Horse horse = (Horse) entity;
                try {
                    horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)
                            .setBaseValue(petData.getHorseSpeed());
                } catch (Exception e) {
                    plugin.getLogger().warning("恢复马速度失败: " + e.getMessage());
                }
            }
        }
        removeAttributes(player);
    }

    public boolean addPetToPlayer(UUID playerUuid, PetData petData) {
        List<PetData> pets = playerPets.computeIfAbsent(playerUuid, k -> new ArrayList<>());

        int maxPets = configManager.getConfig().getInt("egg.max_pets", 3);
        if (pets.size() >= maxPets) {
            return false;
        }

        pets.add(petData);
        return true;
    }

    public boolean removePetFromPlayer(UUID playerUuid, UUID petUuid) {
        List<PetData> pets = playerPets.get(playerUuid);
        if (pets == null) return false;

        for (Iterator<PetData> it = pets.iterator(); it.hasNext();) {
            PetData pet = it.next();
            if (pet.getPetUuid().equals(petUuid)) {
                if (pet.isActive() && pet.getEntityUuid() != null) {
                    Entity entity = plugin.getServer().getEntity(pet.getEntityUuid());
                    if (entity != null && entity.isValid()) {
                        entity.remove();
                    }
                    activePetEntities.remove(pet.getEntityUuid());
                }
                it.remove();
                return true;
            }
        }
        return false;
    }

    public List<PetData> getPlayerPets(UUID playerUuid) {
        return playerPets.computeIfAbsent(playerUuid, k -> new ArrayList<>());
    }

    public PetData getPetByEntityUuid(UUID entityUuid) {
        return activePetEntities.get(entityUuid);
    }

    public PetData getPetByPetUuid(UUID playerUuid, UUID petUuid) {
        List<PetData> pets = playerPets.get(playerUuid);
        if (pets == null) return null;

        for (PetData pet : pets) {
            if (pet.getPetUuid().equals(petUuid)) {
                return pet;
            }
        }
        return null;
    }

    private boolean checkCooldown(Player player) {
        Long lastTime = lastActionTime.get(player.getUniqueId());
        if (lastTime == null) return true;

        long cooldown = configManager.getConfig().getInt("egg.cooldown", 3000);
        long elapsed = System.currentTimeMillis() - lastTime;

        if (elapsed < cooldown) {
            int remaining = (int) Math.ceil((cooldown - elapsed) / 1000.0);
            MessageUtil.send(player, "cooldown", "time", String.valueOf(remaining));
            return false;
        }
        return true;
    }

    private void applyAttributes(Player player, PetData petData) {
        if (attributeBridge != null && attributeBridge.isEnabled()) {
            attributeBridge.applyAttributes(player, petData.getAttributeLore());
        }
    }

    private void removeAttributes(Player player) {
        if (attributeBridge != null && attributeBridge.isEnabled()) {
            attributeBridge.removeAttributes(player);
        }
    }

    private void syncHorseSpeed(Player player, UUID entityUuid, PetData petData) {
        if (attributeBridge == null || !attributeBridge.isEnabled()) return;

        double playerSpeed = attributeBridge.getPlayerMovementSpeed(player);
        if (playerSpeed < 0) return;

        Entity entity = plugin.getServer().getEntity(entityUuid);
        if (entity instanceof Horse) {
            Horse horse = (Horse) entity;
            try {
                double finalSpeed = Math.max(petData.getHorseSpeed(), playerSpeed);
                horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)
                        .setBaseValue(finalSpeed);
            } catch (Exception e) {
                plugin.getLogger().warning("同步马速度失败: " + e.getMessage());
            }
        }
    }

    private void updatePetDataFromConfig(PetData petData) {
        PetType petType = configManager.getPetType(petData.getPetTypeId());
        if (petType == null) return;

        petData.setHorseColor(petType.getHorseColor());
        petData.setHorseStyle(petType.getHorseStyle());
        petData.setMaxHealth(petType.getMaxHealth());

        PetType.LevelData levelData = petType.getLevelData(petData.getLevel());
        if (levelData != null) {
            petData.setAttributeLore(levelData.getAttributes());
            petData.setReviveTime(levelData.getReviveTime());
            petData.setHorseSpeed(levelData.getHorseSpeed());
            petData.setHorseJump(levelData.getHorseJump());
        }
    }

    public boolean forceRemovePet(Player player, PetData petData) {
        if (!petData.isActive() || petData.getEntityUuid() == null) {
            return false;
        }

        Entity entity = plugin.getServer().getEntity(petData.getEntityUuid());
        UUID oldEntityUuid = petData.getEntityUuid();

        if (entity != null && entity.isValid()) {
            petData.setCurrentHealth(((LivingEntity)entity).getHealth());
            entity.remove();
        }

        petData.setActive(false);
        petData.setEntityUuid(null);
        activePetEntities.remove(oldEntityUuid);

        if (player.isInsideVehicle()) {
            removeAttributes(player);
        }

        lastActionTime.put(player.getUniqueId(), System.currentTimeMillis());

        MessageUtil.send(player, "pet_removed");
        return true;
    }

    public AttributeBridge getAttributeBridge() {
        return attributeBridge;
    }

    public void loadPlayerData(UUID playerUuid, List<PetData> pets) {
        playerPets.put(playerUuid, pets);
    }

    public Map<UUID, List<PetData>> getAllPlayerPets() {
        return playerPets;
    }

    public void onPlayerQuit(UUID playerUuid) {
        List<PetData> pets = playerPets.get(playerUuid);
        if (pets == null) return;

        for (PetData pet : pets) {
            if (pet.isActive() && pet.getEntityUuid() != null) {
                Entity entity = plugin.getServer().getEntity(pet.getEntityUuid());
                UUID oldEntityUuid = pet.getEntityUuid();
                if (entity != null && entity.isValid()) {
                    pet.setCurrentHealth(((LivingEntity)entity).getHealth());
                    entity.remove();
                }
                pet.setActive(false);
                pet.setEntityUuid(null);
                activePetEntities.remove(oldEntityUuid);
            }
        }

        lastActionTime.remove(playerUuid);
    }
}
