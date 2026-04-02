/*    */ package saukiya.sxattribute.data.condition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SXConditionType
/*    */ {
/* 12 */   public static final github.saukiya.sxattribute.data.condition.SXConditionType RPG_INVENTORY = new github.saukiya.sxattribute.data.condition.SXConditionType(Type.RPG_INVENTORY);
/*    */ 
/*    */ 
/*    */   
/* 16 */   public static final github.saukiya.sxattribute.data.condition.SXConditionType EQUIPMENT = new github.saukiya.sxattribute.data.condition.SXConditionType(Type.EQUIPMENT);
/*    */ 
/*    */ 
/*    */   
/* 20 */   public static final github.saukiya.sxattribute.data.condition.SXConditionType HAND = new github.saukiya.sxattribute.data.condition.SXConditionType(Type.HAND);
/*    */ 
/*    */ 
/*    */   
/* 24 */   public static final github.saukiya.sxattribute.data.condition.SXConditionType MAIN_HAND = new github.saukiya.sxattribute.data.condition.SXConditionType(Type.HAND, "MainHand");
/*    */ 
/*    */ 
/*    */   
/* 28 */   public static final github.saukiya.sxattribute.data.condition.SXConditionType OFF_HAND = new github.saukiya.sxattribute.data.condition.SXConditionType(Type.HAND, "OffHand");
/*    */ 
/*    */ 
/*    */   
/* 32 */   public static final github.saukiya.sxattribute.data.condition.SXConditionType SLOT = new github.saukiya.sxattribute.data.condition.SXConditionType(Type.SLOT);
/*    */   
/*    */   private final String name;
/*    */   private final Type type;
/* 36 */   public static final github.saukiya.sxattribute.data.condition.SXConditionType ALL = new github.saukiya.sxattribute.data.condition.SXConditionType(Type.ALL);
/*    */   public String getName() {
/* 38 */     return this.name;
/*    */   }
/*    */   public Type getType() {
/* 41 */     return this.type;
/*    */   }
/*    */   
/*    */   public SXConditionType(Type type) {
/* 45 */     this.type = type;
/* 46 */     this.name = type.getName();
/*    */   }
/*    */   
/*    */   public SXConditionType(Type type, String name) {
/* 50 */     this.type = type;
/* 51 */     this.name = name;
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
/* 62 */     return (obj instanceof github.saukiya.sxattribute.data.condition.SXConditionType && this.type.equals(((github.saukiya.sxattribute.data.condition.SXConditionType)obj).type));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\SXConditionType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */