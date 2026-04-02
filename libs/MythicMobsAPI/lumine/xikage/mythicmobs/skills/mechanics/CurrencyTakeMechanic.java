/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.VaultSupport;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "currencyTake", aliases = {"takeCurrency"}, description = "Removes an amount of vault currency")
/*    */ public class CurrencyTakeMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   public CurrencyTakeMechanic(String line, MythicLineConfig mlc) {
/* 18 */     super(line, mlc);
/*    */     
/* 20 */     this.amount = mlc.getPlaceholderDouble(new String[] { "amount", "a" }, 0.0D, new String[0]);
/*    */   }
/*    */   private PlaceholderDouble amount;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 25 */     if (!target.isPlayer()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     if (getPlugin().getCompatibility().getVault().isPresent()) {
/* 30 */       ((VaultSupport)getPlugin().getCompatibility().getVault().get()).takeMoney(target.asPlayer(), this.amount.get((PlaceholderMeta)data, target));
/*    */     } else {
/* 32 */       MythicLogger.errorMechanicConfig(this, this.config, "Could not run TakeCurrency mechanic: Vault not found.");
/*    */     } 
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\CurrencyTakeMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */