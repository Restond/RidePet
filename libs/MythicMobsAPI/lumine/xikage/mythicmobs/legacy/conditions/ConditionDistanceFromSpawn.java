/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionDistanceFromSpawn
/*    */   extends SCondition
/*    */ {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     double distance = l.getWorld().getSpawnLocation().distance(l);
/*    */     
/* 14 */     if (MythicUtil.matchNumber(data, distance)) {
/* 15 */       return true;
/*    */     }
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionDistanceFromSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */