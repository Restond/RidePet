/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
/*    */ import io.lumine.utils.gson.converters.MutableGsonConverter;
/*    */ import java.util.HashMap;
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
/*    */ final class MutableMapBuilder<K, V>
/*    */   implements AbstractGsonConverter.MapBuilder<HashMap<K, V>, K, V>
/*    */ {
/* 60 */   private final HashMap<K, V> builder = new HashMap<>();
/*    */ 
/*    */   
/*    */   public void put(@Nullable K key, @Nullable V value) {
/* 64 */     this.builder.put(key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashMap<K, V> build() {
/* 69 */     return this.builder;
/*    */   }
/*    */   
/*    */   private MutableMapBuilder() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\MutableGsonConverter$MutableMapBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */