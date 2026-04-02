/*     */ package lumine.xikage.mythicmobs.legacy.commands;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.legacy.LegacySkillHandler;
/*     */ import io.lumine.xikage.mythicmobs.legacy.commands.CommandHandler;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillEffect;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.util.HashSet;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class TestCommands
/*     */ {
/*     */   public static void menuCommands(CommandSender sender) {
/*  25 */     sender.sendMessage(MythicMobs.menu_header);
/*  26 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/*  27 */     sender.sendMessage(ChatColor.GOLD + "/mm test effect [effect_string]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Parses the given effect string and executes it.");
/*  28 */     sender.sendMessage(ChatColor.GOLD + "/mm test skill [skill_string]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Parses the given skill string and executes it.");
/*  29 */     sender.sendMessage(ChatColor.GOLD + "/mm test mob [mob_name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Turns you into the given mob!");
/*  30 */     sender.sendMessage(ChatColor.GOLD + "/mm test stop" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Stops any ongoing tests.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void parseCommands(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/*  36 */     if (args.length == 1) {
/*  37 */       menuCommands(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  41 */     switch (args[1].toLowerCase()) { case "effect":
/*     */       case "e":
/*  43 */         cmdTestEffect(sender, args); return;
/*     */       case "skill":
/*     */       case "s":
/*  46 */         cmdTestSkill(sender, args); return;
/*     */       case "mob":
/*     */       case "m":
/*  49 */         cmdTestMob(sender, args);
/*     */         return;
/*     */       case "stop":
/*  52 */         cmdStopTestMob(sender, args);
/*     */         return; }
/*     */     
/*  55 */     menuCommands(sender);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cmdTestEffect(CommandSender sender, String[] args) {
/*  61 */     if (!(sender instanceof Player)) {
/*  62 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     Player p = (Player)sender;
/*     */     
/*  68 */     Location location = p.getTargetBlock((HashSet)null, 64).getLocation();
/*  69 */     if (location == null) {
/*  70 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block!");
/*     */       
/*     */       return;
/*     */     } 
/*  74 */     String es = "effect ";
/*     */     
/*  76 */     for (int i = 2; i < args.length; i++) {
/*  77 */       es = es + args[i] + " ";
/*     */     }
/*     */     
/*     */     try {
/*  81 */       SkillEffect.ExecuteSkill((LivingEntity)p, es, null);
/*  82 */     } catch (Exception ex) {
/*  83 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The effect string tested is invalid!");
/*  84 */       if (ConfigManager.debugLevel > 0) {
/*  85 */         ex.printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdTestSkill(CommandSender sender, String[] args) {
/*  91 */     if (!(sender instanceof Player)) {
/*  92 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  96 */     Player p = (Player)sender;
/*     */     
/*  98 */     LivingEntity target = MythicUtil.getTargetedEntity(p);
/*     */     
/* 100 */     String ss = "";
/*     */     
/* 102 */     for (int i = 2; i < args.length; i++) {
/* 103 */       ss = ss + args[i] + " ";
/*     */     }
/*     */ 
/*     */     
/* 107 */     LegacySkillHandler.ExecuteMobSkill((Entity)p, SkillTrigger.COMBAT, ss, target, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cmdTestMob(CommandSender sender, String[] args) {
/* 117 */     if (!(sender instanceof Player)) {
/* 118 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 122 */     Player p = (Player)sender;
/*     */     
/* 124 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[2]);
/*     */     
/* 126 */     if (mm == null) {
/* 127 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The MythicMob name you entered is invalid!");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 132 */       MythicMobs.inst().getMobManager().registerActiveMob((AbstractEntity)BukkitAdapter.adapt(p), mm, 1);
/* 133 */     } catch (Exception ex) {
/* 134 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Testing that mob failed! Check console for stack trace.");
/* 135 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   public static void cmdStopTestMob(CommandSender sender, String[] args) {
/* 139 */     if (!(sender instanceof Player)) {
/* 140 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 144 */     Player p = (Player)sender;
/*     */     
/*     */     try {
/* 147 */       MythicMobs.inst().getMobManager().unregisterActiveMob(p.getUniqueId());
/* 148 */     } catch (Exception ex) {
/* 149 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Testing that mob failed! Check console for stack trace.");
/* 150 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\TestCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */