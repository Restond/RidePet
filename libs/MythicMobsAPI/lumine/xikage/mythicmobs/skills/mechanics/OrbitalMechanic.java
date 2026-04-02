/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "orbital", aliases = {"o"}, description = "Applies an orbital aura to the target")
/*     */ public class OrbitalMechanic
/*     */   extends Aura
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*     */   protected float radius;
/*     */   protected float hitRadius;
/*     */   protected float verticalHitRadius;
/*     */   protected int points;
/*     */   protected int interval;
/*     */   protected int iterations;
/*  33 */   protected double velocity = 1.0D;
/*     */   
/*     */   protected boolean rotate = false;
/*  36 */   protected double xRotation = 0.0D;
/*  37 */   protected double yRotation = 0.0D;
/*  38 */   protected double zRotation = 0.0D;
/*     */   
/*  40 */   protected double angularVelocityX = 0.015707963267948967D;
/*  41 */   protected double angularVelocityY = 0.018479956785822312D;
/*  42 */   protected double angularVelocityZ = 0.02026833970057931D;
/*     */   
/*  44 */   protected double xOffset = 0.0D;
/*  45 */   protected double yOffset = 0.0D;
/*  46 */   protected double zOffset = 0.0D;
/*     */   
/*  48 */   protected Optional<Skill> onHitSkill = Optional.empty();
/*     */   
/*     */   protected String onHitSkillName;
/*     */   protected boolean hitSelf = false;
/*     */   protected boolean hitPlayers = true;
/*     */   protected boolean hitNonPlayers = false;
/*     */   
/*     */   public OrbitalMechanic(String skill, MythicLineConfig mlc) {
/*  56 */     super(skill, mlc);
/*     */     
/*  58 */     this.radius = mlc.getFloat(new String[] { "radius", "r" }, 4.0F);
/*  59 */     this.hitRadius = mlc.getFloat(new String[] { "hitradius", "hr" }, 1.0F);
/*  60 */     this.verticalHitRadius = mlc.getFloat(new String[] { "verticalhitradius", "vhr", "vr" }, this.hitRadius);
/*     */     
/*  62 */     this.points = mlc.getInteger(new String[] { "points", "p" }, 32);
/*     */     
/*  64 */     this.xRotation = mlc.getDouble(new String[] { "rotationx", "rotx", "rx" }, 0.0D);
/*  65 */     this.yRotation = mlc.getDouble(new String[] { "rotationy", "roty", "ry" }, 0.0D);
/*  66 */     this.zRotation = mlc.getDouble(new String[] { "rotationz", "rotz", "rz" }, 0.0D);
/*     */     
/*  68 */     this.xOffset = mlc.getDouble(new String[] { "offsetx", "offx", "ox" }, 0.0D);
/*  69 */     this.yOffset = mlc.getDouble(new String[] { "offsety", "offy", "oy" }, 0.0D);
/*  70 */     this.zOffset = mlc.getDouble(new String[] { "offsetz", "offz", "oz" }, 0.0D);
/*     */     
/*  72 */     this.angularVelocityX = mlc.getDouble(new String[] { "angularvelocityx", "avx", "vx" }, 0.0D);
/*  73 */     this.angularVelocityY = mlc.getDouble(new String[] { "angularvelocityy", "avy", "vy" }, 0.0D);
/*  74 */     this.angularVelocityZ = mlc.getDouble(new String[] { "angularvelocityz", "avz", "vz" }, 0.0D);
/*     */     
/*  76 */     this.angularVelocityX = (this.angularVelocityX == 0.0D) ? 0.0D : (Math.PI / this.angularVelocityX);
/*  77 */     this.angularVelocityY = (this.angularVelocityY == 0.0D) ? 0.0D : (Math.PI / this.angularVelocityY);
/*  78 */     this.angularVelocityZ = (this.angularVelocityZ == 0.0D) ? 0.0D : (Math.PI / this.angularVelocityZ);
/*     */     
/*  80 */     this.rotate = mlc.getBoolean(new String[] { "rotate" }, (this.angularVelocityX > 0.0D || this.angularVelocityY > 0.0D || this.angularVelocityZ > 0.0D));
/*     */     
/*  82 */     this.hitSelf = mlc.getBoolean(new String[] { "hitself", "hs" }, false);
/*  83 */     this.hitPlayers = mlc.getBoolean(new String[] { "hitplayers", "hp" }, true);
/*  84 */     this.hitNonPlayers = mlc.getBoolean(new String[] { "hitnonplayers", "hnp" }, false);
/*     */     
/*  86 */     this.onHitSkillName = mlc.getString(new String[] { "onhitskill", "onhit", "oh" });
/*     */     
/*  88 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*     */           if (this.onHitSkillName != null)
/*     */             this.onHitSkill = MythicMobs.inst().getSkillManager().getSkill(this.onHitSkillName); 
/*     */           MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onHitSkillName);
/*     */         });
/*     */   }
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  96 */     new ProjectileTracker(this, data, target);
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 102 */     new ProjectileTracker(this, data, target);
/* 103 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OrbitalMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */