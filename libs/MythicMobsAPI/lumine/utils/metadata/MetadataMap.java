/*    */ package lumine.utils.metadata;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import io.lumine.utils.metadata.MetadataKey;
/*    */ import io.lumine.utils.metadata.SimpleMetadataMap;
/*    */ import io.lumine.utils.metadata.TransientValue;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface MetadataMap
/*    */ {
/*    */   static io.lumine.utils.metadata.MetadataMap create() {
/* 19 */     return (io.lumine.utils.metadata.MetadataMap)new SimpleMetadataMap();
/*    */   }
/*    */   
/*    */   <T> void put(MetadataKey<T> paramMetadataKey, T paramT);
/*    */   
/*    */   <T> void put(MetadataKey<T> paramMetadataKey, TransientValue<T> paramTransientValue);
/*    */   
/*    */   <T> void forcePut(MetadataKey<T> paramMetadataKey, T paramT);
/*    */   
/*    */   <T> void forcePut(MetadataKey<T> paramMetadataKey, TransientValue<T> paramTransientValue);
/*    */   
/*    */   <T> boolean putIfAbsent(MetadataKey<T> paramMetadataKey, T paramT);
/*    */   
/*    */   <T> boolean putIfAbsent(MetadataKey<T> paramMetadataKey, TransientValue<T> paramTransientValue);
/*    */   
/*    */   <T> Optional<T> get(MetadataKey<T> paramMetadataKey);
/*    */   
/*    */   <T> T getOrNull(MetadataKey<T> paramMetadataKey);
/*    */   
/*    */   <T> T getOrDefault(MetadataKey<T> paramMetadataKey, T paramT);
/*    */   
/*    */   <T> T getOrPut(MetadataKey<T> paramMetadataKey, Supplier<T> paramSupplier);
/*    */   
/*    */   <T> T getOrPutExpiring(MetadataKey<T> paramMetadataKey, Supplier<TransientValue<T>> paramSupplier);
/*    */   
/*    */   boolean has(MetadataKey<?> paramMetadataKey);
/*    */   
/*    */   boolean remove(MetadataKey<?> paramMetadataKey);
/*    */   
/*    */   void clear();
/*    */   
/*    */   ImmutableMap<MetadataKey<?>, Object> asMap();
/*    */   
/*    */   boolean isEmpty();
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\MetadataMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */