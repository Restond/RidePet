/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectParticleLine
/*    */ {
/*    */   public static void DoEffect(LivingEntity le, Location l, String skill) {
/* 14 */     String[] data = skill.split(":");
/*    */     
/* 16 */     String particle = data[0];
/* 17 */     float hSpread = Float.parseFloat(data[1]);
/* 18 */     float vSpread = Float.parseFloat(data[2]);
/* 19 */     int amount = Integer.parseInt(data[3]);
/* 20 */     float distanceBetween = 0.5F;
/*    */     
/* 22 */     float pSpeed = 1.0F;
/* 23 */     if (data.length >= 5) {
/* 24 */       pSpeed = Float.parseFloat(data[4]);
/*    */     }
/* 26 */     float yOffset = 0.0F;
/* 27 */     if (data.length >= 6) {
/* 28 */       yOffset = Float.parseFloat(data[5]);
/*    */     }
/* 30 */     if (data.length >= 7) {
/* 31 */       distanceBetween = Float.parseFloat(data[6]);
/*    */     }
/* 33 */     float yStartOffset = 0.0F;
/* 34 */     if (data.length >= 8) {
/* 35 */       yStartOffset = Float.parseFloat(data[7]);
/*    */     }
/*    */     
/* 38 */     MythicMobs.debug(4, "Executing particle line effect with the following arguments: particleName=" + particle + ",hSpread=" + hSpread + ",vSpread=" + vSpread + ",amount=" + amount + ",pSpeed=" + pSpeed + ",yOffset=" + yOffset);
/*    */     
/* 40 */     Location sl = le.getLocation().add(0.0D, yStartOffset, 0.0D);
/*    */     
/* 42 */     int c = (int)Math.ceil(sl.distance(l) / distanceBetween) - 1;
/* 43 */     if (c <= 0)
/* 44 */       return;  Vector v = l.toVector().subtract(sl.toVector()).normalize().multiply(distanceBetween);
/* 45 */     Location l2 = sl.clone();
/*    */     
/* 47 */     for (int i = 0; i < c; i++) {
/* 48 */       l2.add(v);
/* 49 */       MythicMobs.inst().getVolatileCodeHandler().doParticleEffect(l2, particle, hSpread, vSpread, amount, pSpeed, yOffset, 256);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectParticleLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */