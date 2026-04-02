/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionHeightAbove extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/*    */     try {
/* 10 */       if (l.getBlockY() >= Integer.parseInt(data)) {
/* 11 */         return true;
/*    */       }
/* 13 */       return false;
/*    */     }
/* 15 */     catch (Exception ex) {
/* 16 */       return true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionHeightAbove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */