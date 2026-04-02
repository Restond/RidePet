/*     */ package lumine.utils.tasks.builder;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import io.lumine.utils.promise.ThreadContext;
/*     */ import io.lumine.utils.tasks.builder.ContextualPromiseBuilder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ContextualPromiseBuilderImpl
/*     */   implements ContextualPromiseBuilder
/*     */ {
/*     */   private final ThreadContext context;
/*     */   
/*     */   ContextualPromiseBuilderImpl(ThreadContext context) {
/* 165 */     this.context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Promise<T> supply(@Nonnull Supplier<T> supplier) {
/* 171 */     return Schedulers.get(this.context).supply(supplier);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Promise<T> call(@Nonnull Callable<T> callable) {
/* 177 */     return Schedulers.get(this.context).call(callable);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<Void> run(@Nonnull Runnable runnable) {
/* 183 */     return Schedulers.get(this.context).run(runnable);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilderImpl$ContextualPromiseBuilderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */