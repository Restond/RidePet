/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.spigotmc.CustomTimingsHandler;
/*    */ 
/*    */ class SpigotTiming extends MCTiming {
/*    */   private final CustomTimingsHandler timing;
/*    */   
/*    */   SpigotTiming(String name) {
/* 11 */     this.timing = new CustomTimingsHandler(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCTiming startTiming() {
/* 16 */     if (Bukkit.isPrimaryThread()) {
/* 17 */       this.timing.startTiming();
/*    */     }
/* 19 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopTiming() {
/* 24 */     if (Bukkit.isPrimaryThread())
/* 25 */       this.timing.stopTiming(); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\SpigotTiming.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */