/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class PasteCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public PasteCommand(Command<MythicMobs> parent) {
/* 17 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 22 */     if (SpawnerSlice.PasteSpawners(BukkitAdapter.adapt(((Player)sender).getLocation())) == true) {
/* 23 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawners pasted from the clipboard!");
/*    */     } else {
/* 25 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "There are no spawners to paste in the clipboard (or an error occured!)");
/*    */     } 
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 37 */     return "mythicmobs.command.spawners.paste";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 47 */     return "paste";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\PasteCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */