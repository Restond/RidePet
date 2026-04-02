/*    */ package lumine.utils.serialize;
/*    */ 
/*    */ import io.lumine.utils.serialize.FileStorageHandler;
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Function;
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
/*    */ final class null
/*    */   extends FileStorageHandler<T>
/*    */ {
/*    */   null(String fileName, String fileExtension, File dataFolder) {
/* 23 */     super(fileName, fileExtension, dataFolder);
/*    */   }
/*    */   
/*    */   protected T readFromFile(Path path) {
/* 27 */     return loadingFunc.apply(path);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void saveToFile(Path path, T t) {
/* 32 */     savingFunc.accept(path, t);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\FileStorageHandler$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */