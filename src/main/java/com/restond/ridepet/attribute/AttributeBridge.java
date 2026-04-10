package com.restond.ridepet.attribute;

import org.bukkit.entity.Player;
import java.util.List;

/** 属性桥接接口 */
public interface AttributeBridge {

    /** 获取属性插件名称 */
    String getName();

    /** 检查属性插件是否已启用 */
    boolean isEnabled();

    /** 应用属性加成 */
    void applyAttributes(Player player, List<String> loreList);

    /** 移除属性加成 */
    void removeAttributes(Player player);

    double getPlayerMovementSpeed(Player player);
}
