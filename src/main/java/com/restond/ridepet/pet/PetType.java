package com.restond.ridepet.pet;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Horse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetType {
    private String id;
    private String name;
    private List<String> description;

    private String eggMaterial;
    private String eggDisplayName;
    private List<String> eggLore;

    private Horse.Color horseColor;
    private Horse.Style horseStyle;
    private double maxHealth;

    private final Map<Integer, LevelData> levels = new HashMap<>();

    /** 从配置文件加载 */
    public static PetType fromConfig(ConfigurationSection config) {
        PetType petType = new PetType();

        petType.id = config.getString("id");
        petType.name = config.getString("name");
        petType.description = config.getStringList("description");

        ConfigurationSection eggSection = config.getConfigurationSection("egg");
        if (eggSection != null) {
            petType.eggMaterial = eggSection.getString("material", "MONSTER_EGG");
            petType.eggDisplayName = eggSection.getString("display_name", "坐骑蛋");
            petType.eggLore = eggSection.getStringList("lore");
        }

        ConfigurationSection horseSection = config.getConfigurationSection("horse");
        if (horseSection != null) {
            petType.horseColor = Horse.Color.valueOf(horseSection.getString("color", "BROWN"));
            petType.horseStyle = Horse.Style.valueOf(horseSection.getString("style", "NONE"));
            petType.maxHealth = horseSection.getDouble("max_health", 20.0);
        }

        ConfigurationSection levelsSection = config.getConfigurationSection("levels");
        if (levelsSection != null) {
            for (String levelKey : levelsSection.getKeys(false)) {
                int level = Integer.parseInt(levelKey);
                ConfigurationSection levelSection = levelsSection.getConfigurationSection(levelKey);
                if (levelSection != null) {
                    LevelData levelData = LevelData.fromConfig(levelSection);
                    petType.levels.put(level, levelData);
                }
            }
        }
        return petType;
    }

    /** 创建宠物数据 */
    public PetData createPetData(int level) {
        PetData petData = new PetData();
        petData.setPetTypeId(this.id);
        petData.setCustomName(this.name);
        petData.setCurrentHealth(this.maxHealth);

        petData.setHorseColor(this.horseColor);
        petData.setHorseStyle(this.horseStyle);
        petData.setMaxHealth(this.maxHealth);

        LevelData levelData = levels.get(level);
        if (levelData == null && !levels.isEmpty()) {
            int fallbackLevel = levels.keySet().stream().min(Integer::compare).orElse(1);
            levelData = levels.get(fallbackLevel);
            level = fallbackLevel;
        }

        if (levelData != null) {
            petData.setAttributeLore(levelData.attributes);
            petData.setReviveTime(levelData.reviveTime);
            petData.setHorseSpeed(levelData.horseSpeed);
            petData.setHorseJump(levelData.horseJump);
        }

        petData.setLevel(level);
        return petData;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    public String getEggMaterial() {
        return eggMaterial;
    }

    public String getEggDisplayName() {
        return eggDisplayName;
    }

    public List<String> getEggLore() {
        return eggLore;
    }

    public Horse.Color getHorseColor() {
        return horseColor;
    }

    public Horse.Style getHorseStyle() {
        return horseStyle;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public LevelData getLevelData(int level) {
        return levels.get(level);
    }

    public Map<Integer, LevelData> getLevels() {
        return levels;
    }

    /** 等级数据 */
    public static class LevelData {
        private List<String> attributes;
        private int reviveTime;
        private double horseSpeed;
        private double horseJump;

        public static LevelData fromConfig(ConfigurationSection config) {
            LevelData data = new LevelData();

            data.attributes = config.getStringList("attributes");

            data.reviveTime = config.getInt("revive_time", 30);
            data.horseSpeed = config.getDouble("horse_speed", 0.2);
            data.horseJump = config.getDouble("horse_jump", 0.7);

            return data;
        }

        public List<String> getAttributes() {
            return attributes;
        }

        public int getReviveTime() {
            return reviveTime;
        }

        public double getHorseSpeed() {
            return horseSpeed;
        }

        public double getHorseJump() {
            return horseJump;
        }
    }
}
