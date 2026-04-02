/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillLeap
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 15 */     if (target == null)
/*    */       return; 
/* 17 */     String[] base = skill.split(" ");
/* 18 */     String[] data = base[1].split(":");
/*    */     
/* 20 */     int gravity = 20;
/*    */ 
/*    */     
/* 23 */     double velocity = Double.parseDouble(data[0]) / 10.0D;
/* 24 */     double cNoise = 1.0D;
/*    */     
/* 26 */     if (data.length > 1) {
/* 27 */       cNoise = Double.parseDouble(data[1]);
/*    */     }
/*    */     
/* 30 */     Location to = target.getLocation();
/* 31 */     Vector test = to.clone().subtract(l.getLocation()).toVector();
/* 32 */     Double elevation = Double.valueOf(test.getY());
/*    */     
/* 34 */     Double launchAngle = MythicUtil.calculateLaunchAngle(l.getLocation(), to, velocity, elevation.doubleValue(), 20.0D);
/* 35 */     Double distance = Double.valueOf(Math.sqrt(Math.pow(test.getX(), 2.0D) + Math.pow(test.getZ(), 2.0D)));
/*    */     
/* 37 */     if (distance.doubleValue() == 0.0D)
/*    */       return; 
/* 39 */     if (launchAngle == null) {
/* 40 */       launchAngle = Double.valueOf(Math.atan((40.0D * elevation.doubleValue() + Math.pow(velocity, 2.0D)) / (40.0D * elevation.doubleValue() + 2.0D * Math.pow(velocity, 2.0D))));
/*    */     }
/*    */     
/* 43 */     Double hangtime = Double.valueOf(MythicUtil.calculateHangtime(launchAngle.doubleValue(), velocity, elevation.doubleValue(), 20.0D));
/*    */     
/* 45 */     test.setY(Math.tan(launchAngle.doubleValue()) * distance.doubleValue());
/* 46 */     test = MythicUtil.normalizeVector(test);
/*    */     
/* 48 */     Vector noise = Vector.getRandom();
/* 49 */     noise = noise.multiply(cNoise / 10.0D);
/* 50 */     test.add(noise);
/*    */     
/* 52 */     velocity = velocity + 1.188D * Math.pow(hangtime.doubleValue(), 2.0D) + (MythicMobs.r.nextDouble() - 0.8D) / 2.0D;
/* 53 */     test = test.multiply(velocity / 20.0D);
/*    */     
/* 55 */     l.setVelocity(test);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillLeap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */