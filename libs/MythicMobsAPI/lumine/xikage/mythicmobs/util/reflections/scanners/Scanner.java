package lumine.xikage.mythicmobs.util.reflections.scanners;

import com.google.common.base.Predicate;
import com.google.common.collect.Multimap;
import io.lumine.xikage.mythicmobs.util.reflections.Configuration;
import io.lumine.xikage.mythicmobs.util.reflections.vfs.Vfs;
import javax.annotation.Nullable;

public interface Scanner {
  void setConfiguration(Configuration paramConfiguration);
  
  Multimap<String, String> getStore();
  
  void setStore(Multimap<String, String> paramMultimap);
  
  io.lumine.xikage.mythicmobs.util.reflections.scanners.Scanner filterResultsBy(Predicate<String> paramPredicate);
  
  boolean acceptsInput(String paramString);
  
  Object scan(Vfs.File paramFile, @Nullable Object paramObject);
  
  boolean acceptResult(String paramString);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\scanners\Scanner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */