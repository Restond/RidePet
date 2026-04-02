/*    */ package lumine.xikage.mythicmobs.util.jnbt;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.NBTUtils;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class NBTSerializer
/*    */   implements JsonSerializer<Tag>, JsonDeserializer<Tag> {
/*    */   public Tag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 17 */     JsonObject obj = json.getAsJsonObject();
/* 18 */     int type = obj.get("type").getAsInt();
/*    */     
/* 20 */     Class<? extends Tag> clazz = NBTUtils.getTypeClass(type);
/* 21 */     return (Tag)context.deserialize(json, clazz);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Tag src, Type typeOfSrc, JsonSerializationContext context) {
/* 26 */     JsonElement element = context.serialize(src);
/* 27 */     int typeCode = NBTUtils.getTypeCode(src.getClass());
/* 28 */     element.getAsJsonObject().addProperty("type", Integer.valueOf(typeCode));
/* 29 */     return element;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\NBTSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */