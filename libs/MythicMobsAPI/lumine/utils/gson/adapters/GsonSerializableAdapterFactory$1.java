/*    */ package lumine.utils.gson.adapters;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.stream.JsonReader;
/*    */ import com.google.gson.stream.JsonWriter;
/*    */ import io.lumine.utils.gson.GsonProvider;
/*    */ import io.lumine.utils.gson.GsonSerializable;
/*    */ import io.lumine.utils.gson.adapters.GsonSerializableAdapterFactory;
/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Method;
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
/*    */   extends TypeAdapter<GsonSerializable>
/*    */ {
/*    */   public void write(JsonWriter out, GsonSerializable value) {
/* 36 */     if (value == null) {
/* 37 */       gson.toJson(null, out);
/*    */       return;
/*    */     } 
/* 40 */     gson.toJson(value.serialize(), out);
/*    */   }
/*    */ 
/*    */   
/*    */   public GsonSerializable read(JsonReader in) throws IOException {
/* 45 */     JsonElement element = GsonProvider.parser().parse(in);
/*    */     
/* 47 */     if (element.isJsonNull()) {
/* 48 */       return null;
/*    */     }
/*    */ 
/*    */     
/*    */     try {
/* 53 */       return (GsonSerializable)deserializeMethod.invoke(null, new Object[] { element });
/* 54 */     } catch (Exception e) {
/* 55 */       throw new IOException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\adapters\GsonSerializableAdapterFactory$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */