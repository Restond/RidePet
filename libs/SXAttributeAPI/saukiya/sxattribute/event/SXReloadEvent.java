/*    */ package saukiya.sxattribute.event;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
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
/*    */ public class SXReloadEvent
/*    */   extends Event
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   public CommandSender getSender() {
/* 19 */     return this.sender;
/*    */   }
/*    */   private CommandSender sender;
/*    */   public SXReloadEvent(CommandSender sender) {
/* 23 */     this.sender = sender;
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


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\event\SXReloadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */