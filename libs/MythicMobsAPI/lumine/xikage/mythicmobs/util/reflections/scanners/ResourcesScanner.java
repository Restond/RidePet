/*    */ package lumine.xikage.mythicmobs.util.reflections.scanners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.scanners.AbstractScanner;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ 
/*    */ public class ResourcesScanner
/*    */   extends AbstractScanner {
/*    */   public boolean acceptsInput(String file) {
/*  9 */     return !file.endsWith(".class");
/*    */   }
/*    */   
/*    */   public Object scan(Vfs.File file, Object classObject) {
/* 13 */     getStore().put(file.getName(), file.getRelativePath());
/* 14 */     return classObject;
/*    */   }
/*    */   
/*    */   public void scan(Object cls) {
/* 18 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\scanners\ResourcesScanner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */