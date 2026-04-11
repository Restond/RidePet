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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PetDeathListener implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;

    public PetDeathListener(RidePet plugin) {
        this.plugin = plugin;
        this.petManager = plugin.getPetManager();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Horse)) return;

        PetData petData = petManager.getPetByEntityUuid(entity.getUniqueId());
        if (petData == null) return;

        Horse horse = (Horse) entity;
        horse.getInventory().clear();

        event.getDrops().clear();
        event.setDroppedExp(0);

        petManager.handlePetDeath(entity.getUniqueId());
    }


    public RidePet getPlugin() {
        return plugin;
    }
}
