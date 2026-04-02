/*    */ package lumine.xikage.mythicmobs.skills.variables;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableUtils;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class VariableSerializer
/*    */   implements JsonSerializer<Variable>, JsonDeserializer<Variable> {
/*    */   public Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 17 */     JsonObject obj = json.getAsJsonObject();
/* 18 */     String type = obj.get("type").getAsString();
/*    */     
/* 20 */     Class<? extends Variable> clazz = VariableUtils.getTypeClass(type);
/* 21 */     return (Variable)context.deserialize(json, clazz);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Variable src, Type typeOfSrc, JsonSerializationContext context) {
/* 26 */     JsonElement element = context.serialize(src);
/* 27 */     String typeCode = VariableUtils.getTypeName(src.getClass());
/* 28 */     element.getAsJsonObject().addProperty("type", typeCode);
/* 29 */     return element;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\VariableSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */