/*     */ package saukiya.sxattribute.data.attribute;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.RegisterSlot;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.damage.CritAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.damage.HitRateAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.damage.LifeStealAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.damage.SlownessAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.defence.BlockAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.defence.HealthRegenAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.defence.ReflectionAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.sub.other.ExpAdditionAttribute;
/*     */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
/*     */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*     */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*     */ import github.saukiya.sxattribute.event.SXLoadItemDataEvent;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class SXAttributeManager {
/*  34 */   private static final AttributeMap attributeMap = SubAttribute.attributeMap;
/*     */   
/*  36 */   private final Map<UUID, SXAttributeData> rpgInventoryMap = new HashMap<>(); public Map<UUID, SXAttributeData> getRpgInventoryMap() { return this.rpgInventoryMap; }
/*     */   
/*  38 */   private final Map<UUID, SXAttributeData> equipmentMap = new HashMap<>(); public Map<UUID, SXAttributeData> getEquipmentMap() { return this.equipmentMap; }
/*     */   
/*  40 */   private final Map<UUID, SXAttributeData> handMap = new HashMap<>(); public Map<UUID, SXAttributeData> getHandMap() { return this.handMap; }
/*     */   
/*  42 */   private final Map<UUID, SXAttributeData> slotMap = new HashMap<>(); private final SXAttribute plugin; private SXAttributeData defaultAttributeData; public Map<UUID, SXAttributeData> getSlotMap() { return this.slotMap; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SXAttributeManager(SXAttribute plugin) {
/*  50 */     this.plugin = plugin;
/*     */     
/*  52 */     (new BlindnessAttribute()).registerAttribute((JavaPlugin)plugin);
/*  53 */     (new CritAttribute()).registerAttribute((JavaPlugin)plugin);
/*  54 */     (new DamageAttribute()).registerAttribute((JavaPlugin)plugin);
/*  55 */     (new HitRateAttribute()).registerAttribute((JavaPlugin)plugin);
/*  56 */     (new IgnitionAttribute()).registerAttribute((JavaPlugin)plugin);
/*  57 */     (new LifeStealAttribute()).registerAttribute((JavaPlugin)plugin);
/*  58 */     (new LightningAttribute()).registerAttribute((JavaPlugin)plugin);
/*  59 */     (new PoisonAttribute()).registerAttribute((JavaPlugin)plugin);
/*  60 */     (new RealAttribute()).registerAttribute((JavaPlugin)plugin);
/*  61 */     (new SlownessAttribute()).registerAttribute((JavaPlugin)plugin);
/*  62 */     (new TearingAttribute()).registerAttribute((JavaPlugin)plugin);
/*  63 */     (new WitherAttribute()).registerAttribute((JavaPlugin)plugin);
/*     */     
/*  65 */     (new BlockAttribute()).registerAttribute((JavaPlugin)plugin);
/*  66 */     (new DefenseAttribute()).registerAttribute((JavaPlugin)plugin);
/*  67 */     (new DodgeAttribute()).registerAttribute((JavaPlugin)plugin);
/*  68 */     (new ReflectionAttribute()).registerAttribute((JavaPlugin)plugin);
/*  69 */     (new ToughnessAttribute()).registerAttribute((JavaPlugin)plugin);
/*     */     
/*  71 */     (new EventMessageAttribute()).registerAttribute((JavaPlugin)plugin);
/*  72 */     (new ExpAdditionAttribute()).registerAttribute((JavaPlugin)plugin);
/*  73 */     (new MythicmobsDropAttribute()).registerAttribute((JavaPlugin)plugin);
/*  74 */     (new HealthRegenAttribute()).registerAttribute((JavaPlugin)plugin);
/*     */     
/*  76 */     (new HealthAttribute()).registerAttribute((JavaPlugin)plugin);
/*  77 */     (new SpeedAttribute()).registerAttribute((JavaPlugin)plugin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Map<Integer, SubAttribute> cloneSXAttributeList() {
/*  86 */     TreeMap<Integer, SubAttribute> map = new TreeMap<>();
/*  87 */     for (Map.Entry<Integer, SubAttribute> entry : (Iterable<Map.Entry<Integer, SubAttribute>>)attributeMap.entrySet()) {
/*  88 */       map.put(entry.getKey(), ((SubAttribute)entry.getValue()).newAttribute());
/*     */     }
/*  90 */     return map;
/*     */   }
/*     */   
/*     */   public void onAttributeEnable() {
/*  94 */     attributeMap.values().forEach(SubAttribute::onEnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onAttributeDisable() {
/*  99 */     attributeMap.values().forEach(SubAttribute::onDisable);
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
/*     */   public SXAttributeData getItemData(LivingEntity entity, SXConditionType type, ItemStack... itemArray) {
/* 111 */     SXAttributeData sxAttributeDataList = new SXAttributeData();
/* 112 */     for (int i = 0; i < itemArray.length; i++) {
/* 113 */       ItemStack item = itemArray[i];
/* 114 */       if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
/* 115 */         String itemName = item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : item.getType().name();
/* 116 */         if (itemName.contains("§X")) {
/* 117 */           itemArray[i] = null;
/*     */         } else {
/*     */           
/* 120 */           SXAttributeData sxAttributeData = new SXAttributeData();
/* 121 */           for (String lore : item.getItemMeta().getLore()) {
/* 122 */             lore = lore.split("§X")[0];
/* 123 */             if (lore.length() > 0) {
/* 124 */               for (SubCondition subCondition : this.plugin.getConditionManager().getConditionMap().values()) {
/* 125 */                 if (subCondition.containsType(type, true)) {
/* 126 */                   SXConditionReturnType returnType = subCondition.determine(entity, item, lore);
/* 127 */                   if (returnType.equals(SXConditionReturnType.ITEM)) {
/* 128 */                     sxAttributeData = null; break;
/*     */                   } 
/* 130 */                   if (returnType.equals(SXConditionReturnType.LORE)) {
/*     */                     break;
/*     */                   }
/*     */                 } 
/*     */               } 
/* 135 */               if (sxAttributeData == null) {
/* 136 */                 itemArray[i] = null;
/*     */                 break;
/*     */               } 
/* 139 */               for (SubAttribute sxAttribute : sxAttributeData.getAttributeMap().values()) {
/* 140 */                 if (sxAttribute.loadAttribute(lore)) {
/* 141 */                   sxAttributeData.valid();
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 147 */           if (sxAttributeData != null)
/* 148 */             sxAttributeDataList.add(sxAttributeData); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 152 */     return sxAttributeDataList.isValid() ? sxAttributeDataList : null;
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
/*     */   public SXAttributeData getListStats(LivingEntity entity, SXConditionType type, List<String> stringList) {
/* 165 */     SXAttributeData sxAttributeData = new SXAttributeData();
/* 166 */     for (String lore : stringList) {
/* 167 */       lore = lore.split("§X")[0];
/* 168 */       if (lore.length() > 0) {
/* 169 */         for (SubCondition subCondition : this.plugin.getConditionManager().getConditionMap().values()) {
/* 170 */           if (subCondition.containsType(type, true)) {
/* 171 */             SXConditionReturnType returnType = subCondition.determine(entity, null, lore);
/* 172 */             if (returnType.equals(SXConditionReturnType.ITEM))
/* 173 */               return null; 
/* 174 */             if (returnType.equals(SXConditionReturnType.LORE)) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/* 179 */         for (SubAttribute sxAttribute : sxAttributeData.getAttributeMap().values()) {
/* 180 */           if (sxAttribute.loadAttribute(lore)) {
/* 181 */             sxAttributeData.valid();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 187 */     return sxAttributeData.isValid() ? sxAttributeData : null;
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
/*     */   public Boolean isUse(LivingEntity entity, SXConditionType type, ItemStack item) {
/* 199 */     if (type != null && item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
/* 200 */       List<String> loreList = item.getItemMeta().getLore();
/* 201 */       for (String lore : loreList) {
/* 202 */         for (SubCondition subCondition : this.plugin.getConditionManager().getConditionMap().values()) {
/* 203 */           if (subCondition.containsType(type, true) && 
/* 204 */             subCondition.determine(entity, item, lore).equals(SXConditionReturnType.ITEM)) {
/* 205 */             return Boolean.valueOf(false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 211 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateStatsEvent(LivingEntity entity) {
/* 220 */     (new Object(this, entity))
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
/*     */       
/* 238 */       .runTask((Plugin)this.plugin);
/*     */   }
/*     */   
/*     */   public void loadDefaultAttributeData() {
/* 242 */     this.defaultAttributeData = getListStats(null, null, Config.getConfig().getStringList("DefaultAttribute"));
/*     */   }
/*     */ 
/*     */   
/*     */   public SXAttributeData getProjectileData(UUID uuid) {
/* 247 */     return (new SXAttributeData()).add(this.equipmentMap.remove(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProjectileData(UUID uuid, SXAttributeData attributeData) {
/* 252 */     if (attributeData != null && attributeData.isValid()) {
/* 253 */       this.equipmentMap.put(uuid, attributeData);
/*     */     } else {
/* 255 */       this.equipmentMap.remove(uuid);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SXAttributeData getEntityData(LivingEntity entity, SXAttributeData... attributeData) {
/* 261 */     SXAttributeData data = new SXAttributeData();
/* 262 */     UUID uuid = entity.getUniqueId();
/* 263 */     if (SXAttribute.isRpgInventory() && entity instanceof Player) {
/* 264 */       data.add(this.rpgInventoryMap.get(uuid));
/*     */     } else {
/* 266 */       data.add(this.equipmentMap.get(uuid));
/* 267 */       data.add(this.slotMap.get(uuid));
/*     */     } 
/* 269 */     if (attributeData.length > 0) {
/* 270 */       data.add(attributeData[0]);
/*     */     } else {
/* 272 */       data.add(this.handMap.get(uuid));
/*     */     } 
/*     */     
/* 275 */     data.add(SXAttribute.getApi().getAPIStats(entity.getUniqueId()));
/*     */     
/* 277 */     data.calculationValue();
/*     */     
/* 279 */     data.add(this.defaultAttributeData);
/*     */     
/* 281 */     data.correct();
/* 282 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearEntityData(UUID uuid) {
/* 291 */     this.equipmentMap.remove(uuid);
/* 292 */     this.handMap.remove(uuid);
/* 293 */     this.slotMap.remove(uuid);
/* 294 */     this.rpgInventoryMap.remove(uuid);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadRPGInventoryData(Player player) {
/* 299 */     if (SXAttribute.isRpgInventory() && player.isOnline()) {
/* 300 */       Inventory inv = InventoryManager.get((OfflinePlayer)player).getInventory();
/* 301 */       if (inv != null) {
/* 302 */         List<ItemStack> itemList = new ArrayList<>();
/* 303 */         List<Integer> whiteSlotList = Config.getConfig().getIntegerList("RPGInventory.WhiteSlot");
/* 304 */         for (int i = 0; i < 54; i++) {
/* 305 */           if (!whiteSlotList.contains(Integer.valueOf(i))) {
/* 306 */             ItemStack item = inv.getItem(i);
/* 307 */             if (item != null && item.getItemMeta().hasLore()) itemList.add(item); 
/*     */           } 
/*     */         } 
/* 310 */         ItemStack[] items = itemList.<ItemStack>toArray(new ItemStack[0]);
/* 311 */         SXAttributeData attributeData = getItemData((LivingEntity)player, SXConditionType.RPG_INVENTORY, items);
/* 312 */         SXLoadItemDataEvent event = new SXLoadItemDataEvent(SXConditionType.RPG_INVENTORY, (LivingEntity)player, attributeData, items);
/* 313 */         if (event.getAttributeData() != null) {
/* 314 */           this.rpgInventoryMap.put(player.getUniqueId(), event.getAttributeData());
/*     */         } else {
/* 316 */           this.rpgInventoryMap.remove(player.getUniqueId());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadEquipmentData(LivingEntity entity) {
/* 324 */     if (SXAttribute.isRpgInventory() && entity instanceof Player) {
/*     */       return;
/*     */     }
/*     */     
/* 328 */     if (Config.isItemUpdate() && entity instanceof Player) {
/* 329 */       for (ItemStack item : entity.getEquipment().getArmorContents()) {
/* 330 */         this.plugin.getItemDataManager().updateItem(item, (Player)entity);
/*     */       }
/*     */     }
/*     */     
/* 334 */     ItemStack[] items = entity.getEquipment().getArmorContents();
/* 335 */     SXAttributeData attributeData = getItemData(entity, SXConditionType.EQUIPMENT, items);
/* 336 */     SXLoadItemDataEvent event = new SXLoadItemDataEvent(SXConditionType.EQUIPMENT, entity, attributeData, items);
/* 337 */     if (event.getAttributeData() != null) {
/* 338 */       this.equipmentMap.put(entity.getUniqueId(), event.getAttributeData());
/*     */     } else {
/* 340 */       this.equipmentMap.remove(entity.getUniqueId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadSlotData(Player player) {
/* 351 */     if (!SXAttribute.isRpgInventory() && Config.getConfig().getStringList("RegisterSlots.List").size() > 0) {
/* 352 */       List<ItemStack> itemList = new ArrayList<>();
/* 353 */       PlayerInventory playerInventory = player.getInventory();
/* 354 */       this.plugin.getRegisterSlotManager().getRegisterSlotMap().forEach((slot, registerSlot) -> {
/*     */             ItemStack item = inv.getItem(slot.intValue());
/*     */             
/*     */             if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta().hasLore() && item.getItemMeta().getLore().stream().anyMatch(())) {
/*     */               itemList.add(item);
/*     */             }
/*     */           });
/* 361 */       ItemStack[] items = itemList.<ItemStack>toArray(new ItemStack[0]);
/* 362 */       SXAttributeData attributeData = getItemData((LivingEntity)player, SXConditionType.SLOT, items);
/* 363 */       SXLoadItemDataEvent event = new SXLoadItemDataEvent(SXConditionType.SLOT, (LivingEntity)player, attributeData, items);
/* 364 */       if (event.getAttributeData() != null) {
/* 365 */         this.slotMap.put(player.getUniqueId(), event.getAttributeData());
/*     */       } else {
/* 367 */         this.slotMap.remove(player.getUniqueId());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadHandData(LivingEntity entity) {
/* 375 */     if (Config.isItemUpdate() && entity instanceof Player) {
/* 376 */       this.plugin.getItemDataManager().updateItem(entity.getEquipment().getItemInMainHand(), (Player)entity);
/* 377 */       this.plugin.getItemDataManager().updateItem(entity.getEquipment().getItemInOffHand(), (Player)entity);
/*     */     } 
/* 379 */     ItemStack mainItem = entity.getEquipment().getItemInMainHand();
/* 380 */     ItemStack offItem = entity.getEquipment().getItemInOffHand();
/* 381 */     ItemStack[] itemArray = { mainItem, null };
/* 382 */     SXAttributeData attributeData = getItemData(entity, SXConditionType.MAIN_HAND, itemArray);
/* 383 */     mainItem = itemArray[0];
/* 384 */     itemArray[0] = null;
/* 385 */     itemArray[1] = offItem;
/* 386 */     attributeData = (attributeData != null) ? attributeData.add(getItemData(entity, SXConditionType.OFF_HAND, itemArray)) : getItemData(entity, SXConditionType.OFF_HAND, itemArray);
/* 387 */     itemArray[0] = mainItem;
/* 388 */     SXLoadItemDataEvent event = new SXLoadItemDataEvent(SXConditionType.HAND, entity, attributeData, itemArray);
/* 389 */     if (event.getAttributeData() != null) {
/* 390 */       this.handMap.put(entity.getUniqueId(), event.getAttributeData());
/*     */     } else {
/* 392 */       this.handMap.remove(entity.getUniqueId());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\SXAttributeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */