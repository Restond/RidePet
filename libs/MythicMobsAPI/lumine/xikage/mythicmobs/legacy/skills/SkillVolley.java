/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Arrow;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ public class SkillVolley
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/*    */     Vector v;
/* 18 */     String[] base = skill.split(" ");
/* 19 */     String[] data = base[1].split(":");
/*    */     
/* 21 */     int arrows = Integer.parseInt(data[0]);
/* 22 */     float speed = Float.parseFloat(data[1]) / 10.0F;
/* 23 */     int spread = Integer.parseInt(data[2]);
/* 24 */     int fire = 0;
/* 25 */     int removeDelay = 200;
/*    */     
/* 27 */     if (data.length > 3) {
/* 28 */       fire = Integer.parseInt(data[3]);
/*    */     }
/*    */     
/* 31 */     Location spawn = l.getLocation().clone();
/* 32 */     spawn.setY(spawn.getY() + 3.0D);
/*    */ 
/*    */     
/* 35 */     if (target == null) {
/* 36 */       v = spawn.getDirection();
/*    */     } else {
/* 38 */       v = target.getLocation().toVector().subtract(spawn.toVector()).normalize();
/*    */     } 
/*    */     
/* 41 */     ArrayList<Arrow> arrowList = new ArrayList<>();
/*    */     
/* 43 */     for (int i = 0; i < arrows; i++) {
/* 44 */       Arrow a = spawn.getWorld().spawnArrow(spawn, v, speed, spread / 10.0F);
/* 45 */       a.setVelocity(a.getVelocity());
/* 46 */       a.setShooter((ProjectileSource)l);
/*    */       
/* 48 */       if (fire > 0) {
/* 49 */         a.setFireTicks(fire);
/*    */       }
/* 51 */       arrowList.add(a);
/*    */     } 
/*    */     
/* 54 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)new Object(arrowList), removeDelay);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillVolley.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */