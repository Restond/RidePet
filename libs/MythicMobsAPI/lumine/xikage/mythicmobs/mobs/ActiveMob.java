/*      */ package lumine.xikage.mythicmobs.mobs;
/*      */ 
/*      */ import com.google.common.collect.Sets;
/*      */ import io.lumine.utils.Schedulers;
/*      */ import io.lumine.utils.tasks.Scheduler;
/*      */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractBossBar;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*      */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*      */ import io.lumine.xikage.mythicmobs.holograms.types.HealthBar;
/*      */ import io.lumine.xikage.mythicmobs.holograms.types.Nameplate;
/*      */ import io.lumine.xikage.mythicmobs.holograms.types.SpeechBubble;
/*      */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*      */ import io.lumine.xikage.mythicmobs.mobs.MobRegistry;
/*      */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*      */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*      */ import io.lumine.xikage.mythicmobs.skills.auras.AuraRegistry;
/*      */ import io.lumine.xikage.mythicmobs.skills.placeholders.GenericPlaceholderMeta;
/*      */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*      */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*      */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*      */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.entity.AnimalTamer;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.Wolf;
/*      */ import org.bukkit.metadata.FixedMetadataValue;
/*      */ import org.bukkit.metadata.MetadataValue;
/*      */ import org.bukkit.plugin.IllegalPluginAccessException;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.scoreboard.Objective;
/*      */ import org.bukkit.scoreboard.Scoreboard;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ActiveMob
/*      */   implements SkillCaster
/*      */ {
/*      */   private UUID uuid;
/*      */   private String mobType;
/*      */   private transient AbstractEntity entity;
/*      */   private transient MythicMob type;
/*      */   
/*      */   private static final MythicMobs getPlugin() {
/*   62 */     return MythicMobs.inst();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   70 */   private long aliveTime = 0L; public long getAliveTime() { return this.aliveTime; }
/*   71 */    private long lastSeen = System.currentTimeMillis(); private AbstractLocation spawnLocation; public long getLastSeen() { return this.lastSeen; } public AbstractLocation getSpawnLocation() {
/*   72 */     return this.spawnLocation;
/*   73 */   } private VariableRegistry variables = new VariableRegistry(); private PlaceholderString displayName; public VariableRegistry getVariables() { return this.variables; }
/*      */    private int level;
/*      */   public int getLevel() {
/*   76 */     return this.level;
/*   77 */   } private int globalCooldown = 0; private String stance; public int getGlobalCooldown() { return this.globalCooldown; } public void setGlobalCooldown(int globalCooldown) { this.globalCooldown = globalCooldown; }
/*   78 */   public String getStance() { return this.stance; } public void setStance(String stance) { this.stance = stance; }
/*   79 */    private String lastSignal = ""; public String getLastSignal() { return this.lastSignal; }
/*   80 */    private int playerKills = 0; public int getPlayerKills() { return this.playerKills; }
/*   81 */    private Optional<UUID> owner = Optional.empty(); public Optional<UUID> getOwner() { return this.owner; }
/*   82 */    private Optional<String> faction = Optional.empty();
/*   83 */   public HashMap<String, Long> cooldowns = new HashMap<>();
/*      */   private transient SkillCaster parent;
/*      */   
/*      */   public SkillCaster getParent() {
/*   87 */     return this.parent;
/*   88 */   } private transient Collection<AbstractEntity> children = Sets.newConcurrentHashSet(); public Collection<AbstractEntity> getChildren() { return this.children; }
/*   89 */    private transient Optional<io.lumine.xikage.mythicmobs.mobs.ActiveMob> mount = Optional.empty(); public Optional<io.lumine.xikage.mythicmobs.mobs.ActiveMob> getMount() { return this.mount; }
/*   90 */    private transient MythicSpawner spawner = null; private transient ThreatTable threatTable; private transient ImmunityTable immunityTable; public MythicSpawner getSpawner() { return this.spawner; }
/*   91 */   public ThreatTable getThreatTable() { return this.threatTable; } public ImmunityTable getImmunityTable() {
/*   92 */     return this.immunityTable;
/*   93 */   } private transient Optional<AbstractBossBar> bossBar = Optional.empty();
/*   94 */   private transient Map<String, AbstractBossBar> bossBars = null;
/*   95 */   protected transient HealthBar healthbar = null;
/*   96 */   protected transient Nameplate nameplate = null;
/*   97 */   protected transient SpeechBubble speechBubble = null; public SpeechBubble getSpeechBubble() { return this.speechBubble; }
/*      */   
/*   99 */   private transient AbstractEntity newTarget = null;
/*  100 */   private transient double lastDamageSkillAmount = 0.0D; private transient boolean damageSkillRunning = false; private transient boolean dead = false; private transient int noDamageTicks; protected transient AbstractEntity lastAggroCause;
/*      */   
/*  102 */   public boolean isDead() { return this.dead; }
/*  103 */   public int getNoDamageTicks() { return this.noDamageTicks; } public AbstractEntity getLastAggroCause() {
/*  104 */     return this.lastAggroCause;
/*      */   }
/*      */ 
/*      */   
/*      */   public ActiveMob(UUID uuid, AbstractEntity e, MythicMob type, int level) {
/*  109 */     this.uuid = uuid;
/*  110 */     this.entity = e;
/*  111 */     this.type = type;
/*  112 */     this.mobType = type.getInternalName();
/*  113 */     this.spawnLocation = e.getLocation();
/*      */     
/*  115 */     this.level = level;
/*  116 */     setScoreboardLevel(level);
/*      */     
/*  118 */     this.faction = Optional.ofNullable(type.getFaction());
/*  119 */     this.globalCooldown = 0;
/*  120 */     this.stance = "default";
/*  121 */     this.noDamageTicks = type.getNoDamageTicks();
/*      */     
/*  123 */     if (type.usesBossBar()) {
/*  124 */       this.bossBar = type.getBossBar();
/*      */     }
/*  126 */     if (!getType().getDespawns()) {
/*  127 */       remountSpawner();
/*      */     }
/*  129 */     if (ConfigManager.EnableThreatTables && type.usesThreatTable()) {
/*  130 */       this.threatTable = new ThreatTable(this);
/*      */     } else {
/*  132 */       this.threatTable = null;
/*      */     } 
/*  134 */     if (type.usesImmunityTable()) {
/*  135 */       this.immunityTable = new ImmunityTable(this);
/*      */     } else {
/*  137 */       this.immunityTable = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean loadSaved() {
/*  142 */     this.type = getPlugin().getMobManager().getMythicMob(this.mobType);
/*  143 */     this.entity = (MythicMobs.inst().getMinecraftVersion() > 8) ? getPlugin().getWorldManager().getEntity(this.uuid) : null;
/*      */     
/*  145 */     if (this.type == null)
/*      */     {
/*  147 */       return false;
/*      */     }
/*  149 */     if (this.entity == null)
/*      */     {
/*  151 */       return false;
/*      */     }
/*  153 */     this.lastSeen = System.currentTimeMillis();
/*  154 */     return true;
/*      */   }
/*      */   
/*      */   public void tick(int c) {
/*  158 */     if (getPlugin().getMinecraftVersion() >= 14 && getEntity() != null && !getEntity().isValid() && !isDead()) {
/*  159 */       setDespawned();
/*      */       return;
/*      */     } 
/*  162 */     this.aliveTime += c;
/*  163 */     if (this.globalCooldown > 0) this.globalCooldown -= c;
/*      */     
/*  165 */     updateBossBar();
/*  166 */     this.children.removeIf(child -> (child.isDead() || !child.isValid()));
/*      */   }
/*      */   
/*      */   public AbstractEntity getEntity() {
/*  170 */     if (this.entity == null) {
/*  171 */       this.entity = getPlugin().getWorldManager().getEntity(this.uuid);
/*      */     }
/*  173 */     return this.entity;
/*      */   }
/*      */   
/*      */   public LivingEntity getLivingEntity() {
/*  177 */     if (this.entity.isLiving()) {
/*  178 */       return (LivingEntity)this.entity.getBukkitEntity();
/*      */     }
/*  180 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEntity(AbstractEntity e) {
/*  185 */     this.entity = e;
/*      */   }
/*      */   
/*      */   public MythicMob getType() {
/*  189 */     if (this.type == null) {
/*  190 */       this.type = getPlugin().getMobManager().getMythicMob(this.mobType);
/*      */     }
/*  192 */     return this.type;
/*      */   }
/*      */   
/*      */   public String getDisplayName() {
/*  196 */     PlaceholderString display = this.type.getDisplayName();
/*  197 */     return (display == null) ? null : display.get(this);
/*      */   }
/*      */   
/*      */   public AbstractLocation getLocation() {
/*  201 */     return this.entity.getLocation();
/*      */   }
/*      */   
/*      */   public void setParent(SkillCaster am) {
/*  205 */     this.parent = am;
/*  206 */     this.parent.addChild(getEntity());
/*      */   }
/*      */ 
/*      */   
/*      */   public void addChild(AbstractEntity entity) {
/*  211 */     this.children.add(entity);
/*      */   }
/*      */   
/*      */   public void setOwner(UUID uuid) {
/*  215 */     this.owner = Optional.of(uuid);
/*  216 */     if (getType().getMythicEntity() instanceof io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitWolf) {
/*  217 */       Wolf wolf = (Wolf)getEntity().getBukkitEntity();
/*  218 */       wolf.setOwner((AnimalTamer)Bukkit.getPlayer(uuid));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeOwner() {
/*  223 */     this.owner = Optional.empty();
/*      */   }
/*      */   
/*      */   public void setMount(io.lumine.xikage.mythicmobs.mobs.ActiveMob am) {
/*  227 */     this.mount = Optional.of(am);
/*      */   }
/*      */   
/*      */   public void remountType() {
/*  231 */     if (this.type == null) {
/*  232 */       unregister();
/*      */       
/*      */       return;
/*      */     } 
/*  236 */     MythicMob mm = getPlugin().getMobManager().getMythicMob(this.type.getInternalName());
/*      */     
/*  238 */     if (mm != null) {
/*  239 */       this.type = mm;
/*      */     }
/*  241 */     if ((getEntity().isLiving() && getEntity().getHealth() == getEntity().getMaxHealth()) || !getEntity().isLiving()) {
/*  242 */       this.type.applyMobOptions(this, this.level);
/*  243 */       this.type.applyMobVolatileOptions(this);
/*      */     } 
/*      */   }
/*      */   public double getDamage() {
/*  247 */     double damage = getType().getBaseDamage();
/*      */     
/*  249 */     if (this.level > 1 && 
/*  250 */       getType().getPerLevelDamage() > 0.0D) {
/*  251 */       damage += getType().getPerLevelDamage() * (this.level - 1);
/*      */     }
/*      */ 
/*      */     
/*  255 */     return damage;
/*      */   }
/*      */   public double getArmor() {
/*  258 */     double armor = getType().getBaseArmor();
/*      */     
/*  260 */     if (this.level > 1 && 
/*  261 */       getType().getPerLevelArmor() > 0.0D) {
/*  262 */       armor += getType().getPerLevelArmor() * (this.level - 1);
/*      */     }
/*      */ 
/*      */     
/*  266 */     return armor;
/*      */   }
/*      */   public void setLevel(int level) {
/*  269 */     setScoreboardLevel(level);
/*  270 */     this.level = level;
/*  271 */     getType().applyMobOptions(this, level);
/*      */   }
/*      */   private void setScoreboardLevel(int level) {
/*  274 */     Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
/*      */     
/*  276 */     Objective o = sb.getObjective("MythicMobLevel");
/*      */     
/*  278 */     if (o == null) {
/*  279 */       o = sb.registerNewObjective("MythicMobLevel", "dummy");
/*      */     }
/*  281 */     o.getScore(getUniqueId().toString()).setScore(level);
/*      */   }
/*      */   
/*      */   public float getPower() {
/*  285 */     return (float)(1.0D + (getLevel() - 1) * getType().getPerLevelPower());
/*      */   }
/*      */   
/*      */   public UUID getUniqueId() {
/*  289 */     return this.uuid;
/*      */   }
/*      */   
/*      */   public boolean hasFaction() {
/*  293 */     if (this.faction.isPresent()) {
/*  294 */       return true;
/*      */     }
/*  296 */     return this.type.hasFaction();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getFaction() {
/*  301 */     if (this.faction.isPresent()) {
/*  302 */       return this.faction.get();
/*      */     }
/*  304 */     return this.type.getFaction();
/*      */   }
/*      */ 
/*      */   
/*      */   public io.lumine.xikage.mythicmobs.mobs.ActiveMob setFaction(String faction) {
/*  309 */     this.faction = Optional.ofNullable(faction);
/*  310 */     if (faction != null) {
/*  311 */       getLivingEntity().setMetadata("faction", (MetadataValue)new FixedMetadataValue((Plugin)getPlugin(), getFaction()));
/*      */     }
/*  313 */     return this;
/*      */   }
/*      */   
/*      */   public void incrementPlayerKills() {
/*  317 */     this.playerKills++;
/*      */   }
/*      */   
/*      */   public void importPlayerKills(int pk) {
/*  321 */     this.playerKills = pk;
/*      */   }
/*      */   
/*      */   public void setLastAggroCause(AbstractEntity aggro) {
/*  325 */     this.lastAggroCause = aggro;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasTarget() {
/*  332 */     if (hasThreatTable()) {
/*  333 */       return (this.threatTable.getTopThreatHolder() == null);
/*      */     }
/*  335 */     return (this.entity.isCreature() && this.entity.getTarget() != null);
/*      */   }
/*      */   
/*      */   public void setTarget(AbstractEntity l) {
/*  339 */     this.newTarget = l;
/*  340 */     if (this.entity.isLiving()) {
/*  341 */       getPlugin().getVolatileCodeHandler().getAIHandler().setTarget((LivingEntity)BukkitAdapter.adapt(this.entity), (LivingEntity)BukkitAdapter.adapt(l));
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean changingTarget() {
/*  346 */     return (this.newTarget != null);
/*      */   }
/*      */   
/*      */   public AbstractEntity getNewTarget() {
/*  350 */     return this.newTarget;
/*      */   }
/*      */   
/*      */   public void voidTargetChange() {
/*  354 */     this.newTarget = null;
/*      */   }
/*      */   
/*      */   public void resetTarget() {
/*  358 */     if (hasThreatTable()) {
/*  359 */       this.threatTable.dropCombat();
/*      */     } else {
/*  361 */       getPlugin().getVolatileCodeHandler().getAIHandler().setTarget((LivingEntity)BukkitAdapter.adapt(this.entity), null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasThreatTable() {
/*  370 */     return (this.threatTable != null);
/*      */   }
/*      */   
/*      */   public void importThreatTable(ThreatTable tt) {
/*  374 */     this.threatTable = tt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasImmunityTable() {
/*  382 */     return (this.immunityTable != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDespawned() {
/*  390 */     if (!this.dead) {
/*  391 */       this.dead = true;
/*      */       
/*      */       try {
/*  394 */         TriggeredSkill triggeredSkill = new TriggeredSkill(SkillTrigger.DESPAWNED, this, getEntity(), new org.apache.commons.lang3.tuple.Pair[0]);
/*  395 */       } catch (IllegalPluginAccessException illegalPluginAccessException) {}
/*      */       
/*  397 */       Scheduler.runSync(() -> getPlugin().getWorldManager().handleMobDespawnEvent(this));
/*      */       
/*  399 */       if (getSpawner() != null) {
/*  400 */         getSpawner().markMobDespawned(this);
/*      */       }
/*      */     } 
/*  403 */     unregister();
/*      */   }
/*      */   
/*      */   public void setDespawnedSync() {
/*  407 */     if (!this.dead) {
/*  408 */       this.dead = true;
/*      */       
/*      */       try {
/*  411 */         TriggeredSkill triggeredSkill = new TriggeredSkill(SkillTrigger.DESPAWNED, this, getEntity(), new org.apache.commons.lang3.tuple.Pair[0]);
/*  412 */       } catch (IllegalPluginAccessException illegalPluginAccessException) {}
/*      */       
/*  414 */       MythicMobs.inst().getWorldManager().handleMobDespawnEvent(this);
/*      */       
/*  416 */       if (getSpawner() != null) {
/*  417 */         getSpawner().markMobDespawned(this);
/*      */       }
/*      */     } 
/*  420 */     unregister();
/*      */   }
/*      */   
/*      */   public void setDead() {
/*  424 */     this.dead = true;
/*      */     
/*  426 */     if (getSpawner() != null) {
/*  427 */       getSpawner().markMobDead(this);
/*      */     }
/*  429 */     unregister();
/*      */   }
/*      */   
/*      */   public void setUnloaded() {
/*  433 */     if (!this.dead) {
/*  434 */       if (this.type.getDespawns() && !this.type.isPersistent()) {
/*  435 */         this.dead = true;
/*      */         
/*      */         try {
/*  438 */           TriggeredSkill triggeredSkill = new TriggeredSkill(SkillTrigger.DESPAWNED, this, getEntity(), new org.apache.commons.lang3.tuple.Pair[0]);
/*  439 */         } catch (IllegalPluginAccessException illegalPluginAccessException) {}
/*      */         
/*  441 */         getPlugin().getWorldManager().handleMobDespawnEvent(this);
/*      */         
/*  443 */         if (getSpawner() != null) {
/*  444 */           getSpawner().markMobDespawned(this);
/*      */         }
/*  446 */         unregister();
/*      */       } else {
/*  448 */         ((MobRegistry)getPlugin().getMobManager().getMobRegistry().get()).setInactive(this.uuid);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void unregister() {
/*  454 */     getPlugin().getMobManager().setIgnoreEntity(this.entity.getUniqueId());
/*      */     
/*  456 */     if (this.bossBar.isPresent()) {
/*  457 */       ((AbstractBossBar)this.bossBar.get()).removeAll();
/*  458 */       this.bossBar = Optional.empty();
/*      */     } 
/*  460 */     if (this.bossBars != null) {
/*  461 */       this.bossBars.values().forEach(bar -> bar.terminate());
/*  462 */       this.bossBars.clear();
/*      */     } 
/*  464 */     setShowCustomNameplate(false);
/*      */     
/*      */     try {
/*  467 */       Schedulers.sync().runLater(() -> Bukkit.getScoreboardManager().getMainScoreboard().resetScores(getUniqueId().toString()), 100L);
/*  468 */     } catch (IllegalPluginAccessException ex) {
/*  469 */       Bukkit.getScoreboardManager().getMainScoreboard().resetScores(getUniqueId().toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUsingDamageSkill(boolean b) {
/*  477 */     this.damageSkillRunning = b;
/*      */   }
/*      */   public boolean isUsingDamageSkill() {
/*  480 */     return this.damageSkillRunning;
/*      */   }
/*      */   public void setLastDamageSkillAmount(double d) {
/*  483 */     this.lastDamageSkillAmount = d;
/*      */   }
/*      */   public double getLastDamageSkillAmount() {
/*  486 */     return this.lastDamageSkillAmount;
/*      */   }
/*      */   public void setSpawner(MythicSpawner ms) {
/*  489 */     this.spawner = ms;
/*      */     
/*  491 */     if (getType().isPersistent()) {
/*  492 */       Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
/*      */       
/*  494 */       Objective o = sb.getObjective("MythicMobSpawner");
/*      */       
/*  496 */       if (o == null) {
/*  497 */         o = sb.registerNewObjective("MythicMobSpawner", "dummy");
/*      */       }
/*  499 */       o.getScore(getUniqueId().toString()).setScore(ms.hashCode());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void remountSpawner() {
/*  504 */     Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
/*      */     
/*  506 */     Objective o = sb.getObjective("MythicMobSpawner");
/*      */     
/*  508 */     if (o == null) {
/*  509 */       o = sb.registerNewObjective("MythicMobSpawner", "dummy");
/*      */     }
/*  511 */     int score = o.getScore(getUniqueId().toString()).getScore();
/*      */     
/*  513 */     if (score != 0) {
/*  514 */       Optional<MythicSpawner> maybeSpawner = getPlugin().getSpawnerManager().getSpawnerByHashcode(score);
/*  515 */       if (maybeSpawner.isPresent()) {
/*  516 */         this.spawner = maybeSpawner.get();
/*  517 */         this.spawner.trackMob(this);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void signalMob(AbstractEntity trigger, String signal) {
/*  523 */     SkillMetadata data = new SkillMetadata(SkillTrigger.SIGNAL, this, trigger);
/*      */     
/*  525 */     if (trigger != null) {
/*  526 */       HashSet<AbstractEntity> eTargets = new HashSet<>();
/*  527 */       eTargets.add(data.getTrigger());
/*  528 */       data.setEntityTargets(eTargets);
/*      */     } 
/*  530 */     data.setPower(getPower());
/*  531 */     this.lastSignal = signal;
/*  532 */     getType().executeSignalSkill(signal, data);
/*      */   }
/*      */   
/*      */   public void signalDamaged() {
/*  536 */     if (this.healthbar != null) {
/*  537 */       this.healthbar.update();
/*      */     }
/*  539 */     else if (getType().getUsesHealthBar().booleanValue()) {
/*  540 */       this.healthbar = getPlugin().getHologramManager().createHealthBar(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AuraRegistry getAuraRegistry() {
/*  547 */     return getPlugin().getSkillManager().getAuraManager().getAuraRegistry(this.entity);
/*      */   }
/*      */   
/*      */   public void addBar(String key, AbstractBossBar bar) {
/*  551 */     if (this.bossBars == null) {
/*  552 */       this.bossBars = new ConcurrentHashMap<>();
/*      */     }
/*  554 */     this.bossBars.put(key, bar);
/*      */   }
/*      */   public AbstractBossBar getBar(String key) {
/*  557 */     return (this.bossBars == null) ? null : this.bossBars.getOrDefault(key, null);
/*      */   }
/*      */   public void removeBar(String key) {
/*  560 */     if (this.bossBars != null && this.bossBars.containsKey(key))
/*  561 */       ((AbstractBossBar)this.bossBars.remove(key)).terminate(); 
/*      */   }
/*      */   
/*      */   public void updateBossBar() {
/*  565 */     if (!this.bossBar.isPresent() && this.bossBars == null) {
/*      */       return;
/*      */     }
/*      */     
/*  569 */     Collection<AbstractPlayer> inRange = getPlugin().getEntityManager().getPlayersInRangeSq(getLocation(), getType().getBossBarRangeSquared());
/*      */     
/*  571 */     if (this.bossBar.isPresent()) {
/*  572 */       AbstractBossBar bar = this.bossBar.get();
/*  573 */       Collection<AbstractPlayer> current = bar.getPlayers();
/*      */       
/*  575 */       double progress = getEntity().getHealth() / getEntity().getMaxHealth();
/*  576 */       String title = this.type.getBossBarTitle().get((PlaceholderMeta)new GenericPlaceholderMeta(this, getEntity()));
/*      */       
/*  578 */       bar.setTitle(title);
/*  579 */       bar.setProgress(progress);
/*  580 */       current.stream().forEach(player -> {
/*      */             if (!paramCollection.contains(player)) {
/*      */               paramAbstractBossBar.removePlayer(player);
/*      */             }
/*      */           });
/*  585 */       inRange.stream().forEach(player -> {
/*      */             if (!paramCollection.contains(player)) {
/*      */               paramAbstractBossBar.addPlayer(player);
/*      */             }
/*      */           });
/*      */     } 
/*  591 */     if (this.bossBars != null) {
/*  592 */       this.bossBars.values().forEach(barx -> barx.getPlayers().removeIf(()));
/*      */       
/*  594 */       inRange.stream().forEach(player -> this.bossBars.values().forEach(()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getShowCustomNameplate() {
/*  605 */     return (this.nameplate == null);
/*      */   }
/*      */   
/*      */   public void setShowCustomNameplate(boolean b) {
/*  609 */     if (b) {
/*  610 */       if (this.nameplate == null) {
/*  611 */         this.nameplate = getPlugin().getHologramManager().createNameplate(this);
/*      */       }
/*      */     }
/*  614 */     else if (this.nameplate != null) {
/*  615 */       this.nameplate.terminate();
/*  616 */       this.nameplate = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public SpeechBubble createSpeechBubble() {
/*  622 */     if (this.speechBubble == null) {
/*  623 */       this.speechBubble = getPlugin().getHologramManager().createSpeechBubble(this);
/*      */     }
/*  625 */     return this.speechBubble;
/*      */   }
/*      */   
/*      */   public void removeSpeechBubble() {
/*  629 */     if (this.speechBubble != null) {
/*  630 */       this.speechBubble.terminate();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1072 */     return getUniqueId().toString().hashCode();
/*      */   }
/*      */   
/*      */   public ActiveMob() {}
/*      */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ActiveMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */