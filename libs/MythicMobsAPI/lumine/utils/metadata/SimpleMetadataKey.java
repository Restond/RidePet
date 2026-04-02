/*    */ package lumine.utils.metadata;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import io.lumine.utils.metadata.MetadataKey;
/*    */ 
/*    */ final class SimpleMetadataKey<T> implements MetadataKey<T> {
/*    */   private final String id;
/*    */   private final TypeToken<T> type;
/*    */   
/*    */   SimpleMetadataKey(String id, TypeToken<T> type) {
/* 12 */     this.id = id.toLowerCase();
/* 13 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getId() {
/* 18 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeToken<T> getType() {
/* 23 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public T cast(Object object) throws ClassCastException {
/* 28 */     Preconditions.checkNotNull(object, "object");
/*    */     
/* 30 */     return this.type.getRawType().cast(object);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 35 */     return (obj instanceof io.lumine.utils.metadata.SimpleMetadataKey && ((io.lumine.utils.metadata.SimpleMetadataKey)obj).getId().equals(this.id));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 40 */     return this.id.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\SimpleMetadataKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */