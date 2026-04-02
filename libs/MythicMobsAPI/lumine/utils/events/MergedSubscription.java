package lumine.utils.events;

import io.lumine.utils.events.Subscription;
import java.util.Set;
import javax.annotation.Nonnull;
import org.bukkit.event.Event;

public interface MergedSubscription<T> extends Subscription {
  @Nonnull
  Class<? super T> getHandledClass();
  
  @Nonnull
  Set<Class<? extends Event>> getEventClasses();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\MergedSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */