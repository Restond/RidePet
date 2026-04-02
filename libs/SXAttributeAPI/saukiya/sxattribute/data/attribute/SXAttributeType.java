/*    */ package saukiya.sxattribute.data.attribute;
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
/*    */ public class SXAttributeType
/*    */ {
/* 14 */   public static final github.saukiya.sxattribute.data.attribute.SXAttributeType DAMAGE = new github.saukiya.sxattribute.data.attribute.SXAttributeType(Type.DAMAGE);
/*    */ 
/*    */ 
/*    */   
/* 18 */   public static final github.saukiya.sxattribute.data.attribute.SXAttributeType DEFENCE = new github.saukiya.sxattribute.data.attribute.SXAttributeType(Type.DEFENCE);
/*    */ 
/*    */ 
/*    */   
/* 22 */   public static final github.saukiya.sxattribute.data.attribute.SXAttributeType UPDATE = new github.saukiya.sxattribute.data.attribute.SXAttributeType(Type.UPDATE);
/*    */   
/*    */   private String name;
/*    */   private Type type;
/* 26 */   public static final github.saukiya.sxattribute.data.attribute.SXAttributeType OTHER = new github.saukiya.sxattribute.data.attribute.SXAttributeType(Type.OTHER);
/*    */   public String getName() {
/* 28 */     return this.name;
/*    */   }
/*    */   public Type getType() {
/* 31 */     return this.type;
/*    */   }
/*    */   
/*    */   public SXAttributeType(Type type) {
/* 35 */     this.type = type;
/* 36 */     this.name = type.getName();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SXAttributeType(Type type, String name) {
/* 47 */     this.type = type;
/* 48 */     this.name = name;
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
/* 59 */     return (obj instanceof github.saukiya.sxattribute.data.attribute.SXAttributeType && this.type.equals(((github.saukiya.sxattribute.data.attribute.SXAttributeType)obj).type));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\SXAttributeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */