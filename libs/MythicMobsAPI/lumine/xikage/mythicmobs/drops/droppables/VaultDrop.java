/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.VaultSupport;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
/*    */ import io.lumine.xikage.mythicmobs.drops.IMessagingDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ 
/*    */ 
/*    */ public class VaultDrop
/*    */   extends Drop
/*    */   implements IIntangibleDrop, IMessagingDrop
/*    */ {
/*    */   private PlaceholderString rewardMessage;
/*    */   
/*    */   public VaultDrop(String line, MythicLineConfig config) {
/* 23 */     super(line, config);
/* 24 */     this.rewardMessage = !ConfigManager.compatVaultShowMoneyMessage ? null : PlaceholderString.of(ConfigManager.compatVaultMoneyMessageFormat);
/*    */   }
/*    */   
/*    */   public VaultDrop(String line, MythicLineConfig config, double amount) {
/* 28 */     super(line, config, amount);
/* 29 */     this.rewardMessage = !ConfigManager.compatVaultShowMoneyMessage ? null : PlaceholderString.of(ConfigManager.compatVaultMoneyMessageFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public void giveDrop(AbstractPlayer target, DropMetadata metadata) {
/* 34 */     if (MythicMobs.inst().getCompatibility().getVault().isPresent() && getAmount() > 0.0D) {
/* 35 */       ((VaultSupport)MythicMobs.inst().getCompatibility().getVault().get()).giveMoney(target, getAmount());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRewardMessage(DropMetadata meta, double amount) {
/* 41 */     if (this.rewardMessage == null) {
/* 42 */       return null;
/*    */     }
/* 44 */     String message = this.rewardMessage.get((PlaceholderMeta)meta);
/* 45 */     message = message.replace("<drops.money>", String.format("%.2f", new Object[] { Double.valueOf(amount) }));
/* 46 */     message = message.replace("<drop.amount>", String.format("%.2f", new Object[] { Double.valueOf(amount) }));
/* 47 */     return message;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\VaultDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */