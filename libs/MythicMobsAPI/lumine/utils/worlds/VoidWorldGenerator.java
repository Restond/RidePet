/*    */ package lumine.utils.worlds;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.generator.BlockPopulator;
/*    */ import org.bukkit.generator.ChunkGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VoidWorldGenerator
/*    */   extends ChunkGenerator
/*    */ {
/*    */   public List<BlockPopulator> getDefaultPopulators(World world) {
/* 17 */     return Arrays.asList(new BlockPopulator[0]);
/*    */   }
/*    */   
/*    */   public boolean canSpawn(World world, int x, int z) {
/* 21 */     return true;
/*    */   }
/*    */   
/*    */   public int xyzToByte(int x, int y, int z) {
/* 25 */     return (x * 16 + z) * 128 + y;
/*    */   }
/*    */   
/*    */   public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
/* 29 */     return new byte[32768];
/*    */   }
/*    */   
/*    */   public Location getFixedSpawnLocation(World world, Random random) {
/* 33 */     return new Location(world, 68.0D, 84.0D, 73.0D);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\worlds\VoidWorldGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */