/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import io.lumine.utils.serialize.Direction;
/*     */ import io.lumine.utils.serialize.Position;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ 
/*     */ public final class Point
/*     */   implements GsonSerializable
/*     */ {
/*     */   private final Position position;
/*     */   private final Direction direction;
/*     */   
/*     */   public static io.lumine.utils.serialize.Point deserialize(JsonElement element) {
/*  22 */     Position position = Position.deserialize(element);
/*  23 */     Direction direction = Direction.deserialize(element);
/*     */     
/*  25 */     return of(position, direction);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Point of(Position position, Direction direction) {
/*  29 */     Objects.requireNonNull(position, "position");
/*  30 */     Objects.requireNonNull(direction, "direction");
/*  31 */     return new io.lumine.utils.serialize.Point(position, direction);
/*     */   }
/*     */   
/*     */   public static io.lumine.utils.serialize.Point of(Location location) {
/*  35 */     Objects.requireNonNull(location, "location");
/*  36 */     return of(Position.of(location), Direction.from(location));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*  42 */   private Location bukkitLocation = null;
/*     */ 
/*     */   
/*     */   private Point(Position position, Direction direction) {
/*  46 */     this.position = position;
/*  47 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public Position getPosition() {
/*  51 */     return this.position;
/*     */   }
/*     */   
/*     */   public Direction getDirection() {
/*  55 */     return this.direction;
/*     */   }
/*     */   
/*     */   public synchronized Location toLocation() {
/*  59 */     if (this.bukkitLocation == null) {
/*  60 */       this.bukkitLocation = new Location(Bukkit.getWorld(this.position.getWorld()), this.position.getX(), this.position.getY(), this.position.getZ(), this.direction.getYaw(), this.direction.getPitch());
/*     */     }
/*     */     
/*  63 */     return this.bukkitLocation.clone();
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Point add(double x, double y, double z) {
/*  67 */     return this.position.add(x, y, z).withDirection(this.direction);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.serialize.Point subtract(double x, double y, double z) {
/*  71 */     return this.position.subtract(x, y, z).withDirection(this.direction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public JsonObject serialize() {
/*  80 */     return JsonBuilder.object().addAll(this.position.serialize()).addAll(this.direction.serialize()).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  85 */     if (o == this) return true; 
/*  86 */     if (!(o instanceof io.lumine.utils.serialize.Point)) return false; 
/*  87 */     io.lumine.utils.serialize.Point other = (io.lumine.utils.serialize.Point)o;
/*  88 */     return (getPosition().equals(other.getPosition()) && getDirection().equals(other.getDirection()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  93 */     int PRIME = 59;
/*  94 */     int result = 1;
/*     */     
/*  96 */     result = result * 59 + getPosition().hashCode();
/*  97 */     result = result * 59 + getDirection().hashCode();
/*     */     
/*  99 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     return "Point(position=" + getPosition() + ", direction=" + getDirection() + ")";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\Point.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */