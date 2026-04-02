/*     */ package lumine.utils.tasks.builder;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.builder.ContextualTaskBuilder;
/*     */ import io.lumine.utils.tasks.builder.TaskBuilder;
/*     */ import io.lumine.utils.tasks.builder.TaskBuilderImpl;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nonnull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class DelayedTickBuilder
/*     */   implements TaskBuilder.DelayedTick
/*     */ {
/*     */   private final ThreadContext context;
/*     */   private final long delay;
/*     */   
/*     */   DelayedTickBuilder(ThreadContext context, long delay) {
/*  96 */     this.context = context;
/*  97 */     this.delay = delay;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Promise<T> supply(@Nonnull Supplier<T> supplier) {
/* 103 */     return Schedulers.get(this.context).supplyLater(supplier, this.delay);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Promise<T> call(@Nonnull Callable<T> callable) {
/* 109 */     return Schedulers.get(this.context).callLater(callable, this.delay);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<Void> run(@Nonnull Runnable runnable) {
/* 115 */     return Schedulers.get(this.context).runLater(runnable, this.delay);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ContextualTaskBuilder every(long ticks) {
/* 121 */     return (ContextualTaskBuilder)new TaskBuilderImpl.ContextualTaskBuilderTickImpl(this.context, this.delay, ticks);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilderImpl$DelayedTickBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */