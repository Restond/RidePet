/*    */ package lumine.utils.cache;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.Objects;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Expiring<T>
/*    */   implements Supplier<T>
/*    */ {
/*    */   private final Supplier<T> supplier;
/*    */   private final long durationNanos;
/*    */   private volatile T value;
/*    */   private volatile long expirationNanos;
/*    */   
/*    */   public static <T> io.lumine.utils.cache.Expiring<T> suppliedBy(Supplier<T> supplier, long duration, TimeUnit unit) {
/* 20 */     Objects.requireNonNull(supplier, "supplier");
/* 21 */     Preconditions.checkArgument((duration > 0L));
/* 22 */     Objects.requireNonNull(unit, "unit");
/*    */     
/* 24 */     return new io.lumine.utils.cache.Expiring<>(supplier, duration, unit);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Expiring(Supplier<T> supplier, long duration, TimeUnit unit) {
/* 36 */     this.supplier = supplier;
/* 37 */     this.durationNanos = unit.toNanos(duration);
/*    */   }
/*    */ 
/*    */   
/*    */   public T get() {
/* 42 */     long nanos = this.expirationNanos;
/* 43 */     long now = System.nanoTime();
/*    */     
/* 45 */     if (nanos == 0L || now - nanos >= 0L) {
/* 46 */       synchronized (this) {
/* 47 */         if (nanos == this.expirationNanos) {
/*    */           
/* 49 */           T t = this.supplier.get();
/* 50 */           this.value = t;
/*    */ 
/*    */           
/* 53 */           nanos = now + this.durationNanos;
/*    */ 
/*    */           
/* 56 */           this.expirationNanos = (nanos == 0L) ? 1L : nanos;
/* 57 */           return t;
/*    */         } 
/*    */       } 
/*    */     }
/* 61 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\cache\Expiring.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */