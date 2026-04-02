package lumine.utils.tasks.builder;

import io.lumine.utils.tasks.builder.ContextualTaskBuilder;
import io.lumine.utils.tasks.builder.TaskBuilder;
import javax.annotation.Nonnull;

public interface DelayedTick extends TaskBuilder.Delayed {
  @Nonnull
  ContextualTaskBuilder every(long paramLong);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilder$DelayedTick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */