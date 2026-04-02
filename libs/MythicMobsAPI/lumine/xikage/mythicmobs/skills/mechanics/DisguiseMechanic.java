/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "disguise", description = "Disguises the target entity")
/*    */ public class DisguiseMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill {
/*    */   protected PlaceholderString disguise;
/*    */   
/*    */   public DisguiseMechanic(String skill, MythicLineConfig mlc) {
/* 18 */     super(skill, mlc);
/* 19 */     this.ASYNC_SAFE = false;
/*    */     
/* 21 */     this.disguise = mlc.getPlaceholderString(new String[] { "type", "disguise", "d" }, "player:Xikage", new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 26 */     if (CompatibilityManager.LibsDisguises != null) {
/* 27 */       CompatibilityManager.LibsDisguises.setDisguise(data.getCaster().getEntity(), this.config);
/* 28 */       return true;
/*    */     } 
/* 30 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DisguiseMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */