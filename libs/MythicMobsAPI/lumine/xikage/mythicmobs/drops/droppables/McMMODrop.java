/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
/*    */ import io.lumine.xikage.mythicmobs.drops.IMessagingDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ 
/*    */ public class McMMODrop extends Drop implements IIntangibleDrop, IMessagingDrop {
/*    */   private PlaceholderString rewardMessage;
/*    */   
/*    */   public McMMODrop(String line, MythicLineConfig config) {
/* 19 */     super(line, config);
/* 20 */     this.rewardMessage = !ConfigManager.compatMcMMOShowXPMessage ? null : PlaceholderString.of(ConfigManager.compatMcMMOXPMessageFormat);
/*    */   }
/*    */   
/*    */   public McMMODrop(String line, MythicLineConfig config, double amount) {
/* 24 */     super(line, config, amount);
/* 25 */     this.rewardMessage = !ConfigManager.compatMcMMOShowXPMessage ? null : PlaceholderString.of(ConfigManager.compatMcMMOXPMessageFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public void giveDrop(AbstractPlayer target, DropMetadata metadata) {
/* 30 */     if (CompatibilityManager.mcMMO != null)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 44 */       CompatibilityManager.mcMMO.giveExp(BukkitAdapter.adapt(target), (float)getAmount(), "swords");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRewardMessage(DropMetadata meta, double amount) {
/* 50 */     if (this.rewardMessage == null || !ConfigManager.compatMcMMOShowXPMessage) {
/* 51 */       return null;
/*    */     }
/* 53 */     String message = this.rewardMessage.get((PlaceholderMeta)meta);
/* 54 */     message = message.replace("<drops.mcmmo>", String.valueOf(amount));
/* 55 */     message = message.replace("<drops.xp>", String.valueOf(amount));
/* 56 */     message = message.replace("<drop.amount>", String.valueOf(amount));
/* 57 */     return message;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\McMMODrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */