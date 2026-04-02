/*    */ package lumine.utils.network.messaging.conversation;
/*    */ 
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
/*    */ public interface ConversationReplyListener<R extends io.lumine.utils.network.messaging.conversation.ConversationMessage>
/*    */ {
/*    */   static <R extends io.lumine.utils.network.messaging.conversation.ConversationMessage> io.lumine.utils.network.messaging.conversation.ConversationReplyListener<R> of(Function<? super R, RegistrationAction> onReply) {
/* 41 */     return (io.lumine.utils.network.messaging.conversation.ConversationReplyListener<R>)new Object(onReply);
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   RegistrationAction onReply(@Nonnull R paramR);
/*    */   
/*    */   void onTimeout(@Nonnull List<R> paramList);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\ConversationReplyListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */