/*    */ package lumine.utils.promise;
/*    */ 
/*    */ import io.lumine.utils.plugin.LoaderUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ThreadContext
/*    */ {
/* 13 */   SYNC,
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   ASYNC;
/*    */   
/*    */   public static io.lumine.utils.promise.ThreadContext forCurrentThread() {
/* 21 */     return forThread(Thread.currentThread());
/*    */   }
/*    */   
/*    */   public static io.lumine.utils.promise.ThreadContext forThread(Thread thread) {
/* 25 */     return (thread == LoaderUtils.getMainThread()) ? SYNC : ASYNC;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\promise\ThreadContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */