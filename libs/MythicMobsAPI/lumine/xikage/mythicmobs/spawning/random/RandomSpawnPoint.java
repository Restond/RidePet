/*     */ package lumine.xikage.mythicmobs.spawning.random;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.SpawnPointType;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class RandomSpawnPoint {
/*     */   protected AbstractEntity entity;
/*     */   protected AbstractLocation location;
/*     */   
/*     */   public RandomSpawnPoint(AbstractLocation location) {
/*     */     SpawnPointType pt;
/*  19 */     this.levelMod = 1.0F;
/*     */ 
/*     */     
/*  22 */     this.entity = null;
/*  23 */     this.location = location;
/*  24 */     this.spawnReason = CreatureSpawnEvent.SpawnReason.CUSTOM;
/*     */ 
/*     */ 
/*     */     
/*  28 */     Location l = BukkitAdapter.adapt(location);
/*     */     
/*  30 */     if (MythicMobs.inst().getMinecraftVersion() < 14) {
/*  31 */       if (!l.getBlock().getType().isOccluding()) {
/*  32 */         if (l.getBlock().isLiquid()) {
/*  33 */           if (l.getBlock().getType().equals(Material.LAVA)) {
/*  34 */             pt = SpawnPointType.LAVA;
/*     */           } else {
/*  36 */             pt = SpawnPointType.SEA;
/*     */           } 
/*  38 */         } else if (l.subtract(0.0D, 1.0D, 0.0D).getBlock().getType().isOccluding()) {
/*  39 */           pt = SpawnPointType.LAND;
/*     */         } else {
/*  41 */           pt = SpawnPointType.AIR;
/*     */         } 
/*     */       } else {
/*  44 */         pt = SpawnPointType.GROUND;
/*     */       } 
/*     */     } else {
/*  47 */       pt = SpawnPointType.GROUND;
/*     */     } 
/*     */     
/*  50 */     this.pointType = pt;
/*     */   } protected CreatureSpawnEvent.SpawnReason spawnReason; protected SpawnPointType pointType; protected float levelMod;
/*     */   public RandomSpawnPoint(AbstractLocation location, SpawnPointType pointType) {
/*     */     this.levelMod = 1.0F;
/*  54 */     this.entity = null;
/*  55 */     this.location = location;
/*  56 */     this.spawnReason = CreatureSpawnEvent.SpawnReason.NATURAL;
/*  57 */     this.pointType = pointType;
/*     */   }
/*     */   
/*     */   public RandomSpawnPoint(AbstractEntity entity, AbstractLocation location, CreatureSpawnEvent.SpawnReason spawnReason) {
/*  61 */     this(entity, location, spawnReason, null);
/*     */     
/*     */     SpawnPointType pt;
/*     */     
/*  65 */     Location loc = BukkitAdapter.adapt(location);
/*     */     
/*  67 */     if (MythicMobs.inst().getMinecraftVersion() < 14) {
/*  68 */       if (!loc.getBlock().getType().isOccluding()) {
/*  69 */         if (loc.getBlock().isLiquid()) {
/*  70 */           if (loc.getBlock().getType().equals(Material.LAVA)) {
/*  71 */             pt = SpawnPointType.LAVA;
/*     */           } else {
/*  73 */             pt = SpawnPointType.SEA;
/*     */           } 
/*  75 */         } else if (loc.subtract(0.0D, 1.0D, 0.0D).getBlock().getType().isOccluding()) {
/*  76 */           pt = SpawnPointType.LAND;
/*     */         } else {
/*  78 */           pt = SpawnPointType.AIR;
/*     */         } 
/*     */       } else {
/*  81 */         pt = SpawnPointType.GROUND;
/*     */       } 
/*     */     } else {
/*  84 */       pt = SpawnPointType.GROUND;
/*     */     } 
/*     */     
/*  87 */     this.pointType = pt;
/*     */   } public RandomSpawnPoint(AbstractEntity entity, AbstractLocation location, CreatureSpawnEvent.SpawnReason spawnReason, SpawnPointType pt) {
/*     */     this.levelMod = 1.0F;
/*  90 */     this.entity = entity;
/*  91 */     this.location = location;
/*  92 */     this.spawnReason = spawnReason;
/*  93 */     this.pointType = pt;
/*     */   }
/*     */   public AbstractEntity getEntity() {
/*  96 */     return this.entity;
/*     */   }
/*     */   public AbstractLocation getLocation() {
/*  99 */     return this.location;
/*     */   }
/*     */   public CreatureSpawnEvent.SpawnReason getReason() {
/* 102 */     return this.spawnReason;
/*     */   }
/*     */   public Biome getBiome() {
/* 105 */     return BukkitAdapter.adapt(this.location).getWorld().getBiome(this.location.getBlockX(), this.location.getBlockZ());
/*     */   }
/*     */   public SpawnPointType getPointType() {
/* 108 */     return this.pointType;
/*     */   }
/*     */   public float getLevelMod() {
/* 111 */     return this.levelMod;
/*     */   }
/*     */   public void setLevelMod(float l) {
/* 114 */     this.levelMod = l;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\random\RandomSpawnPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */