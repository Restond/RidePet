/*     */ package saukiya.sxattribute.api;
/*     */ 
/*     */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.RandomStringManager;
/*     */ import github.saukiya.sxattribute.data.RegisterSlot;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*     */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*     */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*     */ import github.saukiya.sxattribute.util.ItemUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SXAttributeAPI
/*     */ {
/*  25 */   private final Map<UUID, Map<Class<?>, SXAttributeData>> map = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final SXAttribute plugin;
/*     */ 
/*     */ 
/*     */   
/*     */   public SXAttributeAPI(SXAttribute plugin) {
/*  34 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SXAttributeData getAPIStats(UUID uuid) {
/*  44 */     SXAttributeData attributeData = new SXAttributeData();
/*  45 */     if (this.map.containsKey(uuid)) {
/*  46 */       for (Class<?> c : (Iterable<Class<?>>)((Map)this.map.get(uuid)).keySet()) {
/*  47 */         attributeData.add((SXAttributeData)((Map)this.map.get(uuid)).get(c));
/*     */       }
/*     */     }
/*  50 */     return attributeData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemUtil getItemUtil() {
/*  60 */     return this.plugin.getItemUtil();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomStringManager getRandomStringManager() {
/*  69 */     return this.plugin.getRandomStringManager();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<Integer, RegisterSlot>> getRegisterSlotMapEntrySet() {
/*  79 */     return this.plugin.getRegisterSlotManager().getRegisterSlotMap().entrySet();
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
/*     */   @Nullable
/*     */   public void setProjectileData(UUID uuid, SXAttributeData attributeData) {
/*  92 */     this.plugin.getAttributeManager().setProjectileData(uuid, attributeData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SXAttributeData getProjectileData(UUID uuid) {
/* 102 */     return this.plugin.getAttributeManager().getProjectileData(uuid);
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
/*     */   public SXAttributeData getEntityAllData(LivingEntity livingEntity, SXAttributeData... sxAttributeData) {
/* 114 */     return this.plugin.getAttributeManager().getEntityData(livingEntity, sxAttributeData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SXAttributeData getEntityAPIData(Class<?> c, UUID uuid) {
/* 125 */     return this.map.containsKey(uuid) ? (SXAttributeData)((Map)this.map.get(uuid)).get(c) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEntityAPIData(Class<?> c, UUID uuid) {
/* 136 */     return (this.map.containsKey(uuid) && ((Map)this.map.get(uuid)).containsKey(c));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntityAPIData(Class<?> c, UUID uuid, SXAttributeData attributeData) {
/* 147 */     Map<Class<?>, SXAttributeData> statsMap = new HashMap<>();
/* 148 */     if (this.map.containsKey(uuid)) {
/* 149 */       statsMap = this.map.get(uuid);
/*     */     } else {
/* 151 */       this.map.put(uuid, statsMap);
/*     */     } 
/* 153 */     statsMap.put(c, attributeData);
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
/*     */   public SXAttributeData removeEntityAPIData(Class<?> c, UUID uuid) {
/* 165 */     SXAttributeData attributeData = null;
/* 166 */     if (this.map.containsKey(uuid) && ((Map)this.map.get(uuid)).containsKey(c)) {
/* 167 */       attributeData = (SXAttributeData)((Map)this.map.get(uuid)).remove(c);
/*     */     }
/* 169 */     return attributeData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePluginAllEntityData(Class<?> c) {
/* 178 */     for (Map<Class<?>, SXAttributeData> statsMap : this.map.values()) {
/* 179 */       statsMap.remove(c);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEntityAllPluginData(UUID uuid) {
/* 189 */     this.map.remove(uuid);
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
/*     */   public SXAttributeData getLoreData(LivingEntity entity, SXConditionType type, List<String> loreList) {
/* 204 */     return this.plugin.getAttributeManager().getListStats(entity, type, loreList);
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
/*     */   public SXAttributeData getItemData(LivingEntity livingEntity, SXConditionType type, ItemStack... item) {
/* 219 */     return this.plugin.getAttributeManager().getItemData(livingEntity, type, item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEntityName(LivingEntity livingEntity) {
/* 229 */     return this.plugin.getOnHealthChangeDisplayListener().getEntityName(livingEntity);
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
/*     */   public boolean isUse(LivingEntity entity, SXConditionType type, ItemStack item) {
/* 242 */     return this.plugin.getAttributeManager().isUse(entity, type, item).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemLevel(ItemStack item) {
/* 252 */     return SubCondition.getItemLevel(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEntityLevel(LivingEntity entity) {
/* 263 */     return SubCondition.getLevel(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEquipmentData(LivingEntity entity) {
/* 273 */     this.plugin.getAttributeManager().loadEquipmentData(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateRPGInventoryData(Player player) {
/* 283 */     this.plugin.getAttributeManager().loadRPGInventoryData(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateHandData(LivingEntity entity) {
/* 292 */     this.plugin.getAttributeManager().loadHandData(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSlotData(Player player) {
/* 301 */     this.plugin.getAttributeManager().loadSlotData(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateStats(LivingEntity entity) {
/* 310 */     this.plugin.getAttributeManager().updateStatsEvent(entity);
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
/*     */   public ItemStack getItem(String itemKey, Player player) {
/* 322 */     return this.plugin.getItemDataManager().getItem(itemKey, player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getItemList() {
/* 331 */     return this.plugin.getItemDataManager().getItemList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Hologram> getHologramsList() {
/* 340 */     return this.plugin.getOnDamageListener().getHologramsList();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\api\SXAttributeAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */