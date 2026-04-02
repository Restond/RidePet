package com.restond.ridepet.gui;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetData;
import com.restond.ridepet.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PetListGUI {
    private static final String GUI_TITLE = "§6我的坐骑";
    private static final Map<UUID, Integer> playerPage = new HashMap<>();
    private static final Set<UUID> openGUIPlayers = new HashSet<>();
    private static BukkitRunnable refreshTask = null;

    public static void openGUI(Player player) {
        openGUI(player, 0);
    }

    public static void openGUI(Player player, int page) {
        PetManager petManager = RidePet.getInstance().getPetManager();
        List<PetData> pets = petManager.getPlayerPets(player.getUniqueId());

        Inventory gui = Bukkit.createInventory(null, 9, GUI_TITLE);

        int slot = 0;
        for (int i = page * 9; i < Math.min(pets.size(), (page + 1) * 9); i++) {
            PetData pet = pets.get(i);
            ItemStack icon = createPetIcon(pet);
            gui.setItem(slot, icon);
            slot++;
        }

        if (pets.isEmpty()) {
            ItemStack emptyIcon = new ItemBuilder(Material.BARRIER)
                    .setDisplayName("§c暂无坐骑")
                    .setLore("§7使用坐骑蛋获得第一只坐骑！")
                    .build();
            gui.setItem(4, emptyIcon);
        }

        player.openInventory(gui);
        playerPage.put(player.getUniqueId(), page);
        openGUIPlayers.add(player.getUniqueId());
        startRefreshTask();
    }

    private static void startRefreshTask() {
        if (refreshTask != null) return;

        refreshTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (openGUIPlayers.isEmpty()) {
                    stopRefreshTask();
                    return;
                }

                for (UUID playerUuid : new HashSet<>(openGUIPlayers)) {
                    Player player = Bukkit.getPlayer(playerUuid);
                    if (player == null || !player.isOnline()) {
                        openGUIPlayers.remove(playerUuid);
                        continue;
                    }

                    if (player.getOpenInventory() != null
                            && player.getOpenInventory().getTitle().equals(GUI_TITLE)) {
                        refreshGUIContent(player);
                    } else {
                        openGUIPlayers.remove(playerUuid);
                    }
                }
            }
        };
        refreshTask.runTaskTimer(RidePet.getInstance(), 20L, 20L);
    }

    private static void stopRefreshTask() {
        if (refreshTask != null) {
            refreshTask.cancel();
            refreshTask = null;
        }
    }

    private static void refreshGUIContent(Player player) {
        PetManager petManager = RidePet.getInstance().getPetManager();
        List<PetData> pets = petManager.getPlayerPets(player.getUniqueId());
        int page = playerPage.getOrDefault(player.getUniqueId(), 0);

        Inventory gui = player.getOpenInventory().getTopInventory();

        int slot = 0;
        for (int i = page * 9; i < Math.min(pets.size(), (page + 1) * 9); i++) {
            PetData pet = pets.get(i);
            ItemStack icon = createPetIcon(pet);
            gui.setItem(slot, icon);
            slot++;
        }
    }

    private static ItemStack createPetIcon(PetData pet) {
        Material material = Material.MONSTER_EGG;

        String status;
        if (!pet.isAlive()) {
            int remaining = pet.getRemainingReviveTime();
            status = "§c死亡中 §7(剩余 " + remaining + " 秒)";
        } else if (pet.isActive()) {
            status = "§a已召唤";
        } else {
            status = "§e休息中";
        }

        String colorName = pet.getHorseColor() != null ? pet.getHorseColor().name() : "BROWN";
        String styleName = pet.getHorseStyle() != null ? pet.getHorseStyle().name() : "NONE";

        return new ItemBuilder(material)
                .setDisplayName(pet.getCustomName() != null ? pet.getCustomName() : "§f未命名坐骑")
                .setLore(
                        "§7类型: §f" + pet.getPetTypeId(),
                        "§7等级: §fLv." + pet.getLevel(),
                        "§7外观: §f" + colorName + " / " + styleName,
                        "§7状态: " + status,
                        "",
                        "§e左键收放 §7| §c右键删除"

                ).build();
    }

    public static int getPage(UUID playerUuid) {
        return playerPage.getOrDefault(playerUuid, 0);
    }

    public static void clearPage(UUID playerUuid) {
        playerPage.remove(playerUuid);
    }
}
