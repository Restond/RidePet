/*     */ package lumine.xikage.mythicmobs.spawning.spawners;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerManager;
/*     */ import io.lumine.xikage.mythicmobs.util.NumberConversions;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomInt;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Effect;
/*     */ 
/*     */ public class MythicSpawner implements Cloneable {
/*     */   private MythicConfig config;
/*     */   private boolean active = true;
/*     */   private boolean ready = true;
/*     */   private String name;
/*     */   private String group;
/*     */   private String mobName;
/*     */   private AbstractLocation location;
/*     */   private String world;
/*     */   private int blockX;
/*     */   private int blockY;
/*     */   private int blockZ;
/*     */   
/*     */   public AbstractLocation getLocation() {
/*  43 */     return this.location;
/*     */   }
/*  45 */   public int getBlockX() { return this.blockX; } public int getBlockY() { return this.blockY; } public int getBlockZ() { return this.blockZ; } public void setBlockX(int blockX) { this.blockX = blockX; } public void setBlockY(int blockY) { this.blockY = blockY; } public void setBlockZ(int blockZ) { this.blockZ = blockZ; }
/*     */ 
/*     */   
/*     */   private boolean useTimer = true;
/*  49 */   private long spawnCooldownMillis = 0L;
/*  50 */   private long spawnCooldownTimer = 0L;
/*  51 */   private long spawnWarmupMillis = 4L;
/*  52 */   private long spawnWarmupTimer = 0L;
/*     */   
/*  54 */   private int spawnRadius = 0; public int getSpawnRadius() { return this.spawnRadius; } public void setSpawnRadius(int spawnRadius) { this.spawnRadius = spawnRadius; }
/*  55 */    private int spawnRadiusY = 0; public int getSpawnRadiusY() { return this.spawnRadiusY; } public void setSpawnRadiusY(int spawnRadiusY) { this.spawnRadiusY = spawnRadiusY; }
/*     */   
/*  57 */   private int maxMobs = 1; public int getMaxMobs() { return this.maxMobs; } public void setMaxMobs(int maxMobs) { this.maxMobs = maxMobs; }
/*  58 */    private int mobsPerSpawn = 1; public int getMobsPerSpawn() { return this.mobsPerSpawn; } public void setMobsPerSpawn(int mobsPerSpawn) { this.mobsPerSpawn = mobsPerSpawn; }
/*  59 */    private RandomInt mobLevel = new RandomInt(1); public RandomInt getMobLevel() { return this.mobLevel; } public void setMobLevel(RandomInt mobLevel) { this.mobLevel = mobLevel; }
/*     */    private boolean showFlames = false;
/*  61 */   public boolean isShowFlames() { return this.showFlames; } public void setShowFlames(boolean showFlames) { this.showFlames = showFlames; }
/*  62 */   public boolean isBreakable() { return this.breakable; } private boolean breakable = false; public void setBreakable(boolean breakable) { this.breakable = breakable; }
/*     */    private boolean checkForPlayers = true;
/*  64 */   public boolean isCheckForPlayers() { return this.checkForPlayers; } public void setCheckForPlayers(boolean checkForPlayers) { this.checkForPlayers = checkForPlayers; }
/*  65 */    private int activationRange = 40; public int getActivationRange() { return this.activationRange; } public void setActivationRange(int activationRange) { this.activationRange = activationRange; }
/*  66 */    private int leashRange = 32; public int getLeashRange() { return this.leashRange; } public void setLeashRange(int leashRange) { this.leashRange = leashRange; } private boolean healOnLeash = false;
/*  67 */   public boolean isHealOnLeash() { return this.healOnLeash; } public void setHealOnLeash(boolean healOnLeash) { this.healOnLeash = healOnLeash; } private boolean leashResetsThreat = false;
/*  68 */   public boolean isLeashResetsThreat() { return this.leashResetsThreat; } public void setLeashResetsThreat(boolean leashResetsThreat) { this.leashResetsThreat = leashResetsThreat; }
/*     */   
/*  70 */   private ArrayList<String> conditions = new ArrayList<>();
/*     */ 
/*     */   
/*  73 */   protected int cachedActiveMobs = 0;
/*  74 */   private Collection<UUID> mobs = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */   
/*  76 */   private transient int leashTimer = 0;
/*  77 */   private transient int internalCooldown = 8;
/*  78 */   private transient int mobHeight = 0;
/*     */   
/*     */   public MythicSpawner(String name, AbstractLocation location, String mobName) {
/*  81 */     this.name = name;
/*  82 */     this.mobName = mobName;
/*     */     
/*  84 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(this.mobName);
/*     */     
/*  86 */     if (mm == null && 
/*  87 */       this.location == null) {
/*  88 */       MythicMobs.throwWarning("error-spawners-invalidmobtype", "Error enabling spawner '{0}' - MobType '{1}' is invalid! Spawner cannot initialize. Disabling.", new Object[] { this.name, this.mobName });
/*  89 */       Disable();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  94 */     if (mm.getMythicEntity() != null) {
/*  95 */       this.mobHeight = mm.getMythicEntity().getHeight();
/*     */     } else {
/*  97 */       this.mobHeight = 2;
/*     */     } 
/*     */     
/* 100 */     this.location = location;
/* 101 */     this.world = location.getWorld().getName();
/* 102 */     this.blockX = location.getBlockX();
/* 103 */     this.blockY = location.getBlockY();
/* 104 */     this.blockZ = location.getBlockZ();
/* 105 */     this.spawnRadius = 0;
/*     */     
/* 107 */     if (this.location == null) {
/* 108 */       MythicMobs.throwWarning("error-spawners-invalidlocation", "Location data for spawner '{0}' is invalid! Spawner cannot be attached to world. Disabling.", new Object[] { this.name });
/* 109 */       Disable();
/*     */     } 
/*     */     
/* 112 */     this.spawnCooldownTimer = System.currentTimeMillis();
/* 113 */     this.spawnWarmupTimer = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public MythicSpawner(String name, MythicConfig config) {
/* 117 */     this.name = name;
/* 118 */     this.config = config;
/*     */     
/* 120 */     this.group = config.getString("SpawnerGroup");
/* 121 */     this.mobName = config.getString("MobName");
/*     */     
/* 123 */     this.world = config.getString("World");
/* 124 */     this.blockX = config.getInt("X");
/* 125 */     this.blockY = config.getInt("Y");
/* 126 */     this.blockZ = config.getInt("Z");
/* 127 */     this.spawnRadius = config.getInt("Radius", 0);
/* 128 */     this.spawnRadiusY = config.getInt("RadiusY", 0);
/* 129 */     this.useTimer = config.getBoolean("UseTimer", true);
/*     */     
/* 131 */     this.maxMobs = config.getInt("MaxMobs", -1);
/* 132 */     this.mobsPerSpawn = config.getInt("MobsPerSpawn", 1);
/* 133 */     setCooldownSeconds(config.getInt("Cooldown", 0));
/* 134 */     setRemainingCooldownSeconds(config.getInt("CooldownTimer", 0));
/* 135 */     setWarmupSeconds(config.getInt("Warmup", 0));
/* 136 */     setRemainingWarmupSeconds(config.getInt("WarmupTimer", 0));
/* 137 */     this.activationRange = config.getInt("ActivationRange", 40);
/* 138 */     this.leashRange = config.getInt("LeashRange", 0);
/* 139 */     this.healOnLeash = config.getBoolean("HealOnLeash", false);
/* 140 */     this.leashResetsThreat = config.getBoolean("ResetThreatOnLeash", false);
/*     */     
/* 142 */     String mobLevel = config.getString("MobLevel", "1");
/*     */     
/* 144 */     setBreakable(config.getBoolean("Breakable", false));
/* 145 */     this.checkForPlayers = config.getBoolean("CheckForPlayers", true);
/*     */     
/* 147 */     boolean showFlames = config.getBoolean("ShowFlames", false);
/*     */     
/* 149 */     List<String> strConditions = config.getStringList("Conditions");
/* 150 */     int activeMobs = config.getInt("ActiveMobs", 0);
/*     */     
/* 152 */     ArrayList<String> conditions = new ArrayList<>();
/*     */     
/* 154 */     for (String c : strConditions) {
/* 155 */       conditions.add(c);
/*     */     }
/*     */     
/* 158 */     if (this.spawnRadius < 0) this.spawnRadius = 0; 
/* 159 */     if (this.spawnRadiusY < 1) this.spawnRadiusY = 1;
/*     */     
/* 161 */     if (getRemainingWarmupSeconds() > 0 || getRemainingCooldownSeconds() > 0) {
/* 162 */       this.ready = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public MythicSpawner(String name, String group, String mobName, String world, int x, int y, int z, int radius, int radiusY, boolean useTimer, int maxMobs, int mps, String moblevel, int cooldown, int cooldownTimer, int warmup, int warmupTimer, int activeRange, int leashRange, boolean leashHeal, boolean leashResetThreat, boolean showFlames, boolean breakable, int activeMobs, boolean checkForPlayers, ArrayList<String> conditions) {
/* 167 */     MythicMobs.debug(1, "Loading spawner: " + name + "");
/* 168 */     this.name = name;
/* 169 */     this.group = group;
/* 170 */     this.mobName = mobName;
/*     */     
/* 172 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(this.mobName);
/*     */     
/* 174 */     if (mm == null) {
/* 175 */       MythicMobs.throwWarning("error-spawners-invalidmobtype", "Error enabling spawner '{0}' - MobType '{1}' is invalid! Spawner cannot initialize. Disabling.", new Object[] { this.name, this.mobName });
/* 176 */       Disable();
/*     */     } 
/*     */ 
/*     */     
/* 180 */     this.mobHeight = mm.getMythicEntity().getHeight();
/*     */     
/* 182 */     BukkitWorld bukkitWorld = new BukkitWorld(world);
/* 183 */     this.location = new AbstractLocation((AbstractWorld)bukkitWorld, x, y, z);
/*     */     
/* 185 */     if (this.location == null) {
/* 186 */       MythicMobs.throwWarning("error-spawners-invalidlocation", "Location data for spawner '{0}' is invalid! Spawner cannot be attached to world. Disabling.", new Object[] { this.name });
/* 187 */       Disable();
/*     */     } 
/* 189 */     this.world = world;
/* 190 */     this.blockX = x;
/* 191 */     this.blockY = y;
/* 192 */     this.blockZ = z;
/* 193 */     this.spawnRadius = radius;
/* 194 */     this.spawnRadiusY = radiusY;
/* 195 */     this.useTimer = useTimer;
/* 196 */     this.maxMobs = maxMobs;
/* 197 */     this.mobsPerSpawn = mps;
/* 198 */     this.mobLevel = new RandomInt(moblevel);
/* 199 */     setCooldownSeconds(cooldown);
/* 200 */     setRemainingCooldownSeconds(cooldownTimer);
/* 201 */     setWarmupSeconds(warmup);
/* 202 */     setRemainingWarmupSeconds(warmupTimer);
/* 203 */     this.showFlames = showFlames;
/* 204 */     this.activationRange = activeRange;
/* 205 */     this.leashRange = leashRange;
/* 206 */     this.healOnLeash = leashHeal;
/* 207 */     this.leashResetsThreat = leashResetThreat;
/* 208 */     this.cachedActiveMobs = activeMobs;
/* 209 */     this.conditions = conditions;
/* 210 */     setBreakable(breakable);
/* 211 */     this.checkForPlayers = checkForPlayers;
/*     */     
/* 213 */     if (this.spawnRadius < 0) this.spawnRadius = 0; 
/* 214 */     if (this.spawnRadiusY < 1) this.spawnRadiusY = 1;
/*     */     
/* 216 */     if (getRemainingWarmupSeconds() > 0 || getRemainingCooldownSeconds() > 0)
/* 217 */       this.ready = false; 
/*     */   }
/*     */   
/*     */   public void unloadSpawner() {
/* 221 */     this.mobs.removeIf(uuid -> {
/*     */           Optional<ActiveMob> maybeMob = MythicMobs.inst().getMobManager().getActiveMob(uuid);
/*     */           if (maybeMob.isPresent()) {
/*     */             if (!((ActiveMob)maybeMob.get()).getType().isPersistent()) {
/*     */               ActiveMob am = maybeMob.get();
/*     */               if (!am.isDead() && am.getEntity().isValid()) {
/*     */                 this.cachedActiveMobs++;
/*     */                 am.getEntity().remove();
/*     */                 MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/*     */               } 
/*     */             } else {
/*     */               return false;
/*     */             } 
/*     */           }
/*     */           return true;
/*     */         });
/* 237 */     this.internalCooldown = 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 245 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 253 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGroup() {
/* 261 */     return this.group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroup(String group) {
/* 269 */     this.group = group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeName() {
/* 277 */     return this.mobName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/* 285 */     this.mobName = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(MythicMob type) {
/* 293 */     this.mobName = type.getInternalName();
/*     */   }
/*     */   
/*     */   public void setLocation(AbstractLocation location) {
/* 297 */     this.location = location;
/* 298 */     this.blockX = location.getBlockX();
/* 299 */     this.blockY = location.getBlockY();
/* 300 */     this.blockZ = location.getBlockZ();
/* 301 */     this.world = location.getWorld().getName();
/*     */   }
/*     */   
/*     */   public String getWorldName() {
/* 305 */     return this.world;
/*     */   }
/*     */   
/*     */   public void setWorld(String worldName) {
/* 309 */     this.world = worldName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnCooldown() {
/* 316 */     this.spawnCooldownTimer = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnCooldown() {
/* 323 */     return (this.spawnCooldownMillis + this.spawnCooldownTimer - System.currentTimeMillis() > 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCooldownSeconds() {
/* 331 */     return (int)TimeUnit.MILLISECONDS.toSeconds(this.spawnCooldownMillis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCooldownSeconds(int cooldown) {
/* 339 */     this.spawnCooldownMillis = TimeUnit.SECONDS.toMillis(cooldown);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRemainingCooldownSeconds() {
/* 346 */     if (isOnCooldown()) {
/* 347 */       return (int)Math.abs(TimeUnit.MILLISECONDS.toSeconds(this.spawnCooldownMillis + this.spawnCooldownTimer - System.currentTimeMillis()));
/*     */     }
/* 349 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRemainingCooldownSeconds(long seconds) {
/* 357 */     this.spawnCooldownTimer = System.currentTimeMillis() - this.spawnCooldownMillis + TimeUnit.SECONDS.toMillis(seconds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnWarmup() {
/* 364 */     this.spawnWarmupTimer = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnWarmup() {
/* 371 */     return (this.spawnWarmupMillis + this.spawnWarmupTimer - System.currentTimeMillis() > 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWarmupSeconds() {
/* 379 */     return (int)TimeUnit.MILLISECONDS.toSeconds(this.spawnWarmupMillis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWarmupSeconds(int warmup) {
/* 387 */     this.spawnWarmupMillis = TimeUnit.SECONDS.toMillis(warmup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRemainingWarmupSeconds() {
/* 395 */     if (isOnWarmup()) {
/* 396 */       return (int)Math.abs(TimeUnit.MILLISECONDS.toSeconds(this.spawnWarmupMillis + this.spawnWarmupTimer - System.currentTimeMillis()));
/*     */     }
/* 398 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRemainingWarmupSeconds(long seconds) {
/* 406 */     this.spawnWarmupTimer = System.currentTimeMillis() - this.spawnWarmupMillis + TimeUnit.SECONDS.toMillis(seconds);
/*     */   }
/*     */   
/*     */   public boolean getUseTimer() {
/* 410 */     return this.useTimer;
/*     */   }
/*     */   
/*     */   public void setUseTimer(boolean bool) {
/* 414 */     this.useTimer = bool;
/*     */   }
/*     */   
/*     */   public List<String> getConditionList() {
/* 418 */     return this.conditions;
/*     */   }
/*     */   
/*     */   public Collection<UUID> getAssociatedMobs() {
/* 422 */     return this.mobs;
/*     */   }
/*     */   
/*     */   public void setAssociatedMobs(Collection<UUID> collection) {
/* 426 */     this.mobs = collection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInternalCooldown() {
/* 436 */     return this.internalCooldown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickSpawnerClock() {
/* 444 */     this.internalCooldown--;
/* 445 */     boolean spawn = true;
/*     */     
/* 447 */     if (isOnCooldown() || isOnWarmup()) {
/* 448 */       spawn = false;
/* 449 */       this.ready = false;
/* 450 */     } else if (!this.useTimer) {
/* 451 */       spawn = false;
/*     */     } 
/*     */     
/* 454 */     if (!this.active) {
/*     */       return;
/*     */     }
/*     */     
/* 458 */     if (this.internalCooldown <= 1) {
/* 459 */       if ((this.location == null || Bukkit.getWorld(this.world) == null) && 
/* 460 */         !reattachToWorld()) {
/* 461 */         this.internalCooldown = 8;
/*     */         
/*     */         return;
/*     */       } 
/* 465 */       if (!this.location.isLoaded()) {
/* 466 */         this.internalCooldown = 5;
/*     */         return;
/*     */       } 
/* 469 */       if (this.checkForPlayers && !SpawnerManager.playerWithinSpawnerRange(this.activationRange, this.location)) {
/* 470 */         this.internalCooldown = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 480 */       if (this.maxMobs <= getNumberOfMobs()) {
/* 481 */         cleanMobList();
/* 482 */         if (isOnWarmup()) {
/*     */           return;
/*     */         }
/*     */       } 
/* 486 */     } else if (this.internalCooldown > 1) {
/*     */       return;
/*     */     } 
/*     */     
/* 490 */     if (this.leashRange > 0) {
/* 491 */       this.leashTimer++;
/* 492 */       if (this.leashTimer >= 2) {
/* 493 */         Leash();
/* 494 */         this.leashTimer = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 498 */     if (this.showFlames) {
/* 499 */       BukkitAdapter.adapt(this.location).getWorld().playEffect(BukkitAdapter.adapt(this.location), Effect.MOBSPAWNER_FLAMES, 0);
/*     */     }
/*     */     
/* 502 */     if (this.cachedActiveMobs > 0) {
/* 503 */       while (this.cachedActiveMobs > 0) {
/* 504 */         Spawn();
/* 505 */         this.cachedActiveMobs--;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 510 */     if (this.maxMobs <= getNumberOfMobs()) {
/*     */       return;
/*     */     }
/*     */     
/* 514 */     if (spawn && this.useTimer && !ConfigManager.debugMode && 
/* 515 */       checkSpawnConditions()) {
/* 516 */       Collection<ActiveMob> spawns = Spawn();
/*     */       
/* 518 */       for (ActiveMob am : spawns) {
/* 519 */         new TriggeredSkill(SkillTrigger.READY, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ActivateSpawner() {
/* 526 */     if (this.location.getWorld() == null) {
/* 527 */       MythicMobs.debug(4, "Spawner world is unloaded.");
/*     */       return;
/*     */     } 
/* 530 */     if (ConfigManager.debugLevel > 0 || this.showFlames) {
/* 531 */       BukkitAdapter.adapt(this.location).getWorld().playEffect(BukkitAdapter.adapt(this.location), Effect.MOBSPAWNER_FLAMES, 0);
/*     */     }
/*     */     
/* 534 */     if (!this.active || isOnCooldown() || isOnWarmup()) {
/*     */       return;
/*     */     }
/*     */     
/* 538 */     if (this.maxMobs <= getNumberOfMobs())
/*     */     {
/* 540 */       if (cleanMobList()) {
/*     */         
/* 542 */         if (this.spawnWarmupMillis > 0L) {
/* 543 */           setOnWarmup();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */     }
/* 551 */     if (checkSpawnConditions()) {
/* 552 */       Collection<ActiveMob> spawns = Spawn();
/*     */       
/* 554 */       for (ActiveMob am : spawns) {
/* 555 */         new TriggeredSkill(SkillTrigger.READY, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkSpawnConditions() {
/* 562 */     if (this.conditions != null) {
/* 563 */       for (String strCondition : this.conditions) {
/*     */         
/* 565 */         String conditionData, split[] = strCondition.split(" ");
/*     */         
/* 567 */         if (split.length == 1) {
/* 568 */           conditionData = null;
/*     */         } else {
/* 570 */           conditionData = split[1];
/*     */         } 
/*     */         try {
/* 573 */           if (!SCondition.getSpawningConditionByName(split[0]).check(BukkitAdapter.adapt(this.location), null, conditionData))
/*     */           {
/* 575 */             return false;
/*     */           
/*     */           }
/*     */         }
/* 579 */         catch (Exception e) {
/* 580 */           MythicMobs.throwWarning("error-spawnconditions-invalid", "A spawning condition appears to be invalid: {0}", new Object[] { strCondition });
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 585 */     return true;
/*     */   }
/*     */   public Collection<ActiveMob> Spawn() {
/* 588 */     if (!this.active) return null;
/*     */     
/* 590 */     MythicMobs.debug(3, "Spawner " + this.name + " Spawn() called. Spawning mobs...");
/*     */     
/* 592 */     if (this.cachedActiveMobs == 0) {
/* 593 */       setOnCooldown();
/*     */     }
/*     */     
/* 596 */     AbstractLocation loc = new AbstractLocation(this.location.getWorld(), this.location.getX() + 0.5D, this.location.getY() + 1.0D, this.location.getZ() + 0.5D);
/*     */     
/* 598 */     boolean good = false;
/* 599 */     int j = 25;
/*     */ 
/*     */     
/* 602 */     List<ActiveMob> mobs = new ArrayList<>();
/*     */     
/* 604 */     for (int i = this.mobsPerSpawn; i > 0 && 
/* 605 */       this.maxMobs > getNumberOfMobs(); i--) {
/*     */ 
/*     */ 
/*     */       
/* 609 */       if (this.spawnRadius > 0 || this.spawnRadiusY > 1) {
/* 610 */         j = 25;
/* 611 */         good = false;
/* 612 */         while (!good) {
/*     */           
/* 614 */           int ny, nx = this.location.getBlockX() - this.spawnRadius + MythicMobs.r.nextInt(this.spawnRadius * 2);
/* 615 */           int nz = this.location.getBlockZ() - this.spawnRadius + MythicMobs.r.nextInt(this.spawnRadius * 2);
/*     */           
/* 617 */           if (this.spawnRadiusY > 0) {
/* 618 */             ny = this.location.getBlockY() - this.spawnRadiusY + MythicMobs.r.nextInt(this.spawnRadiusY * 2);
/*     */           } else {
/* 620 */             ny = this.location.getBlockY() + 1;
/*     */           } 
/*     */           
/* 623 */           good = true;
/* 624 */           for (int k = 0; k < this.mobHeight + 1; k++) {
/* 625 */             loc = new AbstractLocation(this.location.getWorld(), nx, (ny + k), nz);
/* 626 */             if (BukkitAdapter.adapt(loc).getBlock().getType().isSolid())
/*     */             {
/* 628 */               good = false;
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 634 */           if (good) {
/*     */             
/* 636 */             loc = new AbstractLocation(this.location.getWorld(), nx, ny, nz);
/*     */             
/*     */             break;
/*     */           } 
/* 640 */           j--;
/*     */ 
/*     */           
/* 643 */           if (j == 0) {
/*     */             
/* 645 */             loc = new AbstractLocation(this.location.getWorld(), loc.getBlockX(), (loc.getBlockY() + 1), loc.getBlockZ());
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 650 */         loc = new AbstractLocation(this.location.getWorld(), this.location.getBlockX(), this.location.getBlockY(), this.location.getBlockZ());
/* 651 */         loc = loc.add(0.0D, 1.0D, 0.0D);
/*     */       } 
/*     */       
/* 654 */       ActiveMob am = MythicMobs.inst().getMobManager().spawnMob(this.mobName, loc.add(0.5D, 0.0D, 0.5D), this.mobLevel.get());
/* 655 */       if (am == null) {
/* 656 */         MythicMobs.debug(3, "-- Spawner " + this.name + " failed to spawn mob: SpawnMythicMob returned null.");
/* 657 */         return mobs;
/*     */       } 
/* 659 */       this.mobs.add(am.getUniqueId());
/* 660 */       am.setSpawner(this);
/* 661 */       mobs.add(am);
/*     */     } 
/* 663 */     MythicMobs.debug(3, "-- Spawner spawned mob(s). Number of mobs tied to spawner now: " + getNumberOfMobs());
/* 664 */     return mobs;
/*     */   }
/*     */   public void Leash() {
/* 667 */     if (this.leashRange < 1)
/*     */       return; 
/* 669 */     for (UUID uuid : this.mobs) {
/* 670 */       Optional<ActiveMob> maybeMob = MythicMobs.inst().getMobManager().getActiveMob(uuid);
/*     */       
/* 672 */       if (!maybeMob.isPresent())
/* 673 */         continue;  ActiveMob am = maybeMob.get();
/* 674 */       if (am.getLocation().getWorld().equals(this.location.getWorld())) {
/* 675 */         if (this.location.distanceSquared(am.getEntity().getLocation()) > NumberConversions.square(this.leashRange)) {
/* 676 */           if (am.getEntity().getVehicle() != null) {
/* 677 */             AbstractEntity l = am.getEntity();
/* 678 */             while (l.getVehicle() != null) {
/* 679 */               if (this.healOnLeash && 
/* 680 */                 l.isLiving()) l.setHealth(l.getMaxHealth());
/*     */               
/* 682 */               if (this.leashResetsThreat) {
/* 683 */                 MythicMobs.inst().getMobManager().getMythicMobInstance(l).resetTarget();
/*     */               }
/* 685 */               l = l.getVehicle();
/*     */             } 
/*     */           } 
/* 688 */           doLeash(am);
/*     */         }  continue;
/*     */       } 
/* 691 */       doLeash(am);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doLeash(ActiveMob am) {
/* 696 */     AbstractLocation spawnLocation = this.location.clone().add(0.5D, 1.0D, 0.5D);
/*     */     
/* 698 */     am.getEntity().teleport(spawnLocation);
/* 699 */     if (this.healOnLeash && 
/* 700 */       am.getEntity().isLiving()) am.getEntity().setHealth(am.getEntity().getMaxHealth());
/*     */     
/* 702 */     if (this.leashResetsThreat)
/* 703 */       am.resetTarget(); 
/*     */   }
/*     */   
/*     */   private boolean cleanMobList() {
/* 707 */     return this.mobs.removeIf(uuid -> {
/*     */           if (!MythicMobs.inst().getMobManager().isActiveMob(uuid)) {
/*     */             return true;
/*     */           }
/*     */           ActiveMob am = MythicMobs.inst().getMobManager().getActiveMob(uuid).get();
/*     */           if (am.isDead()) {
/*     */             if (getNumberOfMobs() >= this.maxMobs) {
/*     */               setOnWarmup();
/*     */             }
/*     */             return true;
/*     */           } 
/*     */           return false;
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackMob(ActiveMob am) {
/* 724 */     this.mobs.add(am.getUniqueId());
/*     */   }
/*     */   public void markMobDespawned(ActiveMob am) {
/* 727 */     this.mobs.remove(am.getUniqueId());
/*     */   }
/*     */   public void markMobDead(ActiveMob am) {
/* 730 */     if (this.mobs.contains(am.getUniqueId())) {
/* 731 */       if (getNumberOfMobs() >= this.maxMobs)
/*     */       {
/* 733 */         setOnWarmup();
/*     */       }
/* 735 */       this.mobs.remove(am.getUniqueId());
/*     */     } 
/*     */   }
/*     */   public boolean reattachToWorld() {
/* 739 */     if (Bukkit.getWorld(this.world) == null) return false; 
/* 740 */     this.location = new AbstractLocation(BukkitAdapter.adapt(Bukkit.getWorld(this.world)), this.blockX, this.blockY, this.blockZ);
/*     */     
/* 742 */     if (this.location == null) {
/* 743 */       MythicMobs.debug(2, "Spawner " + this.name + " has an invalid location and could not be reattached to world. Perhaps the world is unloaded?");
/* 744 */       return false;
/*     */     } 
/* 746 */     return true;
/*     */   }
/*     */   public String getInternalName() {
/* 749 */     return this.name;
/*     */   }
/*     */   public int getNumberOfMobs() {
/* 752 */     return this.mobs.size() + this.cachedActiveMobs;
/*     */   }
/*     */   public void Disable() {
/* 755 */     this.active = false;
/*     */   }
/*     */   public void Enable() {
/* 758 */     this.active = true;
/*     */   }
/*     */   public double distanceTo(AbstractLocation abstractLocation) {
/* 761 */     return abstractLocation.distance(this.location);
/*     */   }
/*     */   public io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner clone() throws CloneNotSupportedException {
/* 764 */     return (io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner)super.clone();
/*     */   }
/*     */   public void resetTimers() {
/* 767 */     this.spawnWarmupTimer = System.currentTimeMillis() - this.spawnWarmupMillis;
/* 768 */     this.spawnCooldownTimer = System.currentTimeMillis() - this.spawnCooldownMillis;
/*     */   }
/*     */   
/*     */   public int getChunkX() {
/* 772 */     return this.blockX >> 4;
/*     */   }
/*     */   public int getChunkZ() {
/* 775 */     return this.blockZ >> 4;
/*     */   }
/*     */   public String getChunkString() {
/* 778 */     return this.world + "-" + getChunkX() + "-" + getChunkZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 783 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   public int getNumberOfCachedMobs() {
/* 787 */     return this.cachedActiveMobs;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\spawners\MythicSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */