/*     */ package lumine.utils.gson.converters;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import io.lumine.utils.annotation.NonnullByDefault;
/*     */ import io.lumine.utils.gson.converters.GsonConverter;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @NonnullByDefault
/*     */ abstract class AbstractGsonConverter<M extends Map<String, Object>, L extends List<Object>, S extends Set<Object>>
/*     */   implements GsonConverter
/*     */ {
/*     */   protected abstract MapBuilder<M, String, Object> newMapBuilder();
/*     */   
/*     */   protected abstract ListBuilder<L, Object> newListBuilder();
/*     */   
/*     */   protected abstract SetBuilder<S, Object> newSetBuilder();
/*     */   
/*     */   public M unwrapObject(JsonObject object) {
/*  52 */     MapBuilder<M, String, Object> builder = newMapBuilder();
/*  53 */     for (Map.Entry<String, JsonElement> e : (Iterable<Map.Entry<String, JsonElement>>)object.entrySet()) {
/*  54 */       builder.put(e.getKey(), unwrapElement(e.getValue()));
/*     */     }
/*  56 */     return (M)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public L unwrapArray(JsonArray array) {
/*  61 */     ListBuilder<L, Object> builder = newListBuilder();
/*  62 */     for (JsonElement element : array) {
/*  63 */       builder.add(unwrapElement(element));
/*     */     }
/*  65 */     return (L)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public S unwrapArrayToSet(JsonArray array) {
/*  70 */     SetBuilder<S, Object> builder = newSetBuilder();
/*  71 */     for (JsonElement element : array) {
/*  72 */       builder.add(unwrapElement(element));
/*     */     }
/*  74 */     return (S)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object unwarpPrimitive(JsonPrimitive primitive) {
/*  79 */     if (primitive.isBoolean())
/*  80 */       return Boolean.valueOf(primitive.getAsBoolean()); 
/*  81 */     if (primitive.isNumber())
/*  82 */       return primitive.getAsNumber(); 
/*  83 */     if (primitive.isString()) {
/*  84 */       return primitive.getAsString();
/*     */     }
/*  86 */     throw new IllegalArgumentException("Unknown primitive type: " + primitive);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object unwrapElement(JsonElement element) {
/*  93 */     if (element.isJsonNull())
/*  94 */       return null; 
/*  95 */     if (element.isJsonArray())
/*  96 */       return unwrapArray(element.getAsJsonArray()); 
/*  97 */     if (element.isJsonObject())
/*  98 */       return unwrapObject(element.getAsJsonObject()); 
/*  99 */     if (element.isJsonPrimitive()) {
/* 100 */       return unwarpPrimitive(element.getAsJsonPrimitive());
/*     */     }
/* 102 */     throw new IllegalArgumentException("Unknown element type: " + element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonElement wrap(Object object) {
/* 110 */     if (object instanceof JsonElement)
/* 111 */       return (JsonElement)object; 
/* 112 */     if (object instanceof Iterable) {
/* 113 */       Iterable iterable = (Iterable)object;
/* 114 */       JsonArray array = new JsonArray();
/* 115 */       for (Object o : iterable) {
/* 116 */         array.add(wrap(o));
/*     */       }
/* 118 */       return (JsonElement)array;
/* 119 */     }  if (object instanceof Map) {
/* 120 */       Map<?, ?> map = (Map<?, ?>)object;
/* 121 */       JsonObject obj = new JsonObject();
/* 122 */       for (Map.Entry<?, ?> e : map.entrySet()) {
/* 123 */         if (e.getKey() instanceof String) {
/* 124 */           String key = (String)e.getKey();
/* 125 */           obj.add(key, wrap(e.getValue()));
/*     */         } 
/*     */       } 
/* 128 */       return (JsonElement)obj;
/* 129 */     }  if (object instanceof String)
/* 130 */       return (JsonElement)new JsonPrimitive((String)object); 
/* 131 */     if (object instanceof Character)
/* 132 */       return (JsonElement)new JsonPrimitive((Character)object); 
/* 133 */     if (object instanceof Boolean)
/* 134 */       return (JsonElement)new JsonPrimitive((Boolean)object); 
/* 135 */     if (object instanceof Number) {
/* 136 */       return (JsonElement)new JsonPrimitive((Number)object);
/*     */     }
/* 138 */     throw new IllegalArgumentException("Unable to wrap object: " + object.getClass());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\AbstractGsonConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */