/*    */ package lumine.utils;
/*    */ 
/*    */ import io.lumine.utils.Delegates;
/*    */ import io.lumine.utils.interfaces.Delegate;
/*    */ import java.util.function.Consumer;
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
/*    */ final class RunnableToConsumer<T>
/*    */   implements Consumer<T>, Delegate<Runnable>
/*    */ {
/*    */   private final Runnable delegate;
/*    */   
/*    */   private RunnableToConsumer(Runnable delegate) {
/* 57 */     this.delegate = delegate;
/*    */   } public Runnable getDelegate() {
/* 59 */     return this.delegate;
/*    */   }
/*    */   
/*    */   public void accept(T t) {
/* 63 */     this.delegate.run();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Delegates$RunnableToConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */