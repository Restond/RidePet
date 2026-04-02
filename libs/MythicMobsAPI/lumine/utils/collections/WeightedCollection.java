/*     */ package lumine.utils.collections;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import io.lumine.utils.collections.AWeightedItem;
/*     */ import io.lumine.utils.collections.ISelector;
/*     */ import io.lumine.utils.collections.WeightedSelector;
/*     */ import io.lumine.utils.functions.IParamFunction;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class WeightedCollection<T extends AWeightedItem> {
/*  13 */   private List<T> items = Lists.newArrayList();
/*     */   
/*  15 */   private double weight = 0.0D;
/*     */   
/*  17 */   private ISelector<T> selector = (ISelector<T>)new WeightedSelector();
/*     */ 
/*     */ 
/*     */   
/*     */   public WeightedCollection(io.lumine.utils.collections.WeightedCollection<T> source) {
/*  22 */     this.items = source.getCopy();
/*  23 */     this.weight = source.weight;
/*     */   }
/*     */   
/*     */   public double getWeight() {
/*  27 */     return this.weight;
/*     */   }
/*     */   
/*     */   public double getMaxWeight() {
/*  31 */     return this.items.isEmpty() ? 0.0D : ((AWeightedItem)this.items.get(0)).weight;
/*     */   }
/*     */   
/*     */   public double getMinWeight() {
/*  35 */     return this.items.isEmpty() ? 0.0D : ((AWeightedItem)this.items.get(this.items.size())).weight;
/*     */   }
/*     */   
/*     */   public int size() {
/*  39 */     return this.items.size();
/*     */   }
/*     */   
/*     */   public boolean add(T item) {
/*  43 */     return ((Boolean)sortedAction(this.items::add, item)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends T> items) {
/*  47 */     return ((Boolean)sortedAction(this.items::addAll, items)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean remove(T item) {
/*  51 */     return ((Boolean)sortedAction(this.items::remove, item)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<T> items) {
/*  55 */     return ((Boolean)sortedAction(this.items::removeAll, items)).booleanValue();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  59 */     this.items.clear();
/*  60 */     this.weight = 0.0D;
/*     */   }
/*     */   
/*     */   private final <T1, T2> T1 sortedAction(IParamFunction<T1, T2> action, T2 param) {
/*     */     try {
/*  65 */       return (T1)action.execute(param);
/*     */     } finally {
/*  67 */       this.items.sort(Collections.reverseOrder());
/*  68 */       this.weight = this.items.stream().mapToDouble(d -> d.weight).sum();
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<T> getView() {
/*  73 */     return Collections.unmodifiableList(this.items);
/*     */   }
/*     */   
/*     */   public List<T> getCopy() {
/*  77 */     return Lists.newArrayList(this.items);
/*     */   }
/*     */   
/*     */   public io.lumine.utils.collections.WeightedCollection<T> copy() {
/*  81 */     return new io.lumine.utils.collections.WeightedCollection(this);
/*     */   }
/*     */   
/*     */   public T get() {
/*  85 */     return (T)this.selector.get(this);
/*     */   }
/*     */   
/*     */   public T get(double offset) {
/*  89 */     return (T)this.selector.get(this, offset);
/*     */   }
/*     */   
/*     */   public Collection<T> get(int count) {
/*  93 */     return this.selector.get(this, count);
/*     */   }
/*     */   
/*     */   public Collection<T> get(int count, double offset) {
/*  97 */     return this.selector.get(this, count, offset);
/*     */   }
/*     */   
/*     */   public Collection<T> getNonRepeating(int count) {
/* 101 */     return this.selector.getNonRepeating(this, count);
/*     */   }
/*     */   
/*     */   public Collection<T> getNonRepeating(int count, double offset) {
/* 105 */     return this.selector.getNonRepeating(this, count, offset);
/*     */   }
/*     */   
/*     */   public WeightedCollection() {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\collections\WeightedCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */