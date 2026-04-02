package lumine.xikage.mythicmobs.util.reflections.vfs;

import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;

public interface Dir {
  String getPath();
  
  Iterable<Vfs.File> getFiles();
  
  void close();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\vfs\Vfs$Dir.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */