/*    */ package saukiya.sxattribute.data.attribute.sub.defence;
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
/*    */ import org.bukkit.Location;
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
/*    */ public class DodgeAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public DodgeAttribute() {
/* 27 */     super("Dodge", 1, new SXAttributeType[] { SXAttributeType.DEFENCE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 32 */     if (eventData instanceof DamageEventData) {
/* 33 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 34 */       if (getAttributes()[0] > 0.0D && probability(getAttributes()[0] - damageEventData.getDamagerAttributeDoubles("HitRate")[0])) {
/* 35 */         damageEventData.setCancelled(true);
/* 36 */         Location loc = damageEventData.getDamager().getLocation().clone();
/* 37 */         loc.setYaw(loc.getYaw() + SXAttribute.getRandom().nextInt(80) - 40.0F);
/* 38 */         damageEventData.getEntity().setVelocity(loc.getDirection().setY(0.1D).multiply(0.7D));
/* 39 */         damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__DODGE, new Object[0]));
/* 40 */         Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__DODGE, new Object[] { damageEventData.getEntityName(), getFirstPerson() });
/* 41 */         Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__DODGE, new Object[] { getFirstPerson(), damageEventData.getDamagerName() });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 48 */     return string.equalsIgnoreCase("Dodge") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 53 */     return Collections.singletonList("Dodge");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 58 */     if (lore.contains(Config.getConfig().getString("Attribute.Dodge.Name"))) {
/* 59 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 60 */       return true;
/*    */     } 
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 67 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Dodge.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\defence\DodgeAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */