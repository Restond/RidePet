/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedInt;
/*    */ 
/*    */ 
/*    */ @MythicCondition(author = "Joikd", name = "hasAuraStacks", aliases = {"hasbuffstacks", "hasdebuffstacks", "aurastacks", "buffstacks", "debuffstacks"}, description = "Tests if the target has the given range of stacks from an aura")
/*    */ public class HasAuraStacksCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition
/*    */ {
/*    */   @MythicField(name = "auraName", aliases = {"name", "n"}, description = "The aura name")
/*    */   private String auraName;
/*    */   @MythicField(name = "stacks", aliases = {"s"}, description = "The number/range of stacks to check for")
/*    */   private RangedInt stacks;
/*    */   
/*    */   public HasAuraStacksCondition(String line, MythicLineConfig mlc) {
/* 23 */     super(line);
/*    */     
/* 25 */     this.auraName = mlc.getString(new String[] { "name", "aura", "auraname", "buffname", "buff", "debuffname", "debuff", "n", "b" }, this.conditionVar, new String[0]);
/* 26 */     this.stacks = new RangedInt(mlc.getString(new String[] { "stacks", "s" }, "1", new String[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 31 */     int targetStacks = getPlugin().getSkillManager().getAuraManager().getAuraStacks(target, this.auraName);
/* 32 */     return this.stacks.equals(Integer.valueOf(targetStacks));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasAuraStacksCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */