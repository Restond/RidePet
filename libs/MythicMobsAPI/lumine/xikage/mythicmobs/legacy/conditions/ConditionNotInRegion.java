/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.WorldGuardSupport;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionNotInRegion
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 13 */     if (MythicMobs.inst().getCompatibility().getWorldGuard().isPresent()) {
/* 14 */       return !((WorldGuardSupport)MythicMobs.inst().getCompatibility().getWorldGuard().get()).isLocationInRegions(BukkitAdapter.adapt(l), data);
/*    */     }
/* 16 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionNotInRegion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */