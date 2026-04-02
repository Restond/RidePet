/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionLunarPhase
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 10 */     if (data.contains("" + (l.getWorld().getFullTime() / 24000L % 8L))) {
/* 11 */       return true;
/*    */     }
/* 13 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionLunarPhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */