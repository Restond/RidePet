/*     */ package saukiya.sxattribute.bstats;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import org.apache.commons.lang3.concurrent.BasicThreadFactory;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.ServicePriority;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
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
/*     */ public class Metrics
/*     */ {
/*     */   public static final int B_STATS_VERSION = 1;
/*     */   private static final String URL = "https://bStats.org/submitData/bukkit";
/*     */   private static boolean logFailedRequests;
/*     */   private static String serverUUID;
/*     */   private final JavaPlugin plugin;
/*     */   
/*     */   static {
/*  52 */     if (System.getProperty("bstats.relocatecheck") == null || !"false".equals(System.getProperty("bstats.relocatecheck"))) {
/*     */       
/*  54 */       String defaultPackage = new String(new byte[] { 111, 114, 103, 46, 98, 115, 116, 97, 116, 115, 46, 98, 117, 107, 107, 105, 116 });
/*     */       
/*  56 */       String examplePackage = new String(new byte[] { 121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101 });
/*     */       
/*  58 */       if (github.saukiya.sxattribute.bstats.Metrics.class.getPackage().getName().equals(defaultPackage) || github.saukiya.sxattribute.bstats.Metrics.class.getPackage().getName().equals(examplePackage)) {
/*  59 */         throw new IllegalStateException("bStats Metrics class has not been relocated correctly!");
/*     */       }
/*     */     } 
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
/*  72 */   private final List<CustomChart> charts = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metrics(JavaPlugin plugin) {
/*  80 */     if (plugin == null) {
/*  81 */       throw new IllegalArgumentException("Plugin cannot be null!");
/*     */     }
/*  83 */     this.plugin = plugin;
/*     */ 
/*     */     
/*  86 */     File bStatsFolder = new File(plugin.getDataFolder().getParentFile(), "bStats");
/*  87 */     File configFile = new File(bStatsFolder, "config.yml");
/*  88 */     YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*     */ 
/*     */     
/*  91 */     if (!config.isSet("serverUuid")) {
/*     */ 
/*     */       
/*  94 */       config.addDefault("enabled", Boolean.valueOf(true));
/*     */       
/*  96 */       config.addDefault("serverUuid", UUID.randomUUID().toString());
/*     */       
/*  98 */       config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*     */ 
/*     */       
/* 101 */       config.options().header("bStats collects some data for plugin authors like how many servers are using their plugins.\nTo honor their work, you should not disable it.\nThis has nearly no effect on the server performance!\nCheck out https://bStats.org/ to learn more :)")
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 106 */         .copyDefaults(true);
/*     */       try {
/* 108 */         config.save(configFile);
/* 109 */       } catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 114 */     serverUUID = config.getString("serverUuid");
/* 115 */     logFailedRequests = config.getBoolean("logFailedRequests", false);
/*     */ 
/*     */     
/* 118 */     boolean found = false;
/*     */     
/* 120 */     for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */       
/*     */       try {
/* 123 */         service.getField("B_STATS_VERSION");
/*     */         
/* 125 */         found = true;
/*     */         break;
/* 127 */       } catch (NoSuchFieldException noSuchFieldException) {}
/*     */     } 
/*     */ 
/*     */     
/* 131 */     Bukkit.getServicesManager().register(github.saukiya.sxattribute.bstats.Metrics.class, this, (Plugin)plugin, ServicePriority.Normal);
/* 132 */     if (!found)
/*     */     {
/* 134 */       startSubmitting();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendData(JSONObject data) throws Exception {
/* 145 */     if (data == null) {
/* 146 */       throw new IllegalArgumentException("Data cannot be null!");
/*     */     }
/* 148 */     if (Bukkit.isPrimaryThread()) {
/* 149 */       throw new IllegalAccessException("This Method must not be called from the main thread!");
/*     */     }
/* 151 */     HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://bStats.org/submitData/bukkit")).openConnection();
/*     */ 
/*     */     
/* 154 */     byte[] compressedData = compress(data.toString());
/*     */ 
/*     */     
/* 157 */     connection.setRequestMethod("POST");
/* 158 */     connection.addRequestProperty("Accept", "application/json");
/* 159 */     connection.addRequestProperty("Connection", "close");
/*     */     
/* 161 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 162 */     connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/*     */     
/* 164 */     connection.setRequestProperty("Content-Type", "application/json");
/* 165 */     connection.setRequestProperty("User-Agent", "MC-Server/1");
/*     */ 
/*     */     
/* 168 */     connection.setDoOutput(true);
/* 169 */     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
/* 170 */     outputStream.write(compressedData);
/* 171 */     outputStream.flush();
/* 172 */     outputStream.close();
/*     */ 
/*     */     
/* 175 */     connection.getInputStream().close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] compress(String str) throws IOException {
/* 186 */     if (str == null) {
/* 187 */       return null;
/*     */     }
/* 189 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 190 */     GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
/* 191 */     gzip.write(str.getBytes("UTF-8"));
/* 192 */     gzip.close();
/* 193 */     return outputStream.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCustomChart(CustomChart chart) {
/* 202 */     if (chart == null) {
/* 203 */       throw new IllegalArgumentException("Chart cannot be null!");
/*     */     }
/* 205 */     this.charts.add(chart);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startSubmitting() {
/* 212 */     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, (ThreadFactory)(new BasicThreadFactory.Builder()).namingPattern("metrics-schedule-pool-%d").daemon(true).build());
/* 213 */     executorService.scheduleAtFixedRate(() -> { if (!this.plugin.isEnabled()) { executorService.shutdown(); return; }  Bukkit.getScheduler().runTask((Plugin)this.plugin, this::submitData); }5L, 30L, TimeUnit.MINUTES);
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
/*     */   public JSONObject getPluginData() {
/* 240 */     JSONObject data = new JSONObject();
/*     */     
/* 242 */     String pluginName = this.plugin.getDescription().getName();
/* 243 */     String pluginVersion = this.plugin.getDescription().getVersion();
/*     */ 
/*     */     
/* 246 */     data.put("pluginName", pluginName);
/*     */     
/* 248 */     data.put("pluginVersion", pluginVersion);
/* 249 */     JSONArray customCharts = new JSONArray();
/* 250 */     for (CustomChart customChart : this.charts) {
/*     */       
/* 252 */       JSONObject chart = CustomChart.access$000(customChart);
/*     */       
/* 254 */       if (chart == null) {
/*     */         continue;
/*     */       }
/* 257 */       customCharts.add(chart);
/*     */     } 
/* 259 */     data.put("customCharts", customCharts);
/*     */     
/* 261 */     return data;
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
/*     */   private JSONObject getServerData() {
/*     */     int playerAmount;
/*     */     try {
/* 276 */       Method onlinePlayersMethod = Class.forName("org.bukkit.Server").getMethod("getOnlinePlayers", new Class[0]);
/*     */ 
/*     */       
/* 279 */       playerAmount = onlinePlayersMethod.getReturnType().equals(Collection.class) ? ((Collection)onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).size() : ((Player[])onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).length;
/* 280 */     } catch (Exception e) {
/*     */       
/* 282 */       playerAmount = Bukkit.getOnlinePlayers().size();
/*     */     } 
/* 284 */     int onlineMode = Bukkit.getOnlineMode() ? 1 : 0;
/* 285 */     String bukkitVersion = Bukkit.getVersion();
/* 286 */     bukkitVersion = bukkitVersion.substring(bukkitVersion.indexOf("MC: ") + 4, bukkitVersion.length() - 1);
/*     */ 
/*     */     
/* 289 */     String javaVersion = System.getProperty("java.version");
/* 290 */     String osName = System.getProperty("os.name");
/* 291 */     String osArch = System.getProperty("os.arch");
/* 292 */     String osVersion = System.getProperty("os.version");
/* 293 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */     
/* 295 */     JSONObject data = new JSONObject();
/*     */     
/* 297 */     data.put("serverUUID", serverUUID);
/*     */     
/* 299 */     data.put("playerAmount", Integer.valueOf(playerAmount));
/* 300 */     data.put("onlineMode", Integer.valueOf(onlineMode));
/* 301 */     data.put("bukkitVersion", bukkitVersion);
/*     */     
/* 303 */     data.put("javaVersion", javaVersion);
/* 304 */     data.put("osName", osName);
/* 305 */     data.put("osArch", osArch);
/* 306 */     data.put("osVersion", osVersion);
/* 307 */     data.put("coreCount", Integer.valueOf(coreCount));
/*     */     
/* 309 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void submitData() {
/* 317 */     JSONObject data = getServerData();
/*     */     
/* 319 */     JSONArray pluginData = new JSONArray();
/*     */     
/* 321 */     for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */       
/*     */       try {
/* 324 */         service.getField("B_STATS_VERSION");
/*     */         
/* 326 */         for (RegisteredServiceProvider<?> provider : (Iterable<RegisteredServiceProvider<?>>)Bukkit.getServicesManager().getRegistrations(service)) {
/*     */           try {
/* 328 */             pluginData.add(provider.getService().getMethod("getPluginData", new Class[0]).invoke(provider.getProvider(), new Object[0]));
/* 329 */           } catch (NullPointerException|NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException nullPointerException) {}
/*     */         }
/*     */       
/* 332 */       } catch (NoSuchFieldException noSuchFieldException) {}
/*     */     } 
/*     */ 
/*     */     
/* 336 */     data.put("plugins", pluginData);
/*     */ 
/*     */     
/* 339 */     Executors.newSingleThreadExecutor().execute(() -> {
/*     */ 
/*     */           
/*     */           try {
/*     */             sendData(data);
/* 344 */           } catch (Exception e) {
/*     */             if (logFailedRequests)
/*     */               this.plugin.getLogger().log(Level.WARNING, "Could not submit plugin attribute of " + this.plugin.getName(), e); 
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\bstats\Metrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */