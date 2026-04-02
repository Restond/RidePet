/*     */ package lumine.utils.events.functional.protocol;
/*     */ 
/*     */ import com.comphenix.protocol.PacketType;
/*     */ import com.comphenix.protocol.events.ListenerPriority;
/*     */ import com.comphenix.protocol.events.PacketEvent;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import io.lumine.utils.events.ProtocolSubscription;
/*     */ import io.lumine.utils.events.functional.ExpiryTestStage;
/*     */ import io.lumine.utils.events.functional.SubscriptionBuilder;
/*     */ import io.lumine.utils.events.functional.protocol.ProtocolHandlerList;
/*     */ import io.lumine.utils.events.functional.protocol.ProtocolHandlerListImpl;
/*     */ import io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
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
/*     */ class ProtocolSubscriptionBuilderImpl
/*     */   implements ProtocolSubscriptionBuilder
/*     */ {
/*     */   final Set<PacketType> types;
/*     */   final ListenerPriority priority;
/*  50 */   BiConsumer<? super PacketEvent, Throwable> exceptionConsumer = DEFAULT_EXCEPTION_CONSUMER;
/*     */   
/*  52 */   final List<Predicate<PacketEvent>> filters = new ArrayList<>(3);
/*  53 */   final List<BiPredicate<ProtocolSubscription, PacketEvent>> preExpiryTests = new ArrayList<>(0);
/*  54 */   final List<BiPredicate<ProtocolSubscription, PacketEvent>> midExpiryTests = new ArrayList<>(0);
/*  55 */   final List<BiPredicate<ProtocolSubscription, PacketEvent>> postExpiryTests = new ArrayList<>(0);
/*     */   
/*     */   ProtocolSubscriptionBuilderImpl(Set<PacketType> types, ListenerPriority priority) {
/*  58 */     this.types = (Set<PacketType>)ImmutableSet.copyOf(types);
/*  59 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ProtocolSubscriptionBuilder expireIf(@Nonnull BiPredicate<ProtocolSubscription, PacketEvent> predicate, @Nonnull ExpiryTestStage... testPoints) {
/*  65 */     Objects.requireNonNull(testPoints, "testPoints");
/*  66 */     Objects.requireNonNull(predicate, "predicate");
/*  67 */     for (ExpiryTestStage testPoint : testPoints) {
/*  68 */       switch (null.$SwitchMap$io$lumine$utils$events$functional$ExpiryTestStage[testPoint.ordinal()]) {
/*     */         case 1:
/*  70 */           this.preExpiryTests.add(predicate);
/*     */           break;
/*     */         case 2:
/*  73 */           this.midExpiryTests.add(predicate);
/*     */           break;
/*     */         case 3:
/*  76 */           this.postExpiryTests.add(predicate);
/*     */           break;
/*     */         default:
/*  79 */           throw new IllegalArgumentException("Unknown ExpiryTestPoint: " + testPoint);
/*     */       } 
/*     */     } 
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ProtocolSubscriptionBuilder filter(@Nonnull Predicate<PacketEvent> predicate) {
/*  88 */     Objects.requireNonNull(predicate, "predicate");
/*  89 */     this.filters.add(predicate);
/*  90 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ProtocolSubscriptionBuilder exceptionConsumer(@Nonnull BiConsumer<? super PacketEvent, Throwable> exceptionConsumer) {
/*  96 */     Objects.requireNonNull(exceptionConsumer, "exceptionConsumer");
/*  97 */     this.exceptionConsumer = exceptionConsumer;
/*  98 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ProtocolHandlerList handlers() {
/* 104 */     return (ProtocolHandlerList)new ProtocolHandlerListImpl(this);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\protocol\ProtocolSubscriptionBuilderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */