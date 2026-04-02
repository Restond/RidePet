/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ 
/*    */ public class SmokeEffect
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected int direction;
/*    */   
/*    */   public SmokeEffect(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */     
/* 19 */     this.direction = mlc.getInteger("direction", 4);
/* 20 */     this.direction = mlc.getInteger("dir", this.direction);
/* 21 */     this.direction = mlc.getInteger("d", this.direction);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 26 */     SkillAdapter.get().playSmokeEffect(target, this.direction);
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 32 */     SkillAdapter.get().playSmokeEffect(target.getLocation(), this.direction);
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SmokeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */