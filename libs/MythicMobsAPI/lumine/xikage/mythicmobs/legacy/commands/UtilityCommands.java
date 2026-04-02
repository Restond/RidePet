/*     */ package lumine.xikage.mythicmobs.legacy.commands;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.legacy.commands.CommandHandler;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.WorldScaling;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.util.HashSet;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class UtilityCommands
/*     */ {
/*     */   public static void menuCommands(CommandSender sender) {
/*  28 */     sender.sendMessage(MythicMobs.menu_header);
/*  29 */     sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/*  30 */     sender.sendMessage(ChatColor.GOLD + "/mm utility GetBlockCoords" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Gives you the exact coordinates of the block you're looking at.");
/*  31 */     sender.sendMessage(ChatColor.GOLD + "/mm utility GetTargetInfo" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Gives you info about the target you're looking at.");
/*  32 */     sender.sendMessage(ChatColor.GOLD + "/mm utility ListItemNBT" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Lists the NBT data of the item you are holding.");
/*  33 */     sender.sendMessage(ChatColor.GOLD + "/mm utility ListRegisteredEntities" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Lists all entities registered in Bukkit (mainly for modded versions).");
/*  34 */     sender.sendMessage(ChatColor.GOLD + "/mm utility PrintVoidList" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Prints UUIDs of mobs in the void list.");
/*  35 */     sender.sendMessage(ChatColor.GOLD + "/mm utility GetSpawnLevel" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Tells you what level a vanilla mob would be spawning at your current location.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void parseCommands(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/*  41 */     if (args.length == 1) {
/*  42 */       menuCommands(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  46 */     switch (args[1].toLowerCase()) { case "broadcast":
/*     */       case "b":
/*  48 */         cmdBroadcast(sender, args); return;
/*     */       case "getblockcoords": case "blockcoords":
/*     */       case "gbc":
/*  51 */         cmdGetBlockCoords(sender, args); return;
/*     */       case "gettargetinfo": case "targetinfo":
/*     */       case "gti":
/*  54 */         cmdGetTargetInfo(sender, args); return;
/*     */       case "getspawnlevel":
/*     */       case "gsl":
/*  57 */         cmdGetSpawnLevel(sender, args); return;
/*     */       case "listregisteredentities": case "listentities":
/*     */       case "lre":
/*  60 */         cmdListRegisteredEntities(sender, args);
/*     */         return;
/*     */       case "listitemnbt":
/*  63 */         cmdListItemNBT(sender, args); return;
/*     */       case "printvoidlist":
/*     */       case "voidlist":
/*  66 */         cmdPrintVoidList(sender, args);
/*     */         return; }
/*     */     
/*  69 */     menuCommands(sender);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void cmdGetSpawnLevel(CommandSender sender, String[] args) {
/*  74 */     if (!(sender instanceof Player)) {
/*  75 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/*  79 */     Player p = (Player)sender;
/*     */     
/*  81 */     AbstractLocation l = BukkitAdapter.adapt(p.getLocation());
/*     */     
/*  83 */     WorldScaling.getLevelBonus(l);
/*     */     
/*  85 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Distance from Spawn: " + ChatColor.WHITE + l.distance2D(l.getWorld().getSpawnLocation()));
/*  86 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Blocks per Level: " + ChatColor.WHITE + WorldScaling.get(l.getWorld().getName()).getScaleFactor_BlocksFromSpawn());
/*  87 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Spawn Level for your location: " + ChatColor.WHITE + "1 + " + WorldScaling.getLevelBonus(l));
/*     */   }
/*     */   
/*     */   public static void cmdBroadcast(CommandSender sender, String[] args) {
/*  91 */     String s = "";
/*     */     
/*  93 */     for (int i = 2; i < args.length; i++) {
/*  94 */       s = s.concat(args[i]) + " ";
/*     */     }
/*     */     
/*  97 */     s = SkillString.parseMessageSpecialChars(s);
/*  98 */     s = ChatColor.translateAlternateColorCodes('&', s);
/*     */     
/* 100 */     for (Player p : Bukkit.getServer().getOnlinePlayers()) {
/* 101 */       p.sendMessage(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void cmdGetBlockCoords(CommandSender sender, String[] args) {
/* 107 */     if (!(sender instanceof Player)) {
/* 108 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 112 */     Player p = (Player)sender;
/*     */     
/* 114 */     Location location = p.getTargetBlock((HashSet)null, 64).getLocation();
/* 115 */     if (location == null) {
/* 116 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block!");
/*     */       
/*     */       return;
/*     */     } 
/* 120 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Coordinates you are targeting: " + ChatColor.AQUA + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ChatColor.GREEN + " (x,y,z)");
/*     */   }
/*     */   
/*     */   public static void cmdGetTargetInfo(CommandSender sender, String[] args) {
/* 124 */     if (!(sender instanceof Player)) {
/* 125 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 129 */     Player p = (Player)sender;
/*     */     
/* 131 */     LivingEntity l = MythicUtil.getTargetedEntity(p);
/*     */     
/* 133 */     if (l == null) {
/* 134 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid entity!");
/*     */       
/*     */       return;
/*     */     } 
/* 138 */     sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Information About Target Mod:");
/* 139 */     sender.sendMessage(ChatColor.GOLD + "UUID: " + ChatColor.GRAY + l.getUniqueId());
/*     */     
/* 141 */     if (MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/* 142 */       sender.sendMessage(ChatColor.GOLD + "Is Active MythicMob: " + ChatColor.GRAY + "TRUE");
/*     */     } else {
/* 144 */       sender.sendMessage(ChatColor.GOLD + "Is Active MythicMob: " + ChatColor.GRAY + "FALSE");
/*     */     } 
/* 146 */     if (MythicMobs.inst().getMobManager().isIgnoredEntity(l.getUniqueId())) {
/* 147 */       sender.sendMessage(ChatColor.GOLD + "Is In Void List: " + ChatColor.GRAY + "TRUE");
/*     */     } else {
/* 149 */       sender.sendMessage(ChatColor.GOLD + "Is In Void List: " + ChatColor.GRAY + "FALSE");
/*     */     } 
/*     */     
/* 152 */     if (MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/* 153 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/* 154 */       am.remountSpawner();
/*     */       
/* 156 */       sender.sendMessage(ChatColor.GOLD + "EntityType: " + ChatColor.GRAY + am.getType().getEntityType());
/* 157 */       sender.sendMessage(ChatColor.GOLD + "MythicType: " + ChatColor.GRAY + am.getType().getInternalName());
/* 158 */       sender.sendMessage(ChatColor.GOLD + "Level: " + ChatColor.GRAY + am.getLevel());
/* 159 */       sender.sendMessage(ChatColor.GOLD + "TypeHealth: " + ChatColor.GRAY + am.getType().getBaseHealth());
/* 160 */       sender.sendMessage(ChatColor.GOLD + "+LevelHealth: " + ChatColor.GRAY + am.getType().getPerLevelHealth());
/* 161 */       sender.sendMessage(ChatColor.GOLD + "TargetHealth: " + ChatColor.GRAY + am.getEntity().getHealth());
/* 162 */       sender.sendMessage(ChatColor.GOLD + "TypeDamage: " + ChatColor.GRAY + am.getType().getBaseDamage());
/* 163 */       sender.sendMessage(ChatColor.GOLD + "TargetDamage: " + ChatColor.GRAY + am.getDamage());
/* 164 */       sender.sendMessage(ChatColor.GOLD + "+LevelPower: " + ChatColor.GRAY + am.getType().getPerLevelPower());
/* 165 */       sender.sendMessage(ChatColor.GOLD + "TargetPower: " + ChatColor.GRAY + am.getPower());
/*     */       
/* 167 */       sender.sendMessage(ChatColor.GOLD + "Current Stance: " + ChatColor.GRAY + am.getStance());
/*     */       
/* 169 */       if (am.getSpawner() == null) {
/* 170 */         sender.sendMessage(ChatColor.GOLD + "FromSpawner: " + ChatColor.GRAY + "FALSE");
/*     */       } else {
/* 172 */         sender.sendMessage(ChatColor.GOLD + "FromSpawner: " + ChatColor.AQUA + am.getSpawner().getInternalName());
/*     */       } 
/* 174 */       if (am.isDead()) {
/* 175 */         sender.sendMessage(ChatColor.GOLD + "Marked as Dead: " + ChatColor.GRAY + "TRUE");
/*     */       } else {
/* 177 */         sender.sendMessage(ChatColor.GOLD + "Marked as Dead: " + ChatColor.GRAY + "FALSE");
/*     */       } 
/* 179 */       if (am.getEntity().isValid()) {
/* 180 */         sender.sendMessage(ChatColor.GOLD + "Marked as Valid: " + ChatColor.GRAY + "TRUE");
/*     */       } else {
/* 182 */         sender.sendMessage(ChatColor.GOLD + "Marked as Valid: " + ChatColor.GRAY + "FALSE");
/*     */       } 
/* 184 */       if (am.hasThreatTable()) {
/* 185 */         sender.sendMessage(ChatColor.GOLD + "Using ThreatTable: " + ChatColor.GRAY + "TRUE");
/* 186 */         if (am.getThreatTable().inCombat()) {
/* 187 */           sender.sendMessage(ChatColor.GOLD + "Top Threat Target: " + ChatColor.GRAY + am.getThreatTable().getTopThreatHolder().getName() + " (" + am.getThreatTable().getTopTargetThreat() + ")");
/* 188 */           sender.sendMessage(ChatColor.GOLD + "Total Threat: " + ChatColor.GRAY + am.getThreatTable().getTotalThreat());
/* 189 */           sender.sendMessage(ChatColor.GOLD + "-- Threat Targets: ");
/*     */           
/* 191 */           for (AbstractEntity le : am.getThreatTable().getAllThreatTargets()) {
/* 192 */             sender.sendMessage(ChatColor.GOLD + "* " + le.getName() + " (" + am.getThreatTable().getThreat(le) + ")");
/*     */           }
/*     */         } else {
/* 195 */           sender.sendMessage(ChatColor.GOLD + "-- Mob Not In Combat");
/*     */         } 
/*     */       } else {
/* 198 */         sender.sendMessage(ChatColor.GOLD + "Using ThreatTable: " + ChatColor.GRAY + "FALSE");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void cmdListItemNBT(CommandSender sender, String[] args) {
/* 203 */     if (!(sender instanceof Player)) {
/* 204 */       CommandHandler.NoConsole(sender);
/*     */       
/*     */       return;
/*     */     } 
/* 208 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Finding NBT Data of Item in Hand...");
/* 209 */     MythicMobs.inst().getVolatileCodeHandler().listItemAttributes((Player)sender);
/*     */   }
/*     */   
/*     */   public static void cmdListRegisteredEntities(CommandSender sender, String[] args) {
/* 213 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Printing registered LivingEntity types...");
/*     */     
/* 215 */     for (EntityType type : EntityType.values()) {
/* 216 */       sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.WHITE + type.toString());
/*     */     }
/* 218 */     sender.sendMessage("" + ChatColor.RED + ChatColor.ITALIC + "* Note: Remember, only 'living' entity types can be used in MythicMobs. Do not use non-living types!");
/*     */   }
/*     */   
/*     */   public static void cmdPrintVoidList(CommandSender sender, String[] args) {
/* 222 */     sender.sendMessage(ChatColor.GOLD + "[MythicMobs] Printing UUIDs in Void List...");
/*     */     
/* 224 */     for (UUID u : MythicMobs.inst().getMobManager().getVoidList())
/* 225 */       sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.WHITE + u.toString()); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\UtilityCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */