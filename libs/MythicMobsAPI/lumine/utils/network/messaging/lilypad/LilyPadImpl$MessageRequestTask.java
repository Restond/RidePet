/*     */ package lumine.utils.network.messaging.lilypad;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.network.messaging.lilypad.LilyPadImpl;
/*     */ import java.util.Collections;
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
/*     */ final class MessageRequestTask
/*     */   implements Runnable
/*     */ {
/*     */   private final Connect connect;
/*     */   private final String channel;
/*     */   private final byte[] message;
/*  88 */   private int attempts = 0;
/*     */   
/*     */   MessageRequestTask(Connect connect, String channel, byte[] message) {
/*  91 */     this.connect = connect;
/*  92 */     this.channel = channel;
/*  93 */     this.message = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  98 */     this.attempts++;
/*     */     try {
/* 100 */       this.connect.request((Request)new MessageRequest(Collections.emptyList(), this.channel, this.message));
/* 101 */     } catch (RequestException e) {
/* 102 */       if (e.getMessage().equals("Not open") || e.getMessage().equals("Not connected")) {
/*     */         
/* 104 */         if (this.attempts < 3) {
/* 105 */           Schedulers.async().runLater(this, 2L, TimeUnit.SECONDS);
/*     */         }
/*     */       } else {
/* 108 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\lilypad\LilyPadImpl$MessageRequestTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */