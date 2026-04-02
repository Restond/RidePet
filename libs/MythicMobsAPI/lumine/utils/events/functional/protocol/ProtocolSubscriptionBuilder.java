/*     */ package lumine.utils.events.functional.protocol;
/*     */ 
/*     */ import com.comphenix.protocol.PacketType;
/*     */ import com.comphenix.protocol.events.ListenerPriority;
/*     */ import com.comphenix.protocol.events.PacketEvent;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import io.lumine.utils.Delegates;
/*     */ import io.lumine.utils.events.ProtocolSubscription;
/*     */ import io.lumine.utils.events.functional.ExpiryTestStage;
/*     */ import io.lumine.utils.events.functional.SubscriptionBuilder;
/*     */ import io.lumine.utils.events.functional.protocol.ProtocolHandlerList;
/*     */ import io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilderImpl;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ProtocolSubscriptionBuilder
/*     */   extends SubscriptionBuilder<PacketEvent>
/*     */ {
/*     */   @Nonnull
/*     */   static io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder newBuilder(@Nonnull PacketType... packets) {
/*  61 */     return newBuilder(ListenerPriority.NORMAL, packets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   static io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder newBuilder(@Nonnull ListenerPriority priority, @Nonnull PacketType... packets) {
/*  73 */     Objects.requireNonNull(priority, "priority");
/*  74 */     Objects.requireNonNull(packets, "packets");
/*  75 */     return (io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder)new ProtocolSubscriptionBuilderImpl((Set)ImmutableSet.copyOf((Object[])packets), priority);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder expireIf(@Nonnull Predicate<PacketEvent> predicate) {
/*  83 */     return expireIf(Delegates.predicateToBiPredicateSecond(predicate), new ExpiryTestStage[] { ExpiryTestStage.PRE, ExpiryTestStage.POST_HANDLE });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder expireAfter(long duration, @Nonnull TimeUnit unit) {
/*  89 */     Objects.requireNonNull(unit, "unit");
/*  90 */     Preconditions.checkArgument((duration >= 1L), "duration < 1");
/*  91 */     long expiry = Math.addExact(System.currentTimeMillis(), unit.toMillis(duration));
/*  92 */     return expireIf((handler, event) -> (System.currentTimeMillis() > paramLong), new ExpiryTestStage[] { ExpiryTestStage.PRE });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder expireAfter(long maxCalls) {
/*  98 */     Preconditions.checkArgument((maxCalls >= 1L), "maxCalls < 1");
/*  99 */     return expireIf((handler, event) -> (handler.getCallCounter() >= paramLong), new ExpiryTestStage[] { ExpiryTestStage.PRE, ExpiryTestStage.POST_HANDLE });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default ProtocolSubscription handler(@Nonnull Consumer<? super PacketEvent> handler) {
/* 145 */     return (ProtocolSubscription)handlers().consumer(handler).register();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default ProtocolSubscription biHandler(@Nonnull BiConsumer<ProtocolSubscription, ? super PacketEvent> handler) {
/* 157 */     return (ProtocolSubscription)handlers().biConsumer(handler).register();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder filter(@Nonnull Predicate<PacketEvent> paramPredicate);
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder expireIf(@Nonnull BiPredicate<ProtocolSubscription, PacketEvent> paramBiPredicate, @Nonnull ExpiryTestStage... paramVarArgs);
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder exceptionConsumer(@Nonnull BiConsumer<? super PacketEvent, Throwable> paramBiConsumer);
/*     */   
/*     */   @Nonnull
/*     */   ProtocolHandlerList handlers();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\protocol\ProtocolSubscriptionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */