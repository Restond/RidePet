package lumine.utils.tasks.builder;

import io.lumine.utils.tasks.builder.ContextualPromiseBuilder;
import io.lumine.utils.tasks.builder.ContextualTaskBuilder;
import io.lumine.utils.tasks.builder.TaskBuilder;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

public interface ThreadContextual {
  @Nonnull
  ContextualPromiseBuilder now();
  
  @Nonnull
  TaskBuilder.DelayedTick after(long paramLong);
  
  @Nonnull
  TaskBuilder.DelayedTime after(long paramLong, @Nonnull TimeUnit paramTimeUnit);
  
  @Nonnull
  ContextualTaskBuilder afterAndEvery(long paramLong);
  
  @Nonnull
  ContextualTaskBuilder afterAndEvery(long paramLong, @Nonnull TimeUnit paramTimeUnit);
  
  @Nonnull
  ContextualTaskBuilder every(long paramLong);
  
  @Nonnull
  ContextualTaskBuilder every(long paramLong, @Nonnull TimeUnit paramTimeUnit);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilder$ThreadContextual.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */