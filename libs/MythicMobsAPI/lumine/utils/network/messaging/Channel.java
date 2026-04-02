/*    */ package lumine.utils.network.messaging;
/*    */ 
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import io.lumine.utils.interfaces.TypeAware;
/*    */ import io.lumine.utils.network.messaging.ChannelAgent;
/*    */ import io.lumine.utils.network.messaging.ChannelListener;
/*    */ import io.lumine.utils.network.messaging.codec.Codec;
/*    */ import io.lumine.utils.promise.Promise;
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
/*    */ public interface Channel<T>
/*    */   extends TypeAware<T>
/*    */ {
/*    */   @Nonnull
/*    */   String getName();
/*    */   
/*    */   @Nonnull
/*    */   TypeToken<T> getType();
/*    */   
/*    */   @Nonnull
/*    */   Codec<T> getCodec();
/*    */   
/*    */   @Nonnull
/*    */   ChannelAgent<T> newAgent();
/*    */   
/*    */   @Nonnull
/*    */   default ChannelAgent<T> newAgent(ChannelListener<T> listener) {
/* 62 */     ChannelAgent<T> agent = newAgent();
/* 63 */     agent.addListener(listener);
/* 64 */     return agent;
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   Promise<Void> sendMessage(@Nonnull T paramT);
/*    */   
/*    */   @Nonnull
/*    */   Promise<Void> sendMessage(@Nonnull String paramString, @Nonnull T paramT);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\Channel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */