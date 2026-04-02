/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import io.lumine.utils.serialize.BlockPosition;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.block.Block;
/*     */ 
/*     */ public final class BlockRegion implements GsonSerializable {
/*     */   private final BlockPosition min;
/*     */   private final BlockPosition max;
/*     */   private final int width;
/*     */   private final int height;
/*     */   private final int depth;
/*     */   
/*     */   public static io.lumine.utils.serialize.BlockRegion deserialize(JsonElement element) {
/*  21 */     Preconditions.checkArgument(element.isJsonObject());
/*  22 */     JsonObject object = element.getAsJsonObject();
/*     */     
/*  24 */     Preconditions.checkArgument(object.has("min"));
/*  25 */     Preconditions.checkArgument(object.has("max"));
/*     */     
/*  27 */     BlockPosition a = BlockPosition.deserialize(object.get("min"));
/*  28 */     BlockPosition b = BlockPosition.deserialize(object.get("max"));
/*     */     
/*  30 */     return of(a, b);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.BlockRegion of(BlockPosition a, BlockPosition b) {
/*  34 */     Objects.requireNonNull(a, "a");
/*  35 */     Objects.requireNonNull(b, "b");
/*     */     
/*  37 */     if (!a.getWorld().equals(b.getWorld())) {
/*  38 */       throw new IllegalArgumentException("positions are in different worlds");
/*     */     }
/*     */     
/*  41 */     return new io.lumine.utils.serialize.BlockRegion(a, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BlockRegion(BlockPosition a, BlockPosition b) {
/*  52 */     this.min = BlockPosition.of(Math.min(a.getX(), b.getX()), Math.min(a.getY(), b.getY()), Math.min(a.getZ(), b.getZ()), a.getWorld());
/*  53 */     this.max = BlockPosition.of(Math.max(a.getX(), b.getX()), Math.max(a.getY(), b.getY()), Math.max(a.getZ(), b.getZ()), a.getWorld());
/*     */     
/*  55 */     this.width = this.max.getX() - this.min.getX();
/*  56 */     this.height = this.max.getY() - this.min.getY();
/*  57 */     this.depth = this.max.getZ() - this.min.getZ();
/*     */   }
/*     */   
/*     */   public boolean inRegion(BlockPosition pos) {
/*  61 */     Objects.requireNonNull(pos, "pos");
/*  62 */     return (pos.getWorld().equals(this.min.getWorld()) && inRegion(pos.getX(), pos.getY(), pos.getZ()));
/*     */   }
/*     */   
/*     */   public boolean inRegion(Block block) {
/*  66 */     Objects.requireNonNull(block, "block");
/*  67 */     return (block.getWorld().getName().equals(this.min.getWorld()) && inRegion(block.getX(), block.getY(), block.getZ()));
/*     */   }
/*     */   
/*     */   public boolean inRegion(int x, int y, int z) {
/*  71 */     return (x >= this.min.getX() && x <= this.max.getX() && y >= this.min
/*  72 */       .getY() && y <= this.max.getY() && z >= this.min
/*  73 */       .getZ() && z <= this.max.getZ());
/*     */   }
/*     */   
/*     */   public BlockPosition getMin() {
/*  77 */     return this.min;
/*     */   }
/*     */   
/*     */   public BlockPosition getMax() {
/*  81 */     return this.max;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/*  85 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  89 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getDepth() {
/*  93 */     return this.depth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public JsonObject serialize() {
/* 102 */     return JsonBuilder.object().add("min", (GsonSerializable)this.min).add("max", (GsonSerializable)this.max).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 107 */     if (o == this) return true; 
/* 108 */     if (!(o instanceof io.lumine.utils.serialize.BlockRegion)) return false; 
/* 109 */     io.lumine.utils.serialize.BlockRegion other = (io.lumine.utils.serialize.BlockRegion)o;
/* 110 */     return (getMin().equals(other.getMin()) && getMax().equals(other.getMax()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 115 */     int PRIME = 59;
/* 116 */     int result = 1;
/* 117 */     result = result * 59 + getMin().hashCode();
/* 118 */     result = result * 59 + getMax().hashCode();
/* 119 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 124 */     return "BlockRegion(min=" + getMin() + ", max=" + getMax() + ")";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\BlockRegion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */