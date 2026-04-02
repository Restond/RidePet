/*    */ package lumine.utils.serialize;
/*    */ 
/*    */ import java.util.Base64;
/*    */ import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
/*    */ 
/*    */ 
/*    */ class Base64Util
/*    */ {
/*    */   public static String encode(byte[] buf) {
/* 10 */     return Base64.getEncoder().encodeToString(buf);
/*    */   }
/*    */   
/*    */   public static byte[] decode(String src) {
/*    */     try {
/* 15 */       return Base64.getDecoder().decode(src);
/* 16 */     } catch (IllegalArgumentException e) {
/*    */       
/*    */       try {
/* 19 */         return Base64Coder.decodeLines(src);
/* 20 */       } catch (Exception ignored) {
/* 21 */         throw e;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\Base64Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */