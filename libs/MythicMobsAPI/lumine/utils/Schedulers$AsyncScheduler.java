/*     */ package lumine.utils;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.plugin.LoaderUtils;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.LumineExecutors;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.utils.tasks.TaskScheduler;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class AsyncScheduler
/*     */   implements TaskScheduler
/*     */ {
/*     */   private AsyncScheduler() {}
/*     */   
/*     */   public void execute(Runnable runnable) {
/* 120 */     LumineExecutors.asyncHelper().execute(runnable);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ThreadContext getContext() {
/* 126 */     return ThreadContext.ASYNC;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Task runRepeating(@Nonnull Consumer<Task> consumer, long delayTicks, long intervalTicks) {
/* 132 */     Objects.requireNonNull(consumer, "consumer");
/* 133 */     Schedulers.HelperTask task = new Schedulers.HelperTask(consumer, null);
/* 134 */     task.runTaskTimerAsynchronously((Plugin)LoaderUtils.getPlugin(), delayTicks, intervalTicks);
/* 135 */     return (Task)task;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Task runRepeating(@Nonnull Consumer<Task> consumer, long delay, @Nonnull TimeUnit delayUnit, long interval, @Nonnull TimeUnit intervalUnit) {
/* 141 */     Objects.requireNonNull(consumer, "consumer");
/* 142 */     return (Task)new Schedulers.HelperAsyncTask(consumer, delay, delayUnit, interval, intervalUnit, null);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Schedulers$AsyncScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */