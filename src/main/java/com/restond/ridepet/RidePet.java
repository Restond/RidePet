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

package com.restond.ridepet;

import com.restond.ridepet.listener.*;
import com.restond.ridepet.manager.ConfigManager;
import com.restond.ridepet.manager.DataManager;
import com.restond.ridepet.manager.PetManager;
import com.restond.ridepet.pet.PetType;
import com.restond.ridepet.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.scheduler.BukkitRunnable;


public final class RidePet extends JavaPlugin {

    private static RidePet instance;
    private ConfigManager configManager;
    private PetManager petManager;
    private DataManager dataManager;
    private CombatListener combatListener;

    @Override
    public void onEnable() {
        instance = this;

        configManager = new ConfigManager((this));
        configManager.init();

        petManager = new PetManager(this, configManager);

        dataManager = new DataManager(this, petManager);
        dataManager.init();

        combatListener = new CombatListener(this);
        getServer().getPluginManager().registerEvents(combatListener, this);

        registerListeners();
        registerCommands();
        startAutoSaveTask();
        startMemoryCleanupTask();

        getLogger().info("Ride 插件已启用! ");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        if (dataManager != null) {
            dataManager.saveAllPlayerData();
        }

        if (petManager != null) {
            for (Player player : getServer().getOnlinePlayers()) {
                petManager.onPlayerQuit(player.getUniqueId());
                if (combatListener != null) {
                    combatListener.clearCombatState(player.getUniqueId());
                }
            }
        }

        getLogger().info("RidePet 插件已关闭!");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new PetDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new VehicleListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new WorldChangeListener(this), this);
    }

    private void registerCommands() {
        TabExecutor ridepetCommand = new TabExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (args.length == 0) {
                    sendHelp(sender);
                    return true;
                }

                switch (args[0].toLowerCase()) {
                    case "reload":
                        handleReload(sender);
                        break;
                    case "give":
                        handleGive(sender, args);
                        break;
                    case "list":
                        handleList(sender, args);
                        break;
                    default:
                        sendHelp(sender);
                        break;
                }
                return true;
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                List<String> completions = new ArrayList<>();

                if (args.length == 1) {
                    completions.add("reload");
                    completions.add("give");
                    completions.add("list");
                } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
                    for (Player player : getServer().getOnlinePlayers()) {
                        if (player.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                            completions.add(player.getName());
                        }
                    }
                } else if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
                    for (String typeId : configManager.getAllPetTypeIds()) {
                        if (typeId.toLowerCase().startsWith(args[2].toLowerCase())) {
                            completions.add(typeId);
                        }
                    }
                } else if (args.length == 4 && args[0].equalsIgnoreCase("give")) {
                    String typeId = args[2];
                    PetType petType = configManager.getPetType(typeId);
                    if (petType != null) {
                        for (Integer level : petType.getLevels().keySet()) {
                            String levelStr = String.valueOf(level);
                            if (levelStr.startsWith(args[3])) {
                                completions.add(levelStr);
                            }
                        }
                    }
                }

                return completions;
            }
        };

        getCommand("ridepet").setExecutor(ridepetCommand);
    }

    private void handleReload(CommandSender sender) {
        if (!sender.hasPermission("ridepet.admin")) {
            sender.sendMessage("§c你没有权限执行此命令！");
            return;
        }
        dataManager.saveAllPlayerData();

        configManager.reload();

        for (Player player : getServer().getOnlinePlayers()) {
            petManager.onPlayerQuit(player.getUniqueId());
        }
        petManager.getAllPlayerPets().clear();
        dataManager.loadAllPlayerData();

        sender.sendMessage("§a[RidePet] 配置已重载！");
    }

    private void handleGive(CommandSender sender, String[] args) {
        if (!sender.hasPermission("ridepet.admin")) {
            sender.sendMessage("§c你没有权限执行此命令！");
            return;
        }

        if (args.length < 3) {
            sender.sendMessage("§c用法: /ridepet give <玩家> <类型ID> [等级]");
            return;
        }

        Player target = getServer().getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage("§c玩家不在线！");
            return;
        }

        String typeId = args[2];
        PetType petType = configManager.getPetType(typeId);
        if (petType == null) {
            sender.sendMessage("§c未知的坐骑类型: " + typeId);
            sender.sendMessage("§7可用类型: " + String.join(", ", configManager.getAllPetTypeIds()));
            return;
        }

        int level = 1;
        if (args.length >= 4) {
            try {
                level = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§c无效的等级！");
                return;
            }
        }

        if (!petType.getLevels().containsKey(level)) {
            sender.sendMessage("§c该坐骑没有配置等级 " + level + "！");
            sender.sendMessage("§7可用等级: " + petType.getLevels().keySet().stream()
                    .sorted().map(String::valueOf).collect(java.util.stream.Collectors.joining(", ")));
            return;
        }

        ItemStack egg = createPetEgg(petType, level);

        java.util.Map<Integer, ItemStack> leftover = target.getInventory().addItem(egg);
        if (!leftover.isEmpty()) {
            target.getWorld().dropItemNaturally(target.getLocation(), egg);
            sender.sendMessage("§e玩家背包已满，坐骑蛋已掉落在地上！");
        }

        target.sendMessage("§a你获得了一个坐骑蛋！");
        if (sender != target) {
            sender.sendMessage("§a已给予 " + target.getName() + " 一个坐骑蛋！");
        }
    }

    private void handleList(CommandSender sender, String[] args) {
        if (!sender.hasPermission("ridepet.admin")) {
            sender.sendMessage("§c你没有权限执行此命令！");
            return;
        }

        sender.sendMessage("§e--- 坐骑类型 ---");
        for (String typeId : configManager.getAllPetTypeIds()) {
            PetType type = configManager.getPetType(typeId);
            sender.sendMessage("§e" + typeId + " §7- " + type.getName());
        }
    }

    private ItemStack createPetEgg(PetType petType, int level) {
        List<String> lore = new ArrayList<>();

        if (petType.getEggLore() != null && !petType.getEggLore().isEmpty()) {
            lore.addAll(petType.getEggLore());
        } else {
            lore.add("§7[RidePet] 坐骑蛋");
            lore.add("§7类型: §f" + petType.getId());
            lore.add("§7等级: §fLv." + level);
            lore.add("");
            lore.add("§e左键空中：收放坐骑");
        }

        boolean hasPetId = false;
        for (String line : lore) {
            if (line.contains(petType.getId())) {
                hasPetId = true;
                break;
            }
        }

        if (!hasPetId) {
            lore.add("§7[RidePet] 类型编号: §f" + petType.getId());
        }

        lore.add("§7[RidePet] 等级: §f" + level);

        if (petType.getExpireMillis() > 0) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
            long expireTime = System.currentTimeMillis() + petType.getExpireMillis();
            lore.add("§7[RidePet] 到期时间: §f" + sdf.format(new java.util.Date(expireTime)));
        }

        return new ItemBuilder(Material.MONSTER_EGG)
                .setDisplayName(petType.getEggDisplayName() != null ? petType.getEggDisplayName() : "§f[坐骑蛋] " + petType.getName())
                .setLore(lore)
                .build();
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("§e/ridepet reload §7- 重载配置");
        sender.sendMessage("§e/ridepet give <玩家> <类型> [等级] §7- 给予坐骑蛋");
        sender.sendMessage("§e/ridepet list §7- 列出坐骑类型");
    }

    public static RidePet getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public PetManager getPetManager() {
        return petManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CombatListener getCombatListener() {
        return combatListener;
    }

    private void startAutoSaveTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (dataManager != null) {
                    dataManager.saveAllPlayerDataAsync();
                    getLogger().info("[自动保存] 已保存所有玩家坐骑数据");
                }
            }
        }.runTaskTimerAsynchronously(this, 20L * 60 * 60 * 24, 20L * 60 * 60 * 24);
    }

    private void startMemoryCleanupTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (petManager != null) {
                    int cleaned = cleanupOfflinePlayerData();
                    getLogger().info("[内存清理] 已清理 " + cleaned + " 个离线玩家数据");
                }
            }
        }.runTaskTimer(this, 20L * 60 * 60 * 24 * 7, 20L * 60 * 60 * 24 * 7);
    }

    private int cleanupOfflinePlayerData() {
        int cleaned = 0;

        for (UUID playerUuid : new java.util.HashSet<>(petManager.getAllPlayerPets().keySet())) {
            Player player = getServer().getPlayer(playerUuid);
            if (player == null || !player.isOnline()) {
                petManager.getAllPlayerPets().remove(playerUuid);
                cleaned++;
            }
        }

        return cleaned;
    }
}
