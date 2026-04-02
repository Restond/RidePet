/*    */ package lumine.utils.metadata;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import io.lumine.utils.metadata.TransientValue;
/*    */ import java.lang.ref.WeakReference;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class WeakValue<T>
/*    */   implements TransientValue<T>
/*    */ {
/*    */   private final WeakReference<T> value;
/*    */   
/*    */   public static <T> io.lumine.utils.metadata.WeakValue<T> of(T value) {
/* 15 */     Preconditions.checkNotNull(value, "value");
/* 16 */     return new io.lumine.utils.metadata.WeakValue<>(value);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private WeakValue(T value) {
/* 22 */     this.value = new WeakReference<>(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public T getOrNull() {
/* 27 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExpire() {
/* 32 */     return (this.value.get() == null);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\WeakValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */