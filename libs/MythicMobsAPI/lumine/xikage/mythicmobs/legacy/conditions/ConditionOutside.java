/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionOutside
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 10 */     boolean c = true;
/*    */     
/*    */     try {
/* 13 */       c = Boolean.parseBoolean(data);
/* 14 */     } catch (Exception ex) {
/* 15 */       c = true;
/*    */     } 
/*    */     
/* 18 */     if (c == true) {
/* 19 */       return (l.getWorld().getHighestBlockYAt(l) <= l.getY());
/*    */     }
/* 21 */     return (l.getWorld().getHighestBlockYAt(l) >= l.getY());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionOutside.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */