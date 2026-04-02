package com.restond.ridepet.listener;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.manager.PetManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.UUID;

public class PlayerQuitListener implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;

    public PlayerQuitListener(RidePet plugin) {
        this.plugin = plugin;
        this.petManager = plugin.getPetManager();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID playerUuid = player.getUniqueId();

        petManager.onPlayerQuit(playerUuid);
        plugin.getDataManager().savePlayerDataAsync(playerUuid);
        plugin.getCombatListener().clearCombatState(playerUuid);

    }
}
