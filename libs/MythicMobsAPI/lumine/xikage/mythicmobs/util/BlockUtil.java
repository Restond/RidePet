/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ 
/*    */ public class BlockUtil
/*    */ {
/* 10 */   private static final HashSet<Material> PATHABLE = new HashSet<>();
/* 11 */   private static final HashSet<Material> BREATHABLE = new HashSet<>();
/*    */   
/*    */   static {
/* 14 */     for (Material material : Material.values()) {
/* 15 */       if (!material.isOccluding()) {
/* 16 */         PATHABLE.add(material);
/*    */       
/*    */       }
/* 19 */       else if (material.toString().contains("SLAB")) {
/* 20 */         PATHABLE.add(material);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isPathable(Block block) {
/* 27 */     return isPathable(block.getType());
/*    */   }
/*    */   
/*    */   public static boolean isPathable(Material material) {
/* 31 */     return PATHABLE.contains(material);
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isBreathable(Block block) {
/* 82 */     return isBreathable(block.getType());
/*    */   }
/*    */   
/*    */   public static boolean isBreathable(Material material) {
/* 86 */     return (!material.isSolid() && material != Material.WATER && material != Material.LAVA);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\BlockUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */