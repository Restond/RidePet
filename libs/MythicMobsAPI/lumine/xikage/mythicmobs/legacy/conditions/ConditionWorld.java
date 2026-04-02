/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionWorld
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 10 */     String[] worlds = data.split(",");
/* 11 */     for (String w : worlds) {
/* 12 */       if (w.equals(l.getWorld().getName())) {
/* 13 */         return true;
/*    */       }
/*    */     } 
/* 16 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */