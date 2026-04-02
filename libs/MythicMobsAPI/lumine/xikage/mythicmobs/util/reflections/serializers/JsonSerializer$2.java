/*    */ package lumine.xikage.mythicmobs.util.reflections.serializers;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.serializers.JsonSerializer;
/*    */ import java.lang.reflect.Type;
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
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements JsonSerializer<Multimap>
/*    */ {
/*    */   public JsonElement serialize(Multimap multimap, Type type, JsonSerializationContext jsonSerializationContext) {
/* 55 */     return jsonSerializationContext.serialize(multimap.asMap());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\serializers\JsonSerializer$2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */