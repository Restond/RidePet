/*     */ package lumine.utils.tasks;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import io.lumine.utils.logging.Log;
/*     */ import io.lumine.utils.plugin.LoaderUtils;
/*     */ import io.lumine.utils.timings.Timings;
/*     */ import io.lumine.utils.timingslib.MCTiming;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.CompletionException;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public final class Scheduler
/*     */ {
/*     */   private static final Executor SYNC_EXECUTOR;
/*     */   private static final Executor BUKKIT_ASYNC_EXECUTOR;
/*     */   
/*     */   static {
/*  33 */     SYNC_EXECUTOR = (runnable -> bukkit().scheduleSyncDelayedTask((Plugin)LoaderUtils.getPlugin(), runnable));
/*  34 */     BUKKIT_ASYNC_EXECUTOR = (runnable -> bukkit().runTaskAsynchronously((Plugin)LoaderUtils.getPlugin(), runnable));
/*  35 */   } private static final ExecutorService ASYNC_EXECUTOR = Executors.newCachedThreadPool(); private static final Consumer<Throwable> EXCEPTION_CONSUMER;
/*     */   static {
/*  37 */     EXCEPTION_CONSUMER = (throwable -> {
/*     */         Log.severe("[SCHEDULER] Exception thrown whilst executing task");
/*     */         throwable.printStackTrace();
/*     */       });
/*     */   }
/*     */   private static <T> Supplier<T> wrapSupplier(Supplier<T> supplier) {
/*  43 */     return () -> {
/*     */         try (MCTiming t = Timings.get().ofStart("scheduler: " + paramSupplier.getClass().getName())) {
/*     */           return paramSupplier.get();
/*  46 */         } catch (Throwable t) {
/*     */           EXCEPTION_CONSUMER.accept(t);
/*     */           throw new CompletionException(t);
/*     */         } 
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> Supplier<T> wrapCallable(Callable<T> callable) {
/*  55 */     return () -> {
/*     */         try (MCTiming t = Timings.get().ofStart("scheduler: " + paramCallable.getClass().getName())) {
/*     */           return paramCallable.call();
/*  58 */         } catch (Throwable t) {
/*     */           EXCEPTION_CONSUMER.accept(t);
/*     */           throw new CompletionException(t);
/*     */         } 
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   private static Runnable wrapRunnable(Runnable runnable) {
/*  67 */     return () -> {
/*     */         try (MCTiming t = Timings.get().ofStart("scheduler: " + paramRunnable.getClass().getName())) {
/*     */           paramRunnable.run();
/*  70 */         } catch (Throwable t) {
/*     */           EXCEPTION_CONSUMER.accept(t);
/*     */           throw new CompletionException(t);
/*     */         } 
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Executor sync() {
/*  83 */     return SYNC_EXECUTOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Executor bukkitAsync() {
/*  94 */     return BUKKIT_ASYNC_EXECUTOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized ExecutorService internalAsync() {
/* 102 */     return ASYNC_EXECUTOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Executor async() {
/* 110 */     return LoaderUtils.getPlugin().isEnabled() ? bukkitAsync() : internalAsync();
/*     */   }
/*     */   
/*     */   public static BukkitScheduler bukkit() {
/* 114 */     return Bukkit.getScheduler();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> supplySync(Supplier<T> supplier) {
/* 124 */     Preconditions.checkNotNull(supplier, "supplier");
/* 125 */     return CompletableFuture.supplyAsync(wrapSupplier(supplier), sync());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
/* 135 */     Preconditions.checkNotNull(supplier, "supplier");
/* 136 */     return CompletableFuture.supplyAsync(wrapSupplier(supplier), async());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> callSync(Callable<T> callable) {
/* 146 */     Preconditions.checkNotNull(callable, "callable");
/* 147 */     return CompletableFuture.supplyAsync(wrapCallable(callable), sync());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> callAsync(Callable<T> callable) {
/* 157 */     Preconditions.checkNotNull(callable, "callable");
/* 158 */     return CompletableFuture.supplyAsync(wrapCallable(callable), async());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompletableFuture<Void> runSync(Runnable runnable) {
/* 167 */     Preconditions.checkNotNull(runnable, "runnable");
/* 168 */     return CompletableFuture.runAsync(wrapRunnable(runnable), sync());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompletableFuture<Void> runAsync(Runnable runnable) {
/* 177 */     Preconditions.checkNotNull(runnable, "runnable");
/* 178 */     return CompletableFuture.runAsync(wrapRunnable(runnable), async());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> supplyLaterSync(Supplier<T> supplier, long delay) {
/* 189 */     Preconditions.checkNotNull(supplier, "supplier");
/* 190 */     HelperFuture<T> fut = new HelperFuture(null);
/* 191 */     BukkitTask task = bukkit().runTaskLater((Plugin)LoaderUtils.getPlugin(), () -> {
/*     */           HelperFuture.access$500(paramHelperFuture);
/*     */           try (MCTiming t = Timings.get().ofStart("scheduler: " + paramSupplier.getClass().getName())) {
/*     */             T result = paramSupplier.get();
/*     */             paramHelperFuture.complete(result);
/* 196 */           } catch (Throwable t) {
/*     */             EXCEPTION_CONSUMER.accept(t);
/*     */             
/*     */             paramHelperFuture.completeExceptionally(t);
/*     */           } 
/*     */         }delay);
/* 202 */     HelperFuture.access$000(fut, task::cancel);
/* 203 */     return (CompletableFuture<T>)fut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> supplyLaterAsync(Supplier<T> supplier, long delay) {
/* 214 */     Preconditions.checkNotNull(supplier, "supplier");
/* 215 */     HelperFuture<T> fut = new HelperFuture(null);
/* 216 */     BukkitTask task = bukkit().runTaskLaterAsynchronously((Plugin)LoaderUtils.getPlugin(), () -> {
/*     */           HelperFuture.access$500(paramHelperFuture);
/*     */           try {
/*     */             T result = paramSupplier.get();
/*     */             paramHelperFuture.complete(result);
/* 221 */           } catch (Throwable t) {
/*     */             EXCEPTION_CONSUMER.accept(t);
/*     */             
/*     */             paramHelperFuture.completeExceptionally(t);
/*     */           } 
/*     */         }delay);
/* 227 */     HelperFuture.access$000(fut, task::cancel);
/* 228 */     return (CompletableFuture<T>)fut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> callLaterSync(Callable<T> callable, long delay) {
/* 239 */     Preconditions.checkNotNull(callable, "callable");
/* 240 */     HelperFuture<T> fut = new HelperFuture(null);
/* 241 */     BukkitTask task = bukkit().runTaskLater((Plugin)LoaderUtils.getPlugin(), () -> {
/*     */           HelperFuture.access$500(paramHelperFuture);
/*     */           try (MCTiming t = Timings.get().ofStart("scheduler: " + paramCallable.getClass().getName())) {
/*     */             T result = paramCallable.call();
/*     */             paramHelperFuture.complete(result);
/* 246 */           } catch (Throwable t) {
/*     */             EXCEPTION_CONSUMER.accept(t);
/*     */             
/*     */             paramHelperFuture.completeExceptionally(t);
/*     */           } 
/*     */         }delay);
/* 252 */     HelperFuture.access$000(fut, task::cancel);
/* 253 */     return (CompletableFuture<T>)fut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> CompletableFuture<T> callLaterAsync(Callable<T> callable, long delay) {
/* 264 */     Preconditions.checkNotNull(callable, "callable");
/* 265 */     HelperFuture<T> fut = new HelperFuture(null);
/* 266 */     BukkitTask task = bukkit().runTaskLaterAsynchronously((Plugin)LoaderUtils.getPlugin(), () -> {
/*     */           HelperFuture.access$500(paramHelperFuture);
/*     */           try {
/*     */             T result = paramCallable.call();
/*     */             paramHelperFuture.complete(result);
/* 271 */           } catch (Throwable t) {
/*     */             EXCEPTION_CONSUMER.accept(t);
/*     */             
/*     */             paramHelperFuture.completeExceptionally(t);
/*     */           } 
/*     */         }delay);
/* 277 */     HelperFuture.access$000(fut, task::cancel);
/* 278 */     return (CompletableFuture<T>)fut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompletableFuture<Void> runLaterSync(Runnable runnable, long delay) {
/* 288 */     Preconditions.checkNotNull(runnable, "runnable");
/* 289 */     HelperFuture<Void> fut = new HelperFuture(null);
/* 290 */     BukkitTask task = bukkit().runTaskLater((Plugin)LoaderUtils.getPlugin(), () -> {
/*     */           HelperFuture.access$500(paramHelperFuture);
/*     */           try (MCTiming t = Timings.get().ofStart("scheduler: " + paramRunnable.getClass().getName())) {
/*     */             paramRunnable.run();
/*     */             paramHelperFuture.complete(null);
/* 295 */           } catch (Throwable t) {
/*     */             EXCEPTION_CONSUMER.accept(t);
/*     */             
/*     */             paramHelperFuture.completeExceptionally(t);
/*     */           } 
/*     */         }delay);
/* 301 */     HelperFuture.access$000(fut, task::cancel);
/* 302 */     return (CompletableFuture<Void>)fut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompletableFuture<Void> runLaterAsync(Runnable runnable, long delay) {
/* 312 */     Preconditions.checkNotNull(runnable, "runnable");
/* 313 */     HelperFuture<Void> fut = new HelperFuture(null);
/* 314 */     BukkitTask task = bukkit().runTaskLaterAsynchronously((Plugin)LoaderUtils.getPlugin(), () -> {
/*     */           HelperFuture.access$500(paramHelperFuture);
/*     */           try {
/*     */             paramRunnable.run();
/*     */             paramHelperFuture.complete(null);
/* 319 */           } catch (Throwable t) {
/*     */             EXCEPTION_CONSUMER.accept(t);
/*     */             
/*     */             paramHelperFuture.completeExceptionally(t);
/*     */           } 
/*     */         }delay);
/* 325 */     HelperFuture.access$000(fut, task::cancel);
/* 326 */     return (CompletableFuture<Void>)fut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Task runTaskRepeatingSync(Consumer<Task> consumer, long delay, long interval) {
/* 337 */     Preconditions.checkNotNull(consumer, "consumer");
/* 338 */     TaskImpl task = new TaskImpl(consumer, null);
/* 339 */     task.runTaskTimer((Plugin)LoaderUtils.getPlugin(), delay, interval);
/* 340 */     return (Task)task;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Task runTaskRepeatingAsync(Consumer<Task> consumer, long delay, long interval) {
/* 351 */     Preconditions.checkNotNull(consumer, "consumer");
/* 352 */     TaskImpl task = new TaskImpl(consumer, null);
/* 353 */     task.runTaskTimerAsynchronously((Plugin)LoaderUtils.getPlugin(), delay, interval);
/* 354 */     return (Task)task;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Task runTaskRepeatingSync(Runnable runnable, long delay, long interval) {
/* 365 */     Preconditions.checkNotNull(runnable, "runnable");
/* 366 */     return runTaskRepeatingSync((Consumer<Task>)new DelegateConsumer(runnable, null), delay, interval);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Task runTaskRepeatingAsync(Runnable runnable, long delay, long interval) {
/* 377 */     Preconditions.checkNotNull(runnable, "runnable");
/* 378 */     return runTaskRepeatingAsync((Consumer<Task>)new DelegateConsumer(runnable, null), delay, interval);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getHandlerName(Consumer consumer) {
/* 407 */     if (consumer instanceof DelegateConsumer) {
/* 408 */       return ((DelegateConsumer)consumer).getDelegate().getClass().getName();
/*     */     }
/* 410 */     return consumer.getClass().getName();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Scheduler() {
/* 538 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\Scheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */