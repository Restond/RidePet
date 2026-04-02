/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleWaveEffect;
/*     */ import java.util.HashSet;
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
/*     */   private AbstractEntity entity;
/*     */   private AbstractLocation location;
/*     */   private int interval;
/*     */   private int duration;
/*     */   private int iteration;
/*     */   private Task task;
/*  80 */   HashSet<AbstractVector> waterCache = new HashSet<>();
/*  81 */   HashSet<AbstractVector> cloudCache = new HashSet<>();
/*     */   
/*     */   protected boolean firstStep = true;
/*  84 */   public AbstractVector velocity = new AbstractVector();
/*     */   
/*     */   public Animator(ParticleWaveEffect paramParticleWaveEffect, AbstractLocation location, int interval, int duration) {
/*  87 */     this(interval, duration);
/*  88 */     this.entity = null;
/*  89 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Animator(ParticleWaveEffect paramParticleWaveEffect, AbstractEntity entity, int interval, int duration) {
/*  93 */     this(interval, duration);
/*  94 */     this.entity = entity;
/*     */   }
/*     */   
/*     */   protected Animator(int interval, int duration) {
/*  98 */     this.interval = interval;
/*  99 */     this.duration = duration;
/* 100 */     this.iteration = 0;
/* 101 */     this.task = Schedulers.async().runRepeating(this, 0L, interval);
/* 102 */     MythicMobs.debug(3, "# Started ParticleWave effect");
/*     */   }
/*     */   
/*     */   protected AbstractLocation getLocation() {
/* 106 */     if (this.entity != null) {
/* 107 */       return this.entity.getLocation();
/*     */     }
/* 109 */     return this.location.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setup(AbstractLocation location) {}
/*     */ 
/*     */   
/*     */   public void run() {
/* 118 */     AbstractLocation location = getLocation();
/* 119 */     if (this.firstStep) {
/* 120 */       this.velocity.copy(location.getDirection().setY(0).normalize().multiply(0.2D));
/* 121 */       setup(location);
/*     */     } 
/* 123 */     location.add(this.velocity);
/*     */     
/* 125 */     for (AbstractVector v : this.cloudCache) {
/* 126 */       location.add(v);
/*     */       
/* 128 */       location.subtract(v);
/*     */     } 
/* 130 */     for (AbstractVector v : this.waterCache) {
/* 131 */       location.add(v);
/*     */       
/* 133 */       location.subtract(v);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleWaveEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */