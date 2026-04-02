/*    */ package lumine.xikage.mythicmobs.commands;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicReloadedEvent;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReloadCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public ReloadCommand(Command<MythicMobs> parent) {
/* 18 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 23 */     MythicMobs.inst().getConfigManager().SaveAll();
/*    */     
/* 25 */     ((MythicMobs)this.plugin).getPlaceholderManager().initialize();
/* 26 */     ((MythicMobs)this.plugin).getSpawnerManager().resetAndSaveAll();
/*    */     
/* 28 */     ((MythicMobs)this.plugin).getConfigManager().ResetAll();
/* 29 */     ((MythicMobs)this.plugin).getConfigManager().LoadAll(false);
/*    */     
/* 31 */     ((MythicMobs)this.plugin).getRandomSpawningManager().reload();
/*    */     
/* 33 */     for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 34 */       am.remountSpawner();
/* 35 */       am.remountType();
/*    */     } 
/*    */     
/* 38 */     MythicReloadedEvent event = new MythicReloadedEvent((MythicMobs)this.plugin);
/* 39 */     ((MythicMobs)this.plugin).getServer().getPluginManager().callEvent((Event)event);
/*    */     
/* 41 */     CommandHelper.sendSuccess(sender, "&6MythicMobs &ahas been reloaded!");
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 52 */     return "mythicmobs.command.reload";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 62 */     return "reload";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 67 */     return new String[] { "r", "re" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\ReloadCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */