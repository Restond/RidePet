/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "tagadd", aliases = {"addtag", "addscoreboardtag"}, description = "Adds a scoreboard tag to the target entity")
/*    */ public class TagAddMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderString tag;
/*    */   
/*    */   public TagAddMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */     
/* 19 */     this.tag = PlaceholderString.of(mlc.getString(new String[] { "tag", "t" }, "default", new String[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 24 */     String tag = this.tag.get((PlaceholderMeta)data, target);
/* 25 */     target.addScoreboardTag(tag);
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\TagAddMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */