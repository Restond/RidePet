/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectLightningRing
/*    */ {
/*    */   public static void DoEffect(Location l, String skill) {
/* 11 */     String[] data = skill.split(":");
/*    */     
/* 13 */     double radius = Double.parseDouble(data[0]);
/* 14 */     int points = Integer.parseInt(data[1]);
/*    */ 
/*    */ 
/*    */     
/* 18 */     double bx = l.getX();
/* 19 */     double y = l.getY();
/* 20 */     double bz = l.getZ();
/* 21 */     World w = l.getWorld();
/*    */     
/* 23 */     if (points <= 0) points = 1; 
/* 24 */     float inc = (360 / points);
/*    */     double i;
/* 26 */     for (i = 0.0D; i < 360.0D; i += inc) {
/* 27 */       double angle = i * Math.PI / 180.0D;
/* 28 */       double x = (int)(bx + radius * Math.cos(angle));
/* 29 */       double z = (int)(bz + radius * Math.sin(angle));
/*    */       
/* 31 */       Location l2 = new Location(w, x, y, z);
/* 32 */       l2.getWorld().strikeLightningEffect(l2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectLightningRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */