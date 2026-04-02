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
/*    */ public final class IntArrayTag
/*    */   extends Tag
/*    */ {
/*    */   private final int[] value;
/*    */   
/*    */   public IntArrayTag(int[] value) {
/* 36 */     checkNotNull(value);
/* 37 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] getValue() {
/* 42 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     StringBuilder hex = new StringBuilder();
/* 48 */     for (int b : this.value) {
/* 49 */       String hexDigits = Integer.toHexString(b).toUpperCase();
/* 50 */       if (hexDigits.length() == 1) {
/* 51 */         hex.append("0");
/*    */       }
/* 53 */       hex.append(hexDigits).append(" ");
/*    */     } 
/* 55 */     return "TAG_Int_Array(" + hex + ")";
/*    */   }
/*    */   
/*    */   private static void checkNotNull(Object object) {
/* 59 */     if (object == null)
/* 60 */       throw new NullPointerException(); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\IntArrayTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */