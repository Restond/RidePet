package lumine.xikage.mythicmobs.util.reflections.vfs;

import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
import java.net.URL;

public interface UrlType {
  boolean matches(URL paramURL) throws Exception;
  
  Vfs.Dir createDir(URL paramURL) throws Exception;
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\Vfs$UrlType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */