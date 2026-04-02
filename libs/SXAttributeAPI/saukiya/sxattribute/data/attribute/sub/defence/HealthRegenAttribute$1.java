/*    */ package saukiya.sxattribute.data.attribute.sub.defence;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.sub.defence.HealthRegenAttribute;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityRegainHealthEvent;
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
/*    */ class null
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run() {
/*    */     try {
/* 39 */       for (Player player : new ArrayList(Bukkit.getOnlinePlayers())) {
/* 40 */         if (player != null && !player.isDead() && player.isOnline()) {
/* 41 */           Double maxHealth = Double.valueOf(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
/* 42 */           if (player.getHealth() < maxHealth.doubleValue()) {
/* 43 */             Double healthRegen = Double.valueOf(SXAttribute.getApi().getEntityAllData((LivingEntity)player, new github.saukiya.sxattribute.data.attribute.SXAttributeData[0]).getSubAttribute("HealthRegen").getAttributes()[0]);
/* 44 */             if (healthRegen.doubleValue() > 0.0D) {
/* 45 */               EntityRegainHealthEvent event = new EntityRegainHealthEvent((Entity)player, healthRegen.doubleValue(), EntityRegainHealthEvent.RegainReason.CUSTOM);
/* 46 */               Bukkit.getPluginManager().callEvent((Event)event);
/* 47 */               if (!event.isCancelled()) {
/* 48 */                 healthRegen = Double.valueOf((event.getAmount() + player.getHealth() > maxHealth.doubleValue()) ? (maxHealth.doubleValue() - player.getHealth()) : event.getAmount());
/* 49 */                 player.setHealth(healthRegen.doubleValue() + player.getHealth());
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/* 55 */     } catch (Exception e) {
/* 56 */       e.printStackTrace();
/* 57 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§4生命恢复系统崩溃 正在重新启动!");
/* 58 */       cancel();
/* 59 */       HealthRegenAttribute.this.onEnable();
/* 60 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c启动完毕!");
/* 61 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c如果此消息连续刷屏，请通过Yum重载本插件");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\defence\HealthRegenAttribute$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */