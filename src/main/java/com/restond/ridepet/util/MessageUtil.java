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

package com.restond.ridepet.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageUtil {
    private static JavaPlugin plugin;
    private static String prefix;

    public static void init(JavaPlugin pluginInstance) {
        plugin = pluginInstance;
        reloadConfig();
    }

    public static void reloadConfig() {
        FileConfiguration config = plugin.getConfig();
        prefix = config.getString("messages.prefix", "§6[坐骑] §r");
    }

    public static String getMessage(String key) {
        String message = plugin.getConfig().getString("messages." + key, "");
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }

    public static String getMessage(String key, String placeholder, String value) {
        String message = plugin.getConfig().getString("messages." + key, "");
        if (placeholder != null && value != null) {
            message = message.replace("{" + placeholder + "}", value);
        }
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }

    public static String getRawMessage(String key) {
        String message = plugin.getConfig().getString("messages." + key, "");
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void send(org.bukkit.entity.Player player, String key) {
        player.sendMessage(getMessage(key));
    }

    public static void send(Player player, String key, String placeholder, String value) {
        player.sendMessage(getMessage(key, placeholder, value));
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
