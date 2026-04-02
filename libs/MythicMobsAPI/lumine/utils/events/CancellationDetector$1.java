/*     */ package lumine.utils.events;
/*     */ 
/*     */ import io.lumine.utils.events.CancellationDetector;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.bukkit.event.EventPriority;
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
/*     */ class null
/*     */   extends ArrayList<RegisteredListener>
/*     */ {
/*     */   private static final long serialVersionUID = 7869505892922082581L;
/*     */   
/*     */   public boolean add(RegisteredListener e) {
/*  86 */     super.add(CancellationDetector.access$000(CancellationDetector.this, e));
/*  87 */     return ((ArrayList<RegisteredListener>)CancellationDetector.access$100(CancellationDetector.this).get(priority)).add(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object listener) {
/*  93 */     for (Iterator<RegisteredListener> it = iterator(); it.hasNext(); ) {
/*  94 */       CancellationDetector.DelegatedRegisteredListener delegated = (CancellationDetector.DelegatedRegisteredListener)it.next();
/*  95 */       if (CancellationDetector.DelegatedRegisteredListener.access$200(delegated) == listener) {
/*  96 */         it.remove();
/*     */         break;
/*     */       } 
/*     */     } 
/* 100 */     return ((ArrayList)CancellationDetector.access$100(CancellationDetector.this).get(priority)).remove(listener);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\CancellationDetector$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */