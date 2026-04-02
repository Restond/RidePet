/*    */ package saukiya.sxattribute.data.attribute.sub.damage;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*    */ import github.saukiya.sxattribute.listener.OnHealthChangeDisplayListener;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LifeStealAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public LifeStealAttribute() {
/* 30 */     super("LifeSteal", 2, new SXAttributeType[] { SXAttributeType.DAMAGE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 35 */     if (eventData instanceof DamageEventData && 
/* 36 */       probability(getAttributes()[0])) {
/* 37 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 38 */       LivingEntity damager = damageEventData.getDamager();
/* 39 */       double maxHealth = OnHealthChangeDisplayListener.getMaxHealth(damager);
/* 40 */       double lifeHealth = damageEventData.getDamage() * getAttributes()[1] / 100.0D;
/* 41 */       EntityRegainHealthEvent event = new EntityRegainHealthEvent((Entity)damager, lifeHealth, EntityRegainHealthEvent.RegainReason.CUSTOM);
/* 42 */       Bukkit.getPluginManager().callEvent((Event)event);
/* 43 */       if (event.isCancelled()) {
/*    */         return;
/*    */       }
/* 46 */       lifeHealth = (maxHealth < damager.getHealth() + event.getAmount()) ? (maxHealth - damager.getHealth()) : event.getAmount();
/* 47 */       damager.setHealth(damager.getHealth() + lifeHealth);
/* 48 */       damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__LIFE_STEAL, new Object[] { getDf().format(lifeHealth) }));
/* 49 */       Message.send(damager, Message.PLAYER__BATTLE__LIFE_STEAL, new Object[] { damageEventData.getEntityName(), getFirstPerson(), getDf().format(lifeHealth) });
/* 50 */       Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__LIFE_STEAL, new Object[] { getFirstPerson(), damageEventData.getDamagerName(), getDf().format(lifeHealth) });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 57 */     return string.equalsIgnoreCase("LifeStealRate") ? getDf().format(getAttributes()[0]) : (string.equalsIgnoreCase("LifeSteal") ? getDf().format(getAttributes()[1]) : null);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 62 */     return Arrays.asList(new String[] { "LifeStealRate", "LifeSteal" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 67 */     if (lore.contains(Config.getConfig().getString("Attribute.LifeStealRate.Name"))) {
/* 68 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 69 */       return true;
/*    */     } 
/* 71 */     if (lore.contains(Config.getConfig().getString("Attribute.LifeSteal.Name"))) {
/* 72 */       getAttributes()[1] = getAttributes()[1] + Double.valueOf(getNumber(lore)).doubleValue();
/* 73 */       return true;
/*    */     } 
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 80 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.LifeStealRate.Value") + getAttributes()[1] * Config.getConfig().getInt("Attribute.LifeSteal.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\LifeStealAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */