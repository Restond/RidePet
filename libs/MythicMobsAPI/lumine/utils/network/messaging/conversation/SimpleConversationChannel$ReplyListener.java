/*    */ package lumine.utils.network.messaging.conversation;
/*    */ 
/*    */ import io.lumine.utils.network.messaging.ChannelAgent;
/*    */ import io.lumine.utils.network.messaging.ChannelListener;
/*    */ import io.lumine.utils.network.messaging.conversation.ConversationMessage;
/*    */ import io.lumine.utils.network.messaging.conversation.SimpleConversationChannel;
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
/*    */ final class ReplyListener
/*    */   implements ChannelListener<R>
/*    */ {
/*    */   private ReplyListener() {}
/*    */   
/*    */   public void onMessage(@Nonnull ChannelAgent agent, String sender, R message) {
/* 56 */     SimpleConversationChannel.access$100(SimpleConversationChannel.this).get(message.getConversationId()).removeIf(l -> l.onReply(paramConversationMessage));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\SimpleConversationChannel$ReplyListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */