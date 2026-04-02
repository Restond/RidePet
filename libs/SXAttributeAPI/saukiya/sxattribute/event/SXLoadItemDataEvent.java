/*    */ package saukiya.sxattribute.event;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SXLoadItemDataEvent
/*    */   extends Event
/*    */ {
/*    */   private final SXConditionType type;
/*    */   private final LivingEntity entity;
/*    */   private SXAttributeData attributeData;
/*    */   private ItemStack[] items;
/* 22 */   private static final HandlerList handlers = new HandlerList(); public SXConditionType getType() {
/* 23 */     return this.type;
/*    */   } public LivingEntity getEntity() {
/* 25 */     return this.entity;
/*    */   }
/* 27 */   public void setAttributeData(SXAttributeData attributeData) { this.attributeData = attributeData; } public SXAttributeData getAttributeData() {
/* 28 */     return this.attributeData;
/*    */   }
/* 30 */   public void setItems(ItemStack[] items) { this.items = items; } public ItemStack[] getItems() {
/* 31 */     return this.items;
/*    */   }
/*    */   
/*    */   public SXLoadItemDataEvent(SXConditionType type, LivingEntity entity, SXAttributeData attributeData, ItemStack... items) {
/* 35 */     this.type = type;
/* 36 */     this.entity = entity;
/* 37 */     this.attributeData = attributeData;
/* 38 */     this.items = items;
/* 39 */     Bukkit.getPluginManager().callEvent(this);
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 43 */     return handlers;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 47 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\event\SXLoadItemDataEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */