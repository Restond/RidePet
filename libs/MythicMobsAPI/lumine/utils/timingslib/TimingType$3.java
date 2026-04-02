/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.EmptyTiming;
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import io.lumine.utils.timingslib.Minecraft18Timing;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import org.bukkit.plugin.Plugin;
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
/*    */ enum null
/*    */ {
/*    */   MCTiming newTiming(Plugin plugin, String command, MCTiming parent) {
/*    */     try {
/* 24 */       return (MCTiming)new Minecraft18Timing(plugin, command, parent);
/* 25 */     } catch (InvocationTargetException|IllegalAccessException e) {
/* 26 */       return (MCTiming)new EmptyTiming();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\TimingType$3.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */