/*    */ package lumine.utils.network.messaging;
/*    */ 
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import io.lumine.utils.network.messaging.Channel;
/*    */ import io.lumine.utils.network.messaging.conversation.ConversationChannel;
/*    */ import io.lumine.utils.network.messaging.conversation.SimpleConversationChannel;
/*    */ import java.util.Objects;
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
/*    */ public interface Messenger
/*    */ {
/*    */   @Nonnull
/*    */   <T> Channel<T> getChannel(@Nonnull String paramString, @Nonnull TypeToken<T> paramTypeToken);
/*    */   
/*    */   @Nonnull
/*    */   default <T extends io.lumine.utils.network.messaging.conversation.ConversationMessage, R extends io.lumine.utils.network.messaging.conversation.ConversationMessage> ConversationChannel<T, R> getConversationChannel(@Nonnull String name, @Nonnull TypeToken<T> type, @Nonnull TypeToken<R> replyType) {
/* 42 */     return (ConversationChannel<T, R>)new SimpleConversationChannel(this, name, type, replyType);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   default <T> Channel<T> getChannel(@Nonnull String name, @Nonnull Class<T> clazz) {
/* 55 */     return getChannel(name, TypeToken.of(Objects.<Class<?>>requireNonNull(clazz)));
/*    */   }
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
/*    */   @Nonnull
/*    */   default <T extends io.lumine.utils.network.messaging.conversation.ConversationMessage, R extends io.lumine.utils.network.messaging.conversation.ConversationMessage> ConversationChannel<T, R> getConversationChannel(@Nonnull String name, @Nonnull Class<T> clazz, @Nonnull Class<R> replyClazz) {
/* 70 */     return getConversationChannel(name, TypeToken.of(Objects.<Class<?>>requireNonNull(clazz)), TypeToken.of(Objects.<Class<?>>requireNonNull(replyClazz)));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\Messenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */