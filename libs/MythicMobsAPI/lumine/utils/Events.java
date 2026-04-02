/*     */ package lumine.utils;
/*     */ 
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.events.functional.merged.MergedSubscriptionBuilder;
/*     */ import io.lumine.utils.events.functional.single.SingleSubscriptionBuilder;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.Bukkit;
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
/*     */ public final class Events
/*     */ {
/*     */   @Nonnull
/*     */   public static <T extends Event> SingleSubscriptionBuilder<T> subscribe(@Nonnull Class<T> eventClass) {
/*  29 */     return SingleSubscriptionBuilder.newBuilder(eventClass);
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
/*     */   public static <T extends Event> SingleSubscriptionBuilder<T> subscribe(@Nonnull Class<T> eventClass, @Nonnull EventPriority priority) {
/*  43 */     return SingleSubscriptionBuilder.newBuilder(eventClass, priority);
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
/*     */   public static <T> MergedSubscriptionBuilder<T> merge(@Nonnull Class<T> handledClass) {
/*  55 */     return MergedSubscriptionBuilder.newBuilder(handledClass);
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
/*     */   public static <T> MergedSubscriptionBuilder<T> merge(@Nonnull TypeToken<T> type) {
/*  67 */     return MergedSubscriptionBuilder.newBuilder(type);
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
/*     */   public static <S extends Event> MergedSubscriptionBuilder<S> merge(@Nonnull Class<S> superClass, @Nonnull Class<? extends S>... eventClasses) {
/*  81 */     return MergedSubscriptionBuilder.newBuilder(superClass, (Class[])eventClasses);
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
/*     */   public static <S extends Event> MergedSubscriptionBuilder<S> merge(@Nonnull Class<S> superClass, @Nonnull EventPriority priority, @Nonnull Class<? extends S>... eventClasses) {
/*  96 */     return MergedSubscriptionBuilder.newBuilder(superClass, priority, (Class[])eventClasses);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void call(@Nonnull Event event) {
/* 105 */     Bukkit.getPluginManager().callEvent(event);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void callAsync(@Nonnull Event event) {
/* 114 */     Schedulers.async().run(() -> call(paramEvent));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void callSync(@Nonnull Event event) {
/* 123 */     Schedulers.sync().run(() -> call(paramEvent));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends Event> T callAndReturn(@Nonnull T event) {
/* 133 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 134 */     return event;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends Event> T callAsyncAndJoin(@Nonnull T event) {
/* 144 */     return (T)Schedulers.async().supply(() -> callAndReturn(paramEvent)).join();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends Event> T callSyncAndJoin(@Nonnull T event) {
/* 154 */     return (T)Schedulers.sync().supply(() -> callAndReturn(paramEvent)).join();
/*     */   }
/*     */   
/*     */   private Events() {
/* 158 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Events.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */