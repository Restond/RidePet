/*    */ package lumine.utils.collections;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import io.lumine.utils.collections.AWeightedItem;
/*    */ import io.lumine.utils.collections.ISelector;
/*    */ import io.lumine.utils.collections.WeightedCollection;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WeightedSelector<T extends AWeightedItem>
/*    */   implements ISelector<T> {
/* 14 */   private Random random = new Random();
/*    */   
/*    */   public WeightedSelector() {
/* 17 */     this(new Random());
/*    */   }
/*    */   
/*    */   public WeightedSelector(long seed) {
/* 21 */     this(new Random(seed));
/*    */   }
/*    */   
/*    */   public WeightedSelector(Random random) {
/* 25 */     this.random = random;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public T get(WeightedCollection<T> items) {
/* 31 */     double weight = this.random.nextDouble() * items.getWeight();
/*    */     
/* 33 */     List<T> itemsView = items.getView();
/*    */     
/* 35 */     if (itemsView.isEmpty()) {
/* 36 */       return null;
/*    */     }
/*    */     
/* 39 */     for (AWeightedItem aWeightedItem : itemsView) {
/* 40 */       if (weight <= aWeightedItem.getWeight()) {
/* 41 */         return (T)aWeightedItem;
/*    */       }
/* 43 */       weight -= aWeightedItem.getWeight();
/*    */     } 
/*    */     
/* 46 */     return itemsView.get(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<T> get(WeightedCollection<T> items, int count) {
/* 52 */     List<T> itemList = Lists.newLinkedList();
/*    */     
/* 54 */     for (int i = 0; i < count; i++) {
/* 55 */       T item = get(items);
/*    */       
/* 57 */       if (item != null) {
/* 58 */         itemList.add(item);
/*    */       }
/*    */     } 
/*    */     
/* 62 */     return itemList;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<T> getNonRepeating(WeightedCollection<T> items, int count) {
/* 68 */     List<T> itemList = Lists.newLinkedList();
/*    */     
/* 70 */     List<T> itemsCopy = items.getCopy();
/*    */     
/* 72 */     double maxWeight = items.getWeight();
/*    */     
/* 74 */     for (int i = 0; i < count && 
/* 75 */       !itemsCopy.isEmpty(); i++) {
/*    */ 
/*    */ 
/*    */       
/* 79 */       double weight = this.random.nextDouble() * maxWeight;
/*    */       
/* 81 */       Iterator<T> it = itemsCopy.iterator();
/*    */       
/* 83 */       while (it.hasNext()) {
/* 84 */         AWeightedItem aWeightedItem = (AWeightedItem)it.next();
/*    */         
/* 86 */         if (weight <= aWeightedItem.getWeight()) {
/* 87 */           itemList.add((T)aWeightedItem);
/* 88 */           maxWeight -= aWeightedItem.getWeight();
/* 89 */           it.remove();
/*    */           
/*    */           break;
/*    */         } 
/* 93 */         weight -= aWeightedItem.getWeight();
/*    */       } 
/*    */     } 
/*    */     
/* 97 */     return itemsCopy;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\collections\WeightedSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */