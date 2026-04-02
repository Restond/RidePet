/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionLightLevelAbove
/*    */   extends SCondition
/*    */ {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     int c, ll = l.getBlock().getLightLevel();
/*    */ 
/*    */     
/*    */     try {
/* 16 */       c = Integer.parseInt(data);
/* 17 */     } catch (Exception ex) {
/* 18 */       MythicMobs.error("A LightLevelAbove condition is incorrectly configured: value must be an integer. Value=" + data);
/* 19 */       return true;
/*    */     } 
/*    */     
/* 22 */     if (ll > c) {
/* 23 */       return true;
/*    */     }
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionLightLevelAbove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */