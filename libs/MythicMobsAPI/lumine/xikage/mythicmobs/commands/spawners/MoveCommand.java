/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
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
/*    */ public class MoveCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public MoveCommand(Command<MythicMobs> parent) {
/* 20 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     if (args.length < 1) {
/* 26 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a spawner to move.");
/* 27 */       return true;
/*    */     } 
/* 29 */     String name = args[0];
/* 30 */     if (MythicMobs.inst().getSpawnerManager().getSpawnerByName(name) == null) {
/* 31 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner to move!");
/* 32 */       return true;
/*    */     } 
/*    */     
/* 35 */     Player p = (Player)sender;
/*    */     
/* 37 */     Location location = p.getTargetBlock((HashSet)null, 10).getLocation();
/* 38 */     if (location == null) {
/* 39 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block to move the spawner!");
/* 40 */       return true;
/*    */     } 
/*    */     
/* 43 */     if (MythicMobs.inst().getSpawnerManager().moveSpawner(name, BukkitAdapter.adapt(location))) {
/* 44 */       location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
/* 45 */       location.getWorld().playEffect(location, Effect.EXTINGUISH, 0);
/* 46 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner " + name + " was moved successfully!");
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 58 */     return "mythicmobs.command.spawners.move";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 68 */     return "move";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\MoveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */