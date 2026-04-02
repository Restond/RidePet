/*    */ package lumine.xikage.mythicmobs.util.reflections.scanners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.scanners.AbstractScanner;
/*    */ import java.lang.annotation.Inherited;
/*    */ 
/*    */ public class TypeAnnotationsScanner
/*    */   extends AbstractScanner
/*    */ {
/*    */   public void scan(Object cls) {
/* 10 */     String className = getMetadataAdapter().getClassName(cls);
/*    */     
/* 12 */     for (String annotationType : getMetadataAdapter().getClassAnnotationNames(cls)) {
/*    */       
/* 14 */       if (acceptResult(annotationType) || annotationType
/* 15 */         .equals(Inherited.class.getName()))
/* 16 */         getStore().put(annotationType, className); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\scanners\TypeAnnotationsScanner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */