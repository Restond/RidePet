/*    */ package lumine.xikage.mythicmobs.commands.mobs;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KillAllCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public KillAllCommand(Command<MythicMobs> parent) {
/* 20 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     int radius = 0;
/* 26 */     boolean literally = false;
/*    */     
/* 28 */     if (args != null && args.length > 1 && 
/* 29 */       args[0].startsWith("-")) {
/* 30 */       if (args[0].contains("p")) {
/* 31 */         literally = true;
/*    */       }
/* 33 */       args = Arrays.<String>copyOfRange(args, 1, args.length);
/*    */     } 
/*    */ 
/*    */     
/* 37 */     if (args.length > 0 && sender instanceof Player) {
/* 38 */       radius = Integer.parseInt(args[0]);
/*    */     }
/* 40 */     if (args.length > 1 && 
/* 41 */       args[1].equals("LITERALLY")) {
/* 42 */       literally = true;
/*    */     }
/*    */     
/* 45 */     int amount = 0;
/*    */     
/* 47 */     if (radius > 0) {
/* 48 */       AbstractPlayer player = BukkitAdapter.adapt((Player)sender);
/* 49 */       for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 50 */         if ((!am.getType().isPersistent() || literally) && 
/* 51 */           am.getLocation().getWorld().equals(player.getWorld()) && 
/* 52 */           am.getLocation().distanceSquared(player.getLocation()) <= Math.pow(radius, 2.0D) && 
/* 53 */           am.getEntity().isLoaded()) {
/* 54 */           am.setDespawned();
/*    */           
/* 56 */           MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 57 */           am.getEntity().remove();
/* 58 */           amount++;
/*    */         }
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 64 */     else if (literally == true) {
/* 65 */       amount = MythicMobs.inst().getMobManager().removeAllAllMobs();
/*    */     } else {
/* 67 */       amount = MythicMobs.inst().getMobManager().removeAllMobs();
/*    */     } 
/*    */ 
/*    */     
/* 71 */     sender.sendMessage(ChatColor.GREEN + "Removed " + amount + " Mythic Mobs!");
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 77 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 82 */     return "mythicmobs.command.mobs.killall";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 87 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 92 */     return "killall";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 97 */     return new String[] { "ka" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\KillAllCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */