/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.EnderBeamMechanic;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.EnderCrystal;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Animator
/*     */   implements Runnable
/*     */ {
/*     */   private EnderCrystal crystal;
/*     */   private Location locationSource;
/*     */   private Entity entitySource;
/*     */   private Entity entity;
/*     */   private Location location;
/*  55 */   private int interval = 1;
/*     */   private int duration;
/*     */   private int iteration;
/*     */   private int taskId;
/*     */   
/*     */   public Animator(Entity source, Location location, int duration) {
/*  61 */     this.entitySource = source;
/*  62 */     this.location = location;
/*  63 */     start(duration);
/*     */   }
/*     */   
/*     */   public Animator(Entity source, Entity entity, int duration) {
/*  67 */     this.entitySource = source;
/*  68 */     this.entity = entity;
/*  69 */     start(duration);
/*     */   }
/*     */   
/*     */   public Animator(Location source, Location location, int duration) {
/*  73 */     this.locationSource = source;
/*  74 */     this.location = location;
/*  75 */     start(duration);
/*     */   }
/*     */   
/*     */   public Animator(Location source, Entity entity, int duration) {
/*  79 */     this.locationSource = source;
/*  80 */     this.entity = entity;
/*  81 */     start(duration);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void start(int duration) {
/*  86 */     this.crystal = (EnderCrystal)this.entitySource.getWorld().spawnEntity(this.entitySource.getLocation().add(0.0D, EnderBeamMechanic.this.yOffset, 0.0D), EntityType.ENDER_CRYSTAL);
/*  87 */     this.crystal.setShowingBottom(false);
/*  88 */     this.crystal.setInvulnerable(true);
/*     */     
/*  90 */     this.duration = duration;
/*  91 */     this.iteration = 0;
/*  92 */     this.taskId = Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin)MythicMobs.inst(), this, 0L, this.interval);
/*     */   }
/*     */   
/*     */   public void run() {
/*  96 */     if (this.iteration > this.duration) {
/*  97 */       this.crystal.remove();
/*  98 */       Bukkit.getScheduler().cancelTask(this.taskId);
/*     */     } else {
/*     */       Location target;
/*     */       
/* 102 */       if (this.locationSource != null) {
/* 103 */         source = this.locationSource;
/*     */       } else {
/* 105 */         source = this.entitySource.getLocation();
/*     */       } 
/* 107 */       Location source = source.add(0.0D, EnderBeamMechanic.this.yOffset, 0.0D);
/*     */ 
/*     */       
/* 110 */       if (this.location != null) {
/* 111 */         target = this.location;
/*     */       } else {
/* 113 */         target = this.entity.getLocation();
/*     */       } 
/*     */       
/* 116 */       this.crystal.teleport(source);
/* 117 */       this.crystal.setBeamTarget(target);
/*     */       
/* 119 */       this.iteration++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\EnderBeamMechanic$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */