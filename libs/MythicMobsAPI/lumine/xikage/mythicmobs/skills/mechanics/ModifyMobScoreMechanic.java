/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.ModifyGlobalScoreMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Score;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "modifymobscore", aliases = {"mms"}, description = "")
/*    */ public class ModifyMobScoreMechanic extends ModifyGlobalScoreMechanic implements INoTargetSkill {
/*    */   public ModifyMobScoreMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 22 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 24 */     String fObjective = this.objective.get((PlaceholderMeta)data);
/*    */     
/* 26 */     Objective objective = scoreboard.getObjective(fObjective);
/*    */     
/* 28 */     if (objective == null) {
/* 29 */       objective = scoreboard.registerNewObjective(fObjective, "dummy");
/*    */     }
/*    */     
/* 32 */     Score score = objective.getScore(data.getCaster().getEntity().getUniqueId().toString());
/* 33 */     score.setScore(applyModifier(score.getScore()));
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ModifyMobScoreMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */