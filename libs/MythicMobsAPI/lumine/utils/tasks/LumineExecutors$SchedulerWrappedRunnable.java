/*    */ package lumine.utils.tasks;
/*    */ 
/*    */ import io.lumine.utils.interfaces.Delegate;
/*    */ import io.lumine.utils.tasks.LumineExecutors;
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
/*    */ final class SchedulerWrappedRunnable
/*    */   implements Runnable, Delegate<Runnable>
/*    */ {
/*    */   private final Runnable delegate;
/*    */   
/*    */   private SchedulerWrappedRunnable(Runnable delegate) {
/* 65 */     this.delegate = delegate;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/* 71 */       this.delegate.run();
/* 72 */     } catch (Throwable t) {
/* 73 */       LumineExecutors.access$300().accept(t);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Runnable getDelegate() {
/* 79 */     return this.delegate;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\LumineExecutors$SchedulerWrappedRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */