/*     */ package lumine.utils.network.messaging.bungee;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import com.google.common.io.ByteArrayDataOutput;
/*     */ import com.google.common.io.ByteStreams;
/*     */ import io.lumine.utils.Players;
/*     */ import io.lumine.utils.annotation.NonnullByDefault;
/*     */ import io.lumine.utils.network.messaging.bungee.BungeeCord;
/*     */ import io.lumine.utils.plugin.LuminePlugin;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.messaging.PluginMessageListener;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @NonnullByDefault
/*     */ public final class BungeeCordImpl
/*     */   implements BungeeCord, PluginMessageListener
/*     */ {
/*     */   private static final String CHANNEL = "BungeeCord";
/*     */   private final LuminePlugin plugin;
/*  55 */   private final AtomicBoolean setup = new AtomicBoolean(false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private final List<MessageCallback> listeners = new LinkedList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private final ReentrantLock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private final Set<MessageAgent> queuedMessages = ConcurrentHashMap.newKeySet();
/*     */   
/*     */   public BungeeCordImpl(LuminePlugin plugin) {
/*  73 */     this.plugin = plugin;
/*     */   }
/*     */   
/*     */   private void ensureSetup() {
/*  77 */     if (!this.setup.compareAndSet(false, true)) {
/*     */       return;
/*     */     }
/*     */     
/*  81 */     this.plugin.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this.plugin, "BungeeCord");
/*  82 */     this.plugin.getServer().getMessenger().registerIncomingPluginChannel((Plugin)this.plugin, "BungeeCord", this);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPluginMessageReceived(String channel, Player player, byte[] data) {
/*  99 */     if (!channel.equals("BungeeCord")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 104 */     ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
/*     */ 
/*     */     
/* 107 */     ByteArrayDataInput in = ByteStreams.newDataInput(byteIn);
/*     */ 
/*     */     
/* 110 */     String subChannel = in.readUTF();
/* 111 */     byteIn.mark(0);
/*     */ 
/*     */     
/* 114 */     this.lock.lock();
/*     */     try {
/* 116 */       Iterator<MessageCallback> it = this.listeners.iterator();
/* 117 */       while (it.hasNext()) {
/* 118 */         MessageCallback e = it.next();
/*     */ 
/*     */         
/* 121 */         if (!e.getSubChannel().equals(subChannel)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 126 */         byteIn.reset();
/*     */ 
/*     */         
/* 129 */         boolean accepted = e.testResponse(player, in);
/* 130 */         if (!accepted) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 135 */         byteIn.reset();
/*     */ 
/*     */         
/* 138 */         boolean shouldRemove = e.acceptResponse(player, in);
/* 139 */         if (shouldRemove) {
/* 140 */           it.remove();
/*     */         }
/*     */       } 
/*     */     } finally {
/* 144 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendMessage(MessageAgent agent) {
/* 155 */     Player player = agent.getHandle();
/* 156 */     if (player != null) {
/* 157 */       if (!player.isOnline()) {
/* 158 */         throw new IllegalStateException("Player not online");
/*     */       }
/*     */       
/* 161 */       sendToChannel(agent, player);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 166 */     player = (Player)Iterables.getFirst(Players.all(), null);
/* 167 */     if (player != null) {
/* 168 */       sendToChannel(agent, player);
/*     */     } else {
/*     */       
/* 171 */       this.queuedMessages.add(agent);
/* 172 */       ensureSetup();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void flushQueuedMessages() {
/* 177 */     if (this.queuedMessages.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 181 */     Player p = (Player)Iterables.getFirst(Players.all(), null);
/* 182 */     if (p != null) {
/* 183 */       this.queuedMessages.removeIf(ma -> {
/*     */             sendToChannel(ma, paramPlayer);
/*     */             return true;
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   private void sendToChannel(MessageAgent agent, Player player) {
/* 191 */     ensureSetup();
/*     */ 
/*     */     
/* 194 */     ByteArrayDataOutput out = ByteStreams.newDataOutput();
/*     */ 
/*     */     
/* 197 */     out.writeUTF(agent.getSubChannel());
/*     */     
/* 199 */     agent.appendPayload(out);
/*     */     
/* 201 */     byte[] buf = out.toByteArray();
/* 202 */     player.sendPluginMessage((Plugin)this.plugin, "BungeeCord", buf);
/*     */ 
/*     */     
/* 205 */     if (agent instanceof MessageCallback) {
/* 206 */       MessageCallback callback = (MessageCallback)agent;
/* 207 */       registerCallback(callback);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void registerCallback(MessageCallback callback) {
/* 212 */     ensureSetup();
/*     */     
/* 214 */     this.lock.lock();
/*     */     try {
/* 216 */       this.listeners.add(callback);
/*     */     } finally {
/* 218 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void connect(Player player, String serverName) {
/* 224 */     sendMessage((MessageAgent)new ConnectAgent(player, serverName, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void connectOther(String playerName, String serverName) {
/* 229 */     sendMessage((MessageAgent)new ConnectOtherAgent(playerName, serverName, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<Map.Entry<String, Integer>> ip(Player player) {
/* 234 */     Promise<Map.Entry<String, Integer>> fut = Promise.empty();
/* 235 */     sendMessage((MessageAgent)new IPAgent(player, fut, null));
/* 236 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<Integer> playerCount(String serverName) {
/* 241 */     Promise<Integer> fut = Promise.empty();
/* 242 */     sendMessage((MessageAgent)new PlayerCountAgent(serverName, fut, null));
/* 243 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<List<String>> playerList(String serverName) {
/* 248 */     Promise<List<String>> fut = Promise.empty();
/* 249 */     sendMessage((MessageAgent)new PlayerListAgent(serverName, fut, null));
/* 250 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<List<String>> getServers() {
/* 255 */     Promise<List<String>> fut = Promise.empty();
/* 256 */     sendMessage((MessageAgent)new GetServersAgent(fut, null));
/* 257 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void message(String playerName, String message) {
/* 262 */     sendMessage((MessageAgent)new PlayerMessageAgent(playerName, message, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<String> getServer() {
/* 267 */     Promise<String> fut = Promise.empty();
/* 268 */     sendMessage((MessageAgent)new GetServerAgent(fut, null));
/* 269 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<UUID> uuid(Player player) {
/* 274 */     Promise<UUID> fut = Promise.empty();
/* 275 */     sendMessage((MessageAgent)new UUIDAgent(player, fut, null));
/* 276 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<UUID> uuidOther(String playerName) {
/* 281 */     Promise<UUID> fut = Promise.empty();
/* 282 */     sendMessage((MessageAgent)new UUIDOtherAgent(playerName, fut, null));
/* 283 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Promise<Map.Entry<String, Integer>> serverIp(String serverName) {
/* 288 */     Promise<Map.Entry<String, Integer>> fut = Promise.empty();
/* 289 */     sendMessage((MessageAgent)new ServerIPAgent(serverName, fut, null));
/* 290 */     return fut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void kickPlayer(String playerName, String reason) {
/* 295 */     sendMessage((MessageAgent)new KickPlayerAgent(playerName, reason, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void forward(String serverName, String channelName, byte[] data) {
/* 300 */     sendMessage((MessageAgent)new ForwardAgent(serverName, channelName, data, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void forward(String serverName, String channelName, ByteArrayDataOutput data) {
/* 305 */     sendMessage((MessageAgent)new ForwardAgent(serverName, channelName, data, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void forwardToPlayer(String playerName, String channelName, byte[] data) {
/* 310 */     sendMessage((MessageAgent)new ForwardToPlayerAgent(playerName, channelName, data, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void forwardToPlayer(String playerName, String channelName, ByteArrayDataOutput data) {
/* 315 */     sendMessage((MessageAgent)new ForwardToPlayerAgent(playerName, channelName, data, null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerForwardCallbackRaw(String channelName, Predicate<byte[]> callback) {
/* 320 */     ForwardCustomCallback customCallback = new ForwardCustomCallback(channelName, callback, null);
/* 321 */     registerCallback((MessageCallback)customCallback);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerForwardCallback(String channelName, Predicate<ByteArrayDataInput> callback) {
/* 326 */     Predicate<ByteArrayDataInput> cb = Objects.<Predicate<ByteArrayDataInput>>requireNonNull(callback, "callback");
/* 327 */     ForwardCustomCallback customCallback = new ForwardCustomCallback(channelName, bytes -> paramPredicate.test(ByteStreams.newDataInput(bytes)), null);
/* 328 */     registerCallback((MessageCallback)customCallback);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\bungee\BungeeCordImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */