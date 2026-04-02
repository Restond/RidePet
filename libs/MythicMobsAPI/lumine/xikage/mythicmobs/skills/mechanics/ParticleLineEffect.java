/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleEffect;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ 
/*     */ public class ParticleLineEffect extends ParticleEffect implements ITargetedEntitySkill, ITargetedLocationSkill {
/*     */   protected float distanceBetween;
/*     */   protected float startYOffset;
/*     */   
/*     */   public ParticleLineEffect(String skill, MythicLineConfig mlc) {
/*  22 */     super(skill, mlc);
/*     */     
/*  24 */     this.startYOffset = mlc.getFloat(new String[] { "startyoffset", "syo", "ystartoffset", "ys" }, 0.0F);
/*  25 */     this.targetYOffset = mlc.getFloat(new String[] { "targetyoffset", "tyo", "ytargetoffset", "yt" }, 0.0F);
/*     */     
/*  27 */     this.distanceBetween = mlc.getFloat("distancebetween", 0.25F);
/*  28 */     this.distanceBetween = mlc.getFloat("db", this.distanceBetween);
/*     */     
/*  30 */     this.fromOrigin = mlc.getBoolean(new String[] { "fromorigin", "fo" }, false);
/*     */   }
/*     */   protected float targetYOffset; boolean fromOrigin;
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  35 */     playParticleLineEffect(data, data.getCaster(), data.getOrigin(), target);
/*  36 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  41 */     playParticleLineEffect(data, data.getCaster(), data.getOrigin(), target.getLocation());
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playParticleLineEffect(SkillMetadata data, SkillCaster am, AbstractLocation origin, AbstractLocation target) {
/*  49 */     AbstractLocation startLocation, targetLocation = target.clone().add(0.0D, this.targetYOffset, 0.0D);
/*     */     
/*  51 */     if (this.fromOrigin == true) {
/*  52 */       startLocation = origin.add(0.0D, this.startYOffset, 0.0D);
/*  53 */     } else if (this.useEyeLocation) {
/*  54 */       startLocation = am.getEntity().getEyeLocation();
/*     */     } else {
/*  56 */       startLocation = am.getEntity().getLocation().add(0.0D, this.startYOffset, 0.0D);
/*     */     } 
/*  58 */     if (this.setYaw) {
/*  59 */       startLocation.setYaw(this.yaw);
/*     */     }
/*  61 */     if (this.setPitch) {
/*  62 */       startLocation.setPitch(this.pitch);
/*     */     }
/*  64 */     if (this.startForwardOffset != 0.0F) {
/*  65 */       startLocation = MythicUtil.move(startLocation, this.startForwardOffset, 0.0D, 0.0D);
/*     */     }
/*  67 */     if (this.startSideOffset != 0.0F) {
/*  68 */       startLocation = MythicUtil.move(startLocation, 0.0D, 0.0D, this.startSideOffset);
/*     */     }
/*     */     
/*  71 */     int c = (int)Math.ceil(startLocation.distance(targetLocation) / this.distanceBetween) - 1;
/*  72 */     if (c <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  77 */     AbstractVector v = targetLocation.toVector().subtract(startLocation.toVector()).normalize().multiply(this.distanceBetween);
/*  78 */     AbstractLocation l2 = startLocation.clone().add(0.0D, this.yOffset, 0.0D);
/*     */ 
/*     */ 
/*     */     
/*  82 */     for (int i = 0; i < c; i++) {
/*  83 */       l2.add(v);
/*     */       
/*  85 */       if (this.directional) {
/*  86 */         playDirectionalParticleEffect(data, startLocation, targetLocation, l2);
/*     */       } else {
/*  88 */         playParticleEffect(data, l2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void playDirectionalParticleEffect(SkillMetadata data, AbstractLocation origin, AbstractLocation target, AbstractLocation spawn) {
/*     */     AbstractVector direction;
/*  95 */     if (this.directionReversed) {
/*  96 */       direction = origin.toVector().subtract(target.clone().toVector()).normalize();
/*     */     } else {
/*  98 */       direction = target.toVector().subtract(origin.clone().toVector()).normalize();
/*     */     } 
/* 100 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 101 */       if (this.particleData == null) {
/* 102 */         this.particle.sendDirectional(origin, this.pSpeed, this.amount.get((PlaceholderMeta)data), this.hSpread, this.vSpread, this.hSpread, direction);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 108 */       int amount = this.amount.get((PlaceholderMeta)data);
/* 109 */       for (int i = 0; i < amount; i++) {
/* 110 */         AbstractLocation ln = spawn.clone().add((0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D, this.vSpread + MythicMobs.r.nextDouble() * this.vSpread * 2.0D, (0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D);
/* 111 */         (new ParticleMaker.ParticlePacket(this.strParticle, direction, this.pSpeed, 1, true)).send(ln, this.viewDistance);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleLineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */