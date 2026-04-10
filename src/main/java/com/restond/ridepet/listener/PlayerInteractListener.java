package com.restond.ridepet.listener;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.gui.PetListGUI;
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

        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            handleTogglePet(player, item);
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            PetListGUI.openGUI(player);
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

    /** 从宠物蛋获取宠物类型 */
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
}
