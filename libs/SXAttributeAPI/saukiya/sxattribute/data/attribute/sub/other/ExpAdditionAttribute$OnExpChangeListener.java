/*    */ package saukiya.sxattribute.data.attribute.sub.other;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.sub.other.ExpAdditionAttribute;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerExpChangeEvent;
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
/*    */ 
/*    */ class OnExpChangeListener
/*    */   implements Listener
/*    */ {
/*    */   private OnExpChangeListener() {}
/*    */   
/*    */   @EventHandler
/*    */   private void onExpChangeEvent(PlayerExpChangeEvent event) {
/* 75 */     Player player = event.getPlayer();
/* 76 */     Double expAddition = Double.valueOf(SXAttribute.getApi().getEntityAllData((LivingEntity)player, new github.saukiya.sxattribute.data.attribute.SXAttributeData[0]).getSubAttribute("ExpAddition").getAttributes()[0]);
/* 77 */     if (event.getAmount() > 0 && expAddition.doubleValue() > 0.0D) {
/* 78 */       int exp = (int)(event.getAmount() * expAddition.doubleValue() / 100.0D);
/* 79 */       event.setAmount(exp + event.getAmount());
/* 80 */       Message.send(player, Message.getMsg(Message.PLAYER__EXP_ADDITION, new Object[] { Integer.valueOf(event.getAmount()), expAddition }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\other\ExpAdditionAttribute$OnExpChangeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */