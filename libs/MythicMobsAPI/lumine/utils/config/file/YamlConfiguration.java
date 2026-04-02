/*     */ package lumine.utils.config.file;
/*     */ import io.lumine.utils.config.Configuration;
/*     */ import io.lumine.utils.config.ConfigurationOptions;
/*     */ import io.lumine.utils.config.ConfigurationSection;
/*     */ import io.lumine.utils.config.InvalidConfigurationException;
/*     */ import io.lumine.utils.config.MemoryConfigurationOptions;
/*     */ import io.lumine.utils.config.file.FileConfiguration;
/*     */ import io.lumine.utils.config.file.FileConfigurationOptions;
/*     */ import io.lumine.utils.config.file.YamlConfigurationOptions;
/*     */ import io.lumine.utils.logging.Log;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.yaml.snakeyaml.DumperOptions;
/*     */ import org.yaml.snakeyaml.Yaml;
/*     */ import org.yaml.snakeyaml.constructor.BaseConstructor;
/*     */ import org.yaml.snakeyaml.error.YAMLException;
/*     */ import org.yaml.snakeyaml.representer.Representer;
/*     */ 
/*     */ public class YamlConfiguration extends FileConfiguration {
/*     */   protected static final String COMMENT_PREFIX = "# ";
/*  25 */   private final DumperOptions yamlOptions = new DumperOptions(); protected static final String BLANK_CONFIG = "{}\n";
/*  26 */   private final Representer yamlRepresenter = (Representer)new YamlRepresenter();
/*  27 */   private final Yaml yaml = new Yaml((BaseConstructor)new YamlConstructor(), this.yamlRepresenter, this.yamlOptions);
/*     */ 
/*     */   
/*     */   public String saveToString() {
/*  31 */     this.yamlOptions.setIndent(options().indent());
/*  32 */     this.yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
/*  33 */     this.yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
/*     */     
/*  35 */     String header = buildHeader();
/*  36 */     String dump = this.yaml.dump(getValues(false));
/*     */     
/*  38 */     if (dump.equals("{}\n")) {
/*  39 */       dump = "";
/*     */     }
/*     */     
/*  42 */     return header + dump;
/*     */   }
/*     */   
/*     */   public void loadFromString(String contents) throws InvalidConfigurationException {
/*     */     Map<?, ?> input;
/*  47 */     Validate.notNull(contents, "Contents cannot be null");
/*     */ 
/*     */     
/*     */     try {
/*  51 */       input = (Map<?, ?>)this.yaml.load(contents);
/*  52 */     } catch (YAMLException e) {
/*  53 */       e.printStackTrace();
/*  54 */       throw new InvalidConfigurationException(e);
/*  55 */     } catch (ClassCastException e) {
/*  56 */       throw new InvalidConfigurationException("Top level is not a Map.");
/*     */     } 
/*     */     
/*  59 */     String header = parseHeader(contents);
/*  60 */     if (header.length() > 0) {
/*  61 */       options().header(header);
/*     */     }
/*     */     
/*  64 */     if (input != null) {
/*  65 */       convertMapsToSections(input, (ConfigurationSection)this);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void convertMapsToSections(Map<?, ?> input, ConfigurationSection section) {
/*  70 */     for (Map.Entry<?, ?> entry : input.entrySet()) {
/*  71 */       String key = entry.getKey().toString();
/*  72 */       Object value = entry.getValue();
/*     */       
/*  74 */       if (value instanceof Map) {
/*  75 */         convertMapsToSections((Map<?, ?>)value, section.createSection(key)); continue;
/*     */       } 
/*  77 */       section.set(key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String parseHeader(String input) {
/*  83 */     String[] lines = input.split("\r?\n", -1);
/*  84 */     StringBuilder result = new StringBuilder();
/*  85 */     boolean readingHeader = true;
/*  86 */     boolean foundHeader = false;
/*     */     
/*  88 */     for (int i = 0; i < lines.length && readingHeader; i++) {
/*  89 */       String line = lines[i];
/*     */       
/*  91 */       if (line.startsWith("# ")) {
/*  92 */         if (i > 0) {
/*  93 */           result.append("\n");
/*     */         }
/*     */         
/*  96 */         if (line.length() > "# ".length()) {
/*  97 */           result.append(line.substring("# ".length()));
/*     */         }
/*     */         
/* 100 */         foundHeader = true;
/* 101 */       } else if (foundHeader && line.length() == 0) {
/* 102 */         result.append("\n");
/* 103 */       } else if (foundHeader) {
/* 104 */         readingHeader = false;
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String buildHeader() {
/* 113 */     String header = options().header();
/*     */     
/* 115 */     if (options().copyHeader()) {
/* 116 */       Configuration def = getDefaults();
/*     */       
/* 118 */       if (def != null && def instanceof FileConfiguration) {
/* 119 */         FileConfiguration filedefaults = (FileConfiguration)def;
/* 120 */         String defaultsHeader = filedefaults.buildHeader();
/*     */         
/* 122 */         if (defaultsHeader != null && defaultsHeader.length() > 0) {
/* 123 */           return defaultsHeader;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     if (header == null) {
/* 129 */       return "";
/*     */     }
/*     */     
/* 132 */     StringBuilder builder = new StringBuilder();
/* 133 */     String[] lines = header.split("\r?\n", -1);
/* 134 */     boolean startedHeader = false;
/*     */     
/* 136 */     for (int i = lines.length - 1; i >= 0; i--) {
/* 137 */       builder.insert(0, "\n");
/*     */       
/* 139 */       if (startedHeader || lines[i].length() != 0) {
/* 140 */         builder.insert(0, lines[i]);
/* 141 */         builder.insert(0, "# ");
/* 142 */         startedHeader = true;
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public YamlConfigurationOptions options() {
/* 151 */     if (this.options == null) {
/* 152 */       this.options = (MemoryConfigurationOptions)new YamlConfigurationOptions(this);
/*     */     }
/*     */     
/* 155 */     return (YamlConfigurationOptions)this.options;
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
/*     */   public static io.lumine.utils.config.file.YamlConfiguration loadConfiguration(File file) {
/* 172 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 174 */     io.lumine.utils.config.file.YamlConfiguration config = new io.lumine.utils.config.file.YamlConfiguration();
/*     */ 
/*     */     
/* 177 */     try { config.load(file); }
/* 178 */     catch (FileNotFoundException fileNotFoundException) {  }
/* 179 */     catch (IOException ex)
/* 180 */     { Log.severe("Cannot load " + file, ex); }
/* 181 */     catch (InvalidConfigurationException ex)
/* 182 */     { Log.severe("Cannot load " + file, (Exception)ex); }
/*     */ 
/*     */     
/* 185 */     return config;
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
/*     */   public static io.lumine.utils.config.file.YamlConfiguration loadConfiguration(Reader reader) {
/* 200 */     Validate.notNull(reader, "Stream cannot be null");
/*     */     
/* 202 */     io.lumine.utils.config.file.YamlConfiguration config = new io.lumine.utils.config.file.YamlConfiguration();
/*     */     
/*     */     try {
/* 205 */       config.load(reader);
/* 206 */     } catch (IOException ex) {
/* 207 */       Log.severe("Cannot load configuration from stream", ex);
/* 208 */     } catch (InvalidConfigurationException ex) {
/* 209 */       Log.severe("Cannot load configuration from stream", (Exception)ex);
/*     */     } 
/*     */     
/* 212 */     return config;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\YamlConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */