package lumine.utils.network.messaging.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import io.lumine.utils.promise.Promise;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import org.bukkit.entity.Player;

public interface BungeeCord {
  public static final String ALL_SERVERS = "ALL";
  
  public static final String ONLINE_SERVERS = "ONLINE";
  
  void connect(@Nonnull Player paramPlayer, @Nonnull String paramString);
  
  void connectOther(@Nonnull String paramString1, @Nonnull String paramString2);
  
  @Nonnull
  Promise<Map.Entry<String, Integer>> ip(@Nonnull Player paramPlayer);
  
  @Nonnull
  Promise<Integer> playerCount(@Nonnull String paramString);
  
  @Nonnull
  Promise<List<String>> playerList(@Nonnull String paramString);
  
  @Nonnull
  Promise<List<String>> getServers();
  
  void message(@Nonnull String paramString1, @Nonnull String paramString2);
  
  @Nonnull
  Promise<String> getServer();
  
  @Nonnull
  Promise<UUID> uuid(@Nonnull Player paramPlayer);
  
  @Nonnull
  Promise<UUID> uuidOther(@Nonnull String paramString);
  
  @Nonnull
  Promise<Map.Entry<String, Integer>> serverIp(@Nonnull String paramString);
  
  void kickPlayer(@Nonnull String paramString1, @Nonnull String paramString2);
  
  void forward(@Nonnull String paramString1, @Nonnull String paramString2, @Nonnull byte[] paramArrayOfbyte);
  
  void forward(@Nonnull String paramString1, @Nonnull String paramString2, @Nonnull ByteArrayDataOutput paramByteArrayDataOutput);
  
  void forwardToPlayer(@Nonnull String paramString1, @Nonnull String paramString2, @Nonnull byte[] paramArrayOfbyte);
  
  void forwardToPlayer(@Nonnull String paramString1, @Nonnull String paramString2, @Nonnull ByteArrayDataOutput paramByteArrayDataOutput);
  
  void registerForwardCallbackRaw(@Nonnull String paramString, @Nonnull Predicate<byte[]> paramPredicate);
  
  void registerForwardCallback(@Nonnull String paramString, @Nonnull Predicate<ByteArrayDataInput> paramPredicate);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\bungee\BungeeCord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */