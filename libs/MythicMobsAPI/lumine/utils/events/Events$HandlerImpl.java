/*     */ package lumine.utils.events;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import io.lumine.utils.events.Events;
/*     */ import io.lumine.utils.timings.Timings;
/*     */ import io.lumine.utils.timingslib.MCTiming;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import java.util.OptionalLong;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class HandlerImpl<T extends Event>
/*     */   implements Events.Handler<T>, EventExecutor
/*     */ {
/*     */   private final Class<T> eventClass;
/*     */   private final EventPriority priority;
/*     */   private final long expiry;
/*     */   private final long maxCalls;
/*     */   private final BiConsumer<? super T, Throwable> exceptionConsumer;
/*     */   private final List<Predicate<T>> filters;
/*     */   private final BiConsumer<Events.Handler<T>, ? super T> handler;
/*     */   private final MCTiming timing;
/* 596 */   private final Listener listener = (Listener)new Object(this);
/* 597 */   private final AtomicLong callCount = new AtomicLong(0L);
/* 598 */   private final AtomicBoolean active = new AtomicBoolean(true);
/*     */   
/*     */   private HandlerImpl(Events.HandlerBuilderImpl<T> builder, BiConsumer<Events.Handler<T>, ? super T> handler) {
/* 601 */     this.eventClass = Events.HandlerBuilderImpl.access$400(builder);
/* 602 */     this.priority = Events.HandlerBuilderImpl.access$500(builder);
/* 603 */     this.expiry = Events.HandlerBuilderImpl.access$600(builder);
/* 604 */     this.maxCalls = Events.HandlerBuilderImpl.access$700(builder);
/* 605 */     this.exceptionConsumer = Events.HandlerBuilderImpl.access$800(builder);
/* 606 */     this.filters = (List<Predicate<T>>)ImmutableList.copyOf(Events.HandlerBuilderImpl.access$900(builder));
/* 607 */     this.handler = handler;
/* 608 */     this.timing = Timings.get().of("lumine-events: " + Events.access$1000(handler));
/*     */   }
/*     */   
/*     */   private void register(Plugin plugin) {
/* 612 */     Bukkit.getPluginManager().registerEvent(this.eventClass, this.listener, this.priority, this, plugin, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(Listener listener, Event event) throws EventException {
/* 617 */     if (event.getClass() != this.eventClass) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 622 */     if (!this.active.get()) {
/* 623 */       event.getHandlers().unregister(listener);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 628 */     if (this.expiry != -1L) {
/* 629 */       long now = System.currentTimeMillis();
/* 630 */       if (now > this.expiry) {
/* 631 */         event.getHandlers().unregister(listener);
/* 632 */         this.active.set(false);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 638 */     if (tryExpire(listener, event.getHandlers())) {
/*     */       return;
/*     */     }
/*     */     
/* 642 */     Event event1 = (Event)this.eventClass.cast(event);
/*     */     
/* 644 */     for (Predicate<T> filter : this.filters) {
/* 645 */       if (!filter.test((T)event1)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 651 */     handle((T)event1);
/*     */ 
/*     */     
/* 654 */     tryExpire(listener, event.getHandlers());
/*     */   }
/*     */   
/*     */   private boolean tryExpire(Listener listener, HandlerList handlerList) {
/* 658 */     if (this.maxCalls != -1L && 
/* 659 */       this.callCount.get() >= this.maxCalls) {
/* 660 */       handlerList.unregister(listener);
/* 661 */       this.active.set(false);
/* 662 */       return true;
/*     */     } 
/*     */     
/* 665 */     return false;
/*     */   }
/*     */   
/*     */   private void handle(T e) {
/*     */     try {
/* 670 */       try (MCTiming t = this.timing.startTiming()) {
/* 671 */         this.handler.accept(this, e);
/*     */       } 
/*     */       
/* 674 */       this.callCount.incrementAndGet();
/* 675 */     } catch (Throwable t) {
/* 676 */       this.exceptionConsumer.accept(e, t);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<T> getEventClass() {
/* 682 */     return this.eventClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 687 */     return this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTerminated() {
/* 692 */     return !this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getCallCounter() {
/* 697 */     return this.callCount.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public OptionalLong getExpiryTimeMillis() {
/* 702 */     return (this.expiry == -1L) ? OptionalLong.empty() : OptionalLong.of(this.expiry);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/* 708 */     if (!this.active.getAndSet(false)) {
/* 709 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 715 */       Method getHandlerListMethod = this.eventClass.getMethod("getHandlerList", new Class[0]);
/* 716 */       HandlerList handlerList = (HandlerList)getHandlerListMethod.invoke(null, new Object[0]);
/* 717 */       handlerList.unregister(this.listener);
/* 718 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/* 721 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\Events$HandlerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */