/*    */ package lumine.utils.metadata;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import io.lumine.utils.metadata.TransientValue;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ public final class ExpiringValue<T>
/*    */   implements TransientValue<T>
/*    */ {
/*    */   private final T value;
/*    */   private final long expireAt;
/*    */   
/*    */   public static <T> io.lumine.utils.metadata.ExpiringValue<T> of(T value, long duration, TimeUnit unit) {
/* 14 */     Preconditions.checkArgument((duration >= 0L), "duration must be >= 0");
/* 15 */     Preconditions.checkNotNull(value, "value");
/* 16 */     Preconditions.checkNotNull(unit, "unit");
/*    */     
/* 18 */     long millis = unit.toMillis(duration);
/* 19 */     long expireAt = System.currentTimeMillis() + millis;
/*    */     
/* 21 */     return new io.lumine.utils.metadata.ExpiringValue<>(value, expireAt);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private ExpiringValue(T value, long expireAt) {
/* 28 */     this.value = value;
/* 29 */     this.expireAt = expireAt;
/*    */   }
/*    */ 
/*    */   
/*    */   public T getOrNull() {
/* 34 */     return shouldExpire() ? null : this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExpire() {
/* 39 */     return (System.currentTimeMillis() > this.expireAt);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\ExpiringValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */