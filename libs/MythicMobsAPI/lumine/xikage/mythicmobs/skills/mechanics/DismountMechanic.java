/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "dismount", description = "Dismounts the target entity")
/*    */ public class DismountMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   public DismountMechanic(String skill, MythicLineConfig mlc) {
/* 13 */     super(skill, mlc);
/* 14 */     this.ASYNC_SAFE = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 20 */     if (data.getCaster().getEntity().getVehicle() != null) {
/* 21 */       data.getCaster().getEntity().getVehicle().eject();
/*    */     }
/* 23 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DismountMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */