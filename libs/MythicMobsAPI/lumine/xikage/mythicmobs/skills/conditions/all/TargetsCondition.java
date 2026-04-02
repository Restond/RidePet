/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedInt;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "targets", aliases = {}, description = "Tests if the number of inherited targets from the parent skilltree matches the given range.")
/*    */ public class TargetsCondition
/*    */   extends SkillCondition implements ISkillMetaCondition {
/*    */   @MythicField(name = "amount", aliases = {"a"}, description = "Range of how many targets to check for")
/*    */   private RangedInt amount;
/*    */   
/*    */   public TargetsCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/*    */     
/* 20 */     this.amount = new RangedInt(mlc.getString(new String[] { "amount", "a" }, this.conditionVar, new String[] { ">0" }));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(SkillMetadata meta) {
/* 25 */     if (meta.getEntityTargets() != null) {
/* 26 */       return this.amount.equals(Integer.valueOf(meta.getEntityTargets().size()));
/*    */     }
/* 28 */     if (meta.getLocationTargets() != null) {
/* 29 */       return this.amount.equals(Integer.valueOf(meta.getLocationTargets().size()));
/*    */     }
/* 31 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\TargetsCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */