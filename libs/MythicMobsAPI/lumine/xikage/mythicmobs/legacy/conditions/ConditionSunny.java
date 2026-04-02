/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionSunny
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 10 */     boolean raining = l.getWorld().hasStorm();
/* 11 */     boolean value = Boolean.parseBoolean(data);
/* 12 */     if ((raining && !value) || (!raining && value == true)) {
/* 13 */       return true;
/*    */     }
/* 15 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionSunny.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */