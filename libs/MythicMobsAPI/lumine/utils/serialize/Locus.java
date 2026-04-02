/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Locus
/*     */   implements GsonSerializable
/*     */ {
/*     */   private final double x;
/*     */   private final double y;
/*     */   private final double z;
/*     */   
/*     */   public static io.lumine.utils.serialize.Locus deserialize(JsonElement element) {
/*  27 */     Preconditions.checkArgument(element.isJsonObject());
/*  28 */     JsonObject object = element.getAsJsonObject();
/*     */     
/*  30 */     Preconditions.checkArgument(object.has("x"));
/*  31 */     Preconditions.checkArgument(object.has("y"));
/*  32 */     Preconditions.checkArgument(object.has("z"));
/*     */     
/*  34 */     double x = object.get("x").getAsDouble();
/*  35 */     double y = object.get("y").getAsDouble();
/*  36 */     double z = object.get("z").getAsDouble();
/*     */     
/*  38 */     return of(x, y, z);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Locus of(double x, double y, double z) {
/*  42 */     return new io.lumine.utils.serialize.Locus(x, y, z);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Locus of(Location location) {
/*  46 */     Objects.requireNonNull(location, "location");
/*  47 */     return of(location.getX(), location.getY(), location.getZ());
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Locus of(Block block) {
/*  51 */     Objects.requireNonNull(block, "block");
/*  52 */     return of(block.getLocation());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Locus(double x, double y, double z) {
/*  60 */     this.x = x;
/*  61 */     this.y = y;
/*  62 */     this.z = z;
/*     */   }
/*     */   
/*     */   public double getX() {
/*  66 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/*  70 */     return this.y;
/*     */   }
/*     */   
/*     */   public double getZ() {
/*  74 */     return this.z;
/*     */   }
/*     */   
/*     */   public Vector toVector() {
/*  78 */     return new Vector(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public synchronized Location toLocation(String world) {
/*  82 */     return toLocation(Bukkit.getWorld(world));
/*     */   }
/*     */   
/*     */   public synchronized Location toLocation(World world) {
/*  86 */     return new Location(world, this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Locus floor() {
/*  90 */     return of(bukkitFloor(this.x), bukkitFloor(this.y), bukkitFloor(this.z));
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Locus getRelative(BlockFace face) {
/*  94 */     Objects.requireNonNull(face, "face");
/*  95 */     return of(this.x + face.getModX(), this.y + face.getModY(), this.z + face.getModZ());
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Locus getRelative(BlockFace face, double distance) {
/*  99 */     Objects.requireNonNull(face, "face");
/* 100 */     return of(this.x + face.getModX() * distance, this.y + face.getModY() * distance, this.z + face.getModZ() * distance);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Locus add(Vector vector3i) {
/* 104 */     return add(vector3i.getX(), vector3i.getY(), vector3i.getZ());
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Locus add(double x, double y, double z) {
/* 108 */     return of(this.x + x, this.y + y, this.z + z);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Locus subtract(Vector vector3i) {
/* 112 */     return subtract(vector3i.getX(), vector3i.getY(), vector3i.getZ());
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Locus subtract(double x, double y, double z) {
/* 116 */     return add(-x, -y, -z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public JsonObject serialize() {
/* 126 */     return JsonBuilder.object().add("x", Double.valueOf(this.x)).add("y", Double.valueOf(this.y)).add("z", Double.valueOf(this.z)).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 131 */     if (o == this) return true; 
/* 132 */     if (!(o instanceof io.lumine.utils.serialize.Locus)) return false; 
/* 133 */     io.lumine.utils.serialize.Locus other = (io.lumine.utils.serialize.Locus)o;
/* 134 */     return (Double.compare(getX(), other.getX()) == 0 && 
/* 135 */       Double.compare(getY(), other.getY()) == 0 && 
/* 136 */       Double.compare(getZ(), other.getZ()) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 141 */     int PRIME = 59;
/* 142 */     int result = 1;
/*     */     
/* 144 */     long x = Double.doubleToLongBits(getX());
/* 145 */     long y = Double.doubleToLongBits(getY());
/* 146 */     long z = Double.doubleToLongBits(getZ());
/*     */     
/* 148 */     result = result * 59 + (int)(x >>> 32L ^ x);
/* 149 */     result = result * 59 + (int)(y >>> 32L ^ y);
/* 150 */     result = result * 59 + (int)(z >>> 32L ^ z);
/* 151 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 156 */     return "Locus(x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ")";
/*     */   }
/*     */   
/*     */   private static int bukkitFloor(double num) {
/* 160 */     int floor = (int)num;
/* 161 */     return (floor == num) ? floor : (floor - (int)(Double.doubleToRawLongBits(num) >>> 63L));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\Locus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */