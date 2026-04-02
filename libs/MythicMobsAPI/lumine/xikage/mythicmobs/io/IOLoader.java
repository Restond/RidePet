/*    */ package lumine.xikage.mythicmobs.io;
/*    */ 
/*    */ import io.lumine.utils.config.Configuration;
/*    */ import io.lumine.utils.config.file.FileConfiguration;
/*    */ import io.lumine.utils.config.file.YamlConfiguration;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ import java.util.logging.Level;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ public class IOLoader<T extends JavaPlugin>
/*    */ {
/*    */   private T plugin;
/* 21 */   private File file = null;
/* 22 */   private FileConfiguration fileConfig = null;
/*    */   private String defaultFile;
/*    */   
/*    */   public IOLoader(T plugin, String newfile) {
/* 26 */     this(plugin, newfile, null);
/*    */   }
/*    */   public IOLoader(T plugin, String newfile, String folder) {
/* 29 */     this.plugin = plugin;
/* 30 */     this.defaultFile = newfile;
/*    */     
/* 32 */     if (folder != null) {
/* 33 */       folder = folder.replace("/", System.getProperty("file.separator"));
/*    */       
/* 35 */       String path = plugin.getDataFolder() + System.getProperty("file.separator") + folder;
/* 36 */       File dir = new File(path);
/* 37 */       if (!dir.exists()) {
/* 38 */         dir.mkdir();
/*    */       }
/* 40 */       this.file = new File(plugin.getDataFolder() + System.getProperty("file.separator") + folder, newfile);
/*    */     } else {
/* 42 */       this.file = new File(plugin.getDataFolder(), newfile);
/*    */     } 
/*    */     
/* 45 */     reloadCustomConfig(!this.file.exists());
/*    */   }
/*    */   public IOLoader(T plugin, File newfile, String folder) {
/* 48 */     this.plugin = plugin;
/*    */     
/* 50 */     this.file = newfile;
/*    */     
/* 52 */     reloadCustomConfig((this.file == null));
/*    */   }
/*    */   
/*    */   public void reloadCustomConfig(boolean loadDefaults) {
/* 56 */     if (loadDefaults && this.file != null) {
/* 57 */       this.file = new File(this.file.getParent(), this.defaultFile);
/* 58 */       this.fileConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
/* 59 */       InputStream defConfigStream = this.plugin.getResource(this.defaultFile);
/*    */       
/* 61 */       if (defConfigStream != null) {
/* 62 */         Reader reader = new InputStreamReader(defConfigStream);
/* 63 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(reader);
/* 64 */         this.fileConfig.setDefaults((Configuration)defConfig);
/*    */       } 
/* 66 */       getCustomConfig().options().copyDefaults(true);
/* 67 */       MythicLogger.log("File " + this.defaultFile + " not found! Creating a new one...");
/* 68 */       saveCustomConfig();
/*    */     } else {
/* 70 */       this.fileConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
/*    */     } 
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 75 */     return this.file;
/*    */   }
/*    */   
/*    */   public FileConfiguration getCustomConfig() {
/* 79 */     if (this.fileConfig == null) {
/* 80 */       reloadCustomConfig((this.file == null));
/*    */     }
/* 82 */     return this.fileConfig;
/*    */   }
/*    */   
/*    */   public void saveCustomConfig() {
/* 86 */     if (this.fileConfig == null || this.file == null) {
/*    */       return;
/*    */     }
/*    */     try {
/* 90 */       getCustomConfig().save(this.file);
/* 91 */     } catch (IOException ex) {
/* 92 */       if (ConfigManager.debugLevel > 0) {
/*    */         
/* 94 */         this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.file, ex);
/*    */       } else {
/* 96 */         MythicMobs.error("Severe Error: Could not save config to " + this.file + ". Turn on debugging if this error keeps occuring for details.");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\io\IOLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */