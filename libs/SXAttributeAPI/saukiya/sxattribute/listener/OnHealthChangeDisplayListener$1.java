/*    */ package saukiya.sxattribute.listener;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.BossBarData;
/*    */ import github.saukiya.sxattribute.data.NameData;
/*    */ import github.saukiya.sxattribute.listener.OnHealthChangeDisplayListener;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
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
/*    */ class null
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run() {
/* 50 */     if (OnHealthChangeDisplayListener.access$000(OnHealthChangeDisplayListener.this).size() > 0) {
/* 51 */       Iterator<BossBarData> bossDataIterator = OnHealthChangeDisplayListener.access$000(OnHealthChangeDisplayListener.this).iterator();
/* 52 */       while (bossDataIterator.hasNext()) {
/* 53 */         BossBarData bossBarData = bossDataIterator.next();
/* 54 */         if (bossBarData.getEntity() != null && !bossBarData.getEntity().isDead() && bossBarData.getTimeMap().size() > 0) {
/* 55 */           Iterator<Map.Entry<Player, Long>> entryIterator = bossBarData.getTimeMap().entrySet().iterator();
/* 56 */           while (entryIterator.hasNext()) {
/* 57 */             Map.Entry<Player, Long> entry = entryIterator.next();
/* 58 */             if (!((Player)entry.getKey()).isOnline() || ((Player)entry.getKey()).isDead() || ((Long)entry.getValue()).longValue() < System.currentTimeMillis()) {
/* 59 */               bossBarData.getBossBar().removePlayer(entry.getKey());
/* 60 */               entryIterator.remove();
/*    */             } 
/*    */           } 
/* 63 */           if (bossBarData.getTimeMap().size() == 0) {
/* 64 */             bossBarData.getBossBar().removeAll();
/* 65 */             bossDataIterator.remove();
/*    */           }  continue;
/*    */         } 
/* 68 */         bossBarData.getBossBar().removeAll();
/* 69 */         bossDataIterator.remove();
/*    */       } 
/*    */     } 
/*    */     
/* 73 */     if (OnHealthChangeDisplayListener.access$100(OnHealthChangeDisplayListener.this).size() > 0) {
/* 74 */       Iterator<NameData> nameDataIterator = OnHealthChangeDisplayListener.access$100(OnHealthChangeDisplayListener.this).iterator();
/* 75 */       while (nameDataIterator.hasNext()) {
/* 76 */         NameData nameData = nameDataIterator.next();
/* 77 */         if (nameData.getEntity() == null || nameData.getEntity().isDead() || nameData.getEntity().getHealth() == OnHealthChangeDisplayListener.getMaxHealth(nameData.getEntity()) || nameData.getTick().longValue() < System.currentTimeMillis()) {
/* 78 */           if (nameData.getEntity() != null) {
/* 79 */             nameData.getEntity().setCustomName(nameData.getName());
/* 80 */             nameData.getEntity().setCustomNameVisible(nameData.isVisible());
/*    */           } 
/* 82 */           nameDataIterator.remove();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnHealthChangeDisplayListener$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */