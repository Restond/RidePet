/*    */ package lumine.xikage.mythicmobs.volatilecode.disabled;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*    */ 
/*    */ public interface VolatileEntityHandlerDisabled
/*    */ {
/*    */   AbstractEntity addNBTData(AbstractEntity paramAbstractEntity, String paramString, Tag paramTag);
/*    */   
/*    */   CompoundTag getNBTData(AbstractEntity paramAbstractEntity);
/*    */   
/*    */   AbstractEntity setNBTData(AbstractEntity paramAbstractEntity, CompoundTag paramCompoundTag);
/*    */   
/*    */   boolean isEntityInMotion(AbstractEntity paramAbstractEntity);
/*    */   
/*    */   default void setLocation(AbstractEntity entity, AbstractLocation location) {
/* 20 */     setLocation(entity, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), false, false);
/*    */   }
/*    */   
/*    */   default void setLocation(AbstractEntity entity, double x, double y, double z, float yaw, float pitch) {
/* 24 */     setLocation(entity, x, y, z, yaw, pitch, false, false);
/*    */   }
/*    */   
/*    */   void setLocation(AbstractEntity paramAbstractEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2);
/*    */   
/*    */   default void setHitBox(AbstractEntity target, double a0, double a1, double a2) {}
/*    */   
/*    */   default void setItemPosition(AbstractEntity target, AbstractLocation ol) {}
/*    */   
/*    */   default void sendEntityTeleportPacket(AbstractEntity target) {}
/*    */   
/*    */   void setEntityRotation(AbstractEntity paramAbstractEntity, float paramFloat1, float paramFloat2);
/*    */   
/*    */   void setArmorStandNoGravity(AbstractEntity paramAbstractEntity);
/*    */   
/*    */   void setSkybox(AbstractPlayer paramAbstractPlayer, int paramInt);
/*    */   
/*    */   default void forcePlayCredits(AbstractPlayer target, float f) {}
/*    */   
/*    */   default void forceCloseWindow(AbstractPlayer target) {}
/*    */   
/*    */   default void setPlayerWorldBorder(AbstractPlayer target, AbstractLocation center, int radius) {}
/*    */   
/*    */   float getEntityAbsorptionHearts(AbstractEntity paramAbstractEntity);
/*    */   
/*    */   void setEntityAbsorptionHearts(AbstractEntity paramAbstractEntity, float paramFloat);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\disabled\VolatileEntityHandlerDisabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */