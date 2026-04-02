/*    */ package lumine.utils.config;
/*    */ 
/*    */ import io.lumine.utils.config.Configuration;
/*    */ import io.lumine.utils.config.ConfigurationOptions;
/*    */ import io.lumine.utils.config.MemoryConfiguration;
/*    */ 
/*    */ public class MemoryConfigurationOptions extends ConfigurationOptions {
/*    */   protected MemoryConfigurationOptions(MemoryConfiguration configuration) {
/*  9 */     super((Configuration)configuration);
/*    */   }
/*    */ 
/*    */   
/*    */   public MemoryConfiguration configuration() {
/* 14 */     return (MemoryConfiguration)super.configuration();
/*    */   }
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.MemoryConfigurationOptions copyDefaults(boolean value) {
/* 19 */     super.copyDefaults(value);
/* 20 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.MemoryConfigurationOptions pathSeparator(char value) {
/* 25 */     super.pathSeparator(value);
/* 26 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\MemoryConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */