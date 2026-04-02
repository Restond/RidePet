/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfinderAdapter;
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.server.v1_14_R1.PathfinderGoal;
/*    */ 
/*    */ public class CustomAIAdapter
/*    */   extends PathfinderGoal implements PathfinderAdapter {
/*    */   private final Pathfinder goal;
/*    */   
/*    */   public static PathfinderGoal create(Pathfinder goal) {
/* 13 */     return new io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.CustomAIAdapter(goal);
/*    */   }
/*    */   public Pathfinder getGoal() {
/* 16 */     return this.goal;
/*    */   }
/*    */   public CustomAIAdapter(Pathfinder goal) {
/* 19 */     this.goal = goal;
/*    */     
/* 21 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$mobs$ai$Pathfinder$GoalType[goal.getGoalType().ordinal()]) {
/*    */       case 1:
/* 23 */         a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */         break;
/*    */       case 2:
/* 26 */         a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*    */         break;
/*    */       case 3:
/* 29 */         a(EnumSet.of(PathfinderGoal.Type.TARGET));
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 38 */     return this.goal.isValid();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/*    */     try {
/* 44 */       return this.goal.shouldStart();
/* 45 */     } catch (Exception|Error e) {
/* 46 */       e.printStackTrace();
/*    */       
/* 48 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void c() {
/*    */     try {
/* 54 */       this.goal.start();
/* 55 */     } catch (Exception|Error e) {
/* 56 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/*    */     try {
/* 63 */       this.goal.tick();
/* 64 */     } catch (Exception|Error e) {
/* 65 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/*    */     try {
/* 72 */       return !this.goal.shouldEnd();
/* 73 */     } catch (Exception|Error e) {
/* 74 */       e.printStackTrace();
/*    */       
/* 76 */       return true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void d() {
/*    */     try {
/* 82 */       this.goal.end();
/* 83 */     } catch (Exception|Error e) {
/* 84 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\ai\CustomAIAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */