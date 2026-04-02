/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import de.slikey.effectlib.util.ParticleEffect;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class EffectDNA {
/*    */   public static void DoEffect(Location loc, Location target, String skill) {
/*    */     ParticleEffect pH, pB1, pB2;
/*    */     int psH, psB, interval, ticks;
/*    */     float r, length, growth, baseInterval;
/*    */     double radials;
/* 14 */     if (CompatibilityManager.EffectLib == null)
/*    */       return; 
/* 16 */     String[] data = skill.split(":");
/*    */     
/* 18 */     if (data.length < 9) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 28 */       pH = CompatibilityManager.EffectLib.getParticleEffect(data[0]);
/* 29 */       psH = Integer.parseInt(data[1]);
/* 30 */       pB1 = CompatibilityManager.EffectLib.getParticleEffect(data[2]);
/* 31 */       pB2 = CompatibilityManager.EffectLib.getParticleEffect(data[3]);
/* 32 */       psB = Integer.parseInt(data[4]);
/* 33 */       radials = Double.parseDouble(data[5]);
/* 34 */       r = Float.parseFloat(data[6]);
/* 35 */       length = Float.parseFloat(data[7]);
/* 36 */       growth = Float.parseFloat(data[8]);
/* 37 */       baseInterval = Integer.parseInt(data[9]);
/* 38 */       interval = 1;
/* 39 */       ticks = 1;
/* 40 */     } catch (Exception ex) {
/* 41 */       MythicMobs.error("You have an incorrectly configured effect. Please see the plugin manual for the correct way to configure effects.");
/* 42 */       MythicMobs.error("Error skill string = " + skill);
/*    */       
/*    */       return;
/*    */     } 
/* 46 */     if (data.length > 9 && 
/* 47 */       data[1].matches("[0-9]*")) {
/* 48 */       interval = Integer.parseInt(data[9]);
/*    */     }
/*    */     
/* 51 */     if (data.length > 10 && 
/* 52 */       data[2].matches("[0-9]*")) {
/* 53 */       ticks = Integer.parseInt(data[10]);
/*    */     }
/*    */ 
/*    */     
/* 57 */     MythicMobs.debug(4, "Executing EffectLib effect BLEED");
/* 58 */     CompatibilityManager.EffectLib.doDNAEffect(loc, target, pH, psH, pB1, pB2, psB, radials, r, length, growth, baseInterval, interval, ticks);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectDNA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */