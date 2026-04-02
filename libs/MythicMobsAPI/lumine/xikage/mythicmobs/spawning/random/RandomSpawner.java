/*     */ package lumine.xikage.mythicmobs.spawning.random;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.WorldScaling;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnPoint;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnerAction;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.SpawnPointType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ 
/*     */ public class RandomSpawner
/*     */ {
/*  31 */   public static Set<RandomSpawnPoint> spawnPoints = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */   
/*     */   public String name;
/*  34 */   public List<MythicMob> mobTypes = new ArrayList<>();
/*     */   RandomSpawnerAction action;
/*     */   public int priority;
/*     */   public int level;
/*     */   public float chance;
/*  39 */   public HashSet<Biome> biomes = new HashSet<>();
/*  40 */   public HashSet<AbstractWorld> worlds = new HashSet<>();
/*  41 */   public HashSet<CreatureSpawnEvent.SpawnReason> spawnReasons = new HashSet<>();
/*     */   public List<SkillCondition> conditions;
/*  43 */   private SpawnPointType positionType = SpawnPointType.LAND; public SpawnPointType getPositionType() { return this.positionType; }
/*     */   
/*     */   private boolean hasConditions = false; private boolean useWorldScaling = true;
/*     */   
/*     */   public RandomSpawner(String file, String name, MythicConfig mc) {
/*  48 */     this.name = name;
/*     */     
/*  50 */     String strAction = mc.getString("SpawnMethod", "ADD");
/*  51 */     strAction = mc.getString("Action", strAction);
/*     */     
/*  53 */     this.action = RandomSpawnerAction.valueOf(strAction.toUpperCase());
/*     */     
/*  55 */     String strMobName = mc.getString("Mobname", "");
/*  56 */     strMobName = mc.getString("MobName", strMobName);
/*  57 */     strMobName = mc.getString("MobType", strMobName);
/*  58 */     strMobName = mc.getString("Type", strMobName);
/*  59 */     strMobName = mc.getString("Types", strMobName);
/*     */     
/*  61 */     if (!strMobName.equalsIgnoreCase("GROUP")) {
/*     */ 
/*     */       
/*  64 */       String[] types = strMobName.split(",");
/*     */       
/*  66 */       for (String s : types) {
/*  67 */         MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(s);
/*     */         
/*  69 */         if (mm != null) {
/*  70 */           this.mobTypes.add(mm);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     this.level = mc.getInteger("Level", 1);
/*  76 */     this.chance = (float)mc.getDouble("Chance", 1.0D);
/*  77 */     this.priority = mc.getInteger("Priority", 1);
/*  78 */     this.useWorldScaling = mc.getBoolean("UseWorldScaling", true);
/*     */     
/*  80 */     String strPositionType = "LAND";
/*     */     try {
/*  82 */       strPositionType = mc.getString("PositionType", "LAND");
/*  83 */       this.positionType = SpawnPointType.valueOf(strPositionType.toUpperCase());
/*  84 */     } catch (Exception ex) {
/*  85 */       MythicLogger.errorSpawnConfig(this, mc, "'" + strPositionType + "' is not a valid value for PositionType");
/*  86 */       this.positionType = SpawnPointType.LAND;
/*     */     } 
/*     */     
/*  89 */     List<String> nTConditions = mc.getStringList("Conditions");
/*  90 */     for (String s : nTConditions) {
/*  91 */       if (s.contains("\"")) {
/*  92 */         String[] split = s.split("\"");
/*     */         
/*  94 */         int i = 0;
/*  95 */         String ns = "";
/*  96 */         for (String ss : split) {
/*  97 */           if (i % 2 == 1) {
/*  98 */             ns = ns.concat("\"" + SkillString.unparseMessageSpecialChars(ss) + "\"");
/*     */           } else {
/* 100 */             ns = ns.concat(ss);
/*     */           } 
/* 102 */           i++;
/*     */         } 
/* 104 */         s = ns;
/*     */       } 
/* 106 */       SkillCondition sc = SkillCondition.getCondition(s);
/* 107 */       if (!(sc instanceof io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition)) {
/* 108 */         if (this.conditions == null) {
/* 109 */           this.conditions = new ArrayList<>();
/*     */         }
/* 111 */         this.conditions.add(sc);
/*     */       } 
/*     */     } 
/* 114 */     if (this.conditions != null && this.conditions.size() > 0) this.hasConditions = true;
/*     */     
/* 116 */     String world = mc.getString("Worlds");
/* 117 */     String biome = mc.getString("Biomes");
/* 118 */     String reasons = mc.getString("Reason");
/* 119 */     reasons = mc.getString("Reasons", reasons);
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (world != null) {
/* 124 */       String[] worldName = world.split(",");
/*     */       
/* 126 */       World w = null;
/*     */       
/* 128 */       if (worldName != null) {
/* 129 */         for (String wName : worldName) {
/* 130 */           w = Bukkit.getWorld(wName);
/* 131 */           if (w != null) this.worlds.add(BukkitAdapter.adapt(w));
/*     */         
/*     */         } 
/*     */       }
/*     */     } 
/* 136 */     if (biome != null) {
/* 137 */       String[] biomename = biome.split(",");
/* 138 */       for (String bName : biomename) {
/* 139 */         Biome b = Biome.valueOf(bName.toUpperCase());
/* 140 */         if (b != null) this.biomes.add(b);
/*     */       
/*     */       } 
/*     */     } 
/* 144 */     if (reasons != null) {
/* 145 */       String[] r = reasons.split(",");
/* 146 */       for (String rr : r) {
/* 147 */         CreatureSpawnEvent.SpawnReason fr = CreatureSpawnEvent.SpawnReason.valueOf(rr);
/* 148 */         if (fr != null) this.spawnReasons.add(fr); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public float getChance() {
/* 153 */     return this.chance;
/*     */   }
/*     */   public int getLevel(AbstractLocation location) {
/* 156 */     int level = getBaseLevel();
/*     */     
/* 158 */     if (this.useWorldScaling == true) {
/* 159 */       level += WorldScaling.getLevelBonus(location);
/*     */     }
/*     */     
/* 162 */     return level;
/*     */   }
/*     */   public int getBaseLevel() {
/* 165 */     return this.level;
/*     */   }
/*     */   public int getPriority() {
/* 168 */     return this.priority;
/*     */   }
/*     */   public RandomSpawnerAction getAction() {
/* 171 */     return this.action;
/*     */   }
/*     */   public HashSet<Biome> getBiomes() {
/* 174 */     return this.biomes;
/*     */   }
/*     */   public HashSet<AbstractWorld> getWorlds() {
/* 177 */     return this.worlds;
/*     */   }
/*     */   public HashSet<CreatureSpawnEvent.SpawnReason> getReasons() {
/* 180 */     return this.spawnReasons;
/*     */   }
/*     */   public boolean isValid() {
/* 183 */     return (this.mobTypes.size() > 0);
/*     */   }
/*     */   public boolean hasConditions() {
/* 186 */     return this.hasConditions;
/*     */   }
/*     */   
/*     */   public boolean checkSpawn(RandomSpawnPoint rsp) {
/* 190 */     if (this.chance < MythicMobs.r.nextFloat()) {
/* 191 */       return false;
/*     */     }
/* 193 */     if (this.spawnReasons.size() > 0) {
/* 194 */       if (!this.spawnReasons.contains(rsp.getReason())) return false; 
/*     */     } else {
/* 196 */       switch (null.$SwitchMap$org$bukkit$event$entity$CreatureSpawnEvent$SpawnReason[rsp.getReason().ordinal()]) {
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 204 */           return false;
/*     */       } 
/*     */     } 
/* 207 */     if (!this.worlds.contains(rsp.getLocation().getWorld())) {
/* 208 */       return false;
/*     */     }
/* 210 */     if (this.biomes.size() > 0 && 
/* 211 */       !this.biomes.contains(rsp.getBiome())) return false;
/*     */ 
/*     */     
/* 214 */     if (hasConditions()) {
/* 215 */       for (SkillCondition condition : this.conditions) {
/* 216 */         if (!condition.evaluateRandomSpawnPoint(rsp)) {
/* 217 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/* 221 */     return true;
/*     */   }
/*     */   
/*     */   public ActiveMob spawn(RandomSpawnPoint rsp) {
/*     */     MythicMob mm;
/* 226 */     if (this.mobTypes.size() > 0) {
/* 227 */       mm = this.mobTypes.get(MythicMobs.r.nextInt(this.mobTypes.size()));
/*     */     } else {
/* 229 */       mm = this.mobTypes.get(0);
/*     */     } 
/* 231 */     return MythicMobs.inst().getMobManager().spawnMob(mm.getInternalName(), rsp.getLocation(), (int)(getLevel(rsp.getLocation()) * rsp.getLevelMod()));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\random\RandomSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */