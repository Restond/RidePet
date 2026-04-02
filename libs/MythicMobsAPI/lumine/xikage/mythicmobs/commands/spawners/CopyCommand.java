/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CopyCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public CopyCommand(Command<MythicMobs> parent) {
/* 21 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 26 */     if (args.length < 1) {
/* 27 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a spawner to copy.");
/* 28 */       return true;
/*    */     } 
/* 30 */     String name = args[0];
/* 31 */     if (MythicMobs.inst().getSpawnerManager().getSpawnerByName(name) == null) {
/* 32 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner to copy!");
/* 33 */       return true;
/*    */     } 
/*    */     
/* 36 */     if (args.length < 2) {
/* 37 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a name for the new spawner.");
/* 38 */       return true;
/*    */     } 
/* 40 */     String nameNew = args[1];
/* 41 */     MythicSpawner msNew = MythicMobs.inst().getSpawnerManager().getSpawnerByName(args[1]);
/* 42 */     if (msNew != null) {
/* 43 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The name you entered for a new spawner is already in use!");
/* 44 */       return true;
/*    */     } 
/*    */     
/* 47 */     Player p = (Player)sender;
/*    */     
/* 49 */     Location location = p.getTargetBlock((HashSet)null, 10).getLocation();
/* 50 */     if (location == null) {
/* 51 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block to place the spawner!");
/* 52 */       return true;
/*    */     } 
/*    */     
/* 55 */     if (MythicMobs.inst().getSpawnerManager().copySpawner(name, nameNew, BukkitAdapter.adapt(location))) {
/* 56 */       location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
/* 57 */       location.getWorld().playEffect(location, Effect.EXTINGUISH, 0);
/* 58 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "New spawner was cloned successfully!");
/*    */     } 
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 65 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 70 */     return "mythicmobs.command.spawners.copy";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 80 */     return "copy";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\CopyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */