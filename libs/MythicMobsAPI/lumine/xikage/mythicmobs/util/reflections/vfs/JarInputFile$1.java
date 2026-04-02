/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.JarInputFile;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ class null
/*    */   extends InputStream
/*    */ {
/*    */   public int read() throws IOException {
/* 36 */     if ((JarInputFile.access$000(JarInputFile.this)).cursor >= JarInputFile.access$100(JarInputFile.this) && (JarInputFile.access$000(JarInputFile.this)).cursor <= JarInputFile.access$200(JarInputFile.this)) {
/* 37 */       int read = (JarInputFile.access$000(JarInputFile.this)).jarInputStream.read();
/* 38 */       (JarInputFile.access$000(JarInputFile.this)).cursor++;
/* 39 */       return read;
/*    */     } 
/* 41 */     return -1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\JarInputFile$1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */