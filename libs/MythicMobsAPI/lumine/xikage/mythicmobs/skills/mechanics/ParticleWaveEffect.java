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
/*    */ public class ParticleWaveEffect
/*    */   extends ParticleEffect
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   private String strCloudParticle;
/* 21 */   public int particlesFront = 10;
/* 22 */   public int particlesBack = 10;
/* 23 */   public int rows = 20;
/* 24 */   public float lengthFront = 1.5F;
/* 25 */   public float lengthBack = 3.0F;
/* 26 */   public float depthFront = 1.0F;
/* 27 */   public float heightBack = 0.5F;
/* 28 */   public float height = 2.0F;
/* 29 */   public float width = 5.0F;
/*    */   
/*    */   private int interval;
/*    */   
/*    */   private int duration;
/*    */   
/* 35 */   public double distance = 0.375D;
/*    */   
/*    */   public ParticleWaveEffect(String skill, MythicLineConfig mlc) {
/* 38 */     super(skill, mlc);
/*    */     
/* 40 */     this.strParticle = mlc.getString(new String[] { "particle", "p" }, "dripWater", new String[0]);
/* 41 */     this.strCloudParticle = mlc.getString(new String[] { "cloudparticle", "cp" }, "cloud", new String[0]);
/*    */     
/* 43 */     this.yOffset = mlc.getFloat(new String[] { "yoffset", "yo" }, 0.8F);
/* 44 */     this.interval = mlc.getInteger(new String[] { "interval", "i" }, 4);
/* 45 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 200);
/*    */     
/* 47 */     this.particlesFront = mlc.getInteger(new String[] { "amountfront", "af" }, 10);
/* 48 */     this.particlesBack = mlc.getInteger(new String[] { "amountback", "ab" }, 10);
/* 49 */     this.rows = mlc.getInteger(new String[] { "rows", "r" }, 20);
/* 50 */     this.lengthFront = mlc.getFloat(new String[] { "lengthfront", "lf" }, 1.5F);
/* 51 */     this.lengthBack = mlc.getFloat(new String[] { "lengthback", "lb" }, 3.0F);
/* 52 */     this.depthFront = mlc.getFloat(new String[] { "depthfront", "df" }, 1.0F);
/* 53 */     this.height = mlc.getFloat(new String[] { "height", "h" }, 2.0F);
/* 54 */     this.heightBack = mlc.getFloat(new String[] { "heightback", "hb" }, 0.5F);
/* 55 */     this.width = mlc.getFloat(new String[] { "width", "w" }, 5.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 60 */     new Animator(this, target, this.interval, this.duration);
/* 61 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 66 */     new Animator(this, target, this.interval, this.duration);
/* 67 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleWaveEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */