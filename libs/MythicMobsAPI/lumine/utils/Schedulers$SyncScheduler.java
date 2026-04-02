/*     */ package lumine.utils;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.plugin.LoaderUtils;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.LumineExecutors;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.utils.tasks.TaskScheduler;
/*     */ import io.lumine.utils.tasks.Ticks;
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
/*     */ final class SyncScheduler
/*     */   implements TaskScheduler
/*     */ {
/*     */   private SyncScheduler() {}
/*     */   
/*     */   public void execute(Runnable runnable) {
/*  91 */     LumineExecutors.sync().execute(runnable);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ThreadContext getContext() {
/*  97 */     return ThreadContext.SYNC;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Task runRepeating(@Nonnull Consumer<Task> consumer, long delayTicks, long intervalTicks) {
/* 103 */     Objects.requireNonNull(consumer, "consumer");
/* 104 */     Schedulers.HelperTask task = new Schedulers.HelperTask(consumer, null);
/* 105 */     task.runTaskTimer((Plugin)LoaderUtils.getPlugin(), delayTicks, intervalTicks);
/* 106 */     return (Task)task;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Task runRepeating(@Nonnull Consumer<Task> consumer, long delay, @Nonnull TimeUnit delayUnit, long interval, @Nonnull TimeUnit intervalUnit) {
/* 112 */     return runRepeating(consumer, Ticks.from(delay, delayUnit), Ticks.from(interval, intervalUnit));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Schedulers$SyncScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */