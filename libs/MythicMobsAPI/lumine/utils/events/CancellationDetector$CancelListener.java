package lumine.utils.events;

import org.bukkit.plugin.Plugin;

public interface CancelListener<TEvent extends org.bukkit.event.Event> {
  void onCancelled(Plugin paramPlugin, TEvent paramTEvent);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\CancellationDetector$CancelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */