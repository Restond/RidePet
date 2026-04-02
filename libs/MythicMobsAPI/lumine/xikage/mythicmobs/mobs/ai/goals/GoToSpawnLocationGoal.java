/*    */ package lumine.xikage.mythicmobs.mobs.ai.goals;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfindingGoal;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ @MythicAIGoal(name = "goToSpawnLocation", aliases = {"goToSpawn"}, version = "4.8", description = "Path to the mob's spawn location")
/*    */ public class GoToSpawnLocationGoal extends Pathfinder implements PathfindingGoal {
/*    */   private double maxRangeSq;
/*    */   private double minRangeSq;
/*    */   private float speed;
/*    */   private boolean dropTarget;
/*    */   
/*    */   public GoToSpawnLocationGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 20 */     super(entity, line, mlc);
/* 21 */     this.goalType = Pathfinder.GoalType.MOVE_LOOK;
/*    */     
/* 23 */     double def = 4.0D;
/*    */     try {
/* 25 */       def = Double.valueOf(this.dataVar1).doubleValue();
/* 26 */     } catch (Exception|Error exception) {}
/* 27 */     double maxRange = mlc.getDouble(new String[] { "maxrange", "max", "r" }, def);
/* 28 */     this.maxRangeSq = Math.pow(maxRange, 2.0D);
/*    */     
/* 30 */     double minRange = mlc.getDouble(new String[] { "minrange", "min", "mr" }, def);
/* 31 */     this.minRangeSq = Math.pow(minRange, 2.0D);
/*    */     
/* 33 */     float defSpeed = 1.0F;
/*    */     try {
/* 35 */       defSpeed = Float.valueOf(this.dataVar2).floatValue();
/* 36 */     } catch (Exception|Error exception) {}
/* 37 */     this.speed = mlc.getFloat(new String[] { "speed", "s" }, defSpeed);
/*    */     
/* 39 */     this.dropTarget = mlc.getBoolean(new String[] { "droptarget", "dt" }, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStart() {
/* 44 */     AbstractLocation destination = this.activeMob.getSpawnLocation();
/*    */     
/* 46 */     if (destination != null && this.entity.getLocation().distanceSquared(destination) > this.maxRangeSq) {
/* 47 */       if (this.dropTarget) {
/* 48 */         ai().setTarget((LivingEntity)BukkitAdapter.adapt(this.entity), null);
/*    */       }
/* 50 */       return true;
/*    */     } 
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {}
/*    */ 
/*    */   
/*    */   public void tick() {
/* 60 */     ai().navigateToLocation(this.entity, this.activeMob.getSpawnLocation(), this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldEnd() {
/* 65 */     AbstractLocation destination = this.activeMob.getSpawnLocation();
/*    */     
/* 67 */     if (destination == null || this.entity.getLocation().distanceSquared(destination) <= this.minRangeSq) {
/* 68 */       return true;
/*    */     }
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public void end() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ai\goals\GoToSpawnLocationGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */