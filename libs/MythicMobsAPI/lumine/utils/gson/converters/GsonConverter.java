package lumine.utils.gson.converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.lumine.utils.annotation.NonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@NonnullByDefault
public interface GsonConverter {
  Map<String, Object> unwrapObject(JsonObject paramJsonObject);
  
  List<Object> unwrapArray(JsonArray paramJsonArray);
  
  Set<Object> unwrapArrayToSet(JsonArray paramJsonArray);
  
  Object unwarpPrimitive(JsonPrimitive paramJsonPrimitive);
  
  @Nullable
  Object unwrapElement(JsonElement paramJsonElement);
  
  JsonElement wrap(Object paramObject);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\GsonConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */