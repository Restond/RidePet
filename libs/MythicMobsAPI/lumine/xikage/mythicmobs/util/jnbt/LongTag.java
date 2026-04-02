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
/*    */ public final class LongTag
/*    */   extends Tag
/*    */ {
/*    */   private final long value;
/*    */   
/*    */   public LongTag(long value) {
/* 36 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getValue() {
/* 41 */     return Long.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "TAG_Long(" + this.value + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\LongTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */