/*    */ package lumine.utils.timings;
/*    */ 
/*    */ import io.lumine.utils.plugin.LoaderUtils;
/*    */ import io.lumine.utils.timingslib.TimingManager;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Timings
/*    */ {
/* 13 */   private static TimingManager timingManager = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized TimingManager get() {
/* 20 */     if (timingManager == null) {
/* 21 */       JavaPlugin plugin = LoaderUtils.getPlugin();
/* 22 */       timingManager = TimingManager.of((Plugin)plugin);
/*    */     } 
/*    */     
/* 25 */     return timingManager;
/*    */   }
/*    */   
/*    */   private Timings() {
/* 29 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timings\Timings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */