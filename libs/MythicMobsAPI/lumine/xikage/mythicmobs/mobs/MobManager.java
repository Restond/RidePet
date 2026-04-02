/*     */ package lumine.xikage.mythicmobs.mobs;
/*     */ 
/*     */ import com.google.common.cache.Cache;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.serialization.SerializingModule;
/*     */ import io.lumine.utils.serialization.WrappedJsonFile;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.utils.terminable.TerminableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.IOHandler;
/*     */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.legacy.LegacySkillHandler;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.CasterRegistry;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MobRegistry;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMobStack;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntityType;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableSerializer;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.NBTCompoundSerializer;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.NBTSerializer;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ 
/*     */ 
/*     */ public class MobManager
/*     */   extends SerializingModule
/*     */   implements CasterRegistry, Terminable
/*     */ {
/*     */   protected final MythicMobs core;
/*  73 */   private ConcurrentHashMap<String, MythicMob> mmList = new ConcurrentHashMap<>();
/*  74 */   private ConcurrentHashMap<Integer, MythicMob> mmHashcodeLookup = new ConcurrentHashMap<>();
/*  75 */   private ConcurrentHashMap<String, MythicMob> mmDisplayLookup = new ConcurrentHashMap<>();
/*     */   
/*  77 */   private List<MythicMobStack> listMobStacks = new ArrayList<>(); private WrappedJsonFile<MobRegistry> mobRegistry;
/*  78 */   private ConcurrentHashMap<MythicEntityType, MythicMob> mmDefaultList = new ConcurrentHashMap<>();
/*     */   public WrappedJsonFile<MobRegistry> getMobRegistry() {
/*  80 */     return this.mobRegistry;
/*     */   }
/*  82 */   private transient TerminableRegistry components = TerminableRegistry.create();
/*  83 */   private transient ConcurrentHashMap<UUID, ActiveMob> activeMobsInCombat = new ConcurrentHashMap<>();
/*     */   
/*  85 */   private transient Cache<UUID, Boolean> mmVoidList = (Cache<UUID, Boolean>)CacheBuilder.newBuilder()
/*  86 */     .maximumSize(1000L)
/*  87 */     .expireAfterWrite(10L, TimeUnit.MINUTES)
/*  88 */     .build((CacheLoader)new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MobManager(MythicMobs core) {
/*  95 */     super((JavaPlugin)core, "SavedData");
/*     */     
/*  97 */     this.core = core;
/*     */     
/*  99 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       .GSON = (new GsonBuilder()).enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(AbstractWorld.class, this.GSON.getAdapter(BukkitWorld.class)).registerTypeAdapter(Tag.class, new NBTSerializer()).registerTypeAdapter(CompoundTag.class, new NBTCompoundSerializer()).registerTypeAdapter(Variable.class, new VariableSerializer()).create();
/*     */     
/*     */     try {
/* 110 */       this.mobRegistry = loadFile(getModuleFile("active-mobs.json"), MobRegistry.class);
/* 111 */       Scheduler.runSync(() -> ((MobRegistry)this.mobRegistry.get()).loadSaved());
/* 112 */     } catch (Exception ex) {
/* 113 */       MythicLogger.error("Failed to load active-mobs.json: file may have been corrupted during a server crash or improper shutdown. Please repair or delete this file to restore mob saving capabilities.");
/*     */       
/* 115 */       this.mobRegistry = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/* 120 */     this.components.terminate();
/* 121 */     ((MobRegistry)this.mobRegistry.get()).terminate();
/* 122 */     this.mobRegistry.save();
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadMobs() {
/* 134 */     IOLoader<MythicMobs> defaultMobs = new IOLoader((JavaPlugin)MythicMobs.inst(), "VanillaMobs.yml", "Mobs");
/* 135 */     defaultMobs = new IOLoader((JavaPlugin)MythicMobs.inst(), "ExampleMobs.yml", "Mobs");
/*     */     
/* 137 */     List<File> mobFiles = IOHandler.getAllFiles(defaultMobs.getFile().getParent());
/* 138 */     List<IOLoader<MythicMobs>> mobLoaders = IOHandler.getSaveLoad((JavaPlugin)MythicMobs.inst(), mobFiles, "Mobs");
/* 139 */     this.mmList.clear();
/* 140 */     this.mmDefaultList.clear();
/* 141 */     this.mmHashcodeLookup.clear();
/* 142 */     this.mmDisplayLookup.clear();
/*     */     
/* 144 */     for (IOLoader<MythicMobs> sl : mobLoaders) {
/* 145 */       for (String name : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
/*     */         
/*     */         try {
/* 148 */           MythicConfig mc = new MythicConfig(name, sl.getFile(), sl.getCustomConfig());
/* 149 */           String file = sl.getFile().getPath();
/*     */           
/* 151 */           if (MythicEntity.getMythicEntity(name) != null) {
/* 152 */             MythicEntityType met = MythicEntityType.get(name);
/* 153 */             MythicMob mythicMob = new MythicMob(file, name, mc);
/* 154 */             this.mmDefaultList.put(met, mythicMob); continue;
/* 155 */           }  if (sl.getCustomConfig().getString(name + ".MobStack") != null) {
/* 156 */             String stack = sl.getCustomConfig().getString(name + ".MobStack");
/* 157 */             this.listMobStacks.add(new MythicMobStack(name, stack, file)); continue;
/*     */           } 
/* 159 */           String Display = sl.getCustomConfig().getString(name + ".Display");
/* 160 */           Display = sl.getCustomConfig().getString(name + ".DisplayName", Display);
/*     */           
/* 162 */           MythicMob mm = new MythicMob(file, name, mc);
/* 163 */           this.mmList.put(name, mm);
/*     */           
/* 165 */           if (!this.mmHashcodeLookup.containsKey(Integer.valueOf(name.hashCode()))) {
/* 166 */             this.mmHashcodeLookup.put(Integer.valueOf(name.hashCode()), mm);
/*     */           } else {
/* 168 */             MythicMob conflict = this.mmHashcodeLookup.get(Integer.valueOf(name.hashCode()));
/* 169 */             MythicMobs.error("WARNING: HashCode collision detected when loading mobs.");
/* 170 */             MythicMobs.error("Mob 1 Hash: " + name.hashCode() + " Type: " + ((MythicMob)this.mmHashcodeLookup.get(Integer.valueOf(name.hashCode()))).getInternalName());
/* 171 */             MythicMobs.error("Mob 2 Hash: " + conflict.getInternalName().hashCode() + " Type: " + conflict.getInternalName());
/* 172 */             MythicMobs.error("We recommend changing one of these mobs' internal name to avoid issues resolving mob type.");
/*     */           } 
/*     */           
/* 175 */           if (Display != null) {
/* 176 */             this.mmDisplayLookup.put(Display, mm);
/*     */           }
/*     */         }
/* 179 */         catch (Exception ex) {
/* 180 */           MythicLogger.error("Error loading mob '" + name + "'. Enable debugging for a stack trace.");
/* 181 */           if (ConfigManager.debugLevel > 0) {
/* 182 */             ex.printStackTrace();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<MythicMob> getMobTypes() {
/* 194 */     return this.mmList.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<String> getMobNames() {
/* 202 */     return this.mmList.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<MythicMob> getVanillaTypes() {
/* 210 */     return this.mmDefaultList.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<MythicMob> getVanillaType(MythicEntityType type) {
/* 220 */     return Optional.ofNullable(this.mmDefaultList.getOrDefault(type, null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<MythicMobStack> getMobStacks() {
/* 228 */     return this.listMobStacks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<SkillCaster> getSkillCaster(UUID uuid) {
/* 237 */     return (Optional)Optional.ofNullable(((MobRegistry)this.mobRegistry.get()).get(uuid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ActiveMob> getActiveMobs() {
/* 245 */     return ((MobRegistry)this.mobRegistry.get()).values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ActiveMob> getActiveMobsInCombat() {
/* 254 */     return this.activeMobsInCombat.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActiveMob(AbstractEntity entity) {
/* 263 */     return isActiveMob(entity.getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActiveMob(UUID uuid) {
/* 272 */     return ((MobRegistry)this.mobRegistry.get()).isActiveMob(uuid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<ActiveMob> getActiveMob(UUID uuid) {
/* 281 */     return ((MobRegistry)this.mobRegistry.get()).getActiveMob(uuid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<UUID> getVoidList() {
/* 289 */     return this.mmVoidList.asMap().keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIgnoreEntity(UUID uuid) {
/* 297 */     this.mmVoidList.put(uuid, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIgnoredEntity(UUID uuid) {
/* 306 */     return this.mmVoidList.asMap().keySet().contains(uuid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int removeAllMobs() {
/* 314 */     int amount = 0;
/* 315 */     for (ActiveMob am : ((MobRegistry)this.mobRegistry.get()).values()) {
/* 316 */       if (am.getType() != null && am.getType().isPersistent()) {
/*     */         continue;
/*     */       }
/* 319 */       am.setDespawned();
/* 320 */       unregisterActiveMob(am);
/* 321 */       am.getEntity().remove();
/* 322 */       amount++;
/*     */     } 
/* 324 */     return amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int removeAllAllMobs() {
/* 332 */     int amount = 0;
/* 333 */     for (ActiveMob am : ((MobRegistry)this.mobRegistry.get()).values()) {
/* 334 */       am.setDespawned();
/* 335 */       unregisterActiveMob(am);
/* 336 */       am.getEntity().remove();
/* 337 */       amount++;
/*     */     } 
/* 339 */     return amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int despawnAllMobs() {
/* 347 */     int amount = 0;
/* 348 */     for (ActiveMob am : ((MobRegistry)this.mobRegistry.get()).values()) {
/* 349 */       if ((am.getType()).optionDespawn && !am.getType().isPersistent()) {
/* 350 */         am.setDespawnedSync();
/* 351 */         MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 352 */         am.getEntity().remove();
/* 353 */         amount++;
/*     */       } 
/*     */     } 
/* 356 */     return amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntityDespawned(UUID uniqueId) {
/* 364 */     ((MobRegistry)this.mobRegistry.get()).getActiveMob(uniqueId).ifPresent(mob -> mob.setDespawned());
/*     */   }
/*     */   
/*     */   public void ScanWorld() {
/* 368 */     Schedulers.async().run(() -> {
/*     */           for (AbstractWorld w : MythicMobs.inst().server().getWorlds()) {
/*     */             for (AbstractEntity e : MythicMobs.inst().getEntityManager().getLivingEntities(w)) {
/*     */               if (!isActiveMob(e)) {
/*     */                 registerActiveMob(e);
/*     */                 continue;
/*     */               } 
/*     */               verifyMythicMobInstance(e);
/*     */             } 
/*     */           } 
/*     */           for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/*     */             if (am.getEntity().isDead()) {
/*     */               am.setDespawned();
/*     */               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)new QueuedMobCleanup(am), 200L);
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public ActiveMob registerActiveMob(ActiveMob am) {
/* 389 */     ((MobRegistry)this.mobRegistry.get()).put(am.getUniqueId(), am);
/* 390 */     return am;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActiveMob registerActiveMob(AbstractEntity l, MythicMob mm, int level) {
/* 396 */     if (isActiveMob(l)) {
/* 397 */       return getMythicMobInstance(l);
/*     */     }
/* 399 */     ActiveMob am = new ActiveMob(l.getUniqueId(), l, mm, level);
/* 400 */     ((MobRegistry)this.mobRegistry.get()).put(l.getUniqueId(), am);
/*     */     
/* 402 */     mm.applyMobVolatileOptions(am);
/*     */ 
/*     */     
/* 405 */     new TriggeredSkill(SkillTrigger.SPAWN, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/*     */     
/* 407 */     return am;
/*     */   }
/*     */   
/*     */   public ActiveMob registerActiveMob(AbstractEntity l) {
/* 411 */     if (this.mmVoidList.asMap().containsKey(l.getUniqueId())) {
/* 412 */       return null;
/*     */     }
/* 414 */     if (isActiveMob(l)) {
/* 415 */       return getMythicMobInstance(l);
/*     */     }
/*     */     
/* 418 */     this.mmVoidList.put(l.getUniqueId(), Boolean.valueOf(true));
/* 419 */     return null;
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
/*     */   public void unregisterActiveMob(UUID u) {
/* 448 */     ((MobRegistry)this.mobRegistry.get()).removeMob(u);
/* 449 */     this.activeMobsInCombat.remove(u);
/*     */   }
/*     */   
/*     */   public void unregisterActiveMob(ActiveMob am) {
/* 453 */     am.unregister();
/* 454 */     ((MobRegistry)this.mobRegistry.get()).removeMob(am.getEntity().getUniqueId());
/* 455 */     this.activeMobsInCombat.remove(am.getEntity().getUniqueId());
/*     */   }
/*     */   
/*     */   public ActiveMob getMythicMobInstance(Entity target) {
/* 459 */     return getMythicMobInstance(BukkitAdapter.adapt(target));
/*     */   }
/*     */   
/*     */   public ActiveMob getMythicMobInstance(AbstractEntity target) {
/* 463 */     ActiveMob am = ((MobRegistry)this.mobRegistry.get()).get(target.getUniqueId());
/*     */     
/* 465 */     if (am != null) {
/* 466 */       resetActiveMobEntity(am, target);
/* 467 */       return am;
/*     */     } 
/* 469 */     return registerActiveMob(target);
/*     */   }
/*     */   
/*     */   public void verifyMythicMobInstance(AbstractEntity e) {
/* 473 */     ActiveMob am = ((MobRegistry)this.mobRegistry.get()).get(e.getUniqueId());
/*     */     
/* 475 */     if (am != null) {
/* 476 */       resetActiveMobEntity(am, e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void resetActiveMobEntity(ActiveMob am, AbstractEntity e) {
/* 481 */     if (!am.getEntity().isValid() && e.isValid()) {
/* 482 */       am.setEntity(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void cleanActiveMobs() {
/* 487 */     for (ActiveMob am : ((MobRegistry)this.mobRegistry.get()).values()) {
/* 488 */       if (am.getEntity().isDead()) {
/* 489 */         Schedulers.async().runLater((Runnable)new QueuedMobCleanup(am), 20L);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean spawnflag = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean mountflag = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActiveMob spawnMob(String MobName, Location loc) {
/* 516 */     return spawnMob(MobName, BukkitAdapter.adapt(loc), 1);
/*     */   }
/*     */   
/*     */   public ActiveMob spawnMob(String MobName, Location loc, int level) {
/* 520 */     return spawnMob(MobName, BukkitAdapter.adapt(loc), level);
/*     */   }
/*     */   
/*     */   public ActiveMob spawnMob(String MobName, AbstractLocation loc) {
/* 524 */     return spawnMob(MobName, loc, 1);
/*     */   }
/*     */   
/*     */   public ActiveMob spawnMob(String MobName, AbstractLocation loc, int level) {
/* 528 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(MobName);
/*     */     
/* 530 */     if (mm != null) {
/* 531 */       return mm.spawn(loc, level);
/*     */     }
/* 533 */     MythicMobStack ms = MythicMobs.inst().getMobManager().getMythicMobStack(MobName);
/*     */     
/* 535 */     if (ms != null) {
/* 536 */       return ms.spawn(loc, level);
/*     */     }
/*     */     
/* 539 */     return null;
/*     */   }
/*     */   
/*     */   public void SetupMythicMobCompat(LivingEntity l, MythicMob mm) {
/* 543 */     if (spawnflag)
/*     */       return; 
/* 545 */     if (mm.getMythicEntity() != null) {
/* 546 */       l = (LivingEntity)mm.getMythicEntity().applyOptions((Entity)l);
/*     */     }
/*     */     
/* 549 */     MythicMobs.inst().getMobManager(); ActiveMob am = new ActiveMob(l.getUniqueId(), BukkitAdapter.adapt((Entity)l), mm, getMythicMobLevel(mm, BukkitAdapter.adapt((Entity)l)));
/*     */     
/* 551 */     ((MobRegistry)this.mobRegistry.get()).put(l.getUniqueId(), am);
/*     */     
/* 553 */     l = (LivingEntity)mm.applyMobOptions(am, am.getLevel());
/* 554 */     l = (LivingEntity)mm.applyMobVolatileOptions(am);
/*     */ 
/*     */     
/* 557 */     new TriggeredSkill(SkillTrigger.SPAWN, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/*     */   }
/*     */   
/*     */   public static AbstractLocation findSafeSpawnLocation(AbstractLocation b, int radiusXZ, int radiusY, int mob_height, boolean Ymod, boolean onSurface) {
/*     */     double y;
/* 562 */     Location base = BukkitAdapter.adapt(b);
/*     */     
/* 564 */     if (radiusXZ <= 0) radiusXZ = 1; 
/* 565 */     if (radiusY <= 0) radiusY = 1;
/*     */     
/* 567 */     double x = base.getX() - radiusXZ + MythicMobs.r.nextInt(radiusXZ * 2);
/* 568 */     double z = base.getZ() - radiusXZ + MythicMobs.r.nextInt(radiusXZ * 2);
/*     */     
/* 570 */     if (Ymod) {
/* 571 */       y = base.getY() - radiusY + MythicMobs.r.nextInt(radiusY * 2);
/*     */     } else {
/* 573 */       y = base.getY() + MythicMobs.r.nextInt(radiusY);
/*     */     } 
/*     */     
/* 576 */     Location loc = new Location(base.getWorld(), x, y, z);
/*     */     
/* 578 */     if (loc.getBlock().getType().isSolid()) {
/* 579 */       int j = 10;
/* 580 */       while (loc.getBlock().getType().isSolid()) {
/* 581 */         x = base.getX() - radiusXZ + MythicMobs.r.nextInt(radiusXZ * 2);
/* 582 */         z = base.getZ() - radiusXZ + MythicMobs.r.nextInt(radiusXZ * 2);
/*     */         
/* 584 */         if (Ymod) {
/* 585 */           y = base.getY() - radiusY + MythicMobs.r.nextInt(radiusY * 2);
/*     */         } else {
/* 587 */           y = base.getY() + MythicMobs.r.nextInt(radiusY);
/*     */         } 
/*     */         
/* 590 */         loc = new Location(base.getWorld(), x, y, z);
/*     */         
/* 592 */         j--;
/* 593 */         if (j == 0) {
/* 594 */           loc = new Location(base.getWorld(), base.getX(), base.getY() + 1.0D, base.getZ());
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 599 */     if (onSurface && !loc.getBlock().getRelative(BlockFace.DOWN).getType().isSolid()) {
/* 600 */       int highestY = loc.getWorld().getHighestBlockYAt(loc);
/* 601 */       if (highestY <= loc.getY()) {
/* 602 */         loc.setY((highestY + 1));
/*     */       } else {
/* 604 */         int j = 10;
/* 605 */         while (!loc.getBlock().getRelative(BlockFace.DOWN).getType().isSolid()) {
/* 606 */           if (j == 0) {
/* 607 */             loc = new Location(base.getWorld(), base.getX(), base.getY() + 1.0D, base.getZ());
/*     */             break;
/*     */           } 
/* 610 */           loc.setY(loc.getY() - 1.0D);
/* 611 */           j--;
/*     */         } 
/*     */       } 
/*     */     } 
/* 615 */     return BukkitAdapter.adapt(loc);
/*     */   }
/*     */   public static AbstractLocation findSafeSpawnLocation(AbstractLocation base, int radiusXZ, int radiusY, int mob_height, boolean yMod) {
/* 618 */     return findSafeSpawnLocation(base, radiusXZ, radiusY, mob_height, yMod, false);
/*     */   }
/*     */   public static AbstractLocation findSafeSpawnLocation(AbstractLocation base, int radiusXZ, int radiusY, int mob_height) {
/* 621 */     return findSafeSpawnLocation(base, radiusXZ, radiusY, mob_height, true, false);
/*     */   }
/*     */   public static AbstractLocation findSafeSpawnLocation(AbstractLocation base, int radius, int mob_height) {
/* 624 */     return findSafeSpawnLocation(base, radius, radius, mob_height, true, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ActiveMob> getMobsInCombat() {
/* 632 */     return this.activeMobsInCombat.values();
/*     */   }
/*     */   
/*     */   public boolean getInCombat(ActiveMob am) {
/* 636 */     return this.activeMobsInCombat.containsKey(am.getUniqueId());
/*     */   }
/*     */   
/*     */   public void setInCombat(ActiveMob am, boolean b) {
/* 640 */     if (b) {
/* 641 */       this.activeMobsInCombat.put(am.getUniqueId(), am);
/*     */     } else {
/* 643 */       this.activeMobsInCombat.remove(am.getUniqueId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveCachedActiveMobs() {
/* 652 */     MythicMobs.debug(1, "Saving all active Mythic Mobs...");
/*     */     
/* 654 */     this.mobRegistry.save();
/*     */     
/* 656 */     MythicMobs.debug(1, "All active mobs have been saved!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<LivingEntity> getAllMythicEntities() {
/* 664 */     List<LivingEntity> list = new ArrayList<>();
/*     */     
/* 666 */     for (World w : Bukkit.getWorlds()) {
/* 667 */       for (LivingEntity e : w.getLivingEntities()) {
/* 668 */         if (((MobRegistry)this.mobRegistry.get()).isActiveMob(e.getUniqueId())) {
/* 669 */           list.add(e);
/*     */         }
/*     */       } 
/*     */     } 
/* 673 */     return list;
/*     */   }
/*     */   public MythicMob getMythicMob(String s) {
/* 676 */     if (s == null) return null; 
/* 677 */     if (this.mmList.containsKey(s)) {
/* 678 */       return this.mmList.get(s);
/*     */     }
/* 680 */     return null;
/*     */   }
/*     */   public MythicMobStack getMythicMobStack(String s) {
/* 683 */     for (MythicMobStack ML : this.listMobStacks) {
/* 684 */       if (ML.getName().equals(s)) {
/* 685 */         return ML;
/*     */       }
/*     */     } 
/* 688 */     return null;
/*     */   }
/*     */   
/*     */   public static int getMythicMobLevel(MythicMob mm, AbstractEntity l) {
/* 692 */     Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
/* 693 */     Objective o = sb.getObjective("MythicMobLevel");
/*     */     
/* 695 */     if (o != null) {
/* 696 */       int si = o.getScore(l.getUniqueId().toString()).getScore();
/* 697 */       if (si > 0) return si;
/*     */     
/*     */     } 
/* 700 */     int level = 1;
/*     */     
/* 702 */     if (mm.lvlModHealth > 0.0D) {
/* 703 */       double health = l.getMaxHealth() - mm.getHealth();
/*     */       
/* 705 */       level += (int)(health / mm.lvlModHealth);
/*     */     } else {
/* 707 */       return 1;
/*     */     } 
/* 709 */     return level;
/*     */   }
/*     */   
/*     */   public static int getMythicMobLevel(LivingEntity l) {
/* 713 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/*     */     
/* 715 */     if (am == null) {
/* 716 */       return 1;
/*     */     }
/* 718 */     return am.getLevel();
/*     */   }
/*     */   
/*     */   public static void setMetaData(AbstractEntity abstractEntity, String s, String key) {
/* 722 */     BukkitAdapter.adapt(abstractEntity).setMetadata(key, (MetadataValue)new FixedMetadataValue((Plugin)MythicMobs.inst(), s));
/*     */   }
/*     */   public static void showHealth(ActiveMob am) {
/* 725 */     if (!(am.getType()).optionShowHealthInChat) {
/*     */       return;
/*     */     }
/*     */     
/* 729 */     int per = (int)(am.getEntity().getHealth() / am.getEntity().getMaxHealth() * 10.0D) + 1;
/* 730 */     if (per >= 10) {
/*     */       return;
/*     */     }
/* 733 */     if (!LegacySkillHandler.hasUsedSkill("percentage" + per, am.getEntity())) {
/* 734 */       MythicMobs.inst().getMobManager(); setMetaData(am.getEntity(), "percentage" + per, "percentage" + per);
/*     */       
/* 736 */       String string = ConfigManager.ShowHealthFormat;
/*     */       
/* 738 */       string = SkillString.parseMobVariables(string, (SkillCaster)am, null, null);
/*     */       
/* 740 */       string = string.replace("<mob.hp>", "" + (int)am.getEntity().getHealth());
/* 741 */       string = string.replace("<mob.mhp>", "" + (int)am.getEntity().getMaxHealth());
/* 742 */       string = string.replace("$percent", per + "0");
/* 743 */       string = string.replace("$mobname", am.getEntity().getCustomName() + "");
/*     */       
/* 745 */       string = ChatColor.translateAlternateColorCodes('&', string);
/*     */       
/* 747 */       for (Player p : SkillHelper.getPlayersInRadius((LivingEntity)am.getEntity().getBukkitEntity(), ConfigManager.ShowHealthRadius)) {
/* 748 */         p.sendMessage(string);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MythicMob determineMobType(AbstractEntity l) {
/* 755 */     Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
/*     */     
/* 757 */     Objective o = sb.getObjective("MythicMobType");
/*     */     
/* 759 */     if (o == null) {
/* 760 */       o = sb.registerNewObjective("MythicMobType", "dummy");
/*     */     }
/* 762 */     int hashCode = o.getScore(l.getUniqueId().toString()).getScore();
/*     */     
/* 764 */     if (this.mmHashcodeLookup.containsKey(Integer.valueOf(hashCode))) {
/* 765 */       return this.mmHashcodeLookup.get(Integer.valueOf(hashCode));
/*     */     }
/*     */     
/* 768 */     List<MetadataValue> list = l.getBukkitEntity().getMetadata("mobname");
/*     */     
/* 770 */     MythicMob mm = null;
/*     */     
/* 772 */     for (MetadataValue mv : list) {
/* 773 */       MythicMobs.debug(5, "-- Analyzing Meta Value: " + mv + "");
/* 774 */       mm = this.mmList.get(mv);
/*     */       
/* 776 */       if (mm != null) return mm;
/*     */     
/*     */     } 
/* 779 */     if (l.getCustomName() == null) return null;
/*     */     
/* 781 */     MythicMobs.debug(3, "-- Attempting to match untracked mob by display name: " + l.getCustomName());
/*     */     
/* 783 */     mm = this.mmDisplayLookup.get(l.getCustomName());
/* 784 */     if (mm != null) return mm;
/*     */ 
/*     */ 
/*     */     
/* 788 */     for (MythicMob MM : this.mmList.values()) {
/* 789 */       if (MM.getDisplayName() == null)
/*     */         continue; 
/* 791 */       String search = MM.getDisplayName().toString();
/* 792 */       search = search.replace("[", "\\[");
/* 793 */       search = search.replace("]", "\\]");
/* 794 */       search = search.replace("?", "\\?");
/* 795 */       search = search.replace("$level", "[0-9]*");
/* 796 */       search = search.replace("<mob.level>", "[0-9]*");
/* 797 */       search = ChatColor.translateAlternateColorCodes('&', search);
/*     */       
/* 799 */       MythicMobs.debug(4, "---- Matching mob to name " + search);
/*     */       
/*     */       try {
/* 802 */         if (l.getCustomName().toString().matches(search) && MM.getHealth() <= l.getMaxHealth()) {
/* 803 */           MythicMobs.debug(3, "---- Matched mob to name " + search + " with health check " + MM.getHealth());
/* 804 */           l.setHealth(l.getMaxHealth());
/* 805 */           return MM;
/*     */         } 
/* 807 */       } catch (Exception e) {
/* 808 */         e.printStackTrace();
/* 809 */         MythicMobs.debug(2, "ERROR");
/*     */       } 
/*     */     } 
/* 812 */     return null;
/*     */   }
/*     */   public MythicMob getMythicMobByDisplayCompat(AbstractEntity l) {
/* 815 */     if (l.getCustomName() == null) return null; 
/* 816 */     MythicMobs.debug(3, "-- Attempting to match mob for compatibility: " + l.getCustomName().toString());
/*     */     
/* 818 */     MythicMob mm = this.mmDisplayLookup.get(l.getCustomName());
/* 819 */     if (mm != null) return mm;
/*     */     
/* 821 */     mm = determineMobType(l);
/* 822 */     if (mm != null) return mm;
/*     */ 
/*     */ 
/*     */     
/* 826 */     for (MythicMob MM : this.mmList.values()) {
/* 827 */       if (MM.getDisplayName() == null)
/*     */         continue; 
/* 829 */       String search = MM.getDisplayName().toString();
/* 830 */       search = search.replace("?", "\\?");
/* 831 */       search = search.replace("$level", "[0-9]*");
/* 832 */       search = ChatColor.translateAlternateColorCodes('&', search);
/*     */       
/* 834 */       MythicMobs.debug(4, "---- Matching mob to name " + search);
/*     */       
/*     */       try {
/* 837 */         String name = ChatColor.translateAlternateColorCodes('&', l.getCustomName().toString());
/* 838 */         if (name.matches(search)) {
/* 839 */           MythicMobs.debug(3, "---- Matched mob to name " + search);
/* 840 */           l.setHealth(l.getMaxHealth());
/* 841 */           return MM;
/*     */         } 
/* 843 */       } catch (Exception e) {
/* 844 */         e.printStackTrace();
/* 845 */         MythicMobs.debug(2, "ERROR");
/*     */       } 
/*     */     } 
/* 848 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String convertMobTypeAliases(String s) {
/* 853 */     s = s.toLowerCase();
/*     */     
/* 855 */     s = s.replace("zombievillager", "ZOMBIE_VILLAGER");
/* 856 */     s = s.replace("villagezombie", "ZOMBIE_VILLAGER");
/*     */     
/* 858 */     s = s.replace("babydrowned", "BABY_DROWNED");
/*     */     
/* 860 */     s = s.replace("babyvillagezombie", "BABY_ZOMBIE_VILLAGER");
/*     */     
/* 862 */     s = s.replace("babypigzombie", "BABY_PIG_ZOMBIE");
/* 863 */     s = s.replace("babyzombiepig", "BABY_PIG_ZOMBIE");
/* 864 */     s = s.replace("babyzombiepigman", "BABY_PIG_ZOMBIE");
/*     */     
/* 866 */     s = s.replace("babypigzombievillager", "BABY_PIG_ZOMBIE_VILLAGER");
/* 867 */     s = s.replace("babyzombiepigvillager", "BABY_PIG_ZOMBIE_VILLAGER");
/* 868 */     s = s.replace("babyzombiepigmanvillager", "BABY_PIG_ZOMBIE_VILLAGER");
/*     */     
/* 870 */     s = s.replace("babyzombie", "BABY_ZOMBIE");
/*     */     
/* 872 */     s = s.replace("cavespider", "CAVE_SPIDER");
/*     */     
/* 874 */     s = s.replace("enderdragon", "ENDER_DRAGON");
/*     */     
/* 876 */     s = s.replace("irongolem", "IRON_GOLEM");
/*     */     
/* 878 */     s = s.replace("lavaslime", "MAGMA_CUBE");
/* 879 */     s = s.replace("magmacube", "MAGMA_CUBE");
/*     */     
/* 881 */     s = s.replace("mooshroom", "MUSHROOM_COW");
/* 882 */     s = s.replace("mushroomcow", "MUSHROOM_COW");
/*     */     
/* 884 */     s = s.replace("pigzombie", "PIG_ZOMBIE");
/* 885 */     s = s.replace("zombiepig", "PIG_ZOMBIE");
/* 886 */     s = s.replace("zombiepigman", "PIG_ZOMBIE");
/*     */     
/* 888 */     s = s.replace("pigzombievillager", "PIG_ZOMBIE_VILLAGER");
/* 889 */     s = s.replace("zombiepigvillager", "PIG_ZOMBIE_VILLAGER");
/* 890 */     s = s.replace("zombiepigmanvillager", "PIG_ZOMBIE_VILLAGER");
/*     */     
/* 892 */     s = s.replace("tnt", "PRIMED_TNT");
/*     */     
/* 894 */     s = s.replace("tropicalfish", "TROPICAL_FISH");
/*     */     
/* 896 */     s = s.replace("skeletalhorse", "SKELETON_HORSE");
/* 897 */     s = s.replace("skeletonhorse", "SKELETON_HORSE");
/*     */     
/* 899 */     s = s.replace("zombiehorse", "ZOMBIE_HORSE");
/*     */     
/* 901 */     if (s.equals("cat")) {
/* 902 */       s = s.replace("cat", "OCELOT");
/*     */     }
/*     */     
/* 905 */     s = s.replace("witherskeleton", "WITHER_SKELETON");
/*     */     
/* 907 */     return s;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\MobManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */