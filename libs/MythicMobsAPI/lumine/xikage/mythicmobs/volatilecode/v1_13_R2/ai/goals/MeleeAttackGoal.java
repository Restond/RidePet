/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_13_R2.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.ai.PathfinderHolder;
/*    */ import net.minecraft.server.v1_13_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_13_R2.PathfinderGoal;
/*    */ import net.minecraft.server.v1_13_R2.PathfinderGoalMeleeAttack;
/*    */ 
/*    */ @MythicAIGoal(name = "meleeAttack", aliases = {}, description = "Path to the current target")
/*    */ public class MeleeAttackGoal
/*    */   extends WrappedPathfindingGoal implements PathfinderHolder {
/*    */   public MeleeAttackGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
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
/* 26 */     return (PathfinderGoal)new PathfinderGoalMeleeAttack((EntityCreature)PathfinderHolder.getNMSEntity(this.entity), 1.0D, false);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_13_R2\ai\goals\MeleeAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */