/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionInCombat
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 13 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)e);
/*    */     
/* 15 */     if (am.hasThreatTable()) {
/* 16 */       return am.getThreatTable().inCombat();
/*    */     }
/* 18 */     return (am.getEntity().getTarget() != null);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionInCombat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */