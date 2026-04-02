/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.PathfinderHolder;
/*    */ import net.minecraft.server.v1_14_R1.EntityCreature;
/*    */ import net.minecraft.server.v1_14_R1.EntityInsentient;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoal;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStroll;
/*    */ 
/*    */ 
/*    */ @MythicAIGoal(name = "randomStroll", aliases = {}, description = "Walk around randomly")
/*    */ public class RandomStrollGoal
/*    */   extends WrappedPathfindingGoal
/*    */   implements PathfinderHolder
/*    */ {
/*    */   public RandomStrollGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 20 */     super(entity, line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 25 */     return this.entity.isCreature();
/*    */   }
/*    */ 
/*    */   
/*    */   public PathfinderGoal create() {
/* 30 */     EntityInsentient e = PathfinderHolder.getNMSEntity(this.entity);
/* 31 */     return (PathfinderGoal)new PathfinderGoalRandomStroll((EntityCreature)e, 1.0D);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\ai\goals\RandomStrollGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */