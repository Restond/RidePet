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
/*    */ @MythicCondition(author = "Ashijin", name = "score", aliases = {}, description = "Checks a scoreboard value of the target entity")
/*    */ public class ScoreCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "objective", aliases = {"o"}, description = "The objective")
/* 18 */   private String obj = "";
/*    */   
/*    */   @MythicField(name = "entry", aliases = {"e"}, description = "The entry")
/* 21 */   private String ent = null;
/*    */   
/*    */   @MythicField(name = "value", aliases = {"v"}, description = "The value to match")
/* 24 */   private String val = "";
/*    */ 
/*    */   
/*    */   public ScoreCondition(String line, MythicLineConfig mlc) {
/* 28 */     super(line);
/*    */     
/* 30 */     if ((this.conditionVar.split(";")).length > 2) {
/* 31 */       String[] split = this.conditionVar.split(";");
/*    */       
/* 33 */       this.obj = split[0];
/* 34 */       this.ent = split[1];
/* 35 */       this.val = split[2];
/*    */     } 
/* 37 */     this.obj = mlc.getString(new String[] { "objective", "obj", "o" }, this.obj, new String[0]);
/* 38 */     this.ent = mlc.getString(new String[] { "entry", "ent", "e" }, this.ent, new String[0]);
/* 39 */     this.val = mlc.getString(new String[] { "value", "val", "v" }, this.val, new String[0]);
/*    */   }
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/*    */     int score;
/* 44 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 46 */     Objective objective = scoreboard.getObjective(this.obj);
/*    */     
/* 48 */     if (objective == null) {
/* 49 */       objective = scoreboard.registerNewObjective(this.obj, "dummy");
/*    */     }
/*    */ 
/*    */     
/* 53 */     if (this.ent == null) {
/* 54 */       if (target.isPlayer()) {
/* 55 */         score = objective.getScore(target.asPlayer().getName()).getScore();
/*    */       } else {
/* 57 */         score = objective.getScore(target.getUniqueId().toString()).getScore();
/*    */       } 
/*    */     } else {
/* 60 */       score = objective.getScore(this.ent).getScore();
/*    */     } 
/*    */     
/* 63 */     if (MythicUtil.matchNumber(this.val, score)) {
/* 64 */       return true;
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\ScoreCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */