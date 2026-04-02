/*      */ package lumine.utils.promise;
/*      */ 
/*      */ import io.lumine.utils.Delegates;
/*      */ import io.lumine.utils.promise.LuminePromise;
/*      */ import io.lumine.utils.promise.ThreadContext;
/*      */ import io.lumine.utils.terminable.Terminable;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.Future;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Supplier;
/*      */ import javax.annotation.Nonnull;
/*      */ import javax.annotation.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface Promise<V>
/*      */   extends Future<V>, Terminable
/*      */ {
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> empty() {
/*   43 */     return (io.lumine.utils.promise.Promise<U>)LuminePromise.empty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static io.lumine.utils.promise.Promise<Void> start() {
/*   53 */     return (io.lumine.utils.promise.Promise<Void>)LuminePromise.completed(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> completed(@Nullable U value) {
/*   65 */     return (io.lumine.utils.promise.Promise<U>)LuminePromise.completed(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> exceptionally(@Nonnull Throwable exception) {
/*   77 */     return (io.lumine.utils.promise.Promise<U>)LuminePromise.exceptionally(exception);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> wrapFuture(@Nonnull Future<U> future) {
/*   96 */     return LuminePromise.wrapFuture(future);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplying(@Nonnull ThreadContext context, @Nonnull Supplier<U> supplier) {
/*  109 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  110 */     return p.supply(context, supplier);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingSync(@Nonnull Supplier<U> supplier) {
/*  122 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  123 */     return p.supplySync(supplier);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingAsync(@Nonnull Supplier<U> supplier) {
/*  135 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  136 */     return p.supplyAsync(supplier);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingDelayed(@Nonnull ThreadContext context, @Nonnull Supplier<U> supplier, long delayTicks) {
/*  151 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  152 */     return p.supplyDelayed(context, supplier, delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingDelayed(@Nonnull ThreadContext context, @Nonnull Supplier<U> supplier, long delay, @Nonnull TimeUnit unit) {
/*  168 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  169 */     return p.supplyDelayed(context, supplier, delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingDelayedSync(@Nonnull Supplier<U> supplier, long delayTicks) {
/*  183 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  184 */     return p.supplyDelayedSync(supplier, delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingDelayedSync(@Nonnull Supplier<U> supplier, long delay, @Nonnull TimeUnit unit) {
/*  199 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  200 */     return p.supplyDelayedSync(supplier, delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingDelayedAsync(@Nonnull Supplier<U> supplier, long delayTicks) {
/*  214 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  215 */     return p.supplyDelayedAsync(supplier, delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingDelayedAsync(@Nonnull Supplier<U> supplier, long delay, @Nonnull TimeUnit unit) {
/*  230 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  231 */     return p.supplyDelayedAsync(supplier, delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionally(@Nonnull ThreadContext context, @Nonnull Callable<U> callable) {
/*  244 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  245 */     return p.supplyExceptionally(context, callable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallySync(@Nonnull Callable<U> callable) {
/*  257 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  258 */     return p.supplyExceptionallySync(callable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallyAsync(@Nonnull Callable<U> callable) {
/*  270 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  271 */     return p.supplyExceptionallyAsync(callable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallyDelayed(@Nonnull ThreadContext context, @Nonnull Callable<U> callable, long delayTicks) {
/*  286 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  287 */     return p.supplyExceptionallyDelayed(context, callable, delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallyDelayed(@Nonnull ThreadContext context, @Nonnull Callable<U> callable, long delay, @Nonnull TimeUnit unit) {
/*  303 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  304 */     return p.supplyExceptionallyDelayed(context, callable, delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallyDelayedSync(@Nonnull Callable<U> callable, long delayTicks) {
/*  318 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  319 */     return p.supplyExceptionallyDelayedSync(callable, delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallyDelayedSync(@Nonnull Callable<U> callable, long delay, @Nonnull TimeUnit unit) {
/*  334 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  335 */     return p.supplyExceptionallyDelayedSync(callable, delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallyDelayedAsync(@Nonnull Callable<U> callable, long delayTicks) {
/*  349 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  350 */     return p.supplyExceptionallyDelayedAsync(callable, delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   static <U> io.lumine.utils.promise.Promise<U> supplyingExceptionallyDelayedAsync(@Nonnull Callable<U> callable, long delay, @Nonnull TimeUnit unit) {
/*  365 */     io.lumine.utils.promise.Promise<U> p = empty();
/*  366 */     return p.supplyExceptionallyDelayedAsync(callable, delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean cancel() {
/*  377 */     return cancel(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   V join();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   V getNow(V paramV);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supply(@Nullable V paramV);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyException(@Nonnull Throwable paramThrowable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> supply(@Nonnull ThreadContext context, @Nonnull Supplier<V> supplier) {
/*  439 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  441 */         return supplySync(supplier);
/*      */       case 2:
/*  443 */         return supplyAsync(supplier);
/*      */     } 
/*  445 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplySync(@Nonnull Supplier<V> paramSupplier);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyAsync(@Nonnull Supplier<V> paramSupplier);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> supplyDelayed(@Nonnull ThreadContext context, @Nonnull Supplier<V> supplier, long delayTicks) {
/*  481 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  483 */         return supplyDelayedSync(supplier, delayTicks);
/*      */       case 2:
/*  485 */         return supplyDelayedAsync(supplier, delayTicks);
/*      */     } 
/*  487 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> supplyDelayed(@Nonnull ThreadContext context, @Nonnull Supplier<V> supplier, long delay, @Nonnull TimeUnit unit) {
/*  504 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  506 */         return supplyDelayedSync(supplier, delay, unit);
/*      */       case 2:
/*  508 */         return supplyDelayedAsync(supplier, delay, unit);
/*      */     } 
/*  510 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyDelayedSync(@Nonnull Supplier<V> paramSupplier, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyDelayedSync(@Nonnull Supplier<V> paramSupplier, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyDelayedAsync(@Nonnull Supplier<V> paramSupplier, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyDelayedAsync(@Nonnull Supplier<V> paramSupplier, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> supplyExceptionally(@Nonnull ThreadContext context, @Nonnull Callable<V> callable) {
/*  574 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  576 */         return supplyExceptionallySync(callable);
/*      */       case 2:
/*  578 */         return supplyExceptionallyAsync(callable);
/*      */     } 
/*  580 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyExceptionallySync(@Nonnull Callable<V> paramCallable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyExceptionallyAsync(@Nonnull Callable<V> paramCallable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> supplyExceptionallyDelayed(@Nonnull ThreadContext context, @Nonnull Callable<V> callable, long delayTicks) {
/*  616 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  618 */         return supplyExceptionallyDelayedSync(callable, delayTicks);
/*      */       case 2:
/*  620 */         return supplyExceptionallyDelayedAsync(callable, delayTicks);
/*      */     } 
/*  622 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> supplyExceptionallyDelayed(@Nonnull ThreadContext context, @Nonnull Callable<V> callable, long delay, @Nonnull TimeUnit unit) {
/*  639 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  641 */         return supplyExceptionallyDelayedSync(callable, delay, unit);
/*      */       case 2:
/*  643 */         return supplyExceptionallyDelayedAsync(callable, delay, unit);
/*      */     } 
/*  645 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyExceptionallyDelayedSync(@Nonnull Callable<V> paramCallable, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyExceptionallyDelayedSync(@Nonnull Callable<V> paramCallable, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyExceptionallyDelayedAsync(@Nonnull Callable<V> paramCallable, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> supplyExceptionallyDelayedAsync(@Nonnull Callable<V> paramCallable, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default <U> io.lumine.utils.promise.Promise<U> thenApply(@Nonnull ThreadContext context, @Nonnull Function<? super V, ? extends U> fn) {
/*  711 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  713 */         return thenApplySync(fn);
/*      */       case 2:
/*  715 */         return thenApplyAsync(fn);
/*      */     } 
/*  717 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenApplySync(@Nonnull Function<? super V, ? extends U> paramFunction);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenApplyAsync(@Nonnull Function<? super V, ? extends U> paramFunction);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default <U> io.lumine.utils.promise.Promise<U> thenApplyDelayed(@Nonnull ThreadContext context, @Nonnull Function<? super V, ? extends U> fn, long delayTicks) {
/*  758 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  760 */         return thenApplyDelayedSync(fn, delayTicks);
/*      */       case 2:
/*  762 */         return thenApplyDelayedAsync(fn, delayTicks);
/*      */     } 
/*  764 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default <U> io.lumine.utils.promise.Promise<U> thenApplyDelayed(@Nonnull ThreadContext context, @Nonnull Function<? super V, ? extends U> fn, long delay, @Nonnull TimeUnit unit) {
/*  782 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  784 */         return thenApplyDelayedSync(fn, delay, unit);
/*      */       case 2:
/*  786 */         return thenApplyDelayedAsync(fn, delay, unit);
/*      */     } 
/*  788 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenApplyDelayedSync(@Nonnull Function<? super V, ? extends U> paramFunction, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenApplyDelayedSync(@Nonnull Function<? super V, ? extends U> paramFunction, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenApplyDelayedAsync(@Nonnull Function<? super V, ? extends U> paramFunction, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenApplyDelayedAsync(@Nonnull Function<? super V, ? extends U> paramFunction, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAccept(@Nonnull ThreadContext context, @Nonnull Consumer<? super V> action) {
/*  857 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  859 */         return thenAcceptSync(action);
/*      */       case 2:
/*  861 */         return thenAcceptAsync(action);
/*      */     } 
/*  863 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptSync(@Nonnull Consumer<? super V> action) {
/*  877 */     return thenApplySync(Delegates.consumerToFunction(action));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptAsync(@Nonnull Consumer<? super V> action) {
/*  890 */     return thenApplyAsync(Delegates.consumerToFunction(action));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptDelayed(@Nonnull ThreadContext context, @Nonnull Consumer<? super V> action, long delayTicks) {
/*  905 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  907 */         return thenAcceptDelayedSync(action, delayTicks);
/*      */       case 2:
/*  909 */         return thenAcceptDelayedAsync(action, delayTicks);
/*      */     } 
/*  911 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptDelayed(@Nonnull ThreadContext context, @Nonnull Consumer<? super V> action, long delay, @Nonnull TimeUnit unit) {
/*  928 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/*  930 */         return thenAcceptDelayedSync(action, delay, unit);
/*      */       case 2:
/*  932 */         return thenAcceptDelayedAsync(action, delay, unit);
/*      */     } 
/*  934 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptDelayedSync(@Nonnull Consumer<? super V> action, long delayTicks) {
/*  949 */     return thenApplyDelayedSync(Delegates.consumerToFunction(action), delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptDelayedSync(@Nonnull Consumer<? super V> action, long delay, @Nonnull TimeUnit unit) {
/*  964 */     return thenApplyDelayedSync(Delegates.consumerToFunction(action), delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptDelayedAsync(@Nonnull Consumer<? super V> action, long delayTicks) {
/*  978 */     return thenApplyDelayedAsync(Delegates.consumerToFunction(action), delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenAcceptDelayedAsync(@Nonnull Consumer<? super V> action, long delay, @Nonnull TimeUnit unit) {
/*  993 */     return thenApplyDelayedAsync(Delegates.consumerToFunction(action), delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRun(@Nonnull ThreadContext context, @Nonnull Runnable action) {
/* 1006 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1008 */         return thenRunSync(action);
/*      */       case 2:
/* 1010 */         return thenRunAsync(action);
/*      */     } 
/* 1012 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunSync(@Nonnull Runnable action) {
/* 1025 */     return thenApplySync(Delegates.runnableToFunction(action));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunAsync(@Nonnull Runnable action) {
/* 1037 */     return thenApplyAsync(Delegates.runnableToFunction(action));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunDelayed(@Nonnull ThreadContext context, @Nonnull Runnable action, long delayTicks) {
/* 1051 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1053 */         return thenRunDelayedSync(action, delayTicks);
/*      */       case 2:
/* 1055 */         return thenRunDelayedAsync(action, delayTicks);
/*      */     } 
/* 1057 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunDelayed(@Nonnull ThreadContext context, @Nonnull Runnable action, long delay, @Nonnull TimeUnit unit) {
/* 1073 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1075 */         return thenRunDelayedSync(action, delay, unit);
/*      */       case 2:
/* 1077 */         return thenRunDelayedAsync(action, delay, unit);
/*      */     } 
/* 1079 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunDelayedSync(@Nonnull Runnable action, long delayTicks) {
/* 1093 */     return thenApplyDelayedSync(Delegates.runnableToFunction(action), delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunDelayedSync(@Nonnull Runnable action, long delay, @Nonnull TimeUnit unit) {
/* 1107 */     return thenApplyDelayedSync(Delegates.runnableToFunction(action), delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunDelayedAsync(@Nonnull Runnable action, long delayTicks) {
/* 1120 */     return thenApplyDelayedAsync(Delegates.runnableToFunction(action), delayTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<Void> thenRunDelayedAsync(@Nonnull Runnable action, long delay, @Nonnull TimeUnit unit) {
/* 1134 */     return thenApplyDelayedAsync(Delegates.runnableToFunction(action), delay, unit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default <U> io.lumine.utils.promise.Promise<U> thenCompose(@Nonnull ThreadContext context, @Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> fn) {
/* 1149 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1151 */         return thenComposeSync(fn);
/*      */       case 2:
/* 1153 */         return thenComposeAsync(fn);
/*      */     } 
/* 1155 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenComposeSync(@Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> paramFunction);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenComposeAsync(@Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> paramFunction);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default <U> io.lumine.utils.promise.Promise<U> thenComposeDelayedSync(@Nonnull ThreadContext context, @Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> fn, long delayTicks) {
/* 1196 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1198 */         return thenComposeDelayedSync(fn, delayTicks);
/*      */       case 2:
/* 1200 */         return thenComposeDelayedAsync(fn, delayTicks);
/*      */     } 
/* 1202 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default <U> io.lumine.utils.promise.Promise<U> thenComposeDelayedSync(@Nonnull ThreadContext context, @Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> fn, long delay, @Nonnull TimeUnit unit) {
/* 1220 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1222 */         return thenComposeDelayedSync(fn, delay, unit);
/*      */       case 2:
/* 1224 */         return thenComposeDelayedAsync(fn, delay, unit);
/*      */     } 
/* 1226 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenComposeDelayedSync(@Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> paramFunction, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenComposeDelayedSync(@Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> paramFunction, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenComposeDelayedAsync(@Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> paramFunction, long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   <U> io.lumine.utils.promise.Promise<U> thenComposeDelayedAsync(@Nonnull Function<? super V, ? extends io.lumine.utils.promise.Promise<U>> paramFunction, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> exceptionally(@Nonnull ThreadContext context, @Nonnull Function<Throwable, ? extends V> fn) {
/* 1297 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1299 */         return exceptionallySync(fn);
/*      */       case 2:
/* 1301 */         return exceptionallySync(fn);
/*      */     } 
/* 1303 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> exceptionallySync(@Nonnull Function<Throwable, ? extends V> paramFunction);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> exceptionallyAsync(@Nonnull Function<Throwable, ? extends V> paramFunction);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> exceptionallyDelayed(@Nonnull ThreadContext context, @Nonnull Function<Throwable, ? extends V> fn, long delayTicks) {
/* 1348 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1350 */         return exceptionallyDelayedSync(fn, delayTicks);
/*      */       case 2:
/* 1352 */         return exceptionallyDelayedAsync(fn, delayTicks);
/*      */     } 
/* 1354 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   default io.lumine.utils.promise.Promise<V> exceptionallyDelayed(@Nonnull ThreadContext context, @Nonnull Function<Throwable, ? extends V> fn, long delay, @Nonnull TimeUnit unit) {
/* 1374 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*      */       case 1:
/* 1376 */         return exceptionallyDelayedSync(fn, delay, unit);
/*      */       case 2:
/* 1378 */         return exceptionallyDelayedAsync(fn, delay, unit);
/*      */     } 
/* 1380 */     throw new AssertionError();
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> exceptionallyDelayedSync(@Nonnull Function<Throwable, ? extends V> paramFunction, long paramLong);
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> exceptionallyDelayedSync(@Nonnull Function<Throwable, ? extends V> paramFunction, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> exceptionallyDelayedAsync(@Nonnull Function<Throwable, ? extends V> paramFunction, long paramLong);
/*      */   
/*      */   @Nonnull
/*      */   io.lumine.utils.promise.Promise<V> exceptionallyDelayedAsync(@Nonnull Function<Throwable, ? extends V> paramFunction, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*      */   
/*      */   CompletableFuture<V> toCompletableFuture();
/*      */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\promise\Promise.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */