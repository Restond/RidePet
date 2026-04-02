/*    */ package saukiya.sxattribute.event;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SXDamageEvent
/*    */   extends Event
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   public DamageEventData getData() {
/* 19 */     return this.data;
/*    */   }
/*    */   private DamageEventData data;
/*    */   public SXDamageEvent(DamageEventData data) {
/* 23 */     this.data = data;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 27 */     return handlers;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 31 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\event\SXDamageEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */