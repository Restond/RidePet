/*    */ package lumine.utils.events.functional.protocol;
/*    */ 
/*    */ import com.comphenix.protocol.events.PacketEvent;
/*    */ import io.lumine.utils.events.ProtocolSubscription;
/*    */ import io.lumine.utils.events.functional.FunctionalHandlerList;
/*    */ import io.lumine.utils.events.functional.protocol.ProtocolHandlerList;
/*    */ import io.lumine.utils.events.functional.protocol.ProtocolListener;
/*    */ import io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilderImpl;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiConsumer;
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
/*    */ class ProtocolHandlerListImpl
/*    */   implements ProtocolHandlerList
/*    */ {
/*    */   private final ProtocolSubscriptionBuilderImpl builder;
/* 41 */   private final List<BiConsumer<ProtocolSubscription, ? super PacketEvent>> handlers = new ArrayList<>(1);
/*    */   
/*    */   ProtocolHandlerListImpl(@Nonnull ProtocolSubscriptionBuilderImpl builder) {
/* 44 */     this.builder = builder;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public ProtocolHandlerList biConsumer(@Nonnull BiConsumer<ProtocolSubscription, ? super PacketEvent> handler) {
/* 50 */     Objects.requireNonNull(handler, "handler");
/* 51 */     this.handlers.add(handler);
/* 52 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public ProtocolSubscription register() {
/* 58 */     return (ProtocolSubscription)new ProtocolListener(this.builder, this.handlers);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\protocol\ProtocolHandlerListImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */