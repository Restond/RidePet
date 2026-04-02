package com.restond.ridepet.manager;

import com.restond.ridepet.RidePet;
import com.restond.ridepet.pet.PetData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/** 数据持久化管理器 */
public class DataManager {
    private final RidePet plugin;
    private final PetManager petManager;
    private final File dataFolder;

    public DataManager(RidePet ridePet, PetManager petManager) {
        this.plugin = ridePet;
        this.petManager = petManager;
        this.dataFolder = new File(plugin.getDataFolder(), "data");
    }

    /** 初始化 */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void init() {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        loadAllPlayerData();
        plugin.getLogger().info("数据管理器初始化完毕");
    }

    /** 加载所有玩家数据 */
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

    /** 加载单个玩家数据 */
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

    /** 保存玩家数据 */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void savePlayerData(UUID playerUuid) {
        List<PetData> pets = petManager.getPlayerPets(playerUuid);

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

    /** 保存所有玩家数据 */
    public void saveAllPlayerData() {
        for (Map.Entry<UUID, List<PetData>> entry : petManager.getAllPlayerPets().entrySet()) {
            savePlayerData(entry.getKey());
        }
    }

    /** 异步保存玩家数据 */
    public void savePlayerDataAsync(UUID playerUuid) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> savePlayerData(playerUuid));
    }

    /** 异步保存所有数据 */
    public void saveAllPlayerDataAsync() {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, this::saveAllPlayerData);
    }
}
