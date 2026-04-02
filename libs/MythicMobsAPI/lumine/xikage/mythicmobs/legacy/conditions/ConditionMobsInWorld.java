/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ class ConditionMobsInWorld
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     int i = 0;
/*    */     
/* 14 */     for (Entity ent : l.getWorld().getEntities()) {
/* 15 */       if (ent instanceof LivingEntity) i++;
/*    */     
/*    */     } 
/* 18 */     if (MythicUtil.matchNumber(data, i)) {
/* 19 */       return true;
/*    */     }
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionMobsInWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */