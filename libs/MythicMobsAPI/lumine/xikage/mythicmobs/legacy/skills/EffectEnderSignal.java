/*   */ package lumine.xikage.mythicmobs.legacy.skills;
/*   */ 
/*   */ import org.bukkit.Effect;
/*   */ import org.bukkit.Location;
/*   */ 
/*   */ public class EffectEnderSignal {
/*   */   public static void DoEffect(Location location) {
/* 8 */     location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 0);
/*   */   }
/*   */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectEnderSignal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */