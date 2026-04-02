package com.restond.ridepet.listener;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import java.util.UUID;

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

        petManager.handlePetDeath(entity.getUniqueId());

        event.getDrops().clear();
        event.setDroppedExp(0);

        for (UUID playerUuid : petManager.getAllPlayerPets().keySet()) {
            if (petManager.getPetByPetUuid(playerUuid, petData.getPetUuid()) != null) {
                plugin.getDataManager().savePlayerDataAsync(playerUuid);
                break;
            }
        }
    }





}
