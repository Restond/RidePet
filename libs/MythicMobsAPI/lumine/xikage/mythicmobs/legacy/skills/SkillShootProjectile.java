/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import java.lang.reflect.Array;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.entity.Arrow;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.Egg;
/*    */ import org.bukkit.entity.EnderPearl;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.Snowball;
/*    */ import org.bukkit.metadata.FixedMetadataValue;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class SkillShootProjectile
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/*    */     Projectile projectile;
/* 23 */     if (target == null)
/* 24 */       return;  String[] base = skill.split(" ");
/* 25 */     String[] data = base[1].split(":");
/*    */     
/* 27 */     String projectileType = (Array.getLength(data) > 0) ? data[0] : "arrow";
/* 28 */     int damage = (Array.getLength(data) > 1) ? Integer.parseInt(data[1]) : 1;
/* 29 */     float velocity = (Array.getLength(data) > 2) ? Float.parseFloat(data[2]) : 1.0F;
/* 30 */     int maxdistance = (Array.getLength(data) > 3) ? Integer.parseInt(data[3]) : 64;
/*    */     
/* 32 */     if (l.getLocation().distance(target.getLocation()) > maxdistance) {
/*    */       return;
/*    */     }
/* 35 */     if (projectileType.equalsIgnoreCase("arrow")) {
/* 36 */       projectileClass = (Class)Arrow.class;
/* 37 */       l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
/* 38 */     } else if (projectileType.equalsIgnoreCase("snowball")) {
/* 39 */       projectileClass = (Class)Snowball.class;
/* 40 */       l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
/* 41 */     } else if (projectileType.equalsIgnoreCase("egg")) {
/* 42 */       projectileClass = (Class)Egg.class;
/* 43 */       l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
/* 44 */     } else if (projectileType.equalsIgnoreCase("enderpearl")) {
/* 45 */       projectileClass = (Class)EnderPearl.class;
/*    */     } else {
/* 47 */       projectileClass = (Class)Arrow.class;
/*    */     } 
/*    */     
/* 50 */     if (l instanceof Creature && ((Creature)l).getTarget() == target) {
/* 51 */       projectile = l.launchProjectile(projectileClass);
/* 52 */       projectile.setVelocity(l.getLocation().getDirection().multiply(velocity));
/*    */     } else {
/* 54 */       projectile = l.launchProjectile(projectileClass);
/* 55 */       Vector facing = target.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
/* 56 */       projectile.setVelocity(facing);
/*    */     } 
/*    */     
/* 59 */     projectile.setBounce(false);
/* 60 */     projectile.setShooter((ProjectileSource)l);
/* 61 */     projectile.setMetadata("MythicMobsProjectile", (MetadataValue)new FixedMetadataValue((Plugin)MythicMobs.inst(), new ProjectileData(damage)));
/*    */   }
/*    */   
/*    */   private static Class<? extends Projectile> projectileClass;
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillShootProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */