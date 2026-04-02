/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import io.lumine.utils.serialize.BlockPosition;
/*     */ import io.lumine.utils.serialize.Direction;
/*     */ import io.lumine.utils.serialize.Point;
/*     */ import io.lumine.utils.serialize.Region;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public final class Position implements GsonSerializable {
/*     */   private final double x;
/*     */   private final double y;
/*     */   
/*     */   public static io.lumine.utils.serialize.Position deserialize(JsonElement element) {
/*  27 */     Preconditions.checkArgument(element.isJsonObject());
/*  28 */     JsonObject object = element.getAsJsonObject();
/*     */     
/*  30 */     Preconditions.checkArgument(object.has("x"));
/*  31 */     Preconditions.checkArgument(object.has("y"));
/*  32 */     Preconditions.checkArgument(object.has("z"));
/*  33 */     Preconditions.checkArgument(object.has("world"));
/*     */     
/*  35 */     double x = object.get("x").getAsDouble();
/*  36 */     double y = object.get("y").getAsDouble();
/*  37 */     double z = object.get("z").getAsDouble();
/*  38 */     String world = object.get("world").getAsString();
/*     */     
/*  40 */     return of(x, y, z, world);
/*     */   }
/*     */   private final double z; private final String world;
/*     */   public static io.lumine.utils.serialize.Position of(double x, double y, double z, String world) {
/*  44 */     Objects.requireNonNull(world, "world");
/*  45 */     return new io.lumine.utils.serialize.Position(x, y, z, world);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Position of(double x, double y, double z, World world) {
/*  49 */     Objects.requireNonNull(world, "world");
/*  50 */     return of(x, y, z, world.getName());
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Position of(Location location) {
/*  54 */     Objects.requireNonNull(location, "location");
/*  55 */     return of(location.getX(), location.getY(), location.getZ(), location.getWorld().getName());
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Position of(Block block) {
/*  59 */     Objects.requireNonNull(block, "block");
/*  60 */     return of(block.getLocation());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*  68 */   private Location bukkitLocation = null;
/*     */ 
/*     */   
/*     */   private Position(double x, double y, double z, String world) {
/*  72 */     this.x = x;
/*  73 */     this.y = y;
/*  74 */     this.z = z;
/*  75 */     this.world = world;
/*     */   }
/*     */   
/*     */   public double getX() {
/*  79 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/*  83 */     return this.y;
/*     */   }
/*     */   
/*     */   public double getZ() {
/*  87 */     return this.z;
/*     */   }
/*     */   
/*     */   public String getWorld() {
/*  91 */     return this.world;
/*     */   }
/*     */   
/*     */   public Vector toVector() {
/*  95 */     return new Vector(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public synchronized Location toLocation() {
/*  99 */     if (this.bukkitLocation == null) {
/* 100 */       this.bukkitLocation = new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
/*     */     }
/* 102 */     return this.bukkitLocation.clone();
/*     */   }
/*     */   
/*     */   public BlockPosition floor() {
/* 106 */     return BlockPosition.of(bukkitFloor(this.x), bukkitFloor(this.y), bukkitFloor(this.z), this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Position getRelative(BlockFace face) {
/* 110 */     Objects.requireNonNull(face, "face");
/* 111 */     return of(this.x + face.getModX(), this.y + face.getModY(), this.z + face.getModZ(), this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Position getRelative(BlockFace face, double distance) {
/* 115 */     Objects.requireNonNull(face, "face");
/* 116 */     return of(this.x + face.getModX() * distance, this.y + face.getModY() * distance, this.z + face.getModZ() * distance, this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Position add(Vector vector3i) {
/* 120 */     return add(vector3i.getX(), vector3i.getY(), vector3i.getZ());
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Position add(double x, double y, double z) {
/* 124 */     return of(this.x + x, this.y + y, this.z + z, this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Position subtract(Vector vector3i) {
/* 128 */     return subtract(vector3i.getX(), vector3i.getY(), vector3i.getZ());
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Position subtract(double x, double y, double z) {
/* 132 */     return add(-x, -y, -z);
/*     */   }
/*     */   
/*     */   public Region regionWith(io.lumine.utils.serialize.Position other) {
/* 136 */     Objects.requireNonNull(other, "other");
/* 137 */     return Region.of(this, other);
/*     */   }
/*     */   
/*     */   public Point withDirection(Direction direction) {
/* 141 */     return Point.of(this, direction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public JsonObject serialize() {
/* 152 */     return JsonBuilder.object().add("x", Double.valueOf(this.x)).add("y", Double.valueOf(this.y)).add("z", Double.valueOf(this.z)).add("world", this.world).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 157 */     if (o == this) return true; 
/* 158 */     if (!(o instanceof io.lumine.utils.serialize.Position)) return false; 
/* 159 */     io.lumine.utils.serialize.Position other = (io.lumine.utils.serialize.Position)o;
/* 160 */     return (Double.compare(getX(), other.getX()) == 0 && 
/* 161 */       Double.compare(getY(), other.getY()) == 0 && 
/* 162 */       Double.compare(getZ(), other.getZ()) == 0 && 
/* 163 */       getWorld().equals(other.getWorld()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 168 */     int PRIME = 59;
/* 169 */     int result = 1;
/*     */     
/* 171 */     long x = Double.doubleToLongBits(getX());
/* 172 */     long y = Double.doubleToLongBits(getY());
/* 173 */     long z = Double.doubleToLongBits(getZ());
/*     */     
/* 175 */     result = result * 59 + (int)(x >>> 32L ^ x);
/* 176 */     result = result * 59 + (int)(y >>> 32L ^ y);
/* 177 */     result = result * 59 + (int)(z >>> 32L ^ z);
/* 178 */     result = result * 59 + getWorld().hashCode();
/* 179 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 184 */     return "Position(x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ", world=" + getWorld() + ")";
/*     */   }
/*     */   
/*     */   private static int bukkitFloor(double num) {
/* 188 */     int floor = (int)num;
/* 189 */     return (floor == num) ? floor : (floor - (int)(Double.doubleToRawLongBits(num) >>> 63L));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\Position.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */