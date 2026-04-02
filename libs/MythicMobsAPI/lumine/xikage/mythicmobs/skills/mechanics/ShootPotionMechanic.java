/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.PotionMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.ThrownPotion;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.inventory.meta.PotionMeta;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "shootpotion", description = "Shoots a splash potion")
/*    */ public class ShootPotionMechanic extends PotionMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   public ShootPotionMechanic(String line, MythicLineConfig mlc) {
/* 29 */     super(line, mlc);
/* 30 */     this.ASYNC_SAFE = false;
/*    */     
/* 32 */     this.velocity = mlc.getFloat(new String[] { "velocity", "v" }, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 37 */     return castAtLocation(data, target.getLocation());
/*    */   }
/*    */ 
/*    */   
/*    */   protected float velocity;
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*    */     PotionEffect effect;
/* 45 */     String potionEffect = this.effect.get((PlaceholderMeta)data);
/* 46 */     int duration = this.duration.get((PlaceholderMeta)data);
/* 47 */     int lvl = this.lvl.get((PlaceholderMeta)data);
/*    */     
/*    */     try {
/* 50 */       if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 51 */         effect = new PotionEffect(PotionEffectType.getByName(potionEffect), duration, lvl, this.hasParticles, this.hasIcon);
/*    */       } else {
/* 53 */         effect = new PotionEffect(PotionEffectType.getByName(potionEffect), duration, lvl);
/*    */       } 
/* 55 */     } catch (Exception ex) {
/* 56 */       MythicLogger.errorMechanicConfig((SkillMechanic)this, this.config, "The 'type' attribute must be a valid potion type.");
/* 57 */       return false;
/*    */     } 
/*    */     
/* 60 */     ItemStack potion = new ItemStack(Material.SPLASH_POTION);
/* 61 */     PotionMeta pm = (PotionMeta)potion.getItemMeta();
/* 62 */     pm.addCustomEffect(effect, true);
/*    */     
/* 64 */     potion.setItemMeta((ItemMeta)pm);
/*    */     
/* 66 */     LivingEntity bukkitShooter = (LivingEntity)data.getCaster().getEntity().getBukkitEntity();
/* 67 */     Projectile projectile = bukkitShooter.launchProjectile(ThrownPotion.class);
/* 68 */     ((ThrownPotion)projectile).setItem(potion);
/*    */     
/* 70 */     projectile.setVelocity(bukkitShooter.getLocation().getDirection().multiply(this.velocity));
/* 71 */     projectile.setBounce(false);
/* 72 */     projectile.setShooter((ProjectileSource)bukkitShooter);
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShootPotionMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */