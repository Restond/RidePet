/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class InvalidCondition
/*    */   extends SCondition
/*    */ {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     MythicMobs.debug(4, "A spawning configuration is using an invalid condition!");
/* 13 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\InvalidCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */