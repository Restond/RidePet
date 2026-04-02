/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleEffect;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ 
/*    */ public class ParticleRingEffect
/*    */   extends ParticleEffect implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   float radius;
/*    */   
/*    */   public ParticleRingEffect(String skill, MythicLineConfig mlc) {
/* 21 */     super(skill, mlc);
/*    */     
/* 23 */     this.points = mlc.getInteger("points", 10);
/* 24 */     this.points = mlc.getInteger("pts", this.points);
/*    */     
/* 26 */     this.radius = mlc.getFloat("radius", 10.0F);
/* 27 */     this.radius = mlc.getFloat("r", this.radius);
/*    */     
/* 29 */     if (this.points <= 0) this.points = 1;
/*    */ 
/*    */     
/* 32 */     this.inc = 360 / this.points;
/*    */   }
/*    */   int points; int inc;
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 37 */     if (this.fromOrigin) {
/* 38 */       playParticleRingEffect(data, data.getOrigin());
/*    */     } else {
/* 40 */       playParticleRingEffect(data, target);
/*    */     } 
/*    */     
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 48 */     playParticleRingEffect(data, target.getLocation());
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void playParticleRingEffect(SkillMetadata data, AbstractLocation t) {
/* 54 */     AbstractLocation target = t;
/* 55 */     if (target == null)
/*    */       return; 
/* 57 */     double bx = target.getX();
/* 58 */     double y = target.getY() + this.yOffset;
/* 59 */     double bz = target.getZ();
/* 60 */     AbstractWorld w = target.getWorld();
/*    */     double i;
/* 62 */     for (i = 0.0D; i < 360.0D; i += this.inc) {
/* 63 */       double angle = i * Math.PI / 180.0D;
/* 64 */       double x = bx + this.radius * Math.cos(angle);
/* 65 */       double z = bz + this.radius * Math.sin(angle);
/*    */       
/* 67 */       AbstractLocation l = new AbstractLocation(w, x, y, z);
/* 68 */       if (this.directional) {
/* 69 */         playDirectionalParticleEffect(data, l, target, l);
/*    */       } else {
/* 71 */         playParticleEffect(data, l);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void playDirectionalParticleEffect(SkillMetadata data, AbstractLocation origin, AbstractLocation target, AbstractLocation spawn) {
/*    */     AbstractVector direction;
/* 78 */     if (this.directionReversed) {
/* 79 */       direction = origin.toVector().subtract(target.clone().toVector()).normalize();
/*    */     } else {
/* 81 */       direction = target.toVector().subtract(origin.clone().toVector()).normalize();
/*    */     } 
/* 83 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 84 */       if (this.particleData == null) {
/* 85 */         this.particle.sendDirectional(origin, this.pSpeed, this.amount.get((PlaceholderMeta)data), this.hSpread, this.vSpread, this.hSpread, direction);
/*    */       
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 91 */       int amount = this.amount.get((PlaceholderMeta)data) / this.points;
/* 92 */       for (int i = 0; i < amount; i++) {
/* 93 */         AbstractLocation ln = spawn.clone().add((0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D, this.vSpread + MythicMobs.r.nextDouble() * this.vSpread * 2.0D, (0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D);
/* 94 */         (new ParticleMaker.ParticlePacket(this.strParticle, direction, this.pSpeed, 1, true))
/* 95 */           .send(ln, this.viewDistance);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleRingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */