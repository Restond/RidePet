/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ 
/*    */ 
/*    */ 
/*    */ class EmptyTiming
/*    */   extends MCTiming
/*    */ {
/*    */   public final MCTiming startTiming() {
/* 11 */     return this;
/*    */   }
/*    */   
/*    */   public final void stopTiming() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\EmptyTiming.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */