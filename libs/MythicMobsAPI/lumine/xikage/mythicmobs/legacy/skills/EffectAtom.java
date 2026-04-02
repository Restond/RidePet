/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import de.slikey.effectlib.util.ParticleEffect;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectAtom
/*    */ {
/*    */   public static void DoEffect(Location loc, String skill) {
/* 14 */     if (CompatibilityManager.EffectLib == null)
/*    */       return; 
/* 16 */     String[] data = skill.split(":");
/*    */     
/* 18 */     if (data.length < 9)
/*    */       return; 
/* 20 */     ParticleEffect pN = CompatibilityManager.EffectLib.getParticleEffect(data[0]);
/* 21 */     int psN = 1;
/* 22 */     ParticleEffect pO = CompatibilityManager.EffectLib.getParticleEffect(data[2]);
/* 23 */     int psO = 1;
/* 24 */     int nO = 1;
/* 25 */     int r = 1;
/*    */     
/* 27 */     double velocity = 1.0D;
/* 28 */     int rotation = 1;
/* 29 */     int interval = 1;
/* 30 */     int ticks = 1;
/*    */     
/* 32 */     psN = Integer.parseInt(data[1]);
/* 33 */     psO = Integer.parseInt(data[3]);
/* 34 */     nO = Integer.parseInt(data[4]);
/* 35 */     r = Integer.parseInt(data[5]);
/* 36 */     float rN = Float.parseFloat(data[6]);
/* 37 */     velocity = Double.parseDouble(data[7]);
/* 38 */     rotation = Integer.parseInt(data[8]);
/*    */     
/* 40 */     if (data.length > 9 && 
/* 41 */       data[1].matches("[0-9]*")) {
/* 42 */       interval = Integer.parseInt(data[9]);
/*    */     }
/*    */     
/* 45 */     if (data.length > 10 && 
/* 46 */       data[2].matches("[0-9]*")) {
/* 47 */       ticks = Integer.parseInt(data[10]);
/*    */     }
/*    */ 
/*    */     
/* 51 */     MythicMobs.debug(4, "Executing EffectLib effect BLEED");
/* 52 */     CompatibilityManager.EffectLib.doAtomEffect(loc, pN, psN, pO, psO, nO, r, rN, velocity, rotation, interval, ticks);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectAtom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */