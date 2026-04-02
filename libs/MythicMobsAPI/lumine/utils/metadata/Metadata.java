/*     */ package lumine.utils.metadata;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import io.lumine.utils.Players;
/*     */ import io.lumine.utils.events.Events;
/*     */ import io.lumine.utils.metadata.MetadataKey;
/*     */ import io.lumine.utils.metadata.MetadataMap;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Metadata
/*     */ {
/*  31 */   private static LoadingCache<UUID, MetadataMap> players = null;
/*  32 */   private static LoadingCache<UUID, MetadataMap> entities = null;
/*     */   
/*  34 */   private static LoadingCache<UUID, MetadataMap> worlds = null;
/*     */   
/*     */   private static synchronized void setup() {
/*  37 */     if (players != null)
/*     */       return; 
/*  39 */     players = makeCache();
/*  40 */     entities = makeCache();
/*     */     
/*  42 */     worlds = makeCache();
/*     */ 
/*     */     
/*  45 */     Events.subscribe(PlayerQuitEvent.class, EventPriority.MONITOR)
/*  46 */       .handler(e -> {
/*     */           MetadataMap map = (MetadataMap)players.asMap().remove(e.getPlayer().getUniqueId());
/*     */           
/*     */           if (map != null) {
/*     */             map.clear();
/*     */           }
/*     */         });
/*     */     
/*  54 */     Scheduler.runTaskRepeatingAsync(() -> { players.asMap().values().removeIf(MetadataMap::isEmpty); entities.asMap().values().removeIf(MetadataMap::isEmpty); worlds.asMap().values().removeIf(MetadataMap::isEmpty); }1200L + 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  59 */         ThreadLocalRandom.current().nextInt(20), 1200L);
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> LoadingCache<T, MetadataMap> makeCache() {
/*  64 */     return CacheBuilder.newBuilder().build((CacheLoader)new Object());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MetadataMap provide(Object obj) {
/*  82 */     if (obj instanceof Player)
/*  83 */       return provideForPlayer((Player)obj); 
/*  84 */     if (obj instanceof UUID)
/*  85 */       return provideForPlayer((UUID)obj); 
/*  86 */     if (obj instanceof Entity) {
/*  87 */       return provideForEntity((Entity)obj);
/*     */     }
/*     */     
/*  90 */     if (obj instanceof World) {
/*  91 */       return provideForWorld((World)obj);
/*     */     }
/*  93 */     throw new IllegalArgumentException("Unknown object type: " + obj.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MetadataMap provideForPlayer(Player player) {
/* 105 */     setup();
/* 106 */     return (MetadataMap)players.getUnchecked(player.getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MetadataMap provideForPlayer(UUID uuid) {
/* 117 */     setup();
/* 118 */     return (MetadataMap)players.getUnchecked(uuid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> Map<Player, T> lookupPlayersWithKey(MetadataKey<T> key) {
/* 129 */     Preconditions.checkNotNull("key", key);
/* 130 */     setup();
/*     */     
/* 132 */     ImmutableMap.Builder<Player, T> ret = ImmutableMap.builder();
/* 133 */     players.asMap().forEach((uuid, map) -> map.get(paramMetadataKey).ifPresent(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     return (Map<Player, T>)ret.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MetadataMap provideForEntity(Entity entity) {
/* 150 */     setup();
/*     */     
/* 152 */     if (entity instanceof Player) {
/* 153 */       return provideForPlayer((Player)entity);
/*     */     }
/*     */     
/* 156 */     return (MetadataMap)entities.getUnchecked(entity.getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> Map<Entity, T> lookupEntitiesWithKey(MetadataKey<T> key) {
/* 167 */     Preconditions.checkNotNull("key", key);
/* 168 */     setup();
/*     */     
/* 170 */     ImmutableMap.Builder<Entity, T> ret = ImmutableMap.builder();
/* 171 */     entities.asMap().forEach((uuid, map) -> map.get(paramMetadataKey).ifPresent(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     return (Map<Entity, T>)ret.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MetadataMap provideForWorld(World world) {
/* 216 */     setup();
/* 217 */     return (MetadataMap)worlds.getUnchecked(world.getUID());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> Map<World, T> lookupWorldsWithKey(MetadataKey<T> key) {
/* 228 */     Preconditions.checkNotNull("key", key);
/* 229 */     setup();
/*     */     
/* 231 */     ImmutableMap.Builder<World, T> ret = ImmutableMap.builder();
/* 232 */     worlds.asMap().forEach((uuid, map) -> map.get(paramMetadataKey).ifPresent(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     return (Map<World, T>)ret.build();
/*     */   }
/*     */   
/*     */   private Metadata() {
/* 242 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\Metadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */