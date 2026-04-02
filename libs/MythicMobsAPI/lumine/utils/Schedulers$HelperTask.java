/*     */ package lumine.utils;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.logging.Log;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.function.Consumer;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
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
/*     */ class HelperTask
/*     */   extends BukkitRunnable
/*     */   implements Task
/*     */ {
/*     */   private final Consumer<Task> backingTask;
/* 149 */   private final AtomicInteger counter = new AtomicInteger(0);
/* 150 */   private final AtomicBoolean cancelled = new AtomicBoolean(false);
/*     */   
/*     */   private HelperTask(Consumer<Task> backingTask) {
/* 153 */     this.backingTask = backingTask;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 158 */     if (this.cancelled.get()) {
/* 159 */       cancel();
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 164 */       this.backingTask.accept(this);
/* 165 */       this.counter.incrementAndGet();
/* 166 */     } catch (Throwable e) {
/* 167 */       Log.severe("[SCHEDULER] Exception thrown whilst executing task");
/* 168 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 171 */     if (this.cancelled.get()) {
/* 172 */       cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimesRan() {
/* 178 */     return this.counter.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean stop() {
/* 183 */     return !this.cancelled.getAndSet(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBukkitId() {
/* 188 */     return getTaskId();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTerminated() {
/* 193 */     return this.cancelled.get();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Schedulers$HelperTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */