/*    */ package lumine.utils.network.messaging.conversation;
/*    */ 
/*    */ import io.lumine.utils.network.messaging.Channel;
/*    */ import io.lumine.utils.network.messaging.conversation.ConversationChannelAgent;
/*    */ import io.lumine.utils.network.messaging.conversation.ConversationChannelListener;
/*    */ import io.lumine.utils.network.messaging.conversation.ConversationReplyListener;
/*    */ import io.lumine.utils.promise.Promise;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import java.util.concurrent.TimeUnit;
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
/*    */ public interface ConversationChannel<T extends io.lumine.utils.network.messaging.conversation.ConversationMessage, R extends io.lumine.utils.network.messaging.conversation.ConversationMessage>
/*    */   extends Terminable
/*    */ {
/*    */   @Nonnull
/*    */   String getName();
/*    */   
/*    */   @Nonnull
/*    */   Channel<T> getOutgoingChannel();
/*    */   
/*    */   @Nonnull
/*    */   Channel<R> getReplyChannel();
/*    */   
/*    */   @Nonnull
/*    */   ConversationChannelAgent<T, R> newAgent();
/*    */   
/*    */   @Nonnull
/*    */   default ConversationChannelAgent<T, R> newAgent(ConversationChannelListener<T, R> listener) {
/* 60 */     ConversationChannelAgent<T, R> agent = newAgent();
/* 61 */     agent.addListener(listener);
/* 62 */     return agent;
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   Promise<Void> sendMessage(@Nonnull T paramT, @Nonnull ConversationReplyListener<R> paramConversationReplyListener, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*    */   
/*    */   @Nonnull
/*    */   Promise<Void> sendMessage(@Nonnull String paramString, @Nonnull T paramT, @Nonnull ConversationReplyListener<R> paramConversationReplyListener, long paramLong, @Nonnull TimeUnit paramTimeUnit);
/*    */   
/*    */   boolean terminate();
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\ConversationChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */