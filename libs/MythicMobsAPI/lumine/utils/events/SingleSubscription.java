package lumine.utils.events;

import io.lumine.utils.events.Subscription;
import javax.annotation.Nonnull;

public interface SingleSubscription<T extends org.bukkit.event.Event> extends Subscription {
  @Nonnull
  Class<T> getEventClass();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\SingleSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */