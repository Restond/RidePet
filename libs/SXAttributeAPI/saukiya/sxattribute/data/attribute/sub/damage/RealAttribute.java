/*    */ package saukiya.sxattribute.data.attribute.sub.damage;
/*    */ 
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
/*    */ public class RealAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public RealAttribute() {
/* 25 */     super("Real", 1, new SXAttributeType[] { SXAttributeType.DAMAGE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 30 */     if (eventData instanceof DamageEventData && 
/* 31 */       probability(getAttributes()[0])) {
/* 32 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 33 */       damageEventData.getEffectiveAttributeList().add(getName());
/* 34 */       damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__REAL, new Object[0]));
/* 35 */       Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__REAL, new Object[] { damageEventData.getEntityName(), getFirstPerson() });
/* 36 */       Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__REAL, new Object[] { getFirstPerson(), damageEventData.getDamagerName() });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 43 */     return string.equalsIgnoreCase("Real") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 48 */     return Collections.singletonList("Real");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 53 */     if (lore.contains(Config.getConfig().getString("Attribute.Real.Name"))) {
/* 54 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 55 */       return true;
/*    */     } 
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 62 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Real.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\RealAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */