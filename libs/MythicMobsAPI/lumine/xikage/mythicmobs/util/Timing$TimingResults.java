/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.Timing;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class TimingResults
/*    */ {
/* 38 */   private final List<Timing> timings = new ArrayList<>();
/*    */   
/*    */   public void addTiming(Timing timing) {
/* 41 */     this.timings.add(timing);
/*    */   }
/*    */   
/*    */   public long getAverage() {
/* 45 */     long average = getTotal();
/* 46 */     average /= this.timings.size();
/* 47 */     return average;
/*    */   }
/*    */   
/*    */   public long getTotal() {
/* 51 */     long total = 0L;
/* 52 */     for (Timing timing : this.timings) {
/* 53 */       total += timing.getNanoTime();
/*    */     }
/* 55 */     return total;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\Timing$TimingResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */