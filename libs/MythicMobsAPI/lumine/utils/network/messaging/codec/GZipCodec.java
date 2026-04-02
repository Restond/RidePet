/*    */ package lumine.utils.network.messaging.codec;
/*    */ 
/*    */ import com.google.common.io.ByteStreams;
/*    */ import io.lumine.utils.network.messaging.codec.Codec;
/*    */ import io.lumine.utils.network.messaging.codec.EncodingException;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import java.util.zip.GZIPOutputStream;
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
/*    */ public class GZipCodec<M>
/*    */   implements Codec<M>
/*    */ {
/*    */   private final Codec<M> delegate;
/*    */   
/*    */   public GZipCodec(Codec<M> delegate) {
/* 45 */     this.delegate = delegate;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] encode(M message) throws EncodingException {
/* 50 */     byte[] in = this.delegate.encode(message);
/* 51 */     ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
/* 52 */     try (GZIPOutputStream gzipOut = new GZIPOutputStream(byteOut)) {
/* 53 */       gzipOut.write(in);
/* 54 */     } catch (IOException e) {
/* 55 */       throw new EncodingException(e);
/*    */     } 
/* 57 */     return byteOut.toByteArray();
/*    */   }
/*    */ 
/*    */   
/*    */   public M decode(byte[] buf) throws EncodingException {
/*    */     byte[] uncompressed;
/* 63 */     try (GZIPInputStream gzipIn = new GZIPInputStream(new ByteArrayInputStream(buf))) {
/* 64 */       uncompressed = ByteStreams.toByteArray(gzipIn);
/* 65 */     } catch (IOException e) {
/* 66 */       throw new EncodingException(e);
/*    */     } 
/* 68 */     return (M)this.delegate.decode(uncompressed);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\codec\GZipCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */