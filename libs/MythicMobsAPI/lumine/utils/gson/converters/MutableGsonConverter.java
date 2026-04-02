/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import io.lumine.utils.annotation.NonnullByDefault;
/*    */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
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
/*    */ @NonnullByDefault
/*    */ class MutableGsonConverter
/*    */   extends AbstractGsonConverter<HashMap<String, Object>, ArrayList<Object>, HashSet<Object>>
/*    */ {
/* 38 */   public static final io.lumine.utils.gson.converters.MutableGsonConverter INSTANCE = new io.lumine.utils.gson.converters.MutableGsonConverter();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractGsonConverter.MapBuilder<HashMap<String, Object>, String, Object> newMapBuilder() {
/* 46 */     return (AbstractGsonConverter.MapBuilder<HashMap<String, Object>, String, Object>)new MutableMapBuilder(null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractGsonConverter.ListBuilder<ArrayList<Object>, Object> newListBuilder() {
/* 51 */     return (AbstractGsonConverter.ListBuilder<ArrayList<Object>, Object>)new MutableListBuilder(null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractGsonConverter.SetBuilder<HashSet<Object>, Object> newSetBuilder() {
/* 56 */     return (AbstractGsonConverter.SetBuilder<HashSet<Object>, Object>)new MutableSetBuilder(null);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\MutableGsonConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */