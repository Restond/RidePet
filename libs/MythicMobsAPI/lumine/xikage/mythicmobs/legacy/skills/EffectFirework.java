/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectFirework
/*    */ {
/*    */   public static void DoEffect(Location l, String strData) {
/* 11 */     String[] data = strData.split(":");
/*    */     
/* 13 */     int type = 0;
/* 14 */     if (data.length >= 1) {
/* 15 */       type = Integer.parseInt(data[0]);
/*    */     }
/* 17 */     int[] colors = { 16711680 };
/* 18 */     if (data.length >= 2) {
/* 19 */       String[] c = data[1].split(",");
/* 20 */       colors = new int[c.length];
/* 21 */       for (int i = 0; i < c.length; i++) {
/* 22 */         colors[i] = Integer.parseInt(c[i], 16);
/*    */       }
/*    */     } 
/*    */     
/* 26 */     int[] fadeColors = { 16711680 };
/* 27 */     if (data.length >= 3) {
/* 28 */       String[] c = data[2].split(",");
/* 29 */       fadeColors = new int[c.length];
/* 30 */       for (int i = 0; i < c.length; i++) {
/* 31 */         fadeColors[i] = Integer.parseInt(c[i], 16);
/*    */       }
/*    */     } 
/* 34 */     boolean flicker = false;
/* 35 */     if (data.length >= 4) {
/* 36 */       flicker = Boolean.valueOf(data[3]).booleanValue();
/*    */     }
/* 38 */     boolean trail = false;
/* 39 */     if (data.length >= 5) {
/* 40 */       trail = Boolean.valueOf(data[4]).booleanValue();
/*    */     }
/* 42 */     int flightDuration = 0;
/* 43 */     if (data.length >= 6) {
/* 44 */       flightDuration = Integer.parseInt(data[5]);
/*    */     }
/*    */     
/* 47 */     MythicMobs.inst().getVolatileCodeHandler().CreateFireworksExplosion(l, flicker, trail, type, colors, fadeColors, flightDuration);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */