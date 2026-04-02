/*    */ package saukiya.sxattribute.data.attribute.sub.damage;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LightningAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public LightningAttribute() {
/* 26 */     super("Lightning", 1, new SXAttributeType[] { SXAttributeType.DAMAGE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 31 */     if (eventData instanceof DamageEventData) {
/* 32 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 33 */       if (getAttributes()[0] > 0.0D && probability(getAttributes()[0] - damageEventData.getEntityAttributeDoubles("Toughness")[0])) {
/* 34 */         damageEventData.getEntity().getWorld().strikeLightningEffect(damageEventData.getEntity().getLocation());
/* 35 */         double lightningDamage = damageEventData.getEntity().getHealth() * SXAttribute.getRandom().nextDouble() / 10.0D;
/* 36 */         damageEventData.getEntity().setHealth(damageEventData.getEntity().getHealth() - lightningDamage);
/* 37 */         damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__LIGHTNING, new Object[] { getDf().format(lightningDamage) }));
/* 38 */         Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__LIGHTNING, new Object[] { damageEventData.getEntityName(), getFirstPerson(), getDf().format(lightningDamage) });
/* 39 */         Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__LIGHTNING, new Object[] { getFirstPerson(), damageEventData.getDamagerName(), getDf().format(lightningDamage) });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 46 */     return string.equalsIgnoreCase("Lightning") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 51 */     return Collections.singletonList("Lightning");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 56 */     if (lore.contains(Config.getConfig().getString("Attribute.Lightning.Name"))) {
/* 57 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 58 */       return true;
/*    */     } 
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 65 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Lightning.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\LightningAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */