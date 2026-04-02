/*     */ package lumine.xikage.mythicmobs.commands.spawners;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomInt;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CreateCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public CreateCommand(Command<MythicMobs> parent) {
/*  25 */     super(parent);
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*     */     String mobName;
/*  30 */     if (args.length < 2) {
/*  31 */       sender.sendMessage(ChatColor.RED + "Command Format: " + ChatColor.AQUA + "/mm spawners create [name] [mob_name]");
/*  32 */       return true;
/*     */     } 
/*     */     
/*  35 */     String name = args[0];
/*  36 */     if (name == null) {
/*  37 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a name for the spawner.");
/*  38 */       return true;
/*     */     } 
/*     */     
/*  41 */     Optional<MythicSpawner> maybeSpawner = MythicMobs.inst().getSpawnerManager().getSpawnerByHashcode(name.hashCode());
/*  42 */     if (maybeSpawner.isPresent()) {
/*  43 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Spawner name must resolve to a unique hashcode.");
/*  44 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  48 */     RandomInt level = new RandomInt(1);
/*     */     
/*  50 */     if (args[1].contains(":")) {
/*  51 */       String[] split = args[1].split(":");
/*  52 */       mobName = split[0];
/*     */       try {
/*  54 */         level = new RandomInt(split[1]);
/*  55 */       } catch (Exception ex) {
/*  56 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Invalid mob level supplied: must be an integer or range (1, 2to5, etc).");
/*  57 */         return true;
/*     */       } 
/*     */     } else {
/*  60 */       mobName = args[1];
/*     */     } 
/*     */     
/*  63 */     if (mobName == null) {
/*  64 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a Mythic Mob for the spawner to spawn.");
/*  65 */       return true;
/*     */     } 
/*  67 */     if (MythicMobs.inst().getMobManager().getMythicMob(args[1]) == null) {
/*  68 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter a valid Mythic Mob. That one was not found!");
/*  69 */       return true;
/*     */     } 
/*     */     
/*  72 */     Location location = null;
/*     */     
/*  74 */     if (args.length > 2) {
/*     */       try {
/*  76 */         String[] part = args[2].split(",");
/*  77 */         World w = Bukkit.getWorld(part[0]);
/*  78 */         float x = Float.parseFloat(part[1]);
/*  79 */         float y = Float.parseFloat(part[2]);
/*  80 */         float z = Float.parseFloat(part[3]);
/*     */         
/*  82 */         MythicMobs.debug(1, "Parsing location using: " + w.toString() + "," + x + "," + y + "," + z);
/*     */         
/*  84 */         if (w != null) {
/*  85 */           location = new Location(w, x, y, z);
/*     */         }
/*  87 */       } catch (Exception e) {
/*  88 */         CommandHelper.sendError(sender, "Invalid location specified for spawner: location must be in format world,x,y,z");
/*  89 */         return true;
/*     */       } 
/*  91 */     } else if (sender instanceof Player) {
/*  92 */       location = ((Player)sender).getTargetBlock((HashSet)null, 10).getLocation();
/*     */     } else {
/*  94 */       CommandHelper.sendError(sender, "Invalid location specified for spawner: location must be in format world,x,y,z");
/*  95 */       return true;
/*     */     } 
/*     */     
/*  98 */     if (location == null) {
/*  99 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must target a valid block to place the spawner!");
/* 100 */       return true;
/*     */     } 
/*     */     
/* 103 */     MythicSpawner ms = MythicMobs.inst().getSpawnerManager().createSpawner(name, location, mobName);
/*     */     
/* 105 */     if (ms != null) {
/* 106 */       location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
/* 107 */       location.getWorld().playEffect(location, Effect.EXTINGUISH, 0);
/* 108 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "New spawner was created successfully!");
/* 109 */       ms.setMobLevel(level);
/*     */     } else {
/* 111 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "Spawner creation failed: spawner name is probably already taken!");
/*     */     } 
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 123 */     return "mythicmobs.command.spawners.create";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 133 */     return "create";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 138 */     return new String[] { "cr", "c" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\CreateCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */