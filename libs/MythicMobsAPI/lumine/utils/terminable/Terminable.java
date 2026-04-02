/*    */ package lumine.utils.terminable;
/*    */ 
/*    */ import io.lumine.utils.terminable.TerminableConsumer;
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
/*    */ public interface Terminable
/*    */   extends AutoCloseable
/*    */ {
/*    */   boolean terminate();
/*    */   
/*    */   default void close() {
/* 21 */     terminate();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean isClosed() {
/* 30 */     return hasTerminated();
/*    */   }
/*    */   
/*    */   default boolean hasTerminated() {
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default Exception closeSilently() {
/*    */     try {
/* 45 */       close();
/* 46 */       return null;
/* 47 */     } catch (Exception e) {
/* 48 */       return e;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void closeAndReportException() {
/*    */     try {
/* 57 */       close();
/* 58 */     } catch (Exception e) {
/* 59 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void bindWith(@Nonnull TerminableConsumer consumer) {
/* 69 */     consumer.bind(this);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\terminable\Terminable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */