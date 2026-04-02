/*     */ package lumine.utils.events;
/*     */ 
/*     */ import io.lumine.utils.events.CancellationDetector;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   extends CancellationDetector.DelegatedRegisteredListener
/*     */ {
/*     */   null(RegisteredListener delegate) {
/* 114 */     super(delegate);
/*     */   }
/*     */   
/*     */   public void callEvent(Event event) throws EventException {
/* 118 */     if (event instanceof org.bukkit.event.Cancellable) {
/* 119 */       boolean prior = CancellationDetector.access$300(CancellationDetector.this, event);
/*     */       
/* 121 */       listener.callEvent(event);
/*     */ 
/*     */       
/* 124 */       if (!prior && CancellationDetector.access$300(CancellationDetector.this, event)) {
/* 125 */         CancellationDetector.access$400(CancellationDetector.this, getPlugin(), event);
/*     */       }
/*     */     } else {
/* 128 */       listener.callEvent(event);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\CancellationDetector$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */