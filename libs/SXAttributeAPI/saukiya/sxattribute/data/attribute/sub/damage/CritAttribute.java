/*    */ package saukiya.sxattribute.data.attribute.sub.damage;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.Arrays;
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
/*    */ 
/*    */ public class CritAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public CritAttribute() {
/* 26 */     super("Crit", 2, new SXAttributeType[] { SXAttributeType.DAMAGE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 31 */     if (eventData instanceof DamageEventData && 
/* 32 */       probability(getAttributes()[0])) {
/* 33 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 34 */       damageEventData.setCrit(true);
/* 35 */       damageEventData.setDamage(damageEventData.getDamage() * getAttributes()[1] / 100.0D);
/* 36 */       Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__CRIT, new Object[] { getFirstPerson(), damageEventData.getEntityName() });
/* 37 */       Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__CRIT, new Object[] { damageEventData.getDamagerName(), getFirstPerson() });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 44 */     if (string.equalsIgnoreCase("CritRate"))
/* 45 */       return getDf().format(getAttributes()[0]); 
/* 46 */     if (string.equalsIgnoreCase("Crit")) {
/* 47 */       return getDf().format(getAttributes()[1]);
/*    */     }
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> introduction() {
/* 55 */     return Arrays.asList(new String[] { "Chinese: 暴击", "判断几率造成暴击,默认伤害为100%(原伤害)", "", "English: Crit", "Determine the chance to cause a crit", " the default damage is 100% (original damage)" });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 67 */     return Arrays.asList(new String[] { "CritRate", "Crit" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 72 */     if (lore.contains(Config.getConfig().getString("Attribute.Crit.Name"))) {
/* 73 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 74 */       return true;
/*    */     } 
/* 76 */     if (lore.contains(Config.getConfig().getString("Attribute.CritDamage.Name"))) {
/* 77 */       getAttributes()[1] = getAttributes()[1] + Double.valueOf(getNumber(lore)).doubleValue();
/* 78 */       return true;
/*    */     } 
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void correct() {
/* 85 */     if (getAttributes()[0] < 0.0D) getAttributes()[0] = 0.0D; 
/* 86 */     if (getAttributes()[1] < 100.0D) getAttributes()[1] = 100.0D;
/*    */   
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 91 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Crit.Value") + getAttributes()[1] * Config.getConfig().getInt("Attribute.CritDamage.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\CritAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */