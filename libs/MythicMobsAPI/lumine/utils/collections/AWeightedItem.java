/*    */ package lumine.utils.collections;
/*    */ 
/*    */ public abstract class AWeightedItem implements Comparable<AWeightedItem> {
/*  4 */   protected double weight = 1.0D;
/*    */ 
/*    */ 
/*    */   
/*    */   protected AWeightedItem(double weight) {
/*  9 */     this.weight = weight;
/*    */   }
/*    */   
/*    */   public double getWeight() {
/* 13 */     return this.weight;
/*    */   }
/*    */   
/*    */   public int compareTo(AWeightedItem o) {
/* 17 */     return Double.compare(this.weight, o.getWeight());
/*    */   }
/*    */   
/*    */   protected AWeightedItem() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\collections\AWeightedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */