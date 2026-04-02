/*     */ package lumine.xikage.mythicmobs.metrics.bukkit;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.ServicePriority;
/*     */ 
/*     */ public class Metrics {
/*     */   static {
/*  34 */     if (System.getProperty("bstats.relocatecheck") == null || !System.getProperty("bstats.relocatecheck").equals("false")) {
/*     */       
/*  36 */       String defaultPackage = new String(new byte[] { 111, 114, 103, 46, 98, 115, 116, 97, 116, 115, 46, 98, 117, 107, 107, 105, 116 });
/*     */       
/*  38 */       String examplePackage = new String(new byte[] { 121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101 });
/*     */       
/*  40 */       if (io.lumine.xikage.mythicmobs.metrics.bukkit.Metrics.class.getPackage().getName().equals(defaultPackage) || io.lumine.xikage.mythicmobs.metrics.bukkit.Metrics.class.getPackage().getName().equals(examplePackage)) {
/*  41 */         throw new IllegalStateException("bStats Metrics class has not been relocated correctly!");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int B_STATS_VERSION = 1;
/*     */ 
/*     */   
/*     */   private static final String URL = "https://bStats.org/submitData/bukkit";
/*     */ 
/*     */   
/*     */   private boolean enabled;
/*     */ 
/*     */   
/*     */   private static boolean logFailedRequests;
/*     */ 
/*     */   
/*     */   private static boolean logSentData;
/*     */ 
/*     */   
/*     */   private static boolean logResponseStatusText;
/*     */ 
/*     */   
/*     */   private static String serverUUID;
/*     */ 
/*     */   
/*     */   private final Plugin plugin;
/*     */   
/*  71 */   private final List<CustomChart> charts = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metrics(Plugin plugin) {
/*  79 */     if (plugin == null) {
/*  80 */       throw new IllegalArgumentException("Plugin cannot be null!");
/*     */     }
/*  82 */     this.plugin = plugin;
/*     */ 
/*     */     
/*  85 */     File bStatsFolder = new File(plugin.getDataFolder().getParentFile(), "bStats");
/*  86 */     File configFile = new File(bStatsFolder, "config.yml");
/*  87 */     YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*     */ 
/*     */     
/*  90 */     if (!config.isSet("serverUuid")) {
/*     */ 
/*     */       
/*  93 */       config.addDefault("enabled", Boolean.valueOf(true));
/*     */       
/*  95 */       config.addDefault("serverUuid", UUID.randomUUID().toString());
/*     */       
/*  97 */       config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*     */       
/*  99 */       config.addDefault("logSentData", Boolean.valueOf(false));
/*     */       
/* 101 */       config.addDefault("logResponseStatusText", Boolean.valueOf(false));
/*     */ 
/*     */       
/* 104 */       config.options().header("bStats collects some data for plugin authors like how many servers are using their plugins.\nTo honor their work, you should not disable it.\nThis has nearly no effect on the server performance!\nCheck out https://bStats.org/ to learn more :)")
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 109 */         .copyDefaults(true);
/*     */       try {
/* 111 */         config.save(configFile);
/* 112 */       } catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */     
/* 116 */     this.enabled = config.getBoolean("enabled", true);
/* 117 */     serverUUID = config.getString("serverUuid");
/* 118 */     logFailedRequests = config.getBoolean("logFailedRequests", false);
/* 119 */     logSentData = config.getBoolean("logSentData", false);
/* 120 */     logResponseStatusText = config.getBoolean("logResponseStatusText", false);
/*     */     
/* 122 */     if (this.enabled) {
/* 123 */       boolean found = false;
/*     */       
/* 125 */       for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */         try {
/* 127 */           service.getField("B_STATS_VERSION");
/* 128 */           found = true;
/*     */           break;
/* 130 */         } catch (NoSuchFieldException noSuchFieldException) {}
/*     */       } 
/*     */       
/* 133 */       Bukkit.getServicesManager().register(io.lumine.xikage.mythicmobs.metrics.bukkit.Metrics.class, this, plugin, ServicePriority.Normal);
/* 134 */       if (!found)
/*     */       {
/* 136 */         startSubmitting();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 147 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCustomChart(CustomChart chart) {
/* 156 */     if (chart == null) {
/* 157 */       throw new IllegalArgumentException("Chart cannot be null!");
/*     */     }
/* 159 */     this.charts.add(chart);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startSubmitting() {
/* 166 */     Timer timer = new Timer(true);
/* 167 */     timer.scheduleAtFixedRate((TimerTask)new Object(this, timer), 300000L, 1800000L);
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
/*     */   public JsonObject getPluginData() {
/* 191 */     JsonObject data = new JsonObject();
/*     */     
/* 193 */     String pluginName = this.plugin.getDescription().getName();
/* 194 */     String pluginVersion = this.plugin.getDescription().getVersion();
/*     */     
/* 196 */     data.addProperty("pluginName", pluginName);
/* 197 */     data.addProperty("pluginVersion", pluginVersion);
/* 198 */     JsonArray customCharts = new JsonArray();
/* 199 */     for (CustomChart customChart : this.charts) {
/*     */       
/* 201 */       JsonObject chart = CustomChart.access$200(customChart);
/* 202 */       if (chart == null) {
/*     */         continue;
/*     */       }
/* 205 */       customCharts.add((JsonElement)chart);
/*     */     } 
/* 207 */     data.add("customCharts", (JsonElement)customCharts);
/*     */     
/* 209 */     return data;
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
/*     */   private JsonObject getServerData() {
/*     */     int playerAmount;
/*     */     try {
/* 223 */       Method onlinePlayersMethod = Class.forName("org.bukkit.Server").getMethod("getOnlinePlayers", new Class[0]);
/*     */ 
/*     */       
/* 226 */       playerAmount = onlinePlayersMethod.getReturnType().equals(Collection.class) ? ((Collection)onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).size() : ((Player[])onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).length;
/* 227 */     } catch (Exception e) {
/* 228 */       playerAmount = Bukkit.getOnlinePlayers().size();
/*     */     } 
/* 230 */     int onlineMode = Bukkit.getOnlineMode() ? 1 : 0;
/* 231 */     String bukkitVersion = Bukkit.getVersion();
/* 232 */     String bukkitName = Bukkit.getName();
/*     */ 
/*     */     
/* 235 */     String javaVersion = System.getProperty("java.version");
/* 236 */     String osName = System.getProperty("os.name");
/* 237 */     String osArch = System.getProperty("os.arch");
/* 238 */     String osVersion = System.getProperty("os.version");
/* 239 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */     
/* 241 */     JsonObject data = new JsonObject();
/*     */     
/* 243 */     data.addProperty("serverUUID", serverUUID);
/*     */     
/* 245 */     data.addProperty("playerAmount", Integer.valueOf(playerAmount));
/* 246 */     data.addProperty("onlineMode", Integer.valueOf(onlineMode));
/* 247 */     data.addProperty("bukkitVersion", bukkitVersion);
/* 248 */     data.addProperty("bukkitName", bukkitName);
/*     */     
/* 250 */     data.addProperty("javaVersion", javaVersion);
/* 251 */     data.addProperty("osName", osName);
/* 252 */     data.addProperty("osArch", osArch);
/* 253 */     data.addProperty("osVersion", osVersion);
/* 254 */     data.addProperty("coreCount", Integer.valueOf(coreCount));
/*     */     
/* 256 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void submitData() {
/* 263 */     JsonObject data = getServerData();
/*     */     
/* 265 */     JsonArray pluginData = new JsonArray();
/*     */     
/* 267 */     for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */       try {
/* 269 */         service.getField("B_STATS_VERSION");
/*     */         
/* 271 */         for (RegisteredServiceProvider<?> provider : (Iterable<RegisteredServiceProvider<?>>)Bukkit.getServicesManager().getRegistrations(service)) {
/*     */           try {
/* 273 */             Object plugin = provider.getService().getMethod("getPluginData", new Class[0]).invoke(provider.getProvider(), new Object[0]);
/* 274 */             if (plugin instanceof JsonObject) {
/* 275 */               pluginData.add((JsonElement)plugin); continue;
/*     */             } 
/*     */             try {
/* 278 */               Class<?> jsonObjectJsonSimple = Class.forName("org.json.simple.JSONObject");
/* 279 */               if (plugin.getClass().isAssignableFrom(jsonObjectJsonSimple)) {
/* 280 */                 Method jsonStringGetter = jsonObjectJsonSimple.getDeclaredMethod("toJSONString", new Class[0]);
/* 281 */                 jsonStringGetter.setAccessible(true);
/* 282 */                 String jsonString = (String)jsonStringGetter.invoke(plugin, new Object[0]);
/* 283 */                 JsonObject object = (new JsonParser()).parse(jsonString).getAsJsonObject();
/* 284 */                 pluginData.add((JsonElement)object);
/*     */               } 
/* 286 */             } catch (ClassNotFoundException e) {
/*     */               
/* 288 */               if (logFailedRequests) {
/* 289 */                 this.plugin.getLogger().log(Level.SEVERE, "Encountered unexpected exception", e);
/*     */               }
/*     */             }
/*     */           
/*     */           }
/* 294 */           catch (NullPointerException|NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException nullPointerException) {}
/*     */         } 
/* 296 */       } catch (NoSuchFieldException noSuchFieldException) {}
/*     */     } 
/*     */     
/* 299 */     data.add("plugins", (JsonElement)pluginData);
/*     */ 
/*     */     
/* 302 */     (new Thread((Runnable)new Object(this, data)))
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
/* 315 */       .start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendData(Plugin plugin, JsonObject data) throws Exception {
/* 326 */     if (data == null) {
/* 327 */       throw new IllegalArgumentException("Data cannot be null!");
/*     */     }
/* 329 */     if (Bukkit.isPrimaryThread()) {
/* 330 */       throw new IllegalAccessException("This method must not be called from the main thread!");
/*     */     }
/* 332 */     if (logSentData) {
/* 333 */       plugin.getLogger().info("Sending data to bStats: " + data.toString());
/*     */     }
/* 335 */     HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://bStats.org/submitData/bukkit")).openConnection();
/*     */ 
/*     */     
/* 338 */     byte[] compressedData = compress(data.toString());
/*     */ 
/*     */     
/* 341 */     connection.setRequestMethod("POST");
/* 342 */     connection.addRequestProperty("Accept", "application/json");
/* 343 */     connection.addRequestProperty("Connection", "close");
/* 344 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 345 */     connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/* 346 */     connection.setRequestProperty("Content-Type", "application/json");
/* 347 */     connection.setRequestProperty("User-Agent", "MC-Server/1");
/*     */ 
/*     */     
/* 350 */     connection.setDoOutput(true);
/* 351 */     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
/* 352 */     outputStream.write(compressedData);
/* 353 */     outputStream.flush();
/* 354 */     outputStream.close();
/*     */     
/* 356 */     InputStream inputStream = connection.getInputStream();
/* 357 */     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
/*     */     
/* 359 */     StringBuilder builder = new StringBuilder();
/*     */     String line;
/* 361 */     while ((line = bufferedReader.readLine()) != null) {
/* 362 */       builder.append(line);
/*     */     }
/* 364 */     bufferedReader.close();
/* 365 */     if (logResponseStatusText) {
/* 366 */       plugin.getLogger().info("Sent data to bStats and received response: " + builder.toString());
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
/*     */   private static byte[] compress(String str) throws IOException {
/* 378 */     if (str == null) {
/* 379 */       return null;
/*     */     }
/* 381 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 382 */     GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
/* 383 */     gzip.write(str.getBytes(StandardCharsets.UTF_8));
/* 384 */     gzip.close();
/* 385 */     return outputStream.toByteArray();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\metrics\bukkit\Metrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */