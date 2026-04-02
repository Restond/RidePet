/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.PathfinderHolder;
/*    */ import net.minecraft.server.v1_14_R1.EntityInsentient;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoal;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicAIGoal(name = "randomLookAround", aliases = {"lookAround"}, description = "Randomly look around")
/*    */ public class RandomLookAroundGoal
/*    */   extends WrappedPathfindingGoal
/*    */   implements PathfinderHolder
/*    */ {
/*    */   public RandomLookAroundGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 21 */     super(entity, line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 26 */     return this.entity.isCreature();
/*    */   }
/*    */ 
/*    */   
/*    */   public PathfinderGoal create() {
/* 31 */     EntityInsentient e = PathfinderHolder.getNMSEntity(this.entity);
/* 32 */     return (PathfinderGoal)new PathfinderGoalRandomLookaround(e);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\ai\goals\RandomLookAroundGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */