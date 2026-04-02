/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionInBlock
/*    */   extends SCondition
/*    */ {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 13 */     Block b = l.getBlock();
/*    */     
/* 15 */     MythicMobs.debug(4, "Condition inBlock called. Mob appears to be standing on: " + b.getType().toString().toLowerCase());
/*    */     
/* 17 */     String[] split = data.split(",");
/*    */     
/* 19 */     for (String s : split) {
/* 20 */       if (b.getType().toString().toLowerCase().equals(s.toLowerCase())) {
/* 21 */         return true;
/*    */       }
/*    */     } 
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionInBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */