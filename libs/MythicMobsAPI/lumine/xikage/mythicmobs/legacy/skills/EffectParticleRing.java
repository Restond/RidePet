/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectParticleRing
/*    */ {
/*    */   public static void DoEffect(Location l, String skill) {
/* 14 */     String[] data = skill.split(":");
/*    */     
/* 16 */     String particle = data[0];
/* 17 */     double radius = Double.parseDouble(data[1]);
/* 18 */     int points = Integer.parseInt(data[2]);
/* 19 */     float hSpread = Float.parseFloat(data[3]);
/* 20 */     float vSpread = Float.parseFloat(data[4]);
/* 21 */     int amount = Integer.parseInt(data[5]);
/*    */     
/* 23 */     float pSpeed = 1.0F;
/* 24 */     if (data.length >= 5) {
/* 25 */       pSpeed = Float.parseFloat(data[6]);
/*    */     }
/* 27 */     float yOffset = 0.0F;
/* 28 */     if (data.length >= 6) {
/* 29 */       yOffset = Float.parseFloat(data[7]);
/*    */     }
/*    */     
/* 32 */     MythicMobs.debug(4, "Executing ParticleRing effect with the following arguments: particleName=" + particle + ",hSpread=" + hSpread + ",vSpread=" + vSpread + ",amount=" + amount + ",pSpeed=" + pSpeed + ",yOffset=" + yOffset);
/*    */ 
/*    */ 
/*    */     
/* 36 */     double bx = l.getX();
/* 37 */     double y = l.getY();
/* 38 */     double bz = l.getZ();
/* 39 */     World w = l.getWorld();
/*    */     
/* 41 */     if (points <= 0) points = 1; 
/* 42 */     float inc = (360 / points);
/* 43 */     amount /= points;
/*    */     double i;
/* 45 */     for (i = 0.0D; i < 360.0D; i += inc) {
/* 46 */       double angle = i * Math.PI / 180.0D;
/* 47 */       double x = (int)(bx + radius * Math.cos(angle));
/* 48 */       double z = (int)(bz + radius * Math.sin(angle));
/*    */       
/* 50 */       Location l2 = new Location(w, x, y, z);
/* 51 */       MythicMobs.inst().getVolatileCodeHandler().doParticleEffect(l2, particle, hSpread, vSpread, amount, pSpeed, yOffset, 256);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectParticleRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */