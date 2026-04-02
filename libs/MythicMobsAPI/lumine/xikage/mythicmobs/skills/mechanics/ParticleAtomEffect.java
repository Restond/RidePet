/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitParticle;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleAtomEffect
/*     */   extends ParticleEffect
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*     */   protected String particleNucleus;
/*     */   protected String particleOrbital;
/*     */   protected BukkitParticle pNucleus;
/*  31 */   protected Object pNucleusData = null; protected float radiusN;
/*     */   protected int radius;
/*     */   protected int amountN;
/*     */   protected int amountO;
/*  35 */   protected double velocity = 1.0D; protected int orbitals;
/*     */   protected int rotation;
/*  37 */   protected double angularVelocity = 0.039269908169872414D; protected int interval; protected int iterations;
/*     */   
/*     */   public ParticleAtomEffect(String skill, MythicLineConfig mlc) {
/*  40 */     super(skill, mlc);
/*     */     
/*  42 */     this.particleOrbital = mlc.getString("particleorbital", this.strParticle);
/*  43 */     this.particleOrbital = mlc.getString("particleo", this.particleOrbital);
/*  44 */     this.particleOrbital = mlc.getString("po", this.particleOrbital);
/*     */     
/*  46 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*  47 */       this.particle = BukkitParticle.get(this.particleOrbital);
/*     */       
/*  49 */       if (this.particle.requiresData()) {
/*  50 */         this.particleData = this.particle.parseDataOptions(mlc);
/*     */       }
/*     */     } 
/*     */     
/*  54 */     this.particleNucleus = mlc.getString("particlenucleus", "reddust");
/*  55 */     this.particleNucleus = mlc.getString("particlen", this.particleNucleus);
/*  56 */     this.particleNucleus = mlc.getString("pn", this.particleNucleus);
/*     */     
/*  58 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*  59 */       this.pNucleus = BukkitParticle.get(this.particleNucleus);
/*     */       
/*  61 */       if (this.pNucleus.requiresData()) {
/*  62 */         this.pNucleusData = this.pNucleus.parseDataOptions(mlc);
/*     */       }
/*     */     } 
/*     */     
/*  66 */     this.amountN = mlc.getInteger("amountnucleus", 50);
/*  67 */     this.amountN = mlc.getInteger("amountn", this.amountN);
/*  68 */     this.amountN = mlc.getInteger("apn", this.amountN);
/*  69 */     this.amountN = mlc.getInteger("an", this.amountN);
/*     */     
/*  71 */     this.amount = mlc.getPlaceholderInteger(new String[] { "amountorbital", "amounto", "apo", "ao" }, 1, new String[0]);
/*     */     
/*  73 */     this.orbitals = mlc.getInteger("orbitals", 2);
/*  74 */     this.orbitals = mlc.getInteger("o", this.orbitals);
/*     */     
/*  76 */     this.radius = mlc.getInteger("radius", 4);
/*  77 */     this.radius = mlc.getInteger("r", this.radius);
/*     */     
/*  79 */     this.radiusN = mlc.getFloat("radiusnucleus", 1.0F);
/*  80 */     this.radiusN = mlc.getFloat("rn", this.radiusN);
/*     */     
/*  82 */     this.rotation = mlc.getInteger("rotation", 0);
/*  83 */     this.rotation = mlc.getInteger("ro", this.rotation);
/*     */     
/*  85 */     this.iterations = mlc.getInteger("ticks", 1);
/*  86 */     this.iterations = mlc.getInteger("t", this.iterations);
/*     */     
/*  88 */     this.interval = mlc.getInteger("interval", 10);
/*  89 */     this.interval = mlc.getInteger("in", this.interval);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  94 */     new Animator(this, data, target);
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 100 */     new Animator(this, data, target);
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playNucleusParticleEffect(SkillMetadata data, AbstractLocation target) {
/* 162 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*     */       
/* 164 */       if (this.pNucleusData == null) {
/* 165 */         if (this.color != null) {
/* 166 */           this.pNucleus.sendLegacyColored(target, this.pSpeed, this.amountN, this.hSpread, this.vSpread, this.hSpread, this.color);
/*     */         } else {
/* 168 */           this.pNucleus.send(target, this.pSpeed, this.amountN, this.hSpread, this.vSpread, this.hSpread);
/*     */         } 
/*     */       } else {
/* 171 */         this.pNucleus.send(target, this.pSpeed, this.amountN, this.hSpread, this.vSpread, this.hSpread, this.pNucleusData);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 178 */       if (this.color != null) {
/* 179 */         for (int i = 0; i < this.amountN; i++) {
/* 180 */           AbstractLocation ln = target.clone().add((0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D, (this.yOffset - this.vSpread) + MythicMobs.r.nextDouble() * this.vSpread * 2.0D, (0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D);
/* 181 */           (new ParticleMaker.ParticlePacket(this.particleNucleus, this.color, this.pSpeed, 1, true)).send(ln, this.viewDistance);
/*     */         } 
/*     */         return;
/*     */       } 
/* 185 */       (new ParticleMaker.ParticlePacket(this.particleNucleus, this.hSpread, this.vSpread, this.hSpread, this.pSpeed, this.amountN, true)).send(target, this.viewDistance);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleAtomEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */