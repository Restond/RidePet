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
    private long expireMillis;

    private final Map<Integer, LevelData> levels = new HashMap<>();

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

        ConfigurationSection optionsSection = config.getConfigurationSection("options");
        if (optionsSection != null) {
            String durationStr = optionsSection.getString("duration", "");
            if (durationStr != null && !durationStr.isEmpty()) {
                petType.expireMillis = parseDuration(durationStr);
            }
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

        petData.setAcquireTime(System.currentTimeMillis());
        petData.setExpireMillis(this.expireMillis);

        petData.setLevel(level);
        return petData;
    }

    private static long parseDuration(String duration) {
        long millis = 0;
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("(\\d+)([dhm])").matcher(duration.toLowerCase());
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            switch (matcher.group(2)) {
                case "d":
                    millis += (long) value * 24 * 60 * 60 * 1000;
                    break;
                case "h":
                    millis += (long) value * 60 * 60 * 1000;
                    break;
                case "m":
                    millis += (long) value * 60 * 1000;
                    break;
            }
        }
        return millis;
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

    public long getExpireMillis() {
        return expireMillis;
    }

    public LevelData getLevelData(int level) {
        return levels.get(level);
    }

    public Map<Integer, LevelData> getLevels() {
        return levels;
    }

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
