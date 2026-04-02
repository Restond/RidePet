/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "runaigoalselector", aliases = {"aigoal"}, description = "Modify an AI Goal Selector of the caster")
/*    */ public class RunAIGoalSelectorMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill
/*    */ {
/* 21 */   protected ArrayList<String> goal = new ArrayList<>();
/*    */   
/*    */   public RunAIGoalSelectorMechanic(String skill, MythicLineConfig mlc) {
/* 24 */     super(skill, mlc);
/* 25 */     this.ASYNC_SAFE = false;
/*    */     
/* 27 */     String g = mlc.getString(new String[] { "aigoalselector", "goalselector", "goal", "string", "s" }, null, new String[0]);
/*    */     
/* 29 */     if (g == null) {
/* 30 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'goal' attribute is required.");
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     g = SkillString.parseMessageSpecialChars(g);
/*    */     
/* 36 */     this.goal.add(g);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 41 */     if (!ConfigManager.EnableAIModifiers) return false; 
/* 42 */     MythicMobs.inst().getVolatileCodeHandler().getAIHandler().addPathfinderGoals((LivingEntity)BukkitAdapter.adapt(data.getCaster().getEntity()), this.goal);
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\RunAIGoalSelectorMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */