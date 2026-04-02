package lumine.utils.events;

import com.comphenix.protocol.PacketType;
import io.lumine.utils.events.Subscription;
import java.util.Set;
import javax.annotation.Nonnull;

public interface ProtocolSubscription extends Subscription {
  @Nonnull
  Set<PacketType> getPackets();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\ProtocolSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */