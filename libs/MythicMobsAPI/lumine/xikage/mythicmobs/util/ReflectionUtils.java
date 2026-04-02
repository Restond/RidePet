/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ReflectionUtils
/*    */ {
/*    */   public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
/* 17 */     Class<?>[] primitiveTypes = DataType.getPrimitive(parameterTypes); Constructor[] arrayOfConstructor; int i; byte b;
/* 18 */     for (arrayOfConstructor = (Constructor[])clazz.getConstructors(), i = arrayOfConstructor.length, b = 0; b < i; ) { Constructor<?> constructor = arrayOfConstructor[b];
/* 19 */       if (!DataType.compare(DataType.getPrimitive(constructor.getParameterTypes()), primitiveTypes)) {
/*    */         b++; continue;
/*    */       } 
/* 22 */       return constructor; }
/*    */     
/* 24 */     throw new NoSuchMethodException("There is no such constructor in this class with the specified parameter types");
/*    */   }
/*    */   
/*    */   public static Constructor<?> getConstructor(String className, PackageType packageType, Class<?>... parameterTypes) throws NoSuchMethodException, ClassNotFoundException {
/* 28 */     return getConstructor(packageType.getClass(className), parameterTypes);
/*    */   }
/*    */   
/*    */   public static Object instantiateObject(Class<?> clazz, Object... arguments) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
/* 32 */     return getConstructor(clazz, DataType.getPrimitive(arguments)).newInstance(arguments);
/*    */   }
/*    */   
/*    */   public static Object instantiateObject(String className, PackageType packageType, Object... arguments) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
/* 36 */     return instantiateObject(packageType.getClass(className), arguments);
/*    */   }
/*    */   
/*    */   public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
/* 40 */     Class<?>[] primitiveTypes = DataType.getPrimitive(parameterTypes); Method[] arrayOfMethod; int i; byte b;
/* 41 */     for (arrayOfMethod = clazz.getMethods(), i = arrayOfMethod.length, b = 0; b < i; ) { Method method = arrayOfMethod[b];
/* 42 */       if (!method.getName().equals(methodName) || !DataType.compare(DataType.getPrimitive(method.getParameterTypes()), primitiveTypes)) {
/*    */         b++; continue;
/*    */       } 
/* 45 */       return method; }
/*    */     
/* 47 */     throw new NoSuchMethodException("There is no such method in this class with the specified name and parameter types");
/*    */   }
/*    */   
/*    */   public static Method getMethod(String className, PackageType packageType, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, ClassNotFoundException {
/* 51 */     return getMethod(packageType.getClass(className), methodName, parameterTypes);
/*    */   }
/*    */   
/*    */   public static Object invokeMethod(Object instance, String methodName, Object... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
/* 55 */     return getMethod(instance.getClass(), methodName, DataType.getPrimitive(arguments)).invoke(instance, arguments);
/*    */   }
/*    */   
/*    */   public static Object invokeMethod(Object instance, Class<?> clazz, String methodName, Object... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
/* 59 */     return getMethod(clazz, methodName, DataType.getPrimitive(arguments)).invoke(instance, arguments);
/*    */   }
/*    */   
/*    */   public static Object invokeMethod(Object instance, String className, PackageType packageType, String methodName, Object... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
/* 63 */     return invokeMethod(instance, packageType.getClass(className), methodName, arguments);
/*    */   }
/*    */   
/*    */   public static Field getField(Class<?> clazz, boolean declared, String fieldName) throws NoSuchFieldException, SecurityException {
/* 67 */     Field field = declared ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
/* 68 */     field.setAccessible(true);
/* 69 */     return field;
/*    */   }
/*    */   
/*    */   public static Field getField(String className, PackageType packageType, boolean declared, String fieldName) throws NoSuchFieldException, SecurityException, ClassNotFoundException {
/* 73 */     return getField(packageType.getClass(className), declared, fieldName);
/*    */   }
/*    */   
/*    */   public static Object getValue(Object instance, Class<?> clazz, boolean declared, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 77 */     return getField(clazz, declared, fieldName).get(instance);
/*    */   }
/*    */   
/*    */   public static Object getValue(Object instance, String className, PackageType packageType, boolean declared, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
/* 81 */     return getValue(instance, packageType.getClass(className), declared, fieldName);
/*    */   }
/*    */   
/*    */   public static Object getValue(Object instance, boolean declared, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 85 */     return getValue(instance, instance.getClass(), declared, fieldName);
/*    */   }
/*    */   
/*    */   public static void setValue(Object instance, Class<?> clazz, boolean declared, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 89 */     getField(clazz, declared, fieldName).set(instance, value);
/*    */   }
/*    */   
/*    */   public static void setValue(Object instance, String className, PackageType packageType, boolean declared, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
/* 93 */     setValue(instance, packageType.getClass(className), declared, fieldName, value);
/*    */   }
/*    */   
/*    */   public static void setValue(Object instance, boolean declared, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 97 */     setValue(instance, instance.getClass(), declared, fieldName, value);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\ReflectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */