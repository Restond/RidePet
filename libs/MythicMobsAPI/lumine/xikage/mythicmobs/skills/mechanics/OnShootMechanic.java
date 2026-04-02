/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "onbowshoot", aliases = {"onshoot"}, description = "Applies an aura to the target that triggers a skill when they shoot a bow")
/*    */ public class OnShootMechanic
/*    */   extends Aura
/*    */   implements ITargetedEntitySkill
/*    */ {
/* 27 */   protected Optional<Skill> onShootSkill = Optional.empty();
/*    */   
/*    */   protected String onShootSkillName;
/*    */   
/*    */   @MythicField(name = "cancelEvent", aliases = {"ce"}, defValue = "false", version = "4.6", description = "Whether or not to cancel the event that triggered the aura")
/*    */   protected boolean cancelEvent;
/*    */   @MythicField(name = "forceAsPower", aliases = {"fap"}, defValue = "true", version = "4.6", description = "Whether to pass the force of the bow as the skill's power")
/*    */   protected boolean forceAsPower;
/*    */   
/*    */   public OnShootMechanic(String skill, MythicLineConfig mlc) {
/* 37 */     super(skill, mlc);
/*    */     
/* 39 */     this.onShootSkillName = mlc.getString(new String[] { "onshootskill", "onshoot", "os", "onbowshoot", "onbowshootskill" });
/*    */     
/* 41 */     this.cancelEvent = mlc.getBoolean(new String[] { "cancelevent", "ce" }, false);
/* 42 */     this.forceAsPower = mlc.getBoolean(new String[] { "forceaspower", "fap" }, true);
/*    */     
/* 44 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*    */           if (this.onShootSkillName != null)
/*    */             this.onShootSkill = MythicMobs.inst().getSkillManager().getSkill(this.onShootSkillName); 
/*    */         });
/*    */   }
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 51 */     new Tracker(this, data, target);
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OnShootMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */