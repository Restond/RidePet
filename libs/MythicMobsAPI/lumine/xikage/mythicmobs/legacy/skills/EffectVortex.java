/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class EffectVortex
/*    */ {
/*    */   public static void DoEffect(Location loc, String skill) {
/*    */     String p;
/*    */     int hC, h, interval, ticks;
/*    */     float r;
/* 13 */     if (CompatibilityManager.EffectLib == null)
/*    */       return; 
/* 15 */     MythicMobs.debug(4, "---- Executing Vortex skill");
/*    */     
/* 17 */     String[] data = skill.split(":");
/*    */     
/* 19 */     if (data.length < 9) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 24 */     float growth = 0.5F;
/* 25 */     double radials = 0.19634954084936207D;
/*    */     
/*    */     try {
/* 28 */       p = data[0];
/* 29 */       h = Integer.parseInt(data[1]);
/* 30 */       hC = Integer.parseInt(data[2]);
/* 31 */       r = Float.parseFloat(data[3]);
/* 32 */       interval = 1;
/* 33 */       ticks = 1;
/* 34 */     } catch (Exception ex) {
/* 35 */       MythicMobs.error("You have an incorrectly configured effect. Please see the plugin manual for the correct way to configure effects.");
/* 36 */       MythicMobs.error("Error skill string = " + skill);
/*    */       return;
/*    */     } 
/* 39 */     if (data.length > 4) {
/* 40 */       growth = Float.parseFloat(data[4]);
/*    */     }
/* 42 */     if (data.length > 5) {
/* 43 */       radials = Double.parseDouble(data[5]);
/*    */     }
/* 45 */     if (data.length > 6 && 
/* 46 */       data[6].matches("[0-9]*")) {
/* 47 */       interval = Integer.parseInt(data[6]);
/*    */     }
/*    */     
/* 50 */     if (data.length > 7 && 
/* 51 */       data[7].matches("[0-9]*")) {
/* 52 */       ticks = Integer.parseInt(data[7]);
/*    */     }
/*    */ 
/*    */     
/* 56 */     MythicMobs.debug(4, "------ Executing EffectLib effect VORTEX");
/* 57 */     CompatibilityManager.EffectLib.doVortexLocationEffect(loc, p, h, hC, radials, r, growth, interval, ticks);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectVortex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */