/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class UndoCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public UndoCommand(Command<MythicMobs> parent) {
/* 15 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 20 */     if (SpawnerSlice.Undo() == true) {
/* 21 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Last spawner clipboard changes undone!");
/*    */     } else {
/* 23 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "There are no spawners in the clipboard to undo an action on!");
/*    */     } 
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 35 */     return "mythicmobs.command.spawners.undo";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 45 */     return "undo";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\UndoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */