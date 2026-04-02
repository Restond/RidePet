/*    */ package lumine.utils.events.functional.protocol;
/*    */ 
/*    */ import com.comphenix.protocol.events.PacketEvent;
/*    */ import io.lumine.utils.Delegates;
/*    */ import io.lumine.utils.events.ProtocolSubscription;
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
/*    */ public interface ProtocolHandlerList
/*    */   extends FunctionalHandlerList<PacketEvent, ProtocolSubscription>
/*    */ {
/*    */   @Nonnull
/*    */   io.lumine.utils.events.functional.protocol.ProtocolHandlerList biConsumer(@Nonnull BiConsumer<ProtocolSubscription, ? super PacketEvent> paramBiConsumer);
/*    */   
/*    */   @Nonnull
/*    */   default io.lumine.utils.events.functional.protocol.ProtocolHandlerList consumer(@Nonnull Consumer<? super PacketEvent> handler) {
/* 45 */     Objects.requireNonNull(handler, "handler");
/* 46 */     return biConsumer(Delegates.consumerToBiConsumerSecond(handler));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\protocol\ProtocolHandlerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */