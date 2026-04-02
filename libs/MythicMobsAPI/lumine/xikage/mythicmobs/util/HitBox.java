/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*    */ 
/*    */ public class HitBox
/*    */ {
/*    */   AbstractWorld world;
/*    */   double lowX;
/*    */   double lowY;
/*    */   
/*    */   public HitBox(AbstractLocation center, double radius) {
/* 14 */     this(center, radius, radius);
/*    */   }
/*    */   double lowZ; double highX; double highY; double highZ;
/*    */   public HitBox(AbstractLocation center, double horizRadius, double vertRadius) {
/* 18 */     this.world = center.getWorld();
/* 19 */     this.lowX = center.getX() - horizRadius;
/* 20 */     this.lowY = center.getY() - vertRadius;
/* 21 */     this.lowZ = center.getZ() - horizRadius;
/* 22 */     this.highX = center.getX() + horizRadius;
/* 23 */     this.highY = center.getY() + vertRadius;
/* 24 */     this.highZ = center.getZ() + horizRadius;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean contains(AbstractLocation location) {
/* 29 */     if (!location.getWorld().equals(this.world)) {
/* 30 */       return false;
/*    */     }
/* 32 */     double x = location.getX();
/* 33 */     double y = location.getY();
/* 34 */     double z = location.getZ();
/* 35 */     return (this.lowX <= x && x <= this.highX && this.lowY <= y && y <= this.highY && this.lowZ <= z && z <= this.highZ);
/*    */   }
/*    */   
/*    */   public boolean contains(AbstractEntity entity) {
/* 39 */     return contains(entity.getLocation());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\HitBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */