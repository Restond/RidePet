/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
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
/*    */ 
/*    */ public class SkillAPIDrop
/*    */   extends Drop
/*    */   implements IIntangibleDrop, IMessagingDrop
/*    */ {
/*    */   private PlaceholderString rewardMessage;
/*    */   
/*    */   public SkillAPIDrop(String line, MythicLineConfig config) {
/* 24 */     super(line, config);
/* 25 */     this.rewardMessage = !ConfigManager.compatSkillAPIShowXPMessage ? null : PlaceholderString.of(ConfigManager.compatSkillAPIXPMessageFormat);
/*    */   }
/*    */   
/*    */   public SkillAPIDrop(String line, MythicLineConfig config, double amount) {
/* 29 */     super(line, config, amount);
/* 30 */     this.rewardMessage = !ConfigManager.compatSkillAPIShowXPMessage ? null : PlaceholderString.of(ConfigManager.compatSkillAPIXPMessageFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public void giveDrop(AbstractPlayer target, DropMetadata metadata) {
/* 35 */     MythicMobs.debug(2, "Granting " + getAmount() + " SkillAPI EXP to " + target.getName());
/* 36 */     if (CompatibilityManager.SkillAPI != null) {
/* 37 */       CompatibilityManager.SkillAPI.giveExp(BukkitAdapter.adapt(target), (int)getAmount());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRewardMessage(DropMetadata meta, double amount) {
/* 43 */     if (this.rewardMessage == null || !ConfigManager.compatSkillAPIShowXPMessage) {
/* 44 */       return null;
/*    */     }
/* 46 */     String message = this.rewardMessage.get((PlaceholderMeta)meta);
/* 47 */     message = message.replace("<drops.skillapi>", String.format("%.2f", new Object[] { Double.valueOf(amount) }));
/* 48 */     message = message.replace("<drops.xp>", String.format("%.2f", new Object[] { Double.valueOf(amount) }));
/* 49 */     message = message.replace("<drop.amount>", String.format("%.2f", new Object[] { Double.valueOf(amount) }));
/* 50 */     return message;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\SkillAPIDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */