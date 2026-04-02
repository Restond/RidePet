/*    */ package lumine.utils.tasks.builder;
/*    */ 
/*    */ import io.lumine.utils.promise.ThreadContext;
/*    */ import io.lumine.utils.tasks.builder.TaskBuilder;
/*    */ import javax.annotation.Nonnull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class TaskBuilderImpl
/*    */   implements TaskBuilder
/*    */ {
/* 17 */   static final TaskBuilder INSTANCE = new io.lumine.utils.tasks.builder.TaskBuilderImpl();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   private final TaskBuilder.ThreadContextual sync = (TaskBuilder.ThreadContextual)new ThreadContextualBuilder(ThreadContext.SYNC);
/* 24 */   private final TaskBuilder.ThreadContextual async = (TaskBuilder.ThreadContextual)new ThreadContextualBuilder(ThreadContext.ASYNC);
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public TaskBuilder.ThreadContextual sync() {
/* 30 */     return this.sync;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public TaskBuilder.ThreadContextual async() {
/* 36 */     return this.async;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */