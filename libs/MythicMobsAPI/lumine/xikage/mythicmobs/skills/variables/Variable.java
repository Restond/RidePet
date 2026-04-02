/*    */ package lumine.xikage.mythicmobs.skills.variables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableType;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.StringVariable;
/*    */ 
/*    */ 
/*    */ public abstract class Variable
/*    */ {
/* 11 */   private long expirationTime = 0L;
/*    */   
/*    */   public Variable() {
/* 14 */     this.expirationTime = 0L;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Variable(long expirationTime) {
/* 22 */     this.expirationTime = expirationTime;
/*    */   }
/*    */   
/*    */   public boolean shouldExpire() {
/* 26 */     return (this.expirationTime <= 1L) ? false : ((this.expirationTime <= System.currentTimeMillis()));
/*    */   }
/*    */   
/*    */   public boolean shouldSave() {
/* 30 */     return (!shouldExpire() && this.expirationTime != 1L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Object get();
/*    */ 
/*    */ 
/*    */   
/*    */   public static io.lumine.xikage.mythicmobs.skills.variables.Variable ofType(VariableType type, String value) {
/* 41 */     return ofType(type, value, 0L);
/*    */   }
/*    */   
/*    */   public static io.lumine.xikage.mythicmobs.skills.variables.Variable ofType(VariableType type, String value, boolean save) {
/* 45 */     return ofType(type, value, save ? 0L : 1L);
/*    */   } public static io.lumine.xikage.mythicmobs.skills.variables.Variable ofType(VariableType type, String value, long expire) {
/*    */     int i;
/*    */     float val;
/* 49 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$variables$VariableType[type.ordinal()]) {
/*    */       case 1:
/* 51 */         i = Integer.valueOf(value).intValue();
/* 52 */         return (io.lumine.xikage.mythicmobs.skills.variables.Variable)new IntegerVariable(i, expire);
/*    */       
/*    */       case 2:
/* 55 */         val = Float.valueOf(value).floatValue();
/* 56 */         return (io.lumine.xikage.mythicmobs.skills.variables.Variable)new FloatVariable(val, expire);
/*    */       
/*    */       case 3:
/* 59 */         return (io.lumine.xikage.mythicmobs.skills.variables.Variable)new StringVariable(value, expire);
/*    */     } 
/*    */     
/* 62 */     return null;
/*    */   }
/*    */   
/*    */   public static io.lumine.xikage.mythicmobs.skills.variables.Variable toType(io.lumine.xikage.mythicmobs.skills.variables.Variable var, VariableType type) {
/* 66 */     return ofType(type, var.toString());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\Variable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */