package lumine.utils.tasks.builder;

import io.lumine.utils.tasks.builder.ContextualTaskBuilder;
import io.lumine.utils.tasks.builder.TaskBuilder;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

public interface DelayedTime extends TaskBuilder.Delayed {
  @Nonnull
  ContextualTaskBuilder every(long paramLong, TimeUnit paramTimeUnit);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\TaskBuilder$DelayedTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */