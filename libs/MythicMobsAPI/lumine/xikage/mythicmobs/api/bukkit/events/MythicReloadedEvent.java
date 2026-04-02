/*    */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MythicReloadedEvent
/*    */   extends Event
/*    */ {
/*    */   private final MythicMobs inst;
/*    */   
/*    */   public MythicReloadedEvent(MythicMobs inst) {
/* 17 */     this.inst = inst;
/*    */   }
/*    */   
/*    */   public MythicMobs getInstance() {
/* 21 */     return this.inst;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public HandlerList getHandlers() {
/* 31 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 35 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicReloadedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */