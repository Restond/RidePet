/*    */ package lumine.utils.collections;
/*    */ 
/*    */ import io.lumine.utils.collections.AWeightedItem;
/*    */ 
/*    */ public class WeightedEntry<T> extends AWeightedItem {
/*    */   private T value;
/*    */   
/*  8 */   public T getValue() { return this.value; } public void setValue(T value) { this.value = value; }
/*    */   
/*    */   public WeightedEntry(T value, double weight) {
/* 11 */     super(weight);
/*    */     
/* 13 */     this.value = value;
/*    */   }
/*    */   
/*    */   public WeightedEntry(T value) {
/* 17 */     this(value, 1.0D);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\collections\WeightedEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */