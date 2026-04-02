/*    */ package lumine.utils.cache;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Lazy<T>
/*    */   implements Supplier<T>
/*    */ {
/*    */   private volatile Supplier<T> supplier;
/*    */   
/*    */   public static <T> io.lumine.utils.cache.Lazy<T> suppliedBy(Supplier<T> supplier) {
/* 17 */     return new io.lumine.utils.cache.Lazy<>(Objects.<Supplier<T>>requireNonNull(supplier, "supplier"));
/*    */   }
/*    */   
/*    */   public static <T> io.lumine.utils.cache.Lazy<T> of(T value) {
/* 21 */     return new io.lumine.utils.cache.Lazy<>(Objects.requireNonNull(value, "value"));
/*    */   }
/*    */ 
/*    */   
/*    */   private volatile boolean initialized = false;
/*    */   private T value;
/*    */   
/*    */   private Lazy(Supplier<T> supplier) {
/* 29 */     this.supplier = supplier;
/*    */   }
/*    */   
/*    */   private Lazy(T value) {
/* 33 */     this.value = value;
/* 34 */     this.initialized = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public T get() {
/* 39 */     if (!this.initialized) {
/* 40 */       synchronized (this) {
/* 41 */         if (!this.initialized) {
/*    */           
/* 43 */           T t = this.supplier.get();
/*    */           
/* 45 */           this.value = t;
/* 46 */           this.initialized = true;
/*    */ 
/*    */           
/* 49 */           this.supplier = null;
/* 50 */           return t;
/*    */         } 
/*    */       } 
/*    */     }
/* 54 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\cache\Lazy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */