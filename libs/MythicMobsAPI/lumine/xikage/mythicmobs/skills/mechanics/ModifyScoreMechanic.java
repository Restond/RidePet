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
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "modifyscore", description = "Modifies a scoreboard value")
/*    */ public class ModifyScoreMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill
/*    */ {
/*    */   protected PlaceholderString entry;
/*    */   protected PlaceholderString objective;
/*    */   protected ModifyScoreAction action;
/*    */   protected PlaceholderString value;
/*    */   
/*    */   public ModifyScoreMechanic(String skill, MythicLineConfig mlc) {
/* 35 */     super(skill, mlc);
/*    */     
/* 37 */     this.entry = PlaceholderString.of(mlc.getString(new String[] { "entry", "e", "name", "n" }, "dummy", new String[0]));
/* 38 */     this.objective = PlaceholderString.of(mlc.getString(new String[] { "objective", "obj", "o" }, "", new String[0]));
/* 39 */     this.value = PlaceholderString.of(mlc.getString(new String[] { "value", "v" }, "0", new String[0]));
/*    */     
/* 41 */     String strAction = mlc.getString(new String[] { "action", "a" }, "SET", new String[0]);
/*    */     
/*    */     try {
/* 44 */       this.action = ModifyScoreAction.valueOf(strAction.toUpperCase());
/* 45 */     } catch (Exception ex) {
/* 46 */       this.action = ModifyScoreAction.SET;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 52 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 54 */     String fentry = this.entry.get((PlaceholderMeta)data);
/* 55 */     String fobjective = this.objective.get((PlaceholderMeta)data);
/* 56 */     int fvalue = Integer.valueOf(this.value.get((PlaceholderMeta)data)).intValue();
/*    */     
/* 58 */     Objective objective = scoreboard.getObjective(fobjective);
/*    */     
/* 60 */     if (objective == null) {
/* 61 */       objective = scoreboard.registerNewObjective(fobjective, "dummy");
/*    */     }
/*    */     
/* 64 */     if (this.action != ModifyScoreAction.UNSET) {
/*    */ 
/*    */       
/* 67 */       Score score = objective.getScore(fentry);
/* 68 */       score.setScore(applyModifier(score.getScore(), fvalue));
/*    */     } 
/* 70 */     return true;
/*    */   }
/*    */   
/*    */   protected int applyModifier(int score, int value) {
/* 74 */     if (this.action == ModifyScoreAction.SET) {
/* 75 */       score = value;
/* 76 */     } else if (this.action == ModifyScoreAction.ADD) {
/* 77 */       score += value;
/* 78 */     } else if (this.action == ModifyScoreAction.SUBTRACT) {
/* 79 */       score -= value;
/* 80 */     } else if (this.action == ModifyScoreAction.MULTIPLY) {
/* 81 */       score *= value;
/* 82 */     } else if (this.action == ModifyScoreAction.DIVIDE) {
/* 83 */       score /= value;
/* 84 */     } else if (this.action == ModifyScoreAction.MOD) {
/* 85 */       score %= value;
/*    */     } 
/*    */     
/* 88 */     return score;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ModifyScoreMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */