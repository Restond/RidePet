package lumine.xikage.mythicmobs.adapters;

import io.lumine.utils.terminable.Terminable;
import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
import java.util.List;

public interface AbstractBossBar extends Terminable {
  String getTitle();
  
  void setTitle(String paramString);
  
  BarColor getColor();
  
  void setColor(String paramString);
  
  BarStyle getStyle();
  
  void setStyle(String paramString);
  
  void removeFlag(String paramString);
  
  void addFlag(String paramString);
  
  boolean hasFlag(String paramString);
  
  void setProgress(double paramDouble);
  
  double getProgress();
  
  void addPlayer(AbstractPlayer paramAbstractPlayer);
  
  void removePlayer(AbstractPlayer paramAbstractPlayer);
  
  void removeAll();
  
  List<AbstractPlayer> getPlayers();
  
  void setVisible(boolean paramBoolean);
  
  boolean isVisible();
  
  void setCreateFog(boolean paramBoolean);
  
  void setDarkenSky(boolean paramBoolean);
  
  void setPlayBossMusic(boolean paramBoolean);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractBossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */