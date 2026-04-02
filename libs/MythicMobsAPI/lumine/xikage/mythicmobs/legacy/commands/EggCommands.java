/*    */ package lumine.xikage.mythicmobs.legacy.commands;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.commands.CommandHandler;
/*    */ import io.lumine.xikage.mythicmobs.mobs.EggManager;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class EggCommands
/*    */ {
/*    */   public static void menuCommands(CommandSender sender) {
/* 16 */     sender.sendMessage(MythicMobs.menu_header);
/* 17 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/* 18 */     sender.sendMessage(ChatColor.GOLD + "/mm egg get [mob_name] <amount>" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Gives you some mob eggs!");
/* 19 */     sender.sendMessage(ChatColor.GOLD + "/mm egg give [player_name] [mob_name] <amount>" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Gives player some mob eggs!");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void parseCommands(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/* 25 */     if (args.length == 1) {
/* 26 */       menuCommands(sender);
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     switch (args[1].toLowerCase()) { case "get":
/*    */       case "ge":
/* 32 */         cmdEggGet(sender, args); return;
/*    */       case "give":
/*    */       case "gi":
/* 35 */         cmdEggGive(sender, args);
/*    */         return; }
/*    */     
/* 38 */     menuCommands(sender);
/*    */   }
/*    */   
/*    */   public static void cmdEggGet(CommandSender sender, String[] args) {
/*    */     int amount;
/* 43 */     if (!(sender instanceof Player)) {
/* 44 */       CommandHandler.NoConsole(sender);
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[2]);
/*    */     
/* 50 */     if (mm == null) {
/* 51 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Mob loaded with the name " + args[2] + ".");
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/*    */     try {
/* 57 */       amount = Integer.parseInt(args[3]);
/* 58 */     } catch (Exception e) {
/* 59 */       amount = 1;
/*    */     } 
/*    */     
/* 62 */     if (EggManager.giveMythicEgg(mm, (Player)sender, amount) == true) {
/* 63 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Placed " + ChatColor.AQUA + amount + ChatColor.GREEN + " Mythic Eggs in your inventory!");
/*    */     } else {
/* 65 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "An error occured. Eggs could not be given.");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void cmdEggGive(CommandSender sender, String[] args) {
/*    */     int amount;
/* 72 */     Player player = Bukkit.getPlayer(args[2]);
/*    */     
/* 74 */     if (player == null) {
/* 75 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No player online named " + args[2] + ".");
/*    */       
/*    */       return;
/*    */     } 
/* 79 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[3]);
/*    */     
/* 81 */     if (mm == null) {
/* 82 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Mob loaded with the name " + args[2] + ".");
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/*    */     try {
/* 88 */       amount = Integer.parseInt(args[4]);
/* 89 */     } catch (Exception e) {
/* 90 */       amount = 1;
/*    */     } 
/*    */     
/* 93 */     if (EggManager.giveMythicEgg(mm, player, amount) == true) {
/* 94 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Gave " + ChatColor.AQUA + amount + ChatColor.GREEN + " Mythic Eggs to " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
/*    */     } else {
/* 96 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "An error occured. Eggs could not be given.");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\EggCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */