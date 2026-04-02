/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "auraRemove", aliases = {"removeaura", "removebuff", "removedebuff"}, description = "Removes an aura from the target")
/*    */ public class AuraRemoveMechanic extends SkillMechanic implements INoTargetSkill {
/*    */   private PlaceholderString auraName;
/*    */   private int stacks;
/*    */   
/*    */   public AuraRemoveMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */     
/* 19 */     this.auraName = mlc.getPlaceholderString(new String[] { "aura", "buff", "debuff", "name", "b", "n" }, "default", new String[0]);
/* 20 */     this.stacks = mlc.getInteger(new String[] { "stacks", "s" }, 2147483647);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 25 */     data.getCaster().getAuraRegistry().removeStack(this.auraName.get((PlaceholderMeta)data), this.stacks);
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\AuraRemoveMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */