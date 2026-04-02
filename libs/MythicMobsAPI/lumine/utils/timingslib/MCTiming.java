/*   */ package lumine.utils.timingslib;
/*   */ 
/*   */ public abstract class MCTiming implements AutoCloseable {
/*   */   public abstract io.lumine.utils.timingslib.MCTiming startTiming();
/*   */   
/*   */   public abstract void stopTiming();
/*   */   
/*   */   public void close() {
/* 9 */     stopTiming();
/*   */   }
/*   */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\MCTiming.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */