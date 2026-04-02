/*    */ package lumine.xikage.mythicmobs.util.jnbt;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTCompoundSerializer
/*    */   implements JsonSerializer<CompoundTag>, JsonDeserializer<CompoundTag>
/*    */ {
/*    */   public CompoundTag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 20 */     Class<? extends CompoundTag> clazz = (Class)MythicMobs.inst().getVolatileCodeHandler().createCompoundTag(new HashMap<>()).getClass();
/* 21 */     return (CompoundTag)context.deserialize(json, clazz);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement serialize(CompoundTag src, Type typeOfSrc, JsonSerializationContext context) {
/* 26 */     JsonElement element = context.serialize(src);
/* 27 */     return element;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\NBTCompoundSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */