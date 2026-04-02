/*    */ package lumine.utils.events;
/*    */ 
/*    */ import io.lumine.utils.terminable.Terminable;
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
/*    */ public interface Subscription
/*    */   extends Terminable
/*    */ {
/*    */   boolean isActive();
/*    */   
/*    */   long getCallCounter();
/*    */   
/*    */   boolean unregister();
/*    */   
/*    */   default boolean terminate() {
/* 33 */     unregister();
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\Subscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */