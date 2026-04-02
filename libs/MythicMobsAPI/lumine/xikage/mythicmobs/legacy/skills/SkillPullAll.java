/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillPullAll
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 14 */     String[] base = skill.split(" ");
/* 15 */     String[] data = base[1].split(":");
/*    */     
/* 17 */     int radius = Integer.parseInt(data[0]);
/* 18 */     float velocity = Float.parseFloat(data[1]);
/*    */     
/* 20 */     for (LivingEntity p : SkillHelper.getLivingEntitiesInRadius(l, radius)) {
/* 21 */       if (p.hasLineOfSight((Entity)l)) {
/* 22 */         double distance = l.getLocation().distance(p.getLocation());
/* 23 */         double modxz = distance * 0.5D * velocity;
/* 24 */         double mody = distance * 0.34D * velocity;
/* 25 */         mody = (l.getLocation().getY() - p.getLocation().getY() != 0.0D) ? (mody * Math.abs(l.getLocation().getY() - p.getLocation().getY()) * 0.5D) : mody;
/*    */         
/* 27 */         Vector direction = p.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
/* 28 */         direction.setX(direction.getX() * -1.0D * modxz);
/* 29 */         direction.setZ(direction.getZ() * -1.0D * modxz);
/* 30 */         direction.setY(direction.getY() * -1.0D * mody);
/* 31 */         p.setVelocity(direction);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillPullAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */