/*    */ package lumine.utils.network.messaging.util;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.OptionalLong;
/*    */ import java.util.concurrent.TimeUnit;
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
/*    */ public final class ChannelReceiver<T>
/*    */ {
/*    */   private T value;
/* 43 */   private long timestamp = 0L;
/*    */   private final long expiryMillis;
/*    */   
/*    */   public ChannelReceiver(long expiryDuration, @Nonnull TimeUnit unit) {
/* 47 */     this.expiryMillis = unit.toMillis(expiryDuration);
/*    */   }
/*    */   
/*    */   public void set(T value) {
/* 51 */     this.value = value;
/* 52 */     this.timestamp = System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Optional<T> getLastKnownValue() {
/* 61 */     return Optional.ofNullable(this.value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Optional<T> getValue() {
/* 72 */     long now = System.currentTimeMillis();
/* 73 */     long diff = now - this.timestamp;
/* 74 */     if (diff > this.expiryMillis) {
/* 75 */       return Optional.empty();
/*    */     }
/* 77 */     return getLastKnownValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OptionalLong getLastReceivedTimestamp() {
/* 86 */     return (this.timestamp == 0L) ? OptionalLong.empty() : OptionalLong.of(this.timestamp);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 91 */     return "ChannelReceiver{value=" + this.value + ", timestamp=" + this.timestamp + ", expiryMillis=" + this.expiryMillis + '}';
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messagin\\util\ChannelReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */