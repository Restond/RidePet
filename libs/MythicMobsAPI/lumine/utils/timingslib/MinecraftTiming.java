/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import co.aikar.timings.Timing;
/*    */ import co.aikar.timings.Timings;
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ class MinecraftTiming
/*    */   extends MCTiming {
/*    */   MinecraftTiming(Plugin plugin, String name, MCTiming parent) {
/* 11 */     this.timing = Timings.of(plugin, name, (parent instanceof io.lumine.utils.timingslib.MinecraftTiming) ? ((io.lumine.utils.timingslib.MinecraftTiming)parent).timing : null);
/*    */   }
/*    */   private final Timing timing;
/*    */   
/*    */   public MCTiming startTiming() {
/* 16 */     this.timing.startTimingIfSync();
/* 17 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopTiming() {
/* 22 */     this.timing.stopTimingIfSync();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\MinecraftTiming.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */