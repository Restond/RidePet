package lumine.xikage.mythicmobs.mobs;

import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import java.util.Optional;
import java.util.UUID;

public interface CasterRegistry {
  Optional<SkillCaster> getSkillCaster(UUID paramUUID);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\CasterRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */