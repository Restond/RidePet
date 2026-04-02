/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleAtomEffect;
/*     */ import io.lumine.xikage.mythicmobs.util.RandomUtil;
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
/* 107 */   private Optional<AbstractEntity> entity = Optional.empty();
/* 108 */   private Optional<AbstractLocation> location = Optional.empty();
/* 109 */   private int iteration = 0;
/* 110 */   private int step = 0;
/*     */   private Scheduler.Task task;
/*     */   
/*     */   public Animator(SkillMetadata data, AbstractEntity entity) {
/* 114 */     this.data = data;
/* 115 */     this.entity = Optional.of(entity);
/* 116 */     this.task = Scheduler.runTaskRepeatingAsync(this, 0L, paramParticleAtomEffect.interval);
/*     */   }
/*     */   
/*     */   public Animator(SkillMetadata data, AbstractLocation location) {
/* 120 */     this.data = data;
/* 121 */     this.location = Optional.of(location);
/* 122 */     this.task = Scheduler.runTaskRepeatingAsync(this, 0L, paramParticleAtomEffect.interval);
/*     */   }
/*     */   
/*     */   public AbstractLocation getLocation() {
/* 126 */     if (this.entity.isPresent()) {
/* 127 */       return ((AbstractEntity)this.entity.get()).getLocation();
/*     */     }
/* 129 */     return this.location.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 135 */     AbstractLocation location = getLocation(); int i;
/* 136 */     for (i = 0; i < ParticleAtomEffect.this.amountN; i++) {
/* 137 */       AbstractVector v = RandomUtil.getRandomVector().multiply(ParticleAtomEffect.this.radius * ParticleAtomEffect.this.radiusN);
/* 138 */       location.add(v);
/* 139 */       ParticleAtomEffect.this.playNucleusParticleEffect(this.data, location);
/* 140 */       location.subtract(v);
/*     */     } 
/* 142 */     for (i = 0; i < ParticleAtomEffect.this.amountO; i++) {
/* 143 */       double angle = this.step * ParticleAtomEffect.this.angularVelocity;
/* 144 */       for (int j = 0; j < ParticleAtomEffect.this.orbitals; j++) {
/* 145 */         double xRotation = Math.PI / ParticleAtomEffect.this.orbitals * j;
/* 146 */         AbstractVector v = (new AbstractVector(Math.cos(angle), Math.sin(angle), 0.0D)).multiply(ParticleAtomEffect.this.radius);
/* 147 */         VectorUtils.rotateAroundAxisX(v, xRotation);
/* 148 */         VectorUtils.rotateAroundAxisY(v, ParticleAtomEffect.this.rotation);
/* 149 */         location.add(v);
/* 150 */         ParticleAtomEffect.this.playParticleEffect(this.data, location);
/* 151 */         location.subtract(v);
/*     */       } 
/* 153 */       this.step++;
/*     */     } 
/* 155 */     if (++this.iteration * ParticleAtomEffect.this.interval >= ParticleAtomEffect.this.iterations)
/* 156 */       this.task.terminate(); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleAtomEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */