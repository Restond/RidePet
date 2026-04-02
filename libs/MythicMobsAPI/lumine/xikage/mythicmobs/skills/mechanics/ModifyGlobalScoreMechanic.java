/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Score;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "modifyglobalscore", aliases = {"mgs"}, description = "")
/*    */ public class ModifyGlobalScoreMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill
/*    */ {
/*    */   protected PlaceholderString objective;
/*    */   protected ModifyScoreAction action;
/*    */   protected int value;
/*    */   
/*    */   public ModifyGlobalScoreMechanic(String skill, MythicLineConfig mlc) {
/* 33 */     super(skill, mlc);
/*    */     
/* 35 */     this.objective = PlaceholderString.of(mlc.getString(new String[] { "objective", "obj", "o" }, "", new String[0]));
/* 36 */     this.value = mlc.getInteger(new String[] { "value", "v" });
/*    */     
/* 38 */     String strAction = mlc.getString(new String[] { "action", "a" }, "ADD", new String[0]);
/*    */     
/*    */     try {
/* 41 */       this.action = ModifyScoreAction.valueOf(strAction.toUpperCase());
/* 42 */     } catch (Exception ex) {
/* 43 */       this.action = ModifyScoreAction.ADD;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 49 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 51 */     String fobjective = this.objective.get((PlaceholderMeta)data);
/* 52 */     Objective objective = scoreboard.getObjective(fobjective);
/*    */     
/* 54 */     if (objective == null) {
/* 55 */       objective = scoreboard.registerNewObjective(fobjective, "dummy");
/*    */     }
/*    */     
/* 58 */     Score score = objective.getScore("__GLOBAL__");
/* 59 */     score.setScore(applyModifier(score.getScore()));
/*    */     
/* 61 */     return true;
/*    */   }
/*    */   
/*    */   protected int applyModifier(int score) {
/* 65 */     if (this.action == ModifyScoreAction.SET) {
/* 66 */       score = this.value;
/* 67 */     } else if (this.action == ModifyScoreAction.ADD) {
/* 68 */       score += this.value;
/* 69 */     } else if (this.action == ModifyScoreAction.SUBTRACT) {
/* 70 */       score -= this.value;
/* 71 */     } else if (this.action == ModifyScoreAction.MULTIPLY) {
/* 72 */       score *= this.value;
/* 73 */     } else if (this.action == ModifyScoreAction.DIVIDE) {
/* 74 */       score /= this.value;
/* 75 */     } else if (this.action == ModifyScoreAction.MOD) {
/* 76 */       score %= this.value;
/*    */     } 
/*    */     
/* 79 */     return score;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ModifyGlobalScoreMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */