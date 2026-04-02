/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.VaultSupport;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "hasCurrency", aliases = {"hasmoney"}, description = "If the target has the given amount of vault currency")
/*    */ public class HasCurrencyCondition extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "amount", aliases = {"a"}, description = "The amount of currency")
/*    */   protected double amount;
/*    */   
/*    */   public HasCurrencyCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/*    */     
/* 20 */     String amount = mlc.getString(new String[] { "amount", "a" }, String.valueOf(Double.MAX_VALUE), new String[] { this.conditionVar });
/* 21 */     this.amount = Double.valueOf(amount).doubleValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 26 */     if (!target.isPlayer()) return false;
/*    */     
/* 28 */     if (MythicMobs.inst().getCompatibility().getVault().isPresent()) {
/* 29 */       return ((VaultSupport)MythicMobs.inst().getCompatibility().getVault().get()).hasMoney(target.asPlayer(), this.amount);
/*    */     }
/* 31 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasCurrencyCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */