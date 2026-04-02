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
/*    */ @MythicAIGoal(name = "goToParent", aliases = {}, version = "4.8", description = "Path to the mob's parent")
/*    */ public class GoToParentGoal extends Pathfinder implements PathfindingGoal {
/*    */   private double followRangeSq;
/*    */   private double minRangeSq;
/*    */   private float speed;
/*    */   private boolean dropTarget;
/*    */   
/*    */   public GoToParentGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 20 */     super(entity, line, mlc);
/* 21 */     this.goalType = Pathfinder.GoalType.MOVE_LOOK;
/*    */     
/* 23 */     double def = 4.0D;
/*    */     try {
/* 25 */       def = Double.valueOf(this.dataVar1).doubleValue();
/* 26 */     } catch (Exception|Error exception) {}
/* 27 */     double followRange = mlc.getDouble(new String[] { "followrange", "fr", "r", "maxrange" }, def);
/* 28 */     this.followRangeSq = Math.pow(followRange, 2.0D);
/*    */     
/* 30 */     double minRange = mlc.getDouble(new String[] { "minrange", "mr" }, def);
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
/* 44 */     if (this.activeMob.getParent() == null || this.activeMob.getParent().getEntity().isDead() || !this.activeMob.getParent().getEntity().isValid()) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     AbstractLocation destination = this.activeMob.getParent().getLocation();
/*    */     
/* 50 */     if (this.entity.getLocation().distanceSquared(destination) > this.followRangeSq) {
/* 51 */       if (this.dropTarget) {
/* 52 */         ai().setTarget((LivingEntity)BukkitAdapter.adapt(this.entity), null);
/*    */       }
/* 54 */       return true;
/*    */     } 
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {}
/*    */ 
/*    */   
/*    */   public void tick() {
/* 64 */     AbstractLocation destination = this.activeMob.getParent().getLocation();
/* 65 */     double dist = this.entity.getLocation().distanceSquared(destination);
/*    */     
/* 67 */     if (dist > 4096.0D) {
/* 68 */       this.entity.teleport(destination);
/*    */     } else {
/* 70 */       ai().navigateToLocation(this.entity, destination, this.speed);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldEnd() {
/* 76 */     if (this.activeMob.getParent() == null || this.activeMob.getParent().getEntity().isDead() || !this.activeMob.getParent().getEntity().isValid()) {
/* 77 */       return true;
/*    */     }
/* 79 */     AbstractLocation destination = this.activeMob.getParent().getLocation();
/*    */     
/* 81 */     if (this.entity.getLocation().distanceSquared(destination) <= this.minRangeSq) {
/* 82 */       return true;
/*    */     }
/* 84 */     return false;
/*    */   }
/*    */   
/*    */   public void end() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ai\goals\GoToParentGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */