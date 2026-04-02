/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "mountme", version = "4.8", description = "Causes the ctarget entity to mount the caster")
/*    */ public class MountMeMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   public MountMeMechanic(String line, MythicLineConfig mlc) {
/* 14 */     super(line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 19 */     data.getCaster().getEntity().setPassenger(target);
/* 20 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\MountMeMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */