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
import com.restond.ridepet.manager.ConfigManager;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetData;
import com.restond.ridepet.pet.PetType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class PlayerInteractListener implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;
    private final ConfigManager configManager;

    public PlayerInteractListener(RidePet ridePet) {
        this.plugin = ridePet;
        this.petManager = plugin.getPetManager();
        this.configManager = plugin.getConfigManager();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!isPetEgg(item)) return;

        event.setCancelled(true);

        if (!player.hasPermission("ridepet.use")) {
            player.sendMessage("§c你没有权限使用坐骑！");
            return;
        }

        PetType eggPetType = getPetTypeFromEgg(item);
        boolean eggExpired = isEggExpired(item);

        if (eggExpired) {
            boolean hasActivePet = false;
            List<PetData> pets = petManager.getPlayerPets(player.getUniqueId());
            for (PetData pet : pets) {
                if (eggPetType != null && pet.getPetTypeId().equals(eggPetType.getId()) && pet.isActive()) {
                    hasActivePet = true;
                    break;
                }
            }

            if (!hasActivePet) {
                player.sendMessage("§c该坐骑蛋已超过使用时限！");
                return;
            }

            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                for (PetData pet : pets) {
                    if (pet.getPetTypeId().equals(eggPetType.getId()) && pet.isActive()) {
                        petManager.forceRemovePet(player, pet);
                        player.sendMessage("§c该坐骑已超过使用时限，已自动收回！");
                        plugin.getDataManager().savePlayerDataAsync(player.getUniqueId());
                        break;
                    }
                }
            }
            return;
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            handleTogglePet(player, item);
        }
    }

    private boolean isPetEgg(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return false;
        if (item.getType() != Material.MONSTER_EGG) return false;
        if (!item.hasItemMeta()) return false;

        if (item.getItemMeta().hasLore()) {
            List<String> lore = item.getItemMeta().getLore();
            for (String line : lore) {
                if (line.contains("[RidePet] 类型编号:")) {
                    String typeId = line.replace("§7[RidePet] 类型编号: §f", "")
                            .replace("§7[RidePet] 类型编号:", "")
                            .trim();
                    if (configManager.getPetType(typeId) != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void handleTogglePet(Player player, ItemStack eggItem) {
        PetType eggPetType = getPetTypeFromEgg(eggItem);
        int eggLevel = getLevelFromEgg(eggItem);
        List<PetData> pets = petManager.getPlayerPets(player.getUniqueId());

        if (eggPetType != null) {
            boolean hasSameTypeAndLevel = false;
            PetData matchedPet = null;
            for (PetData pet : pets) {
                if (pet.getPetTypeId().equals(eggPetType.getId()) && pet.getLevel() == eggLevel) {
                    hasSameTypeAndLevel = true;
                    matchedPet = pet;
                    break;
                }
            }

            if (!hasSameTypeAndLevel) {
                PetData newPet = eggPetType.createPetData(eggLevel);
                if (petManager.addPetToPlayer(player.getUniqueId(), newPet)) {
                    if (!petManager.summonPet(player, newPet)) {
                        player.sendMessage("§c坐骑已添加但暂时无法召唤！");
                    }
                } else {
                    player.sendMessage("§c你已拥有最大数量的坐骑！");
                }
                plugin.getDataManager().savePlayerDataAsync(player.getUniqueId());
                return;
            }

            if (matchedPet.isActive()) {
                petManager.removePet(player, matchedPet);
            } else {
                petManager.summonPet(player, matchedPet);
            }
            plugin.getDataManager().savePlayerDataAsync(player.getUniqueId());
            return;
        }

        if (pets.isEmpty()) return;

        PetData activePet = null;
        for (PetData pet : pets) {
            if (pet.isActive()) {
                activePet = pet;
                break;
            }
        }

        if (activePet != null) {
            petManager.removePet(player, activePet);
        } else {
            for (PetData pet : pets) {
                if (petManager.summonPet(player, pet)) {
                    break;
                }
            }
        }

        plugin.getDataManager().savePlayerDataAsync(player.getUniqueId());
    }

    private PetType getPetTypeFromEgg(ItemStack eggItem) {
        if (!eggItem.hasItemMeta()) return null;

        if (eggItem.getItemMeta().hasLore()) {
            List<String> lore = eggItem.getItemMeta().getLore();
            for (String line : lore) {
                if (line.contains("[RidePet] 类型编号:")) {
                    String typeId = line.replace("§7[RidePet] 类型编号: §f", "")
                            .replace("§7[RidePet] 类型编号:", "")
                            .trim();
                    return configManager.getPetType(typeId);
                }
            }
        }

        return null;
    }

    private int getLevelFromEgg(ItemStack eggItem) {
        if (eggItem.hasItemMeta() && eggItem.getItemMeta().hasLore()) {
            List<String> lore = eggItem.getItemMeta().getLore();
            for (String line : lore) {
                if (line.contains("[RidePet] 等级:")) {
                    String levelStr = line.replace("§7[RidePet] 等级: §f", "")
                            .replace("§7[RidePet] 等级:", "")
                            .replace("Lv.", "")
                            .trim();
                    try {
                        return Integer.parseInt(levelStr);
                    } catch (NumberFormatException e) {
                        return 1;
                    }
                }
            }
        }
        return 1;
    }

    private boolean isEggExpired(ItemStack eggItem) {
        PetType petType = getPetTypeFromEgg(eggItem);
        if (petType == null || petType.getExpireMillis() <= 0) return false;

        long expireTime = getExpireTimeFromEgg(eggItem);
        if (expireTime <= 0) return false;

        return System.currentTimeMillis() > expireTime;
    }

    private long getExpireTimeFromEgg(ItemStack eggItem) {
        if (eggItem.hasItemMeta() && eggItem.getItemMeta().hasLore()) {
            List<String> lore = eggItem.getItemMeta().getLore();
            for (String line : lore) {
                if (line.contains("[RidePet] 到期时间:")) {
                    String timeStr = line.replace("§7[RidePet] 到期时间: §f", "")
                            .replace("§7[RidePet] 到期时间:", "")
                            .trim();
                    try {
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        java.util.Date date = sdf.parse(timeStr);
                        return date.getTime();
                    } catch (Exception e) {
                        return -1;
                    }
                }
            }
        }
        return -1;
    }
}
