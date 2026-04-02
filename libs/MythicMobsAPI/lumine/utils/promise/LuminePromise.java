/*     */ package lumine.utils.promise;
/*     */ 
/*     */ import com.google.common.util.concurrent.FutureCallback;
/*     */ import com.google.common.util.concurrent.Futures;
/*     */ import com.google.common.util.concurrent.ListenableFuture;
/*     */ import io.lumine.utils.logging.Log;
/*     */ import io.lumine.utils.plugin.LoaderUtils;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.LumineExecutors;
/*     */ import io.lumine.utils.tasks.Ticks;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.CompletionStage;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class LuminePromise<V>
/*     */   implements Promise<V>
/*     */ {
/*     */   private static final Consumer<Throwable> EXCEPTION_CONSUMER;
/*     */   
/*     */   static {
/*  38 */     EXCEPTION_CONSUMER = (throwable -> {
/*     */         Log.severe("[SCHEDULER] Exception thrown whilst executing task");
/*     */         throwable.printStackTrace();
/*     */       });
/*     */   }
/*     */   @Nonnull
/*     */   static <U> io.lumine.utils.promise.LuminePromise<U> empty() {
/*  45 */     return new io.lumine.utils.promise.LuminePromise<>();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   static <U> io.lumine.utils.promise.LuminePromise<U> completed(@Nullable U value) {
/*  50 */     return new io.lumine.utils.promise.LuminePromise<>(value);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   static <U> io.lumine.utils.promise.LuminePromise<U> exceptionally(@Nonnull Throwable t) {
/*  55 */     return new io.lumine.utils.promise.LuminePromise<>(t);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   static <U> Promise<U> wrapFuture(@Nonnull Future<U> future) {
/*  60 */     if (future instanceof CompletableFuture) {
/*  61 */       return new io.lumine.utils.promise.LuminePromise<>(((CompletableFuture)future).thenApply(Function.identity()));
/*     */     }
/*  63 */     if (future instanceof CompletionStage) {
/*     */       
/*  65 */       CompletionStage<U> fut = (CompletionStage<U>)future;
/*  66 */       return new io.lumine.utils.promise.LuminePromise<>(fut.toCompletableFuture().thenApply(Function.identity()));
/*     */     } 
/*  68 */     if (future instanceof ListenableFuture) {
/*  69 */       ListenableFuture<U> fut = (ListenableFuture<U>)future;
/*  70 */       io.lumine.utils.promise.LuminePromise<U> promise = empty();
/*  71 */       promise.supplied.set(true);
/*     */       
/*  73 */       Futures.addCallback(fut, (FutureCallback)new Object(promise));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  85 */       return promise;
/*     */     } 
/*     */     
/*  88 */     if (future.isDone()) {
/*     */       try {
/*  90 */         return completed(future.get());
/*  91 */       } catch (ExecutionException e) {
/*  92 */         return exceptionally(e);
/*  93 */       } catch (InterruptedException e) {
/*  94 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*  97 */     return Promise.supplyingExceptionallyAsync(future::get);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   private final AtomicBoolean supplied = new AtomicBoolean(false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   private final AtomicBoolean cancelled = new AtomicBoolean(false);
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   private final CompletableFuture<V> fut;
/*     */ 
/*     */ 
/*     */   
/*     */   private LuminePromise() {
/* 119 */     this.fut = new CompletableFuture<>();
/*     */   }
/*     */   
/*     */   private LuminePromise(@Nullable V v) {
/* 123 */     this.fut = CompletableFuture.completedFuture(v);
/* 124 */     this.supplied.set(true);
/*     */   }
/*     */   
/*     */   private LuminePromise(@Nonnull Throwable t) {
/* 128 */     (this.fut = new CompletableFuture<>()).completeExceptionally(t);
/* 129 */     this.supplied.set(true);
/*     */   }
/*     */   
/*     */   private LuminePromise(@Nonnull CompletableFuture<V> fut) {
/* 133 */     this.fut = Objects.<CompletableFuture<V>>requireNonNull(fut, "future");
/* 134 */     this.supplied.set(true);
/* 135 */     this.cancelled.set(fut.isCancelled());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void executeSync(@Nonnull Runnable runnable) {
/* 141 */     if (ThreadContext.forCurrentThread() == ThreadContext.SYNC) {
/* 142 */       LumineExecutors.wrapRunnable(runnable).run();
/*     */     } else {
/* 144 */       LumineExecutors.sync().execute(runnable);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeAsync(@Nonnull Runnable runnable) {
/* 149 */     LumineExecutors.asyncHelper().execute(runnable);
/*     */   }
/*     */   
/*     */   private void executeDelayedSync(@Nonnull Runnable runnable, long delayTicks) {
/* 153 */     if (delayTicks <= 0L) {
/* 154 */       executeSync(runnable);
/*     */     } else {
/* 156 */       Bukkit.getScheduler().runTaskLater((Plugin)LoaderUtils.getPlugin(), LumineExecutors.wrapRunnable(runnable), delayTicks);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeDelayedAsync(@Nonnull Runnable runnable, long delayTicks) {
/* 161 */     if (delayTicks <= 0L) {
/* 162 */       executeAsync(runnable);
/*     */     } else {
/* 164 */       Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)LoaderUtils.getPlugin(), LumineExecutors.wrapRunnable(runnable), delayTicks);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeDelayedSync(@Nonnull Runnable runnable, long delay, TimeUnit unit) {
/* 169 */     if (delay <= 0L) {
/* 170 */       executeSync(runnable);
/*     */     } else {
/* 172 */       Bukkit.getScheduler().runTaskLater((Plugin)LoaderUtils.getPlugin(), LumineExecutors.wrapRunnable(runnable), Ticks.from(delay, unit));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeDelayedAsync(@Nonnull Runnable runnable, long delay, TimeUnit unit) {
/* 177 */     if (delay <= 0L) {
/* 178 */       executeAsync(runnable);
/*     */     } else {
/* 180 */       LumineExecutors.asyncHelper().schedule(LumineExecutors.wrapRunnable(runnable), delay, unit);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean complete(V value) {
/* 185 */     return (!this.cancelled.get() && this.fut.complete(value));
/*     */   }
/*     */   
/*     */   private boolean completeExceptionally(@Nonnull Throwable t) {
/* 189 */     return (!this.cancelled.get() && this.fut.completeExceptionally(t));
/*     */   }
/*     */   
/*     */   private void markAsSupplied() {
/* 193 */     if (!this.supplied.compareAndSet(false, true)) {
/* 194 */       throw new IllegalStateException("Promise is already being supplied.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cancel(boolean mayInterruptIfRunning) {
/* 202 */     this.cancelled.set(true);
/* 203 */     return this.fut.cancel(mayInterruptIfRunning);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 208 */     return this.fut.isCancelled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/* 213 */     return this.fut.isDone();
/*     */   }
/*     */ 
/*     */   
/*     */   public V get() throws InterruptedException, ExecutionException {
/* 218 */     return this.fut.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public V get(long timeout, @Nonnull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
/* 223 */     return this.fut.get(timeout, unit);
/*     */   }
/*     */ 
/*     */   
/*     */   public V join() {
/* 228 */     return this.fut.join();
/*     */   }
/*     */ 
/*     */   
/*     */   public V getNow(V valueIfAbsent) {
/* 233 */     return this.fut.getNow(valueIfAbsent);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompletableFuture<V> toCompletableFuture() {
/* 238 */     return this.fut.thenApply((Function)Function.identity());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 243 */     cancel();
/* 244 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTerminated() {
/* 249 */     return isCancelled();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supply(@Nullable V value) {
/* 257 */     markAsSupplied();
/* 258 */     complete(value);
/* 259 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyException(@Nonnull Throwable exception) {
/* 265 */     markAsSupplied();
/* 266 */     completeExceptionally(exception);
/* 267 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplySync(@Nonnull Supplier<V> supplier) {
/* 273 */     markAsSupplied();
/* 274 */     executeSync((Runnable)new SupplyRunnable(this, supplier, null));
/* 275 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyAsync(@Nonnull Supplier<V> supplier) {
/* 281 */     markAsSupplied();
/* 282 */     executeAsync((Runnable)new SupplyRunnable(this, supplier, null));
/* 283 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyDelayedSync(@Nonnull Supplier<V> supplier, long delayTicks) {
/* 289 */     markAsSupplied();
/* 290 */     executeDelayedSync((Runnable)new SupplyRunnable(this, supplier, null), delayTicks);
/* 291 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyDelayedSync(@Nonnull Supplier<V> supplier, long delay, @Nonnull TimeUnit unit) {
/* 297 */     markAsSupplied();
/* 298 */     executeDelayedSync((Runnable)new SupplyRunnable(this, supplier, null), delay, unit);
/* 299 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyDelayedAsync(@Nonnull Supplier<V> supplier, long delayTicks) {
/* 305 */     markAsSupplied();
/* 306 */     executeDelayedAsync((Runnable)new SupplyRunnable(this, supplier, null), delayTicks);
/* 307 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyDelayedAsync(@Nonnull Supplier<V> supplier, long delay, @Nonnull TimeUnit unit) {
/* 313 */     markAsSupplied();
/* 314 */     executeDelayedAsync((Runnable)new SupplyRunnable(this, supplier, null), delay, unit);
/* 315 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyExceptionallySync(@Nonnull Callable<V> callable) {
/* 321 */     markAsSupplied();
/* 322 */     executeSync((Runnable)new ThrowingSupplyRunnable(this, callable, null));
/* 323 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyExceptionallyAsync(@Nonnull Callable<V> callable) {
/* 329 */     markAsSupplied();
/* 330 */     executeAsync((Runnable)new ThrowingSupplyRunnable(this, callable, null));
/* 331 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyExceptionallyDelayedSync(@Nonnull Callable<V> callable, long delayTicks) {
/* 337 */     markAsSupplied();
/* 338 */     executeDelayedSync((Runnable)new ThrowingSupplyRunnable(this, callable, null), delayTicks);
/* 339 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyExceptionallyDelayedSync(@Nonnull Callable<V> callable, long delay, @Nonnull TimeUnit unit) {
/* 345 */     markAsSupplied();
/* 346 */     executeDelayedSync((Runnable)new ThrowingSupplyRunnable(this, callable, null), delay, unit);
/* 347 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyExceptionallyDelayedAsync(@Nonnull Callable<V> callable, long delayTicks) {
/* 353 */     markAsSupplied();
/* 354 */     executeDelayedAsync((Runnable)new ThrowingSupplyRunnable(this, callable, null), delayTicks);
/* 355 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> supplyExceptionallyDelayedAsync(@Nonnull Callable<V> callable, long delay, @Nonnull TimeUnit unit) {
/* 361 */     markAsSupplied();
/* 362 */     executeDelayedAsync((Runnable)new ThrowingSupplyRunnable(this, callable, null), delay, unit);
/* 363 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenApplySync(@Nonnull Function<? super V, ? extends U> fn) {
/* 369 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 370 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeSync((Runnable)new ApplyRunnable(this, paramLuminePromise, paramFunction, value, null));
/*     */           } 
/*     */         });
/* 377 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenApplyAsync(@Nonnull Function<? super V, ? extends U> fn) {
/* 383 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 384 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeAsync((Runnable)new ApplyRunnable(this, paramLuminePromise, paramFunction, value, null));
/*     */           } 
/*     */         });
/* 391 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenApplyDelayedSync(@Nonnull Function<? super V, ? extends U> fn, long delayTicks) {
/* 397 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 398 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedSync((Runnable)new ApplyRunnable(this, paramLuminePromise, paramFunction, value, null), paramLong);
/*     */           } 
/*     */         });
/* 405 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenApplyDelayedSync(@Nonnull Function<? super V, ? extends U> fn, long delay, @Nonnull TimeUnit unit) {
/* 411 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 412 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedSync((Runnable)new ApplyRunnable(this, paramLuminePromise, paramFunction, value, null), paramLong, paramTimeUnit);
/*     */           } 
/*     */         });
/* 419 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenApplyDelayedAsync(@Nonnull Function<? super V, ? extends U> fn, long delayTicks) {
/* 425 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 426 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedAsync((Runnable)new ApplyRunnable(this, paramLuminePromise, paramFunction, value, null), paramLong);
/*     */           } 
/*     */         });
/* 433 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenApplyDelayedAsync(@Nonnull Function<? super V, ? extends U> fn, long delay, @Nonnull TimeUnit unit) {
/* 439 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 440 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedAsync((Runnable)new ApplyRunnable(this, paramLuminePromise, paramFunction, value, null), paramLong, paramTimeUnit);
/*     */           } 
/*     */         });
/* 447 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenComposeSync(@Nonnull Function<? super V, ? extends Promise<U>> fn) {
/* 453 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 454 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeSync((Runnable)new ComposeRunnable(this, paramLuminePromise, paramFunction, value, true, null));
/*     */           } 
/*     */         });
/* 461 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenComposeAsync(@Nonnull Function<? super V, ? extends Promise<U>> fn) {
/* 467 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 468 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeAsync((Runnable)new ComposeRunnable(this, paramLuminePromise, paramFunction, value, false, null));
/*     */           } 
/*     */         });
/* 475 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenComposeDelayedSync(@Nonnull Function<? super V, ? extends Promise<U>> fn, long delayTicks) {
/* 481 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 482 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedSync((Runnable)new ComposeRunnable(this, paramLuminePromise, paramFunction, value, true, null), paramLong);
/*     */           } 
/*     */         });
/* 489 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenComposeDelayedSync(@Nonnull Function<? super V, ? extends Promise<U>> fn, long delay, @Nonnull TimeUnit unit) {
/* 495 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 496 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedSync((Runnable)new ComposeRunnable(this, paramLuminePromise, paramFunction, value, true, null), paramLong, paramTimeUnit);
/*     */           } 
/*     */         });
/* 503 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenComposeDelayedAsync(@Nonnull Function<? super V, ? extends Promise<U>> fn, long delayTicks) {
/* 509 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 510 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedAsync((Runnable)new ComposeRunnable(this, paramLuminePromise, paramFunction, value, false, null), paramLong);
/*     */           } 
/*     */         });
/* 517 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <U> Promise<U> thenComposeDelayedAsync(@Nonnull Function<? super V, ? extends Promise<U>> fn, long delay, @Nonnull TimeUnit unit) {
/* 523 */     io.lumine.utils.promise.LuminePromise<U> promise = empty();
/* 524 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t != null) {
/*     */             paramLuminePromise.completeExceptionally(t);
/*     */           } else {
/*     */             executeDelayedAsync((Runnable)new ComposeRunnable(this, paramLuminePromise, paramFunction, value, false, null), paramLong, paramTimeUnit);
/*     */           } 
/*     */         });
/* 531 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> exceptionallySync(@Nonnull Function<Throwable, ? extends V> fn) {
/* 537 */     io.lumine.utils.promise.LuminePromise<V> promise = empty();
/* 538 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t == null) {
/*     */             paramLuminePromise.complete(value);
/*     */           } else {
/*     */             executeSync((Runnable)new ExceptionallyRunnable(this, paramLuminePromise, paramFunction, t, null));
/*     */           } 
/*     */         });
/* 545 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> exceptionallyAsync(@Nonnull Function<Throwable, ? extends V> fn) {
/* 551 */     io.lumine.utils.promise.LuminePromise<V> promise = empty();
/* 552 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t == null) {
/*     */             paramLuminePromise.complete(value);
/*     */           } else {
/*     */             executeAsync((Runnable)new ExceptionallyRunnable(this, paramLuminePromise, paramFunction, t, null));
/*     */           } 
/*     */         });
/* 559 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> exceptionallyDelayedSync(@Nonnull Function<Throwable, ? extends V> fn, long delayTicks) {
/* 565 */     io.lumine.utils.promise.LuminePromise<V> promise = empty();
/* 566 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t == null) {
/*     */             paramLuminePromise.complete(value);
/*     */           } else {
/*     */             executeDelayedSync((Runnable)new ExceptionallyRunnable(this, paramLuminePromise, paramFunction, t, null), paramLong);
/*     */           } 
/*     */         });
/* 573 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> exceptionallyDelayedSync(@Nonnull Function<Throwable, ? extends V> fn, long delay, @Nonnull TimeUnit unit) {
/* 579 */     io.lumine.utils.promise.LuminePromise<V> promise = empty();
/* 580 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t == null) {
/*     */             paramLuminePromise.complete(value);
/*     */           } else {
/*     */             executeDelayedSync((Runnable)new ExceptionallyRunnable(this, paramLuminePromise, paramFunction, t, null), paramLong, paramTimeUnit);
/*     */           } 
/*     */         });
/* 587 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> exceptionallyDelayedAsync(@Nonnull Function<Throwable, ? extends V> fn, long delayTicks) {
/* 593 */     io.lumine.utils.promise.LuminePromise<V> promise = empty();
/* 594 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t == null) {
/*     */             paramLuminePromise.complete(value);
/*     */           } else {
/*     */             executeDelayedAsync((Runnable)new ExceptionallyRunnable(this, paramLuminePromise, paramFunction, t, null), paramLong);
/*     */           } 
/*     */         });
/* 601 */     return promise;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<V> exceptionallyDelayedAsync(@Nonnull Function<Throwable, ? extends V> fn, long delay, @Nonnull TimeUnit unit) {
/* 607 */     io.lumine.utils.promise.LuminePromise<V> promise = empty();
/* 608 */     this.fut.whenComplete((value, t) -> {
/*     */           if (t == null) {
/*     */             paramLuminePromise.complete(value);
/*     */           } else {
/*     */             executeDelayedAsync((Runnable)new ExceptionallyRunnable(this, paramLuminePromise, paramFunction, t, null), paramLong, paramTimeUnit);
/*     */           } 
/*     */         });
/* 615 */     return promise;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\promise\LuminePromise.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */