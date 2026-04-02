/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "remount", description = "Causes the caster to remount their mount")
/*    */ public class RemountMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   public RemountMechanic(String skill, MythicLineConfig mlc) {
/* 16 */     super(skill, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 21 */     SkillCaster caster = data.getCaster();
/* 22 */     if (!(caster instanceof ActiveMob)) {
/* 23 */       return false;
/*    */     }
/* 25 */     ActiveMob am = (ActiveMob)caster;
/*    */     
/* 27 */     if (data.getCaster().getEntity().getVehicle() == null && 
/* 28 */       am.getMount().isPresent() && !((ActiveMob)am.getMount().get()).isDead()) {
/* 29 */       AbstractEntity entity = am.getEntity();
/* 30 */       AbstractEntity mount = ((ActiveMob)am.getMount().get()).getEntity();
/*    */       
/* 32 */       mount.setPassenger(entity);
/*    */     } 
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\RemountMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */