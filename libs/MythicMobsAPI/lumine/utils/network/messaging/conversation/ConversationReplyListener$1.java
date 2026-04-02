/*    */ package lumine.utils.network.messaging.conversation;
/*    */ 
/*    */ import io.lumine.utils.network.messaging.conversation.ConversationReplyListener;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
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
/*    */ final class null
/*    */   implements ConversationReplyListener<R>
/*    */ {
/*    */   @Nonnull
/*    */   public ConversationReplyListener.RegistrationAction onReply(@Nonnull R reply) {
/* 45 */     return onReply.apply(reply);
/*    */   }
/*    */   
/*    */   public void onTimeout(@Nonnull List<R> replies) {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\ConversationReplyListener$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */