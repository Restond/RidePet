/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "DamageAmount", aliases = {}, version = "4.7", description = "Checks the damage amount that caused the current skill tree. Only works with onDamaged trigger or aura.")
/*    */ public class DamageAmountCondition
/*    */   extends SkillCondition
/*    */   implements ISkillMetaCondition
/*    */ {
/*    */   @MythicField(name = "damagecause", aliases = {"amount", "a"}, description = "The damage cause to match")
/*    */   protected PlaceholderString amount;
/*    */   
/*    */   public DamageAmountCondition(String line, MythicLineConfig mlc) {
/* 23 */     super(line);
/*    */     
/* 25 */     this.amount = mlc.getPlaceholderString(new String[] { "damageamount", "amount", "a" }, ">0", new String[] { this.conditionVar });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(SkillMetadata meta) {
/* 30 */     if (meta.getVariables().has("damage-cause")) {
/* 31 */       double amount = Double.valueOf((String)meta.getVariables().get("damage-amount").get()).doubleValue();
/* 32 */       return (new RangedDouble(this.amount.get((PlaceholderMeta)meta))).equals(Double.valueOf(amount));
/*    */     } 
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\DamageAmountCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */