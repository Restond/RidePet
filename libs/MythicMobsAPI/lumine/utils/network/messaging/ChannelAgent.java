package lumine.utils.network.messaging;

import io.lumine.utils.network.messaging.Channel;
import io.lumine.utils.network.messaging.ChannelListener;
import io.lumine.utils.terminable.Terminable;
import java.util.Set;
import javax.annotation.Nonnull;

public interface ChannelAgent<T> extends Terminable {
  @Nonnull
  Channel<T> getChannel();
  
  @Nonnull
  Set<ChannelListener<T>> getListeners();
  
  boolean hasListeners();
  
  boolean addListener(@Nonnull ChannelListener<T> paramChannelListener);
  
  boolean removeListener(@Nonnull ChannelListener<T> paramChannelListener);
  
  boolean terminate();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\ChannelAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */