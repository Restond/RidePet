package lumine.utils.network.messaging;

import java.util.Set;
import javax.annotation.Nonnull;

public interface InstanceData {
  @Nonnull
  String getId();
  
  @Nonnull
  Set<String> getGroups();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\InstanceData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */