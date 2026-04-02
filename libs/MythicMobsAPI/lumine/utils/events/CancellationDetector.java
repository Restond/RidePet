/*     */ package lumine.utils.events;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.plugin.IllegalPluginAccessException;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CancellationDetector<TEvent extends Event>
/*     */ {
/*     */   private final Class<TEvent> eventClazz;
/*  29 */   private final List<CancelListener<TEvent>> listeners = Lists.newArrayList();
/*     */   
/*     */   private EnumMap<EventPriority, ArrayList<RegisteredListener>> backup;
/*     */ 
/*     */   
/*     */   public CancellationDetector(Class<TEvent> eventClazz) {
/*  35 */     this.eventClazz = eventClazz;
/*  36 */     injectProxy();
/*     */   }
/*     */   
/*     */   public void addListener(CancelListener<TEvent> listener) {
/*  40 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public void removeListener(CancelListener<Event> listener) {
/*  44 */     this.listeners.remove(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   private EnumMap<EventPriority, ArrayList<RegisteredListener>> getSlots(HandlerList list) {
/*     */     try {
/*  50 */       return (EnumMap<EventPriority, ArrayList<RegisteredListener>>)getSlotsField(list).get(list);
/*  51 */     } catch (Exception e) {
/*  52 */       throw new RuntimeException("Unable to retrieve slots.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Field getSlotsField(HandlerList list) {
/*  57 */     if (list == null) {
/*  58 */       throw new IllegalStateException("Detected a NULL handler list.");
/*     */     }
/*     */     try {
/*  61 */       Field slotField = list.getClass().getDeclaredField("handlerslots");
/*     */ 
/*     */       
/*  64 */       slotField.setAccessible(true);
/*  65 */       return slotField;
/*  66 */     } catch (Exception e) {
/*  67 */       throw new IllegalStateException("Unable to intercept 'handlerslot' in " + list.getClass(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void injectProxy() {
/*  72 */     HandlerList list = getHandlerList(this.eventClazz);
/*  73 */     EnumMap<EventPriority, ArrayList<RegisteredListener>> slots = getSlots(list);
/*     */ 
/*     */     
/*  76 */     this.backup = slots.clone();
/*     */     
/*  78 */     synchronized (list) {
/*  79 */       for (EventPriority p : (EventPriority[])slots.keySet().toArray((Object[])new EventPriority[0])) {
/*  80 */         EventPriority priority = p;
/*  81 */         Object object = new Object(this, priority);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 103 */         slots.put(priority, object);
/*     */         
/* 105 */         for (RegisteredListener listener : this.backup.get(priority)) {
/* 106 */           object.add(listener);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private RegisteredListener injectRegisteredListener(RegisteredListener listener) {
/* 114 */     return (RegisteredListener)new Object(this, listener, listener);
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
/*     */   private void invokeCancelled(Plugin plugin, TEvent event) {
/* 135 */     for (CancelListener<TEvent> listener : this.listeners) {
/* 136 */       listener.onCancelled(plugin, (Event)event);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean getCancelState(Event event) {
/* 141 */     return ((Cancellable)event).isCancelled();
/*     */   }
/*     */   
/*     */   public void close() {
/* 145 */     if (this.backup != null) {
/*     */       try {
/* 147 */         HandlerList list = getHandlerList(this.eventClazz);
/* 148 */         getSlotsField(list).set(list, this.backup);
/*     */         
/* 150 */         Field handlers = list.getClass().getDeclaredField("handlers");
/* 151 */         handlers.setAccessible(true);
/* 152 */         handlers.set(list, null);
/*     */       }
/* 154 */       catch (Exception e) {
/* 155 */         throw new RuntimeException("Unable to clean up handler list.", e);
/*     */       } 
/*     */       
/* 158 */       this.backup = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static HandlerList getHandlerList(Class<? extends Event> clazz) {
/* 170 */     while (clazz.getSuperclass() != null && Event.class.isAssignableFrom(clazz.getSuperclass())) {
/*     */       try {
/* 172 */         Method method = clazz.getDeclaredMethod("getHandlerList", new Class[0]);
/* 173 */         method.setAccessible(true);
/* 174 */         return (HandlerList)method.invoke(null, new Object[0]);
/* 175 */       } catch (NoSuchMethodException e) {
/*     */         
/* 177 */         clazz = clazz.getSuperclass().asSubclass(Event.class);
/* 178 */       } catch (Exception e) {
/* 179 */         throw new IllegalPluginAccessException(e.getMessage());
/*     */       } 
/*     */     } 
/* 182 */     throw new IllegalPluginAccessException("Unable to find handler list for event " + clazz
/* 183 */         .getName());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\CancellationDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */