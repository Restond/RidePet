/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractBiome;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitEntity;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitWorld
/*     */   implements AbstractWorld
/*     */ {
/*     */   private transient WeakReference<World> worldRef;
/*     */   private UUID uuid;
/*     */   private String name;
/*     */   
/*     */   public BukkitWorld() {}
/*     */   
/*     */   public BukkitWorld(World w) {
/*  36 */     this.worldRef = new WeakReference<>(w);
/*  37 */     this.uuid = w.getUID();
/*  38 */     this.name = w.getName();
/*     */   }
/*     */   
/*     */   public BukkitWorld(String world) {
/*  42 */     World w = Bukkit.getWorld(world);
/*     */     
/*  44 */     this.worldRef = new WeakReference<>(w);
/*  45 */     if (this.worldRef != null && this.worldRef.get() != null) {
/*  46 */       this.uuid = w.getUID();
/*     */     }
/*  48 */     this.name = world;
/*     */   }
/*     */ 
/*     */   
/*     */   public World getBukkitWorld() {
/*  53 */     if (this.worldRef.get() == null) {
/*  54 */       if (Bukkit.getWorld(this.name) != null) {
/*  55 */         this.worldRef = new WeakReference<>(Bukkit.getWorld(this.name));
/*     */       } else {
/*  57 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*  61 */     return this.worldRef.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLoaded() {
/*  66 */     return (getBukkitWorld() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AbstractEntity> getLivingEntities() {
/*  71 */     List<AbstractEntity> wl = new ArrayList<>();
/*     */     
/*  73 */     if (getBukkitWorld() == null) return wl; 
/*  74 */     for (LivingEntity e : getBukkitWorld().getLivingEntities()) {
/*  75 */       wl.add(new BukkitEntity((Entity)e));
/*     */     }
/*  77 */     return wl;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  82 */     if (getBukkitWorld() == null) return this.name; 
/*  83 */     return getBukkitWorld().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getUniqueId() {
/*  88 */     if (getBukkitWorld() == null) return this.uuid; 
/*  89 */     return getBukkitWorld().getUID();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/*  94 */     if (other == null)
/*  95 */       return false; 
/*  96 */     if (other instanceof io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld) {
/*  97 */       if (((io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld)other).getBukkitWorld() == null) return false; 
/*  98 */       return ((io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld)other).getBukkitWorld().equals(getBukkitWorld());
/*  99 */     }  if (other instanceof AbstractWorld) {
/* 100 */       return ((AbstractWorld)other).getName().equals(getName());
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 108 */     return getName();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 112 */     if (getBukkitWorld() == null) return -1; 
/* 113 */     return getBukkitWorld().hashCode();
/*     */   }
/*     */   
/*     */   public int getMaxY() {
/* 117 */     return getBukkitWorld().getMaxHeight() - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void createExplosion(AbstractLocation l, float f) {
/* 122 */     if (getBukkitWorld() == null)
/*     */       return; 
/* 124 */     getBukkitWorld().createExplosion(BukkitAdapter.adapt(l), f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createExplosion(AbstractLocation l, float yield, boolean fire, boolean blockdamage) {
/* 129 */     if (getBukkitWorld() == null)
/*     */       return; 
/* 131 */     getBukkitWorld().createExplosion(l.getX(), l.getY(), l.getZ(), yield, fire, blockdamage);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AbstractPlayer> getPlayers() {
/* 136 */     List<AbstractPlayer> pl = new ArrayList<>();
/*     */     
/* 138 */     for (Player p : getBukkitWorld().getPlayers()) {
/* 139 */       pl.add(BukkitAdapter.adapt(p));
/*     */     }
/* 141 */     return pl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStorm(boolean b) {
/* 146 */     getBukkitWorld().setStorm(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThundering(boolean b) {
/* 151 */     getBukkitWorld().setThundering(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWeatherDuration(int duration) {
/* 156 */     getBukkitWorld().setWeatherDuration(duration);
/*     */   }
/*     */   
/* 159 */   private static final Map<Integer, Effect> effects = new HashMap<>();
/*     */   static {
/* 161 */     for (Effect effect : Effect.values()) {
/* 162 */       effects.put(Integer.valueOf(effect.getId()), effect);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playEffect(AbstractLocation location, int type) {
/* 168 */     return playEffect(location, type, 0);
/*     */   }
/*     */   
/*     */   public boolean playEffect(AbstractLocation location, int type, int data) {
/* 172 */     World world = getBukkitWorld();
/*     */     
/* 174 */     Effect effect = effects.get(Integer.valueOf(type));
/* 175 */     if (effect == null) {
/* 176 */       return false;
/*     */     }
/*     */     
/* 179 */     world.playEffect(BukkitAdapter.adapt(location), effect, data);
/*     */     
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockLightLevel(AbstractLocation l) {
/* 186 */     return getBukkitWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ()).getLightLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractLocation getSpawnLocation() {
/* 191 */     if (getBukkitWorld() == null) return null;
/*     */     
/* 193 */     return BukkitAdapter.adapt(getBukkitWorld().getSpawnLocation());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChunkLoaded(int x, int z) {
/* 198 */     if (getBukkitWorld() == null) return false; 
/* 199 */     return getBukkitWorld().isChunkLoaded(x, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocationLoaded(AbstractLocation abstractLocation) {
/* 204 */     if (getBukkitWorld() == null) return false;
/*     */     
/* 206 */     Location loc = BukkitAdapter.adapt(abstractLocation);
/*     */     
/* 208 */     int x = loc.getBlockX() >> 4;
/* 209 */     int z = loc.getBlockZ() >> 4;
/*     */     
/* 211 */     return getBukkitWorld().isChunkLoaded(x, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AbstractPlayer> getPlayersNearLocation(AbstractLocation location, int radius) {
/* 216 */     List<AbstractPlayer> pl = new ArrayList<>();
/* 217 */     Location l = BukkitAdapter.adapt(location);
/*     */     
/* 219 */     for (Player p : getBukkitWorld().getPlayers()) {
/* 220 */       if (p.getLocation().distanceSquared(l) <= Math.pow(radius, 2.0D));
/* 221 */       pl.add(BukkitAdapter.adapt(p));
/*     */     } 
/*     */     
/* 224 */     return pl;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractBiome getLocationBiome(AbstractLocation abstractLocation) {
/* 229 */     Location l = BukkitAdapter.adapt(abstractLocation);
/*     */     
/* 231 */     return BukkitAdapter.adapt(l.getBlock().getBiome());
/*     */   }
/*     */ 
/*     */   
/*     */   public long getFullTime() {
/* 236 */     if (getBukkitWorld() == null) return 0L; 
/* 237 */     return getBukkitWorld().getFullTime();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */