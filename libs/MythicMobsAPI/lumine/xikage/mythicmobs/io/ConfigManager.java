/*     */ package lumine.xikage.mythicmobs.io;
/*     */ 
/*     */ import io.lumine.utils.logging.ConsoleColor;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.WorldScaling;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import java.util.HashSet;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigManager
/*     */ {
/*     */   private final MythicMobs core;
/*     */   private IOLoader<MythicMobs> settings;
/*     */   private MythicConfig config;
/*  21 */   public static int ClockInterval = 1;
/*  22 */   public static int debugLevel = -1;
/*     */   public static boolean debugMode = false;
/*     */   public static boolean debugSpawners = false;
/*     */   public static boolean errorLogging = true;
/*  26 */   public static int SaveInteval = 300;
/*  27 */   public static int SpawningInterval = 1;
/*     */   public static int timerupdate;
/*     */   public static int walkupdate;
/*  30 */   public static int ShowHealthRadius = 25;
/*  31 */   public static int ScanInterval = 10;
/*  32 */   public static int ClnrInterval = 600;
/*     */   public static boolean AllowMetrics = true;
/*     */   public static boolean UseCompatibilityMode = false;
/*     */   public static boolean UseVolatileFeatures = true;
/*  36 */   public static String ShowHealthFormat = "$mobname: $mobhp / $mobmaxhp";
/*  37 */   public static String KillMessagePrefix = "";
/*     */   public static boolean AllowUpdateChecking = true;
/*  39 */   public static int SpawnerRange = 40;
/*     */   
/*     */   private boolean mobsDespawnByDefault = false;
/*     */   
/*     */   private boolean preventOtherDropsByDefault = false;
/*     */   
/*     */   public static boolean EnableAIModifiers = true;
/*     */   
/*     */   public static boolean EnableTimerSkills = true;
/*     */   
/*     */   public static boolean EnableThreatTables = true;
/*     */   public static boolean EnablePlayerFactions = true;
/*     */   public static boolean EnableLegacySkills = true;
/*     */   public static String defaultLevelModifierHealth;
/*     */   public static String defaultLevelModifierDamage;
/*     */   public static String defaultLevelModifierPower;
/*     */   public static String defaultLevelModifierArmor;
/*     */   public static String defaultLevelModifierKBR;
/*     */   public static boolean apiUseDeathEvent = true;
/*     */   public static boolean apiUseSkillEvent = true;
/*     */   public static boolean apiUseCustomSkillEvent = true;
/*     */   public static boolean compatHeroesXPEnable = false;
/*     */   public static boolean compatHeroesShowXPMessage = true;
/*  62 */   public static String compatHeroesXPMessageFormat = "";
/*     */   public static boolean compatSkillAPIShowXPMessage = true;
/*  64 */   public static String compatSkillAPIXPMessageFormat = "";
/*     */   public static boolean compatVaultShowMoneyMessage = true;
/*  66 */   public static String compatVaultMoneyMessageFormat = "";
/*     */   public static boolean compatMcMMOShowXPMessage = true;
/*  68 */   public static String compatMcMMOXPMessageFormat = "";
/*     */   
/*     */   private static boolean rsGeneratePoints = false;
/*  71 */   private static int rsMaxMobsPerChunk = 20;
/*  72 */   private static int rsPlayerRadius = 64;
/*  73 */   private static int rsPlayerRadiusY = 16;
/*  74 */   private static int rsPointsPerSecondLand = 10;
/*  75 */   private static int rsPointsPerSecondAir = 0;
/*  76 */   private static int rsPointsPerSecondSea = 1;
/*  77 */   private static int rsPointsPerSecondLava = 0;
/*  78 */   private static int rsPointsPerSecondGround = 0; private static boolean rsDespawnVanillaOutsideRadius = false;
/*  79 */   private static int rsMaxGenerationTimeMillis = 20; private static boolean vanillaSpawnsDisabled = false;
/*     */   public static boolean isVanillaSpawnsDisabled() {
/*  81 */     return vanillaSpawnsDisabled;
/*     */   }
/*     */   public void SaveAll() {
/*  84 */     this.core.getMobManager().saveCachedActiveMobs();
/*  85 */     this.core.getPlayerManager().saveAll();
/*  86 */     this.core.getSpawnerManager().saveSpawners();
/*     */   }
/*     */   public void ResetAll() {
/*  89 */     this.core.getSkillManager().getLegacySkills().clear();
/*     */   }
/*     */   public void LoadAll(boolean msg) {
/*  92 */     this.settings = new IOLoader((JavaPlugin)this.core, "config.yml");
/*     */     
/*  94 */     loadSettings();
/*  95 */     this.core.getItemManager().loadItems();
/*  96 */     this.core.getSkillManager().loadSkills();
/*  97 */     this.core.getMobManager().loadMobs();
/*     */     
/*  99 */     this.core.getDropManager().loadDropTables();
/* 100 */     this.core.getRandomSpawningManager().loadRandomSpawns();
/* 101 */     this.core.getSpawnerManager().loadSpawners();
/*     */     
/* 103 */     this.core.getSkillManager().runSecondPass();
/* 104 */     this.core.getDropManager().runSecondPass();
/*     */     
/* 106 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getMobManager().getMobTypes().size() + " mobs.");
/* 107 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getMobManager().getVanillaTypes().size() + " vanilla mob overrides.");
/* 108 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getMobManager().getMobStacks().size() + " mob stacks.");
/* 109 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getSkillManager().getSkills().size() + " skills.");
/* 110 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getRandomSpawningManager().getNumberOfSpawners() + " random spawns.");
/* 111 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getItemManager().getItems().size() + " mythic items.");
/* 112 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getDropManager().getDropTables().size() + " drop tables.");
/* 113 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + ConsoleColor.WHITE + " Loaded " + MythicMobs.inst().getSpawnerManager().getSpawners().size() + " mob spawners.");
/*     */   }
/*     */   
/*     */   public ConfigManager(MythicMobs core) {
/* 117 */     this.core = core;
/*     */   }
/*     */   
/*     */   public void loadSettings() {
/* 121 */     loadLegacySettings();
/*     */     
/* 123 */     MythicMobs.debug(1, "* Checking for Settings...");
/* 124 */     if (!this.settings.getCustomConfig().contains("Configuration.Version"))
/*     */       return; 
/* 126 */     MythicMobs.debug(1, "** Loading Settings...");
/* 127 */     MythicConfig mc = new MythicConfig("Configuration", this.settings.getCustomConfig());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     AllowMetrics = mc.getBoolean("General.AllowMetrics", true);
/* 133 */     if (debugLevel == -1) {
/* 134 */       debugLevel = mc.getInteger("General.DebugLevel", 0);
/*     */     }
/* 136 */     errorLogging = mc.getBoolean("General.ErrorLogging", true);
/* 137 */     UseCompatibilityMode = mc.getBoolean("General.Compatibilitymode", false);
/* 138 */     UseVolatileFeatures = mc.getBoolean("General.UseVolatileFeatures", true);
/* 139 */     AllowUpdateChecking = mc.getBoolean("General.CheckForUpdates", true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     int i = mc.getInteger("Clock.SaveInterval", 5);
/* 145 */     SaveInteval = i * 60;
/* 146 */     ClockInterval = mc.getInteger("Clock.ClockInterval", 5);
/* 147 */     SpawningInterval = mc.getInteger("Clock.SpawnsInterval", 2);
/* 148 */     ScanInterval = mc.getInteger("Clock.ScannerInterval", 10);
/* 149 */     ClnrInterval = mc.getInteger("Clock.CleanupInterval", 600);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     this.mobsDespawnByDefault = mc.getBoolean("Mobs.DespawnByDefault", true);
/* 155 */     this.preventOtherDropsByDefault = mc.getBoolean("Mobs.PreventOtherDropsByDefault", false);
/* 156 */     EnableAIModifiers = mc.getBoolean("Mobs.EnableAIModifiers", true);
/* 157 */     EnableTimerSkills = mc.getBoolean("Mobs.EnableTimerSkills", true);
/* 158 */     EnableLegacySkills = mc.getBoolean("Mobs.EnableLegacySkills", false);
/* 159 */     EnableThreatTables = mc.getBoolean("Mobs.EnableThreatTables", true);
/* 160 */     EnablePlayerFactions = mc.getBoolean("Mobs.EnablePlayerFactions", true);
/*     */     
/* 162 */     ShowHealthRadius = mc.getInteger("Mobs.ShowHealth.Radius", 25);
/* 163 */     ShowHealthFormat = mc.getString("Mobs.ShowHealth.Format", ShowHealthFormat);
/* 164 */     KillMessagePrefix = SkillString.parseMessageSpecialChars(mc.getString("Mobs.KillMessagePrefix", KillMessagePrefix));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     rsGeneratePoints = mc.getBoolean("RandomSpawning.GenerateSpawnPoints", false);
/* 170 */     rsMaxMobsPerChunk = mc.getInteger("RandomSpawning.MaxMobsPerChunk", 2);
/* 171 */     rsPlayerRadius = mc.getInteger("RandomSpawning.SpawnRadiusPerPlayer", 64);
/* 172 */     rsPlayerRadiusY = mc.getInteger("RandomSpawning.SpawnRadiusPerPlayerY", 16);
/* 173 */     rsPointsPerSecondLand = mc.getInteger("RandomSpawning.PointsPerSecond.Land", 10);
/* 174 */     rsPointsPerSecondAir = mc.getInteger("RandomSpawning.PointsPerSecond.Air", 1);
/* 175 */     rsPointsPerSecondSea = mc.getInteger("RandomSpawning.PointsPerSecond.Sea", 1);
/* 176 */     rsPointsPerSecondLava = mc.getInteger("RandomSpawning.PointsPerSecond.Lava", 0);
/* 177 */     rsPointsPerSecondGround = mc.getInteger("RandomSpawning.PointsPerSecond.Ground", 0);
/* 178 */     rsDespawnVanillaOutsideRadius = mc.getBoolean("RandomSpawning.DespawnLazyRandomMobs", true);
/* 179 */     rsMaxGenerationTimeMillis = mc.getInteger("RandomSpawning.MaxGenerationTime", 20);
/* 180 */     vanillaSpawnsDisabled = mc.getBoolean("RandomSpawning.DisableVanillaSpawns", false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     compatHeroesXPEnable = mc.getBoolean("Compatibility.Heroes.Enabled", compatHeroesXPEnable);
/* 186 */     compatHeroesShowXPMessage = mc.getBoolean("Compatibility.Heroes.ShowXPMessage", compatHeroesShowXPMessage);
/* 187 */     compatHeroesXPMessageFormat = mc.getString("Compatibility.Heroes.XPMessageFormat", compatHeroesXPMessageFormat);
/*     */     
/* 189 */     compatMcMMOShowXPMessage = mc.getBoolean("Compatibility.McMMO.ShowXPMessage", compatMcMMOShowXPMessage);
/* 190 */     compatMcMMOXPMessageFormat = mc.getString("Compatibility.McMMO.XPMessageFormat", compatMcMMOXPMessageFormat);
/*     */     
/* 192 */     compatSkillAPIShowXPMessage = mc.getBoolean("Compatibility.SkillAPI.ShowXPMessage", compatSkillAPIShowXPMessage);
/* 193 */     compatSkillAPIXPMessageFormat = mc.getString("Compatibility.SkillAPI.XPMessageFormat", compatSkillAPIXPMessageFormat);
/*     */     
/* 195 */     compatVaultShowMoneyMessage = mc.getBoolean("Compatibility.Vault.ShowMoneyMessage", compatVaultShowMoneyMessage);
/* 196 */     compatVaultMoneyMessageFormat = mc.getString("Compatibility.Vault.MoneyMessageFormat", compatVaultMoneyMessageFormat);
/*     */     
/* 198 */     compatHeroesXPMessageFormat = SkillString.convertLegacyVariables(compatHeroesXPMessageFormat);
/* 199 */     compatMcMMOXPMessageFormat = SkillString.convertLegacyVariables(compatMcMMOXPMessageFormat);
/* 200 */     compatSkillAPIXPMessageFormat = SkillString.convertLegacyVariables(compatSkillAPIXPMessageFormat);
/* 201 */     compatVaultMoneyMessageFormat = SkillString.convertLegacyVariables(compatVaultMoneyMessageFormat);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     MythicMobs.debug(1, "** Loading WorldScaling Settings...");
/* 207 */     WorldScaling.reset();
/* 208 */     if (this.settings.getCustomConfig().contains("Configuration.Mobs.Scaling")) {
/* 209 */       for (String world : this.settings.getCustomConfig().getConfigurationSection("Configuration.Mobs.Scaling").getKeys(false)) {
/* 210 */         MythicMobs.debug(1, "*** Loading WorldScaling for world " + world);
/* 211 */         MythicConfig wmc = new MythicConfig("Configuration.Mobs.Scaling." + world, this.settings.getCustomConfig());
/*     */         
/* 213 */         WorldScaling.initialize(world, wmc);
/*     */       } 
/*     */     }
/* 216 */     MythicMobs.debug(1, "** Finished Load WorldScaling Settings!");
/*     */     
/* 218 */     MythicMobs.debug(1, "** Loading Default Level Modifiers...");
/* 219 */     defaultLevelModifierHealth = mc.getString("Mobs.DefaultLevelModifiers.Health", "0.1");
/* 220 */     defaultLevelModifierDamage = mc.getString("Mobs.DefaultLevelModifiers.Damage", "0");
/* 221 */     defaultLevelModifierPower = mc.getString("Mobs.DefaultLevelModifiers.Power", "0");
/* 222 */     defaultLevelModifierArmor = mc.getString("Mobs.DefaultLevelModifiers.Armor", "0");
/* 223 */     defaultLevelModifierKBR = mc.getString("Mobs.DefaultLevelModifiers.KnockbackResistance", "0");
/*     */     
/* 225 */     MythicMobs.debug(1, "**** Default Level Modifiers - Health: " + defaultLevelModifierHealth);
/* 226 */     MythicMobs.debug(1, "**** Default Level Modifiers - Damage: " + defaultLevelModifierDamage);
/* 227 */     MythicMobs.debug(1, "**** Default Level Modifiers - Power: " + defaultLevelModifierPower);
/* 228 */     MythicMobs.debug(1, "**** Default Level Modifiers - Armor: " + defaultLevelModifierArmor);
/* 229 */     MythicMobs.debug(1, "**** Default Level Modifiers - KBR: " + defaultLevelModifierKBR);
/* 230 */     MythicMobs.debug(1, "** Finished Load Default Level Modifiers!");
/*     */   }
/*     */   
/*     */   public MythicConfig getConfig() {
/* 234 */     return this.config;
/*     */   }
/*     */   
/*     */   public void loadLegacySettings() {
/* 238 */     MythicMobs.debug(1, "* Checking for Legacy Settings...");
/* 239 */     if (this.settings.getCustomConfig().contains("general.debug-level")) {
/* 240 */       MythicMobs.debug(1, "** Loading Legacy Settings...");
/*     */ 
/*     */ 
/*     */       
/* 244 */       if (debugLevel == 0) {
/* 245 */         debugLevel = this.settings.getCustomConfig().getInt("general.debug-level", 0);
/*     */       }
/* 247 */       errorLogging = this.settings.getCustomConfig().getBoolean("general.error-logging", true);
/* 248 */       int i = this.settings.getCustomConfig().getInt("general.save-interval", 5);
/* 249 */       SaveInteval = i * 60;
/* 250 */       ClockInterval = this.settings.getCustomConfig().getInt("general.clock-interval", ClockInterval);
/* 251 */       SpawningInterval = this.settings.getCustomConfig().getInt("general.spawns-interval", SpawningInterval);
/* 252 */       ScanInterval = this.settings.getCustomConfig().getInt("general.scanner-interval", ScanInterval);
/* 253 */       ClnrInterval = this.settings.getCustomConfig().getInt("general.cleanup-interval", ClnrInterval);
/* 254 */       EnableTimerSkills = this.settings.getCustomConfig().getBoolean("mobs.enable-timer-skills", EnableTimerSkills);
/* 255 */       EnableLegacySkills = this.settings.getCustomConfig().getBoolean("mobs.enable-legacy-skills", EnableLegacySkills);
/* 256 */       EnableThreatTables = this.settings.getCustomConfig().getBoolean("mobs.enable-threat-tables", EnableThreatTables);
/* 257 */       EnablePlayerFactions = this.settings.getCustomConfig().getBoolean("enable-player-factions", EnablePlayerFactions);
/*     */       
/* 259 */       ShowHealthRadius = this.settings.getCustomConfig().getInt("mobs.show-health-radius", ShowHealthRadius);
/* 260 */       ShowHealthFormat = this.settings.getCustomConfig().getString("mobs.show-health-format", ShowHealthFormat);
/* 261 */       KillMessagePrefix = this.settings.getCustomConfig().getString("mobs.kill-message-prefix", KillMessagePrefix);
/*     */       
/* 263 */       UseCompatibilityMode = this.settings.getCustomConfig().getBoolean("general.compatibility-mode", UseCompatibilityMode);
/* 264 */       UseVolatileFeatures = this.settings.getCustomConfig().getBoolean("general.use-volatile-features");
/* 265 */       AllowUpdateChecking = this.settings.getCustomConfig().getBoolean("general.check-for-updates");
/* 266 */       AllowMetrics = this.settings.getCustomConfig().getBoolean("metrics.allow");
/*     */       
/* 268 */       compatHeroesXPEnable = this.settings.getCustomConfig().getBoolean("compatibility.heroes-xp-enable", compatHeroesXPEnable);
/* 269 */       compatHeroesShowXPMessage = this.settings.getCustomConfig().getBoolean("compatibility.heroes-show-xp-message", compatHeroesShowXPMessage);
/* 270 */       compatHeroesXPMessageFormat = this.settings.getCustomConfig().getString("compatibility.heroes-xp-message-format", compatHeroesXPMessageFormat);
/* 271 */       compatMcMMOShowXPMessage = this.settings.getCustomConfig().getBoolean("compatibility.mcmmo-show-xp-message", compatMcMMOShowXPMessage);
/* 272 */       compatMcMMOXPMessageFormat = this.settings.getCustomConfig().getString("compatibility.mcmmo-xp-message-format", compatMcMMOXPMessageFormat);
/* 273 */       compatSkillAPIShowXPMessage = this.settings.getCustomConfig().getBoolean("compatibility.skillapi-show-xp-message", compatSkillAPIShowXPMessage);
/* 274 */       compatSkillAPIXPMessageFormat = this.settings.getCustomConfig().getString("compatibility.skillapi-xp-message-format", compatSkillAPIXPMessageFormat);
/* 275 */       compatVaultShowMoneyMessage = this.settings.getCustomConfig().getBoolean("compatibility.vault-show-money-message", compatVaultShowMoneyMessage);
/* 276 */       compatVaultMoneyMessageFormat = this.settings.getCustomConfig().getString("compatibility.vault-money-message-format", compatVaultMoneyMessageFormat);
/*     */       
/* 278 */       compatHeroesXPMessageFormat = SkillString.convertLegacyVariables(compatHeroesXPMessageFormat);
/* 279 */       compatMcMMOXPMessageFormat = SkillString.convertLegacyVariables(compatMcMMOXPMessageFormat);
/* 280 */       compatSkillAPIXPMessageFormat = SkillString.convertLegacyVariables(compatSkillAPIXPMessageFormat);
/* 281 */       compatVaultMoneyMessageFormat = SkillString.convertLegacyVariables(compatVaultMoneyMessageFormat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean generateRSPoints() {
/* 288 */     return rsGeneratePoints;
/*     */   }
/*     */   public static int getRSMaxMobsPerChunk() {
/* 291 */     return rsMaxMobsPerChunk;
/*     */   }
/*     */   public static int getRSPlayerRadius() {
/* 294 */     return rsPlayerRadius;
/*     */   }
/*     */   public static int getRSPlayerRadiusY() {
/* 297 */     return rsPlayerRadiusY;
/*     */   }
/*     */   public static int getRSPointsPerSecondLand() {
/* 300 */     return rsPointsPerSecondLand;
/*     */   }
/*     */   public static int getRSPointsPerSecondAir() {
/* 303 */     return rsPointsPerSecondAir;
/*     */   }
/*     */   public static int getRSPointsPerSecondSea() {
/* 306 */     return rsPointsPerSecondSea;
/*     */   }
/*     */   public static int getRSPointsPerSecondLava() {
/* 309 */     return rsPointsPerSecondLava;
/*     */   }
/*     */   public static int getRSPointsPerSecondGround() {
/* 312 */     return rsPointsPerSecondGround;
/*     */   }
/*     */   public static int getRSMaxGenerationTimeMillis() {
/* 315 */     return rsMaxGenerationTimeMillis;
/*     */   }
/*     */   public static boolean getRSDespawnVanillaOutsideRadius() {
/* 318 */     return rsDespawnVanillaOutsideRadius;
/*     */   }
/*     */   public boolean getDespawnMobsByDefault() {
/* 321 */     return this.mobsDespawnByDefault;
/*     */   }
/*     */   public boolean getPreventOtherDropsByDefault() {
/* 324 */     return this.preventOtherDropsByDefault;
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
/* 335 */   private static HashSet<Material> transparentBlocks = new HashSet<>();
/*     */   
/*     */   static {
/* 338 */     for (Material m : Material.values()) {
/* 339 */       if (m.isTransparent() || !m.isSolid() || !m.isOccluding()) {
/* 340 */         transparentBlocks.add(m);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public HashSet<Material> getTransparentBlocks() {
/* 346 */     return transparentBlocks;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\io\ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */