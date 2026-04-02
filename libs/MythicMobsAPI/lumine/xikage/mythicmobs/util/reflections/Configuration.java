package lumine.xikage.mythicmobs.util.reflections;

import com.google.common.base.Predicate;
import io.lumine.xikage.mythicmobs.util.reflections.adapters.MetadataAdapter;
import io.lumine.xikage.mythicmobs.util.reflections.scanners.Scanner;
import io.lumine.xikage.mythicmobs.util.reflections.serializers.Serializer;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

public interface Configuration {
  Set<Scanner> getScanners();
  
  Set<URL> getUrls();
  
  MetadataAdapter getMetadataAdapter();
  
  @Nullable
  Predicate<String> getInputsFilter();
  
  ExecutorService getExecutorService();
  
  Serializer getSerializer();
  
  @Nullable
  ClassLoader[] getClassLoaders();
  
  boolean shouldExpandSuperTypes();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\Configuration.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */