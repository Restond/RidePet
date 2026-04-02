/*    */ package lumine.utils.network.messaging.codec;
/*    */ 
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import com.google.gson.Gson;
/*    */ import io.lumine.utils.gson.GsonProvider;
/*    */ import io.lumine.utils.network.messaging.codec.Codec;
/*    */ import io.lumine.utils.network.messaging.codec.EncodingException;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Reader;
/*    */ import java.io.Writer;
/*    */ import java.nio.charset.StandardCharsets;
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
/*    */ 
/*    */ public class GsonCodec<M>
/*    */   implements Codec<M>
/*    */ {
/*    */   private final Gson gson;
/*    */   private final TypeToken<M> type;
/*    */   
/*    */   public GsonCodec(Gson gson, TypeToken<M> type) {
/* 52 */     this.gson = gson;
/* 53 */     this.type = type;
/*    */   }
/*    */   
/*    */   public GsonCodec(TypeToken<M> type) {
/* 57 */     this(GsonProvider.standard(), type);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] encode(M message) {
/* 62 */     ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
/* 63 */     try (Writer writer = new OutputStreamWriter(byteOut, StandardCharsets.UTF_8)) {
/* 64 */       this.gson.toJson(message, this.type.getType(), writer);
/* 65 */     } catch (IOException e) {
/* 66 */       throw new EncodingException(e);
/*    */     } 
/* 68 */     return byteOut.toByteArray();
/*    */   }
/*    */ 
/*    */   
/*    */   public M decode(byte[] buf) {
/* 73 */     ByteArrayInputStream byteIn = new ByteArrayInputStream(buf);
/* 74 */     try (Reader reader = new InputStreamReader(byteIn, StandardCharsets.UTF_8)) {
/* 75 */       return (M)this.gson.fromJson(reader, this.type.getType());
/* 76 */     } catch (IOException e) {
/* 77 */       throw new EncodingException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\codec\GsonCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */