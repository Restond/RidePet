/*    */ package lumine.utils.metadata;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import io.lumine.utils.Cooldown;
/*    */ import io.lumine.utils.metadata.Empty;
/*    */ import io.lumine.utils.metadata.SimpleMetadataKey;
/*    */ import java.util.UUID;
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
/*    */ public interface MetadataKey<T>
/*    */ {
/*    */   static <T> io.lumine.utils.metadata.MetadataKey<T> create(String id, TypeToken<T> type) {
/* 27 */     Preconditions.checkNotNull(id, "id");
/* 28 */     Preconditions.checkNotNull(type, "type");
/* 29 */     return (io.lumine.utils.metadata.MetadataKey<T>)new SimpleMetadataKey(id, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static <T> io.lumine.utils.metadata.MetadataKey<T> create(String id, Class<T> clazz) {
/* 41 */     return create(id, TypeToken.of(clazz));
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Empty> createEmptyKey(String id) {
/* 45 */     return create(id, Empty.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<String> createStringKey(String id) {
/* 49 */     return create(id, String.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Boolean> createBooleanKey(String id) {
/* 53 */     return create(id, Boolean.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Integer> createIntegerKey(String id) {
/* 57 */     return create(id, Integer.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Long> createLongKey(String id) {
/* 61 */     return create(id, Long.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Double> createDoubleKey(String id) {
/* 65 */     return create(id, Double.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Float> createFloatKey(String id) {
/* 69 */     return create(id, Float.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Short> createShortKey(String id) {
/* 73 */     return create(id, Short.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Character> createCharacterKey(String id) {
/* 77 */     return create(id, Character.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<Cooldown> createCooldownKey(String id) {
/* 81 */     return create(id, Cooldown.class);
/*    */   }
/*    */   
/*    */   static io.lumine.utils.metadata.MetadataKey<UUID> createUuidKey(String id) {
/* 85 */     return create(id, UUID.class);
/*    */   }
/*    */   
/*    */   String getId();
/*    */   
/*    */   TypeToken<T> getType();
/*    */   
/*    */   T cast(Object paramObject) throws ClassCastException;
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\MetadataKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */