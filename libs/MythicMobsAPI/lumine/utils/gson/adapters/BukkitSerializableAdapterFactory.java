/*    */ package lumine.utils.gson.adapters;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
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
/*    */ public final class BukkitSerializableAdapterFactory
/*    */   implements TypeAdapterFactory
/*    */ {
/* 43 */   public static final io.lumine.utils.gson.adapters.BukkitSerializableAdapterFactory INSTANCE = new io.lumine.utils.gson.adapters.BukkitSerializableAdapterFactory();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
/* 50 */     Class<? super T> clazz = type.getRawType();
/*    */     
/* 52 */     if (!ConfigurationSerializable.class.isAssignableFrom(clazz)) {
/* 53 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 57 */     return (TypeAdapter<T>)new Adapter(gson, null);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\adapters\BukkitSerializableAdapterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */