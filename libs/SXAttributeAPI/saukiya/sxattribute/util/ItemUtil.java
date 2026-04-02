/*     */ package saukiya.sxattribute.util;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.EntityEquipment;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemUtil
/*     */ {
/*     */   private Class<?> xCraftItemStack;
/*     */   private Class<?> xNBTTagCompound;
/*     */   private Class<?> xNBTTagList;
/*     */   private Constructor<?> xNewNBTTagString;
/*     */   private Constructor<?> xNewNBTTagDouble;
/*     */   private Constructor<?> xNewNBTTagInt;
/*     */   private Method xAsNMSCopay;
/*     */   private Method xGetTag;
/*     */   private Method xHasTag;
/*     */   private Method xSet;
/*     */   private Method xAdd;
/*     */   private Method xRemove;
/*     */   private Method xSetTag;
/*     */   private Method xAsBukkitCopy;
/*     */   private Method xHasKey;
/*     */   private Method xGet;
/*     */   private Method xGetString;
/*     */   private Method xGetListString;
/*     */   private Method xSize;
/*     */   private SXAttribute plugin;
/*     */   
/*     */   public ItemUtil(SXAttribute plugin) throws NoSuchMethodException, ClassNotFoundException {
/*  61 */     this.plugin = plugin;
/*  62 */     String packet = Bukkit.getServer().getClass().getPackage().getName();
/*  63 */     String nmsName = "net.minecraft.server." + packet.substring(packet.lastIndexOf('.') + 1);
/*  64 */     this.xCraftItemStack = Class.forName(packet + ".inventory.CraftItemStack");
/*  65 */     Class<?> xNMSItemStack = Class.forName(nmsName + ".ItemStack");
/*  66 */     this.xNBTTagCompound = Class.forName(nmsName + ".NBTTagCompound");
/*  67 */     Class<?> xNBTTagString = Class.forName(nmsName + ".NBTTagString");
/*  68 */     Class<?> xNBTTagDouble = Class.forName(nmsName + ".NBTTagDouble");
/*  69 */     Class<?> xNBTTagInt = Class.forName(nmsName + ".NBTTagInt");
/*  70 */     this.xNBTTagList = Class.forName(nmsName + ".NBTTagList");
/*  71 */     Class<?> xNBTBase = Class.forName(nmsName + ".NBTBase");
/*  72 */     this.xNewNBTTagString = xNBTTagString.getConstructor(new Class[] { String.class });
/*  73 */     this.xNewNBTTagDouble = xNBTTagDouble.getConstructor(new Class[] { double.class });
/*  74 */     this.xNewNBTTagInt = xNBTTagInt.getConstructor(new Class[] { int.class });
/*     */     
/*  76 */     this.xAsNMSCopay = this.xCraftItemStack.getDeclaredMethod("asNMSCopy", new Class[] { ItemStack.class });
/*  77 */     this.xGetTag = xNMSItemStack.getDeclaredMethod("getTag", new Class[0]);
/*  78 */     this.xHasTag = xNMSItemStack.getDeclaredMethod("hasTag", new Class[0]);
/*  79 */     this.xSet = this.xNBTTagCompound.getDeclaredMethod("set", new Class[] { String.class, xNBTBase });
/*  80 */     this.xAdd = this.xNBTTagList.getDeclaredMethod("add", new Class[] { xNBTBase });
/*  81 */     this.xRemove = this.xNBTTagCompound.getDeclaredMethod("remove", new Class[] { String.class });
/*  82 */     this.xSetTag = xNMSItemStack.getDeclaredMethod("setTag", new Class[] { this.xNBTTagCompound });
/*  83 */     this.xAsBukkitCopy = this.xCraftItemStack.getDeclaredMethod("asBukkitCopy", new Class[] { xNMSItemStack });
/*     */     
/*  85 */     this.xHasKey = this.xNBTTagCompound.getDeclaredMethod("hasKey", new Class[] { String.class });
/*  86 */     this.xGet = this.xNBTTagCompound.getDeclaredMethod("get", new Class[] { String.class });
/*  87 */     this.xGetString = this.xNBTTagCompound.getDeclaredMethod("getString", new Class[] { String.class });
/*  88 */     this.xGetListString = this.xNBTTagList.getDeclaredMethod("getString", new Class[] { int.class });
/*  89 */     this.xSize = this.xNBTTagList.getDeclaredMethod("size", new Class[0]);
/*  90 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load ItemUtil! ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAttribute(Player player) {
/* 100 */     EntityEquipment eq = player.getEquipment();
/* 101 */     for (ItemStack item : eq.getArmorContents()) {
/* 102 */       if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore() && 
/* 103 */         item.getType().getMaxDurability() != 0) {
/* 104 */         clearAttribute(item);
/*     */       }
/*     */     } 
/*     */     
/* 108 */     if (SXAttribute.getVersionSplit()[1] >= 9) {
/* 109 */       ItemStack mainItem = eq.getItemInMainHand();
/* 110 */       if (mainItem != null && mainItem.hasItemMeta() && mainItem.getItemMeta().hasLore() && 
/* 111 */         mainItem.getType().getMaxDurability() != 0) {
/* 112 */         clearAttribute(mainItem);
/*     */       }
/*     */       
/* 115 */       ItemStack offItem = eq.getItemInOffHand();
/* 116 */       if (offItem != null && offItem.hasItemMeta() && offItem.getItemMeta().hasLore() && 
/* 117 */         offItem.getType().getMaxDurability() != 0) {
/* 118 */         clearAttribute(offItem);
/*     */       }
/*     */     } else {
/*     */       
/* 122 */       ItemStack mainItem = eq.getItemInHand();
/* 123 */       if (mainItem != null && mainItem.hasItemMeta() && mainItem.getItemMeta().hasLore() && 
/* 124 */         mainItem.getType().getMaxDurability() != 0) {
/* 125 */         clearAttribute(mainItem);
/*     */       }
/*     */     } 
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
/*     */   private double getAttackSpeed(ItemStack item, double... addSpeed) {
/* 139 */     double attackSpeed = -0.3D;
/* 140 */     String itemType = item.getType().name();
/* 141 */     if (itemType.contains("_PICKAXE")) {
/* 142 */       attackSpeed = -0.7D;
/* 143 */     } else if (itemType.contains("_AXE")) {
/* 144 */       attackSpeed = -0.8D;
/* 145 */     } else if (itemType.contains("_HOE")) {
/* 146 */       attackSpeed = -0.5D;
/* 147 */     } else if (itemType.contains("_SPADE")) {
/* 148 */       attackSpeed = -0.77D;
/* 149 */     } else if (itemType.contains("_SWORD")) {
/* 150 */       attackSpeed = -0.6D;
/*     */     } 
/* 152 */     if (addSpeed.length > 0) {
/* 153 */       attackSpeed += addSpeed[0] / 500.0D;
/*     */     }
/* 155 */     return (attackSpeed <= -1.0D) ? -0.99D : attackSpeed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setAttackSpeed(ItemStack item, double... speed) {
/* 166 */     if (item != null && !item.getType().name().equals("AIR")) {
/*     */       try {
/* 168 */         Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { setNBT(item, "Clear", "yes" + Config.isDamageGauges()) });
/* 169 */         Object compound = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 170 */         Object modifiers = this.xNBTTagList.newInstance();
/* 171 */         Object attackSpeed = this.xNBTTagCompound.newInstance();
/* 172 */         this.xSet.invoke(attackSpeed, new Object[] { "AttributeName", this.xNewNBTTagString.newInstance(new Object[] { "generic.attackSpeed" }) });
/* 173 */         this.xSet.invoke(attackSpeed, new Object[] { "Name", this.xNewNBTTagString.newInstance(new Object[] { "AttackSpeed" }) });
/* 174 */         this.xSet.invoke(attackSpeed, new Object[] { "Amount", this.xNewNBTTagDouble.newInstance(new Object[] { Double.valueOf(getAttackSpeed(item, speed)) }) });
/* 175 */         this.xSet.invoke(attackSpeed, new Object[] { "Operation", this.xNewNBTTagInt.newInstance(new Object[] { Integer.valueOf(1) }) });
/* 176 */         this.xSet.invoke(attackSpeed, new Object[] { "UUIDLeast", this.xNewNBTTagInt.newInstance(new Object[] { Integer.valueOf(20000) }) });
/* 177 */         this.xSet.invoke(attackSpeed, new Object[] { "UUIDMost", this.xNewNBTTagInt.newInstance(new Object[] { Integer.valueOf(1000) }) });
/* 178 */         this.xSet.invoke(attackSpeed, new Object[] { "Slot", this.xNewNBTTagString.newInstance(new Object[] { "mainhand" }) });
/* 179 */         this.xAdd.invoke(modifiers, new Object[] { attackSpeed });
/* 180 */         this.xSet.invoke(compound, new Object[] { "AttributeModifiers", modifiers });
/* 181 */         this.xSetTag.invoke(nmsItem, new Object[] { compound });
/* 182 */         ItemMeta meta = ((ItemStack)this.xAsBukkitCopy.invoke(this.xCraftItemStack, new Object[] { nmsItem })).getItemMeta();
/* 183 */         meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
/* 184 */         item.setItemMeta(meta);
/* 185 */       } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 186 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 190 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAttribute(ItemStack item) {
/* 199 */     if (item != null && (!isNBT(item, "Clear") || !Objects.equals(getNBT(item, "Clear"), "yes" + Config.isDamageGauges()))) {
/*     */       try {
/* 201 */         Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { setNBT(item, "Clear", "yes" + Config.isDamageGauges()) });
/* 202 */         Object compound = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 203 */         Object modifiers = this.xNBTTagList.newInstance();
/* 204 */         this.xSet.invoke(compound, new Object[] { "AttributeModifiers", modifiers });
/* 205 */         this.xSetTag.invoke(nmsItem, new Object[] { compound });
/* 206 */         ItemMeta meta = ((ItemStack)this.xAsBukkitCopy.invoke(this.xCraftItemStack, new Object[] { nmsItem })).getItemMeta();
/* 207 */         if (Config.isDamageGauges()) {
/* 208 */           meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
/*     */         }
/* 210 */         item.setItemMeta(meta);
/* 211 */       } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 212 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAttribute(Player player) {
/* 224 */     EntityEquipment eq = player.getEquipment();
/* 225 */     for (ItemStack item : eq.getArmorContents()) {
/* 226 */       if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore() && 
/* 227 */         item.getType().getMaxDurability() != 0) {
/* 228 */         removeAttribute(item);
/*     */       }
/*     */     } 
/*     */     
/* 232 */     if (SXAttribute.getVersionSplit()[1] >= 9) {
/* 233 */       ItemStack mainItem = eq.getItemInMainHand();
/* 234 */       if (mainItem != null && mainItem.hasItemMeta() && mainItem.getItemMeta().hasLore() && 
/* 235 */         mainItem.getType().getMaxDurability() != 0) {
/* 236 */         removeAttribute(mainItem);
/*     */       }
/*     */       
/* 239 */       ItemStack offItem = eq.getItemInOffHand();
/* 240 */       if (offItem != null && offItem.hasItemMeta() && offItem.getItemMeta().hasLore() && 
/* 241 */         offItem.getType().getMaxDurability() != 0) {
/* 242 */         removeAttribute(offItem);
/*     */       }
/*     */     } else {
/*     */       
/* 246 */       ItemStack mainItem = eq.getItemInHand();
/* 247 */       if (mainItem != null && mainItem.hasItemMeta() && mainItem.getItemMeta().hasLore() && 
/* 248 */         mainItem.getType().getMaxDurability() != 0) {
/* 249 */         removeAttribute(mainItem);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeAttribute(ItemStack item) {
/* 261 */     if (item != null && (!isNBT(item, "Clear") || !Objects.equals(getNBT(item, "Clear"), "no"))) {
/*     */       try {
/* 263 */         Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { setNBT(item, "Clear", "no") });
/* 264 */         Object compound = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 265 */         this.xRemove.invoke(compound, new Object[] { "AttributeModifiers" });
/* 266 */         this.xSetTag.invoke(nmsItem, new Object[] { compound });
/* 267 */         item.setItemMeta(((ItemStack)this.xAsBukkitCopy.invoke(this.xCraftItemStack, new Object[] { nmsItem })).getItemMeta());
/* 268 */       } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 269 */         e.printStackTrace();
/*     */       } 
/* 271 */       ItemMeta meta = item.getItemMeta();
/* 272 */       meta.removeItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
/* 273 */       item.setItemMeta(meta);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAllNBT(ItemStack item) {
/*     */     try {
/* 285 */       Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { item });
/* 286 */       Object itemTag = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 287 */       return "[" + item.getTypeId() + ":" + item.getDurability() + "-" + item.hashCode() + "]>" + itemTag.toString();
/* 288 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 289 */       return null;
/*     */     } 
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
/*     */   public ItemStack setNBT(ItemStack item, String key, String value) {
/*     */     try {
/* 303 */       Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { item });
/* 304 */       Object itemTag = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 305 */       Object tagString = this.xNewNBTTagString.newInstance(new Object[] { value });
/* 306 */       this.xSet.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key, tagString });
/* 307 */       this.xSetTag.invoke(nmsItem, new Object[] { itemTag });
/* 308 */       item = (ItemStack)this.xAsBukkitCopy.invoke(this.xCraftItemStack, new Object[] { nmsItem });
/* 309 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 310 */       e.printStackTrace();
/*     */     } 
/* 312 */     return item;
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
/*     */   public ItemStack setNBTList(ItemStack item, String key, List<String> list) {
/*     */     try {
/* 325 */       Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { item });
/* 326 */       Object itemTag = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 327 */       Object tagList = this.xNBTTagList.newInstance();
/* 328 */       for (String str : list) {
/* 329 */         this.xAdd.invoke(tagList, new Object[] { this.xNewNBTTagString.newInstance(new Object[] { str }) });
/*     */       } 
/* 331 */       this.xSet.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key, tagList });
/* 332 */       this.xSetTag.invoke(nmsItem, new Object[] { itemTag });
/* 333 */       item.setItemMeta(((ItemStack)this.xAsBukkitCopy.invoke(this.xCraftItemStack, new Object[] { nmsItem })).getItemMeta());
/* 334 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 335 */       e.printStackTrace();
/*     */     } 
/* 337 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNBT(ItemStack item, String key) {
/*     */     try {
/* 349 */       Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { item });
/* 350 */       Object itemTag = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 351 */       if (((Boolean)this.xHasKey.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key })).booleanValue())
/* 352 */         return (String)this.xGetString.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key }); 
/* 353 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 354 */       e.printStackTrace();
/*     */     } 
/* 356 */     return null;
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
/*     */   public List<String> getNBTList(ItemStack item, String key) {
/* 368 */     List<String> list = new ArrayList<>();
/*     */     try {
/* 370 */       Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { item });
/* 371 */       Object itemTag = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 372 */       Object tagList = ((Boolean)this.xHasKey.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key })).booleanValue() ? this.xGet.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key }) : this.xNBTTagList.newInstance();
/* 373 */       for (int i = 0; i < ((Integer)this.xSize.invoke(tagList, new Object[0])).intValue(); i++) {
/* 374 */         list.add((String)this.xGetListString.invoke(tagList, new Object[] { Integer.valueOf(i) }));
/*     */       } 
/* 376 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 377 */       e.printStackTrace();
/*     */     } 
/* 379 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNBT(ItemStack item, String key) {
/*     */     try {
/* 391 */       Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { item });
/* 392 */       Object itemTag = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 393 */       if (((Boolean)this.xHasKey.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key })).booleanValue()) return true; 
/* 394 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 395 */       e.printStackTrace();
/*     */     } 
/* 397 */     return false;
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
/*     */   public boolean removeNBT(ItemStack item, String key) {
/*     */     try {
/* 410 */       Object nmsItem = this.xAsNMSCopay.invoke(this.xCraftItemStack, new Object[] { item });
/* 411 */       Object itemTag = ((Boolean)this.xHasTag.invoke(nmsItem, new Object[0])).booleanValue() ? this.xGetTag.invoke(nmsItem, new Object[0]) : this.xNBTTagCompound.newInstance();
/* 412 */       if (((Boolean)this.xHasKey.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key })).booleanValue()) {
/* 413 */         this.xRemove.invoke(itemTag, new Object[] { this.plugin.getName() + "-" + key });
/* 414 */         this.xSetTag.invoke(nmsItem, new Object[] { itemTag });
/* 415 */         item.setItemMeta(((ItemStack)this.xAsBukkitCopy.invoke(this.xCraftItemStack, new Object[] { nmsItem })).getItemMeta());
/*     */       } 
/* 417 */       return true;
/* 418 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|InstantiationException e) {
/* 419 */       return false;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItemStack(String itemName, String id, List<String> itemLore, List<String> itemFlagList, Boolean unbreakable, String color, String skullName) {
/* 437 */     int itemMaterial = 0;
/* 438 */     int itemDurability = 0;
/* 439 */     if (id != null) {
/* 440 */       if (id.contains(":") && id.split(":")[0].replaceAll("[^0-9]", "").length() > 0) {
/* 441 */         String[] idSplit = id.split(":");
/* 442 */         if (idSplit[0].replaceAll("[^0-9]", "").length() > 0 && idSplit[1].replaceAll("[^0-9]", "").length() > 0) {
/* 443 */           itemMaterial = Integer.valueOf(idSplit[0].replaceAll("[^0-9]", "")).intValue();
/* 444 */           itemDurability = Integer.valueOf(idSplit[1].replaceAll("[^0-9]", "")).intValue();
/*     */         } 
/* 446 */       } else if (id.replaceAll("[^0-9]", "").length() > 0) {
/* 447 */         itemMaterial = Integer.valueOf(id.replaceAll("[^0-9]", "")).intValue();
/*     */       } 
/*     */     }
/* 450 */     ItemStack item = new ItemStack(itemMaterial, 1, (short)itemDurability);
/* 451 */     if (item.getType().name().equals(Material.AIR.name())) {
/* 452 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cItem §4" + itemName + "§c ID Error: §4'" + id + "'§c!");
/* 453 */       return null;
/*     */     } 
/* 455 */     ItemMeta meta = item.getItemMeta();
/* 456 */     if (itemName != null) {
/* 457 */       meta.setDisplayName(itemName.replace("&", "§"));
/*     */     }
/* 459 */     if (itemLore != null) {
/* 460 */       for (int i = 0; i < itemLore.size(); i++) {
/* 461 */         itemLore.set(i, ((String)itemLore.get(i)).replace("&", "§"));
/*     */       }
/* 463 */       meta.setLore(itemLore);
/*     */     } 
/* 465 */     if (itemFlagList != null && itemFlagList.size() > 0) {
/* 466 */       for (String flagName : itemFlagList) {
/*     */         try {
/* 468 */           ItemFlag itemFlag = ItemFlag.valueOf(flagName);
/* 469 */           meta.addItemFlags(new ItemFlag[] { itemFlag });
/* 470 */         } catch (NullPointerException|IllegalArgumentException e) {
/* 471 */           Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c物品: §4" + itemName + " §c的Flag: §4" + flagName + "§c 不是正常的Flag名称！");
/*     */         } 
/*     */       } 
/*     */     }
/* 475 */     if (unbreakable != null) {
/* 476 */       if (SXAttribute.getVersionSplit()[1] >= 11) {
/*     */         
/* 478 */         meta.setUnbreakable(unbreakable.booleanValue());
/*     */       } else {
/*     */         
/* 481 */         meta.spigot().setUnbreakable(unbreakable.booleanValue());
/*     */       } 
/*     */     }
/* 484 */     if (color != null && meta instanceof LeatherArmorMeta) {
/* 485 */       Color c = Color.fromRGB(Integer.valueOf(color.split(",")[0]).intValue(), Integer.valueOf(color.split(",")[1]).intValue(), Integer.valueOf(color.split(",")[2]).intValue());
/* 486 */       ((LeatherArmorMeta)meta).setColor(c);
/*     */     } 
/* 488 */     if (skullName != null && meta instanceof SkullMeta) {
/* 489 */       ((SkullMeta)meta).setOwner(skullName);
/*     */     }
/* 491 */     item.setItemMeta(meta);
/* 492 */     return item;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribut\\util\ItemUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */