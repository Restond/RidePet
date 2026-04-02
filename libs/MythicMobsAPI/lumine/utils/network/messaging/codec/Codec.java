package lumine.utils.network.messaging.codec;

import io.lumine.utils.network.messaging.codec.EncodingException;

public interface Codec<M> {
  byte[] encode(M paramM) throws EncodingException;
  
  M decode(byte[] paramArrayOfbyte) throws EncodingException;
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\codec\Codec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */