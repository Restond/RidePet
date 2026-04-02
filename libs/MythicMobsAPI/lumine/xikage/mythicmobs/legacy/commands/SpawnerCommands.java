/*     */ package lumine.xikage.mythicmobs.legacy.commands;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.legacy.commands.CommandHandler;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpawnerCommands
/*     */ {
/*     */   public static void menuCommands(CommandSender sender) {
/*  25 */     sender.sendMessage(MythicMobs.menu_header);
/*  26 */     sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "* Page 1 of 2");
/*  27 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/*  28 */     sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Commands for creating spawners...");
/*  29 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners create [name] [mob_name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Turns the block you're looking at into a mob spawner.");
/*  30 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners delete [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Deletes the mob spawner.");
/*  31 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners info [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Turns the block you're looking at into a mob spawner.");
/*     */     
/*  33 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners listnear <radius>" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - List all loaded Mythic Spawners");
/*  34 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners set [name] [attribute] [value]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Set the attribute of a Mythic Spawner.");
/*  35 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners spawn [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Activates a Mythic Spawner.");
/*  36 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners addcondition [name] [condition] [value]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Adds a condition to a spawner.");
/*  37 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners removecondition [name] [condition]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Removes a condition from a spawner.");
/*     */   }
/*     */   
/*     */   public static void menuCommands2(CommandSender sender) {
/*  41 */     sender.sendMessage(MythicMobs.menu_header);
/*  42 */     sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "* Page 2 of 2");
/*  43 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/*  44 */     sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Commands for moving spawners...");
/*  45 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners move [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Moves the spawner to the location you are looking at.");
/*  46 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners copy [name] [new_name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Turns the block you're looking at into a copy of a mob spawner.");
/*  47 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners cut [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Cuts the given spawners and saves them to the clipboard.");
/*  48 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners paste [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Pastes spawners in your clipboard based on your relative location.");
/*  49 */     sender.sendMessage(ChatColor.GOLD + "/mm spawners undo" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Undo the last clipboard-related change you've done (clipboard must still have spawners in it for this to work)");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void parseCommands(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/*  55 */     if (args.length == 1) {
/*  56 */       menuCommands(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  60 */     switch (args[1].toLowerCase()) {
/*     */       case "1":
/*  62 */         menuCommands(sender);
/*     */         return;
/*     */       case "2":
/*  65 */         menuCommands2(sender); return;
/*     */       case "create":
/*     */       case "c":
/*  68 */         cmdSpawnerCreate(sender, args); return;
/*     */       case "copy": case "cp": case "duplicate": case "replicate":
/*     */       case "dp":
/*  71 */         cmdSpawnerDuplicate(sender, args);
/*     */         return;
/*     */       case "cut":
/*  74 */         cmdSpawnerCut(sender, args); return;
/*     */       case "paste":
/*     */       case "p":
/*  77 */         cmdSpawnerPaste(sender, args);
/*     */         return;
/*     */       case "undo":
/*  80 */         cmdSpawnerUndo(sender, args); return;
/*     */       case "move":
/*     */       case "m":
/*  83 */         cmdSpawnerMove(sender, args); return;
/*     */       case "set":
/*     */       case "s":
/*  86 */         cmdSpawnerSet(sender, args); return;
/*     */       case "listnear":
/*     */       case "ln":
/*  89 */         cmdSpawnerListNear(sender, args); return;
/*     */       case "remove":
/*     */       case "delete":
/*  92 */         cmdSpawnerRemove(sender, args); return;
/*     */       case "activate": case "spawn":
/*     */       case "a":
/*  95 */         cmdSpawnerActivate(sender, args); return;
/*     */       case "info":
/*     */       case "i":
/*  98 */         cmdSpawnerInfo(sender, args); return;
/*     */       case "addcondition":
/*     */       case "ac":
/* 101 */         cmdConditionAdd(sender, args); return;
/*     */       case "removecondition":
/*     */       case "rc":
/* 104 */         cmdConditionRemove(sender, args);
/*     */         return;
/*     */       case "resettimers":
/* 107 */         cmdResetTimers(sender, args);
/*     */         return;
/*     */     } 
/* 110 */     menuCommands(sender);
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerCreate(CommandSender sender, String[] args) {
/*     */     String mobName;
/* 115 */     if (!(sender instanceof Player)) {
/* 116 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 120 */     if (args.length < 4) {
/* 121 */       sender.sendMessage(ChatColor.RED + "Command Format: " + ChatColor.AQUA + "/mm spawners create [name] [mob_name]");
/*     */       
/*     */       return;
/*     */     } 
/* 125 */     String name = args[2];
/* 126 */     if (name == null) {
/* 127 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a name for the spawner.");
/*     */       
/*     */       return;
/*     */     } 
/* 131 */     Optional<MythicSpawner> maybeSpawner = MythicMobs.inst().getSpawnerManager().getSpawnerByHashcode(name.hashCode());
/* 132 */     if (maybeSpawner.isPresent()) {
/* 133 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Spawner name must resolve to a unique hashcode.");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 138 */     int level = 1;
/*     */     
/* 140 */     if (args[3].contains(":")) {
/* 141 */       String[] split = args[3].split(":");
/* 142 */       mobName = split[0];
/*     */       try {
/* 144 */         level = Integer.parseInt(split[1]);
/* 145 */       } catch (Exception ex) {
/* 146 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Invalid mob level supplied: must be an integer.");
/*     */         return;
/*     */       } 
/*     */     } else {
/* 150 */       mobName = args[3];
/*     */     } 
/*     */     
/* 153 */     if (mobName == null) {
/* 154 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a Mythic Mob for the spawner to spawn.");
/*     */       return;
/*     */     } 
/* 157 */     if (MythicMobs.inst().getMobManager().getMythicMob(args[3]) == null) {
/* 158 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Mob. That one was not found!");
/*     */       return;
/*     */     } 
/* 161 */     Player p = (Player)sender;
/*     */     
/* 163 */     Location location = p.getTargetBlock((HashSet)null, 10).getLocation();
/* 164 */     if (location == null) {
/* 165 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block to place the spawner!");
/*     */       
/*     */       return;
/*     */     } 
/* 169 */     MythicSpawner ms = MythicMobs.inst().getSpawnerManager().createSpawner(name, location, mobName);
/*     */     
/* 171 */     if (ms != null) {
/* 172 */       location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
/* 173 */       location.getWorld().playEffect(location, Effect.EXTINGUISH, 0);
/* 174 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "New spawner was created successfully!");
/*     */       
/* 176 */       if (level > 1);
/*     */     }
/*     */     else {
/*     */       
/* 180 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Spawner creation failed: spawner name is probably already taken!");
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerDuplicate(CommandSender sender, String[] args) {
/* 186 */     if (!(sender instanceof Player)) {
/* 187 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 191 */     if (args.length < 3) {
/* 192 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a spawner to copy.");
/*     */       return;
/*     */     } 
/* 195 */     String name = args[2];
/* 196 */     if (MythicMobs.inst().getSpawnerManager().getSpawnerByName(name) == null) {
/* 197 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner to copy!");
/*     */       
/*     */       return;
/*     */     } 
/* 201 */     if (args.length < 4) {
/* 202 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a name for the new spawner.");
/*     */       return;
/*     */     } 
/* 205 */     String nameNew = args[3];
/* 206 */     MythicSpawner msNew = MythicMobs.inst().getSpawnerManager().getSpawnerByName(args[3]);
/* 207 */     if (msNew != null) {
/* 208 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The name you entered for a new spawner is already in use!");
/*     */       
/*     */       return;
/*     */     } 
/* 212 */     Player p = (Player)sender;
/*     */     
/* 214 */     Location location = p.getTargetBlock((HashSet)null, 10).getLocation();
/* 215 */     if (location == null) {
/* 216 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block to place the spawner!");
/*     */       
/*     */       return;
/*     */     } 
/* 220 */     if (MythicMobs.inst().getSpawnerManager().copySpawner(name, nameNew, BukkitAdapter.adapt(location))) {
/* 221 */       location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
/* 222 */       location.getWorld().playEffect(location, Effect.EXTINGUISH, 0);
/* 223 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "New spawner was cloned successfully!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerMove(CommandSender sender, String[] args) {
/* 228 */     if (!(sender instanceof Player)) {
/* 229 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 233 */     if (args.length < 3) {
/* 234 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a spawner to move.");
/*     */       return;
/*     */     } 
/* 237 */     String name = args[2];
/* 238 */     if (MythicMobs.inst().getSpawnerManager().getSpawnerByName(name) == null) {
/* 239 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner to move!");
/*     */       
/*     */       return;
/*     */     } 
/* 243 */     Player p = (Player)sender;
/*     */     
/* 245 */     Location location = p.getTargetBlock((HashSet)null, 10).getLocation();
/* 246 */     if (location == null) {
/* 247 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block to move the spawner!");
/*     */       
/*     */       return;
/*     */     } 
/* 251 */     if (MythicMobs.inst().getSpawnerManager().moveSpawner(name, BukkitAdapter.adapt(location))) {
/* 252 */       location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
/* 253 */       location.getWorld().playEffect(location, Effect.EXTINGUISH, 0);
/* 254 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner " + name + " was moved successfully!");
/*     */     } 
/*     */   }
/*     */   public static void cmdSpawnerSet(CommandSender sender, String[] args) {
/* 258 */     if (args.length < 5) {
/* 259 */       menuSet(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 263 */     String name = args[2];
/* 264 */     String option = args[3];
/* 265 */     String value = args[4];
/*     */     
/* 267 */     if (name.startsWith("g:")) {
/* 268 */       String group = name.substring(2);
/*     */       
/* 270 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*     */       
/* 272 */       for (MythicSpawner ms : msl) {
/* 273 */         if (!MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/* 274 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The attribute or value you entered was invalid!");
/*     */           return;
/*     */         } 
/*     */       } 
/* 278 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + ChatColor.GREEN + " on group " + group);
/* 279 */     } else if (name.equals("*")) {
/* 280 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 281 */         if (!MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/* 282 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           return;
/*     */         } 
/*     */       } 
/* 286 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + ChatColor.GREEN + " on all spawners!");
/* 287 */     } else if (name.contains("*") || name.contains("?")) {
/* 288 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 289 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?")) && 
/* 290 */           !MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/* 291 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 296 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + ChatColor.GREEN + " on spawners matching pattern " + name + "!");
/*     */     } else {
/* 298 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */       
/* 300 */       if (ms == null) {
/* 301 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/*     */         
/*     */         return;
/*     */       } 
/* 305 */       if (!MythicMobs.inst().getSpawnerManager().setSpawnerAttribute(ms, option, value)) {
/* 306 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The attribute or value you entered was invalid!");
/*     */       } else {
/* 308 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner attribute " + ChatColor.AQUA + option + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + " on spawner " + name);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void cmdSpawnerListNear(CommandSender sender, String[] args) {
/* 313 */     if (!(sender instanceof Player)) {
/* 314 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 318 */     double radius = 32.0D;
/* 319 */     if (args.length >= 3) {
/* 320 */       radius = Double.parseDouble(args[2]);
/*     */     }
/*     */     
/* 323 */     sender.sendMessage(MythicMobs.menu_header);
/* 324 */     sender.sendMessage(ChatColor.GOLD + "Spawners within " + radius + " blocks of you:");
/* 325 */     for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 326 */       if (ms.getLocation().getWorld() != null && 
/* 327 */         ms.getLocation().getWorld().equals(BukkitAdapter.adapt(((Player)sender).getLocation()).getWorld()) && 
/* 328 */         ms.distanceTo(BukkitAdapter.adapt((Player)sender).getLocation()) <= radius) {
/* 329 */         sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + ms.getName() + ChatColor.AQUA + " (" + (int)ms.distanceTo(BukkitAdapter.adapt((Player)sender).getLocation()) + " blocks away)");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerRemove(CommandSender sender, String[] args) {
/* 335 */     if (!(sender instanceof Player)) {
/* 336 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 340 */     if (args.length < 3) {
/* 341 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a mob spawner to remove!");
/*     */       return;
/*     */     } 
/* 344 */     String name = args[2];
/*     */     
/* 346 */     MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */     
/* 348 */     if (ms == null) {
/* 349 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The spawner entered does not exist!");
/*     */       
/*     */       return;
/*     */     } 
/* 353 */     if (MythicMobs.inst().getSpawnerManager().removeSpawner(ms)) {
/* 354 */       BukkitAdapter.adapt(ms.getLocation()).getWorld().playEffect(BukkitAdapter.adapt(ms.getLocation()), Effect.SMOKE, 0);
/* 355 */       BukkitAdapter.adapt(ms.getLocation()).getWorld().playEffect(BukkitAdapter.adapt(ms.getLocation()), Effect.EXTINGUISH, 0);
/* 356 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner was removed successfully!");
/*     */     } 
/*     */   }
/*     */   public static void cmdSpawnerActivate(CommandSender sender, String[] args) {
/* 360 */     if (args.length < 3) {
/* 361 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a mob spawner to activate!");
/*     */     }
/*     */     
/* 364 */     String name = args[2];
/*     */     
/* 366 */     if (name.startsWith("g:")) {
/*     */       
/* 368 */       String group = name.substring(2);
/*     */       
/* 370 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*     */       
/* 372 */       for (MythicSpawner ms : msl) {
/* 373 */         ms.ActivateSpawner();
/*     */       }
/* 375 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "All spawners in group " + group + " have been activated!");
/* 376 */     } else if (name.equals("*")) {
/* 377 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 378 */         ms.ActivateSpawner();
/*     */       }
/* 380 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "All spawners have been activated!");
/* 381 */     } else if (name.contains("*") || name.contains("?")) {
/* 382 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 383 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?"))) {
/* 384 */           ms.ActivateSpawner();
/*     */         }
/*     */       } 
/* 387 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner matching pattern " + name + " have been activated!");
/*     */     } else {
/* 389 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */       
/* 391 */       if (ms == null) {
/* 392 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The spawner entered does not exist!");
/*     */       }
/*     */       
/* 395 */       ms.ActivateSpawner();
/* 396 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawners " + name + " has been activated!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerInfo(CommandSender sender, String[] args) {
/* 401 */     if (args.length < 3) {
/* 402 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a mob spawner!");
/*     */       return;
/*     */     } 
/* 405 */     String name = args[2];
/*     */     
/* 407 */     MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */     
/* 409 */     if (ms == null) {
/* 410 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The spawner entered does not exist!");
/*     */       
/*     */       return;
/*     */     } 
/* 414 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(ms.getTypeName());
/*     */     
/* 416 */     sender.sendMessage(ChatColor.GOLD + "Stats for Spawner " + ChatColor.BOLD + ChatColor.GRAY + ms.getName() + ":");
/* 417 */     sender.sendMessage(ChatColor.GOLD + "Location: " + ChatColor.GRAY + ms.getLocation().getBlockX() + "," + ms.getLocation().getBlockY() + "," + ms.getLocation().getBlockZ() + "@" + ms.getLocation().getWorld().getName() + "");
/* 418 */     sender.sendMessage(ChatColor.GOLD + "MobSpawn: " + ChatColor.GRAY + mm.getInternalName());
/* 419 */     sender.sendMessage(ChatColor.GOLD + "Group: " + ChatColor.GRAY + ms.getGroup());
/* 420 */     sender.sendMessage(ChatColor.GOLD + "MaxMobs: " + ChatColor.GRAY + ms.getMaxMobs());
/* 421 */     sender.sendMessage(ChatColor.GOLD + "MobLevel: " + ChatColor.GRAY + ms.getMobLevel());
/* 422 */     sender.sendMessage(ChatColor.GOLD + "MobsPerSpawn: " + ChatColor.GRAY + ms.getMobsPerSpawn());
/* 423 */     sender.sendMessage(ChatColor.GOLD + "SpawnRadius: " + ChatColor.GRAY + ms.getSpawnRadius());
/* 424 */     sender.sendMessage(ChatColor.GOLD + "SpawnRadiusY: " + ChatColor.GRAY + ms.getSpawnRadiusY());
/* 425 */     sender.sendMessage(ChatColor.GOLD + "ActivationRange: " + ChatColor.GRAY + ms.getActivationRange());
/* 426 */     sender.sendMessage(ChatColor.GOLD + "LeashRange: " + ChatColor.GRAY + ms.getLeashRange());
/* 427 */     sender.sendMessage(ChatColor.GOLD + "HealOnLeash: " + ChatColor.GRAY + ms.isHealOnLeash());
/* 428 */     sender.sendMessage(ChatColor.GOLD + "Cooldown: " + ChatColor.GRAY + ms.getCooldownSeconds());
/* 429 */     sender.sendMessage(ChatColor.GOLD + "Warmup: " + ChatColor.GRAY + ms.getWarmupSeconds());
/* 430 */     sender.sendMessage(ChatColor.GOLD + "Breakable: " + ChatColor.GRAY + ms.isBreakable());
/* 431 */     sender.sendMessage(ChatColor.GOLD + "Conditions: " + ChatColor.GRAY + ms.getConditionList().toString());
/* 432 */     sender.sendMessage(ChatColor.RED + "Current # Mobs Spawned: " + ChatColor.GRAY + ms.getNumberOfMobs() + " mobs (Cached: " + ms.getNumberOfCachedMobs() + ")");
/* 433 */     sender.sendMessage(ChatColor.RED + "Spawner On Cooldown: " + ChatColor.WHITE + ms.isOnCooldown() + " (" + ChatColor.GRAY + ms.getRemainingCooldownSeconds() + " seconds)");
/* 434 */     sender.sendMessage(ChatColor.RED + "Spawner On Warmup: " + ChatColor.WHITE + ms.isOnWarmup() + " (" + ChatColor.GRAY + ms.getRemainingWarmupSeconds() + " seconds)");
/* 435 */     sender.sendMessage(ChatColor.RED + "Spawner Internal Cooldown: " + ChatColor.GRAY + ms.getInternalCooldown() + " ticks");
/*     */     
/* 437 */     if (ConfigManager.debugLevel > 0)
/* 438 */       sender.sendMessage(ChatColor.YELLOW + "Associated Mobs: " + ChatColor.AQUA + ms.getAssociatedMobs().toString()); 
/*     */   }
/*     */   
/*     */   public static void menuSet(CommandSender sender) {
/* 442 */     sender.sendMessage(MythicMobs.menu_header);
/* 443 */     sender.sendMessage(ChatColor.YELLOW + "Command: " + ChatColor.AQUA + "/mm spawners set [name] [attribute] [value]");
/* 444 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Available Attributes:");
/* 445 */     sender.sendMessage(ChatColor.GOLD + "activationrange" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The name of a group to organize the spawner into.");
/* 446 */     sender.sendMessage(ChatColor.GOLD + "cooldown" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The time (in seconds) between mob spawns.");
/* 447 */     sender.sendMessage(ChatColor.GOLD + "group" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The name of a group to organize the spawner into.");
/* 448 */     sender.sendMessage(ChatColor.GOLD + "healonleash" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) Whether or not a mob will heal to full health upon being leashed.");
/* 449 */     sender.sendMessage(ChatColor.GOLD + "leashrange" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The max range a mob can be from the spawner before it is teleported back (0=none).");
/* 450 */     sender.sendMessage(ChatColor.GOLD + "maxmobs" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The maximum number of mobs this spawner can have active at once.");
/* 451 */     sender.sendMessage(ChatColor.GOLD + "moblevel" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The level of the mobs spawned by the spawner.");
/* 452 */     sender.sendMessage(ChatColor.GOLD + "mobsperspawn" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - How many mobs will spawn per spawner cycle.");
/* 453 */     sender.sendMessage(ChatColor.GOLD + "resetthreatonleash" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) Whether or not the mob's target should be reset upon being leashed.");
/* 454 */     sender.sendMessage(ChatColor.GOLD + "showflames" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) whether to always show spawner flames on the block.");
/* 455 */     sender.sendMessage(ChatColor.GOLD + "usetimer" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - (true/false) whether the spawner should spawn on a timer or not..");
/* 456 */     sender.sendMessage(ChatColor.GOLD + "warmup" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - The time (in seconds) until a mob spawns after a mob dies, when the max number of mobs has been reached.");
/*     */   }
/*     */   public static void menuConditions(CommandSender sender) {
/* 459 */     sender.sendMessage(MythicMobs.menu_header);
/* 460 */     sender.sendMessage(ChatColor.YELLOW + "Command: " + ChatColor.AQUA + "/mm spawners addcondition [condition] <data>");
/* 461 */     sender.sendMessage(ChatColor.YELLOW + "View conditions @ http://xikage.elseland.net/mythicmobs");
/*     */   }
/*     */   public static void cmdConditionAdd(CommandSender sender, String[] args) {
/* 464 */     if (!(sender instanceof Player)) {
/* 465 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 469 */     if (args.length < 5) {
/* 470 */       menuConditions(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 474 */     String name = args[2];
/* 475 */     String condition = args[3];
/* 476 */     String value = args[4];
/*     */     
/* 478 */     if (name.startsWith("g:")) {
/* 479 */       String group = name.substring(2);
/*     */       
/* 481 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*     */       
/* 483 */       for (MythicSpawner ms : msl) {
/* 484 */         if (!MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/* 485 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           return;
/*     */         } 
/*     */       } 
/* 489 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " set to " + ChatColor.AQUA + value + " on group " + group);
/* 490 */     } else if (name.equals("*")) {
/* 491 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 492 */         if (!MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/* 493 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           return;
/*     */         } 
/*     */       } 
/* 497 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " added to all spawners!");
/* 498 */     } else if (name.contains("*") || name.contains("?")) {
/* 499 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 500 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?")) && 
/* 501 */           !MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/* 502 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 507 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " added to spawners matching pattern " + name + "!");
/*     */     } else {
/* 509 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */       
/* 511 */       if (ms == null) {
/* 512 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/*     */         
/*     */         return;
/*     */       } 
/* 516 */       if (!MythicMobs.inst().getSpawnerManager().addSpawnerCondition(ms, condition, value)) {
/* 517 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */       } else {
/* 519 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " set to " + ChatColor.AQUA + value);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void cmdConditionRemove(CommandSender sender, String[] args) {
/* 524 */     if (!(sender instanceof Player)) {
/* 525 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 529 */     String name = args[2];
/* 530 */     String condition = args[3];
/*     */     
/* 532 */     if (name.startsWith("g:")) {
/* 533 */       String group = name.substring(2);
/*     */       
/* 535 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*     */       
/* 537 */       for (MythicSpawner ms : msl) {
/* 538 */         if (!MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 539 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           return;
/*     */         } 
/*     */       } 
/* 543 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " was removed on group " + group);
/* 544 */     } else if (name.equals("*")) {
/* 545 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 546 */         if (!MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 547 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           return;
/*     */         } 
/*     */       } 
/* 551 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " removed from all spawners!");
/* 552 */     } else if (name.contains("*") || name.contains("?")) {
/* 553 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 554 */         if (ms.getName().matches(name.replace("?", ".?").replace("*", ".*?")) && 
/* 555 */           !MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 556 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The condition you entered was invalid!");
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 561 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " removed from spawners matching pattern " + name + "!");
/*     */     } else {
/* 563 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */       
/* 565 */       if (ms == null) {
/* 566 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/*     */         
/*     */         return;
/*     */       } 
/* 570 */       if (!MythicMobs.inst().getSpawnerManager().removeSpawnerCondition(ms, condition)) {
/* 571 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The attribute or value you entered was invalid!");
/*     */       } else {
/* 573 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner condition " + ChatColor.AQUA + condition + ChatColor.GREEN + " has been removed!");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void cmdResetTimers(CommandSender sender, String[] args) {
/* 578 */     String name = args[2];
/*     */     
/* 580 */     if (name.startsWith("g:")) {
/* 581 */       String group = name.substring(2);
/*     */       
/* 583 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*     */       
/* 585 */       for (MythicSpawner ms : msl) {
/* 586 */         ms.resetTimers();
/*     */       }
/* 588 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner timers reset on group " + group);
/* 589 */     } else if (name.equals("*")) {
/* 590 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 591 */         ms.resetTimers();
/*     */       }
/* 593 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner timers reset on all spawners!");
/* 594 */     } else if (name.contains("*") || name.contains("?")) {
/* 595 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 596 */         ms.resetTimers();
/*     */       }
/* 598 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Spawner timers reset on spawners matching pattern " + name + "!");
/*     */     } else {
/* 600 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*     */       
/* 602 */       if (ms == null) {
/* 603 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Spawner. That one was not found!");
/*     */         
/*     */         return;
/*     */       } 
/* 607 */       ms.resetTimers();
/* 608 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawner timers reset on spawner " + name);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerCut(CommandSender sender, String[] args) {
/* 613 */     if (!(sender instanceof Player)) {
/* 614 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 618 */     if (args.length < 3) {
/* 619 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a spawner to move.");
/*     */       return;
/*     */     } 
/* 622 */     String name = args[2];
/*     */     
/* 624 */     ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByString(BukkitAdapter.adapt((Player)sender).getLocation(), name);
/*     */     
/* 626 */     if (msl.size() == 0) {
/* 627 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Your entry returned no spawners to copy.");
/*     */       return;
/*     */     } 
/* 630 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Cut " + ChatColor.AQUA + msl.size() + ChatColor.GREEN + " spawners to the clipboard!");
/*     */     
/* 632 */     SpawnerSlice.CutSpawners(msl, BukkitAdapter.adapt(((Player)sender).getLocation()));
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerPaste(CommandSender sender, String[] args) {
/* 636 */     if (!(sender instanceof Player)) {
/* 637 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 641 */     if (SpawnerSlice.PasteSpawners(BukkitAdapter.adapt(((Player)sender).getLocation())) == true) {
/* 642 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawners pasted from the clipboard!");
/*     */     } else {
/* 644 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "There are no spawners to paste in the clipboard (or an error occured!)");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void cmdSpawnerUndo(CommandSender sender, String[] args) {
/* 649 */     if (!(sender instanceof Player)) {
/* 650 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 654 */     if (SpawnerSlice.Undo() == true) {
/* 655 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Last spawner clipboard changes undone!");
/*     */     } else {
/* 657 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "There are no spawners in the clipboard to undo an action on!");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\SpawnerCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */