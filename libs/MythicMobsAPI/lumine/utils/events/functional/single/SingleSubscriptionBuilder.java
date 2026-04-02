/*     */ package lumine.utils.events.functional.single;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import io.lumine.utils.Delegates;
/*     */ import io.lumine.utils.events.SingleSubscription;
/*     */ import io.lumine.utils.events.functional.ExpiryTestStage;
/*     */ import io.lumine.utils.events.functional.SubscriptionBuilder;
/*     */ import io.lumine.utils.events.functional.single.SingleHandlerList;
/*     */ import io.lumine.utils.events.functional.single.SingleSubscriptionBuilderImpl;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventPriority;
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
/*     */ public interface SingleSubscriptionBuilder<T extends Event>
/*     */   extends SubscriptionBuilder<T>
/*     */ {
/*     */   @Nonnull
/*     */   static <T extends Event> io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> newBuilder(@Nonnull Class<T> eventClass) {
/*  64 */     return newBuilder(eventClass, EventPriority.NORMAL);
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
/*     */   @Nonnull
/*     */   static <T extends Event> io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> newBuilder(@Nonnull Class<T> eventClass, @Nonnull EventPriority priority) {
/*  78 */     Objects.requireNonNull(eventClass, "eventClass");
/*  79 */     Objects.requireNonNull(priority, "priority");
/*  80 */     return (io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T>)new SingleSubscriptionBuilderImpl(eventClass, priority);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> expireIf(@Nonnull Predicate<T> predicate) {
/*  88 */     return expireIf(Delegates.predicateToBiPredicateSecond(predicate), new ExpiryTestStage[] { ExpiryTestStage.PRE, ExpiryTestStage.POST_HANDLE });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> expireAfter(long duration, @Nonnull TimeUnit unit) {
/*  94 */     Objects.requireNonNull(unit, "unit");
/*  95 */     Preconditions.checkArgument((duration >= 1L), "duration < 1");
/*  96 */     long expiry = Math.addExact(System.currentTimeMillis(), unit.toMillis(duration));
/*  97 */     return expireIf((handler, event) -> (System.currentTimeMillis() > paramLong), new ExpiryTestStage[] { ExpiryTestStage.PRE });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> expireAfter(long maxCalls) {
/* 103 */     Preconditions.checkArgument((maxCalls >= 1L), "maxCalls < 1");
/* 104 */     return expireIf((handler, event) -> (handler.getCallCounter() >= paramLong), new ExpiryTestStage[] { ExpiryTestStage.PRE, ExpiryTestStage.POST_HANDLE });
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
/*     */   default SingleSubscription<T> handler(@Nonnull Consumer<? super T> handler) {
/* 150 */     return (SingleSubscription<T>)handlers().consumer(handler).register();
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
/*     */   default SingleSubscription<T> biHandler(@Nonnull BiConsumer<SingleSubscription<T>, ? super T> handler) {
/* 162 */     return (SingleSubscription<T>)handlers().biConsumer(handler).register();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> filter(@Nonnull Predicate<T> paramPredicate);
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> expireIf(@Nonnull BiPredicate<SingleSubscription<T>, T> paramBiPredicate, @Nonnull ExpiryTestStage... paramVarArgs);
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.single.SingleSubscriptionBuilder<T> exceptionConsumer(@Nonnull BiConsumer<? super T, Throwable> paramBiConsumer);
/*     */   
/*     */   @Nonnull
/*     */   SingleHandlerList<T> handlers();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\single\SingleSubscriptionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */