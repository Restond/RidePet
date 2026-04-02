/*    */ package lumine.utils.network.messaging.codec;
/*    */ 
/*    */ import io.lumine.utils.network.messaging.codec.Codec;
/*    */ import io.lumine.utils.network.messaging.codec.EncodingException;
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
/*    */ public class ByteArrayCodec<M>
/*    */   implements Codec<M>
/*    */ {
/*    */   private final Codec<M> delegate;
/*    */   
/*    */   public ByteArrayCodec(Codec<M> delegate) {
/* 37 */     this.delegate = delegate;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] encode(M message) throws EncodingException {
/* 42 */     return this.delegate.encode(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public M decode(byte[] buf) throws EncodingException {
/* 47 */     return (M)this.delegate.decode(buf);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\codec\ByteArrayCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */