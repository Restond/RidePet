/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.ModifyGlobalScoreMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Score;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "modifytargetscore", aliases = {"mts"}, description = "")
/*    */ public class ModifyTargetScoreMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected PlaceholderString objective;
/*    */   protected ModifyGlobalScoreMechanic.ModifyScoreAction action;
/*    */   protected PlaceholderString value;
/*    */   
/*    */   public ModifyTargetScoreMechanic(String skill, MythicLineConfig mlc) {
/* 26 */     super(skill, mlc);
/*    */     
/* 28 */     this.objective = PlaceholderString.of(mlc.getString(new String[] { "objective", "obj", "o" }, "", new String[0]));
/* 29 */     this.value = PlaceholderString.of(mlc.getString(new String[] { "value", "v" }));
/*    */     
/* 31 */     String strAction = mlc.getString(new String[] { "action", "a" }, "ADD", new String[0]);
/*    */     
/*    */     try {
/* 34 */       this.action = ModifyGlobalScoreMechanic.ModifyScoreAction.valueOf(strAction.toUpperCase());
/* 35 */     } catch (Exception ex) {
/* 36 */       this.action = ModifyGlobalScoreMechanic.ModifyScoreAction.ADD;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*    */     Score score;
/* 42 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 44 */     String fobjective = this.objective.get((PlaceholderMeta)data, target);
/* 45 */     int fvalue = Integer.valueOf(this.value.get((PlaceholderMeta)data, target)).intValue();
/*    */     
/* 47 */     Objective objective = scoreboard.getObjective(fobjective);
/*    */     
/* 49 */     if (objective == null) {
/* 50 */       objective = scoreboard.registerNewObjective(fobjective, "dummy");
/*    */     }
/*    */ 
/*    */     
/* 54 */     if (target.isPlayer()) {
/* 55 */       score = objective.getScore(target.asPlayer().getName());
/*    */     } else {
/* 57 */       score = objective.getScore(target.getUniqueId().toString());
/*    */     } 
/* 59 */     score.setScore(applyModifier(score.getScore(), fvalue));
/*    */     
/* 61 */     return true;
/*    */   }
/*    */   
/*    */   protected int applyModifier(int score, int value) {
/* 65 */     if (this.action == ModifyGlobalScoreMechanic.ModifyScoreAction.SET) {
/* 66 */       score = value;
/* 67 */     } else if (this.action == ModifyGlobalScoreMechanic.ModifyScoreAction.ADD) {
/* 68 */       score += value;
/* 69 */     } else if (this.action == ModifyGlobalScoreMechanic.ModifyScoreAction.SUBTRACT) {
/* 70 */       score -= value;
/* 71 */     } else if (this.action == ModifyGlobalScoreMechanic.ModifyScoreAction.MULTIPLY) {
/* 72 */       score *= value;
/* 73 */     } else if (this.action == ModifyGlobalScoreMechanic.ModifyScoreAction.DIVIDE) {
/* 74 */       score /= value;
/* 75 */     } else if (this.action == ModifyGlobalScoreMechanic.ModifyScoreAction.MOD) {
/* 76 */       score %= value;
/*    */     } 
/*    */     
/* 79 */     return score;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ModifyTargetScoreMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */