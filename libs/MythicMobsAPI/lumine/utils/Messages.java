/*    */ package lumine.utils;
/*    */ 
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import io.lumine.utils.network.messaging.Channel;
/*    */ import io.lumine.utils.network.messaging.ChannelAgent;
/*    */ import io.lumine.utils.network.messaging.ChannelListener;
/*    */ import io.lumine.utils.network.messaging.Messenger;
/*    */ import javax.annotation.Nonnull;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Messages
/*    */ {
/*    */   public static Messenger getMessenger() {
/* 20 */     Messenger messenger = (Messenger)Bukkit.getServer().getServicesManager().load(Messenger.class);
/* 21 */     return messenger;
/*    */   }
/*    */   
/*    */   public static <T> Channel<T> getChannel(@Nonnull Class<T> packet) {
/*    */     try {
/* 26 */       return getMessenger().getChannel((String)packet.getMethod("getChannel", new Class[0]).invoke(null, new Object[0]), packet);
/* 27 */     } catch (Exception|Error e) {
/* 28 */       e.printStackTrace();
/*    */       
/* 30 */       return null;
/*    */     } 
/*    */   }
/*    */   public static <T> Channel<T> getChannel(@Nonnull String name, @Nonnull Class<T> clazz) {
/* 34 */     return getMessenger().getChannel(name, clazz);
/*    */   }
/*    */   
/*    */   public static <T> Channel<T> getChannel(@Nonnull String name, @Nonnull TypeToken<T> type) {
/* 38 */     return getMessenger().getChannel(name, type);
/*    */   }
/*    */   
/*    */   public static <T> ChannelAgent<T> subscribe(@Nonnull Class<T> clazz, ChannelListener<T> listener) {
/* 42 */     ChannelAgent<T> agent = getChannel(clazz).newAgent();
/* 43 */     agent.addListener(listener);
/* 44 */     return agent;
/*    */   }
/*    */   
/*    */   public static <T> ChannelAgent<T> subscribe(@Nonnull String name, @Nonnull Class<T> clazz, ChannelListener<T> listener) {
/* 48 */     ChannelAgent<T> agent = getMessenger().getChannel(name, clazz).newAgent();
/* 49 */     agent.addListener(listener);
/* 50 */     return agent;
/*    */   }
/*    */   
/*    */   public static <T> ChannelAgent<T> subscribe(@Nonnull String name, @Nonnull TypeToken<T> type, ChannelListener<T> listener) {
/* 54 */     ChannelAgent<T> agent = getMessenger().getChannel(name, type).newAgent();
/* 55 */     agent.addListener(listener);
/* 56 */     return agent;
/*    */   }
/*    */   
/*    */   private Messages() {
/* 60 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */