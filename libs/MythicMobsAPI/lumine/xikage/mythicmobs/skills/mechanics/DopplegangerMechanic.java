/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "doppleganger", aliases = {"copyplayer"}, description = "Disguises the caster as the target entity")
/*    */ public class DopplegangerMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   public DopplegangerMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/* 18 */     this.ASYNC_SAFE = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 23 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 24 */       return false;
/*    */     }
/* 26 */     if (CompatibilityManager.LibsDisguises == null) return false; 
/* 27 */     if (!target.isPlayer()) return false;
/*    */     
/* 29 */     ActiveMob am = (ActiveMob)data.getCaster();
/*    */     
/* 31 */     String disguise = "doppleganger:" + am.getType().getDisplayName() + ":" + target.asPlayer().getName();
/*    */     
/* 33 */     MythicMobs.debug(3, "------ Running Doppleganger AbstractSkill with string: " + disguise);
/*    */     
/* 35 */     CompatibilityManager.LibsDisguises.setDisguise(am, disguise);
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DopplegangerMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */