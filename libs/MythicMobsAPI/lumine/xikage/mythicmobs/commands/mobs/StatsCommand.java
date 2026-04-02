/*    */ package lumine.xikage.mythicmobs.commands.mobs;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StatsCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public StatsCommand(Command<MythicMobs> parent) {
/* 19 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 24 */     int amount = 0;
/* 25 */     int alive = 0;
/* 26 */     int dead = 0;
/* 27 */     int valid = 0;
/* 28 */     int invalid = 0;
/* 29 */     int n = 0;
/*    */     
/* 31 */     AbstractPlayer player = BukkitAdapter.adapt((Player)sender);
/* 32 */     for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 33 */       if (am.getLocation().getWorld().equals(player.getWorld())) {
/* 34 */         amount++;
/* 35 */         if (am.getEntity() == null) {
/* 36 */           n++; continue;
/*    */         } 
/* 38 */         if (am.getEntity().isDead()) {
/* 39 */           dead++;
/*    */         } else {
/* 41 */           alive++;
/*    */         } 
/* 43 */         if (am.getEntity().isValid()) {
/* 44 */           valid++; continue;
/*    */         } 
/* 46 */         invalid++;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 51 */     sender.sendMessage(ChatColor.GREEN + "Total ActiveMob Instances: " + ChatColor.WHITE + amount);
/* 52 */     sender.sendMessage(ChatColor.GREEN + "Alive: " + ChatColor.WHITE + alive);
/* 53 */     sender.sendMessage(ChatColor.GREEN + "Dead: " + ChatColor.WHITE + dead);
/* 54 */     sender.sendMessage(ChatColor.GREEN + "Valid: " + ChatColor.WHITE + valid);
/* 55 */     sender.sendMessage(ChatColor.GREEN + "Invalid: " + ChatColor.WHITE + invalid);
/* 56 */     sender.sendMessage(ChatColor.GREEN + "Null: " + ChatColor.WHITE + n);
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 67 */     return "mythicmobs.command.mobs.stats";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 77 */     return "stats";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\StatsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */