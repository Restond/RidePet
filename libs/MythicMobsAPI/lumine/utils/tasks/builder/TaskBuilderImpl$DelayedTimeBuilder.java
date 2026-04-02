/*     */ package lumine.utils.tasks.builder;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.builder.ContextualTaskBuilder;
/*     */ import io.lumine.utils.tasks.builder.TaskBuilder;
/*     */ import io.lumine.utils.tasks.builder.TaskBuilderImpl;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.TimeUnit;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class DelayedTimeBuilder
/*     */   implements TaskBuilder.DelayedTime
/*     */ {
/*     */   private final ThreadContext context;
/*     */   private final long delay;
/*     */   private final TimeUnit delayUnit;
/*     */   
/*     */   DelayedTimeBuilder(ThreadContext context, long delay, TimeUnit delayUnit) {
/* 131 */     this.context = context;
/* 132 */     this.delay = delay;
/* 133 */     this.delayUnit = delayUnit;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Promise<T> supply(@Nonnull Supplier<T> supplier) {
/* 139 */     return Schedulers.get(this.context).supplyLater(supplier, this.delay, this.delayUnit);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Promise<T> call(@Nonnull Callable<T> callable) {
/* 145 */     return Schedulers.get(this.context).callLater(callable, this.delay, this.delayUnit);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<Void> run(@Nonnull Runnable runnable) {
/* 151 */     return Schedulers.get(this.context).runLater(runnable, this.delay, this.delayUnit);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ContextualTaskBuilder every(long duration, TimeUnit unit) {
/* 157 */     return (ContextualTaskBuilder)new TaskBuilderImpl.ContextualTaskBuilderTimeImpl(this.context, this.delay, this.delayUnit, duration, unit);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilderImpl$DelayedTimeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */