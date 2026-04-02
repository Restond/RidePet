/*    */ package lumine.utils.events.functional;
/*    */ 
/*    */ import io.lumine.utils.logging.Log;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nonnull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface SubscriptionBuilder<T>
/*    */ {
/*    */   public static final BiConsumer<Object, Throwable> DEFAULT_EXCEPTION_CONSUMER;
/*    */   
/*    */   static {
/* 18 */     DEFAULT_EXCEPTION_CONSUMER = ((event, throwable) -> {
/*    */         Log.severe("[EVENTS] Exception thrown whilst handling event: " + event.getClass().getName());
/*    */         throwable.printStackTrace();
/*    */       });
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   io.lumine.utils.events.functional.SubscriptionBuilder<T> filter(@Nonnull Predicate<T> paramPredicate);
/*    */   
/*    */   @Nonnull
/*    */   io.lumine.utils.events.functional.SubscriptionBuilder<T> expireAfter(long paramLong);
/*    */   
/*    */   @Nonnull
/*    */   io.lumine.utils.events.functional.SubscriptionBuilder<T> expireAfter(long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*    */   
/*    */   @Nonnull
/*    */   io.lumine.utils.events.functional.SubscriptionBuilder<T> expireIf(@Nonnull Predicate<T> paramPredicate);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\SubscriptionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */