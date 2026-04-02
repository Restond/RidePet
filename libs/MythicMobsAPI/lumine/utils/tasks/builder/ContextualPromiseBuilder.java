package lumine.utils.tasks.builder;

import io.lumine.utils.promise.Promise;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

public interface ContextualPromiseBuilder {
  @Nonnull
  <T> Promise<T> supply(@Nonnull Supplier<T> paramSupplier);
  
  @Nonnull
  <T> Promise<T> call(@Nonnull Callable<T> paramCallable);
  
  @Nonnull
  Promise<Void> run(@Nonnull Runnable paramRunnable);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\builder\ContextualPromiseBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */