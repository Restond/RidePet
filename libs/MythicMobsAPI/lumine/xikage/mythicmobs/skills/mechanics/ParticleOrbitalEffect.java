/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParticleOrbitalEffect
/*    */   extends ParticleEffect
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   protected float radius;
/*    */   protected int points;
/*    */   protected int interval;
/*    */   protected int iterations;
/* 29 */   protected double velocity = 1.0D;
/*    */   
/*    */   protected boolean rotate = false;
/*    */   protected boolean reversed = false;
/* 33 */   protected double xRotation = 0.0D;
/* 34 */   protected double yRotation = 0.0D;
/* 35 */   protected double zRotation = 0.0D;
/*    */   
/* 37 */   protected double angularVelocityX = 0.015707963267948967D;
/* 38 */   protected double angularVelocityY = 0.018479956785822312D;
/* 39 */   protected double angularVelocityZ = 0.02026833970057931D;
/*    */   
/* 41 */   protected double xOffset = 0.0D;
/* 42 */   protected double yOffset = 0.0D;
/* 43 */   protected double zOffset = 0.0D;
/*    */   
/*    */   public ParticleOrbitalEffect(String skill, MythicLineConfig mlc) {
/* 46 */     super(skill, mlc);
/*    */     
/* 48 */     this.radius = mlc.getFloat(new String[] { "radius", "r" }, 4.0F);
/* 49 */     this.points = mlc.getInteger(new String[] { "points", "p" }, 32);
/* 50 */     this.iterations = mlc.getInteger(new String[] { "ticks", "t" }, 1);
/* 51 */     this.interval = mlc.getInteger(new String[] { "interval", "in", "i" }, 10);
/*    */     
/* 53 */     this.xRotation = mlc.getDouble(new String[] { "rotationx", "rotx", "rx" }, 0.0D);
/* 54 */     this.yRotation = mlc.getDouble(new String[] { "rotationy", "roty", "ry" }, 0.0D);
/* 55 */     this.zRotation = mlc.getDouble(new String[] { "rotationz", "rotz", "rz" }, 0.0D);
/*    */     
/* 57 */     this.xOffset = mlc.getDouble(new String[] { "offsetx", "offx", "ox" }, 0.0D);
/* 58 */     this.yOffset = mlc.getDouble(new String[] { "offsety", "offy", "oy" }, 0.0D);
/* 59 */     this.zOffset = mlc.getDouble(new String[] { "offsetz", "offz", "oz" }, 0.0D);
/*    */     
/* 61 */     this.angularVelocityX = mlc.getDouble(new String[] { "angularvelocityx", "avx", "vx" }, 0.0D);
/* 62 */     this.angularVelocityY = mlc.getDouble(new String[] { "angularvelocityy", "avy", "vy" }, 0.0D);
/* 63 */     this.angularVelocityZ = mlc.getDouble(new String[] { "angularvelocityz", "avz", "vz" }, 0.0D);
/*    */     
/* 65 */     this.angularVelocityX = (this.angularVelocityX == 0.0D) ? 0.0D : (Math.PI / this.angularVelocityX);
/* 66 */     this.angularVelocityY = (this.angularVelocityY == 0.0D) ? 0.0D : (Math.PI / this.angularVelocityY);
/* 67 */     this.angularVelocityZ = (this.angularVelocityZ == 0.0D) ? 0.0D : (Math.PI / this.angularVelocityZ);
/*    */     
/* 69 */     this.rotate = mlc.getBoolean(new String[] { "rotate" }, (this.angularVelocityX > 0.0D || this.angularVelocityY > 0.0D || this.angularVelocityZ > 0.0D));
/* 70 */     this.reversed = mlc.getBoolean(new String[] { "reversed", "reverse" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 75 */     new Animator(this, data, target);
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 81 */     new Animator(this, data, target);
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleOrbitalEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */