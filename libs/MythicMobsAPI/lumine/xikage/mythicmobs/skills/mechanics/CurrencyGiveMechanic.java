/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.VaultSupport;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "currencyGive", aliases = {"giveCurrency"}, description = "Gives an amount of vault currency")
/*    */ public class CurrencyGiveMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   public CurrencyGiveMechanic(String line, MythicLineConfig mlc) {
/* 17 */     super(line, mlc);
/*    */     
/* 19 */     this.amount = mlc.getPlaceholderDouble(new String[] { "amount", "a" }, 0.0D, new String[0]);
/*    */   }
/*    */   private PlaceholderDouble amount;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 24 */     if (!target.isPlayer()) return false;
/*    */     
/* 26 */     if (getPlugin().getCompatibility().getVault().isPresent()) {
/* 27 */       ((VaultSupport)getPlugin().getCompatibility().getVault().get()).giveMoney(target.asPlayer(), this.amount.get((PlaceholderMeta)data, target));
/*    */     }
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\CurrencyGiveMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */