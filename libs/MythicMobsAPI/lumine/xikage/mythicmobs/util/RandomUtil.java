/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RandomUtil
/*    */ {
/* 12 */   public static final Random random = new Random(System.nanoTime());
/*    */ 
/*    */   
/*    */   public static AbstractVector getRandomVector() {
/* 16 */     double x = random.nextDouble() * 2.0D - 1.0D;
/* 17 */     double y = random.nextDouble() * 2.0D - 1.0D;
/* 18 */     double z = random.nextDouble() * 2.0D - 1.0D;
/*    */     
/* 20 */     return (new AbstractVector(x, y, z)).normalize();
/*    */   }
/*    */ 
/*    */   
/*    */   public static AbstractVector getRandomCircleVector() {
/* 25 */     double rnd = random.nextDouble() * 2.0D * Math.PI;
/* 26 */     double x = Math.cos(rnd);
/* 27 */     double z = Math.sin(rnd);
/*    */     
/* 29 */     return new AbstractVector(x, 0.0D, z);
/*    */   }
/*    */   
/*    */   public static Material getRandomMaterial(Material[] materials) {
/* 33 */     return materials[random.nextInt(materials.length)];
/*    */   }
/*    */   
/*    */   public static double getRandomAngle() {
/* 37 */     return random.nextDouble() * 2.0D * Math.PI;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\RandomUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */