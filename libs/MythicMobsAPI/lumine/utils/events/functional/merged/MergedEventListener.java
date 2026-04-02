/*     */ package lumine.utils.events.functional.merged;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.events.MergedSubscription;
/*     */ import io.lumine.utils.events.functional.merged.MergedHandlerMapping;
/*     */ import io.lumine.utils.events.functional.merged.MergedSubscriptionBuilderImpl;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
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
/*     */ class MergedEventListener<T>
/*     */   implements MergedSubscription<T>, EventExecutor, Listener
/*     */ {
/*     */   private final TypeToken<T> handledClass;
/*     */   private final Map<Class<? extends Event>, MergedHandlerMapping<T, ? extends Event>> mappings;
/*     */   private final BiConsumer<? super Event, Throwable> exceptionConsumer;
/*     */   private final Predicate<T>[] filters;
/*     */   private final BiPredicate<MergedSubscription<T>, T>[] preExpiryTests;
/*     */   private final BiPredicate<MergedSubscription<T>, T>[] midExpiryTests;
/*     */   private final BiPredicate<MergedSubscription<T>, T>[] postExpiryTests;
/*     */   private final BiConsumer<MergedSubscription<T>, ? super T>[] handlers;
/*  65 */   private final AtomicLong callCount = new AtomicLong(0L);
/*  66 */   private final AtomicBoolean active = new AtomicBoolean(true);
/*     */ 
/*     */   
/*     */   MergedEventListener(MergedSubscriptionBuilderImpl<T> builder, List<BiConsumer<MergedSubscription<T>, ? super T>> handlers) {
/*  70 */     this.handledClass = builder.handledClass;
/*  71 */     this.mappings = (Map<Class<? extends Event>, MergedHandlerMapping<T, ? extends Event>>)ImmutableMap.copyOf(builder.mappings);
/*  72 */     this.exceptionConsumer = builder.exceptionConsumer;
/*     */     
/*  74 */     this.filters = (Predicate<T>[])builder.filters.toArray((Object[])new Predicate[builder.filters.size()]);
/*  75 */     this.preExpiryTests = (BiPredicate<MergedSubscription<T>, T>[])builder.preExpiryTests.toArray((Object[])new BiPredicate[builder.preExpiryTests.size()]);
/*  76 */     this.midExpiryTests = (BiPredicate<MergedSubscription<T>, T>[])builder.midExpiryTests.toArray((Object[])new BiPredicate[builder.midExpiryTests.size()]);
/*  77 */     this.postExpiryTests = (BiPredicate<MergedSubscription<T>, T>[])builder.postExpiryTests.toArray((Object[])new BiPredicate[builder.postExpiryTests.size()]);
/*  78 */     this.handlers = handlers.<BiConsumer<MergedSubscription<T>, ? super T>>toArray((BiConsumer<MergedSubscription<T>, ? super T>[])new BiConsumer[handlers.size()]);
/*     */   }
/*     */   
/*     */   void register(Plugin plugin) {
/*  82 */     for (Map.Entry<Class<? extends Event>, MergedHandlerMapping<T, ? extends Event>> ent : this.mappings.entrySet()) {
/*  83 */       Bukkit.getPluginManager().registerEvent(ent.getKey(), this, ((MergedHandlerMapping)ent.getValue()).getPriority(), this, plugin, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(Listener listener, Event event) {
/*  89 */     Function<Object, T> function = null;
/*     */     
/*  91 */     for (Map.Entry<Class<? extends Event>, MergedHandlerMapping<T, ? extends Event>> ent : this.mappings.entrySet()) {
/*  92 */       if (event.getClass() == ent.getKey()) {
/*  93 */         function = ((MergedHandlerMapping)ent.getValue()).getFunction();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  98 */     if (function == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 103 */     if (!this.active.get()) {
/* 104 */       event.getHandlers().unregister(listener);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 109 */     T handledInstance = function.apply(event);
/*     */ 
/*     */     
/* 112 */     for (BiPredicate<MergedSubscription<T>, T> test : this.preExpiryTests) {
/* 113 */       if (test.test(this, handledInstance)) {
/* 114 */         event.getHandlers().unregister(listener);
/* 115 */         this.active.set(false);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 123 */       for (Predicate<T> filter : this.filters) {
/* 124 */         if (!filter.test(handledInstance)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 130 */       for (BiPredicate<MergedSubscription<T>, T> test : this.midExpiryTests) {
/* 131 */         if (test.test(this, handledInstance)) {
/* 132 */           event.getHandlers().unregister(listener);
/* 133 */           this.active.set(false);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       for (BiConsumer<MergedSubscription<T>, ? super T> handler : this.handlers) {
/* 140 */         handler.accept(this, handledInstance);
/*     */       }
/*     */ 
/*     */       
/* 144 */       this.callCount.incrementAndGet();
/* 145 */     } catch (Throwable t) {
/* 146 */       this.exceptionConsumer.accept(event, t);
/*     */     } 
/*     */ 
/*     */     
/* 150 */     for (BiPredicate<MergedSubscription<T>, T> test : this.postExpiryTests) {
/* 151 */       if (test.test(this, handledInstance)) {
/* 152 */         event.getHandlers().unregister(listener);
/* 153 */         this.active.set(false);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 161 */     return this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 166 */     return !this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getCallCounter() {
/* 171 */     return this.callCount.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/* 177 */     if (!this.active.getAndSet(false)) {
/* 178 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     for (Class<? extends Event> clazz : this.mappings.keySet()) {
/* 185 */       unregisterListener(clazz, this);
/*     */     }
/*     */     
/* 188 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Class<? super T> getHandledClass() {
/* 194 */     return this.handledClass.getRawType();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Set<Class<? extends Event>> getEventClasses() {
/* 200 */     return this.mappings.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void unregisterListener(Class<? extends Event> eventClass, Listener listener) {
/*     */     try {
/* 207 */       Method getHandlerListMethod = eventClass.getMethod("getHandlerList", new Class[0]);
/* 208 */       HandlerList handlerList = (HandlerList)getHandlerListMethod.invoke(null, new Object[0]);
/* 209 */       handlerList.unregister(listener);
/* 210 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\merged\MergedEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */