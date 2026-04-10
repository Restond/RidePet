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

package com.restond.ridepet.listener;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CombatListener implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;

    private final Map<UUID, Long> lastCombatTime = new HashMap<>();

    public CombatListener(RidePet plugin) {
        this.plugin = plugin;
        this.petManager = plugin.getPetManager();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            handleCombat(player);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Entity victim = event.getEntity();

            if (victim.hasMetadata("ridepet_owner")) {
                String ownerUuid = victim.getMetadata("ridepet_owner").get(0).asString();
                if (ownerUuid.equals(attacker.getUniqueId().toString())) {
                    return;
                }
            }

            handleCombat(attacker);
        }
    }

    private void handleCombat(Player player) {
        UUID playerUuid = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        List<PetData> pets = petManager.getPlayerPets(playerUuid);
        PetData activePet = null;
        for (PetData pet : pets) {
            if (pet.isActive()) {
                activePet = pet;
                break;
            }
        }

        if (activePet != null) {
            petManager.forceRemovePet(player, activePet);
            plugin.getDataManager().savePlayerDataAsync(playerUuid);
            sendActionBar(player);
        }

        lastCombatTime.put(playerUuid, currentTime);
    }

    private void sendActionBar(Player player) {
        try {
            player.sendActionBar("§c已自动收回坐骑！");
        } catch (Exception e) {
            player.sendMessage("§c已自动收回坐骑！");
        }
    }

    public boolean isInCombat(UUID playerUuid) {
        Long lastTime = lastCombatTime.get(playerUuid);
        if (lastTime == null) return false;

        int combatTimeout = plugin.getConfig().getInt("settings.combat_timeout", 10);
        long elapsed = (System.currentTimeMillis() - lastTime) / 1000;

        return elapsed < combatTimeout;
    }

    public int getCombatCooldown(UUID playerUuid) {
        Long lastTime = lastCombatTime.get(playerUuid);
        if (lastTime == null) return 0;

        int combatTimeout = plugin.getConfig().getInt("settings.combat_timeout", 10);
        long elapsed = (System.currentTimeMillis() - lastTime) / 1000;

        return Math.max(0, (int) (combatTimeout - elapsed));
    }

    public void clearCombatState(UUID playerUuid) {
        lastCombatTime.remove(playerUuid);
    }

}
