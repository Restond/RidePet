/*    */ package lumine.xikage.mythicmobs.volatilecode.disabled;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileWorldHandler;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VolatileWorldHandlerDisabled
/*    */   implements VolatileWorldHandler
/*    */ {
/*    */   public void registerWorldAccess(World world) {}
/*    */   
/*    */   public void unregisterWorldAccess(World world) {}
/*    */   
/*    */   public void playSoundAtLocation(AbstractLocation location, String sound, float volume, float pitch, double radius) {
/* 21 */     Location l = BukkitAdapter.adapt(location);
/* 22 */     for (Player player : l.getWorld().getPlayers()) {
/* 23 */       player.playSound(l, sound, volume, pitch);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDifficultyScale(AbstractLocation location) {
/* 29 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\disabled\VolatileWorldHandlerDisabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */