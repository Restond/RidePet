package lumine.utils.gson.converters;

import javax.annotation.Nullable;

public interface SetBuilder<S extends java.util.Set<E>, E> {
  void add(@Nullable E paramE);
  
  S build();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\AbstractGsonConverter$SetBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */