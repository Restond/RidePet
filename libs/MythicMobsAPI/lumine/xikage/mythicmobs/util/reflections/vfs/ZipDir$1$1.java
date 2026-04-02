/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import com.google.common.collect.AbstractIterator;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.ZipDir;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.ZipFile;
/*    */ import java.util.Enumeration;
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
/*    */ class null
/*    */   extends AbstractIterator<Vfs.File>
/*    */ {
/* 27 */   final Enumeration<? extends ZipEntry> entries = this.this$1.this$0.jarFile.entries();
/*    */   
/*    */   protected Vfs.File computeNext() {
/* 30 */     while (this.entries.hasMoreElements()) {
/* 31 */       ZipEntry entry = this.entries.nextElement();
/* 32 */       if (!entry.isDirectory()) {
/* 33 */         return (Vfs.File)new ZipFile(this.this$1.this$0, entry);
/*    */       }
/*    */     } 
/*    */     
/* 37 */     return (Vfs.File)endOfData();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\ZipDir$1$1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */