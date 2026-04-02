/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "distanceFromSpawn", aliases = {}, description = "Whether the distance from the world's spawn point to the target is within the given range")
/*    */ public class DistanceFromSpawnCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition {
/*    */   @MythicField(name = "distance", aliases = {"d"}, description = "The distance to match")
/*    */   private PlaceholderString distance;
/*    */   
/*    */   public DistanceFromSpawnCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     this.distance = mlc.getPlaceholderString(new String[] { "distance", "d" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 27 */     double distanceSq = l.getWorld().getSpawnLocation().distanceSquared(l);
/* 28 */     return (new RangedDouble(this.distance.get())).equalsSquared(Double.valueOf(distanceSq));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\DistanceFromSpawnCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */