/*    */ package lumine.utils;
/*    */ 
/*    */ import io.lumine.utils.Delegates;
/*    */ import io.lumine.utils.interfaces.Delegate;
/*    */ import java.util.concurrent.Callable;
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
/*    */ final class CallableToSupplier<T>
/*    */   implements Supplier<T>, Delegate<Callable<T>>
/*    */ {
/*    */   private final Callable<T> delegate;
/*    */   
/*    */   private CallableToSupplier(Callable<T> delegate) {
/* 70 */     this.delegate = delegate;
/*    */   } public Callable<T> getDelegate() {
/* 72 */     return this.delegate;
/*    */   }
/*    */   
/*    */   public T get() {
/*    */     try {
/* 77 */       return this.delegate.call();
/* 78 */     } catch (RuntimeException|Error e) {
/* 79 */       throw e;
/* 80 */     } catch (Exception e) {
/* 81 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Delegates$CallableToSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */