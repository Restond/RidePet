/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleTornadoEffect;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.util.RandomUtil;
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
/*     */   private AbstractEntity entity;
/*     */   private AbstractLocation location;
/*     */   private int interval;
/*     */   private int duration;
/*     */   private int iteration;
/*     */   private Task task;
/*     */   
/*     */   public Animator(ParticleTornadoEffect paramParticleTornadoEffect, SkillMetadata data, AbstractLocation location, int interval, int duration) {
/* 115 */     this(interval, duration);
/* 116 */     this.data = data;
/* 117 */     this.entity = null;
/* 118 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Animator(ParticleTornadoEffect paramParticleTornadoEffect, SkillMetadata data, AbstractEntity entity, int interval, int duration) {
/* 122 */     this(interval, duration);
/* 123 */     this.data = data;
/* 124 */     this.entity = entity;
/*     */   }
/*     */   
/*     */   protected Animator(int interval, int duration) {
/* 128 */     this.interval = interval;
/* 129 */     this.duration = duration;
/* 130 */     this.iteration = 0;
/* 131 */     this.task = Schedulers.async().runRepeating(this, 0L, interval);
/* 132 */     MythicMobs.debug(3, "# Started ParticleTornado effect");
/*     */   }
/*     */   
/*     */   protected AbstractLocation getLocation() {
/* 136 */     if (this.entity != null) {
/* 137 */       return this.entity.getLocation();
/*     */     }
/* 139 */     return this.location.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 144 */     if (this.iteration * this.interval > this.duration) {
/* 145 */       this.task.terminate();
/*     */     } else {
/* 147 */       this.iteration++;
/* 148 */       AbstractLocation l = getLocation().add(0.0D, ParticleTornadoEffect.this.cloudYOffset, 0.0D);
/* 149 */       for (int i = 0; i < ParticleTornadoEffect.this.cloudSize; i++) {
/* 150 */         AbstractVector v = RandomUtil.getRandomCircleVector().multiply(RandomUtil.random.nextDouble() * ParticleTornadoEffect.this.cloudSize);
/* 151 */         if (ParticleTornadoEffect.this.showCloud) {
/*     */           
/* 153 */           l.add(v);
/*     */           
/* 155 */           if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*     */             
/* 157 */             if (ParticleTornadoEffect.this.particleData == null) {
/* 158 */               if (ParticleTornadoEffect.this.color != null) {
/* 159 */                 ParticleTornadoEffect.this.cloudParticle.sendLegacyColored(l, ParticleTornadoEffect.this.cloudPSpeed, ParticleTornadoEffect.this.cloudAmount, ParticleTornadoEffect.this.cloudHSpread, ParticleTornadoEffect.this.cloudVSpread, ParticleTornadoEffect.this.cloudHSpread, ParticleTornadoEffect.this.color);
/*     */               } else {
/* 161 */                 ParticleTornadoEffect.this.cloudParticle.send(l, ParticleTornadoEffect.this.cloudPSpeed, ParticleTornadoEffect.this.cloudAmount, ParticleTornadoEffect.this.cloudHSpread, ParticleTornadoEffect.this.cloudVSpread, ParticleTornadoEffect.this.cloudHSpread);
/*     */               } 
/*     */             } else {
/* 164 */               ParticleTornadoEffect.this.cloudParticle.send(l, ParticleTornadoEffect.this.cloudPSpeed, ParticleTornadoEffect.this.cloudAmount, ParticleTornadoEffect.this.cloudHSpread, ParticleTornadoEffect.this.cloudVSpread, ParticleTornadoEffect.this.cloudHSpread, ParticleTornadoEffect.this.cloudParticleData);
/*     */             } 
/*     */           } else {
/*     */             
/* 168 */             (new ParticleMaker.ParticlePacket(ParticleTornadoEffect.this.strCloudParticle, ParticleTornadoEffect.this.cloudHSpread, ParticleTornadoEffect.this.cloudVSpread, ParticleTornadoEffect.this.cloudHSpread, ParticleTornadoEffect.this.cloudPSpeed, ParticleTornadoEffect.this.cloudAmount, true))
/* 169 */               .send(l, ParticleTornadoEffect.this.viewDistance);
/*     */           } 
/* 171 */           l.subtract(v);
/*     */         } 
/*     */       } 
/* 174 */       AbstractLocation t = l.clone().add(0.0D, 0.2D + ParticleTornadoEffect.this.yOffset, 0.0D);
/* 175 */       double r = 0.45D * ParticleTornadoEffect.this.maxTornadoRadius * 2.35D / ParticleTornadoEffect.this.tornadoHeight; double y;
/* 176 */       for (y = 0.0D; y < ParticleTornadoEffect.this.tornadoHeight; y += ParticleTornadoEffect.this.distance) {
/* 177 */         double fr = r * y;
/* 178 */         if (fr > ParticleTornadoEffect.this.maxTornadoRadius)
/* 179 */           fr = ParticleTornadoEffect.this.maxTornadoRadius; 
/* 180 */         for (AbstractVector v : ParticleTornadoEffect.this.createCircle(y, fr, this.iteration * this.interval * ParticleTornadoEffect.access$000(ParticleTornadoEffect.this))) {
/* 181 */           if (ParticleTornadoEffect.this.showTornado) {
/*     */             
/* 183 */             l.add(v);
/*     */             
/* 185 */             if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*     */               
/* 187 */               if (ParticleTornadoEffect.this.particleData == null) {
/* 188 */                 if (ParticleTornadoEffect.this.color != null) {
/* 189 */                   ParticleTornadoEffect.this.particle.sendLegacyColored(l, ParticleTornadoEffect.this.pSpeed, ParticleTornadoEffect.this.amount.get((PlaceholderMeta)this.data), ParticleTornadoEffect.this.hSpread, ParticleTornadoEffect.this.vSpread, ParticleTornadoEffect.this.hSpread, ParticleTornadoEffect.this.color);
/*     */                 } else {
/* 191 */                   ParticleTornadoEffect.this.particle.send(l, ParticleTornadoEffect.this.pSpeed, ParticleTornadoEffect.this.amount.get((PlaceholderMeta)this.data), ParticleTornadoEffect.this.hSpread, ParticleTornadoEffect.this.vSpread, ParticleTornadoEffect.this.hSpread);
/*     */                 } 
/*     */               } else {
/* 194 */                 ParticleTornadoEffect.this.particle.send(l, ParticleTornadoEffect.this.pSpeed, ParticleTornadoEffect.this.amount.get((PlaceholderMeta)this.data), ParticleTornadoEffect.this.hSpread, ParticleTornadoEffect.this.vSpread, ParticleTornadoEffect.this.hSpread, ParticleTornadoEffect.this.particleData);
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 201 */               (new ParticleMaker.ParticlePacket(ParticleTornadoEffect.this.strParticle, ParticleTornadoEffect.this.hSpread, ParticleTornadoEffect.this.vSpread, ParticleTornadoEffect.this.hSpread, (float)(ParticleTornadoEffect.this.pSpeed + ParticleTornadoEffect.this.particleSpeedRamp * y), ParticleTornadoEffect.this.amount
/* 202 */                   .get((PlaceholderMeta)this.data), true)).send(l, ParticleTornadoEffect.this.viewDistance);
/*     */             } 
/* 204 */             t.subtract(v);
/*     */           } 
/*     */         } 
/*     */       } 
/* 208 */       l.subtract(0.0D, ParticleTornadoEffect.this.yOffset, 0.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleTornadoEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */