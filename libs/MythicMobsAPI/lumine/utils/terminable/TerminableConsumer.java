/*    */ package lumine.utils.terminable;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FunctionalInterface
/*    */ public interface TerminableConsumer
/*    */ {
/*    */   @Nonnull
/*    */   <T extends AutoCloseable> T bind(@Nonnull T paramT);
/*    */   
/*    */   @Nonnull
/*    */   default <T extends io.lumine.utils.terminable.TerminableModule> T bindModule(@Nonnull T module) {
/* 31 */     module.setup(this);
/* 32 */     return module;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\terminable\TerminableConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */