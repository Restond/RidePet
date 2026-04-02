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
/*    */ public final class StringTag
/*    */   extends Tag
/*    */ {
/*    */   private final String value;
/*    */   
/*    */   public StringTag(String value) {
/* 36 */     checkNotNull(value);
/* 37 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 42 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return "TAG_String(" + this.value + ")";
/*    */   }
/*    */   
/*    */   private static void checkNotNull(Object object) {
/* 51 */     if (object == null)
/* 52 */       throw new NullPointerException(); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\StringTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */