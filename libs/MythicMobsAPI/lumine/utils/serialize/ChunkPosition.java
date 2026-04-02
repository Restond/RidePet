/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.cache.Lazy;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import io.lumine.utils.serialize.BlockPosition;
/*     */ import io.lumine.utils.serialize.Position;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ public final class ChunkPosition
/*     */   implements GsonSerializable {
/*     */   private final int x;
/*     */   private final int z;
/*     */   
/*     */   public static io.lumine.utils.serialize.ChunkPosition deserialize(JsonElement element) {
/*  29 */     Preconditions.checkArgument(element.isJsonObject());
/*  30 */     JsonObject object = element.getAsJsonObject();
/*     */     
/*  32 */     Preconditions.checkArgument(object.has("x"));
/*  33 */     Preconditions.checkArgument(object.has("z"));
/*  34 */     Preconditions.checkArgument(object.has("world"));
/*     */     
/*  36 */     int x = object.get("x").getAsInt();
/*  37 */     int z = object.get("z").getAsInt();
/*  38 */     String world = object.get("world").getAsString();
/*     */     
/*  40 */     return of(x, z, world);
/*     */   }
/*     */   private final String world; private final Lazy<Collection<BlockPosition>> blocks;
/*     */   public static io.lumine.utils.serialize.ChunkPosition of(int x, int z, String world) {
/*  44 */     Objects.requireNonNull(world, "world");
/*  45 */     return new io.lumine.utils.serialize.ChunkPosition(x, z, world);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.ChunkPosition of(int x, int z, World world) {
/*  49 */     Objects.requireNonNull(world, "world");
/*  50 */     return of(x, z, world.getName());
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.ChunkPosition of(Chunk location) {
/*  54 */     Objects.requireNonNull(location, "location");
/*  55 */     return of(location.getX(), location.getZ(), location.getWorld().getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ChunkPosition(int x, int z, String world) {
/*  62 */     this.blocks = Lazy.suppliedBy(() -> {
/*     */           List<BlockPosition> blocks = new ArrayList<>(65536);
/*     */           
/*     */           for (int x = 0; x < 16; x++) {
/*     */             for (int z = 0; z < 16; z++) {
/*     */               for (int y = 0; y < 256; y++) {
/*     */                 blocks.add(getBlock(x, y, z));
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/*     */           return (Supplier)Collections.unmodifiableList(blocks);
/*     */         });
/*  75 */     this.x = x;
/*  76 */     this.z = z;
/*  77 */     this.world = world;
/*     */   }
/*     */   
/*     */   public int getX() {
/*  81 */     return this.x;
/*     */   }
/*     */   
/*     */   public int getZ() {
/*  85 */     return this.z;
/*     */   }
/*     */   
/*     */   public String getWorld() {
/*  89 */     return this.world;
/*     */   }
/*     */   
/*     */   public Chunk toChunk() {
/*  93 */     return Bukkit.getWorld(this.world).getChunkAt(this.x, this.z);
/*     */   }
/*     */   
/*     */   public boolean contains(BlockPosition block) {
/*  97 */     return equals(block.toChunk());
/*     */   }
/*     */   
/*     */   public boolean contains(Position position) {
/* 101 */     return equals(position.floor().toChunk());
/*     */   }
/*     */   
/*     */   public BlockPosition getBlock(int x, int y, int z) {
/* 105 */     return BlockPosition.of(this.x << 4 | x & 0xF, y, this.z << 4 | z & 0xF, this.world);
/*     */   }
/*     */   
/*     */   public Collection<BlockPosition> blocks() {
/* 109 */     return (Collection<BlockPosition>)this.blocks.get();
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.ChunkPosition getRelative(BlockFace face) {
/* 113 */     Preconditions.checkArgument((face != BlockFace.UP && face != BlockFace.DOWN), "invalid face");
/* 114 */     return of(this.x + face.getModX(), this.z + face.getModZ(), this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.ChunkPosition getRelative(BlockFace face, int distance) {
/* 118 */     Preconditions.checkArgument((face != BlockFace.UP && face != BlockFace.DOWN), "invalid face");
/* 119 */     return of(this.x + face.getModX() * distance, this.z + face.getModZ() * distance, this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.ChunkPosition add(int x, int z) {
/* 123 */     return of(this.x + x, this.z + z, this.world);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.ChunkPosition subtract(int x, int z) {
/* 127 */     return add(-x, -z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public JsonObject serialize() {
/* 137 */     return JsonBuilder.object().add("x", Integer.valueOf(this.x)).add("z", Integer.valueOf(this.z)).add("world", this.world).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 142 */     if (o == this) return true; 
/* 143 */     if (!(o instanceof io.lumine.utils.serialize.ChunkPosition)) return false; 
/* 144 */     io.lumine.utils.serialize.ChunkPosition other = (io.lumine.utils.serialize.ChunkPosition)o;
/* 145 */     return (getX() == other.getX() && getZ() == other.getZ() && getWorld().equals(other.getWorld()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 150 */     int PRIME = 59;
/* 151 */     int result = 1;
/* 152 */     result = result * 59 + getX();
/* 153 */     result = result * 59 + getZ();
/* 154 */     result = result * 59 + getWorld().hashCode();
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     return "ChunkPosition(x=" + getX() + ", z=" + getZ() + ", world=" + getWorld() + ")";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\ChunkPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */