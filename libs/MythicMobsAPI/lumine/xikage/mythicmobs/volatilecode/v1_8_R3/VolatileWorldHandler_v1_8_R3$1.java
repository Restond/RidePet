/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_8_R3;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_8_R3.VolatileWorldHandler_v1_8_R3;
/*    */ import net.minecraft.server.v1_8_R3.BlockPosition;
/*    */ import net.minecraft.server.v1_8_R3.Entity;
/*    */ import net.minecraft.server.v1_8_R3.EntityHuman;
/*    */ import net.minecraft.server.v1_8_R3.IWorldAccess;
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
/*    */   public void b(Entity entity) {
/* 39 */     MythicMobs.inst().getMobManager().setEntityDespawned(entity.getBukkitEntity().getUniqueId());
/*    */   }
/*    */   
/*    */   public void a(BlockPosition arg0) {}
/*    */   
/*    */   public void a(Entity arg0) {}
/*    */   
/*    */   public void a(String arg0, BlockPosition arg1) {}
/*    */   
/*    */   public void a(int arg0, BlockPosition arg1, int arg2) {}
/*    */   
/*    */   public void a(EntityHuman arg0, int arg1, BlockPosition arg2, int arg3) {}
/*    */   
/*    */   public void a(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {}
/*    */   
/*    */   public void a(String arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {}
/*    */   
/*    */   public void a(EntityHuman arg0, String arg1, double arg2, double arg3, double arg4, float arg5, float arg6) {}
/*    */   
/*    */   public void a(int arg0, boolean arg1, double arg2, double arg3, double arg4, double arg5, double arg6, double arg7, int... arg8) {}
/*    */   
/*    */   public void b(BlockPosition arg0) {}
/*    */   
/*    */   public void b(int arg0, BlockPosition arg1, int arg2) {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_8_R3\VolatileWorldHandler_v1_8_R3$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */