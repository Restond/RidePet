/*     */ package lumine.utils;
/*     */ 
/*     */ import io.lumine.utils.Delegates;
/*     */ import io.lumine.utils.interfaces.Delegate;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ConsumerToBiConsumerFirst<T, U>
/*     */   implements BiConsumer<T, U>, Delegate<Consumer<T>>
/*     */ {
/*     */   private final Consumer<T> delegate;
/*     */   
/*     */   private ConsumerToBiConsumerFirst(Consumer<T> delegate) {
/* 103 */     this.delegate = delegate;
/*     */   } public Consumer<T> getDelegate() {
/* 105 */     return this.delegate;
/*     */   }
/*     */   
/*     */   public void accept(T t, U u) {
/* 109 */     this.delegate.accept(t);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Delegates$ConsumerToBiConsumerFirst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */