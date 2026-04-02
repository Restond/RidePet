/*     */ package lumine.xikage.mythicmobs.legacy.commands;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DebugCommands
/*     */ {
/*     */   public static void menuCommands(CommandSender sender) {
/*  20 */     sender.sendMessage(MythicMobs.menu_header);
/*  21 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/*  22 */     sender.sendMessage(ChatColor.YELLOW + "/mm debug [level]" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Sets debug reporting. Can be between 0 and 4 (0=off,4=too much info).");
/*  23 */     sender.sendMessage(ChatColor.YELLOW + "/mm debug mode [true/false]" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Sets debug mode on or off. See plugin manual for details).");
/*  24 */     sender.sendMessage(ChatColor.YELLOW + "/mm debug timings" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Show basic timing information (timings must be on first).");
/*  25 */     sender.sendMessage(ChatColor.YELLOW + "/mm debug timings [on/off]" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Turns timings on/off.");
/*  26 */     sender.sendMessage(ChatColor.YELLOW + "/mm debug timings spawners" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Shows spawner-specific timings in the console.");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void parseCommands(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/*  31 */     if (args.length == 1) {
/*  32 */       menuCommands(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  36 */     switch (args[1].toLowerCase()) { case "mode":
/*     */       case "m":
/*  38 */         cmdDebugMode(sender, args);
/*     */         return;
/*     */       case "timings":
/*  41 */         cmdTimings(sender, args);
/*     */         return;
/*     */       case "spawns":
/*  44 */         cmdSpawns(sender, args);
/*     */         return; }
/*     */     
/*  47 */     cmdDebug(sender, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void cmdDebug(CommandSender sender, String[] args) {
/*     */     try {
/*  53 */       ConfigManager.debugLevel = Integer.parseInt(args[1]);
/*  54 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Debug level has been set to " + args[1]);
/*  55 */     } catch (Exception e) {
/*  56 */       menuCommands(sender);
/*     */     } 
/*     */   }
/*     */   public static void cmdDebugMode(CommandSender sender, String[] args) {
/*     */     try {
/*  61 */       ConfigManager.debugMode = Boolean.parseBoolean(args[2]);
/*  62 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Debug level has been set to " + args[1]);
/*  63 */     } catch (Exception e) {
/*  64 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + " Error: Debug Mode must be true or false.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdTimings(CommandSender sender, String[] args) {
/*  69 */     if (args.length > 2) {
/*  70 */       if ("ON".equals(args[2].toUpperCase())) {
/*  71 */         MythicMobs.inst().getTimingsHandler().enable();
/*  72 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings have been enabled.");
/*  73 */       } else if ("OFF".equals(args[2].toUpperCase())) {
/*  74 */         MythicMobs.inst().getTimingsHandler().disable();
/*  75 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings have been disabled.");
/*  76 */       } else if ("RESET".equals(args[2].toUpperCase())) {
/*  77 */         MythicMobs.inst().getTimingsHandler().reset();
/*  78 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings have been reset.");
/*  79 */       } else if ("SPAWNERS".equals(args[2].toUpperCase())) {
/*  80 */         double overallMS = MythicMobs.inst().getTimingsHandler().getRunTimeMillis();
/*  81 */         double totalMS = (MythicMobs.inst().getTimingsHandler().getTimeTotal() / 1000000L);
/*  82 */         double totalSpawners = (MythicMobs.inst().getTimingsHandler().getTimeSpawners() / 1000000L);
/*     */ 
/*     */         
/*  85 */         MythicMobs.debug(0, " Spawner Timings");
/*  86 */         MythicMobs.debug(0, "Time Analyzed: " + overallMS);
/*  87 */         MythicMobs.debug(0, "Clock Time: " + (Math.round(totalMS * 100.0D) / 100L) + "ms (" + (Math.round(totalMS / overallMS * 10000.0D) / 100L) + "%)");
/*  88 */         MythicMobs.debug(0, "- CSpawners: " + (Math.round(totalSpawners * 100.0D) / 100L) + "ms (" + (Math.round(totalSpawners / totalMS * 10000.0D) / 100L) + "% Clock, " + (Math.round(totalSpawners / overallMS * 10000.0D) / 100L) + "% Overall)");
/*  89 */         MythicMobs.debug(0, "-- All Spawners:");
/*     */         
/*  91 */         HashMap<String, Long> map = (HashMap<String, Long>)MythicMobs.inst().getTimingsHandler().getAllSpawnerTimes().clone();
/*  92 */         map = sortHashMapByValuesD(map);
/*     */         
/*  94 */         for (String s : map.keySet()) {
/*  95 */           long tM = ((Long)map.get(s)).longValue() / 1000000L;
/*  96 */           long tMTS = Math.round(tM / totalSpawners * 10000.0D) / 100L;
/*  97 */           long tMC = Math.round(tM / totalMS * 10000.0D) / 100L;
/*  98 */           long tMO = Math.round(tM / overallMS * 10000.0D) / 100L;
/*  99 */           MythicMobs.debug(0, String.format("** %32s %8d ms - %4d pS %4d pC %4d pOverall)", new Object[] { s, Long.valueOf(tM), Long.valueOf(tMTS), Long.valueOf(tMC), Long.valueOf(tMO) }));
/*     */         } 
/* 101 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Spawner timings have been pasted to the console.");
/* 102 */       } else if ("SKILLS".equals(args[2].toUpperCase())) {
/* 103 */         double overallMS = MythicMobs.inst().getTimingsHandler().getRunTimeMillis();
/* 104 */         double totalMS = (MythicMobs.inst().getTimingsHandler().getTimeTotal() / 1000000L);
/* 105 */         double totalSkills = (MythicMobs.inst().getTimingsHandler().getTimeSkills() / 1000000L);
/*     */         
/* 107 */         MythicMobs.debug(0, " AbstractSkill Timings");
/* 108 */         MythicMobs.debug(0, "Time Analyzed: " + overallMS);
/* 109 */         MythicMobs.debug(0, "Clock Time: " + (Math.round(totalMS * 100.0D) / 100L) + "ms (" + (Math.round(totalMS / overallMS * 10000.0D) / 100L) + "%)");
/* 110 */         MythicMobs.debug(0, "- TmrSkills: " + (Math.round(totalSkills * 100.0D) / 100L) + "ms (" + (Math.round(totalSkills / totalMS * 10000.0D) / 100L) + "% Clock, " + (Math.round(totalSkills / overallMS * 10000.0D) / 100L) + "% Overall)");
/* 111 */         MythicMobs.debug(0, "-- All Timer Skills:");
/*     */         
/* 113 */         HashMap<String, Long> map = (HashMap<String, Long>)MythicMobs.inst().getTimingsHandler().getAllSkillTimes().clone();
/* 114 */         map = sortHashMapByValuesD(map);
/*     */         
/* 116 */         for (String s : map.keySet()) {
/* 117 */           long tM = ((Long)map.get(s)).longValue() / 1000000L;
/* 118 */           long tMTS = Math.round(tM / totalSkills * 10000.0D) / 100L;
/* 119 */           long tMC = Math.round(tM / totalMS * 10000.0D) / 100L;
/* 120 */           long tMO = Math.round(tM / overallMS * 10000.0D) / 100L;
/* 121 */           MythicMobs.debug(0, String.format("** %64s %8d ms - %4d pS %4d pC %4d pOverall)", new Object[] { s, Long.valueOf(tM), Long.valueOf(tMTS), Long.valueOf(tMC), Long.valueOf(tMO) }));
/*     */         } 
/* 123 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " AbstractSkill timings have been pasted to the console.");
/*     */       } else {
/* 125 */         menuCommands(sender);
/*     */       } 
/*     */     } else {
/* 128 */       if (!MythicMobs.inst().getTimingsHandler().isEnabled()) {
/* 129 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings must be enabled first.");
/*     */         
/*     */         return;
/*     */       } 
/* 133 */       double overallMS = MythicMobs.inst().getTimingsHandler().getRunTimeMillis();
/* 134 */       double totalMS = (MythicMobs.inst().getTimingsHandler().getTimeTotal() / 1000000L);
/* 135 */       double totalSkills = (MythicMobs.inst().getTimingsHandler().getTimeSkills() / 1000000L);
/* 136 */       double totalRandomSpawners = (MythicMobs.inst().getTimingsHandler().getTimeRandomSpawners() / 1000000L);
/* 137 */       double totalScanner = (MythicMobs.inst().getTimingsHandler().getTimeScanner() / 1000000L);
/* 138 */       double totalSpawners = (MythicMobs.inst().getTimingsHandler().getTimeSpawners() / 1000000L);
/* 139 */       double totalThreatT = (MythicMobs.inst().getTimingsHandler().getTimeThreatTables() / 1000000L);
/* 140 */       double overhead = totalMS - totalSkills - totalSpawners - totalThreatT - totalScanner - totalRandomSpawners;
/*     */       
/* 142 */       sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + " Clock Timings");
/* 143 */       sender.sendMessage(ChatColor.AQUA + "Time Analyzed: " + overallMS);
/* 144 */       sender.sendMessage(ChatColor.AQUA + "Clock Time: " + ChatColor.GREEN + (Math.round(totalMS * 100.0D) / 100.0D) + "ms (" + (Math.round(totalMS / overallMS * 10000.0D) / 100.0D) + "%)");
/* 145 */       sender.sendMessage(ChatColor.AQUA + "- TmrSkills: " + ChatColor.GREEN + (Math.round(totalSkills * 100.0D) / 100.0D) + "ms (" + (Math.round(totalSkills / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalSkills / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 146 */       sender.sendMessage(ChatColor.AQUA + "- RndSpawns: " + ChatColor.GREEN + (Math.round(totalRandomSpawners * 100.0D) / 100.0D) + "ms (" + (Math.round(totalRandomSpawners / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalRandomSpawners / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 147 */       sender.sendMessage(ChatColor.AQUA + "- CSpawners: " + ChatColor.GREEN + (Math.round(totalSpawners * 100.0D) / 100.0D) + "ms (" + (Math.round(totalSpawners / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalSpawners / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 148 */       sender.sendMessage(ChatColor.AQUA + "- MobScannr: " + ChatColor.GREEN + (Math.round(totalScanner * 100.0D) / 100.0D) + "ms (" + (Math.round(totalScanner / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalScanner / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 149 */       sender.sendMessage(ChatColor.AQUA + "- ThrtTables: " + ChatColor.GREEN + (Math.round(totalThreatT * 100.0D) / 100.0D) + "ms (" + (Math.round(totalThreatT / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalThreatT / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 150 */       sender.sendMessage(ChatColor.AQUA + "- Overhead+: " + ChatColor.GREEN + (Math.round(overhead * 100.0D) / 100.0D) + "ms (" + (Math.round(overhead / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(overhead / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdSpawns(CommandSender sender, String[] args) {
/* 155 */     if (args.length > 2) {
/* 156 */       if ("ON".equals(args[2].toUpperCase())) {
/* 157 */         ConfigManager.debugSpawners = false;
/* 158 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " All spawning has been enabled.");
/* 159 */       } else if ("OFF".equals(args[2].toUpperCase())) {
/* 160 */         ConfigManager.debugSpawners = true;
/* 161 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " All spawning has been disabled.");
/*     */       } else {
/* 163 */         menuCommands(sender);
/*     */       } 
/*     */     } else {
/* 166 */       menuCommands(sender);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
/* 172 */     List<Comparable> mapKeys = new ArrayList(passedMap.keySet());
/* 173 */     List<Comparable> mapValues = new ArrayList(passedMap.values());
/* 174 */     Collections.sort(mapValues);
/* 175 */     Collections.sort(mapKeys);
/*     */     
/* 177 */     LinkedHashMap<Object, Object> sortedMap = new LinkedHashMap<>();
/*     */     
/* 179 */     Iterator<Comparable> valueIt = mapValues.iterator();
/* 180 */     while (valueIt.hasNext()) {
/* 181 */       Object val = valueIt.next();
/* 182 */       Iterator<Comparable> keyIt = mapKeys.iterator();
/*     */       
/* 184 */       while (keyIt.hasNext()) {
/* 185 */         Object key = keyIt.next();
/* 186 */         String comp1 = passedMap.get(key).toString();
/* 187 */         String comp2 = val.toString();
/*     */         
/* 189 */         if (comp1.equals(comp2)) {
/* 190 */           passedMap.remove(key);
/* 191 */           mapKeys.remove(key);
/* 192 */           sortedMap.put(key, val);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 199 */     return sortedMap;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\DebugCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */