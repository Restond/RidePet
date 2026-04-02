/*    */ package saukiya.sxattribute.data.eventdata.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UpdateEventData
/*    */   extends EventData
/*    */ {
/*    */   private final LivingEntity entity;
/*    */   
/*    */   public UpdateEventData(LivingEntity entity) {
/* 16 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LivingEntity getEntity() {
/* 25 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\eventdata\sub\UpdateEventData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */