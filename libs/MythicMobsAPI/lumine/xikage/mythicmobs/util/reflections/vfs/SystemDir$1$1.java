/*    */ package lumine.xikage.mythicmobs.util.reflections.vfs;
/*    */ 
/*    */ import com.google.common.collect.AbstractIterator;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.SystemDir;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.SystemFile;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ import java.io.File;
/*    */ import java.util.Stack;
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
/*    */ class null
/*    */   extends AbstractIterator<Vfs.File>
/*    */ {
/*    */   final Stack<File> stack;
/*    */   
/*    */   protected Vfs.File computeNext() {
/* 44 */     while (!this.stack.isEmpty()) {
/* 45 */       File file = this.stack.pop();
/* 46 */       if (file.isDirectory()) {
/* 47 */         this.stack.addAll(SystemDir.access$100(file)); continue;
/*    */       } 
/* 49 */       return (Vfs.File)new SystemFile(this.this$1.this$0, file);
/*    */     } 
/*    */ 
/*    */     
/* 53 */     return (Vfs.File)endOfData();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\SystemDir$1$1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */