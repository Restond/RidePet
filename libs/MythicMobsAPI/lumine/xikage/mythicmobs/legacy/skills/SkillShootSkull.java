/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.WitherSkull;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ public class SkillShootSkull
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/*    */     WitherSkull fireball;
/* 15 */     if (target == null)
/* 16 */       return;  String[] base = skill.split(" ");
/* 17 */     String[] data = base[1].split(":");
/*    */     
/* 19 */     float power = Float.parseFloat(data[0]);
/*    */ 
/*    */ 
/*    */     
/* 23 */     if (l instanceof Creature && ((Creature)l).getTarget() == target) {
/* 24 */       fireball = (WitherSkull)l.launchProjectile(WitherSkull.class);
/*    */     } else {
/* 26 */       Vector facing = target.getLocation().toVector().subtract(l.getLocation().toVector()).normalize();
/*    */       
/* 28 */       Location loc = l.getLocation().clone();
/*    */       
/* 30 */       double yaw = Math.toDegrees(Math.atan2(-facing.getX(), facing.getZ()));
/* 31 */       double pitch = Math.toDegrees(-Math.asin(facing.getY()));
/* 32 */       loc.setYaw((float)yaw);
/* 33 */       loc.setPitch((float)pitch);
/*    */       
/* 35 */       loc.add(facing.multiply(2));
/*    */       
/* 37 */       fireball = (WitherSkull)l.getLocation().getWorld().spawn(loc, WitherSkull.class);
/*    */     } 
/*    */     
/* 40 */     fireball.setBounce(false);
/* 41 */     fireball.setYield(power);
/* 42 */     fireball.setShooter((ProjectileSource)l);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillShootSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */