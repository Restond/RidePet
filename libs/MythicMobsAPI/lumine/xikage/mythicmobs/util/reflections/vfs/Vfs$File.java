package lumine.xikage.mythicmobs.util.reflections.vfs;

import java.io.IOException;
import java.io.InputStream;

public interface File {
  String getName();
  
  String getRelativePath();
  
  InputStream openInputStream() throws IOException;
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\Vfs$File.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */