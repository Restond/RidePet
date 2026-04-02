/*    */ package lumine.xikage.mythicmobs.adapters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractTaskManager;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitTaskManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class TaskManager
/*    */ {
/*    */   protected static AbstractTaskManager taskManager;
/*    */   
/*    */   public static void initializeBukkit(JavaPlugin plugin) {
/* 12 */     taskManager = (AbstractTaskManager)new BukkitTaskManager(plugin);
/*    */   }
/*    */   
/*    */   public static AbstractTaskManager get() {
/* 16 */     return taskManager;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\TaskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */