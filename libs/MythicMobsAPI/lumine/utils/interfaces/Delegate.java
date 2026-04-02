/*    */ package lumine.utils.interfaces;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Delegate<T>
/*    */ {
/*    */   static Object resolve(Object obj) {
/* 11 */     while (obj instanceof io.lumine.utils.interfaces.Delegate) {
/* 12 */       io.lumine.utils.interfaces.Delegate<?> delegate = (io.lumine.utils.interfaces.Delegate)obj;
/* 13 */       obj = delegate.getDelegate();
/*    */     } 
/* 15 */     return obj;
/*    */   }
/*    */   
/*    */   T getDelegate();
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\interfaces\Delegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */