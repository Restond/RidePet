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
/*    */ public final class IntTag
/*    */   extends Tag
/*    */ {
/*    */   private final int value;
/*    */   
/*    */   public IntTag(int value) {
/* 36 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getValue() {
/* 41 */     return Integer.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "TAG_Int(" + this.value + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\IntTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */