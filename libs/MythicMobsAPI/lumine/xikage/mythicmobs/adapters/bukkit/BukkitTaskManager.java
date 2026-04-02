/*    */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractTaskManager;
/*    */ import java.security.InvalidParameterException;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class BukkitTaskManager
/*    */   implements AbstractTaskManager
/*    */ {
/* 12 */   private JavaPlugin plugin = null;
/*    */   
/*    */   public BukkitTaskManager(JavaPlugin plugin) throws InvalidParameterException {
/* 15 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public int scheduleTask(Runnable task, int delay, int interval) {
/* 20 */     return Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, () -> paramRunnable.run(), delay, interval);
/*    */   }
/*    */ 
/*    */   
/*    */   public void cancelTask(int taskId) {
/* 25 */     Bukkit.getScheduler().cancelTask(taskId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int scheduleAsyncTask(Runnable task, int delay, int interval) {
/* 31 */     return Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin)this.plugin, () -> paramRunnable.run(), delay, interval);
/*    */   }
/*    */ 
/*    */   
/*    */   public void run(Runnable task) {
/* 36 */     Bukkit.getScheduler().runTask((Plugin)this.plugin, task);
/*    */   }
/*    */ 
/*    */   
/*    */   public void runAsync(Runnable task) {
/* 41 */     Bukkit.getScheduler().runTaskAsynchronously((Plugin)this.plugin, task);
/*    */   }
/*    */ 
/*    */   
/*    */   public void runAsyncLater(Runnable task, int delay) {
/* 46 */     Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)this.plugin, task, delay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void runLater(Runnable task, int delay) {
/* 51 */     Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, task, delay);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitTaskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */