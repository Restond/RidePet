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

package com.restond.ridepet.attribute;

import com.restond.ridepet.RidePet;
import github.saukiya.sxattribute.SXAttribute;
import github.saukiya.sxattribute.api.SXAttributeAPI;
import github.saukiya.sxattribute.data.attribute.SXAttributeData;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.List;

public class SXAttributeBridge implements AttributeBridge {
    private final Plugin sxPlugin;
    private SXAttributeAPI api;
    private boolean enabled = false;

    public SXAttributeBridge() {
        sxPlugin = Bukkit.getPluginManager().getPlugin("SX-Attribute");

        if (sxPlugin != null && sxPlugin.isEnabled()) {
            try {
                api = SXAttribute.getApi();
                if (api != null) {
                    enabled = true;
                }
            } catch (Exception e) {
                RidePet.getInstance().getLogger().warning("SX-Attribute 操作失败: " + e.getMessage());
            }
        }
    }

    @Override
    public String getName() {
        return "SX-Attribute";
    }

    @Override
    public boolean isEnabled() {
        return enabled && sxPlugin != null && sxPlugin.isEnabled();
    }

    @Override
    public void applyAttributes(Player player, List<String> loreList) {
        if (!isEnabled() || loreList == null || loreList.isEmpty()) {
            return;
        }

        try {
            SXAttributeData data = api.getLoreData(null, null, loreList);

            if (data != null) {
                api.setEntityAPIData(RidePet.class, player.getUniqueId(), data);
                api.updateStats(player);
            }

        } catch (Exception e) {
            RidePet.getInstance().getLogger().warning("SX-Attribute 操作失败: " + e.getMessage());
        }
    }

    @Override
    public void removeAttributes(Player player) {
        if (!isEnabled()) {
            return;
        }

        try {
            api.removeEntityAPIData(RidePet.class, player.getUniqueId());
            api.updateStats(player);
        } catch (Exception e) {
            RidePet.getInstance().getLogger().warning("SX-Attribute 操作失败: " + e.getMessage());
        }
    }

    @Override
    public double getPlayerMovementSpeed(Player player) {
        if (!isEnabled()) {
            return -1;
        }

        try {
            return player.getWalkSpeed();
        } catch (Exception e) {
            return -1;
        }
    }
}
