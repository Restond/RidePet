/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Timing
/*    */ {
/*  9 */   private static final HashMap<String, TimingResults> timings = new HashMap<>();
/*    */   
/*    */   private final String type;
/* 12 */   private final long start = System.nanoTime();
/*    */   private long end;
/*    */   
/*    */   public Timing(String type) {
/* 16 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void end() {
/* 20 */     this.end = System.nanoTime();
/*    */     
/* 22 */     if (!timings.containsKey(this.type)) {
/* 23 */       timings.put(this.type, new TimingResults());
/*    */     }
/* 25 */     ((TimingResults)timings.get(this.type)).addTiming(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public long getNanoTime() {
/* 30 */     return this.end - this.start;
/*    */   }
/*    */   
/*    */   public static HashMap<String, TimingResults> getTimings() {
/* 34 */     return timings;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\Timing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */