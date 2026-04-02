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
/*    */ @MythicCondition(author = "Ashijin", name = "mobsinworld", aliases = {}, description = "Matches a range to how many mobs are in the target world")
/*    */ public class MobsInWorldCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   @MythicField(name = "amount", aliases = {"a"}, description = "The number range to match")
/*    */   private RangedDouble amount;
/*    */   
/*    */   public MobsInWorldCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/*    */     
/* 20 */     String k = mlc.getString(new String[] { "amount", "a" }, "0", new String[] { this.conditionVar });
/* 21 */     this.amount = new RangedDouble(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 26 */     return this.amount.equals(Integer.valueOf(l.getWorld().getLivingEntities().size()));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\MobsInWorldCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */