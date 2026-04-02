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
/*    */ public final class DoubleTag
/*    */   extends Tag
/*    */ {
/*    */   private final double value;
/*    */   
/*    */   public DoubleTag(double value) {
/* 36 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Double getValue() {
/* 41 */     return Double.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "TAG_Double(" + this.value + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\DoubleTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */