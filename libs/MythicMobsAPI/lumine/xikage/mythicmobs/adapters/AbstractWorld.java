package lumine.xikage.mythicmobs.adapters;

import io.lumine.xikage.mythicmobs.adapters.AbstractBiome;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
import java.util.List;
import java.util.UUID;

public interface AbstractWorld {
  List<AbstractEntity> getLivingEntities();
  
  boolean equals(Object paramObject);
  
  String getName();
  
  boolean isLoaded();
  
  void createExplosion(AbstractLocation paramAbstractLocation, float paramFloat);
  
  void createExplosion(AbstractLocation paramAbstractLocation, float paramFloat, boolean paramBoolean1, boolean paramBoolean2);
  
  List<AbstractPlayer> getPlayers();
  
  List<AbstractPlayer> getPlayersNearLocation(AbstractLocation paramAbstractLocation, int paramInt);
  
  void setStorm(boolean paramBoolean);
  
  void setThundering(boolean paramBoolean);
  
  void setWeatherDuration(int paramInt);
  
  int getBlockLightLevel(AbstractLocation paramAbstractLocation);
  
  boolean playEffect(AbstractLocation paramAbstractLocation, int paramInt);
  
  boolean playEffect(AbstractLocation paramAbstractLocation, int paramInt1, int paramInt2);
  
  AbstractLocation getSpawnLocation();
  
  boolean isLocationLoaded(AbstractLocation paramAbstractLocation);
  
  AbstractBiome getLocationBiome(AbstractLocation paramAbstractLocation);
  
  long getFullTime();
  
  boolean isChunkLoaded(int paramInt1, int paramInt2);
  
  UUID getUniqueId();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */