package com.restond.ridepet.listener;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.List;
import java.util.UUID;

public class WorldChangeListener implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;

    public WorldChangeListener(RidePet plugin) {
        this.plugin = plugin;
        this.petManager = plugin.getPetManager();
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        UUID playerUuid = player.getUniqueId();
        List<PetData> pets = petManager.getPlayerPets(playerUuid);

        boolean recalled = false;
        for (PetData pet : pets) {
            if (pet.isActive()) {
                petManager.forceRemovePet(player, pet);
                recalled = true;
            }
        }

        if (recalled) {
            player.sendMessage("§e切换世界，坐骑已自动收回！");
        }

        plugin.getDataManager().savePlayerDataAsync(playerUuid);
    }
}
