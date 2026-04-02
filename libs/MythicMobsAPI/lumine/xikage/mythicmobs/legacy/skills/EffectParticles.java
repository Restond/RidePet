/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectParticles
/*    */ {
/*    */   public static void DoEffect(Location l, String skill) {
/* 13 */     String[] data = skill.split(":");
/*    */     
/* 15 */     String particle = data[0];
/* 16 */     float hSpread = Float.parseFloat(data[1]);
/* 17 */     float vSpread = Float.parseFloat(data[2]);
/* 18 */     int amount = Integer.parseInt(data[3]);
/*    */     
/* 20 */     float pSpeed = 1.0F;
/* 21 */     if (data.length >= 5) {
/* 22 */       pSpeed = Float.parseFloat(data[4]);
/*    */     }
/* 24 */     float yOffset = 0.0F;
/* 25 */     if (data.length >= 6) {
/* 26 */       yOffset = Float.parseFloat(data[5]);
/*    */     }
/*    */     
/* 29 */     MythicMobs.debug(4, "Executing particle effect with the following arguments: particleName=" + particle + ",hSpread=" + hSpread + ",vSpread=" + vSpread + ",amount=" + amount + ",pSpeed=" + pSpeed + ",yOffset=" + yOffset);
/*    */     
/* 31 */     MythicMobs.inst().getVolatileCodeHandler().doParticleEffect(l, particle, hSpread, vSpread, amount, pSpeed, yOffset, 256);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */