/*     */ package lumine.utils.network.messaging.conversation;
/*     */ 
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationMessage;
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationReplyListener;
/*     */ import io.lumine.utils.network.messaging.conversation.SimpleConversationChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ScheduledFuture;
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
/*     */ final class ReplyListenerRegistration<R extends ConversationMessage>
/*     */ {
/*     */   private final ConversationReplyListener<R> listener;
/*  62 */   private final List<R> replies = new ArrayList<>();
/*     */   
/*     */   private ScheduledFuture<?> timeoutFuture;
/*     */   private boolean active = true;
/*     */   
/*     */   private ReplyListenerRegistration(ConversationReplyListener<R> listener) {
/*  68 */     this.listener = listener;
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
/*     */   public boolean onReply(R message) {
/*  80 */     synchronized (this) {
/*  81 */       if (!this.active) {
/*  82 */         return true;
/*     */       }
/*     */       
/*  85 */       this.replies.add(message);
/*  86 */       ConversationReplyListener.RegistrationAction action = this.listener.onReply((ConversationMessage)message);
/*  87 */       if (action == ConversationReplyListener.RegistrationAction.STOP_LISTENING) {
/*     */ 
/*     */         
/*  90 */         this.active = false;
/*  91 */         this.timeoutFuture.cancel(false);
/*     */         
/*  93 */         return true;
/*     */       } 
/*  95 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout() {
/* 101 */     synchronized (this) {
/* 102 */       if (!this.active) {
/*     */         return;
/*     */       }
/*     */       
/* 106 */       this.listener.onTimeout(this.replies);
/* 107 */       this.active = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\SimpleConversationChannel$ReplyListenerRegistration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */