/*     */ package lumine.xikage.mythicmobs;
/*     */ import io.lumine.utils.logging.ConsoleColor;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.utils.terminable.TerminableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.adapters.ServerInterface;
/*     */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.WorldManager;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitServer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitSkillTriggers;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.listeners.ChunkListeners;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.listeners.EggListeners;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.listeners.MobListeners;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
/*     */ import io.lumine.xikage.mythicmobs.clock.MythicMobsAsyncClock;
/*     */ import io.lumine.xikage.mythicmobs.clock.TimingsHandler;
/*     */ import io.lumine.xikage.mythicmobs.commands.BaseCommand;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropManager;
/*     */ import io.lumine.xikage.mythicmobs.holograms.HologramManager;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.items.ItemManager;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.metrics.MetricsLite;
/*     */ import io.lumine.xikage.mythicmobs.metrics.bStats;
/*     */ import io.lumine.xikage.mythicmobs.mobs.EntityManager;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*     */ import io.lumine.xikage.mythicmobs.players.PlayerManager;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillManager;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderManager;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableManager;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnerManager;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerManager;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeDisabled;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Random;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class MythicMobs extends JavaPlugin {
/*     */   private static io.lumine.xikage.mythicmobs.MythicMobs plugin;
/*     */   private ServerInterface server;
/*     */   private BaseCommand baseCommand;
/*     */   private ConfigManager configManager;
/*     */   private EntityManager entityManager;
/*     */   private WorldManager worldManager;
/*     */   private RandomSpawnerManager randomSpawningManager;
/*     */   private MobManager mobManager;
/*     */   private PlayerManager playerManager;
/*     */   private SkillManager skillManager;
/*     */   private DropManager dropManager;
/*  60 */   private static final TerminableRegistry pluginComponents = TerminableRegistry.create(); private ItemManager itemManager; private SpawnerManager spawnerManager; private VariableManager variableManager; private PlaceholderManager placeholderManager; private HologramManager hologramManager; private CompatibilityManager compatibility; private VolatileCodeHandler volatileCodeHandler; private TimingsHandler timingsHandler; private BukkitAPIHelper APIHelper;
/*     */   
/*     */   public BaseCommand getBaseCommand() {
/*  63 */     return this.baseCommand;
/*     */   }
/*  65 */   public ConfigManager getConfigManager() { return this.configManager; }
/*  66 */   public EntityManager getEntityManager() { return this.entityManager; }
/*  67 */   public WorldManager getWorldManager() { return this.worldManager; }
/*  68 */   public RandomSpawnerManager getRandomSpawningManager() { return this.randomSpawningManager; }
/*  69 */   public MobManager getMobManager() { return this.mobManager; }
/*  70 */   public PlayerManager getPlayerManager() { return this.playerManager; }
/*  71 */   public SkillManager getSkillManager() { return this.skillManager; }
/*  72 */   public DropManager getDropManager() { return this.dropManager; }
/*  73 */   public ItemManager getItemManager() { return this.itemManager; }
/*  74 */   public SpawnerManager getSpawnerManager() { return this.spawnerManager; }
/*  75 */   public VariableManager getVariableManager() { return this.variableManager; }
/*  76 */   public PlaceholderManager getPlaceholderManager() { return this.placeholderManager; }
/*  77 */   public HologramManager getHologramManager() { return this.hologramManager; } public CompatibilityManager getCompatibility() {
/*  78 */     return this.compatibility;
/*     */   }
/*     */   public TimingsHandler getTimingsHandler() {
/*  81 */     return this.timingsHandler;
/*     */   } public BukkitAPIHelper getAPIHelper() {
/*  83 */     return this.APIHelper;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static String menu_header = ChatColor.GOLD + "--====|||| " + ChatColor.RED + ChatColor.BOLD + " MythicMobs " + ChatColor.GOLD + " ||||====--";
/*  89 */   public static Random r = new Random();
/*     */ 
/*     */   
/*     */   private String bukkitVersion;
/*     */ 
/*     */   
/*     */   private int minecraftVersion;
/*     */ 
/*     */   
/*     */   private boolean updateAvailable = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 103 */     plugin = this;
/* 104 */     this.server = (ServerInterface)new BukkitServer();
/* 105 */     TaskManager.initializeBukkit(this);
/* 106 */     SkillAdapter.initializeBukkit();
/*     */     
/* 108 */     PluginDescriptionFile pdfFile = getDescription();
/*     */     
/* 110 */     this.bukkitVersion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
/*     */     
/*     */     try {
/* 113 */       String[] split = this.bukkitVersion.split("_");
/* 114 */       this.minecraftVersion = Integer.parseInt(split[1]);
/* 115 */     } catch (Exception ex) {
/* 116 */       this.minecraftVersion = 13;
/* 117 */       ex.printStackTrace();
/*     */     } 
/*     */     
/* 120 */     MythicLogger.log("Loading {0} for Bukkit {1} (Minecraft 1.{2})...", new Object[] { pdfFile.getName(), this.bukkitVersion, Integer.valueOf(this.minecraftVersion) });
/*     */     
/* 122 */     Patterns.CompilePatterns();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     this.configManager = new ConfigManager(this);
/* 128 */     this.volatileCodeHandler = getVolatileCodeHandler();
/* 129 */     this.compatibility = new CompatibilityManager(this);
/* 130 */     this.itemManager = new ItemManager(this);
/* 131 */     this.dropManager = new DropManager(this);
/* 132 */     this.mobManager = new MobManager(this);
/* 133 */     this.playerManager = new PlayerManager(this);
/* 134 */     this.skillManager = new SkillManager(this);
/* 135 */     this.spawnerManager = new SpawnerManager(this);
/* 136 */     this.randomSpawningManager = new RandomSpawnerManager(this);
/* 137 */     this.variableManager = new VariableManager(this);
/* 138 */     this.placeholderManager = new PlaceholderManager(this);
/* 139 */     this.hologramManager = new HologramManager(this, this.compatibility);
/*     */     
/* 141 */     inst().getConfigManager().LoadAll(true);
/* 142 */     MythicLogger.log("MythicMobs configuration file loaded successfully.");
/*     */     
/* 144 */     this.timingsHandler = new TimingsHandler();
/*     */     
/* 146 */     this.entityManager = new EntityManager();
/* 147 */     this.worldManager = (WorldManager)new BukkitWorldManager();
/*     */     
/* 149 */     pluginComponents.accept(this.randomSpawningManager);
/* 150 */     pluginComponents.accept(this.mobManager);
/* 151 */     pluginComponents.accept(this.playerManager);
/* 152 */     pluginComponents.accept(this.skillManager);
/* 153 */     pluginComponents.accept(this.variableManager);
/* 154 */     pluginComponents.accept(this.hologramManager);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     this.baseCommand = new BaseCommand(this);
/*     */     
/* 161 */     registerCommand("mythicmobs", this.baseCommand);
/* 162 */     registerCommand("spawnmob", new SpawnCommand(this));
/*     */     
/* 164 */     getCommand("mythicmobslegacy").setExecutor((CommandExecutor)new CommandHandler());
/*     */     
/* 166 */     pluginComponents.accept(Scheduler.runTaskRepeatingSync((Runnable)new MythicMobsClock(), 0L, ConfigManager.ClockInterval));
/* 167 */     pluginComponents.accept(Scheduler.runTaskRepeatingAsync((Runnable)new MythicMobsAsyncClock(), 0L, ConfigManager.ClockInterval));
/*     */     
/* 169 */     PluginManager manager = getServer().getPluginManager();
/* 170 */     manager.registerEvents((Listener)this.entityManager, (Plugin)this);
/* 171 */     manager.registerEvents((Listener)this.worldManager, (Plugin)this);
/*     */     
/* 173 */     manager.registerEvents((Listener)new ChunkListeners(), (Plugin)this);
/* 174 */     manager.registerEvents((Listener)new BukkitSkillTriggers(), (Plugin)this);
/*     */     
/* 176 */     manager.registerEvents((Listener)new MobListeners(), (Plugin)this);
/* 177 */     manager.registerEvents((Listener)new ThreatTableListeners(), (Plugin)this);
/* 178 */     manager.registerEvents((Listener)new EggListeners(), (Plugin)this);
/* 179 */     manager.registerEvents((Listener)new PlayerListeners(), (Plugin)this);
/*     */ 
/*     */ 
/*     */     
/* 183 */     this.APIHelper = new BukkitAPIHelper();
/*     */     
/* 185 */     if (ConfigManager.AllowMetrics == true) {
/* 186 */       new bStats(this);
/*     */       try {
/* 188 */         MetricsLite metrics = new MetricsLite((Plugin)this);
/* 189 */         metrics.start();
/* 190 */       } catch (IOException e) {
/* 191 */         debug(1, "MetricsLite: Failed to submit MetricsLite stats.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 196 */     MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + " MythicMobs" + (p() ? " Premium" : "") + " v" + getVersion() + " (build " + getBuildNumber() + ") has been successfully loaded!" + ConsoleColor.RESET);
/*     */ 
/*     */ 
/*     */     
/* 200 */     inst().getMobManager().ScanWorld();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 210 */     MythicLogger.log("Disabling Mythic Mobs...");
/*     */     
/* 212 */     getSpawnerManager().resetAndSaveAll();
/* 213 */     this.configManager.SaveAll();
/* 214 */     getMobManager().despawnAllMobs();
/*     */     
/* 216 */     pluginComponents.terminate();
/*     */     
/* 218 */     PluginDescriptionFile pdfFile = getDescription();
/*     */     
/* 220 */     this.entityManager = null;
/*     */     
/* 222 */     this.volatileCodeHandler = null;
/* 223 */     this.timingsHandler = null;
/*     */ 
/*     */ 
/*     */     
/* 227 */     MythicLogger.log("All active settings have been saved.");
/*     */     
/* 229 */     this.configManager.ResetAll();
/* 230 */     this.compatibility.terminate();
/*     */     
/* 232 */     MythicLogger.log("v" + pdfFile.getVersion() + " has been Disabled!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.MythicMobs inst() {
/* 239 */     return plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean p = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean p() {
/* 251 */     return p;
/*     */   }
/*     */   
/*     */   public ServerInterface server() {
/* 255 */     return this.server;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void throwSevere(String key, String default_text, Object... params) {
/* 260 */     log(Level.SEVERE, default_text);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void throwWarning(String key, String default_text, Object... params) {
/* 265 */     log(Level.WARNING, default_text);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void throwInfo(String key, String default_text, Object... params) {
/* 270 */     log(Level.INFO, default_text);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void debug(String message) {
/* 275 */     debug(2, message);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void debug(int level, String message) {
/* 280 */     if (level <= ConfigManager.debugLevel) {
/* 281 */       log(Level.INFO, message);
/*     */     }
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void log(String message) {
/* 287 */     log(Level.INFO, "" + message);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void log(Level level, String message) {
/* 292 */     plugin.getLogger().log(level, message);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void error(String message) {
/* 297 */     log(Level.WARNING, message);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void skillConfigError(String skill, String line, String message) {
/* 302 */     MythicLogger.log(Level.WARNING, "Error with '" + skill + "' skill: " + message);
/* 303 */     MythicLogger.log(Level.WARNING, "-- Incorrect line: " + line);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void targeterConfigError(String skill, String line, String message) {
/* 308 */     MythicLogger.log(Level.WARNING, "Error with '" + skill + "' targeter: " + message);
/* 309 */     MythicLogger.log(Level.WARNING, "-- Incorrect attribute: " + line);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VolatileCodeHandler getVolatileCodeHandler() {
/*     */     VolatileCodeHandler volatileCodeHandler;
/* 317 */     if (this.volatileCodeHandler != null) return this.volatileCodeHandler;
/*     */     
/* 319 */     VolatileCodeDisabled volatileCodeDisabled = new VolatileCodeDisabled();
/*     */     
/* 321 */     String packageName = Bukkit.getServer().getClass().getPackage().getName();
/* 322 */     String version = packageName.substring(packageName.lastIndexOf('.') + 1);
/* 323 */     if (version.equals("craftbukkit")) {
/* 324 */       version = "pre";
/*     */     }
/*     */     try {
/* 327 */       Class<?> clazz = Class.forName("io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeEnabled_" + version);
/* 328 */       if (VolatileCodeHandler.class.isAssignableFrom(clazz)) {
/* 329 */         volatileCodeHandler = clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */       }
/* 331 */     } catch (ClassNotFoundException e) {
/* 332 */       error(menu_header);
/* 333 */       error("This version of MythicMobs is not fully compatible with your version of Bukkit.");
/* 334 */       error("Some features may be limited or disabled until you use a compatible version.");
/* 335 */     } catch (Exception e) {
/* 336 */       throw new RuntimeException("Unknown exception loading version handler. Volatile code has been disabled.", e);
/*     */     } 
/* 338 */     return volatileCodeHandler;
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
/*     */   public void handleException(Exception ex) {
/* 352 */     if (ConfigManager.errorLogging == true) {
/* 353 */       plugin.getLogger().severe("AN EXCEPTION HAS OCCURED:");
/* 354 */       PrintWriter writer = null;
/*     */       try {
/* 356 */         File folder = new File(plugin.getDataFolder(), "Error Logs");
/* 357 */         if (!folder.exists()) folder.mkdir(); 
/* 358 */         writer = new PrintWriter(new File(folder, System.currentTimeMillis() + ".txt"));
/* 359 */         Throwable t = ex;
/* 360 */         while (t != null) {
/* 361 */           plugin.getLogger().severe("    " + t.getMessage() + " (" + t.getClass().getName() + ")");
/* 362 */           t.printStackTrace(writer);
/* 363 */           writer.println();
/* 364 */           t = t.getCause();
/*     */         } 
/* 366 */         plugin.getLogger().severe("This error has been saved in the Error Logs folder");
/* 367 */         writer.println("MythicMobs version: " + plugin.getDescription().getVersion());
/* 368 */         writer.println("Bukkit version: " + Bukkit.getServer().getVersion());
/* 369 */       } catch (Exception x) {
/* 370 */         plugin.getLogger().severe("ERROR HANDLING EXCEPTION");
/* 371 */         x.printStackTrace();
/* 372 */         ex.printStackTrace();
/*     */       } finally {
/* 374 */         if (writer != null) writer.close(); 
/*     */       } 
/*     */     } else {
/* 377 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void handleError(Error ex) {
/* 382 */     if (ConfigManager.errorLogging == true) {
/* 383 */       plugin.getLogger().severe("AN ERROR HAS OCCURED:");
/* 384 */       PrintWriter writer = null;
/*     */       try {
/* 386 */         File folder = new File(plugin.getDataFolder(), "Error Logs");
/* 387 */         if (!folder.exists()) folder.mkdir(); 
/* 388 */         writer = new PrintWriter(new File(folder, System.currentTimeMillis() + ".txt"));
/* 389 */         Throwable t = ex;
/* 390 */         while (t != null) {
/* 391 */           plugin.getLogger().severe("    " + t.getMessage() + " (" + t.getClass().getName() + ")");
/* 392 */           t.printStackTrace(writer);
/* 393 */           writer.println();
/* 394 */           t = t.getCause();
/*     */         } 
/* 396 */         plugin.getLogger().severe("This error has been saved in the Error Logs folder");
/* 397 */         writer.println("MythicMobs version: " + plugin.getDescription().getVersion());
/* 398 */         writer.println("Bukkit version: " + Bukkit.getServer().getVersion());
/* 399 */       } catch (Exception x) {
/* 400 */         plugin.getLogger().severe("ERROR HANDLING EXCEPTION");
/* 401 */         x.printStackTrace();
/* 402 */         ex.printStackTrace();
/*     */       } finally {
/* 404 */         if (writer != null) writer.close(); 
/*     */       } 
/*     */     } else {
/* 407 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T extends CommandExecutor & TabCompleter> void registerCommand(String cmd, T handler) {
/* 412 */     getCommand(cmd).setExecutor((CommandExecutor)handler);
/* 413 */     getCommand(cmd).setTabCompleter((TabCompleter)handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinecraftVersion() {
/* 421 */     return this.minecraftVersion;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 425 */     return getDescription().getVersion().split("-")[0];
/*     */   }
/*     */   
/*     */   public String getBuildNumber() {
/* 429 */     String[] split = getDescription().getVersion().split("-");
/* 430 */     if (split.length == 2)
/* 431 */       return split[1]; 
/* 432 */     if (split.length == 3) {
/* 433 */       return split[2];
/*     */     }
/* 435 */     return "????";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsUpdateAvailable() {
/* 440 */     return this.updateAvailable;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\MythicMobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */