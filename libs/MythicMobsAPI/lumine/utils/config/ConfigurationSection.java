package lumine.utils.config;

import io.lumine.utils.config.Configuration;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ConfigurationSection {
  Set<String> getKeys(boolean paramBoolean);
  
  Map<String, Object> getValues(boolean paramBoolean);
  
  boolean contains(String paramString);
  
  boolean contains(String paramString, boolean paramBoolean);
  
  boolean isSet(String paramString);
  
  String getCurrentPath();
  
  String getName();
  
  Configuration getRoot();
  
  io.lumine.utils.config.ConfigurationSection getParent();
  
  Object get(String paramString);
  
  Object get(String paramString, Object paramObject);
  
  void set(String paramString, Object paramObject);
  
  io.lumine.utils.config.ConfigurationSection createSection(String paramString);
  
  io.lumine.utils.config.ConfigurationSection createSection(String paramString, Map<?, ?> paramMap);
  
  String getString(String paramString);
  
  String getString(String paramString1, String paramString2);
  
  boolean isString(String paramString);
  
  int getInt(String paramString);
  
  int getInt(String paramString, int paramInt);
  
  boolean isInt(String paramString);
  
  boolean getBoolean(String paramString);
  
  boolean getBoolean(String paramString, boolean paramBoolean);
  
  boolean isBoolean(String paramString);
  
  double getDouble(String paramString);
  
  double getDouble(String paramString, double paramDouble);
  
  boolean isDouble(String paramString);
  
  long getLong(String paramString);
  
  long getLong(String paramString, long paramLong);
  
  boolean isLong(String paramString);
  
  List<?> getList(String paramString);
  
  List<?> getList(String paramString, List<?> paramList);
  
  boolean isList(String paramString);
  
  List<String> getStringList(String paramString);
  
  List<Integer> getIntegerList(String paramString);
  
  List<Boolean> getBooleanList(String paramString);
  
  List<Double> getDoubleList(String paramString);
  
  List<Float> getFloatList(String paramString);
  
  List<Long> getLongList(String paramString);
  
  List<Byte> getByteList(String paramString);
  
  List<Character> getCharacterList(String paramString);
  
  List<Short> getShortList(String paramString);
  
  List<Map<?, ?>> getMapList(String paramString);
  
  <T extends org.bukkit.configuration.serialization.ConfigurationSerializable> T getSerializable(String paramString, Class<T> paramClass);
  
  <T extends org.bukkit.configuration.serialization.ConfigurationSerializable> T getSerializable(String paramString, Class<T> paramClass, T paramT);
  
  io.lumine.utils.config.ConfigurationSection getConfigurationSection(String paramString);
  
  boolean isConfigurationSection(String paramString);
  
  io.lumine.utils.config.ConfigurationSection getDefaultSection();
  
  void addDefault(String paramString, Object paramObject);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\ConfigurationSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */