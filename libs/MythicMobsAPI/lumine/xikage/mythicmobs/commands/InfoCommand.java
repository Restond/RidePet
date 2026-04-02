/*    */ package lumine.xikage.mythicmobs.commands;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InfoCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public InfoCommand(Command<MythicMobs> parent) {
/* 15 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 20 */     sender.sendMessage(ChatColor.GOLD + "MM Types Loaded: " + ChatColor.GRAY + MythicMobs.inst().getMobManager().getMobTypes().size() + "");
/* 21 */     sender.sendMessage(ChatColor.GOLD + "Spawners Loaded: " + ChatColor.GRAY + MythicMobs.inst().getSpawnerManager().getSpawners().size() + "");
/* 22 */     sender.sendMessage(ChatColor.GOLD + "Naturals Loaded: " + ChatColor.GRAY + MythicMobs.inst().getRandomSpawningManager().getNumberOfSpawners() + "");
/* 23 */     sender.sendMessage(ChatColor.GOLD + "----");
/* 24 */     sender.sendMessage(ChatColor.GOLD + "Active Mobs: " + ChatColor.GRAY + MythicMobs.inst().getMobManager().getActiveMobs().size() + "");
/* 25 */     sender.sendMessage(ChatColor.GOLD + "- in Combat: " + ChatColor.GRAY + MythicMobs.inst().getMobManager().getMobsInCombat().size() + "");
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 36 */     return "mythicmobs.command.info";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 46 */     return "info";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\InfoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */