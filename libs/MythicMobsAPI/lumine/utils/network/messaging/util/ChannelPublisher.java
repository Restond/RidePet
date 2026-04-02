/*    */ package lumine.utils.network.messaging.util;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.utils.network.messaging.Channel;
/*    */ import io.lumine.utils.promise.ThreadContext;
/*    */ import io.lumine.utils.tasks.Task;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import java.util.Objects;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nonnull;
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
/*    */ public final class ChannelPublisher<T>
/*    */   implements Terminable
/*    */ {
/*    */   private final Channel<T> channel;
/*    */   private final Supplier<? extends T> supplier;
/*    */   private final Task task;
/*    */   
/*    */   @Nonnull
/*    */   public static <T> io.lumine.utils.network.messaging.util.ChannelPublisher<T> create(@Nonnull Channel<T> channel, long duration, @Nonnull TimeUnit unit, @Nonnull ThreadContext threadContext, @Nonnull Supplier<? extends T> supplier) {
/* 35 */     Objects.requireNonNull(channel, "channel");
/* 36 */     Objects.requireNonNull(unit, "unit");
/* 37 */     Objects.requireNonNull(threadContext, "threadContext");
/* 38 */     Objects.requireNonNull(supplier, "supplier");
/*    */     
/* 40 */     return new io.lumine.utils.network.messaging.util.ChannelPublisher<>(channel, supplier, duration, unit, threadContext);
/*    */   }
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
/*    */   @Nonnull
/*    */   public static <T> io.lumine.utils.network.messaging.util.ChannelPublisher<T> create(@Nonnull Channel<T> channel, long duration, @Nonnull TimeUnit unit, @Nonnull Supplier<? extends T> supplier) {
/* 55 */     return create(channel, duration, unit, ThreadContext.ASYNC, supplier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private ChannelPublisher(Channel<T> channel, Supplier<? extends T> supplier, long duration, TimeUnit unit, ThreadContext threadContext) {
/* 63 */     this.channel = channel;
/* 64 */     this.supplier = supplier;
/* 65 */     this
/*    */ 
/*    */       
/* 68 */       .task = Schedulers.builder().on(threadContext).afterAndEvery(duration, unit).run(this::submit);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Channel<T> getChannel() {
/* 77 */     return this.channel;
/*    */   }
/*    */   
/*    */   private void submit() {
/* 81 */     this.channel.sendMessage(this.supplier.get());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean terminate() {
/* 86 */     return this.task.terminate();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messagin\\util\ChannelPublisher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */