package lumine.xikage.mythicmobs.volatilecode.handlers;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import java.util.List;
import org.bukkit.entity.LivingEntity;

public interface VolatileAIHandler {
  void setTarget(LivingEntity paramLivingEntity1, LivingEntity paramLivingEntity2);
  
  void addPathfinderGoals(LivingEntity paramLivingEntity, List<String> paramList);
  
  void addTargetGoals(LivingEntity paramLivingEntity, List<String> paramList);
  
  default void navigateToLocation(AbstractEntity entity, AbstractLocation destination, double maxDistance) {}
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\handlers\VolatileAIHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */