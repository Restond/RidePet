package com.restond.ridepet.attribute;

import com.restond.ridepet.RidePet;
import github.saukiya.sxattribute.SXAttribute;
import github.saukiya.sxattribute.api.SXAttributeAPI;
import github.saukiya.sxattribute.data.attribute.SXAttributeData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/** SX-Attribute 属性桥接实现 */
public class SXAttributeBridge implements AttributeBridge {
    private final Plugin sxPlugin;
    private SXAttributeAPI api;
    private boolean enabled = false;

    /** 构造方法 */
    public SXAttributeBridge() {
        sxPlugin = Bukkit.getPluginManager().getPlugin("SX-Attribute");

        if (sxPlugin != null && sxPlugin.isEnabled()) {
            try {
                api = SXAttribute.getApi();
                if (api != null) {
                    enabled = true;
                }
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
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
            }

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        }
    }

    @Override
    public void removeAttributes(Player player) {
        if (!isEnabled()) {
            return;
        }

        try {
            api.removeEntityAPIData(RidePet.class, player.getUniqueId());

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        }
    }
}
