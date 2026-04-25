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

package com.restond.ridepet.manager;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.pet.PetData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataManager {
    private final RidePet plugin;
    private final PetManager petManager;
    private final File dataFolder;

    public DataManager(RidePet ridePet, PetManager petManager) {
        this.plugin = ridePet;
        this.petManager = petManager;
        this.dataFolder = new File(plugin.getDataFolder(), "data");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void init() {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        loadAllPlayerData();
        plugin.getLogger().info("数据管理器初始化完毕");
    }

    public void loadAllPlayerData() {
        File[] files = dataFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files == null) return;

        for (File file : files) {
            try {
                loadAllPlayerDataFile(file);
            } catch (Exception e) {
                plugin.getLogger().warning("加载玩家数据失败：" + file.getName() + " - " + e.getMessage());
            }
        }
    }

    public void loadAllPlayerDataFile(File file) {
        String fileName = file.getName();
        String uuidString = fileName.substring(0, fileName.length() - 4);

        UUID playerUuid;
        try {
            playerUuid = UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("无效的文件名:" + fileName);
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        List<PetData> pets = new ArrayList<>();

        for (String key : config.getKeys(false)) {
            ConfigurationSection petSection = config.getConfigurationSection(key);
            if (petSection != null) {
                PetData petData = PetData.loadFromConfig(petSection);
                pets.add(petData);
            }
        }

        petManager.loadPlayerData(playerUuid, pets);
    }

    public void savePlayerData(UUID playerUuid) {
        List<PetData> pets = petManager.getPlayerPets(playerUuid);
        writePlayerData(playerUuid, pets);
    }

    public void saveAllPlayerData() {
        Map<UUID, List<PetData>> allPets = petManager.getAllPlayerPets();
        for (Map.Entry<UUID, List<PetData>> entry : allPets.entrySet()) {
            writePlayerData(entry.getKey(), entry.getValue());
        }
    }

    public void savePlayerDataAsync(UUID playerUuid) {
        List<PetData> snapshot = new ArrayList<>(petManager.getPlayerPets(playerUuid));
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> writePlayerData(playerUuid, snapshot));
    }

    public void saveAllPlayerDataAsync() {
        Map<UUID, List<PetData>> snapshot = new HashMap<>();
        for (Map.Entry<UUID, List<PetData>> entry : petManager.getAllPlayerPets().entrySet()) {
            snapshot.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            for (Map.Entry<UUID, List<PetData>> entry : snapshot.entrySet()) {
                writePlayerData(entry.getKey(), entry.getValue());
            }
        });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void writePlayerData(UUID playerUuid, List<PetData> pets) {
        File file = new File(dataFolder, playerUuid.toString() + ".yml");
        if (pets.isEmpty()) {
            if (file.exists()) {
                file.delete();
            }
            return;
        }

        YamlConfiguration config = new YamlConfiguration();

        for (PetData pet : pets) {
            ConfigurationSection petSection = config.createSection(pet.getPetUuid().toString());
            pet.saveToConfig(petSection);
        }

        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("保存玩家数据失败:" + playerUuid + " - " + e.getMessage());
        }
    }
}
