/*    */ package lumine.xikage.mythicmobs.util.reflections.serializers;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.google.common.io.Files;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.Reflections;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.serializers.Serializer;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.util.Utils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.Charset;
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
/*    */ public class JsonSerializer
/*    */   implements Serializer
/*    */ {
/*    */   private Gson gson;
/*    */   
/*    */   public Reflections read(InputStream inputStream) {
/* 33 */     return (Reflections)getGson().fromJson(new InputStreamReader(inputStream), Reflections.class);
/*    */   }
/*    */   
/*    */   public File save(Reflections reflections, String filename) {
/*    */     try {
/* 38 */       File file = Utils.prepareFile(filename);
/* 39 */       Files.write(toString(reflections), file, Charset.defaultCharset());
/* 40 */       return file;
/* 41 */     } catch (IOException e) {
/* 42 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString(Reflections reflections) {
/* 47 */     return getGson().toJson(reflections);
/*    */   }
/*    */   
/*    */   private Gson getGson() {
/* 51 */     if (this.gson == null) {
/* 52 */       this
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
/* 74 */         .gson = (new GsonBuilder()).registerTypeAdapter(Multimap.class, new Object(this)).registerTypeAdapter(Multimap.class, new Object(this)).setPrettyPrinting().create();
/*    */     }
/*    */     
/* 77 */     return this.gson;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\serializers\JsonSerializer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */