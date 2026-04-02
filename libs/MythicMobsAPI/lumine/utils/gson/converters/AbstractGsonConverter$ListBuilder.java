package lumine.utils.gson.converters;

import javax.annotation.Nullable;

public interface ListBuilder<L extends java.util.List<E>, E> {
  void add(@Nullable E paramE);
  
  L build();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\AbstractGsonConverter$ListBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */