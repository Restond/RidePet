/*     */ package lumine.utils.events.functional.single;
/*     */ 
/*     */ import io.lumine.utils.events.SingleSubscription;
/*     */ import io.lumine.utils.events.functional.single.SingleSubscriptionBuilderImpl;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.EventExecutor;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class EventListener<T extends Event>
/*     */   implements SingleSubscription<T>, EventExecutor, Listener
/*     */ {
/*     */   private final Class<T> eventClass;
/*     */   private final EventPriority priority;
/*     */   private final BiConsumer<? super T, Throwable> exceptionConsumer;
/*     */   private final Predicate<T>[] filters;
/*     */   private final BiPredicate<SingleSubscription<T>, T>[] preExpiryTests;
/*     */   private final BiPredicate<SingleSubscription<T>, T>[] midExpiryTests;
/*     */   private final BiPredicate<SingleSubscription<T>, T>[] postExpiryTests;
/*     */   private final BiConsumer<SingleSubscription<T>, ? super T>[] handlers;
/*  60 */   private final AtomicLong callCount = new AtomicLong(0L);
/*  61 */   private final AtomicBoolean active = new AtomicBoolean(true);
/*     */ 
/*     */   
/*     */   EventListener(SingleSubscriptionBuilderImpl<T> builder, List<BiConsumer<SingleSubscription<T>, ? super T>> handlers) {
/*  65 */     this.eventClass = builder.eventClass;
/*  66 */     this.priority = builder.priority;
/*  67 */     this.exceptionConsumer = builder.exceptionConsumer;
/*     */     
/*  69 */     this.filters = (Predicate<T>[])builder.filters.toArray((Object[])new Predicate[builder.filters.size()]);
/*  70 */     this.preExpiryTests = (BiPredicate<SingleSubscription<T>, T>[])builder.preExpiryTests.toArray((Object[])new BiPredicate[builder.preExpiryTests.size()]);
/*  71 */     this.midExpiryTests = (BiPredicate<SingleSubscription<T>, T>[])builder.midExpiryTests.toArray((Object[])new BiPredicate[builder.midExpiryTests.size()]);
/*  72 */     this.postExpiryTests = (BiPredicate<SingleSubscription<T>, T>[])builder.postExpiryTests.toArray((Object[])new BiPredicate[builder.postExpiryTests.size()]);
/*  73 */     this.handlers = handlers.<BiConsumer<SingleSubscription<T>, ? super T>>toArray((BiConsumer<SingleSubscription<T>, ? super T>[])new BiConsumer[handlers.size()]);
/*     */   }
/*     */   
/*     */   void register(Plugin plugin) {
/*  77 */     Bukkit.getPluginManager().registerEvent(this.eventClass, this, this.priority, this, plugin, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Listener listener, Event event) {
/*  83 */     if (event.getClass() != this.eventClass) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  88 */     if (!this.active.get()) {
/*  89 */       event.getHandlers().unregister(listener);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  94 */     Event event1 = (Event)this.eventClass.cast(event);
/*     */ 
/*     */     
/*  97 */     for (BiPredicate<SingleSubscription<T>, T> test : this.preExpiryTests) {
/*  98 */       if (test.test(this, (T)event1)) {
/*  99 */         event.getHandlers().unregister(listener);
/* 100 */         this.active.set(false);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 108 */       for (Predicate<T> filter : this.filters) {
/* 109 */         if (!filter.test((T)event1)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 115 */       for (BiPredicate<SingleSubscription<T>, T> test : this.midExpiryTests) {
/* 116 */         if (test.test(this, (T)event1)) {
/* 117 */           event.getHandlers().unregister(listener);
/* 118 */           this.active.set(false);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 124 */       for (BiConsumer<SingleSubscription<T>, ? super T> handler : this.handlers) {
/* 125 */         handler.accept(this, (T)event1);
/*     */       }
/*     */ 
/*     */       
/* 129 */       this.callCount.incrementAndGet();
/* 130 */     } catch (Throwable t) {
/* 131 */       this.exceptionConsumer.accept((T)event1, t);
/*     */     } 
/*     */ 
/*     */     
/* 135 */     for (BiPredicate<SingleSubscription<T>, T> test : this.postExpiryTests) {
/* 136 */       if (test.test(this, (T)event1)) {
/* 137 */         event.getHandlers().unregister(listener);
/* 138 */         this.active.set(false);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Class<T> getEventClass() {
/* 147 */     return this.eventClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 152 */     return this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 157 */     return !this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getCallCounter() {
/* 162 */     return this.callCount.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/* 168 */     if (!this.active.getAndSet(false)) {
/* 169 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     unregisterListener(this.eventClass, this);
/*     */     
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void unregisterListener(Class<? extends Event> eventClass, Listener listener) {
/*     */     try {
/* 184 */       Method getHandlerListMethod = eventClass.getMethod("getHandlerList", new Class[0]);
/* 185 */       HandlerList handlerList = (HandlerList)getHandlerListMethod.invoke(null, new Object[0]);
/* 186 */       handlerList.unregister(listener);
/* 187 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\single\EventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */