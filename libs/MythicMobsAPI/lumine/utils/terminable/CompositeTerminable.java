package lumine.utils.terminable;

import io.lumine.utils.terminable.Terminable;
import java.util.function.Consumer;

public interface CompositeTerminable {
  void bind(Consumer<Terminable> paramConsumer);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\terminable\CompositeTerminable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */