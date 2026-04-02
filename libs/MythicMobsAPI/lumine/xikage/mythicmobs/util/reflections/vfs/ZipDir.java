/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.Reflections;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import java.io.IOException;
/*    */ import java.util.jar.JarFile;
/*    */ import java.util.zip.ZipFile;
/*    */ 
/*    */ 
/*    */ public class ZipDir
/*    */   implements Vfs.Dir
/*    */ {
/*    */   final ZipFile jarFile;
/*    */   
/*    */   public ZipDir(JarFile jarFile) {
/* 16 */     this.jarFile = jarFile;
/*    */   }
/*    */   
/*    */   public String getPath() {
/* 20 */     return this.jarFile.getName();
/*    */   }
/*    */   
/*    */   public Iterable<Vfs.File> getFiles() {
/* 24 */     return (Iterable<Vfs.File>)new Object(this);
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
/*    */   public void close() {
/*    */     
/* 45 */     try { this.jarFile.close(); } catch (IOException e)
/* 46 */     { if (Reflections.log != null) {
/* 47 */         Reflections.log.warn("Could not close JarFile", e);
/*    */       } }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return this.jarFile.getName();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\ZipDir.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */