/*    */ package saukiya.sxattribute.data.attribute.sub.damage;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.util.Config;
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
/*    */ public class HitRateAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public HitRateAttribute() {
/* 23 */     super("HitRate", 1, new SXAttributeType[] { SXAttributeType.OTHER });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {}
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 32 */     return string.equalsIgnoreCase("HitRate") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 37 */     return Collections.singletonList("HitRate");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 42 */     if (lore.contains(Config.getConfig().getString("Attribute.HitRate.Name"))) {
/* 43 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 44 */       return true;
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 51 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.HitRate.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\HitRateAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */