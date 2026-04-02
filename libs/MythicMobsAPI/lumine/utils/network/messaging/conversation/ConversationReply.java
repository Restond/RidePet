/*     */ package lumine.utils.network.messaging.conversation;
/*     */ 
/*     */ import io.lumine.utils.network.messaging.conversation.ConversationMessage;
/*     */ import io.lumine.utils.promise.Promise;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import javax.annotation.Nonnull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ConversationReply<R extends ConversationMessage>
/*     */ {
/*  16 */   private static final io.lumine.utils.network.messaging.conversation.ConversationReply<?> NO_REPLY = new io.lumine.utils.network.messaging.conversation.ConversationReply(null);
/*     */ 
/*     */ 
/*     */   
/*     */   private final Promise<R> reply;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <R extends ConversationMessage> io.lumine.utils.network.messaging.conversation.ConversationReply<R> noReply() {
/*  26 */     return (io.lumine.utils.network.messaging.conversation.ConversationReply)NO_REPLY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <R extends ConversationMessage> io.lumine.utils.network.messaging.conversation.ConversationReply<R> of(R reply) {
/*  37 */     Objects.requireNonNull(reply, "reply");
/*  38 */     return new io.lumine.utils.network.messaging.conversation.ConversationReply<>(Promise.completed(reply));
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
/*     */   public static <R extends ConversationMessage> io.lumine.utils.network.messaging.conversation.ConversationReply<R> ofCompletableFuture(CompletableFuture<R> futureReply) {
/*  52 */     Objects.requireNonNull(futureReply, "futureReply");
/*  53 */     return new io.lumine.utils.network.messaging.conversation.ConversationReply<>(Promise.wrapFuture(futureReply));
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
/*     */   public static <R extends ConversationMessage> io.lumine.utils.network.messaging.conversation.ConversationReply<R> ofPromise(Promise<R> promiseReply) {
/*  67 */     Objects.requireNonNull(promiseReply, "promiseReply");
/*  68 */     return new io.lumine.utils.network.messaging.conversation.ConversationReply<>(promiseReply);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ConversationReply(Promise<R> reply) {
/*  74 */     this.reply = reply;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasReply() {
/*  83 */     return (this.reply != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Promise<R> getReply() {
/*  94 */     if (this.reply == null) {
/*  95 */       throw new IllegalStateException("No reply present");
/*     */     }
/*  97 */     return this.reply;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 102 */     if (this == obj) {
/* 103 */       return true;
/*     */     }
/*     */     
/* 106 */     if (!(obj instanceof io.lumine.utils.network.messaging.conversation.ConversationReply)) {
/* 107 */       return false;
/*     */     }
/*     */     
/* 110 */     io.lumine.utils.network.messaging.conversation.ConversationReply<?> other = (io.lumine.utils.network.messaging.conversation.ConversationReply)obj;
/* 111 */     return Objects.equals(this.reply, other.reply);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 116 */     return Objects.hashCode(this.reply);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 121 */     return (this.reply != null) ? String.format("ConversationReply[%s]", new Object[] { this.reply }) : "ConversationReply.noReply";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\conversation\ConversationReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */