/*     */ package lumine.xikage.mythicmobs.spawning.random;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnGenerator;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnPoint;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawner;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnerManager;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.SpawnPointType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomPointGeneratorLand
/*     */   extends RandomSpawnGenerator
/*     */ {
/*     */   public void generateSpawnPoints() {
/* 222 */     if (MythicMobs.inst().getEntityManager().getPlayers().size() <= 0)
/* 223 */       return;  MythicMobs.inst().getTimingsHandler().markRandomGeneratorsNew();
/* 224 */     long startTime = System.currentTimeMillis();
/*     */     
/* 226 */     List<AbstractPlayer> players = new ArrayList<>();
/* 227 */     players.addAll(RandomSpawnerManager.access$000(RandomSpawnerManager.this).getEntityManager().getPlayers());
/* 228 */     Collections.shuffle(players);
/*     */     
/* 230 */     int n = 0;
/*     */ 
/*     */     
/* 233 */     int spawnRadius = ConfigManager.getRSPlayerRadius();
/* 234 */     int spawnRadiusY = ConfigManager.getRSPlayerRadiusY();
/* 235 */     int minRadiusSquared = 256;
/*     */     
/* 237 */     Iterator<AbstractPlayer> itp = players.iterator();
/*     */     try {
/* 239 */       while (itp.hasNext()) {
/* 240 */         AbstractPlayer player = itp.next();
/*     */         
/* 242 */         if (player == null || player.isInCreativeMode()) {
/*     */           continue;
/*     */         }
/* 245 */         if (System.currentTimeMillis() - startTime > ConfigManager.getRSMaxGenerationTimeMillis())
/*     */           break; 
/* 247 */         int bucketLand = ConfigManager.getRSPointsPerSecondLand();
/* 248 */         int bucketSea = ConfigManager.getRSPointsPerSecondSea();
/* 249 */         int bucketAir = ConfigManager.getRSPointsPerSecondAir();
/* 250 */         int totalBucket = bucketLand + bucketSea + bucketAir;
/*     */         
/* 252 */         MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "Generating RandomSpawnPoints near Player {0}", new Object[] { player.getName() });
/* 253 */         while (totalBucket > 0 && 
/* 254 */           System.currentTimeMillis() - startTime <= ConfigManager.getRSMaxGenerationTimeMillis()) {
/*     */           RandomSpawnPoint point;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 263 */           Location location = BukkitAdapter.adapt(player.getLocation());
/* 264 */           Location spawnLocation = new Location(location.getWorld(), 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */           
/* 268 */           int nx = (int)(location.getX() - spawnRadius) + MythicMobs.r.nextInt(spawnRadius * 2);
/* 269 */           int nz = (int)(location.getZ() - spawnRadius) + MythicMobs.r.nextInt(spawnRadius * 2);
/* 270 */           int ny = (int)(location.getY() - spawnRadiusY) + MythicMobs.r.nextInt(spawnRadiusY * 2);
/*     */           
/* 272 */           spawnLocation.setX(nx);
/* 273 */           spawnLocation.setY(ny);
/* 274 */           spawnLocation.setZ(nz);
/*     */           
/* 276 */           if (!RandomSpawnerManager.this.isChunkLoaded(player.getWorld(), spawnLocation.getBlockX() >> 4, spawnLocation.getBlockZ() >> 4)) {
/* 277 */             MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "! Skipping player, area chunks not loaded.", new Object[0]);
/* 278 */             totalBucket--;
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 284 */           int mobsInChunk = 0;
/*     */           try {
/* 286 */             mobsInChunk = RandomSpawnerManager.this.getEntitiesInChunk(player.getWorld(), spawnLocation.getBlockX() >> 4, spawnLocation.getBlockZ() >> 4);
/* 287 */           } catch (Exception exception) {}
/*     */           
/* 289 */           if (mobsInChunk >= ConfigManager.getRSMaxMobsPerChunk()) {
/* 290 */             MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "! Too many entities in chunk, skipping", new Object[0]);
/* 291 */             totalBucket--;
/*     */             
/*     */             continue;
/*     */           } 
/* 295 */           if (spawnLocation.distanceSquared(location) < 256.0D) {
/* 296 */             MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "! Distance too far, skipping", new Object[0]);
/*     */             
/*     */             continue;
/*     */           } 
/* 300 */           if (spawnLocation.getBlock().getType() == Material.WATER) {
/* 301 */             MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "+ Found water. Adding Sea point", new Object[0]);
/*     */             
/* 303 */             AbstractLocation returnLocation = new AbstractLocation(player.getWorld(), nx + 0.5D, ny, nz + 0.5D);
/* 304 */             point = new RandomSpawnPoint(returnLocation, SpawnPointType.SEA);
/*     */           } else {
/* 306 */             MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "+ Adding Land point", new Object[0]);
/*     */             
/* 308 */             if (!spawnLocation.getBlock().getType().isSolid()) {
/* 309 */               boolean bool = false;
/*     */               
/* 311 */               for (int j = 0; j < spawnRadiusY * 2; j++) {
/* 312 */                 spawnLocation.setY(--ny);
/* 313 */                 if (spawnLocation.getBlock().getType() == Material.WATER) {
/*     */                   break;
/*     */                 }
/* 316 */                 if (spawnLocation.getBlock().getType().isSolid()) {
/* 317 */                   bool = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 321 */               if (!bool) {
/*     */                 continue;
/*     */               }
/*     */             } 
/*     */             
/* 326 */             boolean goodd = true;
/* 327 */             for (int k = 1; k < 3; k++) {
/* 328 */               spawnLocation.setY(spawnLocation.getY() + 1.0D);
/* 329 */               if (spawnLocation.getBlock().getType().isSolid()) {
/* 330 */                 MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "! {0} is NOT pathable, skipping", new Object[] { spawnLocation.getBlock().getType().toString() });
/* 331 */                 goodd = false;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 335 */             if (!goodd) {
/* 336 */               MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "! Couldn't find land position this time", new Object[0]);
/*     */               
/*     */               continue;
/*     */             } 
/* 340 */             AbstractLocation returnLocation = new AbstractLocation(player.getWorld(), nx + 0.5D, (ny + 1), nz + 0.5D);
/* 341 */             point = new RandomSpawnPoint(returnLocation, SpawnPointType.LAND);
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 352 */           if (point != null) {
/* 353 */             Iterator<RandomSpawner> it = RandomSpawnerManager.this.listRandomSpawningD.iterator();
/*     */             
/* 355 */             boolean good = true;
/* 356 */             while (it.hasNext()) {
/* 357 */               RandomSpawner RS = it.next();
/* 358 */               if (RS.checkSpawn(point)) {
/* 359 */                 good = false;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 363 */             if (good) {
/* 364 */               if (point.getPointType() == SpawnPointType.LAND && bucketLand > 0) {
/* 365 */                 RandomSpawnerManager.this.landSpawnPoints.add(point);
/* 366 */                 bucketLand--;
/* 367 */                 n++;
/* 368 */               } else if (point.getPointType() == SpawnPointType.SEA && bucketSea > 0) {
/* 369 */                 RandomSpawnerManager.this.seaSpawnPoints.add(point);
/* 370 */                 bucketSea--;
/* 371 */                 n++;
/*     */               } 
/* 373 */               totalBucket--;
/*     */             } else {
/*     */               
/* 376 */               MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "! Skipped spawn point due to DENY action", new Object[0]);
/*     */             } 
/* 378 */             MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "++ Generated Random Point at {0}", new Object[] { point.getLocation().toString() });
/*     */           } 
/*     */         } 
/*     */         
/* 382 */         MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "[i] Generated Points: {0}", new Object[] { Integer.valueOf(n) });
/* 383 */         MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "[i] Land Bucket: {0}", new Object[] { Integer.valueOf(bucketLand) });
/* 384 */         MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "[i] Sea Bucket: {0}", new Object[] { Integer.valueOf(bucketSea) });
/* 385 */         MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "[i] Time taken: {0}", new Object[] { Long.valueOf(System.currentTimeMillis() - startTime) });
/*     */       } 
/* 387 */     } catch (Exception exception) {}
/* 388 */     MythicMobs.inst().getTimingsHandler().markRandomGeneratorsComplete();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomSpawnPoint findPointNearPlayer(AbstractPlayer player, boolean searchLand) {
/* 398 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\random\RandomSpawnerManager$RandomPointGeneratorLand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */