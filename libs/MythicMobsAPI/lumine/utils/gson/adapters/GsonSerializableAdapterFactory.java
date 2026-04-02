/*    */ package lumine.utils.gson.adapters;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import io.lumine.utils.gson.GsonSerializable;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class GsonSerializableAdapterFactory
/*    */   implements TypeAdapterFactory
/*    */ {
/* 18 */   public static final io.lumine.utils.gson.adapters.GsonSerializableAdapterFactory INSTANCE = new io.lumine.utils.gson.adapters.GsonSerializableAdapterFactory();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
/* 25 */     Class<? super T> clazz = type.getRawType();
/*    */ 
/*    */     
/* 28 */     Method deserializeMethod = GsonSerializable.getDeserializeMethod(clazz);
/* 29 */     if (deserializeMethod == null) {
/* 30 */       return null;
/*    */     }
/*    */     
/* 33 */     return (TypeAdapter<T>)new Object(this, gson, deserializeMethod);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\adapters\GsonSerializableAdapterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */