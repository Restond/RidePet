/*     */ package lumine.utils.config.file;
/*     */ 
/*     */ import io.lumine.utils.config.Configuration;
/*     */ import io.lumine.utils.config.ConfigurationOptions;
/*     */ import io.lumine.utils.config.MemoryConfiguration;
/*     */ import io.lumine.utils.config.MemoryConfigurationOptions;
/*     */ import io.lumine.utils.config.file.FileConfiguration;
/*     */ 
/*     */ public class FileConfigurationOptions
/*     */   extends MemoryConfigurationOptions {
/*  11 */   private String header = null;
/*     */   private boolean copyHeader = true;
/*     */   
/*     */   protected FileConfigurationOptions(MemoryConfiguration configuration) {
/*  15 */     super(configuration);
/*     */   }
/*     */ 
/*     */   
/*     */   public FileConfiguration configuration() {
/*  20 */     return (FileConfiguration)super.configuration();
/*     */   }
/*     */ 
/*     */   
/*     */   public io.lumine.utils.config.file.FileConfigurationOptions copyDefaults(boolean value) {
/*  25 */     super.copyDefaults(value);
/*  26 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public io.lumine.utils.config.file.FileConfigurationOptions pathSeparator(char value) {
/*  31 */     super.pathSeparator(value);
/*  32 */     return this;
/*     */   }
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
/*     */   public String header() {
/*  50 */     return this.header;
/*     */   }
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
/*     */   public io.lumine.utils.config.file.FileConfigurationOptions header(String value) {
/*  69 */     this.header = value;
/*  70 */     return this;
/*     */   }
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
/*     */   public boolean copyHeader() {
/*  92 */     return this.copyHeader;
/*     */   }
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
/*     */   public io.lumine.utils.config.file.FileConfigurationOptions copyHeader(boolean value) {
/* 115 */     this.copyHeader = value;
/*     */     
/* 117 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\FileConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */