/*    */ package saukiya.sxattribute.data.condition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SXConditionReturnType
/*    */ {
/* 12 */   public static final github.saukiya.sxattribute.data.condition.SXConditionReturnType LORE = new github.saukiya.sxattribute.data.condition.SXConditionReturnType(Type.LORE);
/*    */ 
/*    */ 
/*    */   
/* 16 */   public static final github.saukiya.sxattribute.data.condition.SXConditionReturnType ITEM = new github.saukiya.sxattribute.data.condition.SXConditionReturnType(Type.ITEM);
/*    */   
/*    */   private final String name;
/*    */   private final Type type;
/* 20 */   public static final github.saukiya.sxattribute.data.condition.SXConditionReturnType NULL = new github.saukiya.sxattribute.data.condition.SXConditionReturnType(Type.NULL);
/*    */   
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */   public Type getType() {
/* 26 */     return this.type;
/*    */   }
/*    */   
/*    */   public SXConditionReturnType(Type type) {
/* 30 */     this.type = type;
/* 31 */     this.name = type.getName();
/*    */   }
/*    */   
/*    */   public SXConditionReturnType(Type type, String name) {
/* 35 */     this.type = type;
/* 36 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 47 */     return (obj instanceof github.saukiya.sxattribute.data.condition.SXConditionReturnType && this.type.equals(((github.saukiya.sxattribute.data.condition.SXConditionReturnType)obj).type));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\SXConditionReturnType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */