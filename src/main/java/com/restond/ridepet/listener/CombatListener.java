package com.restond.ridepet.listener;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/** 战斗监听器 */
public class CombatListener implements Listener {
    private final RidePet plugin;
    private final PetManager petManager;

    private final Map<UUID, Long> lastCombatTime = new HashMap<>();

    public CombatListener(RidePet plugin) {
        this.plugin = plugin;
        this.petManager = plugin.getPetManager();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            handleCombat(player);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Entity victim = event.getEntity();

            if (victim.hasMetadata("ridepet_owner")) {
                String ownerUuid = victim.getMetadata("ridepet_owner").get(0).asString();
                if (ownerUuid.equals(attacker.getUniqueId().toString())) {
                    return;
                }
            }

            handleCombat(attacker);
        }
    }

    /** 处理玩家进入战斗 */
    private void handleCombat(Player player) {
        UUID playerUuid = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        List<PetData> pets = petManager.getPlayerPets(playerUuid);
        PetData activePet = null;
        for (PetData pet : pets) {
            if (pet.isActive()) {
                activePet = pet;
                break;
            }
        }

        if (activePet != null) {
            petManager.forceRemovePet(player, activePet);
            plugin.getDataManager().savePlayerDataAsync(playerUuid);
            sendActionBar(player, "§c已自动收回坐骑！");
        }

        lastCombatTime.put(playerUuid, currentTime);
    }

    /** 发送 ActionBar */
    private void sendActionBar(Player player, String message) {
        try {
            player.sendActionBar(message);
        } catch (Exception e) {
            player.sendMessage(message);
        }
    }

    /** 检查是否在战斗中 */
    public boolean isInCombat(UUID playerUuid) {
        Long lastTime = lastCombatTime.get(playerUuid);
        if (lastTime == null) return false;

        int combatTimeout = plugin.getConfig().getInt("settings.combat_timeout", 10);
        long elapsed = (System.currentTimeMillis() - lastTime) / 1000;

        return elapsed < combatTimeout;
    }

    /** 获取脱战剩余时间 */
    public int getCombatCooldown(UUID playerUuid) {
        Long lastTime = lastCombatTime.get(playerUuid);
        if (lastTime == null) return 0;

        int combatTimeout = plugin.getConfig().getInt("settings.combat_timeout", 10);
        long elapsed = (System.currentTimeMillis() - lastTime) / 1000;

        return Math.max(0, (int) (combatTimeout - elapsed));
    }

    /** 清理战斗状态 */
    public void clearCombatState(UUID playerUuid) {
        lastCombatTime.remove(playerUuid);
    }

}
