/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionHeight
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 11 */     double height = l.getY();
/*    */     
/* 13 */     if (MythicUtil.matchNumber(data, height)) {
/* 14 */       return true;
/*    */     }
/* 16 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionHeight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */