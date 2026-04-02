/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "lastSignal", aliases = {}, description = "Matches the last signal received by the target mob")
/*    */ public class LastSignalCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "signal", aliases = {"s"}, description = "The signal to match")
/*    */   private final String signal;
/*    */   
/*    */   public LastSignalCondition(String line, MythicLineConfig mlc) {
/* 19 */     super(line);
/*    */     
/* 21 */     this.signal = mlc.getString(new String[] { "signal", "s" }, "DEFAULT", new String[] { this.conditionVar });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 26 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity)) {
/* 27 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/* 28 */       return am.getLastSignal().matches(this.signal);
/*    */     } 
/* 30 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\LastSignalCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */