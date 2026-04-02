/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.ZipDir;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.zip.ZipEntry;
/*    */ 
/*    */ public class ZipFile implements Vfs.File {
/*    */   private final ZipDir root;
/*    */   
/*    */   public ZipFile(ZipDir root, ZipEntry entry) {
/* 13 */     this.root = root;
/* 14 */     this.entry = entry;
/*    */   }
/*    */   private final ZipEntry entry;
/*    */   public String getName() {
/* 18 */     String name = this.entry.getName();
/* 19 */     return name.substring(name.lastIndexOf("/") + 1);
/*    */   }
/*    */   
/*    */   public String getRelativePath() {
/* 23 */     return this.entry.getName();
/*    */   }
/*    */   
/*    */   public InputStream openInputStream() throws IOException {
/* 27 */     return this.root.jarFile.getInputStream(this.entry);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     return this.root.getPath() + "!" + File.separatorChar + this.entry.toString();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\ZipFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */