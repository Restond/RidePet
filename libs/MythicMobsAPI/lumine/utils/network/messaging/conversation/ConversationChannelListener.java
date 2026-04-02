package lumine.utils.network.messaging.conversation;

import io.lumine.utils.network.messaging.conversation.ConversationChannelAgent;
import io.lumine.utils.network.messaging.conversation.ConversationReply;
import javax.annotation.Nonnull;

@FunctionalInterface
public interface ConversationChannelListener<T extends io.lumine.utils.network.messaging.conversation.ConversationMessage, R extends io.lumine.utils.network.messaging.conversation.ConversationMessage> {
  ConversationReply<R> onMessage(@Nonnull ConversationChannelAgent<T, R> paramConversationChannelAgent, @Nonnull String paramString, @Nonnull T paramT);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\ConversationChannelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */