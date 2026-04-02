/*    */ package lumine.xikage.mythicmobs.skills.conditions;
/*    */ 
/*    */ public enum ConditionAction {
/*  4 */   REQUIRED,
/*  5 */   CANCEL,
/*  6 */   POWER,
/*  7 */   CAST,
/*  8 */   CASTINSTEAD,
/*  9 */   LEVEL,
/*    */ 
/*    */   
/* 12 */   TRUE,
/* 13 */   FALSE;
/*    */   
/*    */   public static boolean isAction(String str) {
/* 16 */     String s = str.toUpperCase();
/*    */     
/* 18 */     for (io.lumine.xikage.mythicmobs.skills.conditions.ConditionAction a : values()) {
/* 19 */       if (a.toString().equals(s)) return true; 
/*    */     } 
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\ConditionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */