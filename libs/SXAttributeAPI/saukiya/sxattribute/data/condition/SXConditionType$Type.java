/*    */ package saukiya.sxattribute.data.condition;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum Type
/*    */ {
/* 69 */   RPG_INVENTORY("RpgInventory"),
/* 70 */   EQUIPMENT("Equipment"),
/* 71 */   HAND("Hand"),
/* 72 */   SLOT("Slot"),
/* 73 */   OTHER("Other"),
/* 74 */   ALL("All");
/*    */   public String getName() {
/* 76 */     return this.name;
/*    */   }
/*    */   String name;
/*    */   Type(String name) {
/* 80 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\SXConditionType$Type.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */