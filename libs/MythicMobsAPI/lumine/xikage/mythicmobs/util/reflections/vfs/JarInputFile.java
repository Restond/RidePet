/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.JarInputDir;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.zip.ZipEntry;
/*    */ 
/*    */ public class JarInputFile
/*    */   implements Vfs.File {
/*    */   private final ZipEntry entry;
/*    */   private final JarInputDir jarInputDir;
/*    */   private final long fromIndex;
/*    */   private final long endIndex;
/*    */   
/*    */   public JarInputFile(ZipEntry entry, JarInputDir jarInputDir, long cursor, long nextCursor) {
/* 17 */     this.entry = entry;
/* 18 */     this.jarInputDir = jarInputDir;
/* 19 */     this.fromIndex = cursor;
/* 20 */     this.endIndex = nextCursor;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 24 */     String name = this.entry.getName();
/* 25 */     return name.substring(name.lastIndexOf("/") + 1);
/*    */   }
/*    */   
/*    */   public String getRelativePath() {
/* 29 */     return this.entry.getName();
/*    */   }
/*    */   
/*    */   public InputStream openInputStream() throws IOException {
/* 33 */     return (InputStream)new Object(this);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\JarInputFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */