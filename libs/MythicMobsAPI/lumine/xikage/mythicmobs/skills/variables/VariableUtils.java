/*    */ package lumine.xikage.mythicmobs.skills.variables;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableType;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.AbstractVariable;
/*    */ 
/*    */ public class VariableUtils {
/*    */   public static VariableType getType(Class<? extends Variable> clazz) {
/*  8 */     if (clazz.equals(IntegerVariable.class))
/*  9 */       return VariableType.INTEGER; 
/* 10 */     if (clazz.equals(FloatVariable.class))
/* 11 */       return VariableType.FLOAT; 
/* 12 */     if (clazz.equals(StringVariable.class))
/* 13 */       return VariableType.STRING; 
/* 14 */     if (clazz.equals(AbstractVariable.class)) {
/* 15 */       return VariableType.ABSTRACT;
/*    */     }
/* 17 */     throw new IllegalArgumentException("Invalid variable class (" + clazz.getName() + ").");
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getTypeName(Class<? extends Variable> clazz) {
/* 22 */     if (clazz.equals(IntegerVariable.class))
/* 23 */       return "Integer"; 
/* 24 */     if (clazz.equals(FloatVariable.class))
/* 25 */       return "Float"; 
/* 26 */     if (clazz.equals(StringVariable.class))
/* 27 */       return "String"; 
/* 28 */     if (clazz.equals(AbstractVariable.class)) {
/* 29 */       return "Abstract";
/*    */     }
/* 31 */     throw new IllegalArgumentException("Invalid variable class (" + clazz.getName() + ").");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Class<? extends Variable> getTypeClass(String type) {
/* 43 */     switch (type) {
/*    */       case "Integer":
/* 45 */         return (Class)IntegerVariable.class;
/*    */       case "Float":
/* 47 */         return (Class)FloatVariable.class;
/*    */       case "String":
/* 49 */         return (Class)StringVariable.class;
/*    */       case "Abstract":
/* 51 */         return (Class)AbstractVariable.class;
/*    */     } 
/* 53 */     throw new IllegalArgumentException("Invalid variable type : " + type + ".");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\VariableUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */