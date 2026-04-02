/*     */ package lumine.utils.gson.adapters;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.TypeAdapter;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.google.gson.stream.JsonWriter;
/*     */ import io.lumine.utils.gson.adapters.BukkitSerializableAdapterFactory;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerialization;
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
/*     */ final class Adapter
/*     */   extends TypeAdapter<ConfigurationSerializable>
/*     */ {
/*  61 */   private static final Type RAW_OUTPUT_TYPE = (new Object()).getType();
/*     */   private final Gson gson;
/*     */   
/*     */   private Adapter(Gson gson) {
/*  65 */     this.gson = gson;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(JsonWriter out, ConfigurationSerializable value) {
/*  70 */     Map<String, Object> serialized = value.serialize();
/*     */     
/*  72 */     Map<String, Object> map = new LinkedHashMap<>(serialized.size() + 1);
/*  73 */     map.put("==", ConfigurationSerialization.getAlias(value.getClass()));
/*  74 */     map.putAll(serialized);
/*     */     
/*  76 */     this.gson.toJson(map, RAW_OUTPUT_TYPE, out);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfigurationSerializable read(JsonReader in) {
/*  81 */     Map<String, Object> map = (Map<String, Object>)this.gson.fromJson(in, RAW_OUTPUT_TYPE);
/*  82 */     deserializeChildren(map);
/*  83 */     return ConfigurationSerialization.deserializeObject(map);
/*     */   }
/*     */   
/*     */   private void deserializeChildren(Map<String, Object> map) {
/*  87 */     for (Map.Entry<String, Object> entry : map.entrySet()) {
/*  88 */       if (entry.getValue() instanceof Map) {
/*     */         
/*     */         try {
/*  91 */           Map<String, Object> value = (Map<String, Object>)entry.getValue();
/*     */           
/*  93 */           deserializeChildren(value);
/*     */           
/*  95 */           if (value.containsKey("==")) {
/*  96 */             entry.setValue(ConfigurationSerialization.deserializeObject(value));
/*     */           }
/*     */         }
/*  99 */         catch (Exception exception) {}
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 104 */       if (entry.getValue() instanceof Number) {
/* 105 */         double doubleVal = ((Number)entry.getValue()).doubleValue();
/* 106 */         int intVal = (int)doubleVal;
/* 107 */         long longVal = (long)doubleVal;
/*     */         
/* 109 */         if (intVal == doubleVal) {
/* 110 */           entry.setValue(Integer.valueOf(intVal)); continue;
/* 111 */         }  if (longVal == doubleVal) {
/* 112 */           entry.setValue(Long.valueOf(longVal)); continue;
/*     */         } 
/* 114 */         entry.setValue(Double.valueOf(doubleVal));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\adapters\BukkitSerializableAdapterFactory$Adapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */