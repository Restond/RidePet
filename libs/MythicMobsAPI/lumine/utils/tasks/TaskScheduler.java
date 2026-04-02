/*     */ package lumine.utils.tasks;
/*     */ 
/*     */ import io.lumine.utils.Delegates;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.Consumer;
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
/*     */ public interface TaskScheduler
/*     */   extends Executor
/*     */ {
/*     */   @Nonnull
/*     */   ThreadContext getContext();
/*     */   
/*     */   @Nonnull
/*     */   default <T> Promise<T> supply(@Nonnull Supplier<T> supplier) {
/*  38 */     Objects.requireNonNull(supplier, "supplier");
/*  39 */     return Promise.supplying(getContext(), supplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default <T> Promise<T> call(@Nonnull Callable<T> callable) {
/*  51 */     Objects.requireNonNull(callable, "callable");
/*  52 */     return Promise.supplying(getContext(), Delegates.callableToSupplier(callable));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default Promise<Void> run(@Nonnull Runnable runnable) {
/*  63 */     Objects.requireNonNull(runnable, "runnable");
/*  64 */     return Promise.supplying(getContext(), Delegates.runnableToSupplier(runnable));
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
/*     */   @Nonnull
/*     */   default <T> Promise<T> supplyLater(@Nonnull Supplier<T> supplier, long delayTicks) {
/*  77 */     Objects.requireNonNull(supplier, "supplier");
/*  78 */     return Promise.supplyingDelayed(getContext(), supplier, delayTicks);
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
/*     */   @Nonnull
/*     */   default <T> Promise<T> supplyLater(@Nonnull Supplier<T> supplier, long delay, @Nonnull TimeUnit unit) {
/*  92 */     Objects.requireNonNull(supplier, "supplier");
/*  93 */     return Promise.supplyingDelayed(getContext(), supplier, delay, unit);
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
/*     */   @Nonnull
/*     */   default <T> Promise<T> callLater(@Nonnull Callable<T> callable, long delayTicks) {
/* 106 */     Objects.requireNonNull(callable, "callable");
/* 107 */     return Promise.supplyingDelayed(getContext(), Delegates.callableToSupplier(callable), delayTicks);
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
/*     */   @Nonnull
/*     */   default <T> Promise<T> callLater(@Nonnull Callable<T> callable, long delay, @Nonnull TimeUnit unit) {
/* 121 */     Objects.requireNonNull(callable, "callable");
/* 122 */     return Promise.supplyingDelayed(getContext(), Delegates.callableToSupplier(callable), delay, unit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default Promise<Void> runLater(@Nonnull Runnable runnable, long delayTicks) {
/* 134 */     Objects.requireNonNull(runnable, "runnable");
/* 135 */     return Promise.supplyingDelayed(getContext(), Delegates.runnableToSupplier(runnable), delayTicks);
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
/*     */   @Nonnull
/*     */   default Promise<Void> runLater(@Nonnull Runnable runnable, long delay, @Nonnull TimeUnit unit) {
/* 148 */     Objects.requireNonNull(runnable, "runnable");
/* 149 */     return Promise.supplyingDelayed(getContext(), Delegates.runnableToSupplier(runnable), delay, unit);
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
/*     */   @Nonnull
/*     */   Task runRepeating(@Nonnull Consumer<Task> paramConsumer, long paramLong1, long paramLong2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   Task runRepeating(@Nonnull Consumer<Task> paramConsumer, long paramLong1, @Nonnull TimeUnit paramTimeUnit1, long paramLong2, @Nonnull TimeUnit paramTimeUnit2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default Task runRepeating(@Nonnull Runnable runnable, long delayTicks, long intervalTicks) {
/* 186 */     return runRepeating(Delegates.runnableToConsumer(runnable), delayTicks, intervalTicks);
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
/*     */   
/*     */   @Nonnull
/*     */   default Task runRepeating(@Nonnull Runnable runnable, long delay, @Nonnull TimeUnit delayUnit, long interval, @Nonnull TimeUnit intervalUnit) {
/* 201 */     return runRepeating(Delegates.runnableToConsumer(runnable), delay, delayUnit, interval, intervalUnit);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\TaskScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */