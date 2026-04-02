/*    */ package lumine.utils.tasks.builder;
/*    */ 
/*    */ import io.lumine.utils.promise.ThreadContext;
/*    */ import io.lumine.utils.tasks.builder.ContextualPromiseBuilder;
/*    */ import io.lumine.utils.tasks.builder.ContextualTaskBuilder;
/*    */ import io.lumine.utils.tasks.builder.TaskBuilder;
/*    */ import io.lumine.utils.tasks.builder.TaskBuilderImpl;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import javax.annotation.Nonnull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ThreadContextualBuilder
/*    */   implements TaskBuilder.ThreadContextual
/*    */ {
/*    */   private final ThreadContext context;
/*    */   private final ContextualPromiseBuilder instant;
/*    */   
/*    */   ThreadContextualBuilder(ThreadContext context) {
/* 44 */     this.context = context;
/* 45 */     this.instant = (ContextualPromiseBuilder)new TaskBuilderImpl.ContextualPromiseBuilderImpl(context);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public ContextualPromiseBuilder now() {
/* 51 */     return this.instant;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public TaskBuilder.DelayedTick after(long ticks) {
/* 57 */     return (TaskBuilder.DelayedTick)new TaskBuilderImpl.DelayedTickBuilder(this.context, ticks);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public TaskBuilder.DelayedTime after(long duration, @Nonnull TimeUnit unit) {
/* 63 */     return (TaskBuilder.DelayedTime)new TaskBuilderImpl.DelayedTimeBuilder(this.context, duration, unit);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public ContextualTaskBuilder afterAndEvery(long ticks) {
/* 69 */     return (ContextualTaskBuilder)new TaskBuilderImpl.ContextualTaskBuilderTickImpl(this.context, ticks, ticks);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public ContextualTaskBuilder afterAndEvery(long duration, @Nonnull TimeUnit unit) {
/* 75 */     return (ContextualTaskBuilder)new TaskBuilderImpl.ContextualTaskBuilderTimeImpl(this.context, duration, unit, duration, unit);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public ContextualTaskBuilder every(long ticks) {
/* 81 */     return (ContextualTaskBuilder)new TaskBuilderImpl.ContextualTaskBuilderTickImpl(this.context, 0L, ticks);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public ContextualTaskBuilder every(long duration, @Nonnull TimeUnit unit) {
/* 87 */     return (ContextualTaskBuilder)new TaskBuilderImpl.ContextualTaskBuilderTimeImpl(this.context, 0L, TimeUnit.NANOSECONDS, duration, unit);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilderImpl$ThreadContextualBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */