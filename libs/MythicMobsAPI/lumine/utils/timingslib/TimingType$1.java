/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import io.lumine.utils.timingslib.SpigotTiming;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ enum null
/*    */ {
/*    */   MCTiming newTiming(Plugin plugin, String command, MCTiming parent) {
/* 11 */     return (MCTiming)new SpigotTiming(command);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\TimingType$1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */