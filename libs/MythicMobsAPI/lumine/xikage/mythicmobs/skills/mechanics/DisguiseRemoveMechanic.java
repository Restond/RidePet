/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "undisguise", aliases = {"disguiseRemove"}, description = "Removes a disguise from the target entity")
/*    */ public class DisguiseRemoveMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill {
/*    */   public DisguiseRemoveMechanic(String skill, MythicLineConfig mlc) {
/* 15 */     super(skill, mlc);
/* 16 */     this.ASYNC_SAFE = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 21 */     if (CompatibilityManager.LibsDisguises != null) {
/* 22 */       CompatibilityManager.LibsDisguises.removeDisguise(data.getCaster().getEntity());
/* 23 */       return true;
/*    */     } 
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DisguiseRemoveMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */