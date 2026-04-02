package lumine.xikage.mythicmobs.adapters;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractGameMode;

public interface AbstractPlayer extends AbstractEntity {
  boolean hasPermission(String paramString);
  
  boolean isInCreativeMode();
  
  boolean isInSpectatorMode();
  
  void sendMessage(String paramString);
  
  float getExperience();
  
  void setExperience(float paramFloat);
  
  void hidePlayer(io.lumine.xikage.mythicmobs.adapters.AbstractPlayer paramAbstractPlayer);
  
  boolean canSee(io.lumine.xikage.mythicmobs.adapters.AbstractPlayer paramAbstractPlayer);
  
  boolean isOnline();
  
  int getLevel();
  
  void setLevel(int paramInt);
  
  void setHealthScale(double paramDouble);
  
  void setHealthScaled(boolean paramBoolean);
  
  void setPersonalTime(long paramLong, boolean paramBoolean);
  
  void resetPersonalTime();
  
  void setPersonalWeather(String paramString);
  
  void resetPersonalWeather();
  
  void setAllowFlight(boolean paramBoolean);
  
  boolean getAllowFlight();
  
  void showPlayer(io.lumine.xikage.mythicmobs.adapters.AbstractPlayer paramAbstractPlayer);
  
  void setFlying(boolean paramBoolean);
  
  void setFlyingSpeed(float paramFloat);
  
  void setWalkSpeed(float paramFloat);
  
  int getFoodLevel();
  
  void setFoodLevel(int paramInt);
  
  float getFoodSaturation();
  
  void setFoodSaturation(float paramFloat);
  
  void setGameMode(AbstractGameMode paramAbstractGameMode);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */