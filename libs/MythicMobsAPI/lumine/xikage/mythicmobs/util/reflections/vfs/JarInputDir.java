/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.util.Utils;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import java.net.URL;
/*    */ import java.util.jar.JarInputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JarInputDir
/*    */   implements Vfs.Dir
/*    */ {
/*    */   private final URL url;
/*    */   JarInputStream jarInputStream;
/* 19 */   long cursor = 0L;
/* 20 */   long nextCursor = 0L;
/*    */   
/*    */   public JarInputDir(URL url) {
/* 23 */     this.url = url;
/*    */   }
/*    */   
/*    */   public String getPath() {
/* 27 */     return this.url.getPath();
/*    */   }
/*    */   
/*    */   public Iterable<Vfs.File> getFiles() {
/* 31 */     return (Iterable<Vfs.File>)new Object(this);
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
/* 65 */     Utils.close(this.jarInputStream);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\JarInputDir.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */