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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ChatListener implements Listener {
    private final RidePet plugin;

    public ChatListener(RidePet plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerUuid = player.getUniqueId();

        if (!GUIListener.isWaitingRename(playerUuid)) return;

        event.setCancelled(true);

        String message = event.getMessage();

        if (message.equalsIgnoreCase("cancel")) {
            GUIListener.finishRename(playerUuid);
            player.sendMessage("§c已取消重命名。");
            return;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                PetManager petManager = plugin.getPetManager();
                UUID petUuid = GUIListener.getRenamingPetUuid(playerUuid);

                PetData pet = petManager.getPetByPetUuid(playerUuid, petUuid);
                if (pet != null) {
                    String newName = message;
                    newName = newName.replace("§", "");
                    newName = newName.replaceAll("&[0-9a-fA-Fk-oK-orR]", "");
                    newName = newName.replaceAll("[\\r\\n\\t]", "");
                    newName = newName.trim();

                    if (newName.isEmpty()) {
                        player.sendMessage("§c名称不能为空！");
                        GUIListener.finishRename(playerUuid);
                        return;
                    }

                    if (newName.length() > 32) {
                        newName = newName.substring(0, 32);
                    }

                    pet.setCustomName(newName);
                    plugin.getDataManager().savePlayerDataAsync(playerUuid);
                    player.sendMessage("§a坐骑已重命名为: §f" + newName);

                    PetListGUI.openGUI(player);
                }

                GUIListener.finishRename(playerUuid);

            }
        }.runTask(plugin);
    }
}
