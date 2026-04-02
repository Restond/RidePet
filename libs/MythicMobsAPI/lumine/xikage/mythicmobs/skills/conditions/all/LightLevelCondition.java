/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "lightLevel", aliases = {}, description = "Tests the light level at the target location")
/*    */ public class LightLevelCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition {
/*    */   @MythicField(name = "level", aliases = {"l"}, description = "The level range to match")
/*    */   private RangedDouble level;
/*    */   
/*    */   public LightLevelCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     String k = mlc.getString(new String[] { "level", "l" }, "0", new String[] { this.conditionVar });
/* 23 */     this.level = new RangedDouble(k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 29 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Checking LightLevel: {0} vs {1}", new Object[] { Integer.valueOf(l.getWorld().getBlockLightLevel(l)), this.level.toString() });
/* 30 */     return this.level.equals(Integer.valueOf(l.getWorld().getBlockLightLevel(l)));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\LightLevelCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */