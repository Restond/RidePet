/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionPlayerKills
/*    */   extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 13 */     return MythicUtil.matchNumber(data, MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)e).getPlayerKills());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionPlayerKills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */