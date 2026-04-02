package lumine.utils.network.messaging.conversation;

import io.lumine.utils.network.messaging.conversation.ConversationChannel;
import io.lumine.utils.network.messaging.conversation.ConversationChannelListener;
import io.lumine.utils.terminable.Terminable;
import java.util.Set;
import javax.annotation.Nonnull;

public interface ConversationChannelAgent<T extends io.lumine.utils.network.messaging.conversation.ConversationMessage, R extends io.lumine.utils.network.messaging.conversation.ConversationMessage> extends Terminable {
  @Nonnull
  ConversationChannel<T, R> getChannel();
  
  @Nonnull
  Set<ConversationChannelListener<T, R>> getListeners();
  
  boolean hasListeners();
  
  boolean addListener(@Nonnull ConversationChannelListener<T, R> paramConversationChannelListener);
  
  boolean removeListener(@Nonnull ConversationChannelListener<T, R> paramConversationChannelListener);
  
  boolean terminate();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\ConversationChannelAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */