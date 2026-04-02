/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionMobType
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 10 */     if (e == null) return false;
/*    */     
/* 12 */     String[] split = data.toLowerCase().split(",");
/*    */     
/* 14 */     for (String s : split) {
/* 15 */       if (e.getType().toString().toLowerCase().equals(s)) return true; 
/*    */     } 
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionMobType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */