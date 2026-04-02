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
/*    */ public class IgnitionAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public IgnitionAttribute() {
/* 26 */     super("Ignition", 1, new SXAttributeType[] { SXAttributeType.DAMAGE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 31 */     if (eventData instanceof DamageEventData) {
/* 32 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 33 */       if (getAttributes()[0] > 0.0D && probability(getAttributes()[0] - damageEventData.getEntityAttributeDoubles("Toughness")[0])) {
/* 34 */         damageEventData.getEntity().setFireTicks(40 + SXAttribute.getRandom().nextInt(60));
/* 35 */         damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__IGNITION, new Object[] { getDf().format(damageEventData.getEntity().getFireTicks() / 20.0D) }));
/* 36 */         Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__IGNITION, new Object[] { damageEventData.getEntityName(), getFirstPerson(), getDf().format(damageEventData.getEntity().getFireTicks() / 20.0D) });
/* 37 */         Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__IGNITION, new Object[] { getFirstPerson(), damageEventData.getDamagerName(), getDf().format(damageEventData.getEntity().getFireTicks() / 20.0D) });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 44 */     return string.equalsIgnoreCase("Ignition") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 49 */     return Collections.singletonList("Ignition");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 54 */     if (lore.contains(Config.getConfig().getString("Attribute.Ignition.Name"))) {
/* 55 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 56 */       return true;
/*    */     } 
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 63 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Ignition.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\IgnitionAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */