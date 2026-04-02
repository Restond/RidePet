/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class FindCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public FindCommand(Command<MythicMobs> parent) {
/* 18 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 23 */     double radius = 32.0D;
/* 24 */     if (args.length > 0) {
/* 25 */       radius = Double.parseDouble(args[0]);
/*    */     }
/*    */     
/* 28 */     CommandHelper.sendHeader(sender);
/* 29 */     sender.sendMessage(ChatColor.GOLD + "Spawners within " + radius + " blocks of you:");
/* 30 */     for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 31 */       if (ms.getLocation().getWorld() != null && 
/* 32 */         ms.getLocation().getWorld().equals(BukkitAdapter.adapt(((Player)sender).getLocation()).getWorld()) && 
/* 33 */         ms.distanceTo(BukkitAdapter.adapt((Player)sender).getLocation()) <= radius) {
/* 34 */         sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + ms.getName() + ChatColor.AQUA + " (" + (int)ms.distanceTo(BukkitAdapter.adapt((Player)sender).getLocation()) + " blocks away)");
/*    */       }
/*    */     } 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 48 */     return "mythicmobs.command.spawners.find";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 53 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 58 */     return "find";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 63 */     return new String[] { "listnear", "ln" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\FindCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */