/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionTargetNotWithin
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     if (e == null) return true; 
/* 13 */     if (!(e instanceof Creature)) return true;
/*    */     
/* 15 */     Creature c = (Creature)e;
/*    */     
/* 17 */     if (c.getTarget() == null) return false;
/*    */     
/* 19 */     int md = 0;
/*    */     
/*    */     try {
/* 22 */       md = Integer.parseInt(data);
/* 23 */     } catch (Exception ex) {
/* 24 */       MythicMobs.error("A TargetNotWithin Condition is inconfigured incorrectly somewhere! It must be an integer. String=" + data);
/* 25 */       return false;
/*    */     } 
/*    */     
/* 28 */     if (c.getTarget().getWorld().equals(c.getWorld())) {
/* 29 */       if (c.getTarget().getLocation().distanceSquared(c.getLocation()) > (md * md)) {
/* 30 */         return true;
/*    */       }
/* 32 */       return false;
/*    */     } 
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionTargetNotWithin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */