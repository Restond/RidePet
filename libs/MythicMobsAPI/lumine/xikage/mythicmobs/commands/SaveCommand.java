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
/*    */ public class SaveCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public SaveCommand(Command<MythicMobs> parent) {
/* 15 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 20 */     ((MythicMobs)this.plugin).getConfigManager().SaveAll();
/* 21 */     sender.sendMessage(ChatColor.GOLD + "MythicMobs" + ChatColor.GREEN + " has been saved!");
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 27 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 32 */     return "mythicmobs.command.save";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 42 */     return "save";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\SaveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */