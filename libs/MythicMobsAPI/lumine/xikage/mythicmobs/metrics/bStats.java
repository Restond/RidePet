/*     */ package lumine.xikage.mythicmobs.metrics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.metrics.bukkit.Metrics;
/*     */ import io.lumine.xikage.mythicmobs.mobs.WorldScaling;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ public class bStats
/*     */ {
/*     */   public bStats(MythicMobs plugin) {
/*     */     try {
/*  15 */       Metrics metrics = new Metrics((Plugin)plugin);
/*     */       
/*  17 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("mob_types", () -> {
/*     */               int count = paramMythicMobs.getMobManager().getMobTypes().size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               return (count <= 0) ? "0" : ((count <= 10) ? "1 to 10" : ((count <= 25) ? "10 to 25" : ((count <= 50) ? "25 to 50" : ((count <= 100) ? "50 to 100" : ((count <= 150) ? "100 to 150" : ((count <= 200) ? "150 to 200" : ((count <= 250) ? "200 to 250" : ((count <= 500) ? "250 to 500" : ((count <= 1000) ? "500 to 750" : ((count <= 750) ? "750 to 1000" : "1000+"))))))))));
/*     */             }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  35 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("skills", () -> {
/*     */               int count = paramMythicMobs.getSkillManager().getSkills().size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               return (count <= 0) ? "0" : ((count <= 5) ? "1 to 5" : ((count <= 10) ? "5 to 10" : ((count <= 25) ? "10 to 25" : ((count <= 50) ? "25 to 50" : ((count <= 100) ? "50 to 100" : ((count <= 150) ? "100 to 150" : ((count <= 250) ? "150 to 250" : ((count <= 500) ? "250 to 500" : ((count <= 1000) ? "500 to 750" : ((count <= 750) ? "750 to 1000" : "1000+"))))))))));
/*     */             }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  52 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("item_types", () -> {
/*     */               int count = paramMythicMobs.getItemManager().getItems().size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               return (count <= 0) ? "0" : ((count <= 5) ? "1 to 5" : ((count <= 10) ? "5 to 10" : ((count <= 25) ? "10 to 25" : ((count <= 50) ? "25 to 50" : ((count <= 100) ? "50 to 100" : ((count <= 150) ? "100 to 150" : ((count <= 250) ? "150 to 250" : ((count <= 500) ? "250 to 500" : ((count <= 1000) ? "500 to 750" : ((count <= 750) ? "750 to 1000" : "1000+"))))))))));
/*     */             }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  69 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("droptables", () -> {
/*     */               int count = paramMythicMobs.getDropManager().getDropTables().size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               return (count <= 0) ? "0" : ((count <= 5) ? "1 to 5" : ((count <= 10) ? "5 to 10" : ((count <= 25) ? "10 to 25" : ((count <= 50) ? "25 to 50" : ((count <= 100) ? "50 to 100" : ((count <= 150) ? "100 to 150" : ((count <= 250) ? "150 to 250" : ((count <= 500) ? "250 to 500" : ((count <= 1000) ? "500 to 750" : ((count <= 750) ? "750 to 1000" : "1000+"))))))))));
/*     */             }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("spawners", () -> {
/*     */               int count = paramMythicMobs.getSpawnerManager().getSpawners().size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               return (count <= 0) ? "0" : ((count <= 5) ? "1 to 5" : ((count <= 10) ? "5 to 10" : ((count <= 25) ? "10 to 25" : ((count <= 50) ? "25 to 50" : ((count <= 100) ? "50 to 100" : ((count <= 150) ? "100 to 150" : ((count <= 250) ? "150 to 250" : ((count <= 500) ? "250 to 500" : ((count <= 1000) ? "500 to 750" : ((count <= 750) ? "750 to 1000" : "1000+"))))))))));
/*     */             }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 103 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("random_spawns", () -> {
/*     */               int count = paramMythicMobs.getRandomSpawningManager().getNumberOfSpawners();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               return (count <= 0) ? "0" : ((count <= 5) ? "1 to 5" : ((count <= 10) ? "5 to 10" : ((count <= 25) ? "10 to 25" : ((count <= 50) ? "25 to 50" : ((count <= 100) ? "50 to 100" : ((count <= 150) ? "100 to 150" : ((count <= 250) ? "150 to 250" : ((count <= 500) ? "250 to 500" : ((count <= 1000) ? "500 to 750" : ((count <= 750) ? "750 to 1000" : "1000+"))))))))));
/*     */             }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 120 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("uses_rsp_generator", () -> ConfigManager.generateRSPoints() ? "Yes" : "No"));
/* 121 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("uses_world_scaling", () -> (WorldScaling.worldSettings.size() > 0) ? "Yes" : "No"));
/* 122 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("clock_speed", () -> String.valueOf(ConfigManager.ClockInterval)));
/*     */       
/* 124 */       String devBuilds = plugin.getDescription().getVersion().contains("SNAPSHOT") ? "Yes" : "No";
/* 125 */       String preBuilds = MythicMobs.p() ? "Yes" : "No";
/*     */       
/* 127 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("pluginVersion", () -> paramMythicMobs.getVersion()));
/* 128 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("version", () -> paramMythicMobs.getVersion()));
/*     */       
/* 130 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("premium", () -> paramString));
/* 131 */       metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("devbuilds", () -> paramString));
/*     */       
/* 133 */       MythicLogger.log("Started up bStats Metrics");
/* 134 */     } catch (Exception e) {
/* 135 */       MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Metrics: Failed to enable bStats Metrics stats.", new Object[0]);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\metrics\bStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */