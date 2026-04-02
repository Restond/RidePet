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

        petManager.onPlayerDismount(player);
    }

    public RidePet getPlugin() {
        return plugin;
    }
}
