/*     */ package lumine.utils.network.messaging.conversation;
/*     */ 
/*     */ import io.lumine.utils.network.messaging.ChannelAgent;
/*     */ import io.lumine.utils.network.messaging.ChannelListener;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationChannelAgent;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationChannelListener;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationMessage;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationReply;
/*     */ import io.lumine.utils.network.messaging.conversation.SimpleConversationChannel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class WrappedListener
/*     */   implements ChannelListener<T>
/*     */ {
/*     */   private final ConversationChannelListener<T, R> delegate;
/*     */   
/*     */   private WrappedListener(ConversationChannelListener<T, R> delegate) {
/* 230 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onMessage(ChannelAgent agent, String sender, T message) {
/* 236 */     ConversationReply<R> reply = this.delegate.onMessage((ConversationChannelAgent)SimpleConversationChannel.Agent.this, sender, (ConversationMessage)message);
/* 237 */     if (reply.hasReply())
/* 238 */       reply.getReply().thenAcceptAsync(m -> {
/*     */             if (m != null)
/*     */               SimpleConversationChannel.access$800(SimpleConversationChannel.Agent.access$700(SimpleConversationChannel.Agent.this)).sendMessage(paramString, m); 
/*     */           }); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\SimpleConversationChannel$Agent$WrappedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */