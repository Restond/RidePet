/*    */ package lumine.utils.tasks;
/*    */ 
/*    */ import io.lumine.utils.logging.Log;
/*    */ import io.lumine.utils.tasks.LumineAsyncExecutor;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LumineExecutors
/*    */ {
/*    */   private static final Consumer<Throwable> EXCEPTION_CONSUMER;
/*    */   
/*    */   static {
/* 18 */     EXCEPTION_CONSUMER = (throwable -> {
/*    */         Log.severe("[SCHEDULER] Exception thrown whilst executing task");
/*    */         throwable.printStackTrace();
/*    */       });
/*    */   }
/* 23 */   private static final Executor SYNC_BUKKIT = (Executor)new BukkitSyncExecutor(null);
/* 24 */   private static final Executor ASYNC_BUKKIT = (Executor)new BukkitAsyncExecutor(null);
/* 25 */   private static final LumineAsyncExecutor ASYNC_HELPER = new LumineAsyncExecutor();
/*    */   
/*    */   public static Executor sync() {
/* 28 */     return SYNC_BUKKIT;
/*    */   }
/*    */   
/*    */   public static ScheduledExecutorService asyncHelper() {
/* 32 */     return (ScheduledExecutorService)ASYNC_HELPER;
/*    */   }
/*    */   
/*    */   public static Executor asyncBukkit() {
/* 36 */     return ASYNC_BUKKIT;
/*    */   }
/*    */   
/*    */   public static void shutdown() {
/* 40 */     ASYNC_HELPER.cancelRepeatingTasks();
/*    */   }
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
/*    */   public static Runnable wrapRunnable(Runnable runnable) {
/* 58 */     return (Runnable)new SchedulerWrappedRunnable(runnable, null);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\LumineExecutors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */