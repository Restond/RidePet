/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "hasTag", aliases = {"hasScoreboardTag"}, description = "Tests if the target has a scoreboard tag")
/*    */ public class HasTagCondition extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "tag", aliases = {"t"}, description = "The tag to check for")
/* 13 */   private String tag = "";
/*    */ 
/*    */   
/*    */   public HasTagCondition(String line, MythicLineConfig mlc) {
/* 17 */     super(line);
/*    */     
/* 19 */     this.tag = mlc.getString(new String[] { "tag", "t" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 24 */     return target.hasScoreboardTag(this.tag);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasTagCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */