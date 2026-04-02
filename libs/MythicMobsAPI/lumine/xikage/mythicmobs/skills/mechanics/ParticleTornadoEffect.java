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
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleTornadoEffect
/*     */   extends ParticleEffect
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*     */   float cloudHSpread;
/*     */   float cloudVSpread;
/*     */   float cloudPSpeed;
/*     */   float cloudYOffset;
/*  25 */   public String strCloudParticle = "cloud";
/*     */   protected BukkitParticle cloudParticle;
/*  27 */   protected Object cloudParticleData = null;
/*     */   
/*  29 */   public float cloudSize = 2.5F;
/*  30 */   public float particleSpeedRamp = 0.0F;
/*  31 */   public int cloudAmount = 1;
/*  32 */   public double yOffset = 0.8D;
/*  33 */   public float tornadoHeight = 5.0F;
/*  34 */   public float maxTornadoRadius = 5.0F;
/*     */   
/*     */   private int sliceHeight;
/*     */   
/*     */   private int interval;
/*     */   
/*     */   private int duration;
/*     */   private float rotationSpeed;
/*     */   public boolean showCloud = true;
/*     */   public boolean showTornado = true;
/*  44 */   public double distance = 0.375D;
/*     */   
/*     */   public ParticleTornadoEffect(String skill, MythicLineConfig mlc) {
/*  47 */     super(skill, mlc);
/*     */     
/*  49 */     this.maxTornadoRadius = mlc.getFloat(new String[] { "maxradius", "mr" }, 3.0F);
/*  50 */     this.yOffset = mlc.getFloat(new String[] { "yoffset", "yo" }, 0.8F);
/*  51 */     this.tornadoHeight = mlc.getFloat(new String[] { "height", "h" }, 4.0F);
/*  52 */     this.interval = mlc.getInteger(new String[] { "interval", "i" }, 4);
/*  53 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 200);
/*  54 */     this.rotationSpeed = mlc.getFloat(new String[] { "rotationspeed", "rs" }, 0.04F);
/*  55 */     this.sliceHeight = mlc.getInteger(new String[] { "sliceheight", "sh" }, 64);
/*  56 */     this.particleSpeedRamp = mlc.getFloat(new String[] { "particlespeedramp", "psr" }, 0.0F);
/*     */     
/*  58 */     this.strCloudParticle = mlc.getString(new String[] { "cloudparticle", "cp" }, "largeexplode", new String[0]);
/*     */     
/*  60 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*  61 */       this.cloudParticle = BukkitParticle.get(this.strCloudParticle);
/*     */       
/*  63 */       if (this.cloudParticle.requiresData()) {
/*  64 */         this.cloudParticleData = this.cloudParticle.parseDataOptions(mlc);
/*     */       }
/*     */     } 
/*     */     
/*  68 */     this.cloudSize = mlc.getFloat(new String[] { "cloudsize", "cs" }, 5.0F);
/*  69 */     this.cloudAmount = mlc.getInteger(new String[] { "cloudamount", "ca" }, 1);
/*  70 */     this.cloudHSpread = mlc.getFloat(new String[] { "cloudhspread", "chs" }, 1.0F);
/*  71 */     this.cloudVSpread = mlc.getFloat(new String[] { "cloudvspread", "cvs" }, 1.8F);
/*  72 */     this.cloudPSpeed = mlc.getFloat(new String[] { "cloudpspeed", "cps", "cs" }, 2.0F);
/*  73 */     this.cloudYOffset = mlc.getFloat(new String[] { "cloudyoffset", "cyo" }, 1.8F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  78 */     new Animator(this, data, target, this.interval, this.duration);
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  84 */     new Animator(this, data, target, this.interval, this.duration);
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public ArrayList<AbstractVector> createCircle(double y, double radius, double rotation) {
/*  89 */     double amount = radius * this.sliceHeight;
/*  90 */     double inc = 6.283185307179586D / amount;
/*  91 */     double r = rotation % 360.0D;
/*     */     
/*  93 */     ArrayList<AbstractVector> vecs = new ArrayList<>();
/*  94 */     for (int i = 0; i < amount; i++) {
/*  95 */       double angle = i * inc + r;
/*  96 */       double x = radius * Math.cos(angle);
/*  97 */       double z = radius * Math.sin(angle);
/*  98 */       AbstractVector v = new AbstractVector(x, y, z);
/*  99 */       vecs.add(v);
/*     */     } 
/* 101 */     return vecs;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleTornadoEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */