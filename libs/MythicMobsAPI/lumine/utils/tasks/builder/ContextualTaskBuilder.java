package lumine.utils.tasks.builder;

import io.lumine.utils.tasks.Task;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

public interface ContextualTaskBuilder {
  @Nonnull
  Task consume(@Nonnull Consumer<Task> paramConsumer);
  
  @Nonnull
  Task run(@Nonnull Runnable paramRunnable);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\ContextualTaskBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */