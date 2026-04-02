/*    */ package lumine.utils.config.file;
/*    */ import io.lumine.utils.config.ConfigurationOptions;
/*    */ import io.lumine.utils.config.MemoryConfiguration;
/*    */ import io.lumine.utils.config.MemoryConfigurationOptions;
/*    */ import io.lumine.utils.config.file.FileConfiguration;
/*    */ import io.lumine.utils.config.file.FileConfigurationOptions;
/*    */ import io.lumine.utils.config.file.YamlConfiguration;
/*    */ 
/*    */ public class YamlConfigurationOptions extends FileConfigurationOptions {
/* 10 */   private int indent = 2;
/*    */   
/*    */   protected YamlConfigurationOptions(YamlConfiguration configuration) {
/* 13 */     super((MemoryConfiguration)configuration);
/*    */   }
/*    */ 
/*    */   
/*    */   public YamlConfiguration configuration() {
/* 18 */     return (YamlConfiguration)super.configuration();
/*    */   }
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.file.YamlConfigurationOptions copyDefaults(boolean value) {
/* 23 */     super.copyDefaults(value);
/* 24 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.file.YamlConfigurationOptions pathSeparator(char value) {
/* 29 */     super.pathSeparator(value);
/* 30 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.file.YamlConfigurationOptions header(String value) {
/* 35 */     super.header(value);
/* 36 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.file.YamlConfigurationOptions copyHeader(boolean value) {
/* 41 */     super.copyHeader(value);
/* 42 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int indent() {
/* 53 */     return this.indent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.file.YamlConfigurationOptions indent(int value) {
/* 65 */     Validate.isTrue((value >= 2), "Indent must be at least 2 characters");
/* 66 */     Validate.isTrue((value <= 9), "Indent cannot be greater than 9 characters");
/*    */     
/* 68 */     this.indent = value;
/* 69 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\YamlConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */