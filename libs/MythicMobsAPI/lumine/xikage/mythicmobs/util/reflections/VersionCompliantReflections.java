/*    */ package lumine.xikage.mythicmobs.util.reflections;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.google.common.collect.Sets;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.ReflectionUtils;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.Reflections;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.scanners.SubTypesScanner;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VersionCompliantReflections
/*    */   extends Reflections
/*    */ {
/* 19 */   private static final String SCANNER_CLASS_NAME = SubTypesScanner.class.getSimpleName();
/*    */   public VersionCompliantReflections(String configuration) {
/* 21 */     super(configuration, new io.lumine.xikage.mythicmobs.util.reflections.scanners.Scanner[0]);
/*    */   }
/*    */   
/*    */   public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
/* 25 */     Set<Class<? extends T>> toReturn = new LinkedHashSet<>();
/* 26 */     for (String s : getAllFromStore(type)) {
/*    */       try {
/* 28 */         toReturn.add(Class.forName(s));
/* 29 */       } catch (ClassNotFoundException e) {
/* 30 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 33 */     return toReturn;
/*    */   }
/*    */   
/*    */   private <T> Iterable<String> getAllFromStore(Class<T> type) {
/* 37 */     Multimap<String, String> mmap = this.store.get(SCANNER_CLASS_NAME);
/* 38 */     Set<String> result = new LinkedHashSet<>();
/* 39 */     Predicate<String> inputsFilter = this.configuration.getInputsFilter();
/* 40 */     for (String key : mmap.keySet()) {
/* 41 */       for (String v : mmap.get(key)) {
/* 42 */         if (inputsFilter.apply(v))
/* 43 */           result.add(v); 
/* 44 */       }  if (inputsFilter.apply(key))
/* 45 */         result.add(key); 
/*    */     } 
/* 47 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public void expandSuperTypes() {
/* 52 */     if (this.store.keySet().contains(SCANNER_CLASS_NAME)) {
/* 53 */       Multimap<String, String> mmap = this.store.get(SCANNER_CLASS_NAME);
/*    */       
/* 55 */       Set<String> s1 = Sets.newHashSet(mmap.keySet());
/* 56 */       Set<String> s2 = Sets.newHashSet(mmap.values());
/* 57 */       s1.removeAll(s2);
/*    */ 
/*    */       
/* 60 */       HashMultimap hashMultimap = HashMultimap.create();
/* 61 */       for (String key : s1) {
/* 62 */         Class<?> type = null;
/*    */         try {
/* 64 */           type = Class.forName(key);
/* 65 */         } catch (ClassNotFoundException e) {
/*    */           
/* 67 */           e.printStackTrace();
/*    */         } 
/* 69 */         if (type != null) {
/* 70 */           expandSupertypes((Multimap<String, String>)hashMultimap, key, type);
/*    */         }
/*    */       } 
/* 73 */       mmap.putAll((Multimap)hashMultimap);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void expandSupertypes(Multimap<String, String> mmap, String key, Class<?> type) {
/* 78 */     for (Class<?> supertype : (Iterable<Class<?>>)ReflectionUtils.getSuperTypes(type)) {
/* 79 */       if (mmap.put(supertype.getName(), key)) {
/* 80 */         if (log != null) log.debug("expanded subtype {} -> {}", supertype.getName(), key); 
/* 81 */         expandSupertypes(mmap, supertype.getName(), supertype);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\VersionCompliantReflections.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */