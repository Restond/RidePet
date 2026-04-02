/*     */ package lumine.xikage.mythicmobs.commands.mobs;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.WorldScaling;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntityType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.StringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpawnCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public SpawnCommand(Command<MythicMobs> parent) {
/*  31 */     super(parent);
/*     */   }
/*     */   
/*     */   public SpawnCommand(MythicMobs plugin) {
/*  35 */     super((Plugin)plugin);
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*     */     String mob;
/*  40 */     Location loc = null;
/*  41 */     int amount = 1;
/*  42 */     boolean optAtTarget = false;
/*  43 */     boolean optAtPlayer = false;
/*  44 */     boolean optSilent = false;
/*     */     
/*  46 */     if (args.length == 0) {
/*  47 */       CommandHelper.sendError(sender, "You must specify a mob to spawn.");
/*  48 */       return true;
/*     */     } 
/*     */     
/*  51 */     if (args != null && args.length > 1 && 
/*  52 */       args[0].startsWith("-")) {
/*  53 */       if (args[0].contains("s")) {
/*  54 */         optSilent = true;
/*     */       }
/*  56 */       if (args[0].contains("t")) {
/*  57 */         optAtTarget = true;
/*     */       }
/*  59 */       if (args[0].contains("p")) {
/*  60 */         optAtPlayer = true;
/*     */       }
/*  62 */       args = Arrays.<String>copyOfRange(args, 1, args.length);
/*     */     } 
/*     */ 
/*     */     
/*  66 */     if (optAtPlayer) {
/*  67 */       String name = args[0];
/*  68 */       Player player = Bukkit.getPlayer(name);
/*     */       
/*  70 */       if (player == null) {
/*  71 */         if (!optSilent) CommandHelper.sendError(sender, "Player " + name + " not found."); 
/*  72 */         return true;
/*     */       } 
/*  74 */       if (optAtTarget) {
/*  75 */         loc = player.getTargetBlock(MythicMobs.inst().getConfigManager().getTransparentBlocks(), 200).getRelative(BlockFace.UP).getLocation();
/*     */       } else {
/*  77 */         loc = player.getLocation();
/*     */       } 
/*  79 */       args = Arrays.<String>copyOfRange(args, 1, args.length);
/*     */     } 
/*     */ 
/*     */     
/*  83 */     int level = 1;
/*     */     
/*  85 */     if (args.length > 1) {
/*     */       try {
/*  87 */         amount = Integer.parseInt(args[1]);
/*  88 */       } catch (Exception e) {
/*  89 */         amount = 1;
/*     */       } 
/*     */     }
/*     */     
/*  93 */     if (args.length > 2) {
/*     */       try {
/*  95 */         String[] part = args[2].split(",");
/*  96 */         World w = Bukkit.getWorld(part[0]);
/*  97 */         float x = Float.parseFloat(part[1]);
/*  98 */         float y = Float.parseFloat(part[2]);
/*  99 */         float z = Float.parseFloat(part[3]);
/*     */         
/* 101 */         if (w != null) {
/* 102 */           loc = new Location(w, x, y, z);
/*     */         }
/* 104 */       } catch (Exception e) {
/* 105 */         CommandHelper.sendError(sender, "Invalid location specified for spawning a mob: location must be in format world,x,y,z");
/* 106 */         return true;
/*     */       }
/*     */     
/* 109 */     } else if (!optAtPlayer && sender instanceof Player) {
/* 110 */       if (optAtTarget) {
/* 111 */         loc = ((Player)sender).getTargetBlock(MythicMobs.inst().getConfigManager().getTransparentBlocks(), 200).getRelative(BlockFace.UP).getLocation();
/*     */       } else {
/* 113 */         loc = ((Player)sender).getLocation();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 118 */     if (loc == null) {
/* 119 */       if (!optSilent) CommandHelper.sendError(sender, "Invalid location specified for spawning a mob: world does not exist."); 
/* 120 */       return true;
/*     */     } 
/*     */     
/* 123 */     if (args[0].contains(":")) {
/* 124 */       String[] split = args[0].split(":");
/* 125 */       mob = split[0];
/*     */       try {
/* 127 */         level = Integer.parseInt(split[1]);
/* 128 */       } catch (Exception ex) {
/* 129 */         if (!optSilent) CommandHelper.sendError(sender, "Invalid mob level supplied: must be an integer."); 
/* 130 */         return true;
/*     */       } 
/*     */     } else {
/* 133 */       mob = args[0];
/* 134 */       level = WorldScaling.getLevelBonus(BukkitAdapter.adapt(loc));
/*     */     } 
/*     */     
/* 137 */     if (MythicMobs.inst().getMobManager().getMythicMob(mob) != null) {
/* 138 */       ActiveMob l = null;
/* 139 */       for (int i = 0; i < amount; i++) {
/* 140 */         l = MythicMobs.inst().getMobManager().spawnMob(mob, loc, level);
/*     */       }
/*     */       
/* 143 */       if (l != null)
/* 144 */       { if (!optSilent) CommandHelper.sendSuccess(sender, "Spawned " + amount + "x " + mob + "&a!");
/*     */          }
/* 146 */       else if (!optSilent) { CommandHelper.sendError(sender, "Failed to spawn mob. See console for more details."); }
/*     */     
/* 148 */     } else if (MythicEntityType.get(mob) != null) {
/* 149 */       for (int i = 0; i < amount; i++) {
/* 150 */         MythicEntity.getMythicEntity(mob).spawn(loc);
/*     */       }
/* 152 */       if (!optSilent) CommandHelper.sendSuccess(sender, "Spawned " + amount + "x " + mob + "&a!"); 
/*     */     } else {
/*     */       try {
/* 155 */         EntityType type = EntityType.valueOf(mob);
/* 156 */         for (int i = 0; i < amount; i++) {
/* 157 */           loc.getWorld().spawnEntity(loc, type);
/*     */         }
/* 159 */         if (!optSilent) CommandHelper.sendSuccess(sender, "Spawned " + amount + "x " + mob + "&a!"); 
/* 160 */         return true;
/* 161 */       } catch (Exception exception) {
/*     */         
/* 163 */         if (!optSilent) CommandHelper.sendError(sender, "No mob type found with the name " + mob + "."); 
/*     */       } 
/* 165 */     }  return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 170 */     if (args != null && args.length > 1 && 
/* 171 */       args[0].startsWith("-")) {
/* 172 */       args = Arrays.<String>copyOfRange(args, 1, args.length);
/*     */     }
/*     */     
/* 175 */     if (args.length == 1) {
/* 176 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getMobManager().getMobNames(), new ArrayList());
/*     */     }
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 183 */     return "mythicmobs.command.mobs.spawn";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 188 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 193 */     return "spawn";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 198 */     return new String[] { "s" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\SpawnCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */