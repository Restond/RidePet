package lumine.xikage.mythicmobs.holograms;

import io.lumine.utils.terminable.Terminable;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;

public interface IHologram extends Terminable {
  void teleport(AbstractLocation paramAbstractLocation);
  
  void setText(String paramString);
  
  void setText(String[] paramArrayOfString, String paramString);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\holograms\IHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */