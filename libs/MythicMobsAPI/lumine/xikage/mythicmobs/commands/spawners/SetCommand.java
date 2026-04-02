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
/*     */ public class SetCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public SetCommand(Command<MythicMobs> parent) {
/*  16 */     super(parent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  22 */     if (args.length < 3) {
/*  23 */       sender.sendMessage(MythicMobs.menu_header);
/*  24 */       sender.sendMessage(ChatColor.YELLOW + "Command: " + ChatColor.AQUA + "/mm spawners set [name] [attribute] [value]");
/*  25 */       sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Available Attributes:");
/*  26 */       sender.sendMessage(ChatColor.GOLD + "activationrange" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The max range a player can be for the spawner to activate.");
/*  27 */       sender.sendMessage(ChatColor.GOLD + "cooldown" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The time (in seconds) between mob spawns.");
/*  28 */       sender.sendMessage(ChatColor.GOLD + "group" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The name of a group to organize the spawner into.");
/*  29 */       sender.sendMessage(ChatColor.GOLD + "healonleash" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) Whether or not a mob will heal to full health upon being leashed.");
/*  30 */       sender.sendMessage(ChatColor.GOLD + "leashrange" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The max range a mob can be from the spawner before it is teleported back (0=none).");
/*  31 */       sender.sendMessage(ChatColor.GOLD + "maxmobs" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The maximum number of mobs this spawner can have active at once.");
/*  32 */       sender.sendMessage(ChatColor.GOLD + "moblevel" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The level of the mobs spawned by the spawner.");
/*  33 */       sender.sendMessage(ChatColor.GOLD + "mobsperspawn" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - How many mobs will spawn per spawner cycle.");
/*  34 */       sender.sendMessage(ChatColor.GOLD + "resetthreatonleash" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) Whether or not the mob's target should be reset upon being leashed.");
/*  35 */       sender.sendMessage(ChatColor.GOLD + "showflames" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) whether to always show spawner flames on the block.");
/*  36 */       sender.sendMessage(ChatColor.GOLD + "usetimer" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) whether the spawner should spawn on a timer or not..");
/*  37 */       sender.sendMessage(ChatColor.GOLD + "warmup" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The time (in seconds) until a mob spawns after a mob dies, when the max number of mobs has been reached.");
/*  38 */       return true;
/*     */     } 
/*     */     
/*  41 */     String name = args[0];
/*  42 */     String option = args[1];
/*  43 */     String value = args[2];
/*     */     
/*  45 */     if (name.startsWith("g:")) {
/*  46 */       String group = name.substring(2);
/*     */       
/*  48 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*     */       
/*  50 */       for (MythicSpawner ms : msl) {
/*  51 */         if (!MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/*  52 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The attribute or value you entered was invalid!");
/*  53 */           return true;
/*     */         } 
/*     */       } 
/*  56 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + ChatColor.GREEN + " on group " + group);
/*  57 */     } else if (name.equals("*")) {
/*  58 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/*  59 */         if (!MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/*  60 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*  61 */           return true;
/*     */         } 
/*     */       } 
/*  64 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + ChatColor.GREEN + " on all spawners!");
/*  65 */     } else if (name.contains("*") || name.contains("?")) {
/*  66 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/*  67 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?")) && 
/*  68 */           !MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/*  69 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*  70 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/*  74 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + ChatColor.GREEN + " on spawners matching pattern " + name + "!");
/*     */     } else {
/*  76 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */       
/*  78 */       if (ms == null) {
/*  79 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/*  80 */         return true;
/*     */       } 
/*     */       
/*  83 */       if (!MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/*  84 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The attribute or value you entered was invalid!");
/*     */       } else {
/*  86 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + " on spawner " + name);
/*     */       } 
/*     */     } 
/*  89 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/*  99 */     return "mythicmobs.command.spawners.set";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 109 */     return "set";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 114 */     return new String[] { "s" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\SetCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */