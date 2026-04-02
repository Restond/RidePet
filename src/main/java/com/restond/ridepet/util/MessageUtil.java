package com.restond.ridepet.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/** 消息工具 */
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

    /** 获取消息 */
    public static String getMessage(String key) {
        String message = plugin.getConfig().getString("messages." + key, "");
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }

    /** 获取消息（带占位符） */
    public static String getMessage(String key, String placeholder, String value) {
        String message = plugin.getConfig().getString("messages." + key, "");
        if (placeholder != null && value != null) {
            message = message.replace("{" + placeholder + "}", value);
        }
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }

    /** 获取原始消息 */
    public static String getRawMessage(String key) {
        String message = plugin.getConfig().getString("messages." + key, "");
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /** 发送消息 */
    public static void send(org.bukkit.entity.Player player, String key) {
        player.sendMessage(getMessage(key));
    }

    /** 发送消息（带占位符） */
    public static void send(Player player, String key, String placeholder, String value) {
        player.sendMessage(getMessage(key, placeholder, value));
    }

    /** 颜色转换 */
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
