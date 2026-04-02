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
/*    */ @MythicCondition(author = "Ashijin", name = "foodSaturation", aliases = {"hungerSaturation"}, description = "Matches the target's food saturation level")
/*    */ public class FoodSaturationCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   @MythicField(name = "amount", aliases = {"a"}, description = "The food range to check for")
/*    */   private RangedDouble amount;
/*    */   
/*    */   public FoodSaturationCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     this.amount = new RangedDouble(mlc.getString(new String[] { "amount", "a", "food", "f", "saturation", "s" }, "0", new String[] { this.conditionVar }));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 28 */     if (entity.isPlayer()) {
/* 29 */       double v = entity.asPlayer().getFoodSaturation();
/* 30 */       MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Checking FoodSaturation: {0} vs {1}", new Object[] { Double.valueOf(v), this.amount.toString() });
/* 31 */       return this.amount.equals(Double.valueOf(v));
/*    */     } 
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\FoodSaturationCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */