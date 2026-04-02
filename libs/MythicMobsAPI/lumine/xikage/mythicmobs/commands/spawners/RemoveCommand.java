/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class RemoveCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public RemoveCommand(Command<MythicMobs> parent) {
/* 17 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 22 */     if (args.length < 1) {
/* 23 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a mob spawner to remove!");
/* 24 */       return true;
/*    */     } 
/* 26 */     String name = args[0];
/*    */     
/* 28 */     MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*    */     
/* 30 */     if (ms == null) {
/* 31 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The spawner entered does not exist!");
/* 32 */       return true;
/*    */     } 
/*    */     
/* 35 */     if (MythicMobs.inst().getSpawnerManager().removeSpawner(ms)) {
/* 36 */       BukkitAdapter.adapt(ms.getLocation()).getWorld().playEffect(BukkitAdapter.adapt(ms.getLocation()), Effect.SMOKE, 0);
/* 37 */       BukkitAdapter.adapt(ms.getLocation()).getWorld().playEffect(BukkitAdapter.adapt(ms.getLocation()), Effect.EXTINGUISH, 0);
/* 38 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner was removed successfully!");
/*    */     } 
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 50 */     return "mythicmobs.command.spawners.remove";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 60 */     return "remove";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 65 */     return new String[] { "re", "delete", "r" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\RemoveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */