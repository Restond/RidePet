/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ public class ConditionScoreMob
/*    */   extends SCondition
/*    */ {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 15 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 17 */     String[] split = data.split(";");
/*    */     
/* 19 */     String obj = split[0];
/* 20 */     String val = split[1];
/*    */     
/* 22 */     Objective objective = scoreboard.getObjective(obj);
/*    */     
/* 24 */     if (objective == null) {
/* 25 */       objective = scoreboard.registerNewObjective(obj, "dummy");
/*    */     }
/*    */     
/* 28 */     int score = objective.getScore(e.getUniqueId().toString()).getScore();
/*    */     
/* 30 */     if (MythicUtil.matchNumber(val, score)) {
/* 31 */       return true;
/*    */     }
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionScoreMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */