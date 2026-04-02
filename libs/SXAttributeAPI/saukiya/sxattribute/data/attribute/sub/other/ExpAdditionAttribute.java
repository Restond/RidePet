/*    */ package saukiya.sxattribute.data.attribute.sub.other;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
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
/*    */ public class ExpAdditionAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public ExpAdditionAttribute() {
/* 31 */     super("ExpAddition", 1, new SXAttributeType[] { SXAttributeType.OTHER });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 37 */     Bukkit.getPluginManager().registerEvents(SXAttribute.isSxLevel() ? (Listener)new OnSXExpChangeListener(this, null) : (Listener)new OnExpChangeListener(this, null), (Plugin)getPlugin());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 47 */     return string.equalsIgnoreCase("ExpAddition") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 52 */     return Collections.singletonList("ExpAddition");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 57 */     if (lore.contains(Config.getConfig().getString("Attribute.ExpAddition.Name"))) {
/* 58 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 59 */       return true;
/*    */     } 
/* 61 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 66 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.ExpAddition.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\other\ExpAdditionAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */