/*     */ package lumine.utils.network.messaging;
/*     */ 
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.network.messaging.AbstractMessenger;
/*     */ import io.lumine.utils.network.messaging.Channel;
/*     */ import io.lumine.utils.network.messaging.ChannelAgent;
/*     */ import io.lumine.utils.network.messaging.codec.ByteArrayCodec;
/*     */ import io.lumine.utils.network.messaging.codec.Codec;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import java.util.Base64;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.annotation.Nonnull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class AbstractChannel<T>
/*     */   implements Channel<T>
/*     */ {
/*     */   private final AbstractMessenger messenger;
/*     */   private final String name;
/*     */   private final TypeToken<T> type;
/*     */   private final Codec<T> codec;
/* 120 */   private final Set<AbstractMessenger.AbstractChannelAgent<T>> agents = ConcurrentHashMap.newKeySet();
/*     */   private boolean subscribed = false;
/*     */   
/*     */   private AbstractChannel(AbstractMessenger messenger, String name, TypeToken<T> type) {
/* 124 */     this.messenger = messenger;
/* 125 */     this.name = name;
/* 126 */     this.type = type;
/* 127 */     this.codec = (Codec<T>)new ByteArrayCodec(AbstractMessenger.access$200(type));
/*     */   }
/*     */   
/*     */   private void onIncomingMessage(String sender, byte[] message) {
/*     */     try {
/* 132 */       T decoded = (T)this.codec.decode(message);
/* 133 */       Objects.requireNonNull(decoded, "decoded");
/*     */ 
/*     */ 
/*     */       
/* 137 */       for (AbstractMessenger.AbstractChannelAgent<T> agent : this.agents) {
/*     */         try {
/* 139 */           AbstractMessenger.AbstractChannelAgent.access$300(agent, (ChannelAgent)agent, sender, decoded);
/* 140 */         } catch (Exception e) {
/* 141 */           (new RuntimeException("Unable to pass decoded message to agent: " + decoded, e)).printStackTrace();
/*     */         }
/*     */       
/*     */       } 
/* 145 */     } catch (Exception e) {
/* 146 */       (new RuntimeException("Unable to decode message: " + Base64.getEncoder().encodeToString(message), e)).printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkSubscription() {
/* 151 */     boolean shouldSubscribe = this.agents.stream().anyMatch(AbstractMessenger.AbstractChannelAgent::hasListeners);
/* 152 */     if (shouldSubscribe == this.subscribed) {
/*     */       return;
/*     */     }
/* 155 */     this.subscribed = shouldSubscribe;
/*     */     
/* 157 */     Schedulers.async().run(() -> {
/*     */           try {
/*     */             if (paramBoolean) {
/*     */               AbstractMessenger.access$600(this.messenger).accept(this.name);
/*     */             } else {
/*     */               AbstractMessenger.access$700(this.messenger).accept(this.name);
/*     */             } 
/* 164 */           } catch (Exception e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 172 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeToken<T> getType() {
/* 177 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Codec<T> getCodec() {
/* 183 */     return this.codec;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelAgent<T> newAgent() {
/* 188 */     AbstractMessenger.AbstractChannelAgent<T> agent = new AbstractMessenger.AbstractChannelAgent(this);
/* 189 */     this.agents.add(agent);
/* 190 */     return (ChannelAgent<T>)agent;
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<Void> sendMessage(T message) {
/* 195 */     Objects.requireNonNull(message, "message");
/* 196 */     return Schedulers.async().call(() -> {
/*     */           byte[] buf = this.codec.encode(paramObject);
/*     */           AbstractMessenger.access$500(this.messenger).accept(this.name, buf);
/*     */           return null;
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<Void> sendMessage(String server, T message) {
/* 205 */     Objects.requireNonNull(server, "server");
/* 206 */     Objects.requireNonNull(message, "message");
/* 207 */     return Schedulers.async().call(() -> {
/*     */           byte[] buf = this.codec.encode(paramObject);
/*     */           AbstractMessenger.access$400(this.messenger).accept(paramString, this.name, buf);
/*     */           return null;
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\AbstractMessenger$AbstractChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */