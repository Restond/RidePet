/*    */ package lumine.xikage.mythicmobs.volatilecode.handlers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ public interface VolatileWorldHandler
/*    */ {
/*    */   void registerWorldAccess(World paramWorld);
/*    */   
/*    */   void unregisterWorldAccess(World paramWorld);
/*    */   
/*    */   void playSoundAtLocation(AbstractLocation paramAbstractLocation, String paramString, float paramFloat1, float paramFloat2, double paramDouble);
/*    */   
/*    */   default boolean isChunkLoaded(AbstractWorld world, int x, int z) {
/* 18 */     return true;
/*    */   }
/*    */   
/*    */   default void doBlockTossEffect(AbstractLocation target, Material material, AbstractVector velocity, int duration, boolean hideSourceBlock) {}
/*    */   
/*    */   float getDifficultyScale(AbstractLocation paramAbstractLocation);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\handlers\VolatileWorldHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */