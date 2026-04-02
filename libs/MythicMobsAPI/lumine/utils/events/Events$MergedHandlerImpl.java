/*     */ package lumine.utils.events;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.events.Events;
/*     */ import io.lumine.utils.timings.Timings;
/*     */ import io.lumine.utils.timingslib.MCTiming;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.OptionalLong;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MergedHandlerImpl<T>
/*     */   implements Events.MergedHandler<T>, EventExecutor
/*     */ {
/*     */   private final TypeToken<T> handledClass;
/*     */   private final Map<Class<? extends Event>, Events.HandlerMapping<T, ? extends Event>> mappings;
/*     */   private final long expiry;
/*     */   private final long maxCalls;
/*     */   private final BiConsumer<Event, Throwable> exceptionConsumer;
/*     */   private final List<Predicate<T>> filters;
/*     */   private final BiConsumer<Events.MergedHandler<T>, ? super T> handler;
/*     */   private final MCTiming timing;
/* 736 */   private final Listener listener = (Listener)new Object(this);
/* 737 */   private final AtomicLong callCount = new AtomicLong(0L);
/* 738 */   private final AtomicBoolean active = new AtomicBoolean(true);
/*     */   
/*     */   private MergedHandlerImpl(Events.MergedHandlerBuilderImpl<T> builder, BiConsumer<Events.MergedHandler<T>, ? super T> handler) {
/* 741 */     this.handledClass = Events.MergedHandlerBuilderImpl.access$1100(builder);
/* 742 */     this.mappings = (Map<Class<? extends Event>, Events.HandlerMapping<T, ? extends Event>>)ImmutableMap.copyOf(Events.MergedHandlerBuilderImpl.access$1200(builder));
/* 743 */     this.expiry = Events.MergedHandlerBuilderImpl.access$1300(builder);
/* 744 */     this.maxCalls = Events.MergedHandlerBuilderImpl.access$1400(builder);
/* 745 */     this.exceptionConsumer = Events.MergedHandlerBuilderImpl.access$1500(builder);
/* 746 */     this.filters = (List<Predicate<T>>)ImmutableList.copyOf(Events.MergedHandlerBuilderImpl.access$1600(builder));
/* 747 */     this.handler = handler;
/* 748 */     this.timing = Timings.get().of("lumine-events: " + Events.access$1000(handler));
/*     */   }
/*     */   
/*     */   private void register(Plugin plugin) {
/* 752 */     for (Map.Entry<Class<? extends Event>, Events.HandlerMapping<T, ? extends Event>> ent : this.mappings.entrySet()) {
/* 753 */       Bukkit.getPluginManager().registerEvent(ent.getKey(), this.listener, ((Events.HandlerMapping)ent.getValue()).getPriority(), this, plugin, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(Listener listener, Event event) throws EventException {
/* 759 */     Function<Object, T> function = null;
/*     */     
/* 761 */     for (Map.Entry<Class<? extends Event>, Events.HandlerMapping<T, ? extends Event>> ent : this.mappings.entrySet()) {
/* 762 */       if (event.getClass() == ent.getKey()) {
/* 763 */         function = ((Events.HandlerMapping)ent.getValue()).getFunction();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 768 */     if (function == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 773 */     if (!this.active.get()) {
/* 774 */       event.getHandlers().unregister(listener);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 779 */     if (tryExpire()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 784 */     if (this.maxCalls != -1L && 
/* 785 */       this.callCount.get() >= this.maxCalls) {
/* 786 */       unregister();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 791 */     T eventInstance = function.apply(event);
/*     */     
/* 793 */     for (Predicate<T> filter : this.filters) {
/* 794 */       if (!filter.test(eventInstance)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 800 */     handle(event, eventInstance);
/*     */ 
/*     */     
/* 803 */     tryExpire();
/*     */   }
/*     */   
/*     */   private boolean tryExpire() {
/* 807 */     if (this.maxCalls != -1L && 
/* 808 */       this.callCount.get() >= this.maxCalls) {
/* 809 */       unregister();
/* 810 */       return true;
/*     */     } 
/*     */     
/* 813 */     return false;
/*     */   }
/*     */   
/*     */   private void handle(Event event, T e) {
/*     */     try {
/* 818 */       try (MCTiming t = this.timing.startTiming()) {
/* 819 */         this.handler.accept(this, e);
/*     */       } 
/*     */       
/* 822 */       this.callCount.incrementAndGet();
/* 823 */     } catch (Throwable t) {
/* 824 */       this.exceptionConsumer.accept(event, t);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 830 */     return this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTerminated() {
/* 835 */     return !this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getCallCounter() {
/* 840 */     return this.callCount.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public OptionalLong getExpiryTimeMillis() {
/* 845 */     return (this.expiry == -1L) ? OptionalLong.empty() : OptionalLong.of(this.expiry);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/* 851 */     if (!this.active.getAndSet(false)) {
/* 852 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 857 */     for (Class<? extends Event> clazz : this.mappings.keySet()) {
/*     */       try {
/* 859 */         Method getHandlerListMethod = clazz.getMethod("getHandlerList", new Class[0]);
/* 860 */         HandlerList handlerList = (HandlerList)getHandlerListMethod.invoke(null, new Object[0]);
/* 861 */         handlerList.unregister(this.listener);
/* 862 */       } catch (Throwable throwable) {}
/*     */     } 
/*     */ 
/*     */     
/* 866 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<? super T> getHandledClass() {
/* 871 */     return this.handledClass.getRawType();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Class<? extends Event>> getEventClasses() {
/* 876 */     return this.mappings.keySet();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\Events$MergedHandlerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */