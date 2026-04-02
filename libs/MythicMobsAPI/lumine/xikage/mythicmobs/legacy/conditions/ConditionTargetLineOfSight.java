/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionTargetLineOfSight extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 10 */     if (e == null) return true; 
/* 11 */     if (!(e instanceof Creature)) return true;
/*    */     
/* 13 */     Creature c = (Creature)e;
/*    */     
/* 15 */     if (c.getTarget() == null) return false;
/*    */     
/* 17 */     return c.hasLineOfSight((Entity)c.getTarget());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionTargetLineOfSight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */