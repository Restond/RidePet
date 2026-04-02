/*    */ package lumine.xikage.mythicmobs.util.reflections.scanners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.scanners.AbstractScanner;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class TypesScanner
/*    */   extends AbstractScanner
/*    */ {
/*    */   public Object scan(Vfs.File file, Object classObject) {
/* 13 */     classObject = super.scan(file, classObject);
/* 14 */     String className = getMetadataAdapter().getClassName(classObject);
/* 15 */     getStore().put(className, className);
/* 16 */     return classObject;
/*    */   }
/*    */ 
/*    */   
/*    */   public void scan(Object cls) {
/* 21 */     throw new UnsupportedOperationException("should not get here");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\scanners\TypesScanner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */