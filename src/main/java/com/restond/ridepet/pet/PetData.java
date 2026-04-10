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
import java.util.*;

public class PetData {
    private UUID petUuid;
    private String petTypeId;
    private String customName;
    private int level;
    private double currentHealth;
    private boolean isAlive;
    private long deathTime;
    private boolean isActive;
    private UUID entityUuid;

    private Horse.Color horseColor;
    private Horse.Style horseStyle;
    private double maxHealth;
    private double horseSpeed;
    private double horseJump;

    private List<String> attributeLore = new ArrayList<>();
    private int reviveTime;

    public PetData() {
        this.petUuid = UUID.randomUUID();
        this.level = 1;
        this.currentHealth = 20.0;
        this.isAlive = true;
        this.isActive = false;
        this.deathTime = 0;
    }

    public UUID getPetUuid() {
        return petUuid;
    }

    public void setPetUuid(UUID petUuid) {
        this.petUuid = petUuid;
    }

    public String getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(String petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public long getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(long deathTime) {
        this.deathTime = deathTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UUID getEntityUuid() {
        return entityUuid;
    }

    public void setEntityUuid(UUID entityUuid) {
        this.entityUuid = entityUuid;
    }

    public Horse.Color getHorseColor() {
        return horseColor;
    }

    public void setHorseColor(Horse.Color horseColor) {
        this.horseColor = horseColor;
    }

    public Horse.Style getHorseStyle() {
        return horseStyle;
    }

    public void setHorseStyle(Horse.Style horseStyle) {
        this.horseStyle = horseStyle;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getHorseSpeed() {
        return horseSpeed;
    }

    public void setHorseSpeed(double horseSpeed) {
        this.horseSpeed = horseSpeed;
    }

    public double getHorseJump() {
        return horseJump;
    }

    public void setHorseJump(double horseJump) {
        this.horseJump = horseJump;
    }

    public List<String> getAttributeLore() {
        return attributeLore;
    }

    public void setAttributeLore(List<String> attributeLore) {
        this.attributeLore = attributeLore;
    }

    public int getReviveTime() {
        return reviveTime;
    }

    public void setReviveTime(int reviveTime) {
        this.reviveTime = reviveTime;
    }

    public int getRemainingReviveTime() {
        if (isAlive) return 0;
        long currentTime = System.currentTimeMillis() / 1000;
        int remaining = reviveTime - (int)(currentTime - deathTime);
        return Math.max(0, remaining);
    }

    public boolean canRevive() {
        return isAlive || getRemainingReviveTime() <= 0;
    }

    public void markDeath() {
        this.isAlive = false;
        this.isActive = false;
        this.deathTime = System.currentTimeMillis() / 1000;
    }

    public void saveToConfig(ConfigurationSection config) {
        config.set("pet_uuid", petUuid.toString());
        config.set("pet_type_id", petTypeId);
        config.set("custom_name", customName);
        config.set("level", level);
        config.set("current_health", currentHealth);
        config.set("is_alive", isAlive);
        config.set("death_time", deathTime);

        config.set("horse_color", horseColor != null ? horseColor.name() : "BROWN");
        config.set("horse_style", horseStyle != null ? horseStyle.name() : "NONE");
        config.set("max_health", maxHealth);
        config.set("horse_speed", horseSpeed);
        config.set("horse_jump", horseJump);

        config.set("attribute_lore", attributeLore);
        config.set("revive_time", reviveTime);
    }

    public static PetData loadFromConfig(ConfigurationSection config) {
        PetData petData = new PetData();
        petData.setPetUuid(UUID.fromString(config.getString("pet_uuid")));


        petData.setPetTypeId(config.getString("pet_type_id"));
        petData.setCustomName(config.getString("custom_name"));
        petData.setLevel(config.getInt("level", 1));
        petData.setCurrentHealth(config.getDouble("current_health", 20.0));
        petData.setAlive(config.getBoolean("is_alive", true));
        petData.setDeathTime(config.getLong("death_time", 0));

        try {
            String colorName = config.getString("horse_color", "BROWN");
            petData.setHorseColor(Horse.Color.valueOf(colorName));
        } catch (IllegalArgumentException e) {
            petData.setHorseColor(Horse.Color.BROWN);
        }

        try {
            String styleName = config.getString("horse_style", "NONE");
            petData.setHorseStyle(Horse.Style.valueOf(styleName));
        } catch (IllegalArgumentException e) {
            petData.setHorseStyle(Horse.Style.NONE);
        }

        petData.setMaxHealth(config.getDouble("max_health", 20.0));
        petData.setHorseSpeed(config.getDouble("horse_speed", 0.2));
        petData.setHorseJump(config.getDouble("horse_jump", 0.7));

        petData.setAttributeLore(config.getStringList("attribute_lore"));
        petData.setReviveTime(config.getInt("revive_time", 30));

        return petData;
    }
}
