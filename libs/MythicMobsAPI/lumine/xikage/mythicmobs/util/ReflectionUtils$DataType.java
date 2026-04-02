/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.util.ReflectionUtils;
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
/*     */ public enum DataType
/*     */ {
/* 152 */   BYTE(byte.class, Byte.class),
/* 153 */   SHORT(short.class, Short.class),
/* 154 */   INTEGER(int.class, Integer.class),
/* 155 */   LONG(long.class, Long.class),
/* 156 */   CHARACTER(char.class, Character.class),
/* 157 */   FLOAT(float.class, Float.class),
/* 158 */   DOUBLE(double.class, Double.class),
/* 159 */   BOOLEAN(boolean.class, Boolean.class); private static final Map<Class<?>, DataType> CLASS_MAP;
/*     */   static {
/* 161 */     CLASS_MAP = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     for (DataType type : values()) {
/* 168 */       CLASS_MAP.put(type.primitive, type);
/* 169 */       CLASS_MAP.put(type.reference, type);
/*     */     } 
/*     */   }
/*     */   private final Class<?> primitive; private final Class<?> reference;
/*     */   DataType(Class<?> primitive, Class<?> reference) {
/* 174 */     this.primitive = primitive;
/* 175 */     this.reference = reference;
/*     */   }
/*     */   
/*     */   public Class<?> getPrimitive() {
/* 179 */     return this.primitive;
/*     */   }
/*     */   
/*     */   public Class<?> getReference() {
/* 183 */     return this.reference;
/*     */   }
/*     */   
/*     */   public static DataType fromClass(Class<?> clazz) {
/* 187 */     return CLASS_MAP.get(clazz);
/*     */   }
/*     */   
/*     */   public static Class<?> getPrimitive(Class<?> clazz) {
/* 191 */     DataType type = fromClass(clazz);
/* 192 */     return (type == null) ? clazz : type.getPrimitive();
/*     */   }
/*     */   
/*     */   public static Class<?> getReference(Class<?> clazz) {
/* 196 */     DataType type = fromClass(clazz);
/* 197 */     return (type == null) ? clazz : type.getReference();
/*     */   }
/*     */   
/*     */   public static Class<?>[] getPrimitive(Class<?>[] classes) {
/* 201 */     int length = (classes == null) ? 0 : classes.length;
/* 202 */     Class<?>[] types = new Class[length];
/* 203 */     for (int index = 0; index < length; index++) {
/* 204 */       types[index] = getPrimitive(classes[index]);
/*     */     }
/* 206 */     return types;
/*     */   }
/*     */   
/*     */   public static Class<?>[] getReference(Class<?>[] classes) {
/* 210 */     int length = (classes == null) ? 0 : classes.length;
/* 211 */     Class<?>[] types = new Class[length];
/* 212 */     for (int index = 0; index < length; index++) {
/* 213 */       types[index] = getReference(classes[index]);
/*     */     }
/* 215 */     return types;
/*     */   }
/*     */   
/*     */   public static Class<?>[] getPrimitive(Object[] objects) {
/* 219 */     int length = (objects == null) ? 0 : objects.length;
/* 220 */     Class<?>[] types = new Class[length];
/* 221 */     for (int index = 0; index < length; index++) {
/* 222 */       types[index] = getPrimitive(objects[index].getClass());
/*     */     }
/* 224 */     return types;
/*     */   }
/*     */   
/*     */   public static Class<?>[] getReference(Object[] objects) {
/* 228 */     int length = (objects == null) ? 0 : objects.length;
/* 229 */     Class<?>[] types = new Class[length];
/* 230 */     for (int index = 0; index < length; index++) {
/* 231 */       types[index] = getReference(objects[index].getClass());
/*     */     }
/* 233 */     return types;
/*     */   }
/*     */   
/*     */   public static boolean compare(Class<?>[] primary, Class<?>[] secondary) {
/* 237 */     if (primary == null || secondary == null || primary.length != secondary.length) {
/* 238 */       return false;
/*     */     }
/* 240 */     for (int index = 0; index < primary.length; ) {
/* 241 */       Class<?> primaryClass = primary[index];
/* 242 */       Class<?> secondaryClass = secondary[index];
/* 243 */       if (primaryClass.equals(secondaryClass) || primaryClass.isAssignableFrom(secondaryClass)) {
/*     */         index++; continue;
/*     */       } 
/* 246 */       return false;
/*     */     } 
/* 248 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\ReflectionUtils$DataType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */