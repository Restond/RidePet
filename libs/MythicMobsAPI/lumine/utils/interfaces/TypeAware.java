package lumine.utils.interfaces;

import com.google.common.reflect.TypeToken;
import javax.annotation.Nonnull;

public interface TypeAware<T> {
  @Nonnull
  TypeToken<T> getType();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\interfaces\TypeAware.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */