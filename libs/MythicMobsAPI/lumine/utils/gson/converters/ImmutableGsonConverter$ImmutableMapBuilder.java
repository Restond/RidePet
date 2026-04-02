/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
/*    */ import io.lumine.utils.gson.converters.ImmutableGsonConverter;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
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
/*    */ final class ImmutableMapBuilder<K, V>
/*    */   implements AbstractGsonConverter.MapBuilder<ImmutableMap<K, V>, K, V>
/*    */ {
/* 60 */   private final ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
/*    */ 
/*    */   
/*    */   public void put(@Nullable K key, @Nullable V value) {
/* 64 */     if (key == null || value == null) {
/*    */       return;
/*    */     }
/* 67 */     this.builder.put(key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImmutableMap<K, V> build() {
/* 72 */     return this.builder.build();
/*    */   }
/*    */   
/*    */   private ImmutableMapBuilder() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\ImmutableGsonConverter$ImmutableMapBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */