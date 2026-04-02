/*    */ package saukiya.sxattribute.data.attribute.sub.defence;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.EntityEffect;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReflectionAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public ReflectionAttribute() {
/* 31 */     super("Reflection", 2, new SXAttributeType[] { SXAttributeType.DEFENCE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 36 */     if (eventData instanceof DamageEventData && 
/* 37 */       probability(getAttributes()[0])) {
/* 38 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 39 */       if (!damageEventData.getEffectiveAttributeList().contains("Real") && !damageEventData.getEffectiveAttributeList().contains("Block")) {
/* 40 */         damageEventData.getEffectiveAttributeList().add(getName());
/* 41 */         double damage = damageEventData.getDamage() * getAttributes()[1] / 100.0D;
/* 42 */         LivingEntity damager = damageEventData.getDamager();
/* 43 */         EntityDamageByEntityEvent event = new EntityDamageByEntityEvent((Entity)damageEventData.getEntity(), (Entity)damager, EntityDamageEvent.DamageCause.CUSTOM, damage);
/* 44 */         Bukkit.getPluginManager().callEvent((Event)event);
/* 45 */         if (event.isCancelled()) {
/*    */           return;
/*    */         }
/* 48 */         damager.playEffect(EntityEffect.HURT);
/* 49 */         damager.setHealth((damager.getHealth() < event.getFinalDamage()) ? 0.0D : (damager.getHealth() - event.getFinalDamage()));
/* 50 */         damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__REFLECTION, new Object[] { getDf().format(damage) }));
/* 51 */         Message.send(damager, Message.PLAYER__BATTLE__REFLECTION, new Object[] { getFirstPerson(), damageEventData.getEntityName(), getDf().format(damage) });
/* 52 */         Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__REFLECTION, new Object[] { damageEventData.getDamagerName(), getFirstPerson(), getDf().format(damage) });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 60 */     if (string.equalsIgnoreCase("ReflectionRate"))
/* 61 */       return getDf().format(getAttributes()[0]); 
/* 62 */     if (string.equalsIgnoreCase("Reflection")) {
/* 63 */       return getDf().format(getAttributes()[1]);
/*    */     }
/* 65 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 71 */     return Arrays.asList(new String[] { "ReflectionRate", "Reflection" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 76 */     if (lore.contains(Config.getConfig().getString("Attribute.ReflectionRate.Name"))) {
/* 77 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 78 */       return true;
/*    */     } 
/* 80 */     if (lore.contains(Config.getConfig().getString("Attribute.Reflection.Name"))) {
/* 81 */       getAttributes()[1] = getAttributes()[1] + Double.valueOf(getNumber(lore)).doubleValue();
/* 82 */       return true;
/*    */     } 
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 89 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.ReflectionRate.Value") + getAttributes()[1] * Config.getConfig().getInt("Attribute.Reflection.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\defence\ReflectionAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */