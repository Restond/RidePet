package lumine.utils.network.messaging.codec;

import io.lumine.utils.network.messaging.codec.Codec;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Message {
  Class<? extends Codec<?>> codec();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\codec\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */