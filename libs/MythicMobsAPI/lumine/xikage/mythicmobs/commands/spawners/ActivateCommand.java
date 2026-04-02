/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class ActivateCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public ActivateCommand(Command<MythicMobs> parent) {
/* 16 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 21 */     if (args.length < 1) {
/* 22 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a mob spawner to activate!");
/*    */     }
/*    */     
/* 25 */     String name = args[0];
/*    */     
/* 27 */     if (name.startsWith("g:")) {
/*    */       
/* 29 */       String group = name.substring(2);
/*    */       
/* 31 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*    */       
/* 33 */       for (MythicSpawner ms : msl) {
/* 34 */         ms.ActivateSpawner();
/*    */       }
/* 36 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "All spawners in group " + group + " have been activated!");
/* 37 */     } else if (name.equals("*")) {
/* 38 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 39 */         ms.ActivateSpawner();
/*    */       }
/* 41 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "All spawners have been activated!");
/* 42 */     } else if (name.contains("*") || name.contains("?")) {
/* 43 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 44 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?"))) {
/* 45 */           ms.ActivateSpawner();
/*    */         }
/*    */       } 
/* 48 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner matching pattern " + name + " have been activated!");
/*    */     } else {
/* 50 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*    */       
/* 52 */       if (ms == null) {
/* 53 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The spawner entered does not exist!");
/*    */       }
/*    */       
/* 56 */       ms.ActivateSpawner();
/* 57 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawners " + name + " has been activated!");
/*    */     } 
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 69 */     return "mythicmobs.command.spawners.activate";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 74 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 79 */     return "activate";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 84 */     return new String[] { "a" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\ActivateCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */