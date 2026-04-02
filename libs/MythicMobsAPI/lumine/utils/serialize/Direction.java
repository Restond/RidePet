/*    */ package lumine.utils.serialize;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import io.lumine.utils.gson.GsonSerializable;
/*    */ import io.lumine.utils.gson.JsonBuilder;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nonnull;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Direction
/*    */   implements GsonSerializable
/*    */ {
/* 20 */   public static final io.lumine.utils.serialize.Direction ZERO = of(0.0F, 0.0F); private final float yaw;
/*    */   
/*    */   public static io.lumine.utils.serialize.Direction deserialize(JsonElement element) {
/* 23 */     Preconditions.checkArgument(element.isJsonObject());
/* 24 */     JsonObject object = element.getAsJsonObject();
/*    */     
/* 26 */     Preconditions.checkArgument(object.has("yaw"));
/* 27 */     Preconditions.checkArgument(object.has("pitch"));
/*    */     
/* 29 */     float yaw = object.get("yaw").getAsFloat();
/* 30 */     float pitch = object.get("pitch").getAsFloat();
/*    */     
/* 32 */     return of(yaw, pitch);
/*    */   }
/*    */   private final float pitch;
/*    */   public static io.lumine.utils.serialize.Direction of(float yaw, float pitch) {
/* 36 */     return new io.lumine.utils.serialize.Direction(yaw, pitch);
/*    */   }
/*    */   
/*    */   public static io.lumine.utils.serialize.Direction from(Location location) {
/* 40 */     Objects.requireNonNull(location, "location");
/* 41 */     return of(location.getYaw(), location.getPitch());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Direction(float yaw, float pitch) {
/* 48 */     this.yaw = yaw;
/* 49 */     this.pitch = pitch;
/*    */   }
/*    */   
/*    */   public float getYaw() {
/* 53 */     return this.yaw;
/*    */   }
/*    */   
/*    */   public float getPitch() {
/* 57 */     return this.pitch;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public JsonObject serialize() {
/* 66 */     return JsonBuilder.object().add("yaw", Float.valueOf(this.yaw)).add("pitch", Float.valueOf(this.pitch)).build();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 71 */     if (o == this) return true; 
/* 72 */     if (!(o instanceof io.lumine.utils.serialize.Direction)) return false; 
/* 73 */     io.lumine.utils.serialize.Direction other = (io.lumine.utils.serialize.Direction)o;
/* 74 */     return (Float.compare(getYaw(), other.getYaw()) == 0 && 
/* 75 */       Float.compare(getPitch(), other.getPitch()) == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 80 */     int PRIME = 59;
/* 81 */     int result = 1;
/*    */     
/* 83 */     result = result * 59 + Float.floatToIntBits(getYaw());
/* 84 */     result = result * 59 + Float.floatToIntBits(getPitch());
/*    */     
/* 86 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 91 */     return "Direction(yaw=" + getYaw() + ", pitch=" + getPitch() + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\Direction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */