package lumine.xikage.mythicmobs.util.reflections.serializers;

import io.lumine.xikage.mythicmobs.util.reflections.Reflections;
import java.io.File;
import java.io.InputStream;

public interface Serializer {
  Reflections read(InputStream paramInputStream);
  
  File save(Reflections paramReflections, String paramString);
  
  String toString(Reflections paramReflections);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\serializers\Serializer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */