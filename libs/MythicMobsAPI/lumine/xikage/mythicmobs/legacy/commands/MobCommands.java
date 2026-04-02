/*     */ package lumine.xikage.mythicmobs.legacy.commands;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMobStack;
/*     */ import io.lumine.xikage.mythicmobs.mobs.WorldScaling;
/*     */ import io.lumine.xikage.mythicmobs.util.PageableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobCommands
/*     */ {
/*     */   public static void menuCommands(CommandSender sender) {
/*  34 */     sender.sendMessage(MythicMobs.menu_header);
/*  35 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/*  36 */     sender.sendMessage(ChatColor.GOLD + "/mm mobs list <search filter>" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - List all loaded Mythic Mobs");
/*  37 */     sender.sendMessage(ChatColor.GOLD + "/mm mobs info [MobName]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Show info about the given Mythic Mob");
/*  38 */     sender.sendMessage(ChatColor.GOLD + "/mm mobs kill [MobName]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Kill all Mythic Mobs with the given name");
/*  39 */     sender.sendMessage(ChatColor.GOLD + "/mm mobs killall" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Remove ALL Mythic Mobs");
/*  40 */     sender.sendMessage(ChatColor.GOLD + "/mm mobs spawn [MobName] <amount> <world,x,y,z>" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Spawn a mob");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void parseCommands(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/*  45 */     if (args.length == 1) {
/*  46 */       menuCommands(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  50 */     switch (args[1]) { case "spawn":
/*     */       case "s":
/*  52 */         cmdMobSpawn(sender, args, false); return;
/*     */       case "spawnsilent":
/*     */       case "ss":
/*  55 */         cmdMobSpawn(sender, args, true); return;
/*     */       case "list":
/*     */       case "l":
/*  58 */         cmdMobsList(sender, args); return;
/*     */       case "listactive":
/*     */       case "la":
/*  61 */         cmdMobsListActive(sender, args); return;
/*     */       case "kill":
/*     */       case "k":
/*  64 */         cmdMobsKill(sender, args); return;
/*     */       case "killall":
/*     */       case "ka":
/*  67 */         cmdMobsKillAll(sender, args); return;
/*     */       case "info":
/*     */       case "i":
/*  70 */         cmdMobInfo(sender, args);
/*     */         return;
/*     */       case "clean":
/*  73 */         cmdMobsClean(sender, args);
/*     */         return;
/*     */       case "stats":
/*  76 */         cmdMobStats(sender, args);
/*     */         return; }
/*     */     
/*  79 */     menuCommands(sender);
/*     */   }
/*     */   
/*     */   public static void cmdMobSpawn(CommandSender sender, String[] args, boolean silent) {
/*     */     String mob;
/*  84 */     Location loc = null;
/*  85 */     int amount = 1;
/*     */     
/*  87 */     if (args.length < 3) {
/*  88 */       if (!silent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must specify a mob to spawn.");
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*  93 */     int level = 1;
/*     */     
/*  95 */     if (args.length > 3) {
/*     */       try {
/*  97 */         MythicMobs.debug(1, "Parsing mob amount from: " + args[3]);
/*  98 */         amount = Integer.parseInt(args[3]);
/*  99 */       } catch (Exception e) {
/* 100 */         if (!silent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Invalid mob amount supplied: must be an integer.");
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 105 */     if (args.length > 4) {
/*     */ 
/*     */       
/*     */       try {
/* 109 */         String[] part = args[4].split(",");
/* 110 */         World w = Bukkit.getWorld(part[0]);
/* 111 */         float x = Float.parseFloat(part[1]);
/* 112 */         float y = Float.parseFloat(part[2]);
/* 113 */         float z = Float.parseFloat(part[3]);
/*     */         
/* 115 */         MythicMobs.debug(1, "Parsing location using: " + w.toString() + "," + x + "," + y + "," + z);
/*     */         
/* 117 */         if (w != null) {
/* 118 */           loc = new Location(w, x, y, z);
/*     */         }
/* 120 */       } catch (Exception e) {
/* 121 */         if (!silent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Invalid location specified for spawning a mob: location must be in format world,x,y,z");
/*     */         
/*     */         return;
/*     */       } 
/* 125 */     } else if (sender instanceof Player) {
/* 126 */       loc = ((Player)sender).getLocation();
/*     */     } else {
/* 128 */       loc = null;
/*     */     } 
/*     */ 
/*     */     
/* 132 */     if (loc == null) {
/* 133 */       if (!silent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Invalid location specified for spawning a mob: world does not exist.");
/*     */       
/*     */       return;
/*     */     } 
/* 137 */     if (args[2].contains(":")) {
/* 138 */       String[] split = args[2].split(":");
/* 139 */       mob = split[0];
/*     */       try {
/* 141 */         level = Integer.parseInt(split[1]);
/* 142 */       } catch (Exception ex) {
/* 143 */         if (!silent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Invalid mob level supplied: must be an integer."); 
/*     */         return;
/*     */       } 
/*     */     } else {
/* 147 */       mob = args[2];
/* 148 */       level = WorldScaling.getLevelBonus(BukkitAdapter.adapt(loc));
/*     */     } 
/*     */     
/* 151 */     ActiveMob l = null;
/* 152 */     for (int i = 0; i < amount; i++) {
/* 153 */       l = MythicMobs.inst().getMobManager().spawnMob(mob, loc, level);
/*     */     }
/*     */     
/* 156 */     if (l != null)
/* 157 */     { if (!silent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Mobs " + ChatColor.AQUA + args[2] + ChatColor.GREEN + " spawned at your location!");
/*     */        }
/* 159 */     else if (!silent) { sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Mob loaded with the name " + args[2] + "."); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void cmdMobsList(CommandSender sender, String[] args) {
/* 164 */     String s = "";
/* 165 */     int page = 1;
/* 166 */     if (args.length > 2) {
/* 167 */       s = ChatColor.GOLD + "Mythic Mobs found containing " + args[2] + ": ";
/*     */     } else {
/* 169 */       s = ChatColor.GOLD + "Mobs Loaded: ";
/*     */     } 
/*     */     
/* 172 */     List<MythicMob> mobs = new ArrayList<>(MythicMobs.inst().getMobManager().getMobTypes());
/* 173 */     Collections.sort(mobs);
/*     */     
/* 175 */     PageableList<MythicMob> pages = new PageableList(mobs);
/*     */     
/* 177 */     for (MythicMob mm : MythicMobs.inst().getMobManager().getMobTypes()) {
/* 178 */       if (args.length > 2) {
/* 179 */         if (mm.getInternalName().contains(args[2])) {
/* 180 */           String[] parts = mm.getInternalName().split(args[2]);
/* 181 */           if (parts.length == 2)
/* 182 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", "; 
/* 183 */           if (parts.length == 1)
/* 184 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/* 185 */           if (parts.length == 0)
/* 186 */             s = s + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/*     */         }  continue;
/*     */       } 
/* 189 */       s = s + ChatColor.RED + mm.getInternalName() + ChatColor.GRAY + ", ";
/*     */     } 
/*     */     
/* 192 */     for (MythicMobStack el : MythicMobs.inst().getMobManager().getMobStacks()) {
/* 193 */       if (args.length > 2) {
/* 194 */         if (el.getName().contains(args[2])) {
/* 195 */           String[] parts = el.getName().split(args[2]);
/* 196 */           if (parts.length == 2)
/* 197 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", "; 
/* 198 */           if (parts.length == 1)
/* 199 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/* 200 */           if (parts.length == 0)
/* 201 */             s = s + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/*     */         }  continue;
/*     */       } 
/* 204 */       s = s + ChatColor.RED + el.getName() + ChatColor.GRAY + ", ";
/*     */     } 
/*     */     
/* 207 */     sender.sendMessage(s);
/*     */   }
/*     */   
/*     */   public static void cmdMobsFind(CommandSender sender, String[] args) {
/* 211 */     String s = "";
/* 212 */     if (args.length > 2) {
/* 213 */       s = ChatColor.GOLD + "Mythic Mobs found containing " + args[2] + ": ";
/*     */     } else {
/* 215 */       s = ChatColor.GOLD + "Mobs Loaded: ";
/*     */     } 
/*     */     
/* 218 */     for (MythicMob mm : MythicMobs.inst().getMobManager().getMobTypes()) {
/* 219 */       if (args.length > 2) {
/* 220 */         if (mm.getInternalName().contains(args[2])) {
/* 221 */           String[] parts = mm.getInternalName().split(args[2]);
/* 222 */           if (parts.length == 2)
/* 223 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", "; 
/* 224 */           if (parts.length == 1)
/* 225 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/* 226 */           if (parts.length == 0)
/* 227 */             s = s + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/*     */         }  continue;
/*     */       } 
/* 230 */       s = s + ChatColor.RED + mm.getInternalName() + ChatColor.GRAY + ", ";
/*     */     } 
/*     */     
/* 233 */     for (MythicMobStack el : MythicMobs.inst().getMobManager().getMobStacks()) {
/* 234 */       if (args.length > 2) {
/* 235 */         if (el.getName().contains(args[2])) {
/* 236 */           String[] parts = el.getName().split(args[2]);
/* 237 */           if (parts.length == 2)
/* 238 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", "; 
/* 239 */           if (parts.length == 1)
/* 240 */             s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/* 241 */           if (parts.length == 0)
/* 242 */             s = s + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/*     */         }  continue;
/*     */       } 
/* 245 */       s = s + ChatColor.RED + el.getName() + ChatColor.GRAY + ", ";
/*     */     } 
/*     */     
/* 248 */     sender.sendMessage(s);
/*     */   }
/*     */   
/*     */   public static void cmdMobsKill(CommandSender sender, String[] args) {
/* 252 */     String s = ChatColor.GREEN + "Mobs Killed: ";
/*     */     
/* 254 */     for (LivingEntity l : MythicMobs.inst().getMobManager().getAllMythicEntities()) {
/* 255 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/*     */       
/* 257 */       if (am != null && 
/* 258 */         am.getType().getInternalName().contains(args[2])) {
/* 259 */         am.setDespawned();
/* 260 */         MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 261 */         l.remove();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 266 */     for (MythicMob mm : MythicMobs.inst().getMobManager().getMobTypes()) {
/* 267 */       if (mm.getInternalName().contains(args[2])) {
/* 268 */         String[] parts = mm.getInternalName().split(args[2]);
/* 269 */         if (parts.length == 2)
/* 270 */           s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", "; 
/* 271 */         if (parts.length == 1)
/* 272 */           s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/* 273 */         if (parts.length == 0)
/* 274 */           s = s + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/*     */       } 
/*     */     } 
/* 277 */     System.gc();
/*     */     
/* 279 */     sender.sendMessage(s);
/*     */   }
/*     */   
/*     */   public static void cmdMobsKillAll(CommandSender sender, String[] args) {
/* 283 */     int radius = 0;
/* 284 */     boolean literally = false;
/*     */     
/* 286 */     if (args.length > 2) {
/* 287 */       radius = Integer.parseInt(args[2]);
/*     */     }
/* 289 */     if (args.length > 3 && 
/* 290 */       args[3].equals("LITERALLY")) {
/* 291 */       literally = true;
/*     */     }
/*     */     
/* 294 */     int amount = 0;
/*     */     
/* 296 */     if (radius > 0) {
/* 297 */       AbstractPlayer player = BukkitAdapter.adapt((Player)sender);
/* 298 */       for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 299 */         if ((!am.getType().isPersistent() || literally) && 
/* 300 */           am.getLocation().getWorld().equals(player.getWorld()) && 
/* 301 */           am.getLocation().distanceSquared(player.getLocation()) <= Math.pow(radius, 2.0D)) {
/* 302 */           am.setDead();
/* 303 */           MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 304 */           am.getEntity().remove();
/* 305 */           amount++;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 310 */     } else if (literally == true) {
/* 311 */       amount = MythicMobs.inst().getMobManager().removeAllAllMobs();
/*     */     } else {
/* 313 */       amount = MythicMobs.inst().getMobManager().removeAllMobs();
/*     */     } 
/*     */ 
/*     */     
/* 317 */     sender.sendMessage(ChatColor.GREEN + "Removed " + amount + " Mythic Mobs!");
/*     */     
/* 319 */     System.gc();
/*     */   }
/*     */   
/*     */   public static void cmdMobsClean(CommandSender sender, String[] args) {
/* 323 */     System.gc();
/*     */   }
/*     */   public static void cmdMobInfo(CommandSender sender, String[] args) {
/* 326 */     if (MythicMobs.inst().getMobManager().getMythicMob(args[2]) != null) {
/*     */       
/* 328 */       MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[2]);
/* 329 */       sender.sendMessage(ChatColor.GOLD + "Stats for " + ChatColor.BOLD + ChatColor.GRAY + mm.getInternalName() + ":");
/* 330 */       sender.sendMessage(ChatColor.GOLD + "displayName: " + ChatColor.GRAY + mm.getDisplayName() + ChatColor.GRAY + " / " + ChatColor.translateAlternateColorCodes('&', mm.getDisplayName().toString()));
/* 331 */       sender.sendMessage(ChatColor.GOLD + "MobType: " + ChatColor.GRAY + mm.getEntityType());
/* 332 */       sender.sendMessage(ChatColor.GOLD + "Damage: " + ChatColor.GRAY + mm.getBaseDamage() + " (+" + mm.getPerLevelDamage() + " per level)");
/* 333 */       sender.sendMessage(ChatColor.GOLD + "Health: " + ChatColor.GRAY + mm.getHealth() + " (+" + mm.getPerLevelHealth() + " per level)");
/* 334 */       sender.sendMessage(ChatColor.GOLD + "Armor: " + ChatColor.GRAY + mm.getBaseArmor() + " (+" + mm.getPerLevelArmor() + " per level)");
/* 335 */       sender.sendMessage(ChatColor.GOLD + "Power per Level: +" + ChatColor.GRAY + mm.getPerLevelPower());
/* 336 */       sender.sendMessage(ChatColor.GOLD + "Located in File: " + ChatColor.GRAY + mm.getFile());
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 341 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Mob loaded with the name " + args[2] + ".");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdMobsListActive(CommandSender sender, String[] args) {
/* 346 */     if (args.length > 2) {
/* 347 */       MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[2]);
/*     */       
/* 349 */       if (mm != null) {
/*     */         
/* 351 */         int radius = 0;
/*     */         
/* 353 */         if (args.length > 3) {
/* 354 */           radius = Integer.parseInt(args[3]);
/*     */         }
/* 356 */         List<ActiveMob> aml = new ArrayList<>();
/*     */         
/* 358 */         if (radius > 0) {
/* 359 */           for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 360 */             if (am.getType().getInternalName().equals(mm.getInternalName())) {
/* 361 */               aml.add(am);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 366 */         sender.sendMessage(ChatColor.GOLD + "ActiveMobs of type " + ChatColor.BOLD + ChatColor.GRAY + mm.getInternalName() + " (" + aml.size() + " total):");
/* 367 */         for (ActiveMob am : aml) {
/* 368 */           sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.AQUA + "(" + am.getLocation().getBlockX() + "," + am.getLocation().getBlockY() + "," + am.getLocation().getBlockZ() + ChatColor.WHITE + " - " + ChatColor.GRAY + am.getUniqueId());
/*     */         }
/*     */       } else {
/* 371 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Mob loaded with the name " + args[2] + ".");
/*     */       } 
/*     */     } else {
/* 374 */       Map<MythicMob, Integer> mobs = new HashMap<>();
/*     */       
/* 376 */       for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 377 */         if (mobs.containsKey(am.getType())) {
/* 378 */           mobs.put(am.getType(), Integer.valueOf(((Integer)mobs.get(am.getType())).intValue() + 1)); continue;
/*     */         } 
/* 380 */         mobs.put(am.getType(), Integer.valueOf(1));
/*     */       } 
/*     */ 
/*     */       
/* 384 */       int i = 0;
/*     */       
/* 386 */       for (Map.Entry<MythicMob, Integer> entry : (Iterable<Map.Entry<MythicMob, Integer>>)sortByValue(mobs).entrySet()) {
/* 387 */         sender.sendMessage("" + ((MythicMob)entry.getKey()).getDisplayName() + " - " + entry.getValue());
/* 388 */         i++;
/* 389 */         if (i >= 50) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdMobStats(CommandSender sender, String[] args) {
/* 397 */     int amount = 0;
/* 398 */     int alive = 0;
/* 399 */     int dead = 0;
/* 400 */     int valid = 0;
/* 401 */     int invalid = 0;
/* 402 */     int n = 0;
/*     */     
/* 404 */     AbstractPlayer player = BukkitAdapter.adapt((Player)sender);
/* 405 */     for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 406 */       if (am.getLocation().getWorld().equals(player.getWorld())) {
/* 407 */         amount++;
/* 408 */         if (am.getEntity() == null) {
/* 409 */           n++; continue;
/*     */         } 
/* 411 */         if (am.getEntity().isDead()) {
/* 412 */           dead++;
/*     */         } else {
/* 414 */           alive++;
/*     */         } 
/* 416 */         if (am.getEntity().isValid()) {
/* 417 */           valid++; continue;
/*     */         } 
/* 419 */         invalid++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 424 */     sender.sendMessage(ChatColor.GREEN + "Total ActiveMob Instances: " + ChatColor.WHITE + amount);
/* 425 */     sender.sendMessage(ChatColor.GREEN + "Alive: " + ChatColor.WHITE + alive);
/* 426 */     sender.sendMessage(ChatColor.GREEN + "Dead: " + ChatColor.WHITE + dead);
/* 427 */     sender.sendMessage(ChatColor.GREEN + "Valid: " + ChatColor.WHITE + valid);
/* 428 */     sender.sendMessage(ChatColor.GREEN + "Invalid: " + ChatColor.WHITE + invalid);
/* 429 */     sender.sendMessage(ChatColor.GREEN + "Null: " + ChatColor.WHITE + n);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
/* 435 */     Map<K, V> result = new LinkedHashMap<>();
/* 436 */     Stream<Map.Entry<K, V>> st = map.entrySet().stream();
/*     */     
/* 438 */     st.sorted(Comparator.comparing(e -> (Comparable)e.getValue()))
/* 439 */       .forEachOrdered(e -> (Comparable)paramMap.put(e.getKey(), e.getValue()));
/*     */     
/* 441 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\MobCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */