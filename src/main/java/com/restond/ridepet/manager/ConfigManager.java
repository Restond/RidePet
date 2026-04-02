package com.restond.ridepet.manager;

import com.restond.ridepet.pet.PetType;
import com.restond.ridepet.util.MessageUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** 配置管理器 */
public class ConfigManager {
    private final JavaPlugin plugin;
    private final Map<String, PetType> petTypes = new HashMap<>();
    private File petsFolder;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /** 初始化配置 */
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

    /** 复制默认宠物配置 */
    private void copyDefaultPetConfigs() {
        copyResource();
    }

    /** 复制资源文件 */
    private void copyResource() {
        File targetFile = new File(petsFolder, "default.yml");
        if (!targetFile.exists()) {
            plugin.saveResource("pets/default.yml", false);
        }
    }

    /** 加载所有宠物配置 */
    private void loadAllPetTypes() {
        petTypes.clear();

        File[] files = petsFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files == null) return;

        for (File file : files) {
            try {
                loadPetType(file);
            } catch (Exception e) {
                plugin.getLogger().warning("加载宠物配置失败: " + file.getName());
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
            }
        }
    }

    /** 加载单个宠物配置 */
    private void loadPetType(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {
            config.load(file);
        } catch (Exception e) {
            plugin.getLogger().warning("加载宠物配置失败: " + file.getName() + " - " + e.getMessage());
            return;
        }

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

    /** 获取宠物类型 */
    public PetType getPetType(String id) {
        return petTypes.get(id);
    }

    /** 获取所有宠物类型ID */
    public Set<String> getAllPetTypeIds() {
        return petTypes.keySet();
    }

    /** 获取所有宠物类型 */
    public Map<String, PetType> getAllPetTypes() {
        return petTypes;
    }

    /** 检查宠物类型是否存在 */
    public boolean hasPetType(String id) {
        return petTypes.containsKey(id);
    }

    /** 获取主配置 */
    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }
}
