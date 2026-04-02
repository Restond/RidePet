/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "localdifficulty", aliases = {}, description = "Tests the difficulty scale at the target location")
/*    */ public class LocalDifficultyCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   @MythicField(name = "difficulty", aliases = {"diff", "d"}, description = "The difficulty range to check")
/*    */   private RangedDouble difficulty;
/*    */   
/*    */   public LocalDifficultyCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/*    */     
/* 20 */     this.difficulty = new RangedDouble(mlc.getString(new String[] { "difficulty", "diff", "d" }, this.conditionVar, new String[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 25 */     float diff = getPlugin().getVolatileCodeHandler().getWorldHandler().getDifficultyScale(target);
/* 26 */     return this.difficulty.equals(Float.valueOf(diff));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\LocalDifficultyCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */