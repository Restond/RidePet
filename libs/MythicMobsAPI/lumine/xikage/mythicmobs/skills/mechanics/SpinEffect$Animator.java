/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.utils.tasks.Task;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.SpinEffect;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Animator
/*    */   implements Runnable
/*    */ {
/*    */   private AbstractEntity target;
/*    */   private float yaw;
/*    */   private int velocity;
/*    */   private int duration;
/*    */   private int iteration;
/*    */   private Task task;
/*    */   
/*    */   public Animator(SpinEffect paramSpinEffect, AbstractEntity target, int duration, int velocity) {
/* 44 */     this(duration, velocity);
/* 45 */     this.target = target;
/* 46 */     this.yaw = target.getLocation().getYaw();
/*    */   }
/*    */   
/*    */   protected Animator(int duration, int velocity) {
/* 50 */     this.velocity = velocity;
/* 51 */     this.duration = duration;
/* 52 */     this.iteration = 0;
/* 53 */     this.task = Schedulers.sync().runRepeating(this, 0L, 1L);
/*    */   }
/*    */   
/*    */   public void run() {
/* 57 */     if (this.iteration > this.duration) {
/* 58 */       this.task.terminate();
/*    */     } else {
/* 60 */       this.yaw = (this.yaw + this.velocity) % 360.0F;
/* 61 */       AbstractLocation l = this.target.getLocation();
/* 62 */       Vector v = this.target.getBukkitEntity().getVelocity();
/*    */       
/* 64 */       v.setY(v.getY() + 0.01D);
/* 65 */       l.setYaw(this.yaw);
/*    */       
/* 67 */       this.target.teleport(l);
/* 68 */       this.target.getBukkitEntity().setVelocity(v);
/* 69 */       this.iteration++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SpinEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */