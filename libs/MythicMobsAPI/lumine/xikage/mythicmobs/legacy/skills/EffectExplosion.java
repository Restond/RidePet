/*   */ package lumine.xikage.mythicmobs.legacy.skills;
/*   */ 
/*   */ import org.bukkit.Location;
/*   */ 
/*   */ public class EffectExplosion {
/*   */   public static void DoEffect(Location location) {
/* 7 */     location.getWorld().createExplosion(location, 0.0F);
/*   */   }
/*   */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectExplosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */