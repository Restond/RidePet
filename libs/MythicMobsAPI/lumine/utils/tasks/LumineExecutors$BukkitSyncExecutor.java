/*    */ package lumine.utils.tasks;
/*    */ 
/*    */ import io.lumine.utils.plugin.LoaderUtils;
/*    */ import io.lumine.utils.tasks.LumineExecutors;
/*    */ import java.util.concurrent.Executor;
/*    */ import org.bukkit.Bukkit;
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
/*    */ final class BukkitSyncExecutor
/*    */   implements Executor
/*    */ {
/*    */   private BukkitSyncExecutor() {}
/*    */   
/*    */   public void execute(Runnable runnable) {
/* 46 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)LoaderUtils.getPlugin(), LumineExecutors.wrapRunnable(runnable));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\LumineExecutors$BukkitSyncExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */