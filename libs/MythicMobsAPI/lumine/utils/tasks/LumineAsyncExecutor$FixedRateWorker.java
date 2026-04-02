/*     */ package lumine.utils.tasks;
/*     */ 
/*     */ import io.lumine.utils.tasks.LumineAsyncExecutor;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class FixedRateWorker
/*     */   implements Runnable
/*     */ {
/*     */   private final Runnable delegate;
/* 104 */   private final ReentrantLock lock = new ReentrantLock();
/* 105 */   private final AtomicInteger running = new AtomicInteger(0);
/*     */   
/*     */   private FixedRateWorker(Runnable delegate) {
/* 108 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 121 */     if (this.running.incrementAndGet() > 2) {
/* 122 */       this.running.decrementAndGet();
/*     */       
/*     */       return;
/*     */     } 
/* 126 */     LumineAsyncExecutor.access$100(LumineAsyncExecutor.this).execute(() -> {
/*     */           this.lock.lock();
/*     */           try {
/*     */             this.delegate.run();
/*     */           } finally {
/*     */             this.lock.unlock();
/*     */             this.running.decrementAndGet();
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\LumineAsyncExecutor$FixedRateWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */