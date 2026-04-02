/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:skybox", aliases = {"skybox", "e:skybox"}, description = "Modifies the skybox for the target player")
/*    */ public class SkyboxEffect extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderInt type;
/*    */   
/*    */   public SkyboxEffect(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */     
/* 19 */     this.type = PlaceholderInt.of(mlc.getString(new String[] { "skybox", "sky", "s", "type", "t", "environment", "env", "e" }, "0", new String[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 24 */     if (!target.isPlayer()) return false;
/*    */     
/* 26 */     getPlugin().getVolatileCodeHandler().getEntityHandler().setSkybox(target.asPlayer(), this.type.get((PlaceholderMeta)data, target));
/*    */ 
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SkyboxEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */