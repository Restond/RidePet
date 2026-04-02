/*     */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.Reflections;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.ReflectionsException;
/*     */ import java.io.File;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
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
/*     */ public abstract class Vfs
/*     */ {
/*  52 */   private static List<UrlType> defaultUrlTypes = Lists.newArrayList((Object[])DefaultUrlTypes.values());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<UrlType> getDefaultUrlTypes() {
/*  76 */     return defaultUrlTypes;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setDefaultURLTypes(List<UrlType> urlTypes) {
/*  81 */     defaultUrlTypes = urlTypes;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addDefaultURLTypes(UrlType urlType) {
/*  86 */     defaultUrlTypes.add(0, urlType);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Dir fromURL(URL url) {
/*  91 */     return fromURL(url, defaultUrlTypes);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Dir fromURL(URL url, List<UrlType> urlTypes) {
/*  96 */     for (UrlType type : urlTypes) {
/*     */       try {
/*  98 */         if (type.matches(url)) {
/*  99 */           Dir dir = type.createDir(url);
/* 100 */           if (dir != null) return dir; 
/*     */         } 
/* 102 */       } catch (Throwable e) {
/* 103 */         if (Reflections.log != null) {
/* 104 */           Reflections.log.warn("could not create Dir using " + type + " from url " + url.toExternalForm() + ". skipping.", e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     throw new ReflectionsException("could not create Vfs.Dir from url, no matching UrlType was found [" + url.toExternalForm() + "]\neither use fromURL(final URL url, final List<UrlType> urlTypes) or use the static setDefaultURLTypes(final List<UrlType> urlTypes) or addDefaultURLTypes(UrlType urlType) with your specialized UrlType.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Dir fromURL(URL url, UrlType... urlTypes) {
/* 117 */     return fromURL(url, Lists.newArrayList((Object[])urlTypes));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Iterable<File> findFiles(Collection<URL> inUrls, String packagePrefix, Predicate<String> nameFilter) {
/* 122 */     Object object = new Object(packagePrefix, nameFilter);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     return findFiles(inUrls, (Predicate<File>)object);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Iterable<File> findFiles(Collection<URL> inUrls, Predicate<File> filePredicate) {
/* 139 */     Iterable<File> result = new ArrayList<>();
/*     */     
/* 141 */     for (URL url : inUrls) {
/*     */       try {
/* 143 */         result = Iterables.concat(result, 
/* 144 */             Iterables.filter((Iterable)new Object(url), filePredicate));
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 149 */       catch (Throwable e) {
/* 150 */         if (Reflections.log != null) {
/* 151 */           Reflections.log.error("could not findFiles for url. continuing. [" + url + "]", e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 156 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static File getFile(URL url) {
/*     */     try {
/* 165 */       String path = url.toURI().getSchemeSpecificPart(); File file;
/* 166 */       if ((file = new File(path)).exists()) return file; 
/* 167 */     } catch (URISyntaxException uRISyntaxException) {}
/*     */ 
/*     */     
/*     */     try {
/* 171 */       String path = URLDecoder.decode(url.getPath(), "UTF-8");
/* 172 */       if (path.contains(".jar!")) path = path.substring(0, path.lastIndexOf(".jar!") + ".jar".length());  File file;
/* 173 */       if ((file = new File(path)).exists()) return file;
/*     */     
/* 175 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*     */ 
/*     */     
/*     */     try {
/* 179 */       String path = url.toExternalForm();
/* 180 */       if (path.startsWith("jar:")) path = path.substring("jar:".length()); 
/* 181 */       if (path.startsWith("wsjar:")) path = path.substring("wsjar:".length()); 
/* 182 */       if (path.startsWith("file:")) path = path.substring("file:".length()); 
/* 183 */       if (path.contains(".jar!")) path = path.substring(0, path.indexOf(".jar!") + ".jar".length());  File file;
/* 184 */       if ((file = new File(path)).exists()) return file;
/*     */       
/* 186 */       path = path.replace("%20", " ");
/* 187 */       if ((file = new File(path)).exists()) return file;
/*     */     
/* 189 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 192 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean hasJarFileInPath(URL url) {
/* 196 */     return url.toExternalForm().matches(".*\\.jar(\\!.*|$)");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\Vfs.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */