/*    */ package saukiya.sxattribute.data.attribute;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
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
/* 66 */   DAMAGE("DAMAGE"),
/* 67 */   DEFENCE("DEFENCE"),
/* 68 */   UPDATE("UPDATE"),
/* 69 */   OTHER("OTHER");
/*    */   public String getName() {
/* 71 */     return this.name;
/*    */   }
/*    */   String name;
/*    */   Type(String name) {
/* 75 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\SXAttributeType$Type.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */