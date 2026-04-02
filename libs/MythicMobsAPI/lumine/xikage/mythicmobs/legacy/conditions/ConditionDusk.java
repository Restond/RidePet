/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionDusk extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/*  9 */     long time = l.getWorld().getTime();
/*    */     
/* 11 */     if (time > 18000L && time < 14000L) {
/* 12 */       return true;
/*    */     }
/* 14 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionDusk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */