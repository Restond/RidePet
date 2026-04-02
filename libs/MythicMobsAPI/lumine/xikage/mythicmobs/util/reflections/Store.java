/*    */ package lumine.xikage.mythicmobs.util.reflections;
/*    */ 
/*    */ import com.google.common.base.Supplier;
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.google.common.collect.Multimaps;
/*    */ import com.google.common.collect.SetMultimap;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.Configuration;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.ReflectionsException;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Store
/*    */ {
/*    */   private transient boolean concurrent;
/*    */   private final Map<String, Multimap<String, String>> storeMap;
/*    */   
/*    */   protected Store() {
/* 23 */     this.storeMap = new HashMap<>();
/* 24 */     this.concurrent = false;
/*    */   }
/*    */   
/*    */   public Store(Configuration configuration) {
/* 28 */     this.storeMap = new HashMap<>();
/* 29 */     this.concurrent = (configuration.getExecutorService() != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> keySet() {
/* 34 */     return this.storeMap.keySet();
/*    */   }
/*    */   
/*    */   public Multimap<String, String> getOrCreate(String index) {
/*    */     SetMultimap setMultimap;
/* 39 */     Multimap<String, String> mmap = this.storeMap.get(index);
/* 40 */     if (mmap == null) {
/*    */       
/* 42 */       SetMultimap<String, String> multimap = Multimaps.newSetMultimap(new HashMap<>(), (Supplier)new Object(this));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 48 */       setMultimap = this.concurrent ? Multimaps.synchronizedSetMultimap(multimap) : multimap;
/* 49 */       this.storeMap.put(index, setMultimap);
/*    */     } 
/* 51 */     return (Multimap<String, String>)setMultimap;
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<String, String> get(String index) {
/* 56 */     Multimap<String, String> mmap = this.storeMap.get(index);
/* 57 */     if (mmap == null) {
/* 58 */       throw new ReflectionsException("Scanner " + index + " was not configured");
/*    */     }
/* 60 */     return mmap;
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterable<String> get(String index, String... keys) {
/* 65 */     return get(index, Arrays.asList(keys));
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterable<String> get(String index, Iterable<String> keys) {
/* 70 */     Multimap<String, String> mmap = get(index);
/* 71 */     IterableChain<String> result = new IterableChain(null);
/* 72 */     for (String key : keys) {
/* 73 */       IterableChain.access$100(result, mmap.get(key));
/*    */     }
/* 75 */     return (Iterable<String>)result;
/*    */   }
/*    */ 
/*    */   
/*    */   private Iterable<String> getAllIncluding(String index, Iterable<String> keys, IterableChain<String> result) {
/* 80 */     IterableChain.access$100(result, keys);
/* 81 */     for (String key : keys) {
/* 82 */       Iterable<String> values = get(index, new String[] { key });
/* 83 */       if (values.iterator().hasNext()) {
/* 84 */         getAllIncluding(index, values, result);
/*    */       }
/*    */     } 
/* 87 */     return (Iterable<String>)result;
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterable<String> getAll(String index, String key) {
/* 92 */     return getAllIncluding(index, get(index, new String[] { key }), new IterableChain(null));
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterable<String> getAll(String index, Iterable<String> keys) {
/* 97 */     return getAllIncluding(index, get(index, keys), new IterableChain(null));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\Store.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */