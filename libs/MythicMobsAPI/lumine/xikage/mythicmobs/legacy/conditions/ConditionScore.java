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
/*    */ public class ConditionScore
/*    */   extends SCondition
/*    */ {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 15 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 17 */     String[] split = data.split(";");
/*    */     
/* 19 */     String obj = split[0];
/* 20 */     String ent = split[1];
/* 21 */     String val = split[2];
/*    */     
/* 23 */     Objective objective = scoreboard.getObjective(obj);
/*    */     
/* 25 */     if (objective == null) {
/* 26 */       objective = scoreboard.registerNewObjective(obj, "dummy");
/*    */     }
/*    */     
/* 29 */     int score = objective.getScore(ent).getScore();
/*    */     
/* 31 */     if (MythicUtil.matchNumber(val, score)) {
/* 32 */       return true;
/*    */     }
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */