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
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PoisonAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public PoisonAttribute() {
/* 28 */     super("Poison", 1, new SXAttributeType[] { SXAttributeType.DAMAGE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 33 */     if (eventData instanceof DamageEventData) {
/* 34 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 35 */       if (getAttributes()[0] > 0.0D && probability(getAttributes()[0] - damageEventData.getEntityAttributeDoubles("Toughness")[0])) {
/* 36 */         int tick = 40 + SXAttribute.getRandom().nextInt(60);
/* 37 */         damageEventData.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.POISON, tick, SXAttribute.getRandom().nextInt(2) + 1));
/* 38 */         damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__POISON, new Object[] { getDf().format(tick / 20.0D) }));
/* 39 */         Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__POISON, new Object[] { damageEventData.getEntityName(), getFirstPerson(), getDf().format(tick / 20.0D) });
/* 40 */         Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__POISON, new Object[] { getFirstPerson(), damageEventData.getDamagerName(), getDf().format(tick / 20.0D) });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 47 */     return string.equalsIgnoreCase("Poison") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 52 */     return Collections.singletonList("Poison");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 57 */     if (lore.contains(Config.getConfig().getString("Attribute.Poison.Name"))) {
/* 58 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 59 */       return true;
/*    */     } 
/* 61 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 66 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Poison.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\PoisonAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */