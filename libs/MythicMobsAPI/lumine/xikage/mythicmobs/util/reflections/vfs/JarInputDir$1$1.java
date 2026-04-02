/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import com.google.common.collect.AbstractIterator;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.ReflectionsException;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.JarInputDir;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.JarInputFile;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import java.io.IOException;
/*    */ import java.util.jar.JarInputStream;
/*    */ import java.util.zip.ZipEntry;
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
/*    */   extends AbstractIterator<Vfs.File>
/*    */ {
/*    */   protected Vfs.File computeNext() {
/*    */     try {
/*    */       while (true) {
/* 43 */         ZipEntry entry = this.this$1.this$0.jarInputStream.getNextJarEntry();
/* 44 */         if (entry == null) {
/* 45 */           return (Vfs.File)endOfData();
/*    */         }
/*    */         
/* 48 */         long size = entry.getSize();
/* 49 */         if (size < 0L) size = 4294967295L + size; 
/* 50 */         this.this$1.this$0.nextCursor += size;
/* 51 */         if (!entry.isDirectory())
/* 52 */           return (Vfs.File)new JarInputFile(entry, this.this$1.this$0, this.this$1.this$0.cursor, this.this$1.this$0.nextCursor); 
/*    */       } 
/* 54 */     } catch (IOException e) {
/* 55 */       throw new ReflectionsException("could not get next zip entry", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\JarInputDir$1$1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */