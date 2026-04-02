/*    */ package lumine.xikage.mythicmobs.util.reflections.serializers;
/*    */ 
/*    */ import com.google.common.base.Supplier;
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.google.common.collect.Multimaps;
/*    */ import com.google.common.collect.SetMultimap;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.serializers.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements JsonDeserializer<Multimap>
/*    */ {
/*    */   public Multimap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
/* 60 */     SetMultimap<String, String> map = Multimaps.newSetMultimap(new HashMap<>(), (Supplier)new Object(this));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 65 */     for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)((JsonObject)jsonElement).entrySet()) {
/* 66 */       for (JsonElement element : entry.getValue()) {
/* 67 */         map.get(entry.getKey()).add(element.getAsString());
/*    */       }
/*    */     } 
/* 70 */     return (Multimap)map;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\serializers\JsonSerializer$1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */