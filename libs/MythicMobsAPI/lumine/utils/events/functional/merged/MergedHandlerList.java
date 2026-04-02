/*    */ package lumine.utils.events.functional.merged;
/*    */ 
/*    */ import io.lumine.utils.Delegates;
/*    */ import io.lumine.utils.events.MergedSubscription;
/*    */ import io.lumine.utils.events.functional.FunctionalHandlerList;
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Consumer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface MergedHandlerList<T>
/*    */   extends FunctionalHandlerList<T, MergedSubscription<T>>
/*    */ {
/*    */   @Nonnull
/*    */   io.lumine.utils.events.functional.merged.MergedHandlerList<T> biConsumer(@Nonnull BiConsumer<MergedSubscription<T>, ? super T> paramBiConsumer);
/*    */   
/*    */   @Nonnull
/*    */   default io.lumine.utils.events.functional.merged.MergedHandlerList<T> consumer(@Nonnull Consumer<? super T> handler) {
/* 43 */     Objects.requireNonNull(handler, "handler");
/* 44 */     return biConsumer(Delegates.consumerToBiConsumerSecond(handler));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\merged\MergedHandlerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */