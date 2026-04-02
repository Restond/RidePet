/*    */ package saukiya.sxattribute.data.attribute.sub.other;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.sub.other.ExpAdditionAttribute;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import github.saukiya.sxlevel.event.ChangeType;
/*    */ import github.saukiya.sxlevel.event.SXExpChangeEvent;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
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
/*    */ class OnSXExpChangeListener
/*    */   implements Listener
/*    */ {
/*    */   private OnSXExpChangeListener() {}
/*    */   
/*    */   @EventHandler
/*    */   private void onExpChangeEvent(SXExpChangeEvent event) {
/* 91 */     if (event.isCancelled() || !event.getType().equals(ChangeType.ADD)) {
/*    */       return;
/*    */     }
/* 94 */     Player player = event.getPlayer();
/* 95 */     Double expAddition = Double.valueOf(SXAttribute.getApi().getEntityAllData((LivingEntity)player, new github.saukiya.sxattribute.data.attribute.SXAttributeData[0]).getSubAttribute("ExpAddition").getAttributes()[0]);
/* 96 */     if (event.getAmount() > 0 && expAddition.doubleValue() > 0.0D) {
/* 97 */       int exp = (int)(event.getAmount() * expAddition.doubleValue() / 100.0D);
/* 98 */       event.setAmount(exp + event.getAmount());
/* 99 */       Message.send(player, Message.getMsg(Message.PLAYER__EXP_ADDITION, new Object[] { Integer.valueOf(event.getAmount()), expAddition }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\other\ExpAdditionAttribute$OnSXExpChangeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */