/*    */ package saukiya.sxattribute.listener;
/*    */ 
/*    */ import github.saukiya.sxattribute.listener.OnUpdateStatsListener;
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
/* 58 */     OnUpdateStatsListener.access$000(OnUpdateStatsListener.this).getAttributeManager().loadSlotData(player);
/* 59 */     OnUpdateStatsListener.access$000(OnUpdateStatsListener.this).getAttributeManager().loadEquipmentData((LivingEntity)player);
/* 60 */     OnUpdateStatsListener.access$000(OnUpdateStatsListener.this).getAttributeManager().loadRPGInventoryData(player);
/* 61 */     OnUpdateStatsListener.access$000(OnUpdateStatsListener.this).getAttributeManager().loadHandData((LivingEntity)player);
/* 62 */     OnUpdateStatsListener.access$000(OnUpdateStatsListener.this).getAttributeManager().updateStatsEvent((LivingEntity)player);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnUpdateStatsListener$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */