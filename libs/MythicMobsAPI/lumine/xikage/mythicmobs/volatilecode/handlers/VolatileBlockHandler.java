package lumine.xikage.mythicmobs.volatilecode.handlers;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import org.bukkit.block.Block;

public interface VolatileBlockHandler {
  void applyPhysics(Block paramBlock);
  
  void togglePowerable(AbstractLocation paramAbstractLocation);
  
  void togglePowerable(AbstractLocation paramAbstractLocation, long paramLong);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\handlers\VolatileBlockHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */