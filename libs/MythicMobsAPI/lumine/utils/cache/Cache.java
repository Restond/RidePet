/*    */ package lumine.utils.cache;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Cache<T>
/*    */   implements Supplier<T>
/*    */ {
/*    */   private final Supplier<T> supplier;
/*    */   
/*    */   public static <T> io.lumine.utils.cache.Cache<T> suppliedBy(Supplier<T> supplier) {
/* 18 */     return new io.lumine.utils.cache.Cache<>(Objects.<Supplier<T>>requireNonNull(supplier, "supplier"));
/*    */   }
/*    */ 
/*    */   
/* 22 */   private volatile T value = null;
/*    */   
/*    */   private Cache(Supplier<T> supplier) {
/* 25 */     this.supplier = supplier;
/*    */   }
/*    */ 
/*    */   
/*    */   public final T get() {
/* 30 */     T val = this.value;
/*    */ 
/*    */     
/* 33 */     if (val == null) {
/* 34 */       synchronized (this) {
/* 35 */         val = this.value;
/* 36 */         if (val == null) {
/* 37 */           val = this.supplier.get();
/* 38 */           this.value = val;
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 43 */     return val;
/*    */   }
/*    */   
/*    */   public final Optional<T> getIfPresent() {
/* 47 */     return Optional.ofNullable(this.value);
/*    */   }
/*    */   
/*    */   public final void invalidate() {
/* 51 */     this.value = null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\cache\Cache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */