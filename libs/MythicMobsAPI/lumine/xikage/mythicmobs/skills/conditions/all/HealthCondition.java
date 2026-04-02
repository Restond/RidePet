/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "health", aliases = {"hp"}, description = "Matches the target's health")
/*    */ public class HealthCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   @MythicField(name = "amount", aliases = {"a"}, description = "The health range to check for")
/*    */   private RangedDouble amount;
/*    */   
/*    */   public HealthCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     this.amount = new RangedDouble(mlc.getString(new String[] { "amount", "a", "health", "h" }, "0", new String[] { this.conditionVar }));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 28 */     double health = entity.getHealth();
/* 29 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Checking Health: {0} vs {1}", new Object[] { Double.valueOf(health), this.amount.toString() });
/* 30 */     return this.amount.equals(Double.valueOf(health));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HealthCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */