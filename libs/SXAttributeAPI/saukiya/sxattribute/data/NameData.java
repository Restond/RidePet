/*    */ package saukiya.sxattribute.data;
/*    */ 
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class NameData {
/*    */   private String name;
/*    */   private LivingEntity entity;
/*    */   private boolean visible;
/*    */   private Long tick;
/*    */   
/*    */   public String getName() {
/* 13 */     return this.name;
/*    */   } public LivingEntity getEntity() {
/* 15 */     return this.entity;
/*    */   } public boolean isVisible() {
/* 17 */     return this.visible;
/*    */   } public Long getTick() {
/* 19 */     return this.tick;
/*    */   }
/* 21 */   private int updateTick = Config.getConfig().getInt("Health.NameVisible.DisplayTime"); public int getUpdateTick() { return this.updateTick; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NameData(LivingEntity entity, String name, boolean visible) {
/* 31 */     this.entity = entity;
/* 32 */     this.name = name;
/* 33 */     this.visible = visible;
/*    */   }
/*    */   
/*    */   public void updateTick() {
/* 37 */     this.tick = Long.valueOf(System.currentTimeMillis() + (this.updateTick * 1000));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\NameData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */