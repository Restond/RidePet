/*    */ package saukiya.sxattribute.data.attribute.sub.update;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.UpdateEventData;
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
/*    */ public class SpeedAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   public SpeedAttribute() {
/* 24 */     super("Speed", 1, new SXAttributeType[] { SXAttributeType.UPDATE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 29 */     if (eventData instanceof UpdateEventData && ((UpdateEventData)eventData).getEntity() instanceof Player) {
/* 30 */       Player player = (Player)((UpdateEventData)eventData).getEntity();
/* 31 */       player.setWalkSpeed((float)(getAttributes()[0] / 500.0D));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 37 */     return string.equalsIgnoreCase("Speed") ? getDf().format(getAttributes()[0]) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 42 */     return Collections.singletonList("Speed");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 47 */     if (lore.contains(Config.getConfig().getString("Attribute.Speed.Name"))) {
/* 48 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 49 */       return true;
/*    */     } 
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void correct() {
/* 56 */     if (getAttributes()[0] <= 0.0D) getAttributes()[0] = 1.0D; 
/* 57 */     if (getAttributes()[0] > 400.0D) getAttributes()[0] = 400.0D;
/*    */   
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 62 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Speed.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\su\\update\SpeedAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */