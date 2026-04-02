package com.restond.ridepet.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.List;

/** 物品构建工具 */
public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    /** 设置名称 */
    public ItemBuilder setDisplayName(String name) {
        meta.setDisplayName(MessageUtil.color(name));
        return this;
    }

    /** 设置 Lore */
    public ItemBuilder setLore(String... lore) {
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    /** 设置 Lore */
    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    /** 设置数量 */
    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /** 设置耐久度 */
    public ItemBuilder setDurability(short durability) {
        item.setDurability(durability);
        return this;
    }

    /** 构建 */
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
