/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_12_R1;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileBlockHandler;
/*    */ import net.minecraft.server.v1_12_R1.Block;
/*    */ import net.minecraft.server.v1_12_R1.BlockPosition;
/*    */ import net.minecraft.server.v1_12_R1.IBlockData;
/*    */ import net.minecraft.server.v1_12_R1.WorldServer;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*    */ 
/*    */ public class VolatileBlockHandler_v1_12_R1
/*    */   implements VolatileBlockHandler
/*    */ {
/*    */   public VolatileBlockHandler_v1_12_R1(VolatileCodeHandler handler) {}
/*    */   
/*    */   public void applyPhysics(Block target) {
/* 20 */     Location location = target.getLocation();
/* 21 */     WorldServer worldServer = ((CraftWorld)location.getWorld()).getHandle();
/* 22 */     BlockPosition blockposition = new BlockPosition(location.getX(), location.getY(), location.getZ());
/* 23 */     IBlockData iblockdata = worldServer.getType(blockposition);
/* 24 */     Block block = iblockdata.getBlock();
/*    */     
/* 26 */     worldServer.applyPhysics(blockposition, block, true);
/*    */   }
/*    */   
/*    */   public void togglePowerable(AbstractLocation location) {}
/*    */   
/*    */   public void togglePowerable(AbstractLocation location, long duration) {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_12_R1\VolatileBlockHandler_v1_12_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */