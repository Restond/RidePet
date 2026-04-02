/*    */ package lumine.utils.events.filter;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nonnull;
/*    */ import org.bukkit.event.Cancellable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EventHandlers
/*    */ {
/*    */   private static final Consumer<? extends Cancellable> SET_CANCELLED;
/*    */   private static final Consumer<? extends Cancellable> UNSET_CANCELLED;
/*    */   
/*    */   static {
/* 40 */     SET_CANCELLED = (e -> e.setCancelled(true));
/* 41 */     UNSET_CANCELLED = (e -> e.setCancelled(false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public static <T extends Cancellable> Consumer<T> cancel() {
/* 51 */     return (Consumer)SET_CANCELLED;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public static <T extends Cancellable> Consumer<T> uncancel() {
/* 62 */     return (Consumer)UNSET_CANCELLED;
/*    */   }
/*    */   
/*    */   private EventHandlers() {
/* 66 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\filter\EventHandlers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */