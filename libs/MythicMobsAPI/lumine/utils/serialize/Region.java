/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import io.lumine.utils.serialize.Position;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.Location;
/*     */ 
/*     */ public final class Region implements GsonSerializable {
/*     */   private final Position min;
/*     */   private final Position max;
/*     */   private final double width;
/*     */   private final double height;
/*     */   private final double depth;
/*     */   
/*     */   public static io.lumine.utils.serialize.Region deserialize(JsonElement element) {
/*  21 */     Preconditions.checkArgument(element.isJsonObject());
/*  22 */     JsonObject object = element.getAsJsonObject();
/*     */     
/*  24 */     Preconditions.checkArgument(object.has("min"));
/*  25 */     Preconditions.checkArgument(object.has("max"));
/*     */     
/*  27 */     Position a = Position.deserialize(object.get("min"));
/*  28 */     Position b = Position.deserialize(object.get("max"));
/*     */     
/*  30 */     return of(a, b);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Region of(Position a, Position b) {
/*  34 */     Objects.requireNonNull(a, "a");
/*  35 */     Objects.requireNonNull(b, "b");
/*     */     
/*  37 */     if (!a.getWorld().equals(b.getWorld())) {
/*  38 */       throw new IllegalArgumentException("positions are in different worlds");
/*     */     }
/*     */     
/*  41 */     return new io.lumine.utils.serialize.Region(a, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Region(Position a, Position b) {
/*  52 */     this.min = Position.of(Math.min(a.getX(), b.getX()), Math.min(a.getY(), b.getY()), Math.min(a.getZ(), b.getZ()), a.getWorld());
/*  53 */     this.max = Position.of(Math.max(a.getX(), b.getX()), Math.max(a.getY(), b.getY()), Math.max(a.getZ(), b.getZ()), a.getWorld());
/*     */     
/*  55 */     this.width = this.max.getX() - this.min.getX();
/*  56 */     this.height = this.max.getY() - this.min.getY();
/*  57 */     this.depth = this.max.getZ() - this.min.getZ();
/*     */   }
/*     */   
/*     */   public boolean inRegion(Position pos) {
/*  61 */     Objects.requireNonNull(pos, "pos");
/*  62 */     return (pos.getWorld().equals(this.min.getWorld()) && inRegion(pos.getX(), pos.getY(), pos.getZ()));
/*     */   }
/*     */   
/*     */   public boolean inRegion(Location loc) {
/*  66 */     Objects.requireNonNull(loc, "loc");
/*  67 */     return (loc.getWorld().getName().equals(this.min.getWorld()) && inRegion(loc.getX(), loc.getY(), loc.getZ()));
/*     */   }
/*     */   
/*     */   public boolean inRegion(double x, double y, double z) {
/*  71 */     return (x >= this.min.getX() && x <= this.max.getX() && y >= this.min
/*  72 */       .getY() && y <= this.max.getY() && z >= this.min
/*  73 */       .getZ() && z <= this.max.getZ());
/*     */   }
/*     */   
/*     */   public Position getMin() {
/*  77 */     return this.min;
/*     */   }
/*     */   
/*     */   public Position getMax() {
/*  81 */     return this.max;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/*  85 */     return this.width;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/*  89 */     return this.height;
/*     */   }
/*     */   
/*     */   public double getDepth() {
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
/* 108 */     if (!(o instanceof io.lumine.utils.serialize.Region)) return false; 
/* 109 */     io.lumine.utils.serialize.Region other = (io.lumine.utils.serialize.Region)o;
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
/* 124 */     return "Region(min=" + getMin() + ", max=" + getMax() + ")";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\Region.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */