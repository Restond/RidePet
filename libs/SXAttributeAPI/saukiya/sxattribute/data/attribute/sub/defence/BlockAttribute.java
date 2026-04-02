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
/*    */ public class BlockAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public BlockAttribute() {
/* 26 */     super("Block", 2, new SXAttributeType[] { SXAttributeType.DEFENCE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 31 */     if (eventData instanceof DamageEventData && 
/* 32 */       probability(getAttributes()[0])) {
/* 33 */       DamageEventData damageEventData = (DamageEventData)eventData;
/* 34 */       if (!damageEventData.getEffectiveAttributeList().contains("Real") && !damageEventData.getEffectiveAttributeList().contains("Reflection")) {
/* 35 */         damageEventData.getEffectiveAttributeList().add(getName());
/* 36 */         double blockDamage = damageEventData.getDamage() * getAttributes()[1] / 100.0D;
/* 37 */         damageEventData.setDamage(damageEventData.getDamage() - blockDamage);
/* 38 */         damageEventData.sendHolo(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__BLOCK, new Object[] { getDf().format(blockDamage) }));
/* 39 */         Message.send(damageEventData.getDamager(), Message.PLAYER__BATTLE__BLOCK, new Object[] { damageEventData.getEntityName(), getFirstPerson(), getDf().format(blockDamage) });
/* 40 */         Message.send(damageEventData.getEntity(), Message.PLAYER__BATTLE__BLOCK, new Object[] { getFirstPerson(), damageEventData.getDamagerName(), getDf().format(blockDamage) });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 48 */     if (string.equalsIgnoreCase("BlockRate"))
/* 49 */       return getDf().format(getAttributes()[0]); 
/* 50 */     if (string.equalsIgnoreCase("Block")) {
/* 51 */       return getDf().format(getAttributes()[1]);
/*    */     }
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 59 */     return Arrays.asList(new String[] { "BlockRate", "Block" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 64 */     if (lore.contains(Config.getConfig().getString("Attribute.BlockRate.Name"))) {
/* 65 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 66 */       return true;
/*    */     } 
/* 68 */     if (lore.contains(Config.getConfig().getString("Attribute.Block.Name"))) {
/* 69 */       getAttributes()[1] = getAttributes()[1] + Double.valueOf(getNumber(lore)).doubleValue();
/* 70 */       return true;
/*    */     } 
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 77 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.BlockRate.Value") + getAttributes()[1] * Config.getConfig().getInt("Attribute.Block.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\defence\BlockAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */