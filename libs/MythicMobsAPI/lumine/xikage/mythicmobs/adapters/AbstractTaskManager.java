package lumine.xikage.mythicmobs.adapters;

@Deprecated
public interface AbstractTaskManager {
  int scheduleAsyncTask(Runnable paramRunnable, int paramInt1, int paramInt2);
  
  int scheduleTask(Runnable paramRunnable, int paramInt1, int paramInt2);
  
  void cancelTask(int paramInt);
  
  void runAsync(Runnable paramRunnable);
  
  void runLater(Runnable paramRunnable, int paramInt);
  
  void runAsyncLater(Runnable paramRunnable, int paramInt);
  
  void run(Runnable paramRunnable);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractTaskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */