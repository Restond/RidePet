package lumine.xikage.mythicmobs.adapters;

import io.lumine.xikage.mythicmobs.adapters.AbstractBossBar;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
import java.util.List;
import java.util.UUID;

public interface ServerInterface {
  List<AbstractWorld> getWorlds();
  
  void dispatchCommand(String paramString);
  
  List<AbstractPlayer> getOnlinePlayers();
  
  AbstractLocation newLocation(AbstractWorld paramAbstractWorld, double paramDouble1, double paramDouble2, double paramDouble3);
  
  AbstractWorld getWorld(String paramString);
  
  boolean isValidBiome(Object paramObject);
  
  AbstractBossBar createBossBar(String paramString, AbstractBossBar.BarColor paramBarColor, AbstractBossBar.BarStyle paramBarStyle);
  
  AbstractPlayer getPlayer(UUID paramUUID);
  
  AbstractPlayer getPlayer(String paramString);
  
  AbstractEntity getEntity(UUID paramUUID);
  
  AbstractWorld getWorld(UUID paramUUID);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\ServerInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */