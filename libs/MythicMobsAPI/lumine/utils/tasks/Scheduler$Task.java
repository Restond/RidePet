package lumine.utils.tasks;

import io.lumine.utils.terminable.Terminable;

public interface Task extends Terminable {
  int getTimesRan();
  
  boolean stop();
  
  int getBukkitId();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\Scheduler$Task.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */