/*    */ package lumine.utils.tasks;
/*    */ 
/*    */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*    */ import io.lumine.utils.tasks.LumineExecutors;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.WeakHashMap;
/*    */ import java.util.concurrent.AbstractExecutorService;
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.ScheduledFuture;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ 
/*    */ final class LumineAsyncExecutor
/*    */   extends AbstractExecutorService
/*    */   implements ScheduledExecutorService
/*    */ {
/*    */   private final ExecutorService taskService;
/*    */   private final ScheduledExecutorService timerExecutionService;
/* 24 */   private final Set<ScheduledFuture<?>> tasks = Collections.newSetFromMap(new WeakHashMap<>());
/*    */   
/*    */   LumineAsyncExecutor() {
/* 27 */     this.taskService = Executors.newCachedThreadPool((new ThreadFactoryBuilder())
/* 28 */         .setDaemon(true)
/* 29 */         .setNameFormat("helper-scheduler-%d")
/* 30 */         .build());
/*    */     
/* 32 */     this.timerExecutionService = Executors.newSingleThreadScheduledExecutor((new ThreadFactoryBuilder())
/* 33 */         .setDaemon(true)
/* 34 */         .setNameFormat("helper-scheduler-timer")
/* 35 */         .build());
/*    */   }
/*    */ 
/*    */   
/*    */   private ScheduledFuture<?> consumeTask(ScheduledFuture<?> future) {
/* 40 */     this.tasks.add(future);
/* 41 */     return future;
/*    */   }
/*    */   
/*    */   public void cancelRepeatingTasks() {
/* 45 */     for (ScheduledFuture<?> task : this.tasks) {
/* 46 */       task.cancel(false);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(Runnable runnable) {
/* 52 */     this.taskService.execute(LumineExecutors.wrapRunnable(runnable));
/*    */   }
/*    */ 
/*    */   
/*    */   public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
/* 57 */     Runnable delegate = LumineExecutors.wrapRunnable(command);
/* 58 */     return consumeTask(this.timerExecutionService.schedule(() -> this.taskService.execute(paramRunnable), delay, unit));
/*    */   }
/*    */ 
/*    */   
/*    */   public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   
/*    */   public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
/* 68 */     return consumeTask(this.timerExecutionService.scheduleAtFixedRate((Runnable)new FixedRateWorker(this, LumineExecutors.wrapRunnable(command), null), initialDelay, period, unit));
/*    */   }
/*    */ 
/*    */   
/*    */   public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
/* 73 */     return scheduleAtFixedRate(command, initialDelay, delay, unit);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void shutdown() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Runnable> shutdownNow() {
/* 84 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isShutdown() {
/* 89 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTerminated() {
/* 94 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean awaitTermination(long timeout, TimeUnit unit) {
/* 99 */     throw new IllegalStateException("Not shutdown");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\LumineAsyncExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */