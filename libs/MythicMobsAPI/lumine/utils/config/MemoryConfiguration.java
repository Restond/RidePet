/*    */ package lumine.utils.config;
/*    */ 
/*    */ import io.lumine.utils.config.Configuration;
/*    */ import io.lumine.utils.config.ConfigurationOptions;
/*    */ import io.lumine.utils.config.ConfigurationSection;
/*    */ import io.lumine.utils.config.MemoryConfigurationOptions;
/*    */ import io.lumine.utils.config.MemorySection;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.Validate;
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
/*    */ public class MemoryConfiguration
/*    */   extends MemorySection
/*    */   implements Configuration
/*    */ {
/*    */   protected Configuration defaults;
/*    */   protected MemoryConfigurationOptions options;
/*    */   
/*    */   public MemoryConfiguration() {}
/*    */   
/*    */   public MemoryConfiguration(Configuration defaults) {
/* 30 */     this.defaults = defaults;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDefault(String path, Object value) {
/* 35 */     Validate.notNull(path, "Path may not be null");
/*    */     
/* 37 */     if (this.defaults == null) {
/* 38 */       this.defaults = new io.lumine.utils.config.MemoryConfiguration();
/*    */     }
/*    */     
/* 41 */     this.defaults.set(path, value);
/*    */   }
/*    */   
/*    */   public void addDefaults(Map<String, Object> defaults) {
/* 45 */     Validate.notNull(defaults, "Defaults may not be null");
/*    */     
/* 47 */     for (Map.Entry<String, Object> entry : defaults.entrySet()) {
/* 48 */       addDefault(entry.getKey(), entry.getValue());
/*    */     }
/*    */   }
/*    */   
/*    */   public void addDefaults(Configuration defaults) {
/* 53 */     Validate.notNull(defaults, "Defaults may not be null");
/*    */     
/* 55 */     addDefaults(defaults.getValues(true));
/*    */   }
/*    */   
/*    */   public void setDefaults(Configuration defaults) {
/* 59 */     Validate.notNull(defaults, "Defaults may not be null");
/*    */     
/* 61 */     this.defaults = defaults;
/*    */   }
/*    */   
/*    */   public Configuration getDefaults() {
/* 65 */     return this.defaults;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationSection getParent() {
/* 70 */     return null;
/*    */   }
/*    */   
/*    */   public MemoryConfigurationOptions options() {
/* 74 */     if (this.options == null) {
/* 75 */       this.options = new MemoryConfigurationOptions(this);
/*    */     }
/*    */     
/* 78 */     return this.options;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\MemoryConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */