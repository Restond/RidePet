/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionTTWithin
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 15 */     float d = Float.parseFloat(data);
/*    */     
/* 17 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)e);
/* 18 */     if (am == null) return false; 
/* 19 */     if (!am.hasThreatTable()) return false;
/*    */     
/* 21 */     if (am.getThreatTable().inCombat())
/* 22 */       for (AbstractEntity p : am.getThreatTable().getAllThreatTargets()) {
/* 23 */         if (BukkitAdapter.adapt(p.getWorld()).equals(e.getWorld()) && 
/* 24 */           l.distanceSquared(BukkitAdapter.adapt(p.getLocation())) <= (d * d)) return true;
/*    */       
/*    */       }  
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionTTWithin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */