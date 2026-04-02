/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.PathfinderHolder;
/*    */ import net.minecraft.server.v1_14_R1.EntityCreature;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoal;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoalRandomFly;
/*    */ 
/*    */ @MythicAIGoal(name = "randomFly", aliases = {}, description = "Fly around randomly")
/*    */ public class RandomFlyGoal
/*    */   extends WrappedPathfindingGoal
/*    */   implements PathfinderHolder
/*    */ {
/*    */   public RandomFlyGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 18 */     super(entity, line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 23 */     return this.entity.isCreature();
/*    */   }
/*    */ 
/*    */   
/*    */   public PathfinderGoal create() {
/* 28 */     return (PathfinderGoal)new PathfinderGoalRandomFly((EntityCreature)PathfinderHolder.getNMSEntity(this.entity), 1.0D);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\ai\goals\RandomFlyGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */