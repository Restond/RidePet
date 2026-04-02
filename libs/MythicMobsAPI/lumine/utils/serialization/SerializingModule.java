/*     */ package lumine.utils.serialization;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import io.lumine.utils.logging.Log;
/*     */ import io.lumine.utils.serialization.WrappedJsonFile;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class SerializingModule {
/*     */   private final JavaPlugin plugin;
/*     */   private final File basedir;
/*     */   protected Gson GSON;
/*     */   
/*     */   public File getBasedir() {
/*  21 */     return this.basedir;
/*     */   }
/*     */ 
/*     */   
/*     */   public SerializingModule(JavaPlugin core, String folderName) {
/*  26 */     this.plugin = core;
/*  27 */     this.basedir = new File(core.getDataFolder(), folderName);
/*  28 */     if (!this.basedir.exists() && !this.basedir.mkdirs()) {
/*  29 */       Log.severe("Couldn't create data folder for " + getClass().getName());
/*     */     }
/*     */     
/*  32 */     this
/*     */ 
/*     */ 
/*     */       
/*  36 */       .GSON = (new GsonBuilder()).enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().create();
/*     */   }
/*     */   
/*     */   public SerializingModule(JavaPlugin core, String folderName, Gson gson) {
/*  40 */     this(core, folderName);
/*  41 */     this.GSON = gson;
/*     */   }
/*     */   
/*     */   public File getModuleFile(String path) {
/*  45 */     return new File(this.basedir, path);
/*     */   }
/*     */   
/*     */   public File getModuleDirectory(String path) {
/*  49 */     File dir = getModuleFile(path);
/*  50 */     if (!dir.exists() && !dir.mkdirs()) {
/*  51 */       Log.severe("Couldn't create data folder for " + getClass().getName());
/*     */     }
/*  53 */     return dir;
/*     */   }
/*     */   
/*     */   public List<File> getModuleFiles() {
/*  57 */     List<File> resultList = new ArrayList<>();
/*     */     
/*  59 */     File[] fList = this.basedir.listFiles();
/*     */     
/*  61 */     for (File file : fList) {
/*  62 */       if (file.isFile()) {
/*  63 */         resultList.add(file);
/*  64 */       } else if (file.isDirectory()) {
/*  65 */         resultList.addAll(getModuleFiles(file.getAbsolutePath()));
/*     */       } 
/*     */     } 
/*  68 */     return resultList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<File> getModuleFiles(String path) {
/*  76 */     File directory = getModuleDirectory(path);
/*     */     
/*  78 */     List<File> resultList = new ArrayList<>();
/*     */     
/*  80 */     File[] fList = directory.listFiles();
/*     */     
/*  82 */     for (File file : fList) {
/*  83 */       if (file.isFile()) {
/*  84 */         resultList.add(file);
/*  85 */       } else if (file.isDirectory()) {
/*  86 */         resultList.addAll(getModuleFiles(file.getAbsolutePath()));
/*     */       } 
/*     */     } 
/*  89 */     return resultList;
/*     */   }
/*     */   
/*     */   public <T> WrappedJsonFile<T> loadFile(File file, Class<T> type) {
/*  93 */     if (!file.exists()) {
/*     */       try {
/*  95 */         return new WrappedJsonFile(this, file, type.newInstance());
/*  96 */       } catch (ReflectiveOperationException e) {
/*  97 */         Log.severe("Couldn't use default constructor when loading file");
/*  98 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 102 */     return new WrappedJsonFile(this, file, loadJson(file, type));
/*     */   }
/*     */   
/*     */   public <T> T loadJson(File file, Class<T> type) {
/* 106 */     if (!file.exists()) {
/* 107 */       return null;
/*     */     }
/*     */     
/* 110 */     try (FileReader reader = new FileReader(file)) {
/* 111 */       return (T)this.GSON.fromJson(reader, type);
/* 112 */     } catch (IOException e) {
/* 113 */       Log.severe("Couldn't load json in module: " + getClass().getName());
/* 114 */       e.printStackTrace();
/*     */       
/* 116 */       return null;
/*     */     } 
/*     */   }
/*     */   public void saveJson(File file, Object value) {
/* 120 */     try (FileWriter writer = new FileWriter(file)) {
/* 121 */       this.GSON.toJson(value, writer);
/* 122 */     } catch (IOException e) {
/* 123 */       Log.severe("Couldn't save json in module: " + getClass().getName());
/* 124 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveFile(File file, String contents) {
/* 129 */     try (FileWriter writer = new FileWriter(file)) {
/* 130 */       writer.write(contents);
/* 131 */     } catch (IOException e) {
/* 132 */       Log.severe("Couldn't save file in module: " + getClass().getName());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialization\SerializingModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */