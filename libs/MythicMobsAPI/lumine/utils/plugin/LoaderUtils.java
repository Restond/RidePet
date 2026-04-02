/*    */ package lumine.utils.plugin;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LoaderUtils
/*    */ {
/* 13 */   private static JavaPlugin plugin = null;
/* 14 */   private static Thread mainThread = null;
/*    */   
/*    */   public static synchronized JavaPlugin getPlugin() {
/* 17 */     if (plugin == null) {
/* 18 */       plugin = JavaPlugin.getProvidingPlugin(io.lumine.utils.plugin.LoaderUtils.class);
/*    */       
/* 20 */       String thisClass = io.lumine.utils.plugin.LoaderUtils.class.getName();
/* 21 */       thisClass = thisClass.substring(0, thisClass.length() - ".utils.LoaderUtils".length());
/* 22 */       Bukkit.getLogger().info("[LumineUtils] (" + thisClass + ") is bound to plugin " + plugin.getName() + " - " + plugin.getClass().getName());
/*    */     } 
/* 24 */     return plugin;
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   public static synchronized Thread getMainThread() {
/* 29 */     if (mainThread == null && 
/* 30 */       Bukkit.getServer().isPrimaryThread()) {
/* 31 */       mainThread = Thread.currentThread();
/*    */     }
/*    */     
/* 34 */     return mainThread;
/*    */   }
/*    */   
/*    */   private LoaderUtils() {
/* 38 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\plugin\LoaderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */