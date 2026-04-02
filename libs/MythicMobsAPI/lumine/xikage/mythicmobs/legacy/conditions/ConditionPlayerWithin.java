/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ConditionPlayerWithin
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 11 */     float d = Float.parseFloat(data);
/*    */     
/* 13 */     for (Player p : l.getWorld().getPlayers()) {
/* 14 */       if (p.getWorld().equals(l.getWorld()) && 
/* 15 */         l.distanceSquared(p.getLocation()) <= (d * d)) return true; 
/*    */     } 
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionPlayerWithin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */