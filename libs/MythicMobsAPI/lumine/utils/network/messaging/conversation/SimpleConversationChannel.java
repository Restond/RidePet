/*     */ package lumine.utils.network.messaging.conversation;
/*     */ 
/*     */ import com.google.common.collect.Multimaps;
/*     */ import com.google.common.collect.SetMultimap;
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.network.messaging.Channel;
/*     */ import io.lumine.utils.network.messaging.ChannelAgent;
/*     */ import io.lumine.utils.network.messaging.ChannelListener;
/*     */ import io.lumine.utils.network.messaging.Messenger;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationChannel;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationChannelAgent;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationMessage;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationReplyListener;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
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
/*     */ public class SimpleConversationChannel<T extends ConversationMessage, R extends ConversationMessage>
/*     */   implements ConversationChannel<T, R>
/*     */ {
/*     */   private final String name;
/*     */   private final Channel<T> outgoingChannel;
/*     */   private final Channel<R> replyChannel;
/*  38 */   private final Set<Agent<T, R>> agents = ConcurrentHashMap.newKeySet();
/*     */   
/*  40 */   private final ScheduledExecutorService replyTimeoutExecutor = Executors.newSingleThreadScheduledExecutor();
/*     */   private final ChannelAgent<R> replyAgent;
/*  42 */   private final SetMultimap<UUID, ReplyListenerRegistration<R>> replyListeners = Multimaps.newSetMultimap(new ConcurrentHashMap<>(), ConcurrentHashMap::newKeySet);
/*     */   
/*     */   public SimpleConversationChannel(Messenger messenger, String name, TypeToken<T> outgoingType, TypeToken<R> replyType) {
/*  45 */     this.name = name;
/*  46 */     this.outgoingChannel = messenger.getChannel(name + "-o", outgoingType);
/*  47 */     this.replyChannel = messenger.getChannel(name + "-r", replyType);
/*     */     
/*  49 */     this.replyAgent = this.replyChannel.newAgent((ChannelListener)new ReplyListener(this, null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public String getName() {
/* 115 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Channel<T> getOutgoingChannel() {
/* 121 */     return this.outgoingChannel;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Channel<R> getReplyChannel() {
/* 127 */     return this.replyChannel;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ConversationChannelAgent<T, R> newAgent() {
/* 133 */     Agent<T, R> agent = new Agent(this, null);
/* 134 */     this.agents.add(agent);
/* 135 */     return (ConversationChannelAgent<T, R>)agent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<Void> sendMessage(@Nonnull T message, @Nonnull ConversationReplyListener<R> replyListener, long timeoutDuration, @Nonnull TimeUnit unit) {
/* 142 */     ReplyListenerRegistration<R> listenerRegistration = new ReplyListenerRegistration(replyListener, null);
/* 143 */     ReplyListenerRegistration.access$402(listenerRegistration, this.replyTimeoutExecutor.schedule(listenerRegistration::timeout, timeoutDuration, unit));
/* 144 */     this.replyListeners.put(message.getConversationId(), listenerRegistration);
/*     */ 
/*     */     
/* 147 */     return this.outgoingChannel.sendMessage(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<Void> sendMessage(@Nonnull String server, @Nonnull T message, @Nonnull ConversationReplyListener<R> replyListener, long timeoutDuration, @Nonnull TimeUnit unit) {
/* 154 */     ReplyListenerRegistration<R> listenerRegistration = new ReplyListenerRegistration(replyListener, null);
/* 155 */     ReplyListenerRegistration.access$402(listenerRegistration, this.replyTimeoutExecutor.schedule(listenerRegistration::timeout, timeoutDuration, unit));
/* 156 */     this.replyListeners.put(message.getConversationId(), listenerRegistration);
/*     */ 
/*     */     
/* 159 */     return this.outgoingChannel.sendMessage(server, message);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 164 */     this.replyAgent.terminate();
/* 165 */     this.replyTimeoutExecutor.shutdown();
/* 166 */     this.agents.forEach(Agent::terminate);
/* 167 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\SimpleConversationChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */