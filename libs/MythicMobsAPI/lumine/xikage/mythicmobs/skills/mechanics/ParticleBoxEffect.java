/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleEffect;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.util.RandomUtil;
/*    */ 
/*    */ public class ParticleBoxEffect
/*    */   extends ParticleEffect implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   public ParticleBoxEffect(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */     
/* 19 */     this.radius = mlc.getFloat(new String[] { "radius", "r" }, 5.0F);
/*    */   }
/*    */   float radius;
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 24 */     playParticleBoxEffect(data, target);
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 30 */     playParticleBoxEffect(data, target.getLocation());
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   protected void playParticleBoxEffect(SkillMetadata data, AbstractLocation target) {
/* 35 */     AbstractLocation location = target.clone();
/* 36 */     location.add(0.0D, this.yOffset, 0.0D);
/* 37 */     int amount = this.amount.get((PlaceholderMeta)data);
/* 38 */     for (int i = 0; i < amount; i++) {
/* 39 */       AbstractVector vector = RandomUtil.getRandomVector().multiply(this.radius);
/* 40 */       location.add(vector);
/* 41 */       playParticleEffect(data, location);
/* 42 */       location.subtract(vector);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ParticleBoxEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */