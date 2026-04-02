/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectRadiusFireworks
/*    */ {
/*    */   public static void DoEffect(Location l, String strData) {
/* 15 */     String[] data = strData.split(":");
/*    */     
/* 17 */     int amount = Integer.parseInt(data[0]);
/* 18 */     int radius = Integer.parseInt(data[1]);
/*    */     
/* 20 */     int type = 0;
/* 21 */     if (data.length >= 3) {
/* 22 */       type = Integer.parseInt(data[2]);
/*    */     }
/* 24 */     int[] colors = { 16711680 };
/* 25 */     if (data.length >= 4) {
/* 26 */       String[] c = data[3].split(",");
/* 27 */       colors = new int[c.length];
/* 28 */       for (int i = 0; i < c.length; i++) {
/* 29 */         colors[i] = Integer.parseInt(c[i], 16);
/*    */       }
/*    */     } 
/*    */     
/* 33 */     int[] fadeColors = { 16711680 };
/* 34 */     if (data.length >= 5) {
/* 35 */       String[] c = data[4].split(",");
/* 36 */       fadeColors = new int[c.length];
/* 37 */       for (int i = 0; i < c.length; i++) {
/* 38 */         fadeColors[i] = Integer.parseInt(c[i], 16);
/*    */       }
/*    */     } 
/* 41 */     boolean flicker = false;
/* 42 */     if (data.length >= 6) {
/* 43 */       flicker = Boolean.valueOf(data[5]).booleanValue();
/*    */     }
/* 45 */     boolean trail = false;
/* 46 */     if (data.length >= 7) {
/* 47 */       trail = Boolean.valueOf(data[6]).booleanValue();
/*    */     }
/* 49 */     int flightDuration = 0;
/* 50 */     if (data.length >= 8) {
/* 51 */       flightDuration = Integer.parseInt(data[7]);
/*    */     }
/*    */     
/* 54 */     for (Location loc : getLocations(amount, radius, l)) {
/* 55 */       MythicMobs.inst().getVolatileCodeHandler().CreateFireworksExplosion(loc, flicker, trail, type, colors, fadeColors, flightDuration);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static List<Location> getLocations(int amount, int radious, Location loc) {
/* 61 */     List<Location> list = new ArrayList<>();
/* 62 */     double Sangle = (360 / amount);
/* 63 */     for (int i = 0; i < amount; i++) {
/*    */       
/* 65 */       double x = Math.cos(Sangle * i);
/* 66 */       double z = Math.sin(Sangle * i);
/* 67 */       Location l = new Location(loc.getWorld(), loc.getX() + x * radious, loc.getY(), loc.getZ() + z * radious);
/* 68 */       list.add(l);
/*    */     } 
/* 70 */     return list;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectRadiusFireworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */