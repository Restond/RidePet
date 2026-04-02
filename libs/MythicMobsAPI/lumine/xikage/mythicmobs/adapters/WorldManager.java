package lumine.xikage.mythicmobs.adapters;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import java.util.UUID;
import org.bukkit.event.Listener;

public interface WorldManager extends Listener {
  boolean isChunkLoaded(AbstractWorld paramAbstractWorld, int paramInt1, int paramInt2);
  
  int getEntitiesInChunk(AbstractWorld paramAbstractWorld, int paramInt1, int paramInt2);
  
  void handleMobDespawnEvent(ActiveMob paramActiveMob);
  
  AbstractEntity getEntity(UUID paramUUID);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\WorldManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */