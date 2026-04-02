/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionOnBlock
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     Material m = l.subtract(0.0D, 1.0D, 0.0D).getBlock().getType();
/*    */     
/* 14 */     MythicMobs.debug(4, "Condition onBlock called. Mob appears to be standing on: " + m.toString().toLowerCase());
/*    */     
/* 16 */     String[] split = data.split(",");
/*    */     
/* 18 */     for (String s : split) {
/* 19 */       if (m.toString().toLowerCase().equals(s.toLowerCase())) {
/* 20 */         return true;
/*    */       }
/*    */     } 
/* 23 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionOnBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */