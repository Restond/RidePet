/*     */ package lumine.utils.events.functional.merged;
/*     */ 
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.events.MergedSubscription;
/*     */ import io.lumine.utils.events.functional.ExpiryTestStage;
/*     */ import io.lumine.utils.events.functional.SubscriptionBuilder;
/*     */ import io.lumine.utils.events.functional.merged.MergedHandlerList;
/*     */ import io.lumine.utils.events.functional.merged.MergedHandlerListImpl;
/*     */ import io.lumine.utils.events.functional.merged.MergedHandlerMapping;
/*     */ import io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
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
/*     */ class MergedSubscriptionBuilderImpl<T>
/*     */   implements MergedSubscriptionBuilder<T>
/*     */ {
/*     */   final TypeToken<T> handledClass;
/*  50 */   final Map<Class<? extends Event>, MergedHandlerMapping<T, ? extends Event>> mappings = new HashMap<>();
/*     */   
/*  52 */   BiConsumer<? super Event, Throwable> exceptionConsumer = DEFAULT_EXCEPTION_CONSUMER;
/*     */   
/*  54 */   final List<Predicate<T>> filters = new ArrayList<>();
/*  55 */   final List<BiPredicate<MergedSubscription<T>, T>> preExpiryTests = new ArrayList<>(0);
/*  56 */   final List<BiPredicate<MergedSubscription<T>, T>> midExpiryTests = new ArrayList<>(0);
/*  57 */   final List<BiPredicate<MergedSubscription<T>, T>> postExpiryTests = new ArrayList<>(0);
/*     */   
/*     */   MergedSubscriptionBuilderImpl(TypeToken<T> handledClass) {
/*  60 */     this.handledClass = handledClass;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <E extends Event> MergedSubscriptionBuilder<T> bindEvent(@Nonnull Class<E> eventClass, @Nonnull Function<E, T> function) {
/*  66 */     return bindEvent(eventClass, EventPriority.NORMAL, function);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <E extends Event> MergedSubscriptionBuilder<T> bindEvent(@Nonnull Class<E> eventClass, @Nonnull EventPriority priority, @Nonnull Function<E, T> function) {
/*  72 */     Objects.requireNonNull(eventClass, "eventClass");
/*  73 */     Objects.requireNonNull(priority, "priority");
/*  74 */     Objects.requireNonNull(function, "function");
/*     */     
/*  76 */     this.mappings.put(eventClass, new MergedHandlerMapping(priority, function));
/*  77 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public MergedSubscriptionBuilder<T> expireIf(@Nonnull BiPredicate<MergedSubscription<T>, T> predicate, @Nonnull ExpiryTestStage... testPoints) {
/*  83 */     Objects.requireNonNull(testPoints, "testPoints");
/*  84 */     Objects.requireNonNull(predicate, "predicate");
/*  85 */     for (ExpiryTestStage testPoint : testPoints) {
/*  86 */       switch (null.$SwitchMap$io$lumine$utils$events$functional$ExpiryTestStage[testPoint.ordinal()]) {
/*     */         case 1:
/*  88 */           this.preExpiryTests.add(predicate);
/*     */           break;
/*     */         case 2:
/*  91 */           this.midExpiryTests.add(predicate);
/*     */           break;
/*     */         case 3:
/*  94 */           this.postExpiryTests.add(predicate);
/*     */           break;
/*     */         default:
/*  97 */           throw new IllegalArgumentException("Unknown ExpiryTestPoint: " + testPoint);
/*     */       } 
/*     */     } 
/* 100 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public MergedSubscriptionBuilder<T> filter(@Nonnull Predicate<T> predicate) {
/* 106 */     Objects.requireNonNull(predicate, "predicate");
/* 107 */     this.filters.add(predicate);
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public MergedSubscriptionBuilder<T> exceptionConsumer(@Nonnull BiConsumer<Event, Throwable> exceptionConsumer) {
/* 114 */     Objects.requireNonNull(exceptionConsumer, "exceptionConsumer");
/* 115 */     this.exceptionConsumer = exceptionConsumer;
/* 116 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public MergedHandlerList<T> handlers() {
/* 122 */     if (this.mappings.isEmpty()) {
/* 123 */       throw new IllegalStateException("No mappings were created");
/*     */     }
/*     */     
/* 126 */     return (MergedHandlerList<T>)new MergedHandlerListImpl(this);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\merged\MergedSubscriptionBuilderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */