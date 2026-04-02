/*    */ package lumine.utils.collections;
/*    */ 
/*    */ import io.lumine.utils.collections.WeightedCollection;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ISelector<T extends io.lumine.utils.collections.AWeightedItem>
/*    */ {
/*    */   default T get(WeightedCollection<T> items) {
/* 13 */     return get(items, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default T get(WeightedCollection<T> items, double offset) {
/* 23 */     throw new IllegalArgumentException("Selector does not support offsets");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default Collection<T> get(WeightedCollection<T> items, int count) {
/* 33 */     return get(items, count, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default Collection<T> get(WeightedCollection<T> items, int count, double offset) {
/* 44 */     throw new IllegalArgumentException("Selector does not support offsets");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default Collection<T> getNonRepeating(WeightedCollection<T> items, int count) {
/* 54 */     return getNonRepeating(items, count, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default Collection<T> getNonRepeating(WeightedCollection<T> items, int count, double offset) {
/* 65 */     throw new IllegalArgumentException("Selector does not support offsets");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\collections\ISelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */