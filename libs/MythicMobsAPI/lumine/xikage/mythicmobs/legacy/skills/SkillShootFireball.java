/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.Fireball;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class SkillShootFireball
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/*    */     Fireball fireball;
/* 15 */     if (target == null)
/* 16 */       return;  String[] base = skill.split(" ");
/* 17 */     String[] data = base[1].split(":");
/*    */     
/* 19 */     float power = Float.parseFloat(data[0]);
/* 20 */     boolean incendiary = Boolean.parseBoolean(data[1]);
/*    */     
/* 22 */     int fireticks = 0;
/* 23 */     boolean playsound = true;
/*    */     
/* 25 */     if (data.length > 2) {
/* 26 */       fireticks = Integer.parseInt(data[2]);
/*    */     }
/* 28 */     if (data.length > 3) {
/* 29 */       playsound = Boolean.parseBoolean(data[3]);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 34 */     if (l instanceof Creature && ((Creature)l).getTarget() == target) {
/* 35 */       fireball = (Fireball)l.launchProjectile(Fireball.class);
/*    */     } else {
/* 37 */       Vector facing = target.getLocation().toVector().subtract(l.getLocation().toVector()).normalize();
/*    */       
/* 39 */       Location loc = l.getLocation().clone();
/*    */       
/* 41 */       double yaw = Math.toDegrees(Math.atan2(-facing.getX(), facing.getZ()));
/* 42 */       double pitch = Math.toDegrees(-Math.asin(facing.getY()));
/* 43 */       loc.setYaw((float)yaw);
/* 44 */       loc.setPitch((float)pitch);
/*    */       
/* 46 */       loc.add(facing.multiply(2));
/*    */       
/* 48 */       fireball = (Fireball)l.getLocation().getWorld().spawn(loc, Fireball.class);
/*    */     } 
/*    */     
/* 51 */     if (playsound == true) {
/* 52 */       l.getWorld().playEffect(l.getLocation(), Effect.GHAST_SHOOT, 0);
/*    */     }
/* 54 */     fireball.setBounce(false);
/* 55 */     fireball.setIsIncendiary(incendiary);
/* 56 */     fireball.setFireTicks(fireticks);
/* 57 */     fireball.setYield(power);
/* 58 */     fireball.setShooter((ProjectileSource)l);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillShootFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */