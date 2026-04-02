/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_13_R2.ai.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.ai.PathfinderHolder;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_13_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_13_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_13_R2.PathfinderGoal;
/*    */ import net.minecraft.server.v1_13_R2.PathfinderGoalNearestAttackableTarget;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ @MythicAIGoal(name = "nearestConditionalTarget", aliases = {"nearestConditional", "nearestIf"}, description = "Target a nearby attacker that meets the conditions", premium = true)
/*    */ public class NearestConditionalTargetGoal extends WrappedPathfindingGoal implements PathfinderHolder {
/*    */   protected String targetConditionString;
/* 20 */   protected List<SkillCondition> targetConditions = null;
/*    */   
/*    */   public NearestConditionalTargetGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 23 */     super(entity, line, mlc);
/*    */     
/* 25 */     this.targetConditionString = mlc.getString(new String[] { "targetconditions", "conditions", "cond", "c" }, "null", new String[0]);
/*    */     
/* 27 */     if (this.targetConditionString != null) {
/* 28 */       this.targetConditions = getPlugin().getSkillManager().getConditions(this.targetConditionString);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 34 */     return this.entity.isCreature();
/*    */   }
/*    */ 
/*    */   
/*    */   public PathfinderGoal create() {
/* 39 */     EntityInsentient e = PathfinderHolder.getNMSEntity(this.entity);
/*    */     
/* 41 */     return (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityInsentient.class, 0, true, false, targetEntity -> {
/*    */           try {
/*    */             AbstractEntity absEntity = BukkitAdapter.adapt((Entity)((EntityInsentient)targetEntity).getBukkitEntity());
/*    */             
/*    */             for (SkillCondition cond : this.targetConditions) {
/*    */               if (!cond.evaluateToEntity(this.entity, absEntity)) {
/*    */                 return false;
/*    */               }
/*    */             } 
/* 50 */           } catch (Exception|Error ex) {
/*    */             ex.printStackTrace();
/*    */           } 
/*    */           return true;
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_13_R2\ai\targeters\NearestConditionalTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */