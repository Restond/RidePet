/*    */ package lumine.utils.metadata;
/*    */ 
/*    */ import com.google.common.cache.CacheLoader;
/*    */ import io.lumine.utils.metadata.Metadata;
/*    */ import io.lumine.utils.metadata.MetadataMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class null
/*    */   extends CacheLoader<T, MetadataMap>
/*    */ {
/*    */   public MetadataMap load(T uuid) {
/* 67 */     return MetadataMap.create();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\Metadata$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */