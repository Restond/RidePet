/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ public class SetMobScoreMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   private PlaceholderString objective;
/*    */   private int value;
/*    */   
/*    */   public SetMobScoreMechanic(String skill, MythicLineConfig mlc) {
/* 19 */     super(skill, mlc);
/*    */     
/* 21 */     this.objective = mlc.getPlaceholderString(new String[] { "objective", "obj", "o" }, "", new String[0]);
/* 22 */     this.value = mlc.getInteger(new String[] { "value", "v" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 27 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 29 */     Objective objective = scoreboard.getObjective(this.objective.get((PlaceholderMeta)data));
/*    */     
/* 31 */     if (objective == null) {
/* 32 */       objective = scoreboard.registerNewObjective(this.objective.get((PlaceholderMeta)data), "dummy");
/*    */     }
/*    */     
/* 35 */     objective.getScore(data.getCaster().getEntity().getUniqueId().toString()).setScore(this.value);
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetMobScoreMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */