/*    */ package lumine.xikage.mythicmobs.mobs.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ 
/*    */ @MythicAIGoal(name = "goToLocation", aliases = {"goto"}, version = "4.8", description = "Go to a location")
/*    */ public class GoToLocationGoal
/*    */   extends Pathfinder
/*    */   implements PathfindingGoal {
/*    */   private double x;
/*    */   private double y;
/* 16 */   private float distanceSq = 4.0F; private double z; private float speed; private AbstractLocation destination;
/*    */   
/*    */   public GoToLocationGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 19 */     super(entity, line, mlc);
/* 20 */     this.goalType = Pathfinder.GoalType.MOVE;
/*    */     
/*    */     try {
/* 23 */       if (this.dataVar1 != null) {
/* 24 */         String[] sp = this.dataVar1.split(",");
/* 25 */         this.x = Integer.valueOf(sp[0]).intValue();
/* 26 */         this.y = Integer.valueOf(sp[1]).intValue();
/* 27 */         this.z = Integer.valueOf(sp[2]).intValue();
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 32 */       float speed = 1.0F;
/* 33 */       if (this.dataVar2 != null) {
/* 34 */         speed = Float.valueOf(this.dataVar2).floatValue();
/*    */       }
/* 36 */     } catch (Exception ex) {
/* 37 */       ex.printStackTrace();
/*    */     } 
/* 39 */     this.destination = new AbstractLocation(entity.getWorld(), this.x, this.y, this.z);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStart() {
/* 44 */     return (this.entity.getLocation().distanceSquared(this.destination) > this.distanceSq);
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 49 */     ai().navigateToLocation(this.entity, this.destination, this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 54 */     ai().navigateToLocation(this.entity, this.destination, this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldEnd() {
/* 59 */     return (this.entity.getLocation().distanceSquared(this.destination) <= this.distanceSq);
/*    */   }
/*    */   
/*    */   public void end() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ai\goals\GoToLocationGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */