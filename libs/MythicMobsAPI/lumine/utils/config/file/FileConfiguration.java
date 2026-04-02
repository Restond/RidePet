/*     */ package lumine.utils.config.file;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.io.Files;
/*     */ import io.lumine.utils.config.Configuration;
/*     */ import io.lumine.utils.config.ConfigurationOptions;
/*     */ import io.lumine.utils.config.InvalidConfigurationException;
/*     */ import io.lumine.utils.config.MemoryConfiguration;
/*     */ import io.lumine.utils.config.MemoryConfigurationOptions;
/*     */ import io.lumine.utils.config.file.FileConfigurationOptions;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import org.apache.commons.lang.Validate;
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
/*     */ public abstract class FileConfiguration
/*     */   extends MemoryConfiguration
/*     */ {
/*     */   public FileConfiguration() {}
/*     */   
/*     */   public FileConfiguration(Configuration defaults) {
/*  41 */     super(defaults);
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
/*     */   public void save(File file) throws IOException {
/*  60 */     Validate.notNull(file, "File cannot be null");
/*     */     
/*  62 */     Files.createParentDirs(file);
/*     */     
/*  64 */     String data = saveToString();
/*     */     
/*  66 */     Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);
/*     */     
/*     */     try {
/*  69 */       writer.write(data);
/*     */     } finally {
/*  71 */       writer.close();
/*     */     } 
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
/*     */   public void save(String file) throws IOException {
/*  91 */     Validate.notNull(file, "File cannot be null");
/*     */     
/*  93 */     save(new File(file));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
/* 122 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 124 */     FileInputStream stream = new FileInputStream(file);
/*     */     
/* 126 */     load(new InputStreamReader(stream, Charsets.UTF_8));
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
/*     */   public void load(Reader reader) throws IOException, InvalidConfigurationException {
/* 143 */     BufferedReader input = (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
/*     */     
/* 145 */     StringBuilder builder = new StringBuilder();
/*     */     
/*     */     try {
/*     */       String line;
/*     */       
/* 150 */       while ((line = input.readLine()) != null) {
/* 151 */         builder.append(line);
/* 152 */         builder.append('\n');
/*     */       } 
/*     */     } finally {
/* 155 */       input.close();
/*     */     } 
/*     */     
/* 158 */     loadFromString(builder.toString());
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
/*     */   public void load(String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
/* 180 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 182 */     load(new File(file));
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
/*     */   public FileConfigurationOptions options() {
/* 216 */     if (this.options == null) {
/* 217 */       this.options = (MemoryConfigurationOptions)new FileConfigurationOptions(this);
/*     */     }
/*     */     
/* 220 */     return (FileConfigurationOptions)this.options;
/*     */   }
/*     */   
/*     */   public abstract String saveToString();
/*     */   
/*     */   public abstract void loadFromString(String paramString) throws InvalidConfigurationException;
/*     */   
/*     */   protected abstract String buildHeader();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\FileConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */