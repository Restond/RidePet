/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.WorldManager;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDespawnEvent;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.world.ChunkLoadEvent;
/*     */ import org.bukkit.event.world.ChunkUnloadEvent;
/*     */ import org.bukkit.event.world.WorldLoadEvent;
/*     */ import org.bukkit.event.world.WorldUnloadEvent;
/*     */ 
/*     */ public class BukkitWorldManager
/*     */   implements WorldManager, Listener
/*     */ {
/*  28 */   private Map<String, Chunk> loadedChunks = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChunkLoaded(AbstractWorld world, int x, int z) {
/*  37 */     return MythicMobs.inst().getVolatileCodeHandler().getWorldHandler().isChunkLoaded(world, x, z);
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
/*     */   public int getEntitiesInChunk(AbstractWorld world, int x, int z) {
/*  52 */     String key = getChunkString(world, x, z);
/*     */     
/*  54 */     Chunk chunk = this.loadedChunks.getOrDefault(key, null);
/*     */     
/*  56 */     if (chunk == null) {
/*  57 */       return 0;
/*     */     }
/*  59 */     return (chunk.getEntities()).length;
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void onWorldLoad(WorldLoadEvent event) {
/*  65 */     MythicMobs.inst().getVolatileCodeHandler().getWorldHandler().registerWorldAccess(event.getWorld());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void onWorldUnload(WorldUnloadEvent event) {
/*  70 */     MythicMobs.inst().getVolatileCodeHandler().getWorldHandler().unregisterWorldAccess(event.getWorld());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void onChunkLoad(ChunkLoadEvent event) {
/*  75 */     String chunk = getChunkString(event.getChunk());
/*     */     
/*  77 */     this.loadedChunks.put(chunk, event.getChunk());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void onChunkUnload(ChunkUnloadEvent event) {
/*  82 */     String chunk = getChunkString(event.getChunk());
/*     */     
/*  84 */     this.loadedChunks.remove(chunk);
/*     */   }
/*     */   
/*     */   public String getChunkString(AbstractWorld world, int x, int z) {
/*  88 */     return world.getName() + "." + x + "." + z;
/*     */   }
/*     */   
/*     */   public String getChunkString(Chunk chunk) {
/*  92 */     return chunk.getWorld().getName() + "." + chunk.getX() + "." + chunk.getZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractEntity getEntity(UUID uuid) {
/*  97 */     Entity entity = Bukkit.getEntity(uuid);
/*     */     
/*  99 */     if (entity != null) {
/* 100 */       return BukkitAdapter.adapt(entity);
/*     */     }
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleMobDespawnEvent(ActiveMob mob) {
/* 107 */     MythicMobDespawnEvent mythicDespawnEvent = new MythicMobDespawnEvent(mob);
/* 108 */     Bukkit.getServer().getPluginManager().callEvent((Event)mythicDespawnEvent);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitWorldManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */