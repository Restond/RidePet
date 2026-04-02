/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_12_R1.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.ai.PathfinderHolder;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_12_R1.EntityCreature;
/*    */ import net.minecraft.server.v1_12_R1.EntityInsentient;
/*    */ import net.minecraft.server.v1_12_R1.PathfinderGoal;
/*    */ import net.minecraft.server.v1_12_R1.PathfinderGoalAvoidTarget;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ 
/*    */ @MythicAIGoal(name = "fleeConditional", aliases = {"fleeIf"}, description = "Run away from nearby entities that meets the conditions", premium = true)
/*    */ public class FleeConditionalGoal
/*    */   extends WrappedPathfindingGoal
/*    */   implements PathfinderHolder
/*    */ {
/*    */   protected float distance;
/*    */   protected double speed;
/*    */   protected double safeSpeed;
/*    */   protected String conditionString;
/* 27 */   protected List<SkillCondition> conditions = null;
/*    */   
/*    */   public FleeConditionalGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 30 */     super(entity, line, mlc);
/*    */     
/* 32 */     this.distance = mlc.getFloat(new String[] { "distance", "d" }, 4.0F);
/* 33 */     this.speed = mlc.getDouble(new String[] { "speed", "s" }, 1.2000000476837158D);
/* 34 */     this.safeSpeed = mlc.getDouble(new String[] { "safespeed", "ss" }, 1.0D);
/*    */     
/* 36 */     this.conditionString = mlc.getString(new String[] { "fleeconditions", "conditions", "cond", "c" }, "null", new String[0]);
/*    */     
/* 38 */     if (this.conditionString != null) {
/* 39 */       this.conditions = getPlugin().getSkillManager().getConditions(this.conditionString);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 45 */     return this.entity.isCreature();
/*    */   }
/*    */ 
/*    */   
/*    */   public PathfinderGoal create() {
/* 50 */     EntityInsentient e = PathfinderHolder.getNMSEntity(this.entity);
/*    */     
/* 52 */     return (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityInsentient.class, targetEntity -> {
/*    */           try {
/*    */             AbstractEntity absEntity = BukkitAdapter.adapt((Entity)((EntityInsentient)targetEntity).getBukkitEntity());
/*    */             for (SkillCondition cond : this.conditions) {
/*    */               if (!cond.evaluateToEntity(this.entity, absEntity)) {
/*    */                 return false;
/*    */               }
/*    */             } 
/* 60 */           } catch (Exception|Error ex) {
/*    */             ex.printStackTrace();
/*    */           } 
/*    */           return true;
/*    */         }this.distance, this.safeSpeed, this.speed);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_12_R1\ai\goals\FleeConditionalGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */