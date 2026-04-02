/*    */ package lumine.utils.terminable;
/*    */ 
/*    */ import io.lumine.utils.terminable.TerminableConsumer;
/*    */ import javax.annotation.Nonnull;
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
/*    */ public interface TerminableModule
/*    */ {
/*    */   void setup(@Nonnull TerminableConsumer paramTerminableConsumer);
/*    */   
/*    */   default void bindModuleWith(@Nonnull TerminableConsumer consumer) {
/* 24 */     consumer.bindModule(this);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\terminable\TerminableModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */