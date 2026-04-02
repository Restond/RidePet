/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.io.GenericConfig;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "disguisemodify", aliases = {"modifydisguise"}, version = "4.8", description = "Disguises the target entity")
/*    */ public class DisguiseModifyMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill {
/*    */   public DisguiseModifyMechanic(String skill, MythicLineConfig mlc) {
/* 16 */     super(skill, mlc);
/* 17 */     this.ASYNC_SAFE = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 22 */     if (CompatibilityManager.LibsDisguises != null) {
/* 23 */       CompatibilityManager.LibsDisguises.modifyDisguise(data.getCaster().getEntity(), (GenericConfig)this.config);
/* 24 */       return true;
/*    */     } 
/* 26 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DisguiseModifyMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */