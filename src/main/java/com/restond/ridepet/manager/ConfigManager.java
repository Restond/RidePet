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

import com.restond.ridepet.pet.PetType;
import com.restond.ridepet.util.MessageUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConfigManager {
    private final JavaPlugin plugin;
    private final Map<String, PetType> petTypes = new HashMap<>();
    private File petsFolder;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void init() {
        plugin.saveDefaultConfig();

        petsFolder = new File(plugin.getDataFolder(), "pets");
        if (!petsFolder.exists()) {
            petsFolder.mkdirs();
            copyDefaultPetConfigs();
        }
        loadAllPetTypes();
        MessageUtil.init(plugin);
        plugin.getLogger().info("配置管理器初始化完成，已加载 " + petTypes.size() + " 种宠物");
    }

    private void copyDefaultPetConfigs() {
        copyResource();
    }

    private void copyResource() {
        File targetFile = new File(petsFolder, "default.yml");
        if (!targetFile.exists()) {
            plugin.saveResource("pets/default.yml", false);
        }
    }

    private void loadAllPetTypes() {
        petTypes.clear();

        File[] files = petsFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files == null) return;

        for (File file : files) {
            try {
                loadPetType(file);
            } catch (Exception e) {
                plugin.getLogger().warning("加载宠物配置失败: " + file.getName() + " - " + e.getMessage());
            }
        }
    }

    private void loadPetType(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        PetType petType = PetType.fromConfig(config);

        if (petType.getId() != null) {
            petTypes.put(petType.getId(), petType);
            plugin.getLogger().info("已加载宠物类型: " + petType.getId());
        } else {
            plugin.getLogger().warning("宠物配置缺少 ID 字段: " + file.getName());
        }
    }

    public void reload() {
        plugin.reloadConfig();
        loadAllPetTypes();
        MessageUtil.reloadConfig();

        plugin.getLogger().info("配置已重载，当前宠物数量: " + petTypes.size());
    }

    public PetType getPetType(String id) {
        return petTypes.get(id);
    }

    public Set<String> getAllPetTypeIds() {
        return petTypes.keySet();
    }

    public Map<String, PetType> getAllPetTypes() {
        return petTypes;
    }

    public boolean hasPetType(String id) {
        return petTypes.containsKey(id);
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }
}
