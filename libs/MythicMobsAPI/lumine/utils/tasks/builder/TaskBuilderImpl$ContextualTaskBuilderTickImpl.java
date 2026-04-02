/*     */ package lumine.utils.tasks.builder;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.utils.tasks.builder.ContextualTaskBuilder;
/*     */ import io.lumine.utils.tasks.builder.TaskBuilderImpl;
/*     */ import java.util.function.Consumer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ContextualTaskBuilderTickImpl
/*     */   implements ContextualTaskBuilder
/*     */ {
/*     */   private final ThreadContext context;
/*     */   private final long delay;
/*     */   private final long interval;
/*     */   
/*     */   ContextualTaskBuilderTickImpl(ThreadContext context, long delay, long interval) {
/* 193 */     this.context = context;
/* 194 */     this.delay = delay;
/* 195 */     this.interval = interval;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Task consume(@Nonnull Consumer<Task> consumer) {
/* 201 */     return Schedulers.get(this.context).runRepeating(consumer, this.delay, this.interval);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Task run(@Nonnull Runnable runnable) {
/* 207 */     return Schedulers.get(this.context).runRepeating(runnable, this.delay, this.interval);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilderImpl$ContextualTaskBuilderTickImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */