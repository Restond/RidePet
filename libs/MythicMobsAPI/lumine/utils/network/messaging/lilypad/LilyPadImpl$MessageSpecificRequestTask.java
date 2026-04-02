/*     */ package lumine.utils.network.messaging.lilypad;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.network.messaging.lilypad.LilyPadImpl;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import lilypad.client.connect.api.Connect;
/*     */ import lilypad.client.connect.api.request.Request;
/*     */ import lilypad.client.connect.api.request.RequestException;
/*     */ import lilypad.client.connect.api.request.impl.MessageRequest;
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
/*     */ final class MessageSpecificRequestTask
/*     */   implements Runnable
/*     */ {
/*     */   private final Connect connect;
/*     */   private final String server;
/*     */   private final String channel;
/*     */   private final byte[] message;
/* 119 */   private int attempts = 0;
/*     */   
/*     */   MessageSpecificRequestTask(Connect connect, String server, String channel, byte[] message) {
/* 122 */     this.connect = connect;
/* 123 */     this.server = server;
/* 124 */     this.channel = channel;
/* 125 */     this.message = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 130 */     this.attempts++;
/*     */     try {
/* 132 */       this.connect.request((Request)new MessageRequest(this.server, this.channel, this.message));
/* 133 */     } catch (RequestException e) {
/* 134 */       if (e.getMessage().equals("Not open") || e.getMessage().equals("Not connected")) {
/*     */         
/* 136 */         if (this.attempts < 3) {
/* 137 */           Schedulers.async().runLater(this, 2L, TimeUnit.SECONDS);
/*     */         }
/*     */       } else {
/* 140 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\lilypad\LilyPadImpl$MessageSpecificRequestTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */