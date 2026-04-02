/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class UUIDUtil
/*    */ {
/*    */   public static UUID getUUIDFromString(String s) {
/* 11 */     String md5 = getMD5(s);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 17 */     String uuid = md5.substring(0, 8) + "-" + md5.substring(8, 12) + "-" + md5.substring(12, 16) + "-" + md5.substring(16, 20) + "-" + md5.substring(20);
/*    */     
/* 19 */     return UUID.fromString(uuid);
/*    */   }
/*    */   
/*    */   public static String getMD5(String input) {
/*    */     try {
/* 24 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 25 */       byte[] messageDigest = md.digest(input.getBytes());
/* 26 */       BigInteger number = new BigInteger(1, messageDigest);
/* 27 */       String hashtext = number.toString(16);
/*    */       
/* 29 */       while (hashtext.length() < 32) {
/* 30 */         hashtext = "0" + hashtext;
/*    */       }
/* 32 */       return hashtext;
/*    */     }
/* 34 */     catch (NoSuchAlgorithmException e) {
/* 35 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\UUIDUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */