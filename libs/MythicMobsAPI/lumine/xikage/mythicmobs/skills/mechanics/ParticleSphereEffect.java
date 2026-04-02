/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleEffect;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ import io.lumine.xikage.mythicmobs.util.RandomUtil;
/*    */ 
/*    */ public class ParticleSphereEffect extends ParticleEffect implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   private PlaceholderInt points;
/*    */   
/*    */   public ParticleSphereEffect(String skill, MythicLineConfig mlc) {
/* 21 */     super(skill, mlc);
/*    */     
/* 23 */     this.points = this.amount;
/* 24 */     this.amount = PlaceholderInt.of("1");
/* 25 */     this.radius = mlc.getFloat("radius", 0.0F);
/* 26 */     this.radius = mlc.getFloat("r", this.radius);
/*    */   }
/*    */   private float radius;
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 31 */     playParticleSphereEffect(data, target);
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 37 */     playParticleSphereEffect(data, target.getLocation());
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   protected void playParticleSphereEffect(SkillMetadata data, AbstractLocation t) {
/* 42 */     AbstractLocation target = t;
/*    */     
/* 44 */     AbstractLocation location = target.clone();
/* 45 */     if (this.setYaw) {
/* 46 */       location.setYaw(this.yaw);
/*    */     }
/* 48 */     if (this.setPitch) {
/* 49 */       location.setPitch(this.pitch);
/*    */     }
/* 51 */     location.add(0.0D, this.yOffset, 0.0D);
/*    */     
/* 53 */     int points = this.points.get((PlaceholderMeta)data);
/* 54 */     for (int i = 0; i < points; i++) {
/* 55 */       AbstractVector vector = RandomUtil.getRandomVector().multiply(this.radius);
/* 56 */       location.add(vector);
/* 57 */       if (this.directional) {
/* 58 */         playDirectionalParticleEffect(data, target, target, location);
/*    */       } else {
/* 60 */         playParticleEffect(data, location);
/*    */       } 
/* 62 */       location.subtract(vector);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void playDirectionalParticleEffect(SkillMetadata data, AbstractLocation origin, AbstractLocation target, AbstractLocation spawn) {
/*    */     AbstractVector direction;
/* 68 */     if (this.directionReversed) {
/* 69 */       direction = origin.toVector().subtract(target.clone().toVector()).normalize();
/*    */     } else {
/* 71 */       direction = target.toVector().subtract(origin.clone().toVector()).normalize();
/*    */     } 
/* 73 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 74 */       if (this.particleData == null) {
/* 75 */         this.particle.sendDirectional(origin, this.pSpeed, 1, this.hSpread, this.vSpread, this.hSpread, direction);
/*    */       
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 81 */       AbstractLocation ln = spawn.clone().add((0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D, this.vSpread + MythicMobs.r.nextDouble() * this.vSpread * 2.0D, (0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D);
/* 82 */       (new ParticleMaker.ParticlePacket(this.strParticle, direction, this.pSpeed, 1, true))
/* 83 */         .send(ln, this.viewDistance);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleSphereEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */