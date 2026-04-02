/*     */ package lumine.utils.network.messaging;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.network.messaging.AbstractMessenger;
/*     */ import io.lumine.utils.network.messaging.Channel;
/*     */ import io.lumine.utils.network.messaging.ChannelAgent;
/*     */ import io.lumine.utils.network.messaging.ChannelListener;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class AbstractChannelAgent<T>
/*     */   implements ChannelAgent<T>
/*     */ {
/*     */   @Nullable
/*     */   private AbstractMessenger.AbstractChannel<T> channel;
/* 218 */   private final Set<ChannelListener<T>> listeners = ConcurrentHashMap.newKeySet();
/*     */   
/*     */   AbstractChannelAgent(AbstractMessenger.AbstractChannel<T> channel) {
/* 221 */     this.channel = channel;
/*     */   }
/*     */   
/*     */   private void onIncomingMessage(ChannelAgent agent, String sender, T data) {
/* 225 */     for (Iterator<ChannelListener<T>> iterator = this.listeners.iterator(); iterator.hasNext(); ) { ChannelListener<T> listener = iterator.next();
/* 226 */       Schedulers.async().run(() -> {
/*     */             try {
/*     */               paramChannelListener.onMessage(paramChannelAgent, paramString, paramObject);
/* 229 */             } catch (Exception e) {
/*     */               (new RuntimeException("Unable to pass decoded message to listener: " + paramChannelListener, e)).printStackTrace();
/*     */             } 
/*     */           }); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public Channel<T> getChannel() {
/* 238 */     Preconditions.checkState((this.channel != null), "agent not active");
/* 239 */     return (Channel<T>)this.channel;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<ChannelListener<T>> getListeners() {
/* 244 */     Preconditions.checkState((this.channel != null), "agent not active");
/* 245 */     return (Set<ChannelListener<T>>)ImmutableSet.copyOf(this.listeners);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasListeners() {
/* 250 */     return !this.listeners.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addListener(ChannelListener<T> listener) {
/* 255 */     Preconditions.checkState((this.channel != null), "agent not active");
/*     */     try {
/* 257 */       return this.listeners.add(listener);
/*     */     } finally {
/* 259 */       AbstractMessenger.AbstractChannel.access$800(this.channel);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeListener(ChannelListener<T> listener) {
/* 265 */     Preconditions.checkState((this.channel != null), "agent not active");
/*     */     try {
/* 267 */       return this.listeners.remove(listener);
/*     */     } finally {
/* 269 */       AbstractMessenger.AbstractChannel.access$800(this.channel);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 275 */     if (this.channel == null) {
/* 276 */       return true;
/*     */     }
/*     */     
/* 279 */     this.listeners.clear();
/* 280 */     AbstractMessenger.AbstractChannel.access$900(this.channel).remove(this);
/* 281 */     AbstractMessenger.AbstractChannel.access$800(this.channel);
/* 282 */     this.channel = null;
/* 283 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\AbstractMessenger$AbstractChannelAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */