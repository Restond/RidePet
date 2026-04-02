/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import io.lumine.utils.timingslib.MinecraftTiming;
/*    */ import org.bukkit.plugin.Plugin;
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
/* 17 */     return (MCTiming)new MinecraftTiming(plugin, command, parent);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\TimingType$2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */