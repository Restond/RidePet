/*    */ package saukiya.sxattribute.data.attribute.sub.defence;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.util.Config;
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
/*    */ 
/*    */ 
/*    */ public class HealthRegenAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public HealthRegenAttribute() {
/* 30 */     super("HealthRegen", 1, new SXAttributeType[] { SXAttributeType.OTHER });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 35 */     (new Object(this))
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 64 */       .runTaskTimer((Plugin)getPlugin(), 19L, 20L);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {}
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 73 */     return string.equalsIgnoreCase("HealthRegen") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 78 */     return Collections.singletonList("HealthRegen");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 83 */     if (lore.contains(Config.getConfig().getString("Attribute.HealthRegen.Name"))) {
/* 84 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 85 */       return true;
/*    */     } 
/* 87 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 92 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.HealthRegen.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\defence\HealthRegenAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */