/*     */ package lumine.xikage.mythicmobs.spawning.spawners;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.listeners.SpawnerListeners;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.IOHandler;
/*     */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*     */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomInt;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileMaterial;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpawnerManager
/*     */ {
/*     */   private final MythicMobs core;
/*     */   private final SpawnerListeners listener;
/*     */   private List<File> spawnerFiles;
/*     */   private List<IOLoader<MythicMobs>> spawnerLoaders;
/*  47 */   public List<MythicSpawner> listSpawners = new ArrayList<>();
/*  48 */   public ConcurrentHashMap<AbstractLocation, MythicSpawner> mmSpawners = new ConcurrentHashMap<>();
/*  49 */   public ConcurrentHashMap<String, HashSet<MythicSpawner>> mmChunkSpawnerLookup = new ConcurrentHashMap<>();
/*  50 */   public ConcurrentHashMap<Integer, MythicSpawner> mmSpawnerHashcodeLookup = new ConcurrentHashMap<>();
/*     */   
/*  52 */   private List<Runnable> secondPass = new ArrayList<>();
/*     */   
/*     */   public SpawnerManager(MythicMobs core) {
/*  55 */     this.core = core;
/*  56 */     this.listener = new SpawnerListeners(this);
/*  57 */     Bukkit.getPluginManager().registerEvents((Listener)this.listener, (Plugin)core);
/*     */   }
/*     */   
/*     */   public void loadSpawners() {
/*  61 */     File spawnerFile = new File(MythicMobs.inst().getDataFolder() + System.getProperty("file.separator") + "Spawners");
/*  62 */     if (!spawnerFile.exists()) {
/*  63 */       MythicLogger.log("Spawners folder not found! Creating...");
/*  64 */       spawnerFile.mkdir();
/*     */     } 
/*  66 */     this.spawnerFiles = IOHandler.getAllFiles(MythicMobs.inst().getDataFolder() + System.getProperty("file.separator") + "Spawners");
/*     */     
/*  68 */     this.spawnerLoaders = IOHandler.getSaveLoad((JavaPlugin)MythicMobs.inst(), this.spawnerFiles, "Spawners");
/*     */     
/*  70 */     this.listSpawners.clear();
/*  71 */     this.mmSpawners.clear();
/*  72 */     this.mmSpawnerHashcodeLookup.clear();
/*  73 */     this.mmChunkSpawnerLookup.clear();
/*     */     
/*  75 */     for (IOLoader<MythicMobs> sl : this.spawnerLoaders) {
/*  76 */       for (String s : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
/*  77 */         if (sl.getCustomConfig().getStringList(s + ".MobName") != null) {
/*     */           try {
/*  79 */             String sName = s;
/*  80 */             String sGroup = sl.getCustomConfig().getString(s + ".SpawnerGroup");
/*  81 */             String sMob = sl.getCustomConfig().getString(s + ".MobName");
/*  82 */             String world = sl.getCustomConfig().getString(s + ".World");
/*  83 */             int x = sl.getCustomConfig().getInt(s + ".X");
/*  84 */             int y = sl.getCustomConfig().getInt(s + ".Y");
/*  85 */             int z = sl.getCustomConfig().getInt(s + ".Z");
/*  86 */             int radius = sl.getCustomConfig().getInt(s + ".Radius", 0);
/*  87 */             int radiusY = sl.getCustomConfig().getInt(s + ".RadiusY", 0);
/*  88 */             boolean usetimer = sl.getCustomConfig().getBoolean(s + ".UseTimer", true);
/*     */             
/*  90 */             int maxmobs = sl.getCustomConfig().getInt(s + ".MaxMobs", -1);
/*  91 */             int mps = sl.getCustomConfig().getInt(s + ".MobsPerSpawn", 1);
/*  92 */             int cooldown = sl.getCustomConfig().getInt(s + ".Cooldown", 0);
/*  93 */             int cooldownTimer = sl.getCustomConfig().getInt(s + ".CooldownTimer", 0);
/*  94 */             int warmup = sl.getCustomConfig().getInt(s + ".Warmup", 0);
/*  95 */             int warmupTimer = sl.getCustomConfig().getInt(s + ".WarmupTimer", 0);
/*  96 */             int activeRange = sl.getCustomConfig().getInt(s + ".ActivationRange", 40);
/*  97 */             int leashRange = sl.getCustomConfig().getInt(s + ".LeashRange", 0);
/*  98 */             boolean leashHeal = sl.getCustomConfig().getBoolean(s + ".HealOnLeash", false);
/*  99 */             boolean leashResetThreat = sl.getCustomConfig().getBoolean(s + ".ResetThreatOnLeash", false);
/* 100 */             String mobLevel = sl.getCustomConfig().getString(s + ".MobLevel", "1");
/* 101 */             boolean breakable = sl.getCustomConfig().getBoolean(s + ".Breakable", false);
/* 102 */             boolean checkForPlayers = sl.getCustomConfig().getBoolean(s + ".CheckForPlayers", true);
/*     */             
/* 104 */             boolean showFlames = sl.getCustomConfig().getBoolean(s + ".ShowFlames", false);
/*     */             
/* 106 */             List<String> strConditions = sl.getCustomConfig().getStringList(s + ".Conditions");
/* 107 */             int activeMobs = sl.getCustomConfig().getInt(s + ".ActiveMobs", 0);
/*     */             
/* 109 */             ArrayList<String> conditions = new ArrayList<>();
/*     */             
/* 111 */             for (String c : strConditions) {
/* 112 */               conditions.add(c);
/*     */             }
/*     */             
/* 115 */             MythicSpawner ms = new MythicSpawner(sName, sGroup, sMob, world, x, y, z, radius, radiusY, usetimer, maxmobs, mps, mobLevel, cooldown, cooldownTimer, warmup, warmupTimer, activeRange, leashRange, leashHeal, leashResetThreat, showFlames, breakable, activeMobs, checkForPlayers, conditions);
/*     */             
/* 117 */             registerSpawner(ms);
/* 118 */           } catch (Exception ex) {
/* 119 */             MythicMobs.throwSevere("error-spawners-spawnerload", "Error loading MythicSpawner {0}: enable debugging for stacktrace.", new Object[] { s });
/*     */             
/* 121 */             if (ConfigManager.debugLevel > 0) {
/* 122 */               ex.printStackTrace();
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 128 */     buildSpawnerChunkLookupTable();
/*     */   }
/*     */   
/*     */   private void registerSpawner(MythicSpawner spawner) {
/* 132 */     this.listSpawners.add(spawner);
/* 133 */     this.mmSpawners.put(spawner.getLocation(), spawner);
/*     */     
/* 135 */     if (!this.mmSpawnerHashcodeLookup.containsKey(Integer.valueOf(spawner.hashCode()))) {
/* 136 */       this.mmSpawnerHashcodeLookup.put(Integer.valueOf(spawner.hashCode()), spawner);
/*     */     } else {
/* 138 */       MythicSpawner conflict = this.mmSpawnerHashcodeLookup.get(Integer.valueOf(spawner.hashCode()));
/* 139 */       MythicMobs.error("WARNING: HashCode collision detected when loading spawners.");
/* 140 */       MythicMobs.error("Spawner 1 Hash: " + spawner.hashCode() + " Type: " + ((MythicSpawner)this.mmSpawnerHashcodeLookup.get(Integer.valueOf(spawner.hashCode()))).getInternalName());
/* 141 */       MythicMobs.error("Spawner 2 Hash: " + conflict.hashCode() + " Type: " + conflict.getInternalName());
/* 142 */       MythicMobs.error("We recommend changing one of these spawners' names to avoid issues resolving mob type.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveSpawners() {
/* 147 */     this.listSpawners.forEach(ms -> saveSpawner(ms));
/*     */   }
/*     */   
/*     */   private boolean saveSpawner(MythicSpawner ms) {
/* 151 */     if (ms.getName().startsWith("Temp#")) {
/* 152 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 157 */     File spawnerFile = new File(this.core.getDataFolder() + System.getProperty("file.separator") + "Spawners", ms.getName() + ".yml");
/*     */     
/* 159 */     if (!spawnerFile.exists()) {
/* 160 */       spawnerFile.getParentFile().mkdirs();
/*     */       try {
/* 162 */         spawnerFile.createNewFile();
/* 163 */       } catch (IOException e) {
/* 164 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 168 */     YamlConfiguration yamlConfiguration = new YamlConfiguration();
/*     */     
/* 170 */     yamlConfiguration.set(ms.getName() + ".SpawnerGroup", ms.getGroup());
/* 171 */     yamlConfiguration.set(ms.getName() + ".MobName", ms.getTypeName());
/* 172 */     yamlConfiguration.set(ms.getName() + ".World", ms.getWorldName());
/* 173 */     yamlConfiguration.set(ms.getName() + ".X", Integer.valueOf(ms.getBlockX()));
/* 174 */     yamlConfiguration.set(ms.getName() + ".Y", Integer.valueOf(ms.getBlockY()));
/* 175 */     yamlConfiguration.set(ms.getName() + ".Z", Integer.valueOf(ms.getBlockZ()));
/* 176 */     yamlConfiguration.set(ms.getName() + ".Radius", Integer.valueOf(ms.getSpawnRadius()));
/* 177 */     yamlConfiguration.set(ms.getName() + ".UseTimer", Boolean.valueOf(ms.getUseTimer()));
/* 178 */     yamlConfiguration.set(ms.getName() + ".MaxMobs", Integer.valueOf(ms.getMaxMobs()));
/* 179 */     yamlConfiguration.set(ms.getName() + ".MobLevel", ms.getMobLevel().toString());
/* 180 */     yamlConfiguration.set(ms.getName() + ".MobsPerSpawn", Integer.valueOf(ms.getMobsPerSpawn()));
/* 181 */     yamlConfiguration.set(ms.getName() + ".Cooldown", Integer.valueOf(ms.getCooldownSeconds()));
/* 182 */     yamlConfiguration.set(ms.getName() + ".CooldownTimer", Integer.valueOf(ms.getRemainingCooldownSeconds()));
/* 183 */     yamlConfiguration.set(ms.getName() + ".Warmup", Integer.valueOf(ms.getWarmupSeconds()));
/* 184 */     yamlConfiguration.set(ms.getName() + ".WarmupTimer", Integer.valueOf(ms.getRemainingWarmupSeconds()));
/* 185 */     yamlConfiguration.set(ms.getName() + ".CheckForPlayers", Boolean.valueOf(ms.isCheckForPlayers()));
/* 186 */     yamlConfiguration.set(ms.getName() + ".ActivationRange", Integer.valueOf(ms.getActivationRange()));
/* 187 */     yamlConfiguration.set(ms.getName() + ".LeashRange", Integer.valueOf(ms.getLeashRange()));
/* 188 */     yamlConfiguration.set(ms.getName() + ".HealOnLeash", Boolean.valueOf(ms.isHealOnLeash()));
/* 189 */     yamlConfiguration.set(ms.getName() + ".ResetThreatOnLeash", Boolean.valueOf(ms.isLeashResetsThreat()));
/* 190 */     yamlConfiguration.set(ms.getName() + ".ShowFlames", Boolean.valueOf(ms.isShowFlames()));
/* 191 */     yamlConfiguration.set(ms.getName() + ".Breakable", Boolean.valueOf(ms.isBreakable()));
/* 192 */     yamlConfiguration.set(ms.getName() + ".Conditions", ms.getConditionList());
/* 193 */     yamlConfiguration.set(ms.getName() + ".ActiveMobs", Integer.valueOf(ms.getAssociatedMobs().size()));
/*     */     
/*     */     try {
/* 196 */       yamlConfiguration.save(spawnerFile);
/* 197 */     } catch (IOException e) {
/* 198 */       MythicMobs.error("Could not save configuration for spawner: " + ms.getName());
/* 199 */       e.printStackTrace();
/*     */     } 
/* 201 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<MythicSpawner> getSpawners() {
/* 205 */     return this.listSpawners;
/*     */   }
/*     */   
/*     */   public void tickSpawnerClocks() {
/* 209 */     this.listSpawners.stream().forEach(spawner -> {
/*     */           MythicMobs.inst().getTimingsHandler().markSpawnerNew(spawner.getName());
/*     */           spawner.tickSpawnerClock();
/*     */           MythicMobs.inst().getTimingsHandler().markSpawnerComplete(spawner.getName());
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetAndSaveAll() {
/* 218 */     for (MythicSpawner ms : this.listSpawners) {
/* 219 */       ms.unloadSpawner();
/*     */     }
/* 221 */     saveSpawners();
/*     */   }
/*     */   
/*     */   public ArrayList<MythicSpawner> getSpawnersByString(String string) {
/* 225 */     return getSpawnersByString(null, string);
/*     */   }
/*     */   
/*     */   public ArrayList<MythicSpawner> getSpawnersByString(AbstractLocation abstractLocation, String string) {
/* 229 */     if (string == null) return null; 
/* 230 */     ArrayList<MythicSpawner> msl = new ArrayList<>();
/*     */     
/* 232 */     if (string.startsWith("g:")) {
/* 233 */       String group = string.substring(2);
/*     */       
/* 235 */       msl = getSpawnersByGroup(group);
/* 236 */     } else if (string.startsWith("r:")) {
/* 237 */       if (abstractLocation == null) return null; 
/* 238 */       String sradius = string.substring(2);
/*     */       
/* 240 */       double radius = 0.0D;
/*     */       try {
/* 242 */         radius = Double.parseDouble(sradius);
/* 243 */       } catch (Exception exception) {}
/*     */       
/* 245 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 246 */         if (ms.getLocation().getWorld() != null && 
/* 247 */           ms.getLocation().getWorld().equals(abstractLocation.getWorld()) && 
/* 248 */           ms.distanceTo(abstractLocation) <= radius) {
/* 249 */           msl.add(ms);
/*     */         }
/*     */       }
/*     */     
/* 253 */     } else if (string.equals("*")) {
/* 254 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 255 */         msl.add(ms);
/*     */       }
/* 257 */     } else if (string.contains("*") || string.contains("?")) {
/* 258 */       for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
/* 259 */         if (ms.getName().matches(string.replace("?", ".?").replace("*", ".*?"))) {
/* 260 */           msl.add(ms);
/*     */         }
/*     */       } 
/*     */     } else {
/* 264 */       MythicSpawner ms = getSpawnerByName(string);
/*     */       
/* 266 */       if (ms != null) {
/* 267 */         msl.add(ms);
/*     */       }
/*     */     } 
/*     */     
/* 271 */     return msl;
/*     */   }
/*     */   
/*     */   public MythicSpawner getSpawnerByName(String name) {
/* 275 */     for (MythicSpawner s : this.listSpawners) {
/* 276 */       if (s.getName().equals(name)) return s; 
/*     */     } 
/* 278 */     return null;
/*     */   }
/*     */   
/*     */   public ArrayList<MythicSpawner> getSpawnersByGroup(String group) {
/* 282 */     ArrayList<MythicSpawner> msl = new ArrayList<>();
/*     */     
/* 284 */     for (MythicSpawner s : this.listSpawners) {
/*     */       try {
/* 286 */         if (s.getGroup().equals(group)) msl.add(s); 
/* 287 */       } catch (Exception e) {}
/*     */     } 
/*     */ 
/*     */     
/* 291 */     return msl;
/*     */   }
/*     */   
/*     */   public MythicSpawner getSpawnerAtLocation(AbstractLocation location) {
/* 295 */     return this.mmSpawners.getOrDefault(location, null);
/*     */   }
/*     */   
/*     */   public Optional<MythicSpawner> getSpawnerByHashcode(int hashcode) {
/* 299 */     return Optional.ofNullable(this.mmSpawnerHashcodeLookup.getOrDefault(Integer.valueOf(hashcode), null));
/*     */   }
/*     */   
/*     */   public Optional<Collection<MythicSpawner>> getSpawnersByChunk(String lookup) {
/* 303 */     return Optional.ofNullable(this.mmChunkSpawnerLookup.getOrDefault(lookup, null));
/*     */   }
/*     */   
/*     */   public boolean hasBreakableSpawner(AbstractLocation location) {
/* 307 */     if (this.mmSpawners.containsKey(location)) {
/* 308 */       return ((MythicSpawner)this.mmSpawners.get(location)).isBreakable();
/*     */     }
/* 310 */     return false;
/*     */   }
/*     */   
/*     */   public MythicSpawner createSpawner(String name, Location location, String mobName) {
/* 314 */     MythicMobs.debug(2, "Creating New Spawner at " + location.getX() + "," + location.getY() + "," + location.getZ());
/*     */     
/* 316 */     if (getSpawnerByName(name) != null) {
/* 317 */       MythicMobs.debug(3, "-- Spawner creation failed due to bad name.");
/* 318 */       return null;
/*     */     } 
/* 320 */     if (MythicMobs.inst().getMobManager().getMythicMob(mobName) == null) {
/* 321 */       MythicMobs.debug(3, "-- Spawner creation failed due to bad mob name.");
/* 322 */       return null;
/*     */     } 
/*     */     
/* 325 */     MythicSpawner newspawner = new MythicSpawner(name, BukkitAdapter.adapt(location), mobName);
/*     */     
/* 327 */     this.listSpawners.add(newspawner);
/* 328 */     saveSpawner(newspawner);
/*     */     
/* 330 */     MythicMobs.debug(2, "New spawner created successfully!");
/*     */     
/* 332 */     return newspawner;
/*     */   } public boolean copySpawner(String name, String nameNew, AbstractLocation location) {
/*     */     MythicSpawner msNew;
/* 335 */     MythicMobs.debug(2, "Creating Copy of Spawner at " + location.getX() + "," + location.getY() + "," + location.getZ());
/*     */     
/* 337 */     MythicSpawner ms = getSpawnerByName(name);
/*     */ 
/*     */     
/*     */     try {
/* 341 */       msNew = ms.clone();
/* 342 */     } catch (CloneNotSupportedException e) {
/* 343 */       e.printStackTrace();
/* 344 */       return false;
/*     */     } 
/*     */     
/* 347 */     msNew.setName(nameNew);
/* 348 */     msNew.setLocation(location);
/* 349 */     msNew.setWorld(location.getWorld().getName());
/* 350 */     msNew.setBlockX(location.getBlockX());
/* 351 */     msNew.setBlockY(location.getBlockY());
/* 352 */     msNew.setBlockZ(location.getBlockZ());
/* 353 */     msNew.setAssociatedMobs(new ArrayList());
/* 354 */     msNew.getAssociatedMobs().clear();
/* 355 */     msNew.setBreakable(false);
/* 356 */     msNew.setBreakable(ms.isBreakable());
/*     */     
/* 358 */     addSpawnerToChunkLookupTable(msNew);
/*     */     
/* 360 */     this.listSpawners.add(msNew);
/* 361 */     saveSpawner(msNew);
/*     */     
/* 363 */     MythicMobs.debug(2, "New spawner cloned successfully!");
/*     */     
/* 365 */     return true;
/*     */   }
/*     */   public boolean moveSpawner(String name, AbstractLocation location) {
/* 368 */     MythicMobs.debug(2, "Moving spawner to " + location.getX() + "," + location.getY() + "," + location.getZ());
/*     */     
/* 370 */     MythicSpawner ms = getSpawnerByName(name);
/*     */     
/* 372 */     if (ms == null) return false; 
/* 373 */     if (!moveSpawner(ms, location)) return false;
/*     */     
/* 375 */     return true;
/*     */   }
/*     */   public boolean moveSpawner(MythicSpawner ms, AbstractLocation location) {
/* 378 */     MythicMobs.debug(2, "Moving spawner to " + location.getX() + "," + location.getY() + "," + location.getZ());
/*     */     
/* 380 */     removeSpawnerFromChunkLookupTable(ms);
/*     */     
/* 382 */     ms.setLocation(location);
/* 383 */     ms.setWorld(location.getWorld().getName());
/* 384 */     ms.setBlockX(location.getBlockX());
/* 385 */     ms.setBlockY(location.getBlockY());
/* 386 */     ms.setBlockZ(location.getBlockZ());
/*     */     
/* 388 */     addSpawnerToChunkLookupTable(ms);
/*     */     
/* 390 */     saveSpawner(ms);
/*     */     
/* 392 */     MythicMobs.debug(2, "Spawner moved successfully!");
/*     */     
/* 394 */     return true;
/*     */   }
/*     */   public static synchronized boolean mobIsValid(Location location, UUID u) {
/* 397 */     if (location.getWorld() == null) return true; 
/* 398 */     Iterator<LivingEntity> it = location.getWorld().getLivingEntities().iterator();
/* 399 */     while (it.hasNext()) {
/* 400 */       LivingEntity l = it.next();
/* 401 */       if (l.getUniqueId().equals(u)) return true; 
/*     */     } 
/* 403 */     return false;
/*     */   }
/*     */   public static boolean playerWithinSpawnerRange(int range, AbstractLocation location) {
/* 406 */     int rangeSquared = (int)Math.pow(range, 2.0D);
/* 407 */     for (AbstractPlayer player : MythicMobs.inst().getEntityManager().getPlayers(location.getWorld())) {
/* 408 */       if (location.getWorld().equals(player.getWorld()) && location.distanceSquared(player.getLocation()) <= rangeSquared) {
/* 409 */         return true;
/*     */       }
/*     */     } 
/* 412 */     return false;
/*     */   }
/*     */   public static void RemoveMobFromSpawners(ActiveMob am) {
/* 415 */     if (am.getSpawner() != null) {
/* 416 */       am.getSpawner().markMobDead(am);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setSpawnerAttribute(MythicSpawner ms, String attr, String value) {
/*     */     try {
/* 423 */       switch (attr.toLowerCase()) {
/*     */         case "group":
/* 425 */           ms.setGroup(value);
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
/* 490 */           saveSpawner(ms);
/* 491 */           return true;case "radius": case "r": ms.setSpawnRadius(Integer.parseInt(value)); saveSpawner(ms); return true;case "radiusy": case "ry": ms.setSpawnRadiusY(Integer.parseInt(value)); saveSpawner(ms); return true;case "cooldown": case "cd": ms.setCooldownSeconds(Integer.parseInt(value)); saveSpawner(ms); return true;case "warmup": case "wu": ms.setWarmupSeconds(Integer.parseInt(value)); saveSpawner(ms); return true;case "maxmobs": ms.setMaxMobs(Integer.parseInt(value)); saveSpawner(ms); return true;case "moblevel": case "level": case "moblvl": try { ms.setMobLevel(new RandomInt(value)); } catch (Exception exception) {} saveSpawner(ms); return true;case "mobsperspawn": case "mps": ms.setMobsPerSpawn(Integer.parseInt(value)); saveSpawner(ms); return true;case "usetimer": case "timer": ms.setUseTimer(Boolean.parseBoolean(value)); saveSpawner(ms); return true;case "showflames": case "flames": ms.setShowFlames(Boolean.parseBoolean(value)); saveSpawner(ms); return true;case "activation": case "activationrange": case "ar": ms.setActivationRange(Integer.parseInt(value)); saveSpawner(ms); return true;case "leash": case "leashrange": ms.setLeashRange(Integer.parseInt(value)); saveSpawner(ms); return true;case "healonleash": case "leashheal": ms.setHealOnLeash(Boolean.parseBoolean(value)); saveSpawner(ms); return true;case "resetthreatonleash": case "leashresetthreat": ms.setLeashResetsThreat(Boolean.parseBoolean(value)); saveSpawner(ms); return true;case "mobtype": if (MythicMobs.inst().getMobManager().getMythicMob(value) != null) ms.setType(value);  saveSpawner(ms); return true;case "breakable": ms.setBreakable(Boolean.parseBoolean(value)); saveSpawner(ms); return true;case "checkforplayers": ms.setCheckForPlayers(Boolean.parseBoolean(value)); saveSpawner(ms); return true;
/*     */       }  MythicMobs.debug(1, "The attribute " + attr + " does not exist!"); return false;
/*     */     } catch (Exception e) {
/*     */       MythicMobs.debug(1, "The value " + value + " is invalid for attribute " + attr); return false;
/* 495 */     }  } public boolean addSpawnerCondition(MythicSpawner ms, String condition, String value) { if (SCondition.getSpawningConditionByName(condition) != null) {
/* 496 */       ms.getConditionList().add(condition + " " + value);
/*     */     } else {
/* 498 */       MythicMobs.debug(1, "The condition " + condition + " does not exist!");
/* 499 */       return false;
/*     */     } 
/*     */     
/* 502 */     saveSpawner(ms);
/* 503 */     return true; }
/*     */ 
/*     */   
/*     */   public boolean removeSpawnerCondition(MythicSpawner ms, String condition) {
/* 507 */     for (String s : ms.getConditionList()) {
/* 508 */       if (s.contains(condition)) {
/* 509 */         ms.getConditionList().remove(s);
/* 510 */         saveSpawner(ms);
/* 511 */         return true;
/*     */       } 
/*     */     } 
/* 514 */     return false;
/*     */   }
/*     */   public boolean removeSpawner(MythicSpawner ms) {
/* 517 */     this.listSpawners.remove(ms);
/* 518 */     this.mmSpawners.remove(ms.getLocation());
/* 519 */     this.mmSpawnerHashcodeLookup.remove(Integer.valueOf(ms.hashCode()));
/* 520 */     if (this.mmChunkSpawnerLookup.containsKey(ms.getChunkString())) {
/* 521 */       ((HashSet)this.mmChunkSpawnerLookup.get(ms.getChunkString())).remove(ms);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 526 */     File spawnerFile = new File(MythicMobs.inst().getDataFolder() + System.getProperty("file.separator") + "Spawners", ms.getName() + ".yml");
/* 527 */     spawnerFile.delete();
/*     */     
/* 529 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Location findSpawningLocation(Location location, int radius) {
/* 535 */     int tries = 0;
/*     */     
/* 537 */     while (tries < 64) {
/*     */       
/* 539 */       tries++;
/*     */       
/* 541 */       Location spawnLoc = randomizeSpawnLocation(location, radius);
/*     */       
/* 543 */       if (!areaIsEmpty(spawnLoc)) {
/*     */         continue;
/*     */       }
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
/* 565 */       return spawnLoc;
/*     */     } 
/*     */     
/* 568 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Location randomizeSpawnLocation(Location location, int r) {
/* 574 */     double x, y, z, sX = location.getX();
/* 575 */     double sY = location.getY();
/* 576 */     double sZ = location.getZ();
/*     */     do {
/* 578 */       x = randomRange(sX - r, sX + r);
/* 579 */       y = randomRange(sY - r, sY + r);
/* 580 */       z = randomRange(sZ - r, sZ + r);
/* 581 */     } while (location.distance(new Location(location.getWorld(), x, y, z)) > r);
/*     */     
/* 583 */     return new Location(location.getWorld(), x, y, z);
/*     */   }
/*     */   
/*     */   public static double randomRange(double arg0, double arg1) {
/* 587 */     double range = (arg0 < arg1) ? (arg1 - arg0) : (arg0 - arg1);
/* 588 */     if (range < 1.0D)
/* 589 */       return Math.floor(arg0) + 0.5D; 
/* 590 */     double min = (arg0 < arg1) ? arg0 : arg1;
/* 591 */     return Math.floor(min + Math.random() * range) + 0.5D;
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
/*     */   public boolean areaIsEmpty(Location loc) {
/* 611 */     return true;
/*     */   }
/*     */   
/*     */   public void addSpawnerToChunkLookupTable(MythicSpawner ms) {
/* 615 */     String cS = ms.getChunkString();
/* 616 */     if (this.mmChunkSpawnerLookup.contains(cS)) {
/* 617 */       ((HashSet<MythicSpawner>)this.mmChunkSpawnerLookup.get(cS)).add(ms);
/*     */     } else {
/* 619 */       HashSet<MythicSpawner> hs = new HashSet<>();
/* 620 */       hs.add(ms);
/* 621 */       this.mmChunkSpawnerLookup.put(cS, hs);
/*     */     } 
/*     */   }
/*     */   public void removeSpawnerFromChunkLookupTable(MythicSpawner ms) {
/* 625 */     String cS = ms.getChunkString();
/* 626 */     if (this.mmChunkSpawnerLookup.contains(cS))
/* 627 */       ((HashSet)this.mmChunkSpawnerLookup.get(cS)).remove(ms); 
/*     */   }
/*     */   
/*     */   public void buildSpawnerChunkLookupTable() {
/* 631 */     this.mmChunkSpawnerLookup.clear();
/*     */     
/* 633 */     for (MythicSpawner ms : this.listSpawners) {
/* 634 */       addSpawnerToChunkLookupTable(ms);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSpawnerCopyName(String name) {
/* 640 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<ItemStack> getSpawnerItem(String type) {
/* 645 */     ItemStack item = new ItemStack(VolatileMaterial.SPAWNER);
/*     */ 
/*     */ 
/*     */     
/* 649 */     return Optional.ofNullable(null);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\spawners\SpawnerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */