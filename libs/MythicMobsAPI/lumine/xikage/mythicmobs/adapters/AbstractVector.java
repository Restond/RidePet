/*     */ package lumine.xikage.mythicmobs.adapters;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.util.NumberConversions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractVector
/*     */   implements Cloneable
/*     */ {
/*     */   private static final double epsilon = 1.0E-6D;
/*     */   protected double x;
/*     */   protected double y;
/*     */   protected double z;
/*     */   
/*     */   public AbstractVector(int x, int y, int z) {
/*  29 */     this.x = x;
/*  30 */     this.y = y;
/*  31 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractVector(double x, double y, double z) {
/*  42 */     this.x = x;
/*  43 */     this.y = y;
/*  44 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractVector(float x, float y, float z) {
/*  55 */     this.x = x;
/*  56 */     this.y = y;
/*  57 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractVector() {
/*  64 */     this.x = 0.0D;
/*  65 */     this.y = 0.0D;
/*  66 */     this.z = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector add(io.lumine.xikage.mythicmobs.adapters.AbstractVector vec) {
/*  76 */     this.x += vec.x;
/*  77 */     this.y += vec.y;
/*  78 */     this.z += vec.z;
/*  79 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector subtract(io.lumine.xikage.mythicmobs.adapters.AbstractVector vec) {
/*  89 */     this.x -= vec.x;
/*  90 */     this.y -= vec.y;
/*  91 */     this.z -= vec.z;
/*  92 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector multiply(io.lumine.xikage.mythicmobs.adapters.AbstractVector vec) {
/* 102 */     this.x *= vec.x;
/* 103 */     this.y *= vec.y;
/* 104 */     this.z *= vec.z;
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector multiply(int m) {
/* 116 */     this.x *= m;
/* 117 */     this.y *= m;
/* 118 */     this.z *= m;
/* 119 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector multiply(double m) {
/* 130 */     this.x *= m;
/* 131 */     this.y *= m;
/* 132 */     this.z *= m;
/* 133 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector multiply(float m) {
/* 144 */     this.x *= m;
/* 145 */     this.y *= m;
/* 146 */     this.z *= m;
/* 147 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector divide(io.lumine.xikage.mythicmobs.adapters.AbstractVector vec) {
/* 157 */     this.x /= vec.x;
/* 158 */     this.y /= vec.y;
/* 159 */     this.z /= vec.z;
/* 160 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector copy(io.lumine.xikage.mythicmobs.adapters.AbstractVector vec) {
/* 170 */     this.x = vec.x;
/* 171 */     this.y = vec.y;
/* 172 */     this.z = vec.z;
/* 173 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double length() {
/* 186 */     return Math.sqrt(NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double lengthSquared() {
/* 195 */     return NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getX() {
/* 206 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockX() {
/* 216 */     return NumberConversions.floor(this.x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getY() {
/* 225 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockY() {
/* 235 */     return NumberConversions.floor(this.y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZ() {
/* 244 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockZ() {
/* 254 */     return NumberConversions.floor(this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setX(int x) {
/* 264 */     this.x = x;
/* 265 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setX(double x) {
/* 275 */     this.x = x;
/* 276 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setX(float x) {
/* 286 */     this.x = x;
/* 287 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setY(int y) {
/* 297 */     this.y = y;
/* 298 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setY(double y) {
/* 308 */     this.y = y;
/* 309 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setY(float y) {
/* 319 */     this.y = y;
/* 320 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setZ(int z) {
/* 330 */     this.z = z;
/* 331 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setZ(double z) {
/* 341 */     this.z = z;
/* 342 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector setZ(float z) {
/* 352 */     this.z = z;
/* 353 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector normalize() {
/* 362 */     double length = length();
/*     */     
/* 364 */     this.x /= length;
/* 365 */     this.y /= length;
/* 366 */     this.z /= length;
/*     */     
/* 368 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector rotate(float degrees) {
/* 377 */     double rad = Math.toRadians(degrees);
/* 378 */     double sin = Math.sin(rad);
/* 379 */     double cos = Math.cos(rad);
/* 380 */     double x = getX() * cos - getZ() * sin;
/* 381 */     double z = getX() * sin + getZ() * cos;
/* 382 */     setX(x);
/* 383 */     setZ(z);
/*     */     
/* 385 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 397 */     if (!(obj instanceof io.lumine.xikage.mythicmobs.adapters.AbstractVector)) {
/* 398 */       return false;
/*     */     }
/*     */     
/* 401 */     io.lumine.xikage.mythicmobs.adapters.AbstractVector other = (io.lumine.xikage.mythicmobs.adapters.AbstractVector)obj;
/*     */     
/* 403 */     return (Math.abs(this.x - other.x) < 1.0E-6D && Math.abs(this.y - other.y) < 1.0E-6D && Math.abs(this.z - other.z) < 1.0E-6D && getClass().equals(obj.getClass()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 413 */     int hash = 7;
/*     */     
/* 415 */     hash = 79 * hash + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32L);
/* 416 */     hash = 79 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32L);
/* 417 */     hash = 79 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32L);
/* 418 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractVector clone() {
/*     */     try {
/* 429 */       return (io.lumine.xikage.mythicmobs.adapters.AbstractVector)super.clone();
/* 430 */     } catch (CloneNotSupportedException e) {
/* 431 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 440 */     return this.x + "," + this.y + "," + this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractLocation toLocation(AbstractWorld world) {
/* 450 */     return new AbstractLocation(world, this.x, this.y, this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractLocation toLocation(AbstractWorld world, float yaw, float pitch) {
/* 462 */     return new AbstractLocation(world, this.x, this.y, this.z, yaw, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getEpsilon() {
/* 471 */     return 1.0E-6D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double dot(io.lumine.xikage.mythicmobs.adapters.AbstractVector other) {
/* 482 */     return this.x * other.x + this.y * other.y + this.z * other.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float angle(io.lumine.xikage.mythicmobs.adapters.AbstractVector other) {
/* 492 */     double dot = dot(other) / length() * other.length();
/*     */     
/* 494 */     return (float)Math.acos(dot);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */