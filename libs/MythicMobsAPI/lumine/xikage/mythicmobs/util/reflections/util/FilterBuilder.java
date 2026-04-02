/*     */ package lumine.xikage.mythicmobs.util.reflections.util;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Lists;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.ReflectionsException;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.util.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterBuilder
/*     */   implements Predicate<String>
/*     */ {
/*     */   private final List<Predicate<String>> chain;
/*     */   
/*     */   public FilterBuilder() {
/*  24 */     this.chain = Lists.newArrayList(); } private FilterBuilder(Iterable<Predicate<String>> filters) {
/*  25 */     this.chain = Lists.newArrayList(filters);
/*     */   }
/*     */   public io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder include(String regex) {
/*  28 */     return add((Predicate<String>)new Include(regex));
/*     */   }
/*     */   public io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder exclude(String regex) {
/*  31 */     add((Predicate<String>)new Exclude(regex)); return this;
/*     */   }
/*     */   public io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder add(Predicate<String> filter) {
/*  34 */     this.chain.add(filter); return this;
/*     */   }
/*     */   public io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder includePackage(Class<?> aClass) {
/*  37 */     return add((Predicate<String>)new Include(packageNameRegex(aClass)));
/*     */   }
/*     */   public io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder excludePackage(Class<?> aClass) {
/*  40 */     return add((Predicate<String>)new Exclude(packageNameRegex(aClass)));
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder includePackage(String... prefixes) {
/*  44 */     for (String prefix : prefixes) {
/*  45 */       add((Predicate<String>)new Include(prefix(prefix)));
/*     */     }
/*  47 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder excludePackage(String prefix) {
/*  51 */     return add((Predicate<String>)new Exclude(prefix(prefix)));
/*     */   } private static String packageNameRegex(Class<?> aClass) {
/*  53 */     return prefix(aClass.getPackage().getName() + ".");
/*     */   } public static String prefix(String qualifiedName) {
/*  55 */     return qualifiedName.replace(".", "\\.") + ".*";
/*     */   } public String toString() {
/*  57 */     return Joiner.on(", ").join(this.chain);
/*     */   }
/*     */   public boolean apply(String regex) {
/*  60 */     boolean accept = (this.chain == null || this.chain.isEmpty() || this.chain.get(0) instanceof Exclude);
/*     */     
/*  62 */     if (this.chain != null)
/*  63 */       for (Predicate<String> filter : this.chain) {
/*  64 */         if ((accept && filter instanceof Include) || (
/*  65 */           !accept && filter instanceof Exclude))
/*  66 */           continue;  accept = filter.apply(regex);
/*  67 */         if (!accept && filter instanceof Exclude)
/*     */           break; 
/*     */       }  
/*  70 */     return accept;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder parse(String includeExcludeString) {
/* 105 */     List<Predicate<String>> filters = new ArrayList<>();
/*     */     
/* 107 */     if (!Utils.isEmpty(includeExcludeString)) {
/* 108 */       for (String string : includeExcludeString.split(",")) {
/* 109 */         Include include; Exclude exclude; String trimmed = string.trim();
/* 110 */         char prefix = trimmed.charAt(0);
/* 111 */         String pattern = trimmed.substring(1);
/*     */ 
/*     */         
/* 114 */         switch (prefix) {
/*     */           case '+':
/* 116 */             include = new Include(pattern);
/*     */             break;
/*     */           case '-':
/* 119 */             exclude = new Exclude(pattern);
/*     */             break;
/*     */           default:
/* 122 */             throw new ReflectionsException("includeExclude should start with either + or -");
/*     */         } 
/*     */         
/* 125 */         filters.add(exclude);
/*     */       } 
/*     */       
/* 128 */       return new io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder(filters);
/*     */     } 
/* 130 */     return new io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder parsePackages(String includeExcludeString) {
/* 146 */     List<Predicate<String>> filters = new ArrayList<>();
/*     */     
/* 148 */     if (!Utils.isEmpty(includeExcludeString)) {
/* 149 */       for (String string : includeExcludeString.split(",")) {
/* 150 */         Include include; Exclude exclude; String trimmed = string.trim();
/* 151 */         char prefix = trimmed.charAt(0);
/* 152 */         String pattern = trimmed.substring(1);
/* 153 */         if (!pattern.endsWith(".")) {
/* 154 */           pattern = pattern + ".";
/*     */         }
/* 156 */         pattern = prefix(pattern);
/*     */ 
/*     */         
/* 159 */         switch (prefix) {
/*     */           case '+':
/* 161 */             include = new Include(pattern);
/*     */             break;
/*     */           case '-':
/* 164 */             exclude = new Exclude(pattern);
/*     */             break;
/*     */           default:
/* 167 */             throw new ReflectionsException("includeExclude should start with either + or -");
/*     */         } 
/*     */         
/* 170 */         filters.add(exclude);
/*     */       } 
/*     */       
/* 173 */       return new io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder(filters);
/*     */     } 
/* 175 */     return new io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflection\\util\FilterBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */