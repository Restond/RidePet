/*     */ package lumine.utils.network.messaging.conversation;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import io.lumine.utils.network.messaging.ChannelAgent;
/*     */ import io.lumine.utils.network.messaging.ChannelListener;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationChannel;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationChannelAgent;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationChannelListener;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationMessage;
/*     */ import io.lumine.utils.network.messaging.conversation.SimpleConversationChannel;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Agent<T extends ConversationMessage, R extends ConversationMessage>
/*     */   implements ConversationChannelAgent<T, R>
/*     */ {
/*     */   private final SimpleConversationChannel<T, R> channel;
/*     */   private final ChannelAgent<T> delegateAgent;
/*     */   
/*     */   private Agent(@Nonnull SimpleConversationChannel<T, R> channel) {
/* 175 */     this.channel = channel;
/* 176 */     this.delegateAgent = this.channel.getOutgoingChannel().newAgent();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public ConversationChannel<T, R> getChannel() {
/* 182 */     this.delegateAgent.getChannel();
/* 183 */     return (ConversationChannel<T, R>)this.channel;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Set<ConversationChannelListener<T, R>> getListeners() {
/* 189 */     Set<ChannelListener<T>> listeners = this.delegateAgent.getListeners();
/*     */     
/* 191 */     ImmutableSet.Builder<ConversationChannelListener<T, R>> ret = ImmutableSet.builder();
/* 192 */     for (ChannelListener<T> listener : listeners)
/*     */     {
/* 194 */       ret.add(WrappedListener.access$500((WrappedListener)listener));
/*     */     }
/* 196 */     return (Set<ConversationChannelListener<T, R>>)ret.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasListeners() {
/* 201 */     return this.delegateAgent.hasListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addListener(@Nonnull ConversationChannelListener<T, R> listener) {
/* 206 */     return this.delegateAgent.addListener((ChannelListener)new WrappedListener(this, listener, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeListener(@Nonnull ConversationChannelListener<T, R> listener) {
/* 211 */     Set<ChannelListener<T>> listeners = this.delegateAgent.getListeners();
/* 212 */     for (ChannelListener<T> other : listeners) {
/* 213 */       WrappedListener wrapped = (WrappedListener)other;
/* 214 */       if (WrappedListener.access$500(wrapped) == listener) {
/* 215 */         return this.delegateAgent.removeListener(other);
/*     */       }
/*     */     } 
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 223 */     return this.delegateAgent.terminate();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\SimpleConversationChannel$Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */