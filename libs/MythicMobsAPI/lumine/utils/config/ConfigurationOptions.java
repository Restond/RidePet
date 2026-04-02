/*    */ package lumine.utils.config;
/*    */ 
/*    */ import io.lumine.utils.config.Configuration;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfigurationOptions
/*    */ {
/*  9 */   private char pathSeparator = '.';
/*    */   private boolean copyDefaults = false;
/*    */   private final Configuration configuration;
/*    */   
/*    */   protected ConfigurationOptions(Configuration configuration) {
/* 14 */     this.configuration = configuration;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Configuration configuration() {
/* 23 */     return this.configuration;
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
/*    */   
/*    */   public char pathSeparator() {
/* 36 */     return this.pathSeparator;
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
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.ConfigurationOptions pathSeparator(char value) {
/* 50 */     this.pathSeparator = value;
/* 51 */     return this;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean copyDefaults() {
/* 69 */     return this.copyDefaults;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public io.lumine.utils.config.ConfigurationOptions copyDefaults(boolean value) {
/* 88 */     this.copyDefaults = value;
/* 89 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\ConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */