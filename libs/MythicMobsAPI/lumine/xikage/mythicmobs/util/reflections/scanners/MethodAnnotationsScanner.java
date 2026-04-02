/*    */ package lumine.xikage.mythicmobs.util.reflections.scanners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.scanners.AbstractScanner;
/*    */ 
/*    */ public class MethodAnnotationsScanner
/*    */   extends AbstractScanner
/*    */ {
/*    */   public void scan(Object cls) {
/*  9 */     for (Object method : getMetadataAdapter().getMethods(cls)) {
/* 10 */       for (String methodAnnotation : getMetadataAdapter().getMethodAnnotationNames(method)) {
/* 11 */         if (acceptResult(methodAnnotation))
/* 12 */           getStore().put(methodAnnotation, getMetadataAdapter().getMethodFullKey(cls, method)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\scanners\MethodAnnotationsScanner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */