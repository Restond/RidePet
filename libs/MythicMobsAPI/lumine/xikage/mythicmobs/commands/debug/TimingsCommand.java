/*     */ package lumine.xikage.mythicmobs.commands.debug;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public class TimingsCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public TimingsCommand(Command<MythicMobs> parent) {
/*  18 */     super(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  23 */     if (args.length > 0) {
/*  24 */       if ("ON".equals(args[0].toUpperCase())) {
/*  25 */         MythicMobs.inst().getTimingsHandler().enable();
/*  26 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings have been enabled.");
/*  27 */       } else if ("OFF".equals(args[0].toUpperCase())) {
/*  28 */         MythicMobs.inst().getTimingsHandler().disable();
/*  29 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings have been disabled.");
/*  30 */       } else if ("RESET".equals(args[0].toUpperCase())) {
/*  31 */         MythicMobs.inst().getTimingsHandler().reset();
/*  32 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings have been reset.");
/*  33 */       } else if ("SPAWNERS".equals(args[0].toUpperCase())) {
/*  34 */         double overallMS = MythicMobs.inst().getTimingsHandler().getRunTimeMillis();
/*  35 */         double totalMS = (MythicMobs.inst().getTimingsHandler().getTimeTotal() / 1000000L);
/*  36 */         double totalSpawners = (MythicMobs.inst().getTimingsHandler().getTimeSpawners() / 1000000L);
/*     */ 
/*     */         
/*  39 */         MythicMobs.debug(0, " Spawner Timings");
/*  40 */         MythicMobs.debug(0, "Time Analyzed: " + overallMS);
/*  41 */         MythicMobs.debug(0, "Clock Time: " + (Math.round(totalMS * 100.0D) / 100L) + "ms (" + (Math.round(totalMS / overallMS * 10000.0D) / 100L) + "%)");
/*  42 */         MythicMobs.debug(0, "- CSpawners: " + (Math.round(totalSpawners * 100.0D) / 100L) + "ms (" + (Math.round(totalSpawners / totalMS * 10000.0D) / 100L) + "% Clock, " + (Math.round(totalSpawners / overallMS * 10000.0D) / 100L) + "% Overall)");
/*  43 */         MythicMobs.debug(0, "-- All Spawners:");
/*     */         
/*  45 */         HashMap<String, Long> map = (HashMap<String, Long>)MythicMobs.inst().getTimingsHandler().getAllSpawnerTimes().clone();
/*  46 */         map = sortHashMapByValuesD(map);
/*     */         
/*  48 */         for (String s : map.keySet()) {
/*  49 */           long tM = ((Long)map.get(s)).longValue() / 1000000L;
/*  50 */           long tMTS = Math.round(tM / totalSpawners * 10000.0D) / 100L;
/*  51 */           long tMC = Math.round(tM / totalMS * 10000.0D) / 100L;
/*  52 */           long tMO = Math.round(tM / overallMS * 10000.0D) / 100L;
/*  53 */           MythicMobs.debug(0, String.format("** %32s %8d ms - %4d pS %4d pC %4d pOverall)", new Object[] { s, Long.valueOf(tM), Long.valueOf(tMTS), Long.valueOf(tMC), Long.valueOf(tMO) }));
/*     */         } 
/*  55 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Spawner timings have been pasted to the console.");
/*  56 */       } else if ("SKILLS".equals(args[0].toUpperCase())) {
/*  57 */         double overallMS = MythicMobs.inst().getTimingsHandler().getRunTimeMillis();
/*  58 */         double totalMS = (MythicMobs.inst().getTimingsHandler().getTimeTotal() / 1000000L);
/*  59 */         double totalSkills = (MythicMobs.inst().getTimingsHandler().getTimeSkills() / 1000000L);
/*     */         
/*  61 */         MythicMobs.debug(0, " AbstractSkill Timings");
/*  62 */         MythicMobs.debug(0, "Time Analyzed: " + overallMS);
/*  63 */         MythicMobs.debug(0, "Clock Time: " + (Math.round(totalMS * 100.0D) / 100L) + "ms (" + (Math.round(totalMS / overallMS * 10000.0D) / 100L) + "%)");
/*  64 */         MythicMobs.debug(0, "- TmrSkills: " + (Math.round(totalSkills * 100.0D) / 100L) + "ms (" + (Math.round(totalSkills / totalMS * 10000.0D) / 100L) + "% Clock, " + (Math.round(totalSkills / overallMS * 10000.0D) / 100L) + "% Overall)");
/*  65 */         MythicMobs.debug(0, "-- All Timer Skills:");
/*     */         
/*  67 */         HashMap<String, Long> map = (HashMap<String, Long>)MythicMobs.inst().getTimingsHandler().getAllSkillTimes().clone();
/*  68 */         map = sortHashMapByValuesD(map);
/*     */         
/*  70 */         for (String s : map.keySet()) {
/*  71 */           long tM = ((Long)map.get(s)).longValue() / 1000000L;
/*  72 */           long tMTS = Math.round(tM / totalSkills * 10000.0D) / 100L;
/*  73 */           long tMC = Math.round(tM / totalMS * 10000.0D) / 100L;
/*  74 */           long tMO = Math.round(tM / overallMS * 10000.0D) / 100L;
/*  75 */           MythicMobs.debug(0, String.format("** %64s %8d ms - %4d pS %4d pC %4d pOverall)", new Object[] { s, Long.valueOf(tM), Long.valueOf(tMTS), Long.valueOf(tMC), Long.valueOf(tMO) }));
/*     */         } 
/*  77 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " AbstractSkill timings have been pasted to the console.");
/*     */       } else {
/*  79 */         return false;
/*     */       } 
/*     */     } else {
/*  82 */       if (!MythicMobs.inst().getTimingsHandler().isEnabled()) {
/*  83 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Timings must be enabled first.");
/*  84 */         return true;
/*     */       } 
/*     */       
/*  87 */       double overallMS = MythicMobs.inst().getTimingsHandler().getRunTimeMillis();
/*  88 */       double totalMS = (MythicMobs.inst().getTimingsHandler().getTimeTotal() / 1000000L);
/*  89 */       double totalSkills = (MythicMobs.inst().getTimingsHandler().getTimeSkills() / 1000000L);
/*  90 */       double totalRandomSpawners = (MythicMobs.inst().getTimingsHandler().getTimeRandomSpawners() / 1000000L);
/*  91 */       double totalRandomGenerators = (MythicMobs.inst().getTimingsHandler().getTimeRandomGenerators() / 1000000L);
/*  92 */       double totalScanner = (MythicMobs.inst().getTimingsHandler().getTimeScanner() / 1000000L);
/*  93 */       double totalSpawners = (MythicMobs.inst().getTimingsHandler().getTimeSpawners() / 1000000L);
/*  94 */       double totalThreatT = (MythicMobs.inst().getTimingsHandler().getTimeThreatTables() / 1000000L);
/*  95 */       double overhead = totalMS - totalSkills - totalSpawners - totalThreatT - totalScanner - totalRandomSpawners;
/*     */       
/*  97 */       sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + " Clock Timings");
/*  98 */       sender.sendMessage(ChatColor.AQUA + "Time Analyzed: " + overallMS);
/*  99 */       sender.sendMessage(ChatColor.AQUA + "Clock Time: " + ChatColor.GREEN + (Math.round(totalMS * 100.0D) / 100.0D) + "ms (" + (Math.round(totalMS / overallMS * 10000.0D) / 100.0D) + "%)");
/* 100 */       sender.sendMessage(ChatColor.AQUA + "- Skills & Conditions: " + ChatColor.GREEN + (Math.round(totalSkills * 100.0D) / 100.0D) + "ms (" + (Math.round(totalSkills / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalSkills / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 101 */       sender.sendMessage(ChatColor.AQUA + "- RndSpawns(Replaced): " + ChatColor.GREEN + (Math.round(totalRandomSpawners * 100.0D) / 100.0D) + "ms (" + (Math.round(totalRandomSpawners / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalRandomSpawners / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 102 */       sender.sendMessage(ChatColor.AQUA + "- RndSpawns(Generate): " + ChatColor.GREEN + (Math.round(totalRandomGenerators * 100.0D) / 100.0D) + "ms (" + (Math.round(totalRandomGenerators / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalRandomGenerators / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 103 */       sender.sendMessage(ChatColor.AQUA + "- Static Mob Spawners: " + ChatColor.GREEN + (Math.round(totalSpawners * 100.0D) / 100.0D) + "ms (" + (Math.round(totalSpawners / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalSpawners / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 104 */       sender.sendMessage(ChatColor.AQUA + "- World Scanner: " + ChatColor.GREEN + (Math.round(totalScanner * 100.0D) / 100.0D) + "ms (" + (Math.round(totalScanner / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalScanner / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 105 */       sender.sendMessage(ChatColor.AQUA + "- Threat Tables: " + ChatColor.GREEN + (Math.round(totalThreatT * 100.0D) / 100.0D) + "ms (" + (Math.round(totalThreatT / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(totalThreatT / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/* 106 */       sender.sendMessage(ChatColor.AQUA + "- Overhead+: " + ChatColor.GREEN + (Math.round(overhead * 100.0D) / 100.0D) + "ms (" + (Math.round(overhead / totalMS * 10000.0D) / 100.0D) + "% Clock, " + (Math.round(overhead / overallMS * 10000.0D) / 100.0D) + "% Overall)");
/*     */     } 
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 118 */     return "mythicmobs.command.debug.timings";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 128 */     return "timings";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 133 */     return new String[] { "t" };
/*     */   }
/*     */ 
/*     */   
/*     */   private static LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
/* 138 */     List<Comparable> mapKeys = new ArrayList(passedMap.keySet());
/* 139 */     List<Comparable> mapValues = new ArrayList(passedMap.values());
/* 140 */     Collections.sort(mapValues);
/* 141 */     Collections.sort(mapKeys);
/*     */     
/* 143 */     LinkedHashMap<Object, Object> sortedMap = new LinkedHashMap<>();
/*     */     
/* 145 */     Iterator<Comparable> valueIt = mapValues.iterator();
/* 146 */     while (valueIt.hasNext()) {
/* 147 */       Object val = valueIt.next();
/* 148 */       Iterator<Comparable> keyIt = mapKeys.iterator();
/*     */       
/* 150 */       while (keyIt.hasNext()) {
/* 151 */         Object key = keyIt.next();
/* 152 */         String comp1 = passedMap.get(key).toString();
/* 153 */         String comp2 = val.toString();
/*     */         
/* 155 */         if (comp1.equals(comp2)) {
/* 156 */           passedMap.remove(key);
/* 157 */           mapKeys.remove(key);
/* 158 */           sortedMap.put(key, val);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 165 */     return sortedMap;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\debug\TimingsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */