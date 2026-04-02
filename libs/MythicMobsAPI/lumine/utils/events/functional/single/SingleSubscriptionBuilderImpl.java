/*     */ package lumine.utils.events.functional.single;
/*     */ 
/*     */ import io.lumine.utils.events.SingleSubscription;
/*     */ import io.lumine.utils.events.functional.ExpiryTestStage;
/*     */ import io.lumine.utils.events.functional.SubscriptionBuilder;
/*     */ import io.lumine.utils.events.functional.single.SingleHandlerList;
/*     */ import io.lumine.utils.events.functional.single.SingleHandlerListImpl;
/*     */ import io.lumine.utils.events.functional.single.SingleSubscriptionBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
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
/*     */ class SingleSubscriptionBuilderImpl<T extends Event>
/*     */   implements SingleSubscriptionBuilder<T>
/*     */ {
/*     */   final Class<T> eventClass;
/*     */   final EventPriority priority;
/*  47 */   BiConsumer<? super T, Throwable> exceptionConsumer = DEFAULT_EXCEPTION_CONSUMER;
/*     */   
/*  49 */   final List<Predicate<T>> filters = new ArrayList<>(3);
/*  50 */   final List<BiPredicate<SingleSubscription<T>, T>> preExpiryTests = new ArrayList<>(0);
/*  51 */   final List<BiPredicate<SingleSubscription<T>, T>> midExpiryTests = new ArrayList<>(0);
/*  52 */   final List<BiPredicate<SingleSubscription<T>, T>> postExpiryTests = new ArrayList<>(0);
/*     */   
/*     */   SingleSubscriptionBuilderImpl(Class<T> eventClass, EventPriority priority) {
/*  55 */     this.eventClass = eventClass;
/*  56 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public SingleSubscriptionBuilder<T> expireIf(@Nonnull BiPredicate<SingleSubscription<T>, T> predicate, @Nonnull ExpiryTestStage... testPoints) {
/*  62 */     Objects.requireNonNull(testPoints, "testPoints");
/*  63 */     Objects.requireNonNull(predicate, "predicate");
/*  64 */     for (ExpiryTestStage testPoint : testPoints) {
/*  65 */       switch (null.$SwitchMap$io$lumine$utils$events$functional$ExpiryTestStage[testPoint.ordinal()]) {
/*     */         case 1:
/*  67 */           this.preExpiryTests.add(predicate);
/*     */           break;
/*     */         case 2:
/*  70 */           this.midExpiryTests.add(predicate);
/*     */           break;
/*     */         case 3:
/*  73 */           this.postExpiryTests.add(predicate);
/*     */           break;
/*     */         default:
/*  76 */           throw new IllegalArgumentException("Unknown ExpiryTestPoint: " + testPoint);
/*     */       } 
/*     */     } 
/*  79 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public SingleSubscriptionBuilder<T> filter(@Nonnull Predicate<T> predicate) {
/*  85 */     Objects.requireNonNull(predicate, "predicate");
/*  86 */     this.filters.add(predicate);
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public SingleSubscriptionBuilder<T> exceptionConsumer(@Nonnull BiConsumer<? super T, Throwable> exceptionConsumer) {
/*  93 */     Objects.requireNonNull(exceptionConsumer, "exceptionConsumer");
/*  94 */     this.exceptionConsumer = exceptionConsumer;
/*  95 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public SingleHandlerList<T> handlers() {
/* 101 */     return (SingleHandlerList<T>)new SingleHandlerListImpl(this);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\single\SingleSubscriptionBuilderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */