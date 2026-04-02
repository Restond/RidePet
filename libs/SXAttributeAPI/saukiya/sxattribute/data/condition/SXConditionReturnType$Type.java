/*    */ package saukiya.sxattribute.data.condition;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
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
/* 54 */   LORE("Lore"),
/* 55 */   ITEM("Item"),
/* 56 */   NULL("Null");
/*    */   public String getName() {
/* 58 */     return this.name;
/*    */   }
/*    */   String name;
/*    */   Type(String name) {
/* 62 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\SXConditionReturnType$Type.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */