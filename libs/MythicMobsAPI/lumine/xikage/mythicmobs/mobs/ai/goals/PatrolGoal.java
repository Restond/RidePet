/*    */ package lumine.xikage.mythicmobs.mobs.ai.goals;
/*    */ 
/*    */ import io.lumine.utils.serialize.Locus;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ 
/*    */ @MythicAIGoal(name = "patrolRoute", aliases = {"patrol"}, version = "4.6", description = "Patrol along a set path of points")
/*    */ public class PatrolGoal
/*    */   extends Pathfinder
/*    */   implements PathfindingGoal {
/* 19 */   private int currentDestination = -1;
/* 20 */   private List<AbstractLocation> points = new ArrayList<>();
/*    */   private float speed;
/*    */   private double tolerance;
/*    */   
/*    */   public PatrolGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 25 */     super(entity, line, mlc);
/* 26 */     this.goalType = Pathfinder.GoalType.MOVE;
/*    */     
/*    */     try {
/* 29 */       List<Locus> defpoints = new ArrayList<>();
/* 30 */       if (this.dataVar1 != null) {
/* 31 */         String[] split2 = this.dataVar1.split(";");
/*    */         
/* 33 */         for (String s : split2) {
/* 34 */           String[] split3 = s.split(",");
/*    */           
/* 36 */           int x = Integer.valueOf(split3[0]).intValue();
/* 37 */           int y = Integer.valueOf(split3[1]).intValue();
/* 38 */           int z = Integer.valueOf(split3[2]).intValue();
/* 39 */           defpoints.add(Locus.of(x, y, z));
/*    */         } 
/*    */       } 
/*    */       
/* 43 */       for (Locus locus : mlc.getLocationList(new String[] { "points", "p" }, defpoints)) {
/* 44 */         this.points.add(new AbstractLocation(entity.getWorld(), locus.getX(), locus.getY(), locus.getZ()));
/*    */       }
/*    */       
/* 47 */       float defSpeed = 1.0F;
/*    */       try {
/* 49 */         defSpeed = Float.valueOf(this.dataVar2).floatValue();
/* 50 */       } catch (Exception|Error exception) {}
/* 51 */       this.speed = mlc.getFloat(new String[] { "speed", "s" }, defSpeed);
/*    */       
/* 53 */       this.tolerance = mlc.getDouble(new String[] { "tolerance", "t" }, 4.0D);
/*    */       
/* 55 */       this.currentDestination = getNearestLocation();
/* 56 */     } catch (Exception ex) {
/* 57 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getNearestLocation() {
/* 62 */     AbstractLocation entityLocation = this.entity.getLocation();
/* 63 */     AbstractLocation closest = this.points.stream().min(Comparator.comparing(l -> Double.valueOf(l.distanceSquared(paramAbstractLocation1)))).orElse(null);
/*    */     
/* 65 */     if (closest == null) {
/* 66 */       return -1;
/*    */     }
/* 68 */     MythicLogger.log("Closest is " + closest);
/* 69 */     return this.points.indexOf(closest);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStart() {
/* 74 */     if (this.points.size() < 2) return false;
/*    */     
/* 76 */     AbstractLocation currentTarget = this.points.get(this.currentDestination);
/*    */     
/* 78 */     if (this.entity.getLocation().distanceSquared(currentTarget) <= this.tolerance) {
/* 79 */       if (++this.currentDestination == this.points.size()) {
/* 80 */         this.currentDestination = 0;
/*    */       }
/* 82 */       currentTarget = this.points.get(this.currentDestination);
/*    */     } 
/* 84 */     ai().navigateToLocation(this.entity, currentTarget, this.speed);
/* 85 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {}
/*    */ 
/*    */   
/*    */   public void tick() {}
/*    */ 
/*    */   
/*    */   public boolean shouldEnd() {
/* 96 */     return true;
/*    */   }
/*    */   
/*    */   public void end() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ai\goals\PatrolGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */