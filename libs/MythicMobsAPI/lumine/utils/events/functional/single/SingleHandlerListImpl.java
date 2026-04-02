/*    */ package lumine.utils.events.functional.single;
/*    */ 
/*    */ import io.lumine.utils.events.SingleSubscription;
/*    */ import io.lumine.utils.events.functional.FunctionalHandlerList;
/*    */ import io.lumine.utils.events.functional.single.EventListener;
/*    */ import io.lumine.utils.events.functional.single.SingleHandlerList;
/*    */ import io.lumine.utils.events.functional.single.SingleSubscriptionBuilderImpl;
/*    */ import io.lumine.utils.plugin.LoaderUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiConsumer;
/*    */ import javax.annotation.Nonnull;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.plugin.Plugin;
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
/*    */ class SingleHandlerListImpl<T extends Event>
/*    */   implements SingleHandlerList<T>
/*    */ {
/*    */   private final SingleSubscriptionBuilderImpl<T> builder;
/* 42 */   private final List<BiConsumer<SingleSubscription<T>, ? super T>> handlers = new ArrayList<>(1);
/*    */   
/*    */   SingleHandlerListImpl(@Nonnull SingleSubscriptionBuilderImpl<T> builder) {
/* 45 */     this.builder = builder;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public SingleHandlerList<T> biConsumer(@Nonnull BiConsumer<SingleSubscription<T>, ? super T> handler) {
/* 51 */     Objects.requireNonNull(handler, "handler");
/* 52 */     this.handlers.add(handler);
/* 53 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public SingleSubscription<T> register() {
/* 59 */     if (this.handlers.isEmpty()) {
/* 60 */       throw new IllegalStateException("No handlers have been registered");
/*    */     }
/*    */     
/* 63 */     EventListener<T> listener = new EventListener(this.builder, this.handlers);
/* 64 */     listener.register((Plugin)LoaderUtils.getPlugin());
/* 65 */     return (SingleSubscription<T>)listener;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\single\SingleHandlerListImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */