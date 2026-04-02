/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ public class VectorUtils
/*     */ {
/*     */   public static float clampYaw(float yaw) {
/*  11 */     while (yaw < -180.0F) {
/*  12 */       yaw += 360.0F;
/*     */     }
/*     */     
/*  15 */     while (yaw >= 180.0F) {
/*  16 */       yaw -= 360.0F;
/*     */     }
/*  18 */     return yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final AbstractVector rotateAroundAxisX(AbstractVector v, double angle) {
/*  23 */     double cos = Math.cos(angle);
/*  24 */     double sin = Math.sin(angle);
/*  25 */     double y = v.getY() * cos - v.getZ() * sin;
/*  26 */     double z = v.getY() * sin + v.getZ() * cos;
/*  27 */     return v.setY(y).setZ(z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final AbstractVector rotateAroundAxisY(AbstractVector v, double angle) {
/*  32 */     double cos = Math.cos(angle);
/*  33 */     double sin = Math.sin(angle);
/*  34 */     double x = v.getX() * cos + v.getZ() * sin;
/*  35 */     double z = v.getX() * -sin + v.getZ() * cos;
/*  36 */     return v.setX(x).setZ(z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final AbstractVector rotateAroundAxisZ(AbstractVector v, double angle) {
/*  41 */     double cos = Math.cos(angle);
/*  42 */     double sin = Math.sin(angle);
/*  43 */     double x = v.getX() * cos - v.getY() * sin;
/*  44 */     double y = v.getX() * sin + v.getY() * cos;
/*  45 */     return v.setX(x).setY(y);
/*     */   }
/*     */   
/*     */   public static final AbstractVector rotateVector(AbstractVector v, double angleX, double angleY, double angleZ) {
/*  49 */     rotateAroundAxisX(v, angleX);
/*  50 */     rotateAroundAxisY(v, angleY);
/*  51 */     rotateAroundAxisZ(v, angleZ);
/*  52 */     return v;
/*     */   }
/*     */   
/*     */   public static final AbstractVector rotateVector(AbstractVector v, Location location) {
/*  56 */     return rotateVector(v, location.getYaw(), location.getPitch());
/*     */   }
/*     */   
/*     */   public static final AbstractVector rotateVector(AbstractVector v, float yawDegrees, float pitchDegrees) {
/*  60 */     double yaw = Math.toRadians((-1.0F * (yawDegrees + 90.0F)));
/*  61 */     double pitch = Math.toRadians(-pitchDegrees);
/*     */     
/*  63 */     double cosYaw = Math.cos(yaw);
/*  64 */     double cosPitch = Math.cos(pitch);
/*  65 */     double sinYaw = Math.sin(yaw);
/*  66 */     double sinPitch = Math.sin(pitch);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     double initialX = v.getX();
/*  73 */     double initialY = v.getY();
/*  74 */     double x = initialX * cosPitch - initialY * sinPitch;
/*  75 */     double y = initialX * sinPitch + initialY * cosPitch;
/*     */ 
/*     */     
/*  78 */     double initialZ = v.getZ();
/*  79 */     initialX = x;
/*  80 */     double z = initialZ * cosYaw - initialX * sinYaw;
/*  81 */     x = initialZ * sinYaw + initialX * cosYaw;
/*     */     
/*  83 */     return new AbstractVector(x, y, z);
/*     */   }
/*     */   
/*     */   public static final double angleToXAxis(AbstractVector abstractVector) {
/*  87 */     return Math.atan2(abstractVector.getX(), abstractVector.getY());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Vector rotateAroundAxisX(Vector v, double angle) {
/*  92 */     double cos = Math.cos(angle);
/*  93 */     double sin = Math.sin(angle);
/*  94 */     double y = v.getY() * cos - v.getZ() * sin;
/*  95 */     double z = v.getY() * sin + v.getZ() * cos;
/*  96 */     return v.setY(y).setZ(z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Vector rotateAroundAxisY(Vector v, double angle) {
/* 101 */     double cos = Math.cos(angle);
/* 102 */     double sin = Math.sin(angle);
/* 103 */     double x = v.getX() * cos + v.getZ() * sin;
/* 104 */     double z = v.getX() * -sin + v.getZ() * cos;
/* 105 */     return v.setX(x).setZ(z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Vector rotateAroundAxisZ(Vector v, double angle) {
/* 110 */     double cos = Math.cos(angle);
/* 111 */     double sin = Math.sin(angle);
/* 112 */     double x = v.getX() * cos - v.getY() * sin;
/* 113 */     double y = v.getX() * sin + v.getY() * cos;
/* 114 */     return v.setX(x).setY(y);
/*     */   }
/*     */   
/*     */   public static final Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
/* 118 */     rotateAroundAxisX(v, angleX);
/* 119 */     rotateAroundAxisY(v, angleY);
/* 120 */     rotateAroundAxisZ(v, angleZ);
/* 121 */     return v;
/*     */   }
/*     */   
/*     */   public static final Vector rotateVector(Vector v, Location location) {
/* 125 */     return rotateVector(v, location.getYaw(), location.getPitch());
/*     */   }
/*     */   
/*     */   public static final Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
/* 129 */     double yaw = Math.toRadians((-1.0F * (yawDegrees + 90.0F)));
/* 130 */     double pitch = Math.toRadians(-pitchDegrees);
/*     */     
/* 132 */     double cosYaw = Math.cos(yaw);
/* 133 */     double cosPitch = Math.cos(pitch);
/* 134 */     double sinYaw = Math.sin(yaw);
/* 135 */     double sinPitch = Math.sin(pitch);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     double initialX = v.getX();
/* 142 */     double initialY = v.getY();
/* 143 */     double x = initialX * cosPitch - initialY * sinPitch;
/* 144 */     double y = initialX * sinPitch + initialY * cosPitch;
/*     */ 
/*     */     
/* 147 */     double initialZ = v.getZ();
/* 148 */     initialX = x;
/* 149 */     double z = initialZ * cosYaw - initialX * sinYaw;
/* 150 */     x = initialZ * sinYaw + initialX * cosYaw;
/*     */     
/* 152 */     return new Vector(x, y, z);
/*     */   }
/*     */   
/*     */   public static final double angleToXAxis(Vector vector) {
/* 156 */     return Math.atan2(vector.getX(), vector.getY());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\VectorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */