/*     */ package lumine.utils;
/*     */ 
/*     */ import io.lumine.utils.Time;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.LongSupplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cooldown
/*     */   implements LongSupplier
/*     */ {
/*     */   private long lastCalled;
/*     */   private final long timeout;
/*     */   
/*     */   public static io.lumine.utils.Cooldown ofTicks(long ticks) {
/*  18 */     return new io.lumine.utils.Cooldown(ticks * 50L, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static io.lumine.utils.Cooldown of(long amount, TimeUnit unit) {
/*  29 */     return new io.lumine.utils.Cooldown(amount, unit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Cooldown(long amount, TimeUnit unit) {
/*  39 */     this.timeout = unit.toMillis(amount);
/*  40 */     this.lastCalled = 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean test() {
/*  51 */     if (!testSilently()) {
/*  52 */       return false;
/*     */     }
/*     */     
/*  55 */     reset();
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean testSilently() {
/*  65 */     return (elapsed() > this.timeout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long elapsed() {
/*  74 */     return Time.now() - this.lastCalled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  81 */     this.lastCalled = Time.now();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long remainingMillis() {
/*  92 */     long diff = elapsed();
/*  93 */     return (diff > this.timeout) ? 0L : (this.timeout - diff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long remainingTime(TimeUnit unit) {
/* 105 */     return Math.max(0L, unit.convert(remainingMillis(), TimeUnit.MILLISECONDS));
/*     */   }
/*     */ 
/*     */   
/*     */   public long getAsLong() {
/* 110 */     return remainingMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getTimeout() {
/* 119 */     return this.timeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.utils.Cooldown copy() {
/* 128 */     return new io.lumine.utils.Cooldown(this.timeout, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Cooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */