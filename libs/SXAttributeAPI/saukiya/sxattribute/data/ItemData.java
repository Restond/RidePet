/*     */ package saukiya.sxattribute.data;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ 
/*     */ class ItemData {
/*     */   private String name;
/*     */   private ItemStack item;
/*     */   private boolean importItem;
/*     */   
/*     */   public String getName() {
/*  25 */     return this.name;
/*     */   } private List<String> ids; private List<String> enchantList; private int hashCode; public ItemStack getItem() {
/*  27 */     return this.item;
/*     */   } public boolean isImportItem() {
/*  29 */     return this.importItem;
/*     */   } public List<String> getIds() {
/*  31 */     return this.ids;
/*     */   } public List<String> getEnchantList() {
/*  33 */     return this.enchantList;
/*     */   } public int getHashCode() {
/*  35 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   ItemData(String name, ItemStack item, boolean importItem, List<String> ids, List<String> enchantList, int hashCode) {
/*  39 */     this.name = name;
/*  40 */     this.item = item;
/*  41 */     this.importItem = importItem;
/*  42 */     this.ids = ids;
/*  43 */     this.enchantList = enchantList;
/*  44 */     this.hashCode = hashCode;
/*     */   }
/*     */   
/*     */   public ItemStack getItem(SXAttribute plugin, Player player) {
/*  48 */     if (this.importItem) {
/*  49 */       return getItem().clone();
/*     */     }
/*  51 */     ItemStack item = getItem().clone();
/*  52 */     Map<String, String> lockRandomMap = new HashMap<>();
/*  53 */     String id = plugin.getRandomStringManager().processRandomString(this.name, getIds().get(SXAttribute.getRandom().nextInt(getIds().size())), lockRandomMap);
/*  54 */     int itemMaterial = 260, itemDurability = 0;
/*  55 */     if (id != null) {
/*  56 */       if (id.contains(":")) {
/*  57 */         String[] idSplit = id.split(":");
/*  58 */         itemMaterial = Integer.valueOf(idSplit[0]).intValue();
/*  59 */         itemDurability = Integer.valueOf(idSplit[1]).intValue();
/*     */       } else {
/*  61 */         itemMaterial = Integer.valueOf(id).intValue();
/*     */       } 
/*     */     }
/*  64 */     item.setTypeId(itemMaterial);
/*  65 */     item.setDurability((short)itemDurability);
/*  66 */     if (item.getType().name().equals(Material.AIR.name())) {
/*  67 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cItem §4" + this.name + "§c Error ID! Please Check ID: §4" + id + "§c!");
/*  68 */       return null;
/*     */     } 
/*  70 */     ItemMeta meta = item.getItemMeta();
/*  71 */     if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
/*  72 */       if (meta.hasDisplayName()) {
/*  73 */         String itemName = plugin.getRandomStringManager().processRandomString(this.name, meta.getDisplayName(), lockRandomMap);
/*  74 */         meta.setDisplayName(itemName.replace("&", "§").replace("%DeleteLore%", ""));
/*     */       } 
/*  76 */       List<String> loreList = meta.getLore();
/*  77 */       for (int i = loreList.size() - 1; i >= 0; i--) {
/*  78 */         String lore = plugin.getRandomStringManager().processRandomString(this.name, loreList.get(i), lockRandomMap);
/*     */         
/*  80 */         if (lore.contains(Config.getConfig().getString("Condition.Durability.Name")))
/*     */         {
/*  82 */           if (item.getType().getMaxDurability() > 0 && 
/*  83 */             !SubCondition.getUnbreakable(meta)) {
/*  84 */             Repairable repairable = (Repairable)meta;
/*  85 */             repairable.setRepairCost(999);
/*  86 */             meta = (ItemMeta)repairable;
/*  87 */             int durability = SubCondition.getDurability(lore);
/*  88 */             int maxDurability = SubCondition.getMaxDurability(lore);
/*  89 */             int maxDefaultDurability = item.getType().getMaxDurability();
/*  90 */             int defaultDurability = (int)(durability / maxDurability * maxDefaultDurability);
/*  91 */             item.setDurability((short)(maxDefaultDurability - defaultDurability));
/*     */           } 
/*     */         }
/*     */         
/*  95 */         if (lore.contains("%DeleteLore%")) {
/*  96 */           loreList.remove(i);
/*     */         } else {
/*  98 */           lore = lore.replace("&", "§");
/*  99 */           if (lore.contains("\n") || lore.contains("/n")) {
/* 100 */             loreList.remove(i);
/* 101 */             loreList.addAll(i, Arrays.asList(lore.replace("/n", "\n").split("\n")));
/*     */           } else {
/* 103 */             loreList.set(i, lore);
/*     */           } 
/*     */         } 
/*     */       } 
/* 107 */       if (SXAttribute.isPlaceholder() && player != null) {
/* 108 */         loreList = PlaceholderAPI.setPlaceholders(player, loreList);
/*     */       }
/* 110 */       meta.setLore(loreList);
/* 111 */       item.setItemMeta(meta);
/*     */     } 
/* 113 */     if (getEnchantList() != null && getEnchantList().size() > 0) {
/* 114 */       List<String> enchantList = new ArrayList<>();
/* 115 */       for (String enchantName : getEnchantList()) {
/* 116 */         enchantName = plugin.getRandomStringManager().processRandomString(this.name, enchantName, lockRandomMap);
/* 117 */         if (enchantName.contains("\n") || enchantName.contains("/n")) {
/* 118 */           enchantList.addAll(Arrays.asList(enchantName.replace("/n", "\n").split("\n"))); continue;
/*     */         } 
/* 120 */         enchantList.add(enchantName);
/*     */       } 
/*     */       
/* 123 */       for (String enchantName : enchantList) {
/* 124 */         if (enchantName.contains(":") && (enchantName.split(":")).length > 1) {
/* 125 */           Enchantment enchant = Enchantment.getByName(enchantName.split(":")[0]);
/* 126 */           int level = Integer.valueOf(enchantName.split(":")[1].replaceAll("[^0-9]", "")).intValue();
/* 127 */           if (enchant != null) {
/* 128 */             if (level > 0)
/* 129 */               meta.addEnchant(enchant, level, true); 
/*     */             continue;
/*     */           } 
/* 132 */           Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c物品: §4" + this.name + " §c的附魔: §4" + enchantName.split(":")[0] + "§c 不是正常的附魔名称！");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     item.setItemMeta(meta);
/*     */     
/* 139 */     if (lockRandomMap.size() > 0) {
/* 140 */       List<String> list = new ArrayList<>();
/* 141 */       lockRandomMap.forEach((key, value) -> list.add(key + "§e§l§k|§e§r" + value));
/* 142 */       plugin.getItemUtil().setNBTList(item, "LockRandomMap", list);
/*     */     } 
/* 144 */     if (Config.isDamageGauges()) {
/* 145 */       return plugin.getItemUtil().setAttackSpeed(item, new double[0]);
/*     */     }
/* 147 */     return item;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\ItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */