/*    */ package lumine.xikage.mythicmobs.util.jnbt;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
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
/*    */ public final class ByteArrayTag
/*    */   extends Tag
/*    */ {
/*    */   private final byte[] value;
/*    */   
/*    */   public ByteArrayTag(byte[] value) {
/* 36 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getValue() {
/* 41 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     StringBuilder hex = new StringBuilder();
/* 47 */     for (byte b : this.value) {
/* 48 */       String hexDigits = Integer.toHexString(b).toUpperCase();
/* 49 */       if (hexDigits.length() == 1) {
/* 50 */         hex.append("0");
/*    */       }
/* 52 */       hex.append(hexDigits).append(" ");
/*    */     } 
/* 54 */     return "TAG_Byte_Array(" + hex + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\ByteArrayTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */