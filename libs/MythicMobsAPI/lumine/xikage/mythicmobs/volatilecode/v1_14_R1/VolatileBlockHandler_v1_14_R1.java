/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1;
/*    */ 
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileBlockHandler;
/*    */ import net.minecraft.server.v1_14_R1.Block;
/*    */ import net.minecraft.server.v1_14_R1.BlockPosition;
/*    */ import net.minecraft.server.v1_14_R1.IBlockData;
/*    */ import net.minecraft.server.v1_14_R1.WorldServer;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
/*    */ 
/*    */ public class VolatileBlockHandler_v1_14_R1
/*    */   implements VolatileBlockHandler {
/*    */   public VolatileBlockHandler_v1_14_R1(VolatileCodeHandler handler) {}
/*    */   
/*    */   public void applyPhysics(Block target) {
/* 23 */     Location location = target.getLocation();
/* 24 */     WorldServer worldServer = ((CraftWorld)location.getWorld()).getHandle();
/* 25 */     BlockPosition blockposition = new BlockPosition(location.getX(), location.getY(), location.getZ());
/* 26 */     IBlockData iblockdata = worldServer.getType(blockposition);
/* 27 */     Block block = iblockdata.getBlock();
/*    */     
/* 29 */     worldServer.applyPhysics(blockposition, block);
/*    */   }
/*    */ 
/*    */   
/*    */   public void togglePowerable(AbstractLocation location) {
/* 34 */     togglePowerable(location, 0L);
/*    */   }
/*    */ 
/*    */   
/*    */   public void togglePowerable(AbstractLocation location, long duration) {
/* 39 */     Location l = BukkitAdapter.adapt(location);
/* 40 */     Block block = l.getWorld().getBlockAt(l);
/*    */     
/* 42 */     BlockData bd = block.getBlockData();
/*    */     
/* 44 */     if (bd instanceof Powerable) {
/* 45 */       Powerable pbd = (Powerable)bd;
/* 46 */       pbd.setPowered(true);
/* 47 */       block.setBlockData((BlockData)pbd);
/* 48 */       applyPhysics(block);
/*    */       
/* 50 */       Scheduler.runLaterSync(() -> { paramPowerable.setPowered(false); paramBlock.setBlockData((BlockData)paramPowerable); applyPhysics(paramBlock); }duration);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\VolatileBlockHandler_v1_14_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */