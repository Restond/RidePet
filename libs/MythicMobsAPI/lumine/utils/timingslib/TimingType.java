/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.EmptyTiming;
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ enum TimingType {
/*  8 */   SPIGOT(true),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   MINECRAFT,
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   MINECRAFT_18,
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   EMPTY;
/*    */   
/*    */   private final boolean useCache;
/*    */   
/*    */   public boolean useCache() {
/* 35 */     return this.useCache;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   TimingType(boolean useCache) {
/* 42 */     this.useCache = useCache;
/*    */   }
/*    */   
/*    */   MCTiming newTiming(Plugin plugin, String command, MCTiming parent) {
/* 46 */     return (MCTiming)new EmptyTiming();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\TimingType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */