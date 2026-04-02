package lumine.utils.events;

import io.lumine.utils.metadata.MetadataKey;
import java.util.function.Predicate;

public interface DefaultFilters {
  <T extends org.bukkit.event.Cancellable> Predicate<T> ignoreCancelled();
  
  <T extends org.bukkit.event.player.PlayerMoveEvent> Predicate<T> ignoreSameBlock();
  
  <T extends org.bukkit.event.player.PlayerMoveEvent> Predicate<T> ignoreSameBlockAndY();
  
  <T extends org.bukkit.event.player.PlayerMoveEvent> Predicate<T> ignoreSameChunk();
  
  <T extends org.bukkit.event.entity.EntityEvent> Predicate<T> entityHasMetadata(MetadataKey<?> paramMetadataKey);
  
  <T extends org.bukkit.event.player.PlayerEvent> Predicate<T> playerHasMetadata(MetadataKey<?> paramMetadataKey);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\Events$DefaultFilters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */