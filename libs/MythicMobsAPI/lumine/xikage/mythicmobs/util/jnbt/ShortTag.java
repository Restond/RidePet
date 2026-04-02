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
/*    */ public final class ShortTag
/*    */   extends Tag
/*    */ {
/*    */   private final short value;
/*    */   
/*    */   public ShortTag(short value) {
/* 36 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Short getValue() {
/* 41 */     return Short.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "TAG_Short(" + this.value + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\ShortTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */