/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Joikd", name = "hasAura", aliases = {"hasbuff", "hasdebuff"}, description = "Checks if the target entity has the given aura")
/*    */ public class HasAuraCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "auraName", aliases = {"name", "n"}, description = "The name of the aura to check for")
/*    */   private String auraName;
/*    */   
/*    */   public HasAuraCondition(String line, MythicLineConfig mlc) {
/* 17 */     super(line);
/*    */     
/* 19 */     this.auraName = mlc.getString(new String[] { "name", "aura", "auraname", "buffname", "buff", "debuffname", "debuff", "n", "b" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 24 */     return getPlugin().getSkillManager().getAuraManager().getHasAura(target, this.auraName);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasAuraCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */