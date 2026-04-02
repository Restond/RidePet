/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class ResetTimersCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public ResetTimersCommand(Command<MythicMobs> parent) {
/* 17 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 23 */     if (args.length == 0) {
/* 24 */       Collection<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawners();
/* 25 */       for (MythicSpawner ms : msl) {
/* 26 */         ms.resetTimers();
/*    */       }
/* 28 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner timers reset for all spawners");
/* 29 */       return true;
/*    */     } 
/*    */     
/* 32 */     String name = args[0];
/*    */     
/* 34 */     if (name.startsWith("g:")) {
/* 35 */       String group = name.substring(2);
/*    */       
/* 37 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*    */       
/* 39 */       for (MythicSpawner ms : msl) {
/* 40 */         ms.resetTimers();
/*    */       }
/* 42 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner timers reset on group " + group);
/* 43 */     } else if (name.equals("*")) {
/* 44 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 45 */         ms.resetTimers();
/*    */       }
/* 47 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner timers reset on all spawners!");
/* 48 */     } else if (name.contains("*") || name.contains("?")) {
/* 49 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 50 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?"))) {
/* 51 */           ms.resetTimers();
/*    */         }
/*    */       } 
/* 54 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner timers reset on spawners matching pattern " + name + "!");
/*    */     } else {
/* 56 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*    */       
/* 58 */       if (ms == null) {
/* 59 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/* 60 */         return true;
/*    */       } 
/*    */       
/* 63 */       ms.resetTimers();
/* 64 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner timers reset on spawner " + name);
/*    */     } 
/* 66 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 71 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 76 */     return "mythicmobs.command.spawners.resettimers";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 81 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 86 */     return "resettimers";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 91 */     return new String[] { "rt" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\ResetTimersCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */