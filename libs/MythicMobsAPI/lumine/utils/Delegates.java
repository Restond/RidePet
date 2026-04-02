/*     */ package lumine.utils;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Delegates
/*     */ {
/*     */   public static <T> Consumer<T> runnableToConsumer(Runnable runnable) {
/*  19 */     return (Consumer<T>)new RunnableToConsumer(runnable, null);
/*     */   }
/*     */   
/*     */   public static Supplier<Void> runnableToSupplier(Runnable runnable) {
/*  23 */     return (Supplier<Void>)new RunnableToSupplier(runnable, null);
/*     */   }
/*     */   
/*     */   public static <T> Supplier<T> callableToSupplier(Callable<T> callable) {
/*  27 */     return (Supplier<T>)new CallableToSupplier(callable, null);
/*     */   }
/*     */   
/*     */   public static <T, U> BiConsumer<T, U> consumerToBiConsumerFirst(Consumer<T> consumer) {
/*  31 */     return (BiConsumer<T, U>)new ConsumerToBiConsumerFirst(consumer, null);
/*     */   }
/*     */   
/*     */   public static <T, U> BiConsumer<T, U> consumerToBiConsumerSecond(Consumer<U> consumer) {
/*  35 */     return (BiConsumer<T, U>)new ConsumerToBiConsumerSecond(consumer, null);
/*     */   }
/*     */   
/*     */   public static <T, U> BiPredicate<T, U> predicateToBiPredicateFirst(Predicate<T> predicate) {
/*  39 */     return (BiPredicate<T, U>)new PredicateToBiPredicateFirst(predicate, null);
/*     */   }
/*     */   
/*     */   public static <T, U> BiPredicate<T, U> predicateToBiPredicateSecond(Predicate<U> predicate) {
/*  43 */     return (BiPredicate<T, U>)new PredicateToBiPredicateSecond(predicate, null);
/*     */   }
/*     */   
/*     */   public static <T, U> Function<T, U> consumerToFunction(Consumer<T> consumer) {
/*  47 */     return (Function<T, U>)new ConsumerToFunction(consumer, null);
/*     */   }
/*     */   
/*     */   public static <T, U> Function<T, U> runnableToFunction(Runnable runnable) {
/*  51 */     return (Function<T, U>)new RunnableToFunction(runnable, null);
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
/*     */   private Delegates() {
/* 181 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Delegates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */