/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.SystemDir;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class SystemFile implements Vfs.File {
/*    */   private final SystemDir root;
/*    */   
/*    */   public SystemFile(SystemDir root, File file) {
/* 13 */     this.root = root;
/* 14 */     this.file = file;
/*    */   }
/*    */   private final File file;
/*    */   public String getName() {
/* 18 */     return this.file.getName();
/*    */   }
/*    */   
/*    */   public String getRelativePath() {
/* 22 */     String filepath = this.file.getPath().replace("\\", "/");
/* 23 */     if (filepath.startsWith(this.root.getPath())) {
/* 24 */       return filepath.substring(this.root.getPath().length() + 1);
/*    */     }
/*    */     
/* 27 */     return null;
/*    */   }
/*    */   
/*    */   public InputStream openInputStream() {
/*    */     try {
/* 32 */       return new FileInputStream(this.file);
/* 33 */     } catch (FileNotFoundException e) {
/* 34 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return this.file.toString();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\SystemFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */