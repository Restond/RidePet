/*    */ package lumine.utils.events.functional.merged;
/*    */ 
/*    */ import io.lumine.utils.events.MergedSubscription;
/*    */ import io.lumine.utils.events.functional.FunctionalHandlerList;
/*    */ import io.lumine.utils.events.functional.merged.MergedEventListener;
/*    */ import io.lumine.utils.events.functional.merged.MergedHandlerList;
/*    */ import io.lumine.utils.events.functional.merged.MergedSubscriptionBuilderImpl;
/*    */ import io.lumine.utils.plugin.LoaderUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiConsumer;
/*    */ import javax.annotation.Nonnull;
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
/*    */ class MergedHandlerListImpl<T>
/*    */   implements MergedHandlerList<T>
/*    */ {
/*    */   private final MergedSubscriptionBuilderImpl<T> builder;
/* 40 */   private final List<BiConsumer<MergedSubscription<T>, ? super T>> handlers = new ArrayList<>(1);
/*    */   
/*    */   MergedHandlerListImpl(@Nonnull MergedSubscriptionBuilderImpl<T> builder) {
/* 43 */     this.builder = builder;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public MergedHandlerList<T> biConsumer(@Nonnull BiConsumer<MergedSubscription<T>, ? super T> handler) {
/* 49 */     Objects.requireNonNull(handler, "handler");
/* 50 */     this.handlers.add(handler);
/* 51 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public MergedSubscription<T> register() {
/* 57 */     if (this.handlers.isEmpty()) {
/* 58 */       throw new IllegalStateException("No handlers have been registered");
/*    */     }
/*    */     
/* 61 */     MergedEventListener<T> listener = new MergedEventListener(this.builder, this.handlers);
/* 62 */     listener.register((Plugin)LoaderUtils.getPlugin());
/* 63 */     return (MergedSubscription<T>)listener;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\merged\MergedHandlerListImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */