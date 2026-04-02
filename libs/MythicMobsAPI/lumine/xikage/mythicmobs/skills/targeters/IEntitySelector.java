/*     */ package lumine.xikage.mythicmobs.skills.targeters;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class IEntitySelector
/*     */   extends SkillTargeter
/*     */ {
/*     */   private boolean targetSelf = false;
/*     */   private boolean targetPlayers = true;
/*     */   private boolean targetCreativeMode = false;
/*     */   private boolean targetSpectatorMode = false;
/*     */   private boolean targetCitizensNPCs = false;
/*     */   private boolean targetAnimals = true;
/*     */   private boolean targetCreatures = true;
/*     */   private boolean targetMonsters = true;
/*     */   private boolean targetWaterMobs = true;
/*     */   private boolean targetFlyingMobs = true;
/*     */   private boolean targetSameFaction = true;
/*  47 */   private int limit = 0;
/*  48 */   private FilterSorter sorter = FilterSorter.NONE;
/*     */   
/*  50 */   private List<String> ignoreTypes = null;
/*  51 */   private List<SkillCondition> conditions = null;
/*     */   
/*     */   public IEntitySelector(MythicLineConfig mlc) {
/*  54 */     super(mlc);
/*     */     
/*  56 */     this.limit = mlc.getInteger(new String[] { "limit" }, 0);
/*     */     
/*  58 */     String sort = mlc.getString(new String[] { "sort", "sortby" }, "NONE", new String[0]).toUpperCase();
/*     */     
/*     */     try {
/*  61 */       this.sorter = FilterSorter.valueOf(sort);
/*  62 */     } catch (Exception ex) {
/*  63 */       MythicLogger.errorTargeterConfig(this, mlc, "'" + sort + "' is not a valid sorter.");
/*     */     } 
/*     */     
/*  66 */     String target = mlc.getString("target", null);
/*  67 */     String ignore = mlc.getString("ignore", null);
/*     */     
/*  69 */     String ignoreTypes = mlc.getString("ignoretype", null);
/*     */     
/*  71 */     if (target != null) {
/*  72 */       this.targetPlayers = false;
/*  73 */       this.targetCreativeMode = false;
/*  74 */       this.targetSpectatorMode = false;
/*  75 */       this.targetCitizensNPCs = false;
/*  76 */       this.targetAnimals = false;
/*  77 */       this.targetCreatures = false;
/*  78 */       this.targetMonsters = false;
/*  79 */       this.targetWaterMobs = false;
/*  80 */       this.targetFlyingMobs = false;
/*     */       
/*  82 */       if (target.contains("self") || target.contains("caster")) {
/*  83 */         this.targetSelf = true;
/*     */       }
/*  85 */       if (target.contains("player")) {
/*  86 */         this.targetPlayers = true;
/*     */       }
/*  88 */       if (target.contains("creative")) {
/*  89 */         this.targetCreativeMode = true;
/*     */       }
/*  91 */       if (target.contains("spectator")) {
/*  92 */         this.targetSpectatorMode = true;
/*     */       }
/*  94 */       if (target.contains("npc")) {
/*  95 */         this.targetCitizensNPCs = true;
/*     */       }
/*  97 */       if (target.contains("animal")) {
/*  98 */         this.targetAnimals = true;
/*     */       }
/* 100 */       if (target.contains("monster")) {
/* 101 */         this.targetMonsters = true;
/*     */       }
/* 103 */       if (target.contains("creatures")) {
/* 104 */         this.targetCreatures = true;
/*     */       }
/*     */     } 
/* 107 */     if (ignore != null) {
/* 108 */       if (ignore.contains("player")) {
/* 109 */         this.targetPlayers = false;
/*     */       }
/* 111 */       if (ignore.contains("creative")) {
/* 112 */         this.targetCreativeMode = false;
/*     */       }
/* 114 */       if (ignore.contains("spectator")) {
/* 115 */         this.targetSpectatorMode = false;
/*     */       }
/* 117 */       if (ignore.contains("npc")) {
/* 118 */         this.targetCitizensNPCs = false;
/*     */       }
/* 120 */       if (ignore.contains("animal")) {
/* 121 */         this.targetAnimals = false;
/*     */       }
/* 123 */       if (ignore.contains("monsters")) {
/* 124 */         this.targetMonsters = false;
/*     */       }
/* 126 */       if (ignore.contains("creatures")) {
/* 127 */         this.targetCreatures = false;
/*     */       }
/* 129 */       if (ignore.contains("faction")) {
/* 130 */         this.targetSameFaction = false;
/*     */       }
/*     */     } 
/* 133 */     if (ignoreTypes != null) {
/* 134 */       this.ignoreTypes = new ArrayList<>();
/* 135 */       for (String s : ignoreTypes.split(",")) this.ignoreTypes.add(s);
/*     */     
/*     */     } 
/* 138 */     this.targetSelf = mlc.getBoolean("targetself", this.targetSelf);
/* 139 */     this.targetPlayers = mlc.getBoolean("targetplayers", this.targetPlayers);
/* 140 */     this.targetCreativeMode = mlc.getBoolean("targetcreative", this.targetCreativeMode);
/* 141 */     this.targetSpectatorMode = mlc.getBoolean("targetspectator", this.targetSpectatorMode);
/* 142 */     this.targetCitizensNPCs = mlc.getBoolean("targetnpcs", this.targetCitizensNPCs);
/* 143 */     this.targetAnimals = mlc.getBoolean("targetanimals", this.targetAnimals);
/* 144 */     this.targetCreatures = mlc.getBoolean("targetcreatures", this.targetCreatures);
/* 145 */     this.targetSameFaction = mlc.getBoolean("targetsamefaction", this.targetSameFaction);
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
/*     */   public void filter(SkillMetadata data, boolean targetCreative) {
/* 157 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "+ Applying entity target filters", new Object[0]);
/* 158 */     HashSet<AbstractEntity> targets = data.getEntityTargets();
/*     */     
/* 160 */     targets.removeIf(t -> {
/*     */           if (t == null) {
/*     */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           if (!this.targetSelf && !(this instanceof io.lumine.xikage.mythicmobs.skills.targeters.SelfTargeter) && t.getUniqueId().equals(paramSkillMetadata.getCaster().getEntity().getUniqueId())) {
/*     */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           if (!this.targetPlayers) {
/*     */             if (t.isPlayer()) {
/*     */               return true;
/*     */             }
/*     */           } else {
/*     */             if (!this.targetCreativeMode && !paramBoolean && t.isPlayer() && t.asPlayer().isInCreativeMode()) {
/*     */               return true;
/*     */             }
/*     */ 
/*     */             
/*     */             if (!this.targetSpectatorMode && !paramBoolean && t.isPlayer() && t.asPlayer().isInSpectatorMode()) {
/*     */               return true;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/*     */           if (!this.targetAnimals && t.isAnimal()) {
/*     */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           if (!this.targetCreatures && t.isCreature()) {
/*     */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           if (!this.targetMonsters && t.isMonster()) {
/*     */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           if (!this.targetCitizensNPCs && t.isLiving() && t.getBukkitEntity().hasMetadata("NPC")) {
/*     */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           if (!this.targetSameFaction) {
/*     */             ActiveMob am = (ActiveMob)paramSkillMetadata.getCaster();
/*     */ 
/*     */             
/*     */             if (t.isLiving() && t.getBukkitEntity().hasMetadata("Faction")) {
/*     */               List<MetadataValue> md = t.getBukkitEntity().getMetadata("Faction");
/*     */ 
/*     */               
/*     */               for (MetadataValue v : md) {
/*     */                 if (v.asString().equals(am.getFaction())) {
/*     */                   return true;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/*     */           return false;
/*     */         });
/*     */     
/* 226 */     if (this.limit > 0) {
/* 227 */       AbstractLocation origin; ActiveMob am; ActiveMob.ThreatTable tt; switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$targeters$IEntitySelector$FilterSorter[this.sorter.ordinal()]) {
/*     */         case 1:
/* 229 */           origin = data.getOrigin();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 238 */           targets = (HashSet<AbstractEntity>)targets.stream().sorted((o1, o2) -> { double d1 = paramAbstractLocation.distanceSquared(o1.getLocation()); double d2 = paramAbstractLocation.distanceSquared(o2.getLocation()); return Double.compare(d1, d2); }).limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */         
/*     */         case 2:
/* 242 */           origin = data.getOrigin();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 251 */           targets = (HashSet<AbstractEntity>)targets.stream().sorted((o1, o2) -> { double d1 = paramAbstractLocation.distanceSquared(o1.getLocation()); double d2 = paramAbstractLocation.distanceSquared(o2.getLocation()); return Double.compare(d2, d1); }).limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 263 */           targets = (HashSet<AbstractEntity>)targets.stream().sorted((o1, o2) -> { double d1 = o1.getHealth(); double d2 = o2.getHealth(); return Double.compare(d2, d1); }).limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 275 */           targets = (HashSet<AbstractEntity>)targets.stream().sorted((o1, o2) -> { double d1 = o1.getHealth(); double d2 = o2.getHealth(); return Double.compare(d1, d2); }).limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */         
/*     */         case 5:
/* 279 */           if (!(data.getCaster() instanceof ActiveMob) || !((ActiveMob)data.getCaster()).hasThreatTable()) {
/* 280 */             MythicLogger.errorTargeterConfig(this, this.config, "Threat sorters can only be used with mobs that have ThreatTables enabled");
/*     */           }
/* 282 */           am = (ActiveMob)data.getCaster();
/* 283 */           tt = am.getThreatTable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 293 */           targets = (HashSet<AbstractEntity>)targets.stream().sorted((o1, o2) -> { double d1 = paramThreatTable.getThreat(o1); double d2 = paramThreatTable.getThreat(o2); return Double.compare(d2, d1); }).limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */         
/*     */         case 6:
/* 297 */           if (!(data.getCaster() instanceof ActiveMob) || !((ActiveMob)data.getCaster()).hasThreatTable()) {
/* 298 */             MythicLogger.errorTargeterConfig(this, this.config, "Threat sorters can only be used with mobs that have ThreatTables enabled");
/*     */           }
/* 300 */           am = (ActiveMob)data.getCaster();
/* 301 */           tt = am.getThreatTable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 311 */           targets = (HashSet<AbstractEntity>)targets.stream().sorted((o1, o2) -> { double d1 = paramThreatTable.getThreat(o1); double d2 = paramThreatTable.getThreat(o2); return Double.compare(d1, d2); }).limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 7:
/* 321 */           targets = (HashSet<AbstractEntity>)((Stream)targets.stream().collect(Collectors.collectingAndThen(Collectors.toList(), collected -> { Collections.shuffle(collected); return collected.stream(); }))).limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */         
/*     */         default:
/* 325 */           targets = (HashSet<AbstractEntity>)targets.stream().limit(this.limit).collect(Collectors.toCollection(HashSet::new));
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 331 */     data.setEntityTargets(targets);
/*     */   }
/*     */   
/*     */   public abstract HashSet<AbstractEntity> getEntities(SkillMetadata paramSkillMetadata);
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\IEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */