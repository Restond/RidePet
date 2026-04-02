/*    */ package lumine.utils.serialize;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Optional;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FileStorageHandler<T>
/*    */ {
/* 20 */   private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
/*    */   
/*    */   public static <T> io.lumine.utils.serialize.FileStorageHandler<T> build(String fileName, String fileExtension, File dataFolder, Function<Path, T> loadingFunc, BiConsumer<Path, T> savingFunc) {
/* 23 */     return (io.lumine.utils.serialize.FileStorageHandler<T>)new Object(fileName, fileExtension, dataFolder, loadingFunc, savingFunc);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final String fileName;
/*    */ 
/*    */ 
/*    */   
/*    */   private final String fileExtension;
/*    */ 
/*    */ 
/*    */   
/*    */   private final File dataFolder;
/*    */ 
/*    */ 
/*    */   
/*    */   public FileStorageHandler(String fileName, String fileExtension, File dataFolder) {
/* 42 */     this.fileName = fileName;
/* 43 */     this.fileExtension = fileExtension;
/* 44 */     this.dataFolder = dataFolder;
/*    */   }
/*    */   
/*    */   protected abstract T readFromFile(Path paramPath);
/*    */   
/*    */   protected abstract void saveToFile(Path paramPath, T paramT);
/*    */   
/*    */   public Optional<T> load() {
/* 52 */     File file = new File(this.dataFolder, this.fileName + this.fileExtension);
/* 53 */     if (file.exists()) {
/* 54 */       return Optional.ofNullable(readFromFile(file.toPath()));
/*    */     }
/* 56 */     return Optional.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveAndBackup(T data) {
/* 61 */     this.dataFolder.mkdirs();
/* 62 */     File file = new File(this.dataFolder, this.fileName + this.fileExtension);
/* 63 */     if (file.exists()) {
/* 64 */       File backupDir = new File(this.dataFolder, "backups");
/* 65 */       backupDir.mkdirs();
/*    */       
/* 67 */       File backupFile = new File(backupDir, this.fileName + "-" + DATE_FORMAT.format(new Date(System.currentTimeMillis())) + this.fileExtension);
/*    */       
/*    */       try {
/* 70 */         Files.move(file.toPath(), backupFile.toPath(), new java.nio.file.CopyOption[0]);
/* 71 */       } catch (IOException e) {
/* 72 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */     
/*    */     try {
/* 77 */       file.createNewFile();
/* 78 */     } catch (IOException e) {
/* 79 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 82 */     saveToFile(file.toPath(), data);
/*    */   }
/*    */   
/*    */   public void save(T data) {
/* 86 */     this.dataFolder.mkdirs();
/* 87 */     File file = new File(this.dataFolder, this.fileName + this.fileExtension);
/* 88 */     if (file.exists()) {
/* 89 */       file.delete();
/*    */     }
/*    */     
/*    */     try {
/* 93 */       file.createNewFile();
/* 94 */     } catch (IOException e) {
/* 95 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 98 */     saveToFile(file.toPath(), data);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\FileStorageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */