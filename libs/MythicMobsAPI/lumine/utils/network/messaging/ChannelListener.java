package lumine.utils.network.messaging;

import io.lumine.utils.network.messaging.ChannelAgent;

@FunctionalInterface
public interface ChannelListener<T> {
  void onMessage(ChannelAgent paramChannelAgent, String paramString, T paramT);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\ChannelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */