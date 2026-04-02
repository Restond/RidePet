/*    */ package lumine.utils.serialize;
/*    */ 
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import com.google.gson.Gson;
/*    */ import io.lumine.utils.gson.GsonProvider;
/*    */ import io.lumine.utils.serialize.FileStorageHandler;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GsonStorageHandler<T>
/*    */   extends FileStorageHandler<T>
/*    */ {
/*    */   protected final Type type;
/*    */   protected final Gson gson;
/*    */   
/*    */   public GsonStorageHandler(String fileName, String fileExtension, File dataFolder, Class<T> clazz) {
/* 27 */     this(fileName, fileExtension, dataFolder, TypeToken.of(clazz));
/*    */   }
/*    */   
/*    */   public GsonStorageHandler(String fileName, String fileExtension, File dataFolder, Class<T> clazz, Gson gson) {
/* 31 */     this(fileName, fileExtension, dataFolder, TypeToken.of(clazz), gson);
/*    */   }
/*    */   
/*    */   public GsonStorageHandler(String fileName, String fileExtension, File dataFolder, TypeToken<T> type) {
/* 35 */     this(fileName, fileExtension, dataFolder, type.getType());
/*    */   }
/*    */   
/*    */   public GsonStorageHandler(String fileName, String fileExtension, File dataFolder, TypeToken<T> type, Gson gson) {
/* 39 */     this(fileName, fileExtension, dataFolder, type.getType(), gson);
/*    */   }
/*    */   
/*    */   public GsonStorageHandler(String fileName, String fileExtension, File dataFolder, Type type) {
/* 43 */     this(fileName, fileExtension, dataFolder, type, GsonProvider.prettyPrinting());
/*    */   }
/*    */   
/*    */   public GsonStorageHandler(String fileName, String fileExtension, File dataFolder, Type type, Gson gson) {
/* 47 */     super(fileName, fileExtension, dataFolder);
/* 48 */     this.type = type;
/* 49 */     this.gson = gson;
/*    */   }
/*    */ 
/*    */   
/*    */   protected T readFromFile(Path path) {
/* 54 */     try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
/* 55 */       return (T)this.gson.fromJson(reader, this.type);
/* 56 */     } catch (IOException e) {
/* 57 */       e.printStackTrace();
/* 58 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void saveToFile(Path path, T t) {
/* 64 */     try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, new java.nio.file.OpenOption[0])) {
/* 65 */       this.gson.toJson(t, this.type, writer);
/* 66 */     } catch (IOException e) {
/* 67 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\GsonStorageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */