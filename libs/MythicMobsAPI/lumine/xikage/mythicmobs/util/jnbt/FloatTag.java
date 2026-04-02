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
/*    */ public final class FloatTag
/*    */   extends Tag
/*    */ {
/*    */   private final float value;
/*    */   
/*    */   public FloatTag(float value) {
/* 36 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Float getValue() {
/* 41 */     return Float.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "TAG_Float(" + this.value + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\FloatTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */