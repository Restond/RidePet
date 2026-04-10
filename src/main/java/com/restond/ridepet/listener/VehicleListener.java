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
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class VehicleListener  implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;

    public VehicleListener(RidePet plugin) {
        this.plugin = plugin;
        this.petManager = plugin.getPetManager();
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        Entity vehicle = event.getVehicle();
        Entity passenger = event.getEntered();

        if (!(vehicle instanceof Horse)) return;
        if (!(passenger instanceof Player)) return;

        Horse horse = (Horse) vehicle;
        Player player = (Player) passenger;

        PetData petData = petManager.getPetByEntityUuid(horse.getUniqueId());
        if (petData == null) return;

        if (horse.hasMetadata("ridepet_owner")) {
            String ownerUuidStr = horse.getMetadata("ridepet_owner").get(0).asString();
            if (!player.getUniqueId().toString().equals(ownerUuidStr)) {
                event.setCancelled(true);
                player.sendMessage("§c这不是你的坐骑！");
                return;
            }
        }

        petManager.onPlayerMount(player, horse.getUniqueId());
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        Entity vehicle = event.getVehicle();
        Entity passenger = event.getExited();

        if (!(vehicle instanceof Horse)) return;
        if (!(passenger instanceof Player)) return;

        Horse horse = (Horse) vehicle;
        Player player = (Player) passenger;

        PetData petData = petManager.getPetByEntityUuid(horse.getUniqueId());
        if (petData == null) return;

        petManager.onPlayerDismount(player, horse.getUniqueId());
    }

    public RidePet getPlugin() {
        return plugin;
    }
}
