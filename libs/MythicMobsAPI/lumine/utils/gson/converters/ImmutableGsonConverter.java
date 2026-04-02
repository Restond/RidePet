/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import io.lumine.utils.annotation.NonnullByDefault;
/*    */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
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
/*    */ class ImmutableGsonConverter
/*    */   extends AbstractGsonConverter<ImmutableMap<String, Object>, ImmutableList<Object>, ImmutableSet<Object>>
/*    */ {
/* 38 */   public static final io.lumine.utils.gson.converters.ImmutableGsonConverter INSTANCE = new io.lumine.utils.gson.converters.ImmutableGsonConverter();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractGsonConverter.MapBuilder<ImmutableMap<String, Object>, String, Object> newMapBuilder() {
/* 46 */     return (AbstractGsonConverter.MapBuilder<ImmutableMap<String, Object>, String, Object>)new ImmutableMapBuilder(null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractGsonConverter.ListBuilder<ImmutableList<Object>, Object> newListBuilder() {
/* 51 */     return (AbstractGsonConverter.ListBuilder<ImmutableList<Object>, Object>)new ImmutableListBuilder(null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractGsonConverter.SetBuilder<ImmutableSet<Object>, Object> newSetBuilder() {
/* 56 */     return (AbstractGsonConverter.SetBuilder<ImmutableSet<Object>, Object>)new ImmutableSetBuilder(null);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\ImmutableGsonConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */