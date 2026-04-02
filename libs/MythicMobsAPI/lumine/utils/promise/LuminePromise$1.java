/*    */ package lumine.utils.promise;
/*    */ 
/*    */ import com.google.common.util.concurrent.FutureCallback;
/*    */ import io.lumine.utils.promise.LuminePromise;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class null
/*    */   implements FutureCallback<U>
/*    */ {
/*    */   public void onSuccess(@Nullable U result) {
/* 76 */     LuminePromise.access$000(promise, result);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onFailure(@Nonnull Throwable t) {
/* 81 */     LuminePromise.access$100(promise, t);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\promise\LuminePromise$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */