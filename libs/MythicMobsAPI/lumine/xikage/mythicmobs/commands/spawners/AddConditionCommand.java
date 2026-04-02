/*     */ package lumine.xikage.mythicmobs.commands.spawners;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ 
/*     */ public class AddConditionCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public AddConditionCommand(Command<MythicMobs> parent) {
/*  16 */     super(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  21 */     if (args.length < 3) {
/*  22 */       sender.sendMessage(ChatColor.YELLOW + "Command: " + ChatColor.AQUA + "/mm spawners addcondition [condition] <data>");
/*  23 */       sender.sendMessage(ChatColor.YELLOW + "View conditions on the Manual @ http://www.mythicmobs.net");
/*  24 */       return true;
/*     */     } 
/*     */     
/*  27 */     String name = args[0];
/*  28 */     String condition = args[1];
/*  29 */     String value = args[2];
/*     */     
/*  31 */     if (name.startsWith("g:")) {
/*  32 */       String group = name.substring(2);
/*     */       
/*  34 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*     */       
/*  36 */       for (MythicSpawner ms : msl) {
/*  37 */         if (!MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/*  38 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*  39 */           return true;
/*     */         } 
/*     */       } 
/*  42 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + " on group " + group);
/*  43 */     } else if (name.equals("*")) {
/*  44 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/*  45 */         if (!MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/*  46 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*  47 */           return true;
/*     */         } 
/*     */       } 
/*  50 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " added to all spawners!");
/*  51 */     } else if (name.contains("*") || name.contains("?")) {
/*  52 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/*  53 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?")) && 
/*  54 */           !MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/*  55 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*  56 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/*  60 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " added to spawners matching pattern " + name + "!");
/*     */     } else {
/*  62 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */       
/*  64 */       if (ms == null) {
/*  65 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/*  66 */         return true;
/*     */       } 
/*     */       
/*  69 */       if (!MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/*  70 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */       } else {
/*  72 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " set to " + ChatColor.AQUA + value);
/*     */       } 
/*     */     } 
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/*  85 */     return "mythicmobs.command.spawners.addcondition";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  95 */     return "addcondition";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 100 */     return new String[] { "ac" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\AddConditionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */