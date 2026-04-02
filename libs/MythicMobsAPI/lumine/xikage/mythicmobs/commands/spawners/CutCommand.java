/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CutCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public CutCommand(Command<MythicMobs> parent) {
/* 19 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 24 */     if (args.length < 1) {
/* 25 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a spawner to move.");
/* 26 */       return true;
/*    */     } 
/* 28 */     String name = args[0];
/*    */     
/* 30 */     ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByString(BukkitAdapter.adapt((Player)sender).getLocation(), name);
/*    */     
/* 32 */     if (msl.size() == 0) {
/* 33 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Your entry returned no spawners to copy.");
/* 34 */       return true;
/*    */     } 
/* 36 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Cut " + ChatColor.AQUA + msl.size() + ChatColor.GREEN + " spawners to the clipboard!");
/*    */     
/* 38 */     SpawnerSlice.CutSpawners(msl, BukkitAdapter.adapt(((Player)sender).getLocation()));
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 49 */     return "mythicmobs.command.spawners.";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 59 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\CutCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */