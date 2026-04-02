package lumine.utils.metadata;

public interface TransientValue<T> {
  T getOrNull();
  
  boolean shouldExpire();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\TransientValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */