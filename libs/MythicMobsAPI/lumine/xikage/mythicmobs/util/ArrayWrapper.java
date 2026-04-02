/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.lang.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ArrayWrapper<E>
/*     */ {
/*     */   private E[] _array;
/*     */   
/*     */   public ArrayWrapper(E... elements) {
/*  26 */     setArray(elements);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E[] getArray() {
/*  36 */     return this._array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArray(E[] array) {
/*  44 */     Validate.notNull(array, "The array must not be null.");
/*  45 */     this._array = array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/*  56 */     if (!(other instanceof io.lumine.xikage.mythicmobs.util.ArrayWrapper))
/*     */     {
/*  58 */       return false;
/*     */     }
/*  60 */     return Arrays.equals((Object[])this._array, (Object[])((io.lumine.xikage.mythicmobs.util.ArrayWrapper)other)._array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  71 */     return Arrays.hashCode((Object[])this._array);
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
/*     */   public static <T> T[] toArray(Iterable<? extends T> list, Class<T> c) {
/*  83 */     int size = -1;
/*  84 */     if (list instanceof Collection) {
/*     */       
/*  86 */       Collection coll = (Collection)list;
/*  87 */       size = coll.size();
/*     */     } 
/*     */ 
/*     */     
/*  91 */     if (size < 0) {
/*  92 */       size = 0;
/*     */       
/*  94 */       for (T element : list) {
/*  95 */         size++;
/*     */       }
/*     */     } 
/*     */     
/*  99 */     T[] result = (T[])Array.newInstance(c, size);
/* 100 */     int i = 0;
/* 101 */     for (T element : list) {
/* 102 */       result[i++] = element;
/*     */     }
/* 104 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\ArrayWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */