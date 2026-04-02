/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationComparisonCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "distance", aliases = {}, description = "Whether the distance between the caster and target is within the given range")
/*    */ public class DistanceCondition
/*    */   extends SkillCondition implements IEntityComparisonCondition, ILocationComparisonCondition {
/*    */   @MythicField(name = "distance", aliases = {"d"}, description = "The distance to match")
/*    */   protected RangedDouble distance;
/*    */   
/*    */   public DistanceCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     String d = mlc.getString(new String[] { "distance", "d" }, this.conditionVar, new String[0]);
/* 23 */     this.distance = new RangedDouble(d, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity, AbstractEntity target) {
/* 28 */     double diffSq = (float)entity.getLocation().distanceSquared(target.getLocation());
/* 29 */     return this.distance.equals(Double.valueOf(diffSq));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation location, AbstractLocation target) {
/* 34 */     double diffSq = (float)location.distanceSquared(target);
/* 35 */     return this.distance.equals(Double.valueOf(diffSq));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\DistanceCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */