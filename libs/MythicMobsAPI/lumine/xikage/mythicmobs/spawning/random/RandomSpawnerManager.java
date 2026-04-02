/*     */ package lumine.xikage.mythicmobs.spawning.random;
/*     */ 
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.IOHandler;
/*     */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnPoint;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawner;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnerAction;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomSpawnerManager
/*     */   implements Terminable
/*     */ {
/*     */   private final MythicMobs core;
/*     */   private List<File> spawnFiles;
/*     */   private IOLoader<MythicMobs> defaultSpawns;
/*     */   private List<IOLoader<MythicMobs>> spawnLoaders;
/*  39 */   public List<RandomSpawner> listRandomSpawningAddLand = new ArrayList<>();
/*  40 */   public List<RandomSpawner> listRandomSpawningAddSea = new ArrayList<>();
/*  41 */   public List<RandomSpawner> listRandomSpawningAddAir = new ArrayList<>();
/*     */   
/*  43 */   public List<RandomSpawner> listRandomSpawningR = new ArrayList<>();
/*  44 */   public List<RandomSpawner> listRandomSpawningD = new ArrayList<>();
/*     */   
/*  46 */   public Set<RandomSpawnPoint> landSpawnPoints = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*  47 */   public Set<RandomSpawnPoint> airSpawnPoints = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*  48 */   public Set<RandomSpawnPoint> seaSpawnPoints = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*  49 */   public Set<RandomSpawnPoint> lavaSpawnPoints = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*  50 */   public Set<RandomSpawnPoint> groundSpawnPoints = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */   
/*  52 */   public Set<ActiveMob> randomlySpawnedMobs = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */   
/*     */   RandomPointGeneratorLand spawnPointGenerator;
/*     */   
/*     */   public RandomSpawnerManager(MythicMobs core) {
/*  57 */     this.core = core;
/*     */   }
/*     */   
/*     */   public void loadRandomSpawns() {
/*  61 */     this.defaultSpawns = new IOLoader((JavaPlugin)MythicMobs.inst(), "ExampleRandomSpawns.yml", "RandomSpawns");
/*  62 */     this.spawnFiles = IOHandler.getAllFiles(this.defaultSpawns.getFile().getParent());
/*     */     
/*  64 */     this.spawnLoaders = IOHandler.getSaveLoad((JavaPlugin)MythicMobs.inst(), this.spawnFiles, "RandomSpawns");
/*     */     
/*  66 */     this.listRandomSpawningAddLand.clear();
/*  67 */     this.listRandomSpawningAddSea.clear();
/*  68 */     this.listRandomSpawningAddAir.clear();
/*  69 */     this.listRandomSpawningR.clear();
/*  70 */     this.listRandomSpawningD.clear();
/*     */     
/*  72 */     for (IOLoader<MythicMobs> sl : this.spawnLoaders) {
/*  73 */       for (String name : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
/*  74 */         MythicConfig mc = new MythicConfig(name, sl.getCustomConfig());
/*  75 */         String file = sl.getFile().getName();
/*     */         
/*  77 */         RandomSpawner rs = new RandomSpawner(file, name, mc);
/*     */         
/*  79 */         if (rs.getAction() == RandomSpawnerAction.ADD) {
/*  80 */           switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$spawning$random$SpawnPointType[rs.getPositionType().ordinal()]) {
/*     */             case 1:
/*  82 */               this.listRandomSpawningAddAir.add(rs);
/*     */               continue;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             case 3:
/*  89 */               this.listRandomSpawningAddLand.add(rs);
/*     */               continue;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             case 5:
/*  96 */               this.listRandomSpawningAddSea.add(rs); continue;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 100 */         if (rs.getAction() == RandomSpawnerAction.DENY) {
/* 101 */           this.listRandomSpawningD.add(rs); continue;
/*     */         } 
/* 103 */         this.listRandomSpawningR.add(rs);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 108 */     MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Sorting ADD Random Spawners...", new Object[0]);
/* 109 */     this.listRandomSpawningAddLand = sortSpawners(this.listRandomSpawningAddLand);
/* 110 */     this.listRandomSpawningAddSea = sortSpawners(this.listRandomSpawningAddSea);
/* 111 */     this.listRandomSpawningAddAir = sortSpawners(this.listRandomSpawningAddAir);
/*     */     
/* 113 */     MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Sorting REPLACE Random Spawners...", new Object[0]);
/* 114 */     this.listRandomSpawningR = sortSpawners(this.listRandomSpawningR);
/*     */     
/* 116 */     if (ConfigManager.generateRSPoints()) {
/* 117 */       if (this.spawnPointGenerator == null && ConfigManager.getRSPointsPerSecondLand() > 0) {
/* 118 */         MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Starting up SpawnPointGenerator!", new Object[0]);
/* 119 */         this.spawnPointGenerator = new RandomPointGeneratorLand(this);
/*     */       }
/*     */     
/* 122 */     } else if (this.spawnPointGenerator != null) {
/* 123 */       this.spawnPointGenerator.stop();
/* 124 */       this.spawnPointGenerator = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumberOfSpawners() {
/* 130 */     return this.listRandomSpawningAddLand.size() + this.listRandomSpawningAddSea.size() + this.listRandomSpawningAddAir.size() + this.listRandomSpawningR.size() + this.listRandomSpawningD.size();
/*     */   }
/*     */   
/*     */   public AbstractEntity handleSpawnEvent(RandomSpawnPoint rsp) {
/* 134 */     if (rsp.getEntity() == null) return null; 
/* 135 */     if (this.listRandomSpawningR.size() == 0) return null;
/*     */     
/* 137 */     if (!ConfigManager.generateRSPoints()) {
/* 138 */       this.landSpawnPoints.add(rsp);
/*     */     }
/*     */     
/* 141 */     for (RandomSpawner RS : this.listRandomSpawningR) {
/* 142 */       if (!RS.checkSpawn(rsp) || 
/* 143 */         !RS.isValid())
/*     */         continue; 
/* 145 */       ActiveMob mob = RS.spawn(rsp);
/* 146 */       return (mob != null) ? mob.getEntity() : null;
/*     */     } 
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public void reload() {
/* 152 */     if (this.spawnPointGenerator != null) this.spawnPointGenerator.stop(); 
/* 153 */     if (ConfigManager.generateRSPoints() && 
/* 154 */       ConfigManager.getRSPointsPerSecondLand() > 0) {
/* 155 */       this.spawnPointGenerator = new RandomPointGeneratorLand(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 161 */     if (this.spawnPointGenerator != null) this.spawnPointGenerator.stop(); 
/* 162 */     return true;
/*     */   }
/*     */   
/*     */   public void spawnMobs() {
/* 166 */     this.landSpawnPoints.forEach(rsp -> doSpawns(rsp, this.listRandomSpawningAddLand.iterator()));
/*     */ 
/*     */     
/* 169 */     this.landSpawnPoints.clear();
/*     */     
/* 171 */     this.seaSpawnPoints.forEach(rsp -> doSpawns(rsp, this.listRandomSpawningAddSea.iterator()));
/*     */ 
/*     */     
/* 174 */     this.seaSpawnPoints.clear();
/*     */   }
/*     */   public void doSpawns(RandomSpawnPoint rsp, Iterator<RandomSpawner> it) {
/* 177 */     if (rsp == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 182 */     while (it.hasNext()) {
/* 183 */       RandomSpawner RS = it.next();
/* 184 */       if (!RS.checkSpawn(rsp)) {
/*     */         continue;
/*     */       }
/*     */       
/* 188 */       if (!RS.isValid()) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 193 */       RS.spawn(rsp);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMobsNearPlayer(AbstractPlayer p) {
/* 199 */     int i = 0;
/* 200 */     int spawnRadius = (int)Math.pow(ConfigManager.getRSPlayerRadius(), 2.0D);
/*     */     
/* 202 */     for (AbstractEntity e : MythicMobs.inst().getEntityManager().getLivingEntities(p.getLocation().getWorld())) {
/* 203 */       if (e.getLocation().distanceSquared(p.getLocation()) <= spawnRadius) {
/* 204 */         i++;
/*     */       }
/*     */     } 
/* 207 */     return i;
/*     */   }
/*     */   
/*     */   public boolean isChunkLoaded(AbstractWorld world, int x, int z) {
/* 211 */     return MythicMobs.inst().getWorldManager().isChunkLoaded(world, x, z);
/*     */   }
/*     */   
/*     */   public int getEntitiesInChunk(AbstractWorld world, int x, int z) {
/* 215 */     return MythicMobs.inst().getWorldManager().getEntitiesInChunk(world, x, z);
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
/*     */   public Set<RandomSpawnPoint> getLandSpawnPoints() {
/* 403 */     return this.landSpawnPoints;
/*     */   }
/*     */   
/*     */   public Set<RandomSpawnPoint> getSeaSpawnPoints() {
/* 407 */     return this.seaSpawnPoints;
/*     */   }
/*     */   
/*     */   private List<RandomSpawner> sortSpawners(List<RandomSpawner> list) {
/*     */     int i;
/* 412 */     for (i = 0; i < list.size(); i++) {
/* 413 */       for (int q = 1; q < list.size() - i; q++) {
/* 414 */         RandomSpawner o = list.get(q - 1);
/* 415 */         RandomSpawner n = list.get(q);
/*     */         
/* 417 */         if (o.priority < n.priority) {
/* 418 */           list.set(q - 1, n);
/* 419 */           list.set(q, o);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 425 */     for (i = 0; i < list.size(); i++) {
/* 426 */       MythicMobs.debug(2, "* " + ((RandomSpawner)list.get(i)).getPriority() + " = " + ((RandomSpawner)list.get(i)).name);
/*     */     }
/* 428 */     return list;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\random\RandomSpawnerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */