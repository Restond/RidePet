/*     */ package lumine.xikage.mythicmobs.adapters;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractBiome;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ 
/*     */ public class AbstractLocation implements Cloneable {
/*     */   private AbstractWorld world;
/*     */   private double x;
/*     */   private double y;
/*     */   
/*     */   public AbstractLocation(AbstractWorld world, double x, double y, double z) {
/*  12 */     this.world = world;
/*  13 */     this.x = x;
/*  14 */     this.y = y;
/*  15 */     this.z = z;
/*     */   } private double z; private float yaw; private float pitch;
/*     */   public AbstractLocation() {}
/*     */   public AbstractLocation(AbstractWorld world, double x, double y, double z, float yaw, float pitch) {
/*  19 */     this.world = world;
/*  20 */     this.x = x;
/*  21 */     this.y = y;
/*  22 */     this.z = z;
/*  23 */     this.yaw = yaw;
/*  24 */     this.pitch = pitch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setX(double x) {
/*  33 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getX() {
/*  41 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setY(double y) {
/*  50 */     this.y = y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getY() {
/*  59 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZ(double z) {
/*  68 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZ() {
/*  77 */     return this.z;
/*     */   }
/*     */   public int getBlockX() {
/*  80 */     return (int)this.x;
/*     */   }
/*     */   public int getBlockY() {
/*  83 */     return (int)this.y;
/*     */   }
/*     */   public int getBlockZ() {
/*  86 */     return (int)this.z;
/*     */   }
/*     */   public int getChunkX() {
/*  89 */     return getBlockX() / 16;
/*     */   }
/*     */   public int getChunkZ() {
/*  92 */     return getBlockZ() / 16;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getYaw() {
/* 110 */     return this.yaw;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYaw(float yaw) {
/* 128 */     this.yaw = yaw;
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
/*     */ 
/*     */   
/*     */   public float getPitch() {
/* 143 */     return this.pitch;
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
/*     */ 
/*     */   
/*     */   public void setPitch(float pitch) {
/* 158 */     this.pitch = pitch;
/*     */   }
/*     */   public AbstractWorld getWorld() {
/* 161 */     return this.world;
/*     */   }
/*     */   
/*     */   public double distance(io.lumine.xikage.mythicmobs.adapters.AbstractLocation t) throws IllegalArgumentException {
/* 165 */     return Math.sqrt(distanceSquared(t));
/*     */   }
/*     */   public double distanceSquared(io.lumine.xikage.mythicmobs.adapters.AbstractLocation t) throws IllegalArgumentException {
/* 168 */     if (!t.getWorld().equals(this.world)) throw new IllegalArgumentException("Cannot measure distance between two different worlds.");
/*     */ 
/*     */     
/* 171 */     return Math.pow(t.getX() - getX(), 2.0D) + Math.pow(t.getY() - getY(), 2.0D) + Math.pow(t.getZ() - getZ(), 2.0D);
/*     */   }
/*     */   public double distance2D(io.lumine.xikage.mythicmobs.adapters.AbstractLocation t) {
/* 174 */     return Math.sqrt(distance2DSquared(t));
/*     */   }
/*     */   public double distance2DSquared(io.lumine.xikage.mythicmobs.adapters.AbstractLocation t) {
/* 177 */     if (!t.getWorld().equals(this.world)) throw new IllegalArgumentException("Cannot measure distance between two different worlds.");
/*     */ 
/*     */     
/* 180 */     return Math.pow(t.getX() - getX(), 2.0D) + Math.pow(t.getZ() - getZ(), 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation add(double xA, double yA, double zA) {
/* 186 */     this.x += xA;
/* 187 */     this.y += yA;
/* 188 */     this.z += zA;
/* 189 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractVector getDirection() {
/* 200 */     AbstractVector vector = new AbstractVector();
/*     */     
/* 202 */     double rotX = getYaw();
/* 203 */     double rotY = getPitch();
/*     */     
/* 205 */     vector.setY(-Math.sin(Math.toRadians(rotY)));
/*     */     
/* 207 */     double xz = Math.cos(Math.toRadians(rotY));
/*     */     
/* 209 */     vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
/* 210 */     vector.setZ(xz * Math.cos(Math.toRadians(rotX)));
/*     */     
/* 212 */     return vector;
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
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation add(io.lumine.xikage.mythicmobs.adapters.AbstractLocation vec) {
/* 224 */     if (vec == null || !vec.getWorld().equals(getWorld())) {
/* 225 */       throw new IllegalArgumentException("Cannot add Locations of differing worlds");
/*     */     }
/*     */     
/* 228 */     this.x += vec.x;
/* 229 */     this.y += vec.y;
/* 230 */     this.z += vec.z;
/* 231 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation add(AbstractVector vec) {
/* 242 */     this.x += vec.getX();
/* 243 */     this.y += vec.getY();
/* 244 */     this.z += vec.getZ();
/* 245 */     return this;
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
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation subtract(double x, double y, double z) {
/* 259 */     this.x -= x;
/* 260 */     this.y -= y;
/* 261 */     this.z -= z;
/* 262 */     return this;
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
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation subtract(io.lumine.xikage.mythicmobs.adapters.AbstractLocation vec) {
/* 274 */     if (vec == null || !vec.getWorld().equals(getWorld())) {
/* 275 */       throw new IllegalArgumentException("Cannot add Locations of differing worlds");
/*     */     }
/*     */     
/* 278 */     this.x -= vec.x;
/* 279 */     this.y -= vec.y;
/* 280 */     this.z -= vec.z;
/* 281 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation subtract(AbstractVector vec) {
/* 292 */     this.x -= vec.getX();
/* 293 */     this.y -= vec.getY();
/* 294 */     this.z -= vec.getZ();
/* 295 */     return this;
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
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation multiply(double m) {
/* 307 */     this.x *= m;
/* 308 */     this.y *= m;
/* 309 */     this.z *= m;
/* 310 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation zero() {
/* 320 */     this.x = 0.0D;
/* 321 */     this.y = 0.0D;
/* 322 */     this.z = 0.0D;
/* 323 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractVector toVector() {
/* 333 */     return new AbstractVector(this.x, this.y, this.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 338 */     if (obj == null) {
/* 339 */       return false;
/*     */     }
/* 341 */     if (getClass() != obj.getClass()) {
/* 342 */       return false;
/*     */     }
/* 344 */     io.lumine.xikage.mythicmobs.adapters.AbstractLocation other = (io.lumine.xikage.mythicmobs.adapters.AbstractLocation)obj;
/*     */     
/* 346 */     if (this.world != other.world && (this.world == null || !this.world.equals(other.world))) {
/* 347 */       return false;
/*     */     }
/* 349 */     if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
/* 350 */       return false;
/*     */     }
/* 352 */     if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
/* 353 */       return false;
/*     */     }
/* 355 */     if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
/* 356 */       return false;
/*     */     }
/* 358 */     if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch)) {
/* 359 */       return false;
/*     */     }
/* 361 */     if (Float.floatToIntBits(this.yaw) != Float.floatToIntBits(other.yaw)) {
/* 362 */       return false;
/*     */     }
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 369 */     return "Location{world=" + this.world + ",x=" + this.x + ",y=" + this.y + ",z=" + this.z + ",pitch=" + this.pitch + ",yaw=" + this.yaw + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 374 */     int hash = 3;
/*     */     
/* 376 */     hash = 19 * hash + ((this.world != null) ? this.world.hashCode() : 0);
/* 377 */     hash = 19 * hash + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32L);
/* 378 */     hash = 19 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32L);
/* 379 */     hash = 19 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32L);
/* 380 */     hash = 19 * hash + Float.floatToIntBits(this.pitch);
/* 381 */     hash = 19 * hash + Float.floatToIntBits(this.yaw);
/* 382 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.AbstractLocation clone() {
/*     */     try {
/* 388 */       return (io.lumine.xikage.mythicmobs.adapters.AbstractLocation)super.clone();
/* 389 */     } catch (CloneNotSupportedException e) {
/* 390 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isLoaded() {
/* 395 */     return getWorld().isLocationLoaded(this);
/*     */   }
/*     */   
/*     */   public AbstractBiome getBiome() {
/* 399 */     return getWorld().getLocationBiome(this);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */