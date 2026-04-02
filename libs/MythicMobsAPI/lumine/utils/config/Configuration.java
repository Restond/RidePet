package lumine.utils.config;

import io.lumine.utils.config.ConfigurationOptions;
import io.lumine.utils.config.ConfigurationSection;
import java.util.Map;

public interface Configuration extends ConfigurationSection {
  void addDefault(String paramString, Object paramObject);
  
  void addDefaults(Map<String, Object> paramMap);
  
  void addDefaults(io.lumine.utils.config.Configuration paramConfiguration);
  
  void setDefaults(io.lumine.utils.config.Configuration paramConfiguration);
  
  io.lumine.utils.config.Configuration getDefaults();
  
  ConfigurationOptions options();
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\Configuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */