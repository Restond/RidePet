/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.sub.ReloadCommand;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run() {
/* 50 */     Bukkit.getOnlinePlayers().forEach(player -> {
/*    */           plugin.getAttributeManager().loadSlotData(player);
/*    */           plugin.getAttributeManager().updateStatsEvent((LivingEntity)player);
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\ReloadCommand$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */