/*     */ package lumine.xikage.mythicmobs.compatibility;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.ArtifactsSupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.CMISupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.HolographicDisplaysSupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.MMOItemsSupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.MPetCompat;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.NoCheatPlusSupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.OpenTerrainGeneratorSupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.PlaceholderAPISupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.RelicsSupport;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.WorldGuardSupport;
/*     */ import java.util.Optional;
/*     */ 
/*     */ public class CompatibilityManager implements Terminable {
/*  16 */   private Optional<ArtifactsSupport> artifacts = Optional.empty(); public Optional<ArtifactsSupport> getArtifacts() { return this.artifacts; }
/*  17 */    private Optional<CMISupport> CMI = Optional.empty(); public Optional<CMISupport> getCMI() { return this.CMI; }
/*  18 */    private Optional<HologramsSupport> holograms = Optional.empty(); public Optional<HologramsSupport> getHolograms() { return this.holograms; }
/*  19 */    private Optional<HolographicDisplaysSupport> holographicDisplays = Optional.empty(); public Optional<HolographicDisplaysSupport> getHolographicDisplays() { return this.holographicDisplays; }
/*  20 */    private Optional<MMOItemsSupport> MMOItems = Optional.empty(); public Optional<MMOItemsSupport> getMMOItems() { return this.MMOItems; }
/*  21 */    private Optional<MPetCompat> miniaturePets = Optional.empty(); public Optional<MPetCompat> getMiniaturePets() { return this.miniaturePets; }
/*  22 */    private Optional<NoCheatPlusSupport> noCheatPlus = Optional.empty(); public Optional<NoCheatPlusSupport> getNoCheatPlus() { return this.noCheatPlus; }
/*  23 */    private Optional<OpenTerrainGeneratorSupport> openTerrainGenerator = Optional.empty(); public Optional<OpenTerrainGeneratorSupport> getOpenTerrainGenerator() { return this.openTerrainGenerator; }
/*  24 */    private Optional<PlaceholderAPISupport> placeholderAPI = Optional.empty(); public Optional<PlaceholderAPISupport> getPlaceholderAPI() { return this.placeholderAPI; }
/*  25 */    private Optional<RelicsSupport> relics = Optional.empty(); public Optional<RelicsSupport> getRelics() { return this.relics; }
/*  26 */    private Optional<TerrainControlSupport> terrainControl = Optional.empty(); public Optional<TerrainControlSupport> getTerrainControl() { return this.terrainControl; }
/*  27 */    private Optional<VaultSupport> vault = Optional.empty(); public Optional<VaultSupport> getVault() { return this.vault; }
/*  28 */    private Optional<WorldGuardSupport> worldGuard = Optional.empty(); public Optional<WorldGuardSupport> getWorldGuard() { return this.worldGuard; }
/*     */   
/*  30 */   public static EffectLibSupport EffectLib = null;
/*  31 */   public static EnchantsPlusSupport EnchantsPlus = null;
/*  32 */   public static HeroesSupport Heroes = null;
/*  33 */   public static LibsDisguisesSupport LibsDisguises = null;
/*  34 */   public static mcMMOSupport mcMMO = null;
/*  35 */   public static MythicDropsSupport MythicDrops = null;
/*  36 */   public static PhatLootsSupport PhatLoots = null;
/*  37 */   public static SkillAPISupport SkillAPI = null;
/*     */ 
/*     */   
/*     */   public CompatibilityManager(MythicMobs core) {
/*  41 */     registerCompatibility("MythicArtifacts", () -> this.artifacts = Optional.of(new ArtifactsSupport()));
/*  42 */     registerCompatibility("CMI", () -> this.CMI = Optional.of(new CMISupport(paramMythicMobs)));
/*  43 */     registerCompatibility("EffectLib", () -> EffectLib = new EffectLibSupport());
/*  44 */     registerCompatibility("EnchantsPlus", () -> EnchantsPlus = new EnchantsPlusSupport());
/*  45 */     registerCompatibility("Heroes", () -> Heroes = new HeroesSupport());
/*  46 */     registerCompatibility("Holograms", () -> this.holograms = Optional.of(new HologramsSupport(paramMythicMobs)));
/*  47 */     registerCompatibility("HolographicDisplays", () -> this.holographicDisplays = Optional.of(new HolographicDisplaysSupport(paramMythicMobs)));
/*  48 */     registerCompatibility("LibsDisguises", () -> LibsDisguises = new LibsDisguisesSupport());
/*  49 */     registerCompatibility("mcMMO", () -> mcMMO = new mcMMOSupport());
/*  50 */     registerCompatibility("MiniaturePets", () -> this.miniaturePets = Optional.of(new MPetCompat()));
/*  51 */     registerCompatibility("MMOItems", () -> this.MMOItems = Optional.of(new MMOItemsSupport()));
/*  52 */     registerCompatibility("MythicDrops", () -> MythicDrops = new MythicDropsSupport());
/*  53 */     registerCompatibility("NoCheatPlus", () -> this.noCheatPlus = Optional.of(new NoCheatPlusSupport()));
/*  54 */     registerCompatibility("OpenTerrainGenerator", () -> this.openTerrainGenerator = Optional.of(new OpenTerrainGeneratorSupport()));
/*  55 */     registerCompatibility("PhatLoots", () -> PhatLoots = new PhatLootsSupport());
/*  56 */     registerCompatibility("PlaceholderAPI", () -> this.placeholderAPI = Optional.of(new PlaceholderAPISupport(paramMythicMobs)));
/*  57 */     registerCompatibility("Relics", () -> this.relics = Optional.of(new RelicsSupport()));
/*  58 */     registerCompatibility("SkillAPI", () -> SkillAPI = new SkillAPISupport());
/*  59 */     registerCompatibility("TerrainControl", () -> this.terrainControl = Optional.of(new TerrainControlSupport()));
/*  60 */     registerCompatibility("Vault", () -> this.vault = Optional.of(new VaultSupport()));
/*  61 */     registerCompatibility("WorldGuard", () -> this.worldGuard = Optional.of(new WorldGuardSupport()));
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
/*     */   public boolean terminate() {
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   private void registerCompatibility(String name, Runnable run) {
/*     */     try {
/*  93 */       if (Bukkit.getPluginManager().getPlugin(name) != null) {
/*  94 */         run.run();
/*  95 */         MythicLogger.log("MythicMobs " + name + " Support has been enabled!");
/*     */       } 
/*  97 */     } catch (NoClassDefFoundError er) {
/*  98 */       MythicLogger.errorCompatibility(name, "Plugin not found/incompatible version");
/*  99 */       if (ConfigManager.debugLevel > 0) {
/* 100 */         er.printStackTrace();
/*     */       }
/* 102 */     } catch (Exception ex) {
/* 103 */       MythicLogger.error("Failed to enable support for " + name + ". Is it up to date?");
/* 104 */       if (ConfigManager.debugLevel > 0)
/* 105 */         ex.printStackTrace(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\CompatibilityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */