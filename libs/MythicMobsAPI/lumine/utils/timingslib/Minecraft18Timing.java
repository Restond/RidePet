/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Minecraft18Timing
/*    */   extends MCTiming
/*    */ {
/*    */   private final Object timing;
/*    */   private static Method startTiming;
/*    */   private static Method stopTiming;
/*    */   private static Method of;
/*    */   
/*    */   static {
/*    */     try {
/* 23 */       Class<?> timing = Class.forName("co.aikar.timings.Timing");
/* 24 */       Class<?> timings = Class.forName("co.aikar.timings.Timings");
/* 25 */       startTiming = timing.getDeclaredMethod("startTimingIfSync", new Class[0]);
/* 26 */       stopTiming = timing.getDeclaredMethod("stopTimingIfSync", new Class[0]);
/* 27 */       of = timings.getDeclaredMethod("of", new Class[] { Plugin.class, String.class, timing });
/* 28 */     } catch (ClassNotFoundException|NoSuchMethodException e) {
/* 29 */       e.printStackTrace();
/* 30 */       Bukkit.getLogger().severe("Timings18 failed to initialize correctly. Stuff's going to be broken.");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   Minecraft18Timing(Plugin plugin, String name, MCTiming parent) throws InvocationTargetException, IllegalAccessException {
/* 36 */     this.timing = of.invoke(null, new Object[] { plugin, name, (parent instanceof io.lumine.utils.timingslib.Minecraft18Timing) ? ((io.lumine.utils.timingslib.Minecraft18Timing)parent).timing : null });
/*    */   }
/*    */ 
/*    */   
/*    */   public MCTiming startTiming() {
/*    */     try {
/* 42 */       if (startTiming != null) {
/* 43 */         startTiming.invoke(this.timing, new Object[0]);
/*    */       }
/* 45 */     } catch (IllegalAccessException|InvocationTargetException illegalAccessException) {}
/* 46 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopTiming() {
/*    */     try {
/* 52 */       if (stopTiming != null) {
/* 53 */         stopTiming.invoke(this.timing, new Object[0]);
/*    */       }
/* 55 */     } catch (IllegalAccessException|InvocationTargetException illegalAccessException) {}
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\Minecraft18Timing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */