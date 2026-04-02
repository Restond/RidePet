/*    */ package lumine.utils.events.functional.merged;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventPriority;
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
/*    */ class MergedHandlerMapping<T, E extends Event>
/*    */ {
/*    */   private final EventPriority priority;
/*    */   private final Function<Object, T> function;
/*    */   
/*    */   MergedHandlerMapping(EventPriority priority, Function<E, T> function) {
/* 38 */     this.priority = priority;
/*    */     
/* 40 */     this.function = (o -> paramFunction.apply(o));
/*    */   }
/*    */   
/*    */   public Function<Object, T> getFunction() {
/* 44 */     return this.function;
/*    */   }
/*    */   
/*    */   public EventPriority getPriority() {
/* 48 */     return this.priority;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\merged\MergedHandlerMapping.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */