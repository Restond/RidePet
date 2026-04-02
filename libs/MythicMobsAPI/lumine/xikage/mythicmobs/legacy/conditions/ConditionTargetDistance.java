/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionTargetDistance
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     if (e == null) return true; 
/* 13 */     if (!(e instanceof Creature)) return true;
/*    */     
/* 15 */     Creature c = (Creature)e;
/*    */     
/* 17 */     if (c.getTarget() == null) return false; 
/* 18 */     if (!c.getTarget().getWorld().equals(c.getWorld())) return false;
/*    */     
/* 20 */     double distance = c.getTarget().getLocation().distance(c.getLocation());
/*    */     
/* 22 */     if (MythicUtil.matchNumber(data, distance)) {
/* 23 */       return true;
/*    */     }
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionTargetDistance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */