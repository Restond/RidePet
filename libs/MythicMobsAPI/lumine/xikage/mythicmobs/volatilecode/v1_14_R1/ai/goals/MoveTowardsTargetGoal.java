/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.PathfinderHolder;
/*    */ import net.minecraft.server.v1_14_R1.EntityCreature;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoal;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoalMoveTowardsTarget;
/*    */ 
/*    */ @MythicAIGoal(name = "moveTowardsTarget", aliases = {}, description = "Path to the current target")
/*    */ public class MoveTowardsTargetGoal
/*    */   extends WrappedPathfindingGoal implements PathfinderHolder {
/*    */   public MoveTowardsTargetGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 16 */     super(entity, line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 21 */     return this.entity.isCreature();
/*    */   }
/*    */ 
/*    */   
/*    */   public PathfinderGoal create() {
/* 26 */     return (PathfinderGoal)new PathfinderGoalMoveTowardsTarget((EntityCreature)PathfinderHolder.getNMSEntity(this.entity), 0.9D, 32.0F);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\ai\goals\MoveTowardsTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */