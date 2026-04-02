/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "globalscore", aliases = {"scoreglobal"}, description = "Checks a global scoreboard value")
/*    */ public class ScoreGlobalCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "objective", aliases = {"o"}, description = "The objective")
/* 18 */   private String obj = "";
/*    */   
/*    */   @MythicField(name = "value", aliases = {"v"}, description = "The value to match")
/* 21 */   private String val = "";
/*    */ 
/*    */   
/*    */   public ScoreGlobalCondition(String line, MythicLineConfig mlc) {
/* 25 */     super(line);
/*    */     
/* 27 */     if ((this.conditionVar.split(";")).length > 2) {
/* 28 */       String[] split = this.conditionVar.split(";");
/*    */       
/* 30 */       this.obj = split[0];
/* 31 */       this.val = split[1];
/*    */     } 
/* 33 */     this.obj = mlc.getString(new String[] { "objective", "obj", "o" }, this.obj, new String[0]);
/* 34 */     this.val = mlc.getString(new String[] { "value", "val", "v" }, this.val, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 39 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 41 */     Objective objective = scoreboard.getObjective(this.obj);
/*    */     
/* 43 */     if (objective == null) {
/* 44 */       objective = scoreboard.registerNewObjective(this.obj, "dummy");
/*    */     }
/*    */     
/* 47 */     int score = objective.getScore("__GLOBAL__").getScore();
/*    */     
/* 49 */     if (MythicUtil.matchNumber(this.val, score)) {
/* 50 */       return true;
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\ScoreGlobalCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */