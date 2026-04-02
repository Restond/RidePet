/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import java.io.File;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemDir
/*    */   implements Vfs.Dir
/*    */ {
/*    */   private final File file;
/*    */   
/*    */   public SystemDir(File file) {
/* 19 */     if (file != null && (!file.isDirectory() || !file.canRead())) {
/* 20 */       throw new RuntimeException("cannot use dir " + file);
/*    */     }
/*    */     
/* 23 */     this.file = file;
/*    */   }
/*    */   
/*    */   public String getPath() {
/* 27 */     if (this.file == null) {
/* 28 */       return "/NO-SUCH-DIRECTORY/";
/*    */     }
/* 30 */     return this.file.getPath().replace("\\", "/");
/*    */   }
/*    */   
/*    */   public Iterable<Vfs.File> getFiles() {
/* 34 */     if (this.file == null || !this.file.exists()) {
/* 35 */       return Collections.emptyList();
/*    */     }
/* 37 */     return (Iterable<Vfs.File>)new Object(this);
/*    */   }
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
/*    */   private static List<File> listFiles(File file) {
/* 61 */     File[] files = file.listFiles();
/*    */     
/* 63 */     if (files != null) {
/* 64 */       return Lists.newArrayList((Object[])files);
/*    */     }
/* 66 */     return Lists.newArrayList();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {}
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     return getPath();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\SystemDir.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */