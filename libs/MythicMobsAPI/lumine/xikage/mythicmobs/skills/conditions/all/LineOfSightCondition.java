/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "lineOfSight", aliases = {"inlineofsight"}, description = "Tests if the target is within line of sight of the caster")
/*    */ public class LineOfSightCondition
/*    */   extends SkillCondition implements IEntityComparisonCondition {
/*    */   public LineOfSightCondition(String line, MythicLineConfig mlc) {
/* 13 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity, AbstractEntity target) {
/* 18 */     return entity.hasLineOfSight(target);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\LineOfSightCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */