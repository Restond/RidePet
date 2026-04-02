/*     */ package lumine.xikage.mythicmobs.legacy.commands;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicReloadedEvent;
/*     */ import io.lumine.xikage.mythicmobs.legacy.commands.DebugCommands;
/*     */ import io.lumine.xikage.mythicmobs.legacy.commands.ItemCommands;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ 
/*     */ public class CommandHandler implements CommandExecutor {
/*     */   public void menuCommands(CommandSender sender) {
/*  20 */     sender.sendMessage(MythicMobs.menu_header);
/*  21 */     sender.sendMessage(ChatColor.GOLD + "/mm mobs" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists mob-related commands.");
/*  22 */     sender.sendMessage(ChatColor.GOLD + "/mm eggs" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists egg-related commands.");
/*  23 */     sender.sendMessage(ChatColor.GOLD + "/mm items" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists item-related commands.");
/*  24 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists spawner-related commands.");
/*  25 */     sender.sendMessage(ChatColor.GOLD + "/mm test" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists special commands used for testing.");
/*  26 */     sender.sendMessage(ChatColor.GOLD + "/mm utility" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists some helpful utility commands.");
/*  27 */     sender.sendMessage(ChatColor.YELLOW + "/mm reload" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Reloads all configuration files and mobs.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/*  33 */     if (args.length == 0) {
/*  34 */       if (!sender.hasPermission("mythicmobs.admin")) {
/*  35 */         return false;
/*     */       }
/*  37 */       menuCommands(sender);
/*  38 */       return true;
/*     */     } 
/*     */     
/*  41 */     if (sender instanceof Player) {
/*  42 */       Player p = (Player)sender;
/*  43 */       if (!args[0].toLowerCase().equals("signal") && !p.hasPermission("mythicmobs.admin")) {
/*  44 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  48 */     switch (args[0].toLowerCase()) { case "mobs": case "mob":
/*     */       case "m":
/*  50 */         MobCommands.parseCommands(sender, cmd, commandlabel, args);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  86 */         return true;case "signal": cmdSignal(sender, args); return true;case "items": case "item": case "i": ItemCommands.parseCommands(sender, cmd, commandlabel, args); return true;case "eggs": case "egg": case "e": EggCommands.parseCommands(sender, cmd, commandlabel, args); return true;case "spawners": case "spawner": case "s": SpawnerCommands.parseCommands(sender, cmd, commandlabel, args); return true;case "test": case "t": TestCommands.parseCommands(sender, cmd, commandlabel, args); return true;case "utilities": case "utility": case "u": UtilityCommands.parseCommands(sender, cmd, commandlabel, args); return true;case "reload": case "r": cmdReload(sender, args); return true;case "debug": case "d": DebugCommands.parseCommands(sender, cmd, commandlabel, args); return true;case "save": cmdSave(sender, args); return true;case "info": cmdInfo(sender, args); return true; }  menuCommands(sender); return true;
/*     */   }
/*     */   private void cmdSignal(CommandSender sender, String[] args) {
/*     */     UUID uuid;
/*     */     String signal;
/*     */     AbstractPlayer abstractPlayer;
/*     */     try {
/*  93 */       uuid = UUID.fromString(args[1]);
/*  94 */       signal = args[2];
/*  95 */     } catch (Exception ex) {
/*  96 */       sender.sendMessage(ChatColor.RED + "Signal skill must be in format: /mm signal <uuid> <signal>");
/*     */       
/*     */       return;
/*     */     } 
/* 100 */     if (!MythicMobs.inst().getMobManager().isActiveMob(uuid)) {
/* 101 */       sender.sendMessage(ChatColor.RED + "Failed to send Signal: UUID does not correspond to a Mythic Mob.");
/*     */       return;
/*     */     } 
/* 104 */     ActiveMob am = MythicMobs.inst().getMobManager().getActiveMob(uuid).get();
/*     */     
/* 106 */     AbstractEntity trigger = null;
/* 107 */     if (sender instanceof Player) {
/* 108 */       if (!sender.hasPermission("mythicmobs.signal")) {
/* 109 */         sender.sendMessage(ChatColor.RED + "Failed to send Signal: You do not have permission to use that command!");
/*     */         return;
/*     */       } 
/* 112 */       abstractPlayer = BukkitAdapter.adapt((Player)sender);
/*     */     } 
/*     */     
/* 115 */     am.signalMob((AbstractEntity)abstractPlayer, signal);
/*     */   }
/*     */   
/*     */   public static void cmdReload(CommandSender sender, String[] args) {
/* 119 */     MythicMobs.inst().getConfigManager().SaveAll();
/*     */     
/* 121 */     MythicMobs.inst().getSpawnerManager().resetAndSaveAll();
/*     */     
/* 123 */     MythicMobs.inst().getConfigManager().ResetAll();
/* 124 */     MythicMobs.inst().getConfigManager().LoadAll(false);
/*     */     
/* 126 */     MythicMobs.inst().getRandomSpawningManager().reload();
/*     */     
/* 128 */     for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 129 */       am.remountSpawner();
/* 130 */       am.remountType();
/*     */     } 
/*     */     
/* 133 */     MythicReloadedEvent event = new MythicReloadedEvent(MythicMobs.inst());
/* 134 */     MythicMobs.inst().getServer().getPluginManager().callEvent((Event)event);
/*     */     
/* 136 */     sender.sendMessage(ChatColor.GOLD + "MythicMobs" + ChatColor.GREEN + " has been reloaded!");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void cmdSave(CommandSender sender, String[] args) {}
/*     */ 
/*     */   
/*     */   public static void cmdInfo(CommandSender sender, String[] args) {
/* 144 */     sender.sendMessage(MythicMobs.menu_header);
/* 145 */     sender.sendMessage(ChatColor.GOLD + "MM Types Loaded: " + ChatColor.GRAY + MythicMobs.inst().getMobManager().getMobTypes().size() + "");
/* 146 */     sender.sendMessage(ChatColor.GOLD + "Spawners Loaded: " + ChatColor.GRAY + MythicMobs.inst().getSpawnerManager().getSpawners().size() + "");
/* 147 */     sender.sendMessage(ChatColor.GOLD + "Naturals Loaded: " + ChatColor.GRAY + MythicMobs.inst().getRandomSpawningManager().getNumberOfSpawners() + "");
/* 148 */     sender.sendMessage(ChatColor.GOLD + "----");
/* 149 */     sender.sendMessage(ChatColor.GOLD + "Active Mobs: " + ChatColor.GRAY + MythicMobs.inst().getMobManager().getActiveMobs().size() + "");
/* 150 */     sender.sendMessage(ChatColor.GOLD + "- in Combat: " + ChatColor.GRAY + MythicMobs.inst().getMobManager().getMobsInCombat().size() + "");
/*     */   }
/*     */   
/*     */   public static void NoConsole(CommandSender sender) {
/* 154 */     sender.sendMessage(ChatColor.RED + "This command can't be cast from the console! :(");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\CommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */