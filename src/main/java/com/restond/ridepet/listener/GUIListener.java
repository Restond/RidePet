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
import com.restond.ridepet.gui.PetListGUI;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;
import java.util.UUID;

public class GUIListener implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;

    private static final java.util.Set<UUID> waitingRename = java.util.Collections.synchronizedSet(new java.util.HashSet<>());
    private static final java.util.Map<UUID, UUID> renamingPets = new java.util.concurrent.ConcurrentHashMap<>();

    public GUIListener(RidePet plugin) {
        this.plugin = plugin;
        this.petManager = plugin.getPetManager();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;

        String title = event.getView().getTitle();
        if (!title.equals("§6我的坐骑")) return;

        event.setCancelled(true);

        ItemStack clickItem = event.getCurrentItem();
        if (clickItem == null || clickItem.getType() == Material.AIR) return;

        int slot = event.getSlot();
        List<PetData> pets = petManager.getPlayerPets(player.getUniqueId());

        if (slot >= pets.size()) return;

        PetData pet = pets.get(slot);

        ClickType clickType = event.getClick();

        if (clickType == ClickType.LEFT) {
            handleTogglePet(player, pet);
        } else if (clickType == ClickType.RIGHT) {
            handleDeletePet(player, pet);
        } else if (clickType == ClickType.SHIFT_RIGHT) {
            handleRenamePet(player, pet);
        }
    }

    private void handleTogglePet(Player player, PetData pet) {
        if (pet.isActive()) {
            petManager.removePet(player, pet);
        } else {
            petManager.summonPet(player, pet);
        }
        plugin.getDataManager().savePlayerDataAsync(player.getUniqueId());

        player.closeInventory();
        new BukkitRunnable() {
            @Override
            public void run() {
                PetListGUI.openGUI(player);
            }
        }.runTaskLater(plugin, 1L);
    }

    private void handleDeletePet(Player player, PetData pet) {
        if (pet.isActive()) {
            petManager.removePet(player, pet);
        }

        petManager.removePetFromPlayer(player.getUniqueId(), pet.getPetUuid());
        plugin.getDataManager().savePlayerDataAsync(player.getUniqueId());

        player.sendMessage("§c坐骑已删除！");

        player.closeInventory();
        new BukkitRunnable() {
            @Override
            public void run() {
                PetListGUI.openGUI(player);
            }
        }.runTaskLater(plugin, 1L);
    }

    private void handleRenamePet(Player player, PetData pet) {
        player.closeInventory();
        player.sendMessage("§e请在聊天框中输入新名称（输入 'cancel' 取消）：");

        waitingRename.add(player.getUniqueId());
        renamingPets.put(player.getUniqueId(), pet.getPetUuid());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            PetListGUI.clearPage(event.getPlayer().getUniqueId());
        }
    }

    public static boolean isWaitingRename(UUID playerUuid) {
        return waitingRename.contains(playerUuid);
    }

    public static UUID getRenamingPetUuid(UUID playerUuid) {
        return renamingPets.get(playerUuid);
    }

    public static void finishRename(UUID playerUuid) {
        waitingRename.remove(playerUuid);
        renamingPets.remove(playerUuid);

    }
}
