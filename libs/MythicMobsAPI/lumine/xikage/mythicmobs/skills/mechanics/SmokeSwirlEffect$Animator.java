/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.SmokeSwirlEffect;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.plugin.Plugin;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Animator
/*    */   implements Runnable
/*    */ {
/*    */   private Entity entity;
/*    */   private Location location;
/*    */   private int interval;
/*    */   private int duration;
/*    */   private int iteration;
/*    */   private int taskId;
/*    */   
/*    */   public Animator(SmokeSwirlEffect paramSmokeSwirlEffect, Location location, int interval, int duration) {
/* 51 */     this(interval, duration);
/* 52 */     this.location = location;
/*    */   }
/*    */   
/*    */   public Animator(SmokeSwirlEffect paramSmokeSwirlEffect, Entity entity, int interval, int duration) {
/* 56 */     this(interval, duration);
/* 57 */     this.entity = entity;
/*    */   }
/*    */   
/* 60 */   private int[] x = new int[] { 1, 1, 0, -1, -1, -1, 0, 1 };
/* 61 */   private int[] z = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };
/* 62 */   private int[] v = new int[] { 7, 6, 3, 0, 1, 2, 5, 8 };
/*    */ 
/*    */   
/*    */   protected Animator(int interval, int duration) {
/* 66 */     this.interval = interval;
/* 67 */     this.duration = duration;
/* 68 */     this.iteration = 0;
/* 69 */     this.taskId = Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin)MythicMobs.inst(), this, 0L, interval);
/*    */   }
/*    */   
/*    */   public void run() {
/* 73 */     if (this.iteration * this.interval > this.duration) {
/* 74 */       Bukkit.getScheduler().cancelTask(this.taskId);
/*    */     } else {
/* 76 */       Location loc; int i = this.iteration % 8;
/*    */       
/* 78 */       if (this.location != null) {
/* 79 */         loc = this.location;
/*    */       } else {
/* 81 */         loc = this.entity.getLocation();
/*    */       } 
/* 83 */       loc.getWorld().playEffect(loc.clone().add(this.x[i], 0.0D, this.z[i]), Effect.SMOKE, this.v[i]);
/* 84 */       this.iteration++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SmokeSwirlEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */