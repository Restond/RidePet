/*    */ package lumine.utils.config;
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
/*    */ public class InvalidConfigurationException
/*    */   extends Exception
/*    */ {
/*    */   public InvalidConfigurationException() {}
/*    */   
/*    */   public InvalidConfigurationException(String msg) {
/* 22 */     super(msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidConfigurationException(Throwable cause) {
/* 32 */     super(cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidConfigurationException(String msg, Throwable cause) {
/* 43 */     super(msg, cause);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\InvalidConfigurationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */