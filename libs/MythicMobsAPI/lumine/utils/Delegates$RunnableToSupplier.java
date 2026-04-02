/*    */ package lumine.utils;
/*    */ 
/*    */ import io.lumine.utils.Delegates;
/*    */ import io.lumine.utils.interfaces.Delegate;
/*    */ import java.util.function.Supplier;
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
/*    */ final class RunnableToSupplier<T>
/*    */   implements Supplier<T>, Delegate<Runnable>
/*    */ {
/*    */   private final Runnable delegate;
/*    */   
/*    */   private RunnableToSupplier(Runnable delegate) {
/* 89 */     this.delegate = delegate;
/*    */   } public Runnable getDelegate() {
/* 91 */     return this.delegate;
/*    */   }
/*    */   
/*    */   public T get() {
/* 95 */     this.delegate.run();
/* 96 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Delegates$RunnableToSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */