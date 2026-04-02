/*     */ package lumine.utils.events.functional.protocol;
/*     */ 
/*     */ import com.comphenix.protocol.PacketType;
/*     */ import com.comphenix.protocol.events.PacketAdapter;
/*     */ import com.comphenix.protocol.events.PacketEvent;
/*     */ import com.comphenix.protocol.events.PacketListener;
/*     */ import io.lumine.utils.events.ProtocolSubscription;
/*     */ import io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilderImpl;
/*     */ import io.lumine.utils.plugin.LoaderUtils;
/*     */ import io.lumine.utils.protocol.Protocol;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
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
/*     */ class ProtocolListener
/*     */   extends PacketAdapter
/*     */   implements ProtocolSubscription
/*     */ {
/*     */   private final Set<PacketType> types;
/*     */   private final BiConsumer<? super PacketEvent, Throwable> exceptionConsumer;
/*     */   private final Predicate<PacketEvent>[] filters;
/*     */   private final BiPredicate<ProtocolSubscription, PacketEvent>[] preExpiryTests;
/*     */   private final BiPredicate<ProtocolSubscription, PacketEvent>[] midExpiryTests;
/*     */   private final BiPredicate<ProtocolSubscription, PacketEvent>[] postExpiryTests;
/*     */   private final BiConsumer<ProtocolSubscription, ? super PacketEvent>[] handlers;
/*  57 */   private final AtomicLong callCount = new AtomicLong(0L);
/*  58 */   private final AtomicBoolean active = new AtomicBoolean(true);
/*     */ 
/*     */   
/*     */   ProtocolListener(ProtocolSubscriptionBuilderImpl builder, List<BiConsumer<ProtocolSubscription, ? super PacketEvent>> handlers) {
/*  62 */     super((Plugin)LoaderUtils.getPlugin(), builder.priority, builder.types);
/*     */     
/*  64 */     this.types = builder.types;
/*  65 */     this.exceptionConsumer = builder.exceptionConsumer;
/*     */     
/*  67 */     this.filters = (Predicate<PacketEvent>[])builder.filters.toArray((Object[])new Predicate[builder.filters.size()]);
/*  68 */     this.preExpiryTests = (BiPredicate<ProtocolSubscription, PacketEvent>[])builder.preExpiryTests.toArray((Object[])new BiPredicate[builder.preExpiryTests.size()]);
/*  69 */     this.midExpiryTests = (BiPredicate<ProtocolSubscription, PacketEvent>[])builder.midExpiryTests.toArray((Object[])new BiPredicate[builder.midExpiryTests.size()]);
/*  70 */     this.postExpiryTests = (BiPredicate<ProtocolSubscription, PacketEvent>[])builder.postExpiryTests.toArray((Object[])new BiPredicate[builder.postExpiryTests.size()]);
/*  71 */     this.handlers = handlers.<BiConsumer<ProtocolSubscription, ? super PacketEvent>>toArray((BiConsumer<ProtocolSubscription, ? super PacketEvent>[])new BiConsumer[handlers.size()]);
/*     */     
/*  73 */     Protocol.manager().addPacketListener((PacketListener)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPacketReceiving(PacketEvent event) {
/*  78 */     onPacket(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPacketSending(PacketEvent event) {
/*  83 */     onPacket(event);
/*     */   }
/*     */ 
/*     */   
/*     */   private void onPacket(PacketEvent event) {
/*  88 */     if (!this.types.contains(event.getPacketType())) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  93 */     if (!this.active.get()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  98 */     for (BiPredicate<ProtocolSubscription, PacketEvent> test : this.preExpiryTests) {
/*  99 */       if (test.test(this, event)) {
/* 100 */         unregister();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 108 */       for (Predicate<PacketEvent> filter : this.filters) {
/* 109 */         if (!filter.test(event)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 115 */       for (BiPredicate<ProtocolSubscription, PacketEvent> test : this.midExpiryTests) {
/* 116 */         if (test.test(this, event)) {
/* 117 */           unregister();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 123 */       for (BiConsumer<ProtocolSubscription, ? super PacketEvent> handler : this.handlers) {
/* 124 */         handler.accept(this, event);
/*     */       }
/*     */ 
/*     */       
/* 128 */       this.callCount.incrementAndGet();
/* 129 */     } catch (Throwable t) {
/* 130 */       this.exceptionConsumer.accept(event, t);
/*     */     } 
/*     */ 
/*     */     
/* 134 */     for (BiPredicate<ProtocolSubscription, PacketEvent> test : this.postExpiryTests) {
/* 135 */       if (test.test(this, event)) {
/* 136 */         unregister();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Set<PacketType> getPackets() {
/* 145 */     return this.types;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 150 */     return this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 155 */     return !this.active.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getCallCounter() {
/* 160 */     return this.callCount.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/* 166 */     if (!this.active.getAndSet(false)) {
/* 167 */       return false;
/*     */     }
/*     */     
/* 170 */     Protocol.manager().removePacketListener((PacketListener)this);
/* 171 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\functional\protocol\ProtocolListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */