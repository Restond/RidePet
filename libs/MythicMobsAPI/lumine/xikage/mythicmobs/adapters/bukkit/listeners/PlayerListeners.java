/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.listeners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ 
/*    */ public class PlayerListeners
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler(priority = EventPriority.HIGH)
/*    */   public void PlayerLoginEvent(PlayerJoinEvent e) {
/* 16 */     MythicMobs.inst().getEntityManager().getOnlinePlayers().add(e.getPlayer());
/* 17 */     if (e.getPlayer().isOp() == true && 
/* 18 */       MythicMobs.inst().getIsUpdateAvailable() == true) {
/* 19 */       e.getPlayer().sendMessage(MythicMobs.menu_header);
/* 20 */       e.getPlayer().sendMessage(ChatColor.GOLD + ">> " + "A new version of MythicMobs is available from BukkitDev!");
/* 21 */       e.getPlayer().sendMessage(ChatColor.GOLD + ">> Update @ " + ChatColor.AQUA + "http://dev.bukkit.org/bukkit-plugins/mythicmobs/");
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGH)
/*    */   public void PlayerLogoffEvent(PlayerQuitEvent e) {
/* 27 */     MythicMobs.inst().getEntityManager().getOnlinePlayers().remove(e.getPlayer());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\listeners\PlayerListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */