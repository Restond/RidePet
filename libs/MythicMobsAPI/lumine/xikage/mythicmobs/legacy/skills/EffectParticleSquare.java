/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectParticleSquare
/*    */ {
/*    */   public static void DoEffect(Location l, String skill) {
/* 13 */     String[] data = skill.split(":");
/*    */     
/* 15 */     String particle = data[0];
/* 16 */     double radius = Double.parseDouble(data[1]);
/* 17 */     float hSpread = Float.parseFloat(data[2]);
/* 18 */     float vSpread = Float.parseFloat(data[3]);
/* 19 */     int amount = Integer.parseInt(data[4]);
/*    */     
/* 21 */     float pSpeed = 1.0F;
/* 22 */     if (data.length >= 5) {
/* 23 */       pSpeed = Float.parseFloat(data[5]);
/*    */     }
/* 25 */     float yOffset = 0.0F;
/* 26 */     if (data.length >= 6) {
/* 27 */       yOffset = Float.parseFloat(data[6]);
/*    */     }
/*    */     
/* 30 */     MythicMobs.debug(4, "Executing ParticleSquare effect with the following arguments: particleName=" + particle + ",hSpread=" + hSpread + ",vSpread=" + vSpread + ",amount=" + amount + ",pSpeed=" + pSpeed + ",yOffset=" + yOffset);
/*    */ 
/*    */     
/* 33 */     double bx = l.getX();
/* 34 */     double y = l.getY();
/* 35 */     double bz = l.getZ();
/*    */     
/* 37 */     amount = (int)(amount / radius * 8.0D);
/*    */     double x;
/* 39 */     for (x = bx - radius; x <= bx + radius; x++) {
/* 40 */       double z; for (z = bz - radius; z <= bz + radius; z++) {
/* 41 */         if (Math.abs(x - bx) == radius || Math.abs(z - bz) == radius) {
/* 42 */           Location l2 = new Location(l.getWorld(), x, y, z);
/* 43 */           MythicMobs.inst().getVolatileCodeHandler().doParticleEffect(l2, particle, hSpread, vSpread, amount, pSpeed, yOffset, 32);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectParticleSquare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */