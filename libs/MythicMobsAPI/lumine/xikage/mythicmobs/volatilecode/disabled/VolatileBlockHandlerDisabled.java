package lumine.xikage.mythicmobs.volatilecode.disabled;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileBlockHandler;
import org.bukkit.block.Block;

public class VolatileBlockHandlerDisabled implements VolatileBlockHandler {
  public void applyPhysics(Block target) {}
  
  public void togglePowerable(AbstractLocation location) {}
  
  public void togglePowerable(AbstractLocation location, long duration) {}
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\disabled\VolatileBlockHandlerDisabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */