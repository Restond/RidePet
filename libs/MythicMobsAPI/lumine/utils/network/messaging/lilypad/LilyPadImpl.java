/*     */ package lumine.utils.network.messaging.lilypad;
/*     */ 
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import io.lumine.utils.network.messaging.AbstractMessenger;
/*     */ import io.lumine.utils.network.messaging.Channel;
/*     */ import io.lumine.utils.network.messaging.InstanceData;
/*     */ import io.lumine.utils.network.messaging.Messenger;
/*     */ import io.lumine.utils.network.messaging.lilypad.LilyPad;
/*     */ import io.lumine.utils.plugin.LuminePlugin;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.annotation.Nonnull;
/*     */ import lilypad.client.connect.api.Connect;
/*     */ import lilypad.client.connect.api.event.EventListener;
/*     */ import lilypad.client.connect.api.event.MessageEvent;
/*     */ import lilypad.client.connect.api.request.Request;
/*     */ import lilypad.client.connect.api.request.RequestException;
/*     */ import lilypad.client.connect.api.request.impl.MessageRequest;
/*     */ import lilypad.client.connect.api.request.impl.RedirectRequest;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LilyPadImpl
/*     */   implements LilyPad
/*     */ {
/*     */   private final LuminePlugin plugin;
/*     */   private final Connect connect;
/*     */   private final AbstractMessenger messenger;
/*  31 */   private final AtomicBoolean listening = new AtomicBoolean(false);
/*     */   
/*     */   public LilyPadImpl(LuminePlugin plugin) {
/*  34 */     this.plugin = plugin;
/*  35 */     this.connect = (Connect)plugin.getServer().getServicesManager().getRegistration(Connect.class).getProvider();
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
/*  54 */     this.messenger = new AbstractMessenger((channel, message) -> {
/*     */ 
/*     */           
/*     */           try {
/*     */             this.connect.request((Request)new MessageRequest(Collections.emptyList(), channel, message));
/*  59 */           } catch (Exception|Error exception) {}
/*     */         }(server, channel, message) -> {
/*     */           
/*     */           try {
/*     */             this.connect.request((Request)new MessageRequest(server, channel, message));
/*  64 */           } catch (Exception|Error exception) {}
/*     */         }channel -> {
/*     */           if (this.listening.getAndSet(true)) {
/*     */             return;
/*     */           }
/*     */           
/*     */           try {
/*     */             this.connect.registerEvents(this);
/*  72 */           } catch (Exception e) {
/*     */             this.listening.set(false);
/*     */           } 
/*     */         }channel -> {
/*     */         
/*     */         });
/*     */     
/*  79 */     plugin.provideService(LilyPad.class, this);
/*  80 */     plugin.provideService(Messenger.class, this);
/*  81 */     plugin.provideService(InstanceData.class, this);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventListener
/*     */   public void onMessage(MessageEvent event) {
/* 148 */     String sender = event.getSender();
/* 149 */     String channel = event.getChannel();
/* 150 */     byte[] message = event.getMessage();
/*     */     
/* 152 */     this.messenger.registerIncomingMessage(channel, sender, message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void redirectPlayer(@Nonnull String serverId, @Nonnull String playerUsername) {
/*     */     try {
/* 158 */       this.connect.request((Request)new RedirectRequest(serverId, playerUsername));
/* 159 */     } catch (RequestException e) {
/* 160 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Connect getConnect() {
/* 167 */     return this.connect;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public String getId() {
/* 173 */     return this.connect.getSettings().getUsername();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Set<String> getGroups() {
/* 179 */     return Collections.emptySet();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public <T> Channel<T> getChannel(@Nonnull String name, @Nonnull TypeToken<T> type) {
/* 185 */     return this.messenger.getChannel(name, type);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\lilypad\LilyPadImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */