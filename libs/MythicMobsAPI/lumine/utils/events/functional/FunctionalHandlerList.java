package lumine.utils.events.functional;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

public interface FunctionalHandlerList<T, R> {
  @Nonnull
  io.lumine.utils.events.functional.FunctionalHandlerList<T, R> consumer(@Nonnull Consumer<? super T> paramConsumer);
  
  @Nonnull
  io.lumine.utils.events.functional.FunctionalHandlerList<T, R> biConsumer(@Nonnull BiConsumer<R, ? super T> paramBiConsumer);
  
  @Nonnull
  R register();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\FunctionalHandlerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */