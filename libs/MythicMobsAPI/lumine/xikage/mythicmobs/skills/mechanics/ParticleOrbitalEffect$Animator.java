/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleOrbitalEffect;
/*     */ import io.lumine.xikage.mythicmobs.util.VectorUtils;
/*     */ import java.util.Optional;
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
/*     */   private SkillMetadata data;
/*  88 */   private Optional<AbstractEntity> entity = Optional.empty();
/*  89 */   private Optional<AbstractLocation> location = Optional.empty();
/*  90 */   private int iteration = 0;
/*  91 */   private int step = 0;
/*     */   private Scheduler.Task task;
/*     */   
/*     */   public Animator(SkillMetadata data, AbstractEntity entity) {
/*  95 */     this.data = data;
/*  96 */     this.entity = Optional.of(entity);
/*  97 */     this.task = Scheduler.runTaskRepeatingAsync(this, 0L, paramParticleOrbitalEffect.interval);
/*     */   }
/*     */   
/*     */   public Animator(SkillMetadata data, AbstractLocation location) {
/* 101 */     this.data = data;
/* 102 */     this.location = Optional.of(location);
/* 103 */     this.task = Scheduler.runTaskRepeatingAsync(this, 0L, paramParticleOrbitalEffect.interval);
/*     */   }
/*     */   
/*     */   public AbstractLocation getLocation() {
/* 107 */     if (this.entity.isPresent()) {
/* 108 */       return ((AbstractEntity)this.entity.get()).getLocation().add(ParticleOrbitalEffect.this.xOffset, ParticleOrbitalEffect.this.yOffset, ParticleOrbitalEffect.this.zOffset);
/*     */     }
/* 110 */     return ((AbstractLocation)this.location.get()).clone().add(ParticleOrbitalEffect.this.xOffset, ParticleOrbitalEffect.this.yOffset, ParticleOrbitalEffect.this.zOffset);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 116 */     AbstractLocation location = getLocation();
/* 117 */     double inc = 6.283185307179586D / ParticleOrbitalEffect.this.points;
/* 118 */     double angle = this.step * inc;
/* 119 */     AbstractVector v = new AbstractVector();
/* 120 */     v.setX(Math.cos(angle) * ParticleOrbitalEffect.this.radius);
/* 121 */     v.setZ(Math.sin(angle) * ParticleOrbitalEffect.this.radius);
/* 122 */     VectorUtils.rotateVector(v, ParticleOrbitalEffect.this.xRotation, ParticleOrbitalEffect.this.yRotation, ParticleOrbitalEffect.this.zRotation);
/*     */     
/* 124 */     if (ParticleOrbitalEffect.this.rotate) {
/* 125 */       VectorUtils.rotateVector(v, ParticleOrbitalEffect.this.angularVelocityX * this.step, ParticleOrbitalEffect.this.angularVelocityY * this.step, ParticleOrbitalEffect.this.angularVelocityZ * this.step);
/*     */     }
/* 127 */     AbstractLocation loc = ParticleOrbitalEffect.this.reversed ? location.subtract(v) : location.add(v);
/* 128 */     ParticleOrbitalEffect.this.playParticleEffect(this.data, loc);
/*     */     
/* 130 */     this.step++;
/*     */     
/* 132 */     if (++this.iteration * ParticleOrbitalEffect.this.interval >= ParticleOrbitalEffect.this.iterations)
/* 133 */       this.task.terminate(); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleOrbitalEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */