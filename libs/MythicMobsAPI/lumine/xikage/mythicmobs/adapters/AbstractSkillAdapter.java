package lumine.xikage.mythicmobs.adapters;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata;
import io.lumine.xikage.mythicmobs.skills.mechanics.ShootMechanic;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public interface AbstractSkillAdapter {
  void strikeLightning(AbstractLocation paramAbstractLocation);
  
  void doDamage(DamageMetadata paramDamageMetadata, AbstractEntity paramAbstractEntity);
  
  void throwSkill(AbstractLocation paramAbstractLocation, AbstractEntity paramAbstractEntity, float paramFloat1, float paramFloat2);
  
  void itemSprayEffect(AbstractLocation paramAbstractLocation, ItemStack paramItemStack, int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, boolean paramBoolean);
  
  void strikeLightningEffect(AbstractLocation paramAbstractLocation);
  
  void playSmokeEffect(AbstractLocation paramAbstractLocation, int paramInt);
  
  void shootFireball(SkillCaster paramSkillCaster, AbstractLocation paramAbstractLocation, float paramFloat1, float paramFloat2, boolean paramBoolean1, int paramInt, boolean paramBoolean2, boolean paramBoolean3);
  
  void pushButton(SkillCaster paramSkillCaster, AbstractLocation paramAbstractLocation);
  
  void toggleLever(SkillCaster paramSkillCaster, AbstractLocation paramAbstractLocation, int paramInt);
  
  AbstractEntity shootProjectile(SkillCaster paramSkillCaster, AbstractLocation paramAbstractLocation1, AbstractLocation paramAbstractLocation2, Class<? extends Projectile> paramClass, float paramFloat, ShootMechanic.ProjectileData paramProjectileData, boolean paramBoolean);
  
  AbstractEntity shootArcProjectile(SkillCaster paramSkillCaster, AbstractLocation paramAbstractLocation1, AbstractLocation paramAbstractLocation2, Class<? extends Projectile> paramClass, float paramFloat, ShootMechanic.ProjectileData paramProjectileData, boolean paramBoolean);
  
  void executeVolley(SkillCaster paramSkillCaster, AbstractLocation paramAbstractLocation, int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3);
  
  AbstractEntity rainProjectile(SkillCaster paramSkillCaster, AbstractLocation paramAbstractLocation, Class<? extends Projectile> paramClass, float paramFloat, ShootMechanic.ProjectileData paramProjectileData);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractSkillAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */