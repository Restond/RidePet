/*     */ package lumine.xikage.mythicmobs.commands.mobs;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.util.StringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListActiveCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public ListActiveCommand(Command<MythicMobs> parent) {
/*  25 */     super(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  30 */     if (args.length > 0) {
/*  31 */       MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[0]);
/*     */       
/*  33 */       if (mm != null) {
/*     */         
/*  35 */         int radius = 0;
/*     */         
/*  37 */         if (args.length > 1) {
/*  38 */           radius = Integer.parseInt(args[1]);
/*     */         }
/*  40 */         List<ActiveMob> aml = new ArrayList<>();
/*     */         
/*  42 */         if (radius > 0) {
/*  43 */           for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/*  44 */             if (am.getType().getInternalName().equals(mm.getInternalName())) {
/*  45 */               aml.add(am);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/*  50 */         sender.sendMessage(ChatColor.GOLD + "ActiveMobs of type " + ChatColor.BOLD + ChatColor.GRAY + mm.getInternalName() + " (" + aml.size() + " total):");
/*  51 */         for (ActiveMob am : aml) {
/*  52 */           sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.AQUA + "(" + am.getLocation().getBlockX() + "," + am.getLocation().getBlockY() + "," + am.getLocation().getBlockZ() + ChatColor.WHITE + " - " + ChatColor.GRAY + am.getUniqueId());
/*     */         }
/*     */       } else {
/*  55 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Mob loaded with the name " + args[0] + ".");
/*     */       } 
/*     */     } else {
/*  58 */       Map<MythicMob, Integer> mobs = new HashMap<>();
/*     */       
/*  60 */       for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/*  61 */         if (mobs.containsKey(am.getType())) {
/*  62 */           mobs.put(am.getType(), Integer.valueOf(((Integer)mobs.get(am.getType())).intValue() + 1)); continue;
/*     */         } 
/*  64 */         mobs.put(am.getType(), Integer.valueOf(1));
/*     */       } 
/*     */ 
/*     */       
/*  68 */       int i = 0;
/*     */       
/*  70 */       for (Map.Entry<MythicMob, Integer> entry : (Iterable<Map.Entry<MythicMob, Integer>>)sortByValue(mobs).entrySet()) {
/*  71 */         sender.sendMessage("" + ((MythicMob)entry.getKey()).getDisplayName() + " - " + entry.getValue());
/*  72 */         i++;
/*  73 */         if (i >= 50)
/*     */           break; 
/*     */       } 
/*     */     } 
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  82 */     if (args.length == 1) {
/*  83 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getMobManager().getMobNames(), new ArrayList());
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/*  90 */     return "mythicmobs.command.mobs.listactive";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 100 */     return "listactive";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 105 */     return new String[] { "la" };
/*     */   }
/*     */   
/*     */   public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
/* 109 */     Map<K, V> result = new LinkedHashMap<>();
/* 110 */     Stream<Map.Entry<K, V>> st = map.entrySet().stream();
/*     */     
/* 112 */     st.sorted(Comparator.comparing(e -> (Comparable)e.getValue()))
/* 113 */       .forEachOrdered(e -> (Comparable)paramMap.put(e.getKey(), e.getValue()));
/*     */     
/* 115 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\ListActiveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */