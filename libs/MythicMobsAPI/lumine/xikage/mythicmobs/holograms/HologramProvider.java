package lumine.xikage.mythicmobs.holograms;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.holograms.IHologram;

public interface HologramProvider {
  IHologram createHologram(String paramString1, AbstractLocation paramAbstractLocation, String paramString2);
  
  void cleanup();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\holograms\HologramProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */