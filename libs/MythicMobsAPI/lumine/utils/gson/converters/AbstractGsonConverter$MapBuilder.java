package lumine.utils.gson.converters;

import javax.annotation.Nullable;

public interface MapBuilder<M extends java.util.Map<K, V>, K, V> {
  void put(@Nullable K paramK, @Nullable V paramV);
  
  M build();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\AbstractGsonConverter$MapBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */