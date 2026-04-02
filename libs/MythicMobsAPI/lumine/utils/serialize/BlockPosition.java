/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import io.lumine.utils.serialize.BlockRegion;
/*     */ import io.lumine.utils.serialize.ChunkPosition;
/*     */ import io.lumine.utils.serialize.Position;
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
/*     */ public final class BlockPosition
/*     */   implements GsonSerializable {
/*     */   private final int x;
/*     */   private final int y;
/*     */   
/*     */   public static io.lumine.utils.serialize.BlockPosition deserialize(JsonElement element) {
/*  27 */     Preconditions.checkArgument(element.isJsonObject());
/*  28 */     JsonObject object = element.getAsJsonObject();
/*     */     
/*  30 */     Preconditions.checkArgument(object.has("x"));
/*  31 */     Preconditions.checkArgument(object.has("y"));
/*  32 */     Preconditions.checkArgument(object.has("z"));
/*  33 */     Preconditions.checkArgument(object.has("world"));
/*     */     
/*  35 */     int x = object.get("x").getAsInt();
/*  36 */     int y = object.get("y").getAsInt();
/*  37 */     int z = object.get("z").getAsInt();
/*  38 */     String world = object.get("world").getAsString();
/*     */     
/*  40 */     return of(x, y, z, world);
/*     */   }
/*     */   private final int z; private final String world;
/*     */   public static io.lumine.utils.serialize.BlockPosition of(int x, int y, int z, String world) {
/*  44 */     Objects.requireNonNull(world, "world");
/*  45 */     return new io.lumine.utils.serialize.BlockPosition(x, y, z, world);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.BlockPosition of(int x, int y, int z, World world) {
/*  49 */     Objects.requireNonNull(world, "world");
/*  50 */     return of(x, y, z, world.getName());
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.BlockPosition of(Location location) {
/*  54 */     Objects.requireNonNull(location, "location");
/*  55 */     return of(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld().getName());
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.BlockPosition of(Block block) {
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
/*     */   private BlockPosition(int x, int y, int z, String world) {
/*  72 */     this.x = x;
/*  73 */     this.y = y;
/*  74 */     this.z = z;
/*  75 */     this.world = world;
/*     */   }
/*     */   
/*     */   public int getX() {
/*  79 */     return this.x;
/*     */   }
/*     */   
/*     */   public int getY() {
/*  83 */     return this.y;
/*     */   }
/*     */   
/*     */   public int getZ() {
/*  87 */     return this.z;
/*     */   }
/*     */   
/*     */   public String getWorld() {
/*  91 */     return this.world;
/*     */   }
/*     */   
/*     */   public synchronized Location toLocation() {
/*  95 */     if (this.bukkitLocation == null) {
/*  96 */       this.bukkitLocation = new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
/*     */     }
/*     */     
/*  99 */     return this.bukkitLocation.clone();
/*     */   }
/*     */   
/*     */   public Vector toVector() {
/* 103 */     return new Vector(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public Block toBlock() {
/* 107 */     return toLocation().getBlock();
/*     */   }
/*     */   
/*     */   public Position toPosition() {
/* 111 */     return Position.of(this.x, this.y, this.z, this.world);
/*     */   }
/*     */   
/*     */   public Position toPositionCenter() {
/* 115 */     return Position.of(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D, this.world);
/*     */   }
/*     */   
/*     */   public ChunkPosition toChunk() {
/* 119 */     return ChunkPosition.of(this.x >> 4, this.z >> 4, this.world);
/*     */   }
/*     */   
/*     */   public boolean contains(Position position) {
/* 123 */     return equals(position.floor());
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.BlockPosition getRelative(BlockFace face) {
/* 127 */     Objects.requireNonNull(face, "face");
/* 128 */     return of(this.x + face.getModX(), this.y + face.getModY(), this.z + face.getModZ(), this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.BlockPosition getRelative(BlockFace face, int distance) {
/* 132 */     Objects.requireNonNull(face, "face");
/* 133 */     return of(this.x + face.getModX() * distance, this.y + face.getModY() * distance, this.z + face.getModZ() * distance, this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.BlockPosition add(Vector vector3i) {
/* 137 */     return add((int)vector3i.getX(), (int)vector3i.getY(), (int)vector3i.getZ());
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.BlockPosition add(int x, int y, int z) {
/* 141 */     return of(this.x + x, this.y + y, this.z + z, this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.BlockPosition subtract(int x, int y, int z) {
/* 145 */     return add(-x, -y, -z);
/*     */   }
/*     */   
/*     */   public BlockRegion regionWith(io.lumine.utils.serialize.BlockPosition other) {
/* 149 */     Objects.requireNonNull(other, "other");
/* 150 */     return BlockRegion.of(this, other);
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
/* 161 */     return JsonBuilder.object().add("x", Integer.valueOf(this.x)).add("y", Integer.valueOf(this.y)).add("z", Integer.valueOf(this.z)).add("world", this.world).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 166 */     if (o == this) return true; 
/* 167 */     if (!(o instanceof io.lumine.utils.serialize.BlockPosition)) return false; 
/* 168 */     io.lumine.utils.serialize.BlockPosition other = (io.lumine.utils.serialize.BlockPosition)o;
/* 169 */     return (getX() == other.getX() && getY() == other.getY() && getZ() == other.getZ() && getWorld().equals(other.getWorld()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 174 */     int PRIME = 59;
/* 175 */     int result = 1;
/* 176 */     result = result * 59 + getX();
/* 177 */     result = result * 59 + getY();
/* 178 */     result = result * 59 + getZ();
/* 179 */     result = result * 59 + getWorld().hashCode();
/* 180 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 185 */     return "BlockPosition(x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ", world=" + getWorld() + ")";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\BlockPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */