/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class EffectSmoke
/*    */ {
/*    */   public static void DoEffect(Location location, String ed) {
/*  9 */     int direction = 4;
/* 10 */     if (ed != null) {
/*    */       try {
/* 12 */         direction = Integer.parseInt(ed);
/* 13 */       } catch (NumberFormatException numberFormatException) {}
/*    */     }
/*    */ 
/*    */     
/* 17 */     location.getWorld().playEffect(location, Effect.SMOKE, direction);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectSmoke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */