/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ public class SetTargetScoreMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   private PlaceholderString objective;
/*    */   private int value;
/*    */   
/*    */   public SetTargetScoreMechanic(String skill, MythicLineConfig mlc) {
/* 20 */     super(skill, mlc);
/*    */     
/* 22 */     this.objective = mlc.getPlaceholderString(new String[] { "objective", "obj", "o" }, "", new String[0]);
/* 23 */     this.value = mlc.getInteger(new String[] { "value", "v" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 28 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 30 */     Objective objective = scoreboard.getObjective(this.objective.get((PlaceholderMeta)data, target));
/*    */     
/* 32 */     if (objective == null) {
/* 33 */       objective = scoreboard.registerNewObjective(this.objective.get((PlaceholderMeta)data, target), "dummy");
/*    */     }
/*    */     
/* 36 */     if (target.isPlayer()) {
/* 37 */       objective.getScore(target.asPlayer().getName()).setScore(this.value);
/*    */     } else {
/* 39 */       objective.getScore(target.getUniqueId().toString()).setScore(this.value);
/*    */     } 
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetTargetScoreMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */