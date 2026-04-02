/*    */ package lumine.utils.events.functional.single;
/*    */ 
/*    */ import io.lumine.utils.Delegates;
/*    */ import io.lumine.utils.events.SingleSubscription;
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
/*    */ 
/*    */ 
/*    */ public interface SingleHandlerList<T extends org.bukkit.event.Event>
/*    */   extends FunctionalHandlerList<T, SingleSubscription<T>>
/*    */ {
/*    */   @Nonnull
/*    */   io.lumine.utils.events.functional.single.SingleHandlerList<T> biConsumer(@Nonnull BiConsumer<SingleSubscription<T>, ? super T> paramBiConsumer);
/*    */   
/*    */   @Nonnull
/*    */   default io.lumine.utils.events.functional.single.SingleHandlerList<T> consumer(@Nonnull Consumer<? super T> handler) {
/* 45 */     Objects.requireNonNull(handler, "handler");
/* 46 */     return biConsumer(Delegates.consumerToBiConsumerSecond(handler));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\single\SingleHandlerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */