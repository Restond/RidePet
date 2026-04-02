/*      */ package lumine.xikage.mythicmobs.mobs;
/*      */ 
/*      */ import io.lumine.utils.Schedulers;
/*      */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractBossBar;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*      */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*      */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
/*      */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*      */ import io.lumine.xikage.mythicmobs.compatibility.MPetCompat;
/*      */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*      */ import io.lumine.xikage.mythicmobs.drops.DropTable;
/*      */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*      */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*      */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*      */ import io.lumine.xikage.mythicmobs.legacy.LegacyMythicTimerSkill;
/*      */ import io.lumine.xikage.mythicmobs.legacy.LegacySkillHandler;
/*      */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*      */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*      */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*      */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*      */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*      */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntityType;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*      */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*      */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Queue;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.attribute.Attribute;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.inventory.EntityEquipment;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.metadata.FixedMetadataValue;
/*      */ import org.bukkit.metadata.MetadataValue;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.scoreboard.Objective;
/*      */ import org.bukkit.scoreboard.Scoreboard;
/*      */ import org.bukkit.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MythicMob
/*      */   implements Comparable<MythicMob>
/*      */ {
/*      */   private String file;
/*      */   private MythicConfig config;
/*      */   private String internalName;
/*      */   protected PlaceholderString displayName;
/*      */   protected MythicEntity mobType;
/*   75 */   protected Optional<MPetCompat.MiniaturePetType> mPetType = Optional.empty();
/*      */   
/*      */   protected boolean optionDespawn;
/*      */   
/*      */   protected boolean optionPersistent;
/*      */   protected boolean optionShowHealthInChat = false;
/*      */   protected String strMobType;
/*   82 */   protected String faction = null;
/*      */   
/*      */   protected double health;
/*      */   
/*      */   protected double damage;
/*      */   
/*      */   protected double armor;
/*      */   protected double attrMovementSpeed;
/*      */   protected double attrKnockbackResist;
/*      */   protected double attrFollowRange;
/*      */   protected double attrAttackSpeed;
/*      */   protected double lvlModDamage;
/*      */   protected double lvlModHealth;
/*      */   protected double lvlModArmor;
/*      */   protected double lvlModKBR;
/*      */   protected double lvlModPower;
/*      */   protected double lvlModSpeed;
/*      */   protected double lvlModAttackSpeed;
/*      */   protected boolean optionSilent = false;
/*      */   protected boolean optionNoAI = false;
/*      */   protected boolean optionGlowing = false;
/*      */   protected boolean optionInvincible = false;
/*      */   protected boolean optionCollidable = true;
/*      */   protected boolean optionNoGravity = true;
/*      */   protected boolean optionInteractable = true;
/*      */   protected boolean useBossBar = false;
/*      */   protected int bossBarRange;
/*      */   protected int bossBarRangeSq;
/*      */   protected PlaceholderString bossBarTitle;
/*      */   protected AbstractBossBar.BarColor bossBarColor;
/*      */   protected AbstractBossBar.BarStyle bossBarStyle;
/*      */   protected boolean bossBarCreateFog;
/*      */   protected boolean bossBarDarkenSky;
/*      */   protected boolean bossBarPlayMusic;
/*  116 */   protected Optional<String> mount = Optional.empty();
/*  117 */   protected Optional<String> rider = Optional.empty();
/*      */   private List<String> drops; private DropTable dropTable; private List<String> equipment; private DropTable equipmentTable; private Map<String, Double> damageModifiers; private Map<String, Double> entityDamageModifiers; private List<String> levelmods;
/*  119 */   public List<String> getDrops() { return this.drops; } public DropTable getDropTable() {
/*  120 */     return this.dropTable;
/*      */   }
/*  122 */   public List<String> getEquipment() { return this.equipment; } public DropTable getEquipmentTable() {
/*  123 */     return this.equipmentTable;
/*      */   }
/*  125 */   public Map<String, Double> getDamageModifiers() { return this.damageModifiers; } public Map<String, Double> getEntityDamageModifiers() {
/*  126 */     return this.entityDamageModifiers;
/*      */   }
/*  128 */   private List<String> aiGoalSelectors = new ArrayList<>();
/*  129 */   private List<String> aiTargetSelectors = new ArrayList<>();
/*      */   
/*  131 */   private Queue<SkillMechanic> mSkills = new LinkedList<>();
/*  132 */   private Queue<SkillMechanic> mSpawnSkills = new LinkedList<>();
/*  133 */   private Queue<SkillMechanic> mDeathSkills = new LinkedList<>();
/*  134 */   private Queue<SkillMechanic> mPlayerDeathSkills = new LinkedList<>();
/*  135 */   private Queue<SkillMechanic> mTimerSkills = new LinkedList<>();
/*  136 */   private Queue<SkillMechanic> mGenericSignalSkills = new LinkedList<>();
/*  137 */   private Queue<SkillMechanic> mSpawnerReadySkills = new LinkedList<>();
/*  138 */   private Map<String, SkillMechanic> mSignalSkills = new HashMap<>();
/*      */   private List<String> legacySkills; public List<LegacyMythicTimerSkill> legacyTimerSkills; public boolean usingTimers = false;
/*      */   int size;
/*      */   private int noDamageTicks;
/*      */   private int maxAttackRange;
/*      */   private int maxAttackableRange;
/*      */   private int maxThreatDistance;
/*      */   
/*  146 */   public int getNoDamageTicks() { return this.noDamageTicks; }
/*  147 */   public int getMaxAttackRange() { return this.maxAttackRange; }
/*  148 */   public int getMaxAttackableRange() { return this.maxAttackableRange; } public int getMaxThreatDistance() {
/*  149 */     return this.maxThreatDistance;
/*      */   }
/*      */   private boolean alwaysShowName = true; private boolean showNameOnDamage = true; private boolean useThreatTable;
/*      */   private boolean useImmunityTable;
/*      */   private boolean useCustomNameplate;
/*      */   
/*      */   public boolean isUseCustomNameplate() {
/*  156 */     return this.useCustomNameplate;
/*      */   }
/*      */   
/*      */   private boolean optionTTFromDamage = true;
/*      */   private boolean optionTTDecayUnreachable = true;
/*  161 */   private Boolean repeatAllSkills = Boolean.valueOf(false); public Boolean getRepeatAllSkills() { return this.repeatAllSkills; }
/*  162 */    private Boolean preventOtherDrops = Boolean.valueOf(false); public Boolean getPreventOtherDrops() { return this.preventOtherDrops; }
/*  163 */    private Boolean preventRandomEquipment = Boolean.valueOf(false); public Boolean getPreventRandomEquipment() { return this.preventRandomEquipment; }
/*  164 */    private Boolean preventLeashing = Boolean.valueOf(false); public Boolean getPreventLeashing() { return this.preventLeashing; }
/*  165 */    private Boolean preventRename = Boolean.valueOf(true); public Boolean getPreventRename() { return this.preventRename; }
/*  166 */    private Boolean preventSlimeSplit = Boolean.valueOf(true); public Boolean getPreventSlimeSplit() { return this.preventSlimeSplit; }
/*  167 */    private Boolean preventEndermanTeleport = Boolean.valueOf(true); public Boolean getPreventEndermanTeleport() { return this.preventEndermanTeleport; }
/*  168 */    private Boolean preventItemPickup = Boolean.valueOf(true); public Boolean getPreventItemPickup() { return this.preventItemPickup; }
/*  169 */    private Boolean preventSilverfishInfection = Boolean.valueOf(true); public Boolean getPreventSilverfishInfection() { return this.preventSilverfishInfection; }
/*  170 */    private Boolean preventSunburn = Boolean.valueOf(false); public Boolean getPreventSunburn() { return this.preventSunburn; }
/*  171 */    private Boolean preventExploding = Boolean.valueOf(false); public Boolean getPreventExploding() { return this.preventExploding; }
/*  172 */    private Boolean preventMobKillDrops = Boolean.valueOf(false); public Boolean getPreventMobKillDrops() { return this.preventMobKillDrops; }
/*  173 */    private Boolean preventTransformation = Boolean.valueOf(false); public Boolean getPreventTransformation() { return this.preventTransformation; }
/*  174 */    private Boolean passthroughDamage = Boolean.valueOf(false); public Boolean getPassthroughDamage() { return this.passthroughDamage; }
/*      */   
/*  176 */   private Boolean digOutOfGround = Boolean.valueOf(false); public Boolean getDigOutOfGround() { return this.digOutOfGround; }
/*      */   
/*  178 */   private Boolean usesHealthBar = Boolean.valueOf(false); protected double spawnVelocityX; public Boolean getUsesHealthBar() { return this.usesHealthBar; }
/*      */   
/*  180 */   protected double spawnVelocityXMax = 0.0D; protected double spawnVelocityY; public double getSpawnVelocityXMax() { return this.spawnVelocityXMax; }
/*  181 */    protected double spawnVelocityYMax = 0.0D; protected double spawnVelocityZ; public double getSpawnVelocityYMax() { return this.spawnVelocityYMax; }
/*  182 */    protected double spawnVelocityZMax = 0.0D; public double getSpawnVelocityZMax() { return this.spawnVelocityZMax; }
/*  183 */   protected boolean spawnVelocityXRange = false; public boolean isSpawnVelocityXRange() { return this.spawnVelocityXRange; }
/*  184 */   protected boolean spawnVelocityYRange = false; public boolean isSpawnVelocityYRange() { return this.spawnVelocityYRange; } protected boolean spawnVelocityZRange = false; public boolean isSpawnVelocityZRange() {
/*  185 */     return this.spawnVelocityZRange;
/*      */   } private boolean fakePlayer = false; protected List<PlaceholderString> killMessages; public String disguise; public boolean isFakePlayer() {
/*  187 */     return this.fakePlayer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MythicMob(String file, String internalName, MythicConfig mc) {
/*  195 */     this.config = mc;
/*  196 */     this.file = file;
/*  197 */     this.internalName = internalName;
/*      */     
/*  199 */     MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Loading MythicMob type '{0}'...", new Object[] { this.internalName });
/*      */     
/*  201 */     this.strMobType = mc.getString("Type", this.strMobType);
/*  202 */     this.strMobType = mc.getString("MobType", this.strMobType);
/*  203 */     this.strMobType = mc.getString("Mobtype", this.strMobType);
/*      */     
/*  205 */     if (this.strMobType == null) {
/*  206 */       MythicEntity me = MythicEntity.getMythicEntity(internalName);
/*      */       
/*  208 */       if (me == null) {
/*  209 */         MythicLogger.errorMobConfig(this, mc, "Could not load MythicMob " + this.internalName + "! No Type specified.");
/*  210 */         this.strMobType = "NULL";
/*  211 */         this.mobType = MythicEntity.getMythicEntity("SKELETON");
/*  212 */         this.displayName = PlaceholderString.of("ERROR: MOB TYPE FOR '" + this.internalName + "' IS NOT OPTIONAL");
/*      */         return;
/*      */       } 
/*  215 */       MythicLogger.debug(MythicLogger.DebugLevel.INFO, "+ EntityType is vanilla override for {0}", new Object[] { this.strMobType });
/*  216 */       this.mobType = me;
/*      */     } else {
/*      */       
/*  219 */       MythicMobs.inst().getMobManager(); this.strMobType = MobManager.convertMobTypeAliases(this.strMobType);
/*      */       
/*  221 */       if (this.strMobType.equalsIgnoreCase("MINIATUREPET") || this.strMobType.equalsIgnoreCase("MPET")) {
/*  222 */         if (MythicMobs.inst().getCompatibility().getMiniaturePets().isPresent()) {
/*      */           try {
/*  224 */             MPetCompat compat = MythicMobs.inst().getCompatibility().getMiniaturePets().get();
/*  225 */             compat.getClass(); this.mPetType = Optional.of(new MPetCompat.MiniaturePetType(compat, mc));
/*  226 */           } catch (Exception ex) {
/*  227 */             ex.printStackTrace();
/*      */           } 
/*      */         } else {
/*  230 */           MythicLogger.errorMobConfig(this, mc, "Could not load MiniaturePets type: MiniaturePets is not enabled.");
/*  231 */           this.mobType = MythicEntity.getMythicEntity(MythicEntityType.ZOMBIE);
/*      */         } 
/*      */       } else {
/*  234 */         this.mobType = MythicEntity.getMythicEntity(this.strMobType);
/*      */       } 
/*      */     } 
/*      */     
/*  238 */     if (this.mobType != null) {
/*  239 */       this.mobType.instantiate(mc);
/*  240 */     } else if (!this.mPetType.isPresent()) {
/*  241 */       MythicMobs.throwSevere("error-mythicmob-load-invalidtype", "Could not load MythicMob {0}! Invalid type specified.", new Object[] { this.internalName });
/*  242 */       this.strMobType = "NULL";
/*  243 */       this.mobType = MythicEntity.getMythicEntity("SKELETON");
/*  244 */       this.displayName = PlaceholderString.of("ERROR: MOB TYPE FOR '" + this.internalName + "' IS INVALID");
/*      */       
/*      */       return;
/*      */     } 
/*  248 */     String strDisplayName = mc.getString("Display", (this.displayName == null) ? null : this.displayName.toString());
/*  249 */     strDisplayName = mc.getString("DisplayName", strDisplayName);
/*      */     
/*  251 */     if (strDisplayName != null) {
/*  252 */       this.displayName = PlaceholderString.of(strDisplayName);
/*      */     }
/*      */     
/*  255 */     this.health = mc.getDouble("Health");
/*  256 */     this.damage = mc.getDouble("Damage", -1.0D);
/*      */     
/*  258 */     this.armor = mc.getDouble("Armor");
/*  259 */     this.armor = mc.getDouble("Armour", this.armor);
/*      */     
/*  261 */     this.optionInvincible = mc.getBoolean("Options.Invincible", false);
/*  262 */     this.optionInvincible = mc.getBoolean("Options.Invulnerable", this.optionInvincible);
/*      */     
/*  264 */     this.faction = mc.getString("Faction", null);
/*      */ 
/*      */     
/*  267 */     String mount = mc.getString("Mount", null);
/*  268 */     mount = mc.getString("Riding", mount);
/*  269 */     this.mount = Optional.ofNullable(mount);
/*      */ 
/*      */     
/*  272 */     String rider = mc.getString("Rider", null);
/*  273 */     rider = mc.getString("Passenger", rider);
/*  274 */     this.rider = Optional.ofNullable(rider);
/*      */     
/*  276 */     this.optionDespawn = mc.getBoolean("Despawn", MythicMobs.inst().getConfigManager().getDespawnMobsByDefault());
/*  277 */     this.optionDespawn = mc.getBoolean("Options.Despawn", this.optionDespawn);
/*      */     
/*  279 */     this.optionPersistent = mc.getBoolean("Persistent", false);
/*  280 */     this.optionPersistent = mc.getBoolean("Options.Persistent", this.optionPersistent);
/*      */     
/*  282 */     this.attrAttackSpeed = mc.getDouble("Options.AttackSpeed", 0.0D);
/*  283 */     this.attrMovementSpeed = mc.getDouble("Options.MovementSpeed", 0.0D);
/*  284 */     this.attrKnockbackResist = mc.getDouble("Options.KnockbackResistance", 0.0D);
/*  285 */     this.attrFollowRange = mc.getDouble("Options.FollowRange", 0.0D);
/*  286 */     this.attrAttackSpeed = mc.getDouble("Options.AttackSpeed", 0.0D);
/*      */     
/*  288 */     this.optionGlowing = mc.getBoolean("Options.Glowing", false);
/*  289 */     this.optionCollidable = mc.getBoolean("Options.Collidable", true);
/*  290 */     this.optionNoGravity = mc.getBoolean("Options.NoGravity", false);
/*  291 */     this.optionInteractable = mc.getBoolean("Options.Interactable", this.optionInteractable);
/*  292 */     this.optionSilent = mc.getBoolean("Options.Silent", this.optionSilent);
/*  293 */     this.optionNoAI = mc.getBoolean("Options.NoAI", this.optionNoAI);
/*  294 */     this.noDamageTicks = mc.getInt("Options.NoDamageTicks", 10) * 2;
/*      */     
/*  296 */     this.useBossBar = mc.getBoolean("BossBar.Enabled", false);
/*  297 */     this.bossBarTitle = mc.getPlaceholderString("BossBar.Title", (this.displayName == null) ? "" : this.displayName.toString());
/*  298 */     this.bossBarRange = mc.getInteger("BossBar.Range", 64);
/*  299 */     this.bossBarRangeSq = (int)Math.pow(this.bossBarRange, 2.0D);
/*      */     
/*  301 */     String bossBarColor = mc.getString("BossBar.Color", "WHITE");
/*  302 */     String bossBarStyle = mc.getString("BossBar.Style", "SOLID");
/*      */     
/*      */     try {
/*  305 */       this.bossBarColor = AbstractBossBar.BarColor.valueOf(bossBarColor);
/*  306 */     } catch (Exception ex) {
/*  307 */       this.bossBarColor = AbstractBossBar.BarColor.WHITE;
/*      */     } 
/*      */     
/*      */     try {
/*  311 */       this.bossBarStyle = AbstractBossBar.BarStyle.valueOf(bossBarStyle);
/*  312 */     } catch (Exception ex) {
/*  313 */       this.bossBarStyle = AbstractBossBar.BarStyle.SOLID;
/*      */     } 
/*  315 */     this.bossBarCreateFog = mc.getBoolean("BossBar.CreateFog", false);
/*  316 */     this.bossBarDarkenSky = mc.getBoolean("BossBar.DarkenSky", false);
/*  317 */     this.bossBarPlayMusic = mc.getBoolean("BossBar.PlayMusic", false);
/*      */     
/*  319 */     this.usesHealthBar = Boolean.valueOf(mc.getBoolean("HealthBar.Enabled", false));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  335 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "Loading mob skills... ", new Object[0]);
/*  336 */     List<String> nSkills = mc.getStringList("Skills");
/*      */ 
/*      */ 
/*      */     
/*  340 */     int interval = 1;
/*      */     
/*  342 */     for (String s : nSkills) {
/*  343 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "Loading mechanic line: {0}", new Object[] { s });
/*      */       
/*  345 */       s = MythicLineConfig.unparseBlock(s);
/*  346 */       SkillMechanic ms = MythicMobs.inst().getSkillManager().getSkillMechanic(s);
/*      */       
/*  348 */       if (ms != null) {
/*  349 */         if (s.contains("~onTimer")) {
/*  350 */           Pattern Rpattern = Pattern.compile("~onTimer:([0-9]+)");
/*  351 */           Matcher Rmatcher = Rpattern.matcher(s);
/*  352 */           Rmatcher.find();
/*      */           try {
/*  354 */             interval = Integer.parseInt(Rmatcher.group(1));
/*  355 */           } catch (Exception e) {
/*  356 */             MythicLogger.errorMobConfig(this, mc, "Error parsing Timer skill, invalid interval specified (must be an integer). AbstractSkill=" + s);
/*      */             continue;
/*      */           } 
/*  359 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Mechanic set on timer with interval {0}", new Object[] { Integer.valueOf(interval) });
/*  360 */           ms.setTimerInterval(interval);
/*  361 */           this.mTimerSkills.add(ms); continue;
/*  362 */         }  if (s.contains("~onSignal:")) {
/*  363 */           String signal; Pattern Rpattern = Pattern.compile("~onSignal:([a-zA-Z0-9_-]*)");
/*  364 */           Matcher Rmatcher = Rpattern.matcher(s);
/*  365 */           Rmatcher.find();
/*      */           
/*      */           try {
/*  368 */             signal = Rmatcher.group(1);
/*  369 */           } catch (Exception e) {
/*  370 */             MythicLogger.errorMobConfig(this, mc, "Error parsing Signal skill, invalid signal specified (contains invalid characters). AbstractSkill=" + s);
/*      */             continue;
/*      */           } 
/*  373 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Mechanic set on Signal with key '{0}'", new Object[] { signal });
/*  374 */           this.mSignalSkills.put(signal, ms); continue;
/*  375 */         }  if (s.contains("~onSignal")) {
/*  376 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Loading mechanic to signal skill tree...", new Object[0]);
/*  377 */           this.mGenericSignalSkills.add(ms); continue;
/*  378 */         }  if (s.contains("~onSpawn")) {
/*  379 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Loading mechanic to spawn skill tree...", new Object[0]);
/*  380 */           this.mSpawnSkills.add(ms); continue;
/*  381 */         }  if (s.contains("~onDeath")) {
/*  382 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Loading mechanic to death skill tree...", new Object[0]);
/*  383 */           this.mDeathSkills.add(ms); continue;
/*  384 */         }  if (s.contains("~onPlayerDeath")) {
/*  385 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Loading mechanic to kill skill tree...", new Object[0]);
/*  386 */           this.mPlayerDeathSkills.add(ms); continue;
/*  387 */         }  if (s.contains("~onReady") || s.contains("~onFirstSpawn")) {
/*  388 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Loading mechanic to spawner skill tree...", new Object[0]);
/*  389 */           this.mSpawnerReadySkills.add(ms); continue;
/*      */         } 
/*  391 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Loading mechanic to base skill tree...", new Object[0]);
/*  392 */         this.mSkills.add(ms);
/*      */         continue;
/*      */       } 
/*  395 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Mechanic was not found. Skipping.", new Object[0]);
/*      */     } 
/*      */     
/*  398 */     if (this.mTimerSkills.size() > 0) {
/*  399 */       this.usingTimers = true;
/*      */     }
/*      */ 
/*      */     
/*  403 */     this.legacySkills = mc.getStringList("LegacySkills");
/*      */     
/*  405 */     List<String> eSkills = new ArrayList<>();
/*  406 */     List<LegacyMythicTimerSkill> tSkills = new ArrayList<>();
/*      */     
/*  408 */     for (String skill : this.legacySkills) {
/*  409 */       if (skill.contains("~onTimer")) {
/*  410 */         Pattern Rpattern = Pattern.compile("~onTimer:([0-9]+)");
/*  411 */         Matcher Rmatcher = Rpattern.matcher(skill);
/*  412 */         Rmatcher.find();
/*      */         try {
/*  414 */           interval = Integer.parseInt(Rmatcher.group(1));
/*  415 */         } catch (Exception e) {
/*  416 */           MythicMobs.error("Error parsing Timer skill, invalid interval specified (must be an integer). AbstractSkill=" + skill);
/*      */           continue;
/*      */         } 
/*  419 */         tSkills.add(new LegacyMythicTimerSkill(skill, interval)); continue;
/*      */       } 
/*  421 */       eSkills.add(skill);
/*      */     } 
/*      */ 
/*      */     
/*  425 */     List<String> pSkills = new ArrayList<>();
/*  426 */     for (String skill : eSkills) {
/*  427 */       if (skill.contains("'")) {
/*  428 */         String[] split = skill.split("'");
/*  429 */         if (split.length > 2) {
/*  430 */           skill = split[0] + "'" + SkillString.unparseMessageSpecialChars(split[1]) + "'" + split[2];
/*      */         } else {
/*  432 */           skill = split[0] + "'" + SkillString.unparseMessageSpecialChars(split[1]) + "'";
/*      */         } 
/*      */       } 
/*  435 */       pSkills.add(skill);
/*      */     } 
/*      */     
/*  438 */     this.legacySkills = pSkills;
/*  439 */     this.legacyTimerSkills = tSkills;
/*      */     
/*  441 */     if (this.legacyTimerSkills.size() > 0) {
/*  442 */       this.usingTimers = true;
/*      */     }
/*      */     
/*  445 */     this.optionShowHealthInChat = mc.getBoolean("ShowHealth", false);
/*      */     
/*  447 */     this.useThreatTable = mc.getBoolean("Modules.ThreatTable", false);
/*  448 */     this.useImmunityTable = mc.getBoolean("Modules.ImmunityTable", false);
/*  449 */     this.useCustomNameplate = mc.getBoolean("Nameplate.Enabled", false);
/*      */     
/*  451 */     this.useThreatTable = mc.getBoolean("Options.UseThreatTable", this.useThreatTable);
/*  452 */     this.useImmunityTable = mc.getBoolean("Options.UseImmunityTable", this.useImmunityTable);
/*  453 */     this.useThreatTable = mc.getBoolean("ThreatTable.Enabled", this.useThreatTable);
/*  454 */     this.optionTTFromDamage = mc.getBoolean("ThreatTable.UseDamageTaken", true);
/*  455 */     this.optionTTDecayUnreachable = mc.getBoolean("ThreatTable.DecayUnreachable", true);
/*      */     
/*  457 */     this.maxAttackRange = mc.getInteger("Options.MaxAttackRange", 64);
/*  458 */     this.maxAttackableRange = mc.getInteger("Options.MaxCombatDistance", 256);
/*  459 */     this.maxAttackableRange = mc.getInteger("Options.MaxAttackableRange", this.maxAttackableRange);
/*  460 */     this.maxThreatDistance = mc.getInteger("Options.MaxThreatDistance", 40);
/*      */     
/*  462 */     this.alwaysShowName = mc.getBoolean("Options.AlwaysShowName", false);
/*  463 */     this.showNameOnDamage = mc.getBoolean("Options.ShowNameOnDamaged", false);
/*      */     
/*  465 */     this.repeatAllSkills = Boolean.valueOf(mc.getBoolean("Options.RepeatAllSkills", false));
/*  466 */     this.preventOtherDrops = Boolean.valueOf(mc.getBoolean("Options.PreventOtherDrops", MythicMobs.inst().getConfigManager().getPreventOtherDropsByDefault()));
/*  467 */     this.preventRandomEquipment = Boolean.valueOf(mc.getBoolean("Options.PreventRandomEquipment", false));
/*  468 */     this.preventLeashing = Boolean.valueOf(mc.getBoolean("Options.PreventLeashing", true));
/*  469 */     this.preventRename = Boolean.valueOf(mc.getBoolean("Options.PreventRenaming", true));
/*      */     
/*  471 */     this.preventSunburn = Boolean.valueOf(mc.getBoolean("Options.PreventSunburn", false));
/*  472 */     this.preventEndermanTeleport = Boolean.valueOf(mc.getBoolean("Options.PreventTeleport", false));
/*  473 */     this.preventEndermanTeleport = Boolean.valueOf(mc.getBoolean("Options.PreventTeleporting", this.preventEndermanTeleport.booleanValue()));
/*      */     
/*  475 */     this.preventTransformation = Boolean.valueOf(mc.getBoolean("Options.PreventTransformation", true));
/*  476 */     this.preventTransformation = Boolean.valueOf(mc.getBoolean("Options.PreventTransforming", this.preventTransformation.booleanValue()));
/*      */     
/*  478 */     this.preventItemPickup = Boolean.valueOf(mc.getBoolean("Options.PreventItemPickup", false));
/*  479 */     this.preventMobKillDrops = Boolean.valueOf(mc.getBoolean("Options.PreventMobKillDrops", false));
/*      */     
/*  481 */     this.passthroughDamage = Boolean.valueOf(mc.getBoolean("Options.PassthroughDamage", false));
/*      */     
/*  483 */     List<String> lstAIGoalSelectors = mc.getStringList("AIGoalSelectors");
/*  484 */     if (lstAIGoalSelectors != null) {
/*  485 */       for (String s : lstAIGoalSelectors) {
/*  486 */         s = MythicLineConfig.unparseBlock(s);
/*  487 */         this.aiGoalSelectors.add(s);
/*      */       } 
/*      */     }
/*  490 */     List<String> lstAITargetSelectors = mc.getStringList("AITargetSelectors");
/*  491 */     if (lstAITargetSelectors != null) {
/*  492 */       for (String s : lstAITargetSelectors) {
/*  493 */         s = MythicLineConfig.unparseBlock(s);
/*  494 */         this.aiTargetSelectors.add(s);
/*      */       } 
/*      */     }
/*      */     
/*  498 */     this.drops = mc.getStringList("Drops");
/*  499 */     this.dropTable = new DropTable(this.file, "Mob:" + this.internalName, this.drops, true);
/*      */     
/*  501 */     this.equipment = mc.getStringList("Equipment");
/*  502 */     this.equipmentTable = new DropTable(this.file, "MobEquipment:" + this.internalName, this.equipment, true);
/*      */ 
/*      */ 
/*      */     
/*  506 */     List<String> lstDamageMod = mc.getStringList("DamageModifiers");
/*  507 */     this.damageModifiers = new HashMap<>();
/*      */     
/*  509 */     if (lstDamageMod != null && lstDamageMod.size() > 0) {
/*  510 */       for (String dm : lstDamageMod) {
/*      */         try {
/*  512 */           String[] split = dm.split(" ");
/*  513 */           String type = split[0];
/*  514 */           double mod = Double.valueOf(split[1]).doubleValue();
/*      */           
/*  516 */           this.damageModifiers.put(type, Double.valueOf(mod));
/*  517 */         } catch (Exception ex) {
/*  518 */           MythicLogger.errorMobConfig(this, mc, "Invalid syntax for DamageModifier");
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  523 */     List<String> lstEntDamageMod = mc.getStringList("EntityDamageModifiers");
/*  524 */     this.entityDamageModifiers = new HashMap<>();
/*      */     
/*  526 */     if (lstEntDamageMod != null && lstEntDamageMod.size() > 0) {
/*  527 */       for (String dm : lstEntDamageMod) {
/*      */         try {
/*  529 */           String[] split = dm.split(" ");
/*  530 */           String type = split[0];
/*  531 */           double mod = Double.valueOf(split[1]).doubleValue();
/*      */           
/*  533 */           this.entityDamageModifiers.put(type, Double.valueOf(mod));
/*  534 */         } catch (Exception ex) {
/*  535 */           MythicLogger.errorMobConfig(this, mc, "Invalid syntax for DamageModifier");
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  540 */     this.equipment = mc.getStringList("Equipment");
/*      */     
/*  542 */     List<String> killMessages = mc.getStringList("KillMessages");
/*  543 */     if (killMessages != null && killMessages.size() > 0) {
/*  544 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "Loading mob kill messages...", new Object[0]);
/*  545 */       if (this.killMessages == null) {
/*  546 */         this.killMessages = new ArrayList<>();
/*      */       }
/*  548 */       killMessages.forEach(message -> this.killMessages.add(PlaceholderString.of(message)));
/*      */     } 
/*      */     
/*  551 */     this.lvlModDamage = mc.getDouble("LevelModifiers.Damage", -1.0D);
/*  552 */     this.lvlModHealth = mc.getDouble("LevelModifiers.Health", -1.0D);
/*  553 */     this.lvlModKBR = mc.getDouble("LevelModifiers.KnockbackResistance", -1.0D);
/*  554 */     this.lvlModPower = mc.getDouble("LevelModifiers.Power", -1.0D);
/*  555 */     this.lvlModArmor = mc.getDouble("LevelModifiers.Armor", -1.0D);
/*  556 */     this.lvlModSpeed = mc.getDouble("LevelModifiers.MovementSpeed", -1.0D);
/*  557 */     this.lvlModAttackSpeed = mc.getDouble("LevelModifiers.AttackSpeed", -1.0D);
/*      */     
/*      */     try {
/*  560 */       if (this.lvlModDamage < 0.0D) {
/*  561 */         if (ConfigManager.defaultLevelModifierDamage.startsWith("+")) {
/*  562 */           this.lvlModDamage = Double.valueOf(ConfigManager.defaultLevelModifierDamage.substring(1)).doubleValue();
/*  563 */         } else if (ConfigManager.defaultLevelModifierDamage.startsWith("*")) {
/*  564 */           this.lvlModDamage = this.damage * Double.valueOf(ConfigManager.defaultLevelModifierDamage.substring(1)).doubleValue();
/*      */         } else {
/*  566 */           this.lvlModDamage = this.damage * Double.valueOf(ConfigManager.defaultLevelModifierDamage).doubleValue();
/*      */         } 
/*      */       }
/*  569 */     } catch (Exception ex) {
/*  570 */       MythicMobs.throwSevere("error-mythicmob-config-badlevelmod-damage", "Error calculating Damage Level Modifier: Default configuration is bad.", new Object[0]);
/*  571 */       if (ConfigManager.debugLevel > 0) ex.printStackTrace();
/*      */     
/*      */     } 
/*      */     try {
/*  575 */       if (this.lvlModHealth < 0.0D) {
/*  576 */         if (ConfigManager.defaultLevelModifierHealth.startsWith("+")) {
/*  577 */           this.lvlModHealth = Double.valueOf(ConfigManager.defaultLevelModifierHealth.substring(1)).doubleValue();
/*  578 */         } else if (ConfigManager.defaultLevelModifierHealth.startsWith("*")) {
/*  579 */           this.lvlModHealth = this.health * Double.valueOf(ConfigManager.defaultLevelModifierHealth.substring(1)).doubleValue();
/*      */         } else {
/*  581 */           this.lvlModHealth = this.health * Double.valueOf(ConfigManager.defaultLevelModifierHealth).doubleValue();
/*      */         } 
/*      */       }
/*  584 */     } catch (Exception ex) {
/*  585 */       MythicMobs.throwSevere("error-mythicmob-config-badlevelmod-health", "Error calculating Health Level Modifier: Default configuration is bad.", new Object[0]);
/*  586 */       if (ConfigManager.debugLevel > 0) ex.printStackTrace();
/*      */     
/*      */     } 
/*      */     try {
/*  590 */       if (this.lvlModPower < 0.0D) {
/*  591 */         if (ConfigManager.defaultLevelModifierPower.startsWith("+")) {
/*  592 */           this.lvlModPower = Double.valueOf(ConfigManager.defaultLevelModifierPower.substring(1)).doubleValue();
/*  593 */         } else if (ConfigManager.defaultLevelModifierPower.startsWith("*")) {
/*  594 */           this.lvlModPower = Double.valueOf(ConfigManager.defaultLevelModifierPower.substring(1)).doubleValue();
/*      */         } else {
/*  596 */           this.lvlModPower = Double.valueOf(ConfigManager.defaultLevelModifierPower).doubleValue();
/*      */         } 
/*      */       }
/*  599 */     } catch (Exception ex) {
/*  600 */       MythicMobs.throwSevere("error-mythicmob-config-badlevelmod-power", "Error calculating Power Level Modifier: Default configuration is bad.", new Object[0]);
/*  601 */       if (ConfigManager.debugLevel > 0) ex.printStackTrace();
/*      */     
/*      */     } 
/*      */     try {
/*  605 */       if (this.lvlModArmor < 0.0D) {
/*  606 */         if (ConfigManager.defaultLevelModifierArmor.startsWith("+")) {
/*  607 */           this.lvlModArmor = Double.valueOf(ConfigManager.defaultLevelModifierArmor.substring(1)).doubleValue();
/*  608 */         } else if (ConfigManager.defaultLevelModifierArmor.startsWith("*")) {
/*  609 */           this.lvlModArmor = this.armor * Double.valueOf(ConfigManager.defaultLevelModifierArmor.substring(1)).doubleValue();
/*      */         } else {
/*  611 */           this.lvlModArmor = Double.valueOf(ConfigManager.defaultLevelModifierArmor).doubleValue();
/*      */         } 
/*      */       }
/*  614 */     } catch (Exception ex) {
/*  615 */       MythicMobs.throwSevere("error-mythicmob-config-badlevelmod-armor", "Error calculating Armor Level Modifier: Default configuration is bad.", new Object[0]);
/*  616 */       if (ConfigManager.debugLevel > 0) ex.printStackTrace();
/*      */     
/*      */     } 
/*      */     try {
/*  620 */       if (this.lvlModKBR < 0.0D) {
/*  621 */         if (ConfigManager.defaultLevelModifierKBR.startsWith("+")) {
/*  622 */           this.lvlModKBR = Double.valueOf(ConfigManager.defaultLevelModifierKBR.substring(1)).doubleValue();
/*  623 */         } else if (ConfigManager.defaultLevelModifierKBR.startsWith("*")) {
/*  624 */           this.lvlModKBR = this.attrKnockbackResist * Double.valueOf(ConfigManager.defaultLevelModifierKBR.substring(1)).doubleValue();
/*      */         } else {
/*  626 */           this.lvlModKBR = Double.valueOf(ConfigManager.defaultLevelModifierKBR).doubleValue();
/*      */         } 
/*      */       }
/*  629 */     } catch (Exception ex) {
/*  630 */       MythicMobs.throwSevere("error-mythicmob-config-badlevelmod-armor", "Error calculating KBR Level Modifier: Default configuration is bad.", new Object[0]);
/*  631 */       if (ConfigManager.debugLevel > 0) ex.printStackTrace();
/*      */     
/*      */     } 
/*  634 */     this.digOutOfGround = Boolean.valueOf(mc.getBoolean("Options.DigOutOfGround", false));
/*      */     
/*  636 */     String strSpawnVelocityX = mc.getString("SpawnModifiers.VelocityX", "0");
/*  637 */     String strSpawnVelocityY = mc.getString("SpawnModifiers.VelocityY", "0");
/*  638 */     String strSpawnVelocityZ = mc.getString("SpawnModifiers.VelocityZ", "0");
/*      */     
/*  640 */     if (strSpawnVelocityX.contains("to")) {
/*  641 */       String[] split = strSpawnVelocityX.split("to");
/*      */       
/*      */       try {
/*  644 */         this.spawnVelocityX = Double.valueOf(split[0]).doubleValue();
/*  645 */         this.spawnVelocityXMax = Double.valueOf(split[1]).doubleValue();
/*  646 */         this.spawnVelocityXRange = true;
/*  647 */       } catch (Exception ex) {
/*  648 */         this.spawnVelocityX = 0.0D;
/*  649 */         MythicMobs.throwSevere("error-mythicmob-load-invalidspawnmodifier-velocityx", "Error loading MythicMob {0}! Invalid value for SpawnModifier.VelocityX.", new Object[] { this.internalName });
/*      */       } 
/*      */     } else {
/*      */       try {
/*  653 */         this.spawnVelocityX = Double.valueOf(strSpawnVelocityX).doubleValue();
/*  654 */       } catch (Exception ex) {
/*  655 */         this.spawnVelocityX = 0.0D;
/*  656 */         MythicMobs.throwSevere("error-mythicmob-load-invalidspawnmodifier-velocityx", "Error loading MythicMob {0}! Invalid value for SpawnModifier.VelocityX.", new Object[] { this.internalName });
/*      */       } 
/*      */     } 
/*  659 */     if (strSpawnVelocityY.contains("to")) {
/*  660 */       String[] split = strSpawnVelocityY.split("to");
/*      */       
/*      */       try {
/*  663 */         this.spawnVelocityY = Double.valueOf(split[0]).doubleValue();
/*  664 */         this.spawnVelocityYMax = Double.valueOf(split[1]).doubleValue();
/*  665 */         this.spawnVelocityYRange = true;
/*  666 */       } catch (Exception ex) {
/*  667 */         this.spawnVelocityY = 0.0D;
/*  668 */         MythicMobs.throwSevere("error-mythicmob-load-invalidspawnmodifier-velocityy", "Error loading MythicMob {0}! Invalid value for SpawnModifier.VelocityY.", new Object[] { this.internalName });
/*      */       } 
/*      */     } else {
/*      */       try {
/*  672 */         this.spawnVelocityY = Double.valueOf(strSpawnVelocityY).doubleValue();
/*  673 */       } catch (Exception ex) {
/*  674 */         this.spawnVelocityY = 0.0D;
/*  675 */         MythicMobs.throwSevere("error-mythicmob-load-invalidspawnmodifier-velocityy", "Error loading MythicMob {0}! Invalid value for SpawnModifier.VelocityY.", new Object[] { this.internalName });
/*      */       } 
/*      */     } 
/*  678 */     if (strSpawnVelocityZ.contains("to")) {
/*  679 */       String[] split = strSpawnVelocityZ.split("to");
/*      */       
/*      */       try {
/*  682 */         this.spawnVelocityZ = Double.valueOf(split[0]).doubleValue();
/*  683 */         this.spawnVelocityZMax = Double.valueOf(split[1]).doubleValue();
/*  684 */         this.spawnVelocityZRange = true;
/*  685 */       } catch (Exception ex) {
/*  686 */         this.spawnVelocityZ = 0.0D;
/*  687 */         MythicMobs.throwSevere("error-mythicmob-load-invalidspawnmodifier-velocityz", "Error loading MythicMob {0}! Invalid value for SpawnModifier.VelocityZ.", new Object[] { this.internalName });
/*      */       } 
/*      */     } else {
/*      */       try {
/*  691 */         this.spawnVelocityZ = Double.valueOf(strSpawnVelocityZ).doubleValue();
/*  692 */       } catch (Exception ex) {
/*  693 */         this.spawnVelocityZ = 0.0D;
/*  694 */         MythicMobs.throwSevere("error-mythicmob-load-invalidspawnmodifier-velocityz", "Error loading MythicMob {0}! Invalid value for SpawnModifier.VelocityZ.", new Object[] { this.internalName });
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  699 */     this.disguise = mc.getString("Options.Disguise", this.disguise);
/*  700 */     this.disguise = mc.getString("Disguise.Type", this.disguise);
/*      */     
/*  702 */     if (this.disguise != null && 
/*  703 */       this.disguise.toUpperCase().contains("PLAYER")) {
/*  704 */       this.fakePlayer = true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MythicConfig getConfig() {
/*  713 */     return this.config;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActiveMob spawn(AbstractLocation location, int level) {
/*      */     Entity e;
/*  724 */     MythicMobs.inst().getMobManager(); MobManager.spawnflag = true;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  729 */       if (this.mPetType.isPresent()) {
/*  730 */         e = ((MPetCompat.MiniaturePetType)this.mPetType.get()).spawn(location);
/*  731 */       } else if (this.mobType != null) {
/*  732 */         e = this.mobType.spawn(this, BukkitAdapter.adapt(location));
/*      */       } else {
/*  734 */         return null;
/*      */       } 
/*  736 */     } catch (Exception ex) {
/*  737 */       MythicLogger.errorMobConfig(this, this.config, "Mob type may not be supported on this version of Minecraft. Enable debugging for more information.");
/*  738 */       MythicMobs.inst().getConfigManager(); if (ConfigManager.debugLevel > 0) {
/*  739 */         ex.printStackTrace();
/*      */       }
/*  741 */       return null;
/*  742 */     } catch (Error ex) {
/*  743 */       MythicLogger.errorMobConfig(this, this.config, "Mob type may not be supported on this version of Minecraft. Enable debugging for more information.");
/*  744 */       MythicMobs.inst().getConfigManager(); if (ConfigManager.debugLevel > 0) {
/*  745 */         ex.printStackTrace();
/*      */       }
/*  747 */       return null;
/*      */     } 
/*      */     
/*  750 */     MythicMobs.debug(1, "Calling MythicMobSpawnEvent for " + getInternalName() + " (level: " + level + ")");
/*  751 */     MythicMobSpawnEvent event = new MythicMobSpawnEvent(this, e, level);
/*      */     
/*  753 */     Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/*  755 */     if (event.isCancelled()) {
/*  756 */       e.remove();
/*  757 */       return null;
/*      */     } 
/*      */     
/*  760 */     level = event.getMobLevel();
/*      */     
/*  762 */     ActiveMob am = new ActiveMob(e.getUniqueId(), BukkitAdapter.adapt(e), this, level);
/*  763 */     MythicMobs.inst().getMobManager().registerActiveMob(am);
/*      */     
/*  765 */     Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
/*      */     
/*  767 */     Objective o = sb.getObjective("MythicMobType");
/*      */     
/*  769 */     if (o == null) {
/*  770 */       if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*  771 */         o = sb.registerNewObjective("MythicMobType", "dummy", "MythicMobs Mob Type");
/*      */       } else {
/*  773 */         o = sb.registerNewObjective("MythicMobType", "dummy");
/*      */       } 
/*      */     }
/*  776 */     o.getScore(am.getUniqueId().toString()).setScore(getInternalName().hashCode());
/*      */     
/*  778 */     am = applyMobOptions(am, level);
/*  779 */     am = applyMobVolatileOptions(am);
/*  780 */     am = applySpawnModifiers(am);
/*      */     
/*  782 */     if (getSkills() != null)
/*      */     {
/*  784 */       new TriggeredSkill(SkillTrigger.SPAWN, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/*      */     }
/*      */     
/*  787 */     MythicMobs.inst().getMobManager(); MobManager.spawnflag = false;
/*  788 */     return am;
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
/*      */   public ActiveMob applyMobOptions(ActiveMob am, int level) {
/*  800 */     Entity entity = am.getEntity().getBukkitEntity();
/*      */     
/*  802 */     if (am.getEntity().isLiving()) {
/*  803 */       LivingEntity asLiving = (LivingEntity)entity;
/*      */       
/*  805 */       if (!this.optionDespawn) {
/*  806 */         asLiving.setRemoveWhenFarAway(false);
/*      */       }
/*      */       
/*  809 */       if (this.alwaysShowName) {
/*  810 */         asLiving.setCustomNameVisible(true);
/*      */       }
/*      */       
/*  813 */       if (this.preventItemPickup.booleanValue()) {
/*  814 */         asLiving.setCanPickupItems(false);
/*      */       }
/*      */       
/*  817 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  818 */         if (this.damage > 0.0D && 
/*  819 */           asLiving.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
/*  820 */           asLiving.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(am.getDamage());
/*      */         }
/*      */         
/*  823 */         if (this.attrMovementSpeed != 0.0D) {
/*  824 */           asLiving.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(getMovementSpeed(level));
/*      */         }
/*  826 */         if (this.attrAttackSpeed != 0.0D && 
/*  827 */           asLiving.getAttribute(Attribute.GENERIC_ATTACK_SPEED) != null) {
/*  828 */           asLiving.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(getAttackSpeed(level));
/*      */         }
/*      */         
/*  831 */         if (this.attrFollowRange != 0.0D) {
/*  832 */           asLiving.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(this.attrFollowRange);
/*      */         }
/*  834 */         if (this.attrKnockbackResist != 0.0D) {
/*  835 */           asLiving.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(getKnockbackResistance(level));
/*      */         }
/*  837 */         if (!this.optionCollidable) {
/*  838 */           asLiving.setCollidable(false);
/*      */         }
/*      */       } else {
/*  841 */         if (this.attrMovementSpeed != 0.0D) {
/*  842 */           if (this.attrMovementSpeed == -1.0D) {
/*  843 */             MythicMobs.inst().getVolatileCodeHandler().setMobSpeed((Entity)asLiving, 0.0D);
/*      */           } else {
/*  845 */             MythicMobs.inst().getVolatileCodeHandler().setMobSpeed((Entity)asLiving, getMovementSpeed(level));
/*      */           } 
/*      */         }
/*  848 */         if (this.attrFollowRange != 0.0D) {
/*  849 */           MythicMobs.inst().getVolatileCodeHandler().setFollowRange((Entity)asLiving, this.attrFollowRange);
/*      */         }
/*  851 */         if (this.attrKnockbackResist > 0.0D) {
/*  852 */           MythicMobs.inst().getVolatileCodeHandler().setKnockBackResistance((Entity)asLiving, getKnockbackResistance(level));
/*      */         }
/*      */       } 
/*  855 */       if (this.damage > 0.0D && 
/*  856 */         CompatibilityManager.Heroes != null) {
/*  857 */         CompatibilityManager.Heroes.setMobDamage(asLiving, am.getDamage());
/*      */       }
/*      */ 
/*      */       
/*  861 */       if (this.health > 0.0D) {
/*  862 */         double health = this.health;
/*      */         
/*  864 */         if (level > 1 && 
/*  865 */           this.lvlModHealth > 0.0D) {
/*  866 */           health += this.lvlModHealth * (level - 1);
/*      */         }
/*      */         
/*      */         try {
/*  870 */           double fhealth = Math.ceil(health);
/*  871 */           asLiving.setMaxHealth(fhealth);
/*  872 */           asLiving.setHealth(fhealth);
/*      */           
/*  874 */           if (this.mPetType.isPresent()) {
/*  875 */             Schedulers.sync().runLater(() -> { if (!paramLivingEntity.isDead()) { paramLivingEntity.setMaxHealth(paramDouble); paramLivingEntity.setHealth(paramDouble); }  }20L);
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*  882 */         catch (IllegalArgumentException ex) {
/*  883 */           MythicLogger.errorMobConfig(this, this.config, "Mob HP is greater than server's maxHealth setting. Please modify spigot.yml and increase the maxHealth attribute to compensate.");
/*  884 */           if (ConfigManager.debugLevel > 0) ex.printStackTrace();
/*      */         
/*      */         } 
/*      */       } 
/*  888 */       asLiving.setMaximumNoDamageTicks(this.noDamageTicks);
/*      */       
/*  890 */       if (entity instanceof org.bukkit.entity.Creature) {
/*  891 */         if (this.preventRandomEquipment.booleanValue()) {
/*  892 */           EntityEquipment ee = asLiving.getEquipment();
/*      */           
/*  894 */           Schedulers.sync().runLater(() -> { paramEntityEquipment.setHelmet(new ItemStack(Material.STONE)); paramEntityEquipment.setChestplate(new ItemStack(Material.STONE)); paramEntityEquipment.setLeggings(new ItemStack(Material.STONE)); paramEntityEquipment.setBoots(new ItemStack(Material.STONE)); paramEntityEquipment.setItemInHand(new ItemStack(Material.STONE)); }1L);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  902 */           Schedulers.sync().runLater(() -> { paramEntityEquipment.setHelmet(new ItemStack(Material.AIR)); paramEntityEquipment.setChestplate(new ItemStack(Material.AIR)); paramEntityEquipment.setLeggings(new ItemStack(Material.AIR)); paramEntityEquipment.setBoots(new ItemStack(Material.AIR)); paramEntityEquipment.setItemInHand(new ItemStack(Material.AIR)); }2L);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  911 */         ActiveMob amm = am;
/*  912 */         Schedulers.sync().runLater(() -> { this.equipmentTable.generate(new DropMetadata((SkillCaster)paramActiveMob, paramActiveMob.getEntity())).equip(paramActiveMob.getEntity()); EntityEquipment ee = paramLivingEntity.getEquipment(); ee.setItemInHandDropChance(0.0F); ee.setHelmetDropChance(0.0F); ee.setChestplateDropChance(0.0F); ee.setLeggingsDropChance(0.0F); ee.setBootsDropChance(0.0F); if (this.useCustomNameplate) paramActiveMob.setShowCustomNameplate(true);  }5L);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  930 */       if (getDisplayName() != null) {
/*  931 */         asLiving.setCustomName(am.getDisplayName());
/*      */       }
/*      */     } 
/*      */     
/*  935 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  936 */       if (this.optionInvincible) {
/*  937 */         entity.setInvulnerable(true);
/*      */       }
/*  939 */       if (this.optionGlowing) {
/*  940 */         entity.setGlowing(true);
/*      */       }
/*      */     } 
/*      */     
/*  944 */     if (MythicMobs.inst().getMinecraftVersion() >= 10) {
/*  945 */       if (this.optionNoGravity) {
/*  946 */         entity.setGravity(false);
/*      */       }
/*  948 */       if (this.optionSilent) {
/*  949 */         entity.setSilent(true);
/*      */       }
/*      */     } 
/*      */     
/*  953 */     am.getEntity().setMetadata("mobname", getInternalName());
/*  954 */     am.getEntity().setMetadata("mythicmob", "true");
/*      */     
/*  956 */     if (this.mount.isPresent()) { MythicMobs.inst().getMobManager(); if (!MobManager.mountflag && 
/*  957 */         MythicMobs.inst().getMobManager().getMythicMob(this.mount.get()) != null) {
/*  958 */         MythicMobs.inst().getMobManager(); MobManager.mountflag = true;
/*      */         
/*  960 */         if (entity.getVehicle() != null) {
/*  961 */           entity.getVehicle().remove();
/*      */         }
/*  963 */         AbstractEntity mount = MythicMobs.inst().getMobManager().getMythicMob(this.mount.get()).spawn(am.getLocation(), level).getEntity();
/*      */         
/*  965 */         MythicMobs.inst().getMobManager(); MobManager.mountflag = false;
/*      */         
/*  967 */         mount.setPassenger(entity);
/*      */         
/*  969 */         ActiveMob mountInstance = MythicMobs.inst().getMobManager().getMythicMobInstance(mount);
/*  970 */         am.setMount(mountInstance);
/*  971 */         mountInstance.setParent((SkillCaster)am);
/*      */       }  }
/*      */ 
/*      */     
/*  975 */     if (this.rider.isPresent()) { MythicMobs.inst().getMobManager(); if (!MobManager.mountflag && 
/*  976 */         MythicMobs.inst().getMobManager().getMythicMob(this.rider.get()) != null) {
/*  977 */         MythicMobs.inst().getMobManager(); MobManager.mountflag = true;
/*  978 */         AbstractEntity rider = MythicMobs.inst().getMobManager().getMythicMob(this.rider.get()).spawn(am.getLocation(), level).getEntity();
/*  979 */         MythicMobs.inst().getMobManager(); MobManager.mountflag = false;
/*      */         
/*  981 */         rider.setPassenger(entity);
/*      */         
/*  983 */         ActiveMob riderInstance = MythicMobs.inst().getMobManager().getMythicMobInstance(rider);
/*  984 */         riderInstance.setMount(am);
/*  985 */         riderInstance.setParent((SkillCaster)am);
/*      */       }  }
/*      */ 
/*      */     
/*  989 */     return am;
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
/*      */   public ActiveMob applyMobVolatileOptions(ActiveMob am) {
/* 1001 */     Entity e = am.getEntity().getBukkitEntity();
/*      */     
/* 1003 */     if (hasFaction()) {
/* 1004 */       e.setMetadata("Faction", (MetadataValue)new FixedMetadataValue((Plugin)MythicMobs.inst(), getFaction()));
/*      */     }
/*      */     
/* 1007 */     if (am.getEntity().isCreature()) {
/* 1008 */       if (this.optionNoAI) {
/* 1009 */         if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 1010 */           ((LivingEntity)BukkitAdapter.adapt(am.getEntity())).setAI(false);
/*      */         } else {
/* 1012 */           MythicMobs.inst().getVolatileCodeHandler().setNoAI(am);
/*      */         } 
/*      */       }
/* 1015 */       if (ConfigManager.EnableAIModifiers) {
/* 1016 */         if (getAIGoalSelectors() != null) {
/* 1017 */           MythicMobs.inst().getVolatileCodeHandler().getAIHandler().addPathfinderGoals((LivingEntity)e, getAIGoalSelectors());
/*      */         }
/* 1019 */         if (getAITargetSelectors() != null) {
/* 1020 */           MythicMobs.inst().getVolatileCodeHandler().getAIHandler().addTargetGoals((LivingEntity)e, getAITargetSelectors());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1025 */     if (MythicMobs.inst().getMinecraftVersion() < 10 && 
/* 1026 */       this.optionSilent) {
/* 1027 */       MythicMobs.inst().getVolatileCodeHandler().setEntitySilent(e);
/*      */     }
/*      */ 
/*      */     
/* 1031 */     if (this.disguise != null && 
/* 1032 */       CompatibilityManager.LibsDisguises != null) {
/* 1033 */       CompatibilityManager.LibsDisguises.setDisguise(am, this.disguise);
/*      */     }
/*      */ 
/*      */     
/* 1037 */     return am;
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
/*      */   public ActiveMob applySpawnModifiers(ActiveMob am) {
/* 1049 */     Entity e = am.getEntity().getBukkitEntity();
/* 1050 */     Vector v = e.getVelocity();
/*      */     
/* 1052 */     if (this.spawnVelocityXRange) {
/* 1053 */       double vl = MythicMobs.r.nextDouble() * (this.spawnVelocityXMax - this.spawnVelocityX) + this.spawnVelocityX;
/* 1054 */       v.setX(vl);
/*      */     } else {
/* 1056 */       v.setX(this.spawnVelocityX);
/*      */     } 
/* 1058 */     if (this.spawnVelocityYRange) {
/* 1059 */       double vl = MythicMobs.r.nextDouble() * (this.spawnVelocityYMax - this.spawnVelocityY) + this.spawnVelocityY;
/* 1060 */       v.setY(vl);
/*      */     } else {
/* 1062 */       v.setY(this.spawnVelocityY);
/*      */     } 
/* 1064 */     if (this.spawnVelocityZRange) {
/* 1065 */       double vl = MythicMobs.r.nextDouble() * (this.spawnVelocityZMax - this.spawnVelocityZ) + this.spawnVelocityZ;
/* 1066 */       v.setZ(vl);
/*      */     } else {
/* 1068 */       v.setZ(this.spawnVelocityZ);
/*      */     } 
/*      */     
/* 1071 */     e.setVelocity(v);
/*      */     
/* 1073 */     return am;
/*      */   }
/*      */   
/*      */   public void executeSkills(SkillTrigger cause, SkillMetadata data) {
/* 1077 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "Running Mechanics for ActiveMob {0} (uuid: {1})", new Object[] { this.internalName, data.getCaster().getEntity().getUniqueId() });
/* 1078 */     if (data.getCaster().getEntity() == null || !data.getCaster().getEntity().getWorld().isLoaded()) {
/* 1079 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "! Mob is not loaded. Ignoring skills.", new Object[0]);
/*      */       return;
/*      */     } 
/* 1082 */     if (data.getIsAsync()) {
/* 1083 */       for (SkillMechanic ms : getSkills(cause)) {
/* 1084 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "+ Running Mechanics for ActiveMob '{0}' (uuid: {1})", new Object[] { this.internalName, data.getCaster().getEntity().getUniqueId() });
/* 1085 */         if (!ms.getRunAsync() && ms.isUsableFromCaster(data)) {
/* 1086 */           ms.execute(data.deepClone().setIsAsync(false));
/*      */         }
/*      */       } 
/* 1089 */       Schedulers.async().run(() -> {
/*      */             for (SkillMechanic ms : getSkills(paramSkillTrigger)) {
/*      */               MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "+ Evaluating SkillMechanic {0}", new Object[] { ms.getConfigLine() });
/*      */               if (ms.getRunAsync() && ms.isUsableFromCaster(paramSkillMetadata)) {
/*      */                 ms.execute(paramSkillMetadata);
/*      */               }
/*      */             } 
/*      */             if (ConfigManager.EnableLegacySkills && paramSkillMetadata.getCaster().getEntity().isLiving()) {
/*      */               LegacySkillHandler.ExecuteSkills(this.legacySkills, BukkitAdapter.adapt(paramSkillMetadata.getCaster().getEntity()), (LivingEntity)BukkitAdapter.adapt(paramSkillMetadata.getTrigger()), paramSkillMetadata.getCause());
/*      */             }
/*      */           });
/*      */     } else {
/* 1101 */       for (SkillMechanic ms : getSkills(cause)) {
/* 1102 */         if (ms.isUsableFromCaster(data)) {
/* 1103 */           ms.execute(data);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void executeSignalSkill(String signal, SkillMetadata data) {
/* 1110 */     if (this.mSignalSkills.containsKey(signal)) {
/* 1111 */       SkillMechanic ms = this.mSignalSkills.get(signal);
/* 1112 */       if (ms.isUsableFromCaster(data)) {
/* 1113 */         ms.execute(data);
/*      */       }
/*      */     } 
/* 1116 */     for (SkillMechanic ms : this.mGenericSignalSkills) {
/* 1117 */       if (ms.isUsableFromCaster(data)) ms.execute(data);
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getInternalName() {
/* 1126 */     return this.internalName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFile() {
/* 1134 */     return this.file;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PlaceholderString getDisplayName() {
/* 1142 */     return this.displayName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEntityType() {
/* 1150 */     return this.strMobType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MythicEntity getMythicEntity() {
/* 1159 */     return this.mobType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDespawns() {
/* 1167 */     return this.optionDespawn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPersistent() {
/* 1175 */     return this.optionPersistent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getBaseHealth() {
/* 1183 */     return this.health;
/*      */   }
/*      */   
/*      */   public void setBaseHealth(double health) {
/* 1187 */     this.config.set("Health", Double.valueOf(health));
/* 1188 */     this.config.save();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getPerLevelHealth() {
/* 1196 */     return this.lvlModHealth;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getBaseDamage() {
/* 1201 */     return this.damage;
/*      */   }
/*      */   public double getPerLevelDamage() {
/* 1204 */     return this.lvlModDamage;
/*      */   }
/*      */   public double getPerLevelPower() {
/* 1207 */     return this.lvlModPower;
/*      */   }
/*      */   public double getBaseArmor() {
/* 1210 */     return this.armor;
/*      */   }
/*      */   public double getPerLevelArmor() {
/* 1213 */     return this.lvlModArmor;
/*      */   }
/*      */   public double getMovementSpeed(int level) {
/* 1216 */     double attr = this.attrMovementSpeed;
/*      */     
/* 1218 */     if (level > 1 && 
/* 1219 */       this.lvlModSpeed > 0.0D) {
/* 1220 */       attr += this.lvlModSpeed * (level - 1);
/*      */     }
/*      */     
/* 1223 */     return attr;
/*      */   }
/*      */   public double getKnockbackResistance(int level) {
/* 1226 */     double attr = this.attrKnockbackResist;
/*      */     
/* 1228 */     if (level > 1 && 
/* 1229 */       this.lvlModKBR > 0.0D) {
/* 1230 */       attr += this.lvlModKBR * (level - 1);
/*      */     }
/*      */     
/* 1233 */     return attr;
/*      */   }
/*      */   public double getAttackSpeed(int level) {
/* 1236 */     double attr = this.attrAttackSpeed;
/*      */     
/* 1238 */     if (level > 1 && 
/* 1239 */       this.lvlModAttackSpeed > 0.0D) {
/* 1240 */       attr += this.lvlModAttackSpeed * (level - 1);
/*      */     }
/*      */     
/* 1243 */     return attr;
/*      */   }
/*      */   public boolean hasFaction() {
/* 1246 */     return (this.faction != null);
/*      */   }
/*      */   public String getFaction() {
/* 1249 */     return this.faction;
/*      */   }
/*      */   public double getHealth() {
/* 1252 */     return this.health;
/*      */   }
/*      */   public boolean getIsInvincible() {
/* 1255 */     return this.optionInvincible;
/*      */   }
/*      */   public boolean usesThreatTable() {
/* 1258 */     return this.useThreatTable;
/*      */   }
/*      */   public boolean usesImmunityTable() {
/* 1261 */     return this.useImmunityTable;
/*      */   }
/*      */   public boolean getThreatTableUseDamageTaken() {
/* 1264 */     return this.optionTTFromDamage;
/*      */   }
/*      */   public boolean getThreatTableDecaysUnreachable() {
/* 1267 */     return this.optionTTDecayUnreachable;
/*      */   }
/*      */   public List<String> getSkills() {
/* 1270 */     return this.legacySkills;
/*      */   }
/*      */   public List<String> getLevelModifiers() {
/* 1273 */     return this.levelmods;
/*      */   }
/*      */   public List<String> getAIGoalSelectors() {
/* 1276 */     return this.aiGoalSelectors;
/*      */   }
/*      */   public List<String> getAITargetSelectors() {
/* 1279 */     return this.aiTargetSelectors;
/*      */   }
/*      */   public Queue<SkillMechanic> getSkills(SkillTrigger st) {
/* 1282 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$SkillTrigger[st.ordinal()]) {
/*      */       case 1:
/* 1284 */         return this.mSpawnSkills;
/*      */       case 2:
/* 1286 */         return this.mDeathSkills;
/*      */       case 3:
/* 1288 */         return this.mPlayerDeathSkills;
/*      */       case 4:
/* 1290 */         return this.mSpawnerReadySkills;
/*      */     } 
/* 1292 */     return this.mSkills;
/*      */   }
/*      */   
/*      */   public Queue<SkillMechanic> getTimerSkills() {
/* 1296 */     return this.mTimerSkills;
/*      */   }
/*      */   public boolean hasKillMessages() {
/* 1299 */     return (this.killMessages != null && this.killMessages.size() > 0);
/*      */   }
/*      */   public PlaceholderString getKillMessage() {
/* 1302 */     if (!hasKillMessages()) return null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1310 */     return this.killMessages.get(MythicMobs.r.nextInt(this.killMessages.size()));
/*      */   }
/*      */   public double getSpawnVelocityX() {
/* 1313 */     return this.spawnVelocityX;
/*      */   }
/*      */   public double getSpawnVelocityY() {
/* 1316 */     return this.spawnVelocityY;
/*      */   }
/*      */   public double getSpawnVelocityZ() {
/* 1319 */     return this.spawnVelocityZ;
/*      */   }
/*      */   
/*      */   public boolean getIsInteractable() {
/* 1323 */     return this.optionInteractable;
/*      */   }
/*      */   
/*      */   public boolean usesBossBar() {
/* 1327 */     return this.useBossBar;
/*      */   }
/*      */   public int getBossBarRangeSquared() {
/* 1330 */     return this.bossBarRangeSq;
/*      */   }
/*      */   public Optional<AbstractBossBar> getBossBar() {
/* 1333 */     if (!this.useBossBar) {
/* 1334 */       return Optional.empty();
/*      */     }
/*      */     
/* 1337 */     AbstractBossBar bar = MythicMobs.inst().server().createBossBar(" ", this.bossBarColor, this.bossBarStyle);
/*      */     
/* 1339 */     bar.setProgress(1.0D);
/*      */     
/* 1341 */     if (this.bossBarCreateFog) {
/* 1342 */       bar.setCreateFog(true);
/*      */     }
/* 1344 */     if (this.bossBarDarkenSky) {
/* 1345 */       bar.setDarkenSky(true);
/*      */     }
/* 1347 */     if (this.bossBarPlayMusic) {
/* 1348 */       bar.setPlayBossMusic(true);
/*      */     }
/* 1350 */     return Optional.of(bar);
/*      */   }
/*      */   public PlaceholderString getBossBarTitle() {
/* 1353 */     return this.bossBarTitle;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/* 1358 */     if (o instanceof MythicMob) {
/* 1359 */       return ((MythicMob)o).getInternalName().equals(this.internalName);
/*      */     }
/* 1361 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1366 */     return "MythicMob{" + this.internalName + "}";
/*      */   }
/*      */ 
/*      */   
/*      */   public int compareTo(MythicMob mm) {
/* 1371 */     return this.internalName.compareTo(mm.getInternalName());
/*      */   }
/*      */   
/*      */   public boolean getShowHealthInChat() {
/* 1375 */     return this.optionShowHealthInChat;
/*      */   }
/*      */   
/*      */   public boolean getShowNameOnDamaged() {
/* 1379 */     return this.showNameOnDamage;
/*      */   }
/*      */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\MythicMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */