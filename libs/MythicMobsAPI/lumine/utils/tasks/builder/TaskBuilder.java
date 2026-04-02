/*    */ package lumine.utils.tasks.builder;
/*    */ 
/*    */ import io.lumine.utils.promise.ThreadContext;
/*    */ import io.lumine.utils.tasks.builder.TaskBuilderImpl;
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
/*    */ public interface TaskBuilder
/*    */ {
/*    */   @Nonnull
/*    */   static io.lumine.utils.tasks.builder.TaskBuilder newBuilder() {
/* 21 */     return TaskBuilderImpl.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   default ThreadContextual on(@Nonnull ThreadContext context) {
/* 33 */     switch (null.$SwitchMap$io$lumine$utils$promise$ThreadContext[context.ordinal()]) {
/*    */       case 1:
/* 35 */         return sync();
/*    */       case 2:
/* 37 */         return async();
/*    */     } 
/* 39 */     throw new AssertionError();
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   ThreadContextual sync();
/*    */   
/*    */   @Nonnull
/*    */   ThreadContextual async();
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */