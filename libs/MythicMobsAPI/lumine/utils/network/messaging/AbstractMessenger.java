/*     */ package lumine.utils.network.messaging;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.annotation.NonnullByDefault;
/*     */ import io.lumine.utils.functions.TriConsumer;
/*     */ import io.lumine.utils.network.messaging.Channel;
/*     */ import io.lumine.utils.network.messaging.Messenger;
/*     */ import io.lumine.utils.network.messaging.codec.Codec;
/*     */ import io.lumine.utils.network.messaging.codec.GsonCodec;
/*     */ import io.lumine.utils.network.messaging.codec.Message;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
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
/*     */ @NonnullByDefault
/*     */ public class AbstractMessenger
/*     */   implements Messenger
/*     */ {
/*  42 */   private final LoadingCache<Map.Entry<String, TypeToken<?>>, AbstractChannel<?>> channels = CacheBuilder.newBuilder().build((CacheLoader)new ChannelLoader(this, null));
/*     */ 
/*     */ 
/*     */   
/*     */   private final TriConsumer<String, String, byte[]> outgoingToMessages;
/*     */ 
/*     */ 
/*     */   
/*     */   private final BiConsumer<String, byte[]> outgoingMessages;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Consumer<String> notifySub;
/*     */ 
/*     */   
/*     */   private final Consumer<String> notifyUnsub;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMessenger(BiConsumer<String, byte[]> outgoingMessages, TriConsumer<String, String, byte[]> outgoingToMessages, Consumer<String> notifySub, Consumer<String> notifyUnsub) {
/*  62 */     this.outgoingMessages = Objects.<BiConsumer<String, byte[]>>requireNonNull(outgoingMessages, "outgoingMessages");
/*  63 */     this.outgoingToMessages = Objects.<TriConsumer<String, String, byte[]>>requireNonNull(outgoingToMessages, "outgoingMessages");
/*  64 */     this.notifySub = Objects.<Consumer<String>>requireNonNull(notifySub, "notifySub");
/*  65 */     this.notifyUnsub = Objects.<Consumer<String>>requireNonNull(notifyUnsub, "notifyUnsub");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIncomingMessage(String channel, String sender, byte[] message) {
/*  78 */     for (Map.Entry<Map.Entry<String, TypeToken<?>>, AbstractChannel<?>> c : (Iterable<Map.Entry<Map.Entry<String, TypeToken<?>>, AbstractChannel<?>>>)this.channels.asMap().entrySet()) {
/*  79 */       if (((String)((Map.Entry)c.getKey()).getKey()).equals(channel)) {
/*  80 */         AbstractChannel.access$100(c.getValue(), sender, message);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Channel<T> getChannel(@Nonnull String name, @Nonnull TypeToken<T> type) {
/*  89 */     Objects.requireNonNull(name, "name");
/*  90 */     Preconditions.checkArgument(!name.trim().isEmpty(), "name cannot be empty");
/*  91 */     Objects.requireNonNull(type, "type");
/*     */     
/*  93 */     return (Channel<T>)this.channels.getUnchecked(Maps.immutableEntry(name, type));
/*     */   }
/*     */   
/*     */   private static <T> Codec<T> getCodec(TypeToken<T> type) {
/*  97 */     Class<? super T> rawType = type.getRawType();
/*     */     do {
/*  99 */       Message message = rawType.<Message>getAnnotation(Message.class);
/* 100 */       if (message == null)
/* 101 */         continue;  Class<? extends Codec<?>> codec = message.codec();
/*     */       
/*     */       try {
/* 104 */         return (Codec<T>)codec.newInstance();
/* 105 */       } catch (InstantiationException|IllegalAccessException e) {
/* 106 */         e.printStackTrace();
/*     */       }
/*     */     
/* 109 */     } while ((rawType = rawType.getSuperclass()) != null);
/*     */     
/* 111 */     return (Codec<T>)new GsonCodec(type);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\AbstractMessenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */