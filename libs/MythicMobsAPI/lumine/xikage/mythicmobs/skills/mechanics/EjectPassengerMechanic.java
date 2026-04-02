/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "ejectpassenger", aliases = {"eject_passenger"}, description = "Kicks off any entities using the caster as a vehicle")
/*    */ public class EjectPassengerMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   public EjectPassengerMechanic(String skill, MythicLineConfig mlc) {
/* 13 */     super(skill, mlc);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 19 */     if (data.getCaster().getEntity().getBukkitEntity().getPassenger() != null) {
/* 20 */       data.getCaster().getEntity().getBukkitEntity().eject();
/*    */     }
/* 22 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\EjectPassengerMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */