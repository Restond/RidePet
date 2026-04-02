/*     */ package lumine.utils.gson;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface GsonSerializable
/*     */ {
/*     */   @Nonnull
/*     */   static <T extends io.lumine.utils.gson.GsonSerializable> T deserialize(@Nonnull Class<T> clazz, @Nonnull JsonElement element) {
/*  56 */     Method deserializeMethod = getDeserializeMethod(clazz);
/*  57 */     if (deserializeMethod == null) {
/*  58 */       throw new IllegalStateException("Class does not have a deserialize method accessible.");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  63 */       return (T)deserializeMethod.invoke(null, new Object[] { element });
/*  64 */     } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
/*  65 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   static io.lumine.utils.gson.GsonSerializable deserializeRaw(@Nonnull Class<?> clazz, @Nonnull JsonElement element) {
/*  79 */     Class<? extends io.lumine.utils.gson.GsonSerializable> typeCastedClass = clazz.asSubclass(io.lumine.utils.gson.GsonSerializable.class);
/*  80 */     return deserialize((Class)typeCastedClass, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   static Method getDeserializeMethod(@Nonnull Class<?> clazz) {
/*     */     Method deserializeMethod;
/*  91 */     if (!io.lumine.utils.gson.GsonSerializable.class.isAssignableFrom(clazz)) {
/*  92 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  98 */       deserializeMethod = clazz.getDeclaredMethod("deserialize", new Class[] { JsonElement.class });
/*  99 */       deserializeMethod.setAccessible(true);
/* 100 */     } catch (Exception e) {
/* 101 */       return null;
/*     */     } 
/*     */     
/* 104 */     if (!Modifier.isStatic(deserializeMethod.getModifiers())) {
/* 105 */       return null;
/*     */     }
/*     */     
/* 108 */     return deserializeMethod;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   JsonElement serialize();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\GsonSerializable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */