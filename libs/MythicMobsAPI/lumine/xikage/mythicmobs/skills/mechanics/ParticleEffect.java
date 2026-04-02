/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitParticle;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.awt.Color;
/*     */ 
/*     */ 
/*     */ public class ParticleEffect
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*     */   protected String strParticle;
/*     */   protected BukkitParticle particle;
/*  26 */   protected Object particleData = null;
/*     */   
/*     */   protected float hSpread;
/*     */   
/*     */   protected float vSpread;
/*     */   
/*     */   protected float pSpeed;
/*     */   
/*     */   protected float yOffset;
/*     */   
/*     */   protected float startForwardOffset;
/*     */   
/*     */   protected float startSideOffset;
/*     */   
/*     */   protected boolean useEyeLocation;
/*  41 */   protected Color color = null; protected PlaceholderInt amount; protected int viewDistance; protected boolean directional; protected boolean directionReversed; protected float yaw; protected float pitch; protected boolean setYaw = false;
/*     */   protected boolean setPitch = false;
/*     */   boolean fromOrigin;
/*     */   
/*     */   public ParticleEffect(String skill, MythicLineConfig mlc) {
/*  46 */     super(skill, mlc);
/*     */     
/*  48 */     this.strParticle = mlc.getString("particle", "reddust");
/*  49 */     this.strParticle = mlc.getString("p", this.strParticle);
/*     */     
/*  51 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*  52 */       this.particle = BukkitParticle.get(this.strParticle);
/*     */       
/*  54 */       if (this.particle.requiresData()) {
/*  55 */         this.particleData = this.particle.parseDataOptions(mlc);
/*     */       }
/*     */     } 
/*     */     
/*  59 */     this.amount = mlc.getPlaceholderInteger(new String[] { "amount", "a" }, 10, new String[0]);
/*     */     
/*  61 */     this.hSpread = mlc.getFloat("hspread", 0.0F);
/*  62 */     this.hSpread = mlc.getFloat("hs", this.hSpread);
/*     */     
/*  64 */     this.vSpread = mlc.getFloat("vspread", 0.0F);
/*  65 */     this.vSpread = mlc.getFloat("vs", this.vSpread);
/*     */     
/*  67 */     this.pSpeed = mlc.getFloat("speed", 0.0F);
/*  68 */     this.pSpeed = mlc.getFloat("s", this.pSpeed);
/*     */     
/*  70 */     this.yOffset = mlc.getFloat(new String[] { "yoffset", "y" }, 0.0F);
/*  71 */     this.startForwardOffset = mlc.getFloat(new String[] { "forwardoffset", "startfoffset", "sfo" }, 0.0F);
/*  72 */     this.startSideOffset = mlc.getFloat(new String[] { "sideoffset", "soffset", "sso" }, 0.0F);
/*     */     
/*  74 */     this.useEyeLocation = mlc.getBoolean("useeyelocation", false);
/*  75 */     this.useEyeLocation = mlc.getBoolean("uel", this.useEyeLocation);
/*     */     
/*  77 */     this.viewDistance = mlc.getInteger(new String[] { "viewdistance", "vd" }, 128);
/*     */ 
/*     */     
/*  80 */     this.fromOrigin = mlc.getBoolean(new String[] { "fromorigin", "fo" }, false);
/*  81 */     this.directional = mlc.getBoolean(new String[] { "directional", "d" }, false);
/*  82 */     this.directionReversed = mlc.getBoolean(new String[] { "directionreversed", "dr" }, false);
/*     */     
/*  84 */     this.yaw = mlc.getFloat(new String[] { "fixedyaw", "yaw" }, -1111.0F);
/*     */     
/*  86 */     if (this.yaw != -1111.0F) {
/*  87 */       this.setYaw = true;
/*     */     }
/*     */     
/*  90 */     this.pitch = mlc.getFloat(new String[] { "fixedpitch", "pitch" }, -1111.0F);
/*     */     
/*  92 */     if (this.pitch != -1111.0F) {
/*  93 */       this.setPitch = true;
/*     */     }
/*     */     
/*  96 */     String strColor = mlc.getString(new String[] { "color", "c" }, null, new String[0]);
/*  97 */     this.color = (strColor == null) ? null : Color.decode(strColor);
/*     */     
/*  99 */     this.startForwardOffset *= -1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 105 */     if (this.setYaw)
/*     */     {
/* 107 */       target.setYaw(this.yaw);
/*     */     }
/*     */     
/* 110 */     if (this.setPitch)
/*     */     {
/* 112 */       target.setPitch(this.pitch);
/*     */     }
/*     */     
/* 115 */     if (this.startForwardOffset != 0.0F) {
/* 116 */       target = MythicUtil.move(target, this.startForwardOffset, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 119 */     if (this.startSideOffset != 0.0F) {
/* 120 */       target = MythicUtil.move(target, 0.0D, 0.0D, this.startSideOffset);
/*     */     }
/*     */     
/* 123 */     if (this.directional) {
/* 124 */       playDirectionalParticleEffect(data, data.getOrigin(), target);
/*     */     } else {
/* 126 */       playParticleEffect(data, target);
/*     */     } 
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*     */     AbstractLocation startLocation;
/* 135 */     if (this.useEyeLocation == true) {
/* 136 */       startLocation = target.getEyeLocation();
/*     */     } else {
/* 138 */       startLocation = target.getLocation();
/*     */     } 
/*     */     
/* 141 */     castAtLocation(data, startLocation);
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playParticleEffect(SkillMetadata data, AbstractLocation target) {
/* 147 */     AbstractLocation ln = target.clone().add(0.0D, this.yOffset, 0.0D);
/*     */     
/* 149 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 150 */       if (this.particleData == null) {
/* 151 */         if (this.color != null) {
/* 152 */           this.particle.sendLegacyColored(ln, this.pSpeed, this.amount.get((PlaceholderMeta)data), this.hSpread, this.vSpread, this.hSpread, this.color);
/*     */         } else {
/* 154 */           this.particle.send(ln, this.pSpeed, this.amount.get((PlaceholderMeta)data), this.hSpread, this.vSpread, this.hSpread);
/*     */         } 
/*     */       } else {
/* 157 */         this.particle.send(ln, this.pSpeed, this.amount.get((PlaceholderMeta)data), this.hSpread, this.vSpread, this.hSpread, this.particleData);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 164 */       if (this.color != null) {
/* 165 */         playColoredParticleEffect(data, target);
/*     */         return;
/*     */       } 
/* 168 */       (new ParticleMaker.ParticlePacket(this.strParticle, this.hSpread, this.vSpread, this.hSpread, this.pSpeed, this.amount
/* 169 */           .get((PlaceholderMeta)data), true)).send(ln, this.viewDistance);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playColoredParticleEffect(SkillMetadata data, AbstractLocation target) {
/* 177 */     int amount = this.amount.get((PlaceholderMeta)data);
/* 178 */     for (int i = 0; i < amount; i++) {
/* 179 */       AbstractLocation ln = target.clone().add((0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D, (this.yOffset - this.vSpread) + MythicMobs.r.nextDouble() * this.vSpread * 2.0D, (0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D);
/* 180 */       (new ParticleMaker.ParticlePacket(this.strParticle, this.color, this.pSpeed, 1, true))
/* 181 */         .send(ln, this.viewDistance);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void playDirectionalParticleEffect(SkillMetadata data, AbstractLocation origin, AbstractLocation target) {
/*     */     AbstractVector direction;
/* 187 */     if (this.directionReversed) {
/* 188 */       direction = origin.toVector().subtract(target.clone().toVector()).normalize();
/*     */     } else {
/* 190 */       direction = target.toVector().subtract(origin.clone().toVector()).normalize();
/*     */     } 
/*     */     
/* 193 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 194 */       if (this.particleData == null) {
/* 195 */         this.particle.sendDirectional(origin, this.pSpeed, this.amount.get((PlaceholderMeta)data), this.hSpread, this.vSpread, this.hSpread, direction);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 201 */       int amount = this.amount.get((PlaceholderMeta)data);
/* 202 */       for (int i = 0; i < amount; i++) {
/* 203 */         AbstractLocation ln = target.clone().add((0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D, (this.yOffset - this.vSpread) + MythicMobs.r.nextDouble() * this.vSpread * 2.0D, (0.0F - this.hSpread) + MythicMobs.r.nextDouble() * this.hSpread * 2.0D);
/* 204 */         (new ParticleMaker.ParticlePacket(this.strParticle, direction, this.pSpeed, 1, true))
/* 205 */           .send(ln, this.viewDistance);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */