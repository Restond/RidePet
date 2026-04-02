/*     */ package saukiya.sxattribute.data;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.ItemData;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*     */ import github.saukiya.sxattribute.data.condition.sub.DurabilityCondition;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class ItemDataManager {
/*  34 */   private static final List<String> COLOR_LIST = Arrays.asList(new String[] { "§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9" });
/*  35 */   private static final List<String> COLOR_REPLACE_LIST = Arrays.asList(new String[] { "%零%", "%一%", "%二%", "%三%", "%四%", "%五%", "%六%", "%七%", "%八%", "%九%" });
/*     */   
/*  37 */   private final File itemFiles = new File(SXAttribute.getPluginFile(), "Item");
/*  38 */   private final File itemDefaultFile = new File(this.itemFiles, "Default" + File.separator + "Default.yml");
/*  39 */   private final File itemImportFile = new File(this.itemFiles, "ImportItem.yml");
/*  40 */   private final Map<String, ItemData> itemMap = new HashMap<>();
/*     */   private final SXAttribute plugin;
/*     */   
/*     */   public ItemDataManager(SXAttribute plugin) throws IOException, InvalidConfigurationException {
/*  44 */     this.plugin = plugin;
/*  45 */     loadItemData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String clearColor(String lore) {
/*  55 */     for (int i = 0; i < 10; i++) {
/*  56 */       lore = lore.replace(COLOR_LIST.get(i), COLOR_REPLACE_LIST.get(i));
/*     */     }
/*  58 */     return lore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replaceColor(String lore) {
/*  68 */     for (int i = 0; i < 10; i++) {
/*  69 */       lore = lore.replace(COLOR_REPLACE_LIST.get(i), COLOR_LIST.get(i));
/*     */     }
/*  71 */     return lore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getSellValue(ItemStack item) {
/*  81 */     double sell = -0.0D;
/*  82 */     List<String> loreList = item.getItemMeta().getLore();
/*  83 */     for (String lore : loreList) {
/*  84 */       if (lore.contains(Config.getConfig().getString("Condition.Sell.Name"))) {
/*  85 */         sell = Double.valueOf(SubAttribute.getNumber(lore)).doubleValue();
/*     */       }
/*     */     } 
/*  88 */     return sell;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadItemData() throws IOException, InvalidConfigurationException {
/*  98 */     loadItemMap();
/*  99 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load §c" + this.itemMap.size() + " §rItems");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getItemList() {
/* 108 */     return this.itemMap.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadItemMap() throws IOException, InvalidConfigurationException {
/* 118 */     this.itemMap.clear();
/* 119 */     if (!this.itemFiles.exists() || ((File[])Objects.requireNonNull((T)this.itemFiles.listFiles())).length == 0) {
/* 120 */       createDefaultItemData();
/*     */     }
/* 122 */     loadItem(this.itemFiles);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadItem(File files) throws IOException, InvalidConfigurationException {
/* 133 */     for (File file : (File[])Objects.<File[]>requireNonNull(files.listFiles())) {
/* 134 */       if (file.isDirectory()) {
/* 135 */         loadItem(file);
/* 136 */       } else if (!file.getName().equals(this.itemImportFile.getName())) {
/* 137 */         YamlConfiguration itemYml = new YamlConfiguration();
/* 138 */         itemYml.load(file);
/* 139 */         for (String name : itemYml.getKeys(false)) {
/* 140 */           if (this.itemMap.containsKey(name)) {
/* 141 */             Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cDon't Repeat Item Name: §4" + file.getName() + File.separator + name + " §c!");
/*     */             continue;
/*     */           } 
/* 144 */           String itemName = itemYml.getString(name + ".Name");
/* 145 */           List<String> ids = new ArrayList<>();
/* 146 */           Object idObject = itemYml.get(name + ".ID");
/* 147 */           if (idObject instanceof List) {
/* 148 */             ids = itemYml.getStringList(name + ".ID");
/*     */           } else {
/* 150 */             ids.add(itemYml.getString(name + ".ID", "260"));
/*     */           } 
/* 152 */           if (ids.size() == 0) {
/* 153 */             Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cItem: §4" + name + " §cNull ID!");
/* 154 */             ids.add("260");
/*     */           } 
/* 156 */           String id = this.plugin.getRandomStringManager().processRandomString(itemName, ids.get(SXAttribute.getRandom().nextInt(ids.size())), new HashMap<>());
/* 157 */           List<String> itemLore = itemYml.getStringList(name + ".Lore");
/* 158 */           List<String> enchantList = itemYml.getStringList(name + ".EnchantList");
/* 159 */           List<String> itemFlagList = itemYml.getStringList(name + ".ItemFlagList");
/* 160 */           Boolean unbreakable = Boolean.valueOf(itemYml.getBoolean(name + ".Unbreakable"));
/* 161 */           String colorStr = itemYml.getString(name + ".Color");
/* 162 */           String skullName = itemYml.getString(name + ".SkullName");
/*     */           
/* 164 */           ItemStack item = this.plugin.getItemUtil().getItemStack(itemName, id, itemLore, itemFlagList, unbreakable, colorStr, skullName);
/* 165 */           if (item == null) {
/*     */             continue;
/*     */           }
/* 168 */           int hashCode = item.getType().name().hashCode() / 100 + item.getDurability();
/*     */           
/* 170 */           if (itemName != null) {
/* 171 */             hashCode += itemName.hashCode() / 100;
/*     */           }
/*     */           
/* 174 */           if (itemLore != null) {
/* 175 */             hashCode += itemLore.hashCode() / 100;
/*     */           }
/*     */           
/* 178 */           if (item.getEnchantments().size() > 0) {
/* 179 */             hashCode += enchantList.hashCode() / 100;
/*     */           }
/*     */           
/* 182 */           if (item.getItemMeta().getItemFlags().size() > 0) {
/* 183 */             hashCode += item.getItemMeta().getItemFlags().hashCode() / 100;
/*     */           }
/*     */           
/* 186 */           if (colorStr != null) {
/* 187 */             Color color = Color.fromRGB(Integer.valueOf(colorStr.split(",")[0]).intValue(), Integer.valueOf(colorStr.split(",")[1]).intValue(), Integer.valueOf(colorStr.split(",")[2]).intValue());
/* 188 */             hashCode += color.hashCode() / 100;
/*     */           } 
/*     */ 
/*     */           
/* 192 */           if (Config.isClearDefaultAttributePlugin() && item.getItemMeta().hasLore()) {
/* 193 */             this.plugin.getItemUtil().clearAttribute(item);
/*     */           }
/*     */           
/* 196 */           if (hashCode != item.getType().name().hashCode() / 100 + item.getDurability()) {
/* 197 */             item = this.plugin.getItemUtil().setNBT(this.plugin.getItemUtil().setNBT(item, "Name", name), "HasCode", String.valueOf(hashCode));
/*     */           }
/*     */           
/* 200 */           this.itemMap.put(name, new ItemData(name, item, false, ids, enchantList, hashCode));
/*     */         } 
/*     */       } else {
/* 203 */         YamlConfiguration itemYml = new YamlConfiguration();
/* 204 */         itemYml.load(file);
/* 205 */         for (String name : itemYml.getKeys(false)) {
/* 206 */           if (this.itemMap.containsKey(name)) {
/* 207 */             Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cDon't Repeat Item Name: §4" + file.getName() + File.separator + name + " §c!");
/*     */             continue;
/*     */           } 
/* 210 */           ItemStack item = itemYml.getItemStack(name);
/* 211 */           if (item != null) {
/* 212 */             this.itemMap.put(name, new ItemData(name, item, true, Collections.singletonList(String.valueOf(item.getTypeId())), null, 0));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createDefaultItemData() throws IOException {
/* 225 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cCreate Item/Default.yml");
/* 226 */     YamlConfiguration itemData = new YamlConfiguration();
/* 227 */     itemData.set("默认一.Name", "<s:DefaultPrefix>&c炎之洗礼<s:DefaultSuffix> <s:<l:品质>Color><l:品质>");
/* 228 */     itemData.set("默认一.ID", Collections.singletonList("<s:<l:职业>ID>"));
/* 229 */     itemData.set("默认一.Lore", Arrays.asList(new String[] { "&6品质等级: <s:<l:品质>Color><l:品质>", "<s:<l:品质>职业>", "&6物品类型: 主武器", "&6限制等级: <s:<l:品质>等级-10>级", "&c攻击力: +<s:<l:品质>攻击-10>", "<s:<l:品质>攻一-10>", "<s:<l:品质>攻二-10>", "<s:<l:品质>攻三-10>", "<l:材质>", "<s:<l:品质>宝石孔>", "&7耐久度: <r:100_<s:<l:品质>耐久最低>>/<s:<l:品质>耐久>", "<s:<l:品质>绑定>", "&a获得时间: <t:0>", "&a到期时间: <t:600>" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     itemData.set("默认一.EnchantList", Collections.singletonList("<s:<l:职业>附魔>"));
/* 244 */     itemData.set("默认一.Unbreakable", Boolean.valueOf(false));
/* 245 */     itemData.set("默认二.Name", "&c机械轻羽之靴");
/* 246 */     itemData.set("默认二.ID", Integer.valueOf(301));
/* 247 */     itemData.set("默认二.Lore", Arrays.asList(new String[] { "&6物品类型: 靴子", "&b防御力: +15", "&c生命上限: +2000", "&d移动速度: +100%", "&d闪避几率: +20%", "&2生命恢复: +10", "&e经验加成: +20%", "&6限制等级: <r:50_100>级", "&r", "<s:DefaultLore>", "&r", "&e出售价格: 250" }));
/* 248 */     itemData.set("默认二.EnchantList", Collections.singletonList("DURABILITY:1"));
/* 249 */     itemData.set("默认二.ItemFlagList", Arrays.asList(new String[] { "HIDE_ENCHANTS", "HIDE_UNBREAKABLE" }));
/* 250 */     itemData.set("默认二.Unbreakable", Boolean.valueOf(true));
/* 251 */     itemData.set("默认二.Color", "128,128,128");
/* 252 */     itemData.set("默认三.Name", "&b雷霆领主项链");
/* 253 */     itemData.set("默认三.ID", Integer.valueOf(287));
/* 254 */     itemData.set("默认三.Lore", Arrays.asList(new String[] { "&6物品类型: 项链", "&c生命上限: +200", "&d移动速度: +50%", "&d雷霆几率: +20%", "&6限制等级: <r:20_30>级", "&r", "&e出售价格: 500" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     itemData.set("默认三.EnchantList", Collections.singletonList("DURABILITY:1"));
/* 261 */     itemData.set("默认三.ItemFlagList", Collections.singletonList("HIDE_ENCHANTS"));
/* 262 */     itemData.save(this.itemDefaultFile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItem(String itemName) {
/* 272 */     return this.itemMap.containsKey(itemName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItem(String itemName, Player player) {
/* 284 */     ItemData itemData = this.itemMap.get(itemName);
/* 285 */     if (itemData != null) {
/* 286 */       return itemData.getItem(this.plugin, player);
/*     */     }
/* 288 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateItem(ItemStack item, Player player) {
/* 300 */     if (item != null && this.plugin.getItemUtil().isNBT(item, "Name")) {
/* 301 */       String dataName = this.plugin.getItemUtil().getNBT(item, "Name");
/*     */       
/* 303 */       ItemData itemData = this.itemMap.get(dataName);
/* 304 */       if (itemData != null && !itemData.isImportItem()) {
/*     */         
/* 306 */         int hasCode = Integer.valueOf(Objects.<String>requireNonNull(this.plugin.getItemUtil().getNBT(item, "HasCode"))).intValue();
/* 307 */         ItemStack dataItem = itemData.getItem(this.plugin, player);
/* 308 */         assert dataItem != null;
/* 309 */         ItemMeta dataMeta = dataItem.getItemMeta();
/*     */         
/* 311 */         int dataHasCode = itemData.getHashCode();
/*     */         
/* 313 */         if (dataHasCode != hasCode) {
/*     */           
/* 315 */           this.plugin.getItemUtil().setNBT(item, "HasCode", String.valueOf(dataHasCode));
/*     */           
/* 317 */           List<String> dataItemLore = dataMeta.getLore();
/* 318 */           for (int i = 0; i < dataItemLore.size(); i++) {
/*     */             
/* 320 */             if (((String)dataItemLore.get(i)).contains(Config.getConfig().getString("Condition.Durability.Name"))) {
/* 321 */               List<String> itemLoreList = item.getItemMeta().getLore();
/* 322 */               for (String itemLote : itemLoreList) {
/*     */                 
/* 324 */                 if (itemLote.contains(Config.getConfig().getString("Condition.Durability.Name"))) {
/* 325 */                   double maxDefaultDurability = SubCondition.getMaxDurability(itemLote);
/* 326 */                   double defaultDurability = SubCondition.getDurability(itemLote);
/* 327 */                   String lore = dataItemLore.get(i);
/* 328 */                   double maxDurability = SubCondition.getMaxDurability(lore);
/*     */                   
/* 330 */                   lore = replaceColor(clearColor(lore).replaceFirst(String.valueOf(DurabilityCondition.getDurability(lore)), String.valueOf((int)(defaultDurability / maxDefaultDurability * maxDurability))));
/* 331 */                   dataItemLore.set(i, lore);
/* 332 */                   dataMeta.setLore(dataItemLore);
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 341 */           item.setItemMeta(dataMeta);
/*     */           
/* 343 */           item.setType(dataItem.getType());
/*     */           
/* 345 */           if (dataItem.getType().getMaxDurability() == 0) {
/* 346 */             item.setDurability(dataItem.getDurability());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void importItem(String itemName, ItemStack itemStack) throws IOException, InvalidConfigurationException {
/* 355 */     YamlConfiguration itemData = new YamlConfiguration();
/* 356 */     if (this.itemImportFile.exists() && !this.itemImportFile.isDirectory()) {
/* 357 */       itemData.load(this.itemImportFile);
/*     */     }
/* 359 */     itemData.set(itemName, itemStack);
/* 360 */     itemData.save(this.itemImportFile);
/* 361 */     this.itemMap.put(itemName, new ItemData(itemName, itemStack, true, Collections.singletonList(String.valueOf(itemStack.getTypeId())), null, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveItem(String itemName, ItemStack itemStack) throws IOException, InvalidConfigurationException {
/* 374 */     ItemMeta itemMeta = itemStack.getItemMeta();
/* 375 */     itemStack.getType();
/* 376 */     String name = null;
/* 377 */     if (itemMeta.hasDisplayName()) {
/* 378 */       name = itemMeta.getDisplayName().replace("§", "&");
/*     */     }
/* 380 */     String id = String.valueOf(itemStack.getTypeId());
/* 381 */     if (itemStack.getType().getMaxDurability() != 0) {
/* 382 */       id = id + ":" + itemStack.getDurability();
/*     */     }
/* 384 */     List<String> lore = itemMeta.getLore();
/* 385 */     if (lore != null) {
/* 386 */       for (int i = 0; i < lore.size(); i++) {
/* 387 */         lore.set(i, ((String)lore.get(i)).replace("§", "&"));
/*     */       }
/*     */     }
/* 390 */     YamlConfiguration itemData = new YamlConfiguration();
/* 391 */     if (this.itemDefaultFile.exists() && !this.itemDefaultFile.isDirectory()) {
/* 392 */       itemData.load(this.itemDefaultFile);
/*     */     }
/* 394 */     List<String> enchantList = new ArrayList<>();
/* 395 */     if (itemMeta.hasEnchants()) {
/* 396 */       for (Enchantment enchant : itemMeta.getEnchants().keySet()) {
/* 397 */         enchantList.add(enchant.getName() + ":" + itemMeta.getEnchants().get(enchant));
/*     */       }
/*     */     }
/* 400 */     List<String> itemFlagList = new ArrayList<>();
/* 401 */     if (itemMeta.getItemFlags().size() > 0) {
/* 402 */       for (ItemFlag itemFlag : itemMeta.getItemFlags()) {
/* 403 */         itemFlagList.add(itemFlag.name());
/*     */       }
/*     */     }
/* 406 */     String color = null;
/* 407 */     if (itemMeta instanceof LeatherArmorMeta) {
/* 408 */       Color c = ((LeatherArmorMeta)itemMeta).getColor();
/* 409 */       color = c.getRed() + "," + c.getGreen() + "," + c.getBlue();
/*     */     } 
/* 411 */     String skullName = null;
/* 412 */     if (itemMeta instanceof SkullMeta) {
/* 413 */       skullName = ((SkullMeta)itemMeta).getOwner();
/*     */     }
/*     */     
/* 416 */     itemData.set(itemName + ".Name", name);
/* 417 */     itemData.set(itemName + ".ID", id);
/* 418 */     if (lore != null) itemData.set(itemName + ".Lore", lore); 
/* 419 */     if (enchantList.size() > 0) itemData.set(itemName + ".EnchantList", enchantList); 
/* 420 */     if (itemFlagList.size() > 0) itemData.set(itemName + ".ItemFlagList", itemFlagList); 
/* 421 */     itemData.set(itemName + ".Unbreakable", Boolean.valueOf(SubCondition.getUnbreakable(itemMeta)));
/* 422 */     itemData.set(itemName + ".Color", color);
/* 423 */     itemData.set(itemName + ".SkullName", skullName);
/* 424 */     itemData.save(this.itemDefaultFile);
/* 425 */     loadItemMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendItemMapToPlayer(CommandSender sender, String... searchs) {
/* 435 */     if (sender instanceof Player) {
/* 436 */       sender.sendMessage("§e物品列表§b - §e点击获取");
/*     */     } else {
/* 438 */       sender.sendMessage("§e物品列表");
/*     */     } 
/* 440 */     String search = "";
/* 441 */     if (searchs.length > 0) {
/* 442 */       search = searchs[0];
/* 443 */       sender.sendMessage("§c正在搜索关键词: " + search);
/*     */     } 
/* 445 */     int z = 1;
/* 446 */     for (String key : this.itemMap.keySet()) {
/* 447 */       ItemData itemData = this.itemMap.get(key);
/* 448 */       ItemStack item = itemData.getItem();
/*     */       
/* 450 */       ItemMeta itemMeta = item.getItemMeta();
/* 451 */       String itemName = item.getType().name();
/* 452 */       if (itemMeta.hasDisplayName()) {
/* 453 */         itemName = itemMeta.getDisplayName();
/*     */       }
/*     */       
/* 456 */       String str = "§b" + z + " - §a" + key + " §7(" + itemName + "§7)" + (itemData.isImportItem() ? " §8[§cImportItem§8]" : "");
/* 457 */       if (!str.contains(search)) {
/*     */         continue;
/*     */       }
/* 460 */       z++;
/* 461 */       List<String> itemLore = itemMeta.getLore();
/* 462 */       if (sender instanceof Player) {
/* 463 */         List<String> ids = itemData.getIds();
/* 464 */         StringBuilder id = new StringBuilder(ids.get(0));
/* 465 */         if (ids.size() > 1) {
/* 466 */           for (int i = 1; i < ids.size(); i++) {
/* 467 */             id.append("/").append(ids.get(i));
/*     */           }
/*     */         }
/* 470 */         List<String> loreList = new ArrayList<>();
/* 471 */         loreList.add(itemName + "&b - " + id);
/* 472 */         if (itemLore != null) {
/* 473 */           loreList.addAll(itemLore);
/*     */         } else {
/* 475 */           loreList.add("&cN/A");
/*     */         } 
/* 477 */         ((Player)sender).spigot().sendMessage((BaseComponent)Message.getTextComponent(str, "/sxAttribute give " + key, loreList)); continue;
/*     */       } 
/* 479 */       sender.sendMessage(str);
/*     */     } 
/*     */     
/* 482 */     if (z == 1 && searchs.length > 0)
/* 483 */       sender.sendMessage("§c搜索失败! 请核对关键词!"); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\ItemDataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */