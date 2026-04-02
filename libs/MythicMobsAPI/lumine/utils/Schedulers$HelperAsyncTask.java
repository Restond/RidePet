/*     */ package lumine.utils;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.logging.Log;
/*     */ import io.lumine.utils.tasks.LumineExecutors;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class HelperAsyncTask
/*     */   implements Runnable, Task
/*     */ {
/*     */   private final Consumer<Task> backingTask;
/*     */   private final ScheduledFuture<?> future;
/* 201 */   private final AtomicInteger counter = new AtomicInteger(0);
/* 202 */   private final AtomicBoolean cancelled = new AtomicBoolean(false);
/*     */   
/*     */   private HelperAsyncTask(Consumer<Task> backingTask, long delay, TimeUnit delayUnit, long interval, TimeUnit intervalUnit) {
/* 205 */     this.backingTask = backingTask;
/* 206 */     this.future = LumineExecutors.asyncHelper().scheduleAtFixedRate(this, delayUnit.toNanos(delay), intervalUnit.toNanos(interval), TimeUnit.NANOSECONDS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 211 */     if (this.cancelled.get()) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 216 */       this.backingTask.accept(this);
/* 217 */       this.counter.incrementAndGet();
/* 218 */     } catch (Throwable e) {
/* 219 */       Log.severe("[SCHEDULER] Exception thrown whilst executing task");
/* 220 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimesRan() {
/* 226 */     return this.counter.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean stop() {
/* 231 */     if (!this.cancelled.getAndSet(true)) {
/* 232 */       this.future.cancel(false);
/* 233 */       return true;
/*     */     } 
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBukkitId() {
/* 241 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTerminated() {
/* 246 */     return this.cancelled.get();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Schedulers$HelperAsyncTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */