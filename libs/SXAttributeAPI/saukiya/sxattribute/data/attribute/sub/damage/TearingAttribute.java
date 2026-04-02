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
/*    */ import org.bukkit.plugin.Plugin;
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
/*    */ public class TearingAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public TearingAttribute() {
/* 31 */     super("Tearing", 1, new SXAttributeType[] { SXAttributeType.DAMAGE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 36 */     if (eventData instanceof DamageEventData) {
/* 37 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 38 */       if (getAttributes()[0] > 0.0D && probability(getAttributes()[0] - damageEventData.getEntityAttributeDoubles("Toughness")[0])) {
/* 39 */         int size = SXAttribute.getRandom().nextInt(3) + 1;
/* 40 */         double tearingDamage = damageEventData.getEntity().getHealth() / 100.0D;
/* 41 */         (new Object(this, size, damageEventData, tearingDamage))
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
/* 62 */           .runTaskTimer((Plugin)getPlugin(), 5L, size);
/* 63 */         damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__TEARING, new Object[] { getDf().format(tearingDamage * 12.0D / size) }));
/* 64 */         Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__TEARING, new Object[] { damageEventData.getEntityName(), getFirstPerson(), getDf().format(tearingDamage * 12.0D / size) });
/* 65 */         Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__TEARING, new Object[] { getFirstPerson(), damageEventData.getDamagerName(), getDf().format(tearingDamage * 12.0D / size) });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 72 */     return string.equalsIgnoreCase("Tearing") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 77 */     return Collections.singletonList("Tearing");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 82 */     if (lore.contains(Config.getConfig().getString("Attribute.Tearing.Name"))) {
/* 83 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 84 */       return true;
/*    */     } 
/* 86 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 91 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Tearing.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\TearingAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */