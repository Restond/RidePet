/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_13_R2;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.VolatileWorldHandler_v1_13_R2;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.v1_13_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_13_R2.Entity;
/*    */ import net.minecraft.server.v1_13_R2.EntityHuman;
/*    */ import net.minecraft.server.v1_13_R2.IBlockAccess;
/*    */ import net.minecraft.server.v1_13_R2.IBlockData;
/*    */ import net.minecraft.server.v1_13_R2.IWorldAccess;
/*    */ import net.minecraft.server.v1_13_R2.ParticleParam;
/*    */ import net.minecraft.server.v1_13_R2.SoundCategory;
/*    */ import net.minecraft.server.v1_13_R2.SoundEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements IWorldAccess
/*    */ {
/*    */   public void a(IBlockAccess iBlockAccess, BlockPosition blockPosition, IBlockData iBlockData, IBlockData iBlockData1, int i) {}
/*    */   
/*    */   public void a(BlockPosition blockPosition) {}
/*    */   
/*    */   public void a(int i, int i1, int i2, int i3, int i4, int i5) {}
/*    */   
/*    */   public void a(@Nullable EntityHuman entityHuman, SoundEffect soundEffect, SoundCategory soundCategory, double v, double v1, double v2, float v3, float v4) {}
/*    */   
/*    */   public void a(SoundEffect soundEffect, BlockPosition blockPosition) {}
/*    */   
/*    */   public void a(ParticleParam particleParam, boolean b, double v, double v1, double v2, double v3, double v4, double v5) {}
/*    */   
/*    */   public void a(ParticleParam particleParam, boolean b, boolean b1, double v, double v1, double v2, double v3, double v4, double v5) {}
/*    */   
/*    */   public void a(Entity entity) {}
/*    */   
/*    */   public void b(Entity entity) {
/* 78 */     MythicMobs.inst().getMobManager().setEntityDespawned(entity.getBukkitEntity().getUniqueId());
/*    */   }
/*    */   
/*    */   public void a(int i, BlockPosition blockPosition, int i1) {}
/*    */   
/*    */   public void a(EntityHuman entityHuman, int i, BlockPosition blockPosition, int i1) {}
/*    */   
/*    */   public void b(int i, BlockPosition blockPosition, int i1) {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_13_R2\VolatileWorldHandler_v1_13_R2$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */