/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillPull
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 15 */     String[] base = skill.split(" ");
/* 16 */     String[] data = base[1].split(":");
/*    */     
/* 18 */     int radius = Integer.parseInt(data[0]);
/* 19 */     float velocity = Float.parseFloat(data[1]);
/*    */     
/* 21 */     if (radius > 0) {
/*    */       
/* 23 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/*    */         
/* 25 */         if (p.hasLineOfSight((Entity)l)) {
/* 26 */           double distance = l.getLocation().distance(p.getLocation());
/* 27 */           double modxz = distance * 0.5D * velocity;
/* 28 */           double mody = distance * 0.34D * velocity;
/* 29 */           mody = (l.getLocation().getY() - p.getLocation().getY() != 0.0D) ? (mody * Math.abs(l.getLocation().getY() - p.getLocation().getY()) * 0.5D) : mody;
/*    */           
/* 31 */           Vector direction = p.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
/* 32 */           direction.setX(direction.getX() * -1.0D * modxz);
/* 33 */           direction.setZ(direction.getZ() * -1.0D * modxz);
/* 34 */           direction.setY(direction.getY() * -1.0D * mody);
/* 35 */           p.setVelocity(direction);
/*    */         } 
/*    */       } 
/*    */     } else {
/* 39 */       Vector direction = target.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
/* 40 */       direction.setX(direction.getX() * -1.0D);
/* 41 */       direction.setZ(direction.getZ() * -1.0D);
/* 42 */       target.setVelocity(direction);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillPull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */