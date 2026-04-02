/*     */ package lumine.utils.events.functional.merged;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.Delegates;
/*     */ import io.lumine.utils.events.MergedSubscription;
/*     */ import io.lumine.utils.events.functional.ExpiryTestStage;
/*     */ import io.lumine.utils.events.functional.SubscriptionBuilder;
/*     */ import io.lumine.utils.events.functional.merged.MergedHandlerList;
/*     */ import io.lumine.utils.events.functional.merged.MergedSubscriptionBuilderImpl;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
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
/*     */ public interface MergedSubscriptionBuilder<T>
/*     */   extends SubscriptionBuilder<T>
/*     */ {
/*     */   @Nonnull
/*     */   static <T> io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> newBuilder(@Nonnull Class<T> handledClass) {
/*  65 */     Objects.requireNonNull(handledClass, "handledClass");
/*  66 */     return (io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T>)new MergedSubscriptionBuilderImpl(TypeToken.of(handledClass));
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
/*     */   static <T> io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> newBuilder(@Nonnull TypeToken<T> type) {
/*  78 */     Objects.requireNonNull(type, "type");
/*  79 */     return (io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T>)new MergedSubscriptionBuilderImpl(type);
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
/*     */   @Nonnull
/*     */   @SafeVarargs
/*     */   static <S extends Event> io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<S> newBuilder(@Nonnull Class<S> superClass, @Nonnull Class<? extends S>... eventClasses) {
/*  93 */     return newBuilder(superClass, EventPriority.NORMAL, eventClasses);
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
/*     */   @SafeVarargs
/*     */   static <S extends Event> io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<S> newBuilder(@Nonnull Class<S> superClass, @Nonnull EventPriority priority, @Nonnull Class<? extends S>... eventClasses) {
/* 108 */     Objects.requireNonNull(superClass, "superClass");
/* 109 */     Objects.requireNonNull(eventClasses, "eventClasses");
/* 110 */     Objects.requireNonNull(priority, "priority");
/* 111 */     if (eventClasses.length < 2) {
/* 112 */       throw new IllegalArgumentException("merge method used for only one subclass");
/*     */     }
/*     */     
/* 115 */     MergedSubscriptionBuilderImpl<S> h = new MergedSubscriptionBuilderImpl(TypeToken.of(superClass));
/* 116 */     for (Class<? extends S> clazz : eventClasses) {
/* 117 */       h.bindEvent(clazz, priority, e -> e);
/*     */     }
/* 119 */     return (io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<S>)h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> expireIf(@Nonnull Predicate<T> predicate) {
/* 127 */     return expireIf(Delegates.predicateToBiPredicateSecond(predicate), new ExpiryTestStage[] { ExpiryTestStage.PRE, ExpiryTestStage.POST_HANDLE });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> expireAfter(long duration, @Nonnull TimeUnit unit) {
/* 133 */     Objects.requireNonNull(unit, "unit");
/* 134 */     Preconditions.checkArgument((duration >= 1L), "duration < 1");
/* 135 */     long expiry = Math.addExact(System.currentTimeMillis(), unit.toMillis(duration));
/* 136 */     return expireIf((handler, event) -> (System.currentTimeMillis() > paramLong), new ExpiryTestStage[] { ExpiryTestStage.PRE });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> expireAfter(long maxCalls) {
/* 142 */     Preconditions.checkArgument((maxCalls >= 1L), "maxCalls < 1");
/* 143 */     return expireIf((handler, event) -> (handler.getCallCounter() >= paramLong), new ExpiryTestStage[] { ExpiryTestStage.PRE, ExpiryTestStage.POST_HANDLE });
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
/*     */   default MergedSubscription<T> handler(@Nonnull Consumer<? super T> handler) {
/* 213 */     return (MergedSubscription<T>)handlers().consumer(handler).register();
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
/*     */   @Nonnull
/*     */   default MergedSubscription<T> biHandler(@Nonnull BiConsumer<MergedSubscription<T>, ? super T> handler) {
/* 226 */     return (MergedSubscription<T>)handlers().biConsumer(handler).register();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> filter(@Nonnull Predicate<T> paramPredicate);
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> expireIf(@Nonnull BiPredicate<MergedSubscription<T>, T> paramBiPredicate, @Nonnull ExpiryTestStage... paramVarArgs);
/*     */   
/*     */   @Nonnull
/*     */   <E extends Event> io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> bindEvent(@Nonnull Class<E> paramClass, @Nonnull Function<E, T> paramFunction);
/*     */   
/*     */   @Nonnull
/*     */   <E extends Event> io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> bindEvent(@Nonnull Class<E> paramClass, @Nonnull EventPriority paramEventPriority, @Nonnull Function<E, T> paramFunction);
/*     */   
/*     */   @Nonnull
/*     */   io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder<T> exceptionConsumer(@Nonnull BiConsumer<Event, Throwable> paramBiConsumer);
/*     */   
/*     */   @Nonnull
/*     */   MergedHandlerList<T> handlers();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\merged\MergedSubscriptionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */