/*     */ package lumine.utils.reflection;
/*     */ 
/*     */ import io.lumine.utils.reflection.Reflector;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum DataType
/*     */ {
/* 190 */   BYTE(byte.class, Byte.class),
/* 191 */   SHORT(short.class, Short.class),
/* 192 */   INTEGER(int.class, Integer.class),
/* 193 */   LONG(long.class, Long.class),
/* 194 */   CHARACTER(char.class, Character.class),
/* 195 */   FLOAT(float.class, Float.class),
/* 196 */   DOUBLE(double.class, Double.class),
/* 197 */   BOOLEAN(boolean.class, Boolean.class); private static final Map<Class<?>, DataType> CLASS_MAP;
/*     */   static {
/* 199 */     CLASS_MAP = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     for (DataType type : values()) {
/* 206 */       CLASS_MAP.put(type.primitive, type);
/* 207 */       CLASS_MAP.put(type.reference, type);
/*     */     } 
/*     */   }
/*     */   private final Class<?> primitive; private final Class<?> reference;
/*     */   DataType(Class<?> primitive, Class<?> reference) {
/* 212 */     this.primitive = primitive;
/* 213 */     this.reference = reference;
/*     */   }
/*     */   
/*     */   public Class<?> getPrimitive() {
/* 217 */     return this.primitive;
/*     */   }
/*     */   
/*     */   public Class<?> getReference() {
/* 221 */     return this.reference;
/*     */   }
/*     */   
/*     */   public static DataType fromClass(Class<?> clazz) {
/* 225 */     return CLASS_MAP.get(clazz);
/*     */   }
/*     */   
/*     */   public static Class<?> getPrimitive(Class<?> clazz) {
/* 229 */     DataType type = fromClass(clazz);
/* 230 */     return (type == null) ? clazz : type.getPrimitive();
/*     */   }
/*     */   
/*     */   public static Class<?> getReference(Class<?> clazz) {
/* 234 */     DataType type = fromClass(clazz);
/* 235 */     return (type == null) ? clazz : type.getReference();
/*     */   }
/*     */   
/*     */   public static Class<?>[] getPrimitive(Class<?>[] classes) {
/* 239 */     int length = (classes == null) ? 0 : classes.length;
/* 240 */     Class<?>[] types = new Class[length];
/* 241 */     for (int index = 0; index < length; index++) {
/* 242 */       types[index] = getPrimitive(classes[index]);
/*     */     }
/* 244 */     return types;
/*     */   }
/*     */   
/*     */   public static Class<?>[] getReference(Class<?>[] classes) {
/* 248 */     int length = (classes == null) ? 0 : classes.length;
/* 249 */     Class<?>[] types = new Class[length];
/* 250 */     for (int index = 0; index < length; index++) {
/* 251 */       types[index] = getReference(classes[index]);
/*     */     }
/* 253 */     return types;
/*     */   }
/*     */   
/*     */   public static Class<?>[] getPrimitive(Object[] objects) {
/* 257 */     int length = (objects == null) ? 0 : objects.length;
/* 258 */     Class<?>[] types = new Class[length];
/* 259 */     for (int index = 0; index < length; index++) {
/* 260 */       types[index] = getPrimitive(objects[index].getClass());
/*     */     }
/* 262 */     return types;
/*     */   }
/*     */   
/*     */   public static Class<?>[] getReference(Object[] objects) {
/* 266 */     int length = (objects == null) ? 0 : objects.length;
/* 267 */     Class<?>[] types = new Class[length];
/* 268 */     for (int index = 0; index < length; index++) {
/* 269 */       types[index] = getReference(objects[index].getClass());
/*     */     }
/* 271 */     return types;
/*     */   }
/*     */   
/*     */   public static boolean compare(Class<?>[] primary, Class<?>[] secondary) {
/* 275 */     if (primary == null || secondary == null || primary.length != secondary.length) {
/* 276 */       return false;
/*     */     }
/* 278 */     for (int index = 0; index < primary.length; ) {
/* 279 */       Class<?> primaryClass = primary[index];
/* 280 */       Class<?> secondaryClass = secondary[index];
/* 281 */       if (primaryClass.equals(secondaryClass) || primaryClass.isAssignableFrom(secondaryClass)) {
/*     */         index++; continue;
/*     */       } 
/* 284 */       return false;
/*     */     } 
/* 286 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\reflection\Reflector$DataType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */