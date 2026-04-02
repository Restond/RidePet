/*    */ package lumine.xikage.mythicmobs.commands.spawners;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class InfoCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public InfoCommand(Command<MythicMobs> parent) {
/* 17 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 22 */     if (args.length < 1) {
/* 23 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "You must enter the name of a mob spawner!");
/* 24 */       return true;
/*    */     } 
/* 26 */     String name = args[0];
/*    */     
/* 28 */     MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*    */     
/* 30 */     if (ms == null) {
/* 31 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "The spawner entered does not exist!");
/* 32 */       return true;
/*    */     } 
/*    */     
/* 35 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(ms.getTypeName());
/*    */     
/* 37 */     sender.sendMessage(ChatColor.GOLD + "Stats for Spawner " + ChatColor.BOLD + ChatColor.GRAY + ms.getName() + ":");
/* 38 */     sender.sendMessage(ChatColor.GOLD + "Location: " + ChatColor.GRAY + ms.getLocation().getBlockX() + "," + ms.getLocation().getBlockY() + "," + ms.getLocation().getBlockZ() + "@" + ms.getLocation().getWorld().getName() + "");
/* 39 */     sender.sendMessage(ChatColor.GOLD + "MobSpawn: " + ChatColor.GRAY + mm.getInternalName());
/* 40 */     sender.sendMessage(ChatColor.GOLD + "Group: " + ChatColor.GRAY + ms.getGroup());
/* 41 */     sender.sendMessage(ChatColor.GOLD + "MaxMobs: " + ChatColor.GRAY + ms.getMaxMobs());
/* 42 */     sender.sendMessage(ChatColor.GOLD + "MobLevel: " + ChatColor.GRAY + ms.getMobLevel());
/* 43 */     sender.sendMessage(ChatColor.GOLD + "MobsPerSpawn: " + ChatColor.GRAY + ms.getMobsPerSpawn());
/* 44 */     sender.sendMessage(ChatColor.GOLD + "SpawnRadius: " + ChatColor.GRAY + ms.getSpawnRadius());
/* 45 */     sender.sendMessage(ChatColor.GOLD + "SpawnRadiusY: " + ChatColor.GRAY + ms.getSpawnRadiusY());
/* 46 */     sender.sendMessage(ChatColor.GOLD + "ActivationRange: " + ChatColor.GRAY + ms.getActivationRange());
/* 47 */     sender.sendMessage(ChatColor.GOLD + "LeashRange: " + ChatColor.GRAY + ms.getLeashRange());
/* 48 */     sender.sendMessage(ChatColor.GOLD + "HealOnLeash: " + ChatColor.GRAY + ms.isHealOnLeash());
/* 49 */     sender.sendMessage(ChatColor.GOLD + "Cooldown: " + ChatColor.GRAY + ms.getCooldownSeconds());
/* 50 */     sender.sendMessage(ChatColor.GOLD + "Warmup: " + ChatColor.GRAY + ms.getWarmupSeconds());
/* 51 */     sender.sendMessage(ChatColor.GOLD + "Breakable: " + ChatColor.GRAY + ms.isBreakable());
/* 52 */     sender.sendMessage(ChatColor.GOLD + "Conditions: " + ChatColor.GRAY + ms.getConditionList().toString());
/* 53 */     sender.sendMessage(ChatColor.RED + "Current # Mobs Spawned: " + ChatColor.GRAY + ms.getNumberOfMobs() + " mobs (Cached: " + ms.getNumberOfCachedMobs() + ")");
/* 54 */     sender.sendMessage(ChatColor.RED + "Spawner On Cooldown: " + ChatColor.WHITE + ms.isOnCooldown() + " (" + ChatColor.GRAY + ms.getRemainingCooldownSeconds() + " seconds)");
/* 55 */     sender.sendMessage(ChatColor.RED + "Spawner On Warmup: " + ChatColor.WHITE + ms.isOnWarmup() + " (" + ChatColor.GRAY + ms.getRemainingWarmupSeconds() + " seconds)");
/* 56 */     sender.sendMessage(ChatColor.RED + "Spawner Internal Cooldown: " + ChatColor.GRAY + ms.getInternalCooldown() + " ticks");
/*    */     
/* 58 */     if (ConfigManager.debugLevel > 0) {
/* 59 */       sender.sendMessage(ChatColor.YELLOW + "Associated Mobs: " + ChatColor.AQUA + ms.getAssociatedMobs().toString());
/*    */     }
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 66 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 71 */     return "mythicmobs.command.spawners.info";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 81 */     return "info";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\InfoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */