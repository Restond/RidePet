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
/*    */ public class RemoveConditionCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public RemoveConditionCommand(Command<MythicMobs> parent) {
/* 16 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 21 */     if (args.length < 2) {
/* 22 */       sender.sendMessage(ChatColor.YELLOW + "Command: " + ChatColor.AQUA + "/mm spawners removecondition [condition]");
/* 23 */       sender.sendMessage(ChatColor.YELLOW + "View conditions on the Manual @ http://www.mythicmobs.net");
/* 24 */       return true;
/*    */     } 
/*    */     
/* 27 */     String name = args[0];
/* 28 */     String condition = args[1];
/*    */     
/* 30 */     if (name.startsWith("g:")) {
/* 31 */       String group = name.substring(2);
/*    */       
/* 33 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*    */       
/* 35 */       for (MythicSpawner ms : msl) {
/* 36 */         if (!MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 37 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/* 38 */           return true;
/*    */         } 
/*    */       } 
/* 41 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " was removed on group " + group);
/* 42 */     } else if (name.equals("*")) {
/* 43 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 44 */         if (!MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 45 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/* 46 */           return true;
/*    */         } 
/*    */       } 
/* 49 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " removed from all spawners!");
/* 50 */     } else if (name.contains("*") || name.contains("?")) {
/* 51 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 52 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?")) && 
/* 53 */           !MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 54 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/* 55 */           return true;
/*    */         } 
/*    */       } 
/*    */       
/* 59 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " removed from spawners matching pattern " + name + "!");
/*    */     } else {
/* 61 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*    */       
/* 63 */       if (ms == null) {
/* 64 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/* 65 */         return true;
/*    */       } 
/*    */       
/* 68 */       if (!MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 69 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The attribute or value you entered was invalid!");
/*    */       } else {
/* 71 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " has been removed!");
/*    */       } 
/*    */     } 
/* 74 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 79 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 84 */     return "mythicmobs.command.spawners.removecondition";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 89 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 94 */     return "removecondition";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 99 */     return new String[] { "rc" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\RemoveConditionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */