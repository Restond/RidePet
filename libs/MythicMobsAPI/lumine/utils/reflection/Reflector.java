/*     */ package lumine.utils.reflection;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
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
/*     */ public class Reflector<E>
/*     */ {
/*  20 */   Map<String, Field> fields = new HashMap<>();
/*     */   
/*     */   public Reflector(Class<? extends E> type, String... fields) {
/*  23 */     for (String fieldName : fields) {
/*     */       try {
/*  25 */         Field field = type.getDeclaredField(fieldName);
/*  26 */         field.setAccessible(true);
/*  27 */         this.fields.put(fieldName, field);
/*  28 */       } catch (NoSuchFieldException e) {
/*  29 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getInt(E object, String field) {
/*     */     try {
/*  36 */       return ((Field)this.fields.get(field)).getInt(object);
/*  37 */     } catch (IllegalAccessException e) {
/*  38 */       e.printStackTrace();
/*  39 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setInt(E object, String field, int val) {
/*     */     try {
/*  45 */       ((Field)this.fields.get(field)).setInt(object, val);
/*  46 */     } catch (IllegalAccessException e) {
/*  47 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte getByte(E object, String field) {
/*     */     try {
/*  53 */       return ((Field)this.fields.get(field)).getByte(object);
/*  54 */     } catch (IllegalAccessException e) {
/*  55 */       e.printStackTrace();
/*  56 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setByte(E object, String field, byte val) {
/*     */     try {
/*  62 */       ((Field)this.fields.get(field)).setByte(object, val);
/*  63 */     } catch (IllegalAccessException e) {
/*  64 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getString(E object, String field) {
/*     */     try {
/*  70 */       return (String)((Field)this.fields.get(field)).get(object);
/*  71 */     } catch (IllegalAccessException e) {
/*  72 */       e.printStackTrace();
/*  73 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setString(E object, String field, String val) {
/*     */     try {
/*  79 */       ((Field)this.fields.get(field)).set(object, val);
/*  80 */     } catch (IllegalAccessException e) {
/*  81 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(E object, String field) {
/*     */     try {
/*  87 */       return ((Field)this.fields.get(field)).get(object);
/*  88 */     } catch (IllegalAccessException e) {
/*  89 */       e.printStackTrace();
/*  90 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(E object, String field, Object val) {
/*     */     try {
/*  96 */       ((Field)this.fields.get(field)).set(object, val);
/*  97 */     } catch (IllegalAccessException e) {
/*  98 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
/* 106 */     Class<?>[] primitiveTypes = DataType.getPrimitive(parameterTypes); Constructor[] arrayOfConstructor; int i; byte b;
/* 107 */     for (arrayOfConstructor = (Constructor[])clazz.getConstructors(), i = arrayOfConstructor.length, b = 0; b < i; ) { Constructor<?> constructor = arrayOfConstructor[b];
/* 108 */       if (!DataType.compare(DataType.getPrimitive(constructor.getParameterTypes()), primitiveTypes)) {
/*     */         b++; continue;
/*     */       } 
/* 111 */       return constructor; }
/*     */     
/* 113 */     throw new NoSuchMethodException("There is no such constructor in this class with the specified parameter types");
/*     */   }
/*     */   
/*     */   public static Constructor<?> getConstructor(String className, PackageType packageType, Class<?>... parameterTypes) throws NoSuchMethodException, ClassNotFoundException {
/* 117 */     return getConstructor(packageType.getClass(className), parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object instantiateObject(Class<?> clazz, Object... arguments) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
/* 121 */     return getConstructor(clazz, DataType.getPrimitive(arguments)).newInstance(arguments);
/*     */   }
/*     */   
/*     */   public static Object instantiateObject(String className, PackageType packageType, Object... arguments) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
/* 125 */     return instantiateObject(packageType.getClass(className), arguments);
/*     */   }
/*     */   
/*     */   public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
/* 129 */     Class<?>[] primitiveTypes = DataType.getPrimitive(parameterTypes); Method[] arrayOfMethod; int i; byte b;
/* 130 */     for (arrayOfMethod = clazz.getMethods(), i = arrayOfMethod.length, b = 0; b < i; ) { Method method = arrayOfMethod[b];
/* 131 */       if (!method.getName().equals(methodName) || !DataType.compare(DataType.getPrimitive(method.getParameterTypes()), primitiveTypes)) {
/*     */         b++; continue;
/*     */       } 
/* 134 */       return method; }
/*     */     
/* 136 */     throw new NoSuchMethodException("There is no such method in this class with the specified name and parameter types");
/*     */   }
/*     */   
/*     */   public static Method getMethod(String className, PackageType packageType, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, ClassNotFoundException {
/* 140 */     return getMethod(packageType.getClass(className), methodName, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeMethod(Object instance, String methodName, Object... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
/* 144 */     return getMethod(instance.getClass(), methodName, DataType.getPrimitive(arguments)).invoke(instance, arguments);
/*     */   }
/*     */   
/*     */   public static Object invokeMethod(Object instance, Class<?> clazz, String methodName, Object... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
/* 148 */     return getMethod(clazz, methodName, DataType.getPrimitive(arguments)).invoke(instance, arguments);
/*     */   }
/*     */   
/*     */   public static Object invokeMethod(Object instance, String className, PackageType packageType, String methodName, Object... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
/* 152 */     return invokeMethod(instance, packageType.getClass(className), methodName, arguments);
/*     */   }
/*     */   
/*     */   public static Field getField(Class<?> clazz, boolean declared, String fieldName) throws NoSuchFieldException, SecurityException {
/* 156 */     Field field = declared ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
/* 157 */     field.setAccessible(true);
/* 158 */     return field;
/*     */   }
/*     */   
/*     */   public static Field getField(String className, PackageType packageType, boolean declared, String fieldName) throws NoSuchFieldException, SecurityException, ClassNotFoundException {
/* 162 */     return getField(packageType.getClass(className), declared, fieldName);
/*     */   }
/*     */   
/*     */   public static Object getValue(Object instance, Class<?> clazz, boolean declared, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 166 */     return getField(clazz, declared, fieldName).get(instance);
/*     */   }
/*     */   
/*     */   public static Object getValue(Object instance, String className, PackageType packageType, boolean declared, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
/* 170 */     return getValue(instance, packageType.getClass(className), declared, fieldName);
/*     */   }
/*     */   
/*     */   public static Object getValue(Object instance, boolean declared, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 174 */     return getValue(instance, instance.getClass(), declared, fieldName);
/*     */   }
/*     */   
/*     */   public static void setValue(Object instance, Class<?> clazz, boolean declared, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 178 */     getField(clazz, declared, fieldName).set(instance, value);
/*     */   }
/*     */   
/*     */   public static void setValue(Object instance, String className, PackageType packageType, boolean declared, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
/* 182 */     setValue(instance, packageType.getClass(className), declared, fieldName, value);
/*     */   }
/*     */   
/*     */   public static void setValue(Object instance, boolean declared, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 186 */     setValue(instance, instance.getClass(), declared, fieldName, value);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\reflection\Reflector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */