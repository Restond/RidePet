/*     */ package lumine.xikage.mythicmobs.metrics;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.Proxy;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.scheduler.BukkitTask;
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
/*     */ public class MetricsLite
/*     */ {
/*     */   private static final int REVISION = 7;
/*     */   private static final String BASE_URL = "http://report.mcstats.org";
/*     */   private static final String REPORT_URL = "/plugin/%s";
/*     */   private static final int PING_INTERVAL = 15;
/*     */   private final Plugin plugin;
/*     */   private final YamlConfiguration configuration;
/*     */   private final File configurationFile;
/*     */   private final String guid;
/*     */   private final boolean debug;
/* 103 */   private final Object optOutLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   private volatile BukkitTask task = null;
/*     */   
/*     */   public MetricsLite(Plugin plugin) throws IOException {
/* 111 */     if (plugin == null) {
/* 112 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/*     */     
/* 115 */     this.plugin = plugin;
/*     */ 
/*     */     
/* 118 */     this.configurationFile = getConfigFile();
/* 119 */     this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile);
/*     */ 
/*     */     
/* 122 */     this.configuration.addDefault("opt-out", Boolean.valueOf(false));
/* 123 */     this.configuration.addDefault("guid", UUID.randomUUID().toString());
/* 124 */     this.configuration.addDefault("debug", Boolean.valueOf(false));
/*     */ 
/*     */     
/* 127 */     if (this.configuration.get("guid", null) == null) {
/* 128 */       this.configuration.options().header("http://mcstats.org").copyDefaults(true);
/* 129 */       this.configuration.save(this.configurationFile);
/*     */     } 
/*     */ 
/*     */     
/* 133 */     this.guid = this.configuration.getString("guid");
/* 134 */     this.debug = this.configuration.getBoolean("debug", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean start() {
/* 145 */     synchronized (this.optOutLock) {
/*     */       
/* 147 */       if (isOptOut()) {
/* 148 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 152 */       if (this.task != null) {
/* 153 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 157 */       this.task = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, (Runnable)new Object(this), 0L, 18000L);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 188 */       return true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOptOut() {
/* 198 */     synchronized (this.optOutLock) {
/*     */       
/*     */       try {
/* 201 */         this.configuration.load(getConfigFile());
/* 202 */       } catch (IOException ex) {
/* 203 */         if (this.debug) {
/* 204 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/*     */         }
/* 206 */         return true;
/* 207 */       } catch (InvalidConfigurationException ex) {
/* 208 */         if (this.debug) {
/* 209 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/*     */         }
/* 211 */         return true;
/*     */       } 
/* 213 */       return this.configuration.getBoolean("opt-out", false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enable() throws IOException {
/* 224 */     synchronized (this.optOutLock) {
/*     */       
/* 226 */       if (isOptOut()) {
/* 227 */         this.configuration.set("opt-out", Boolean.valueOf(false));
/* 228 */         this.configuration.save(this.configurationFile);
/*     */       } 
/*     */ 
/*     */       
/* 232 */       if (this.task == null) {
/* 233 */         start();
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
/*     */   public void disable() throws IOException {
/* 245 */     synchronized (this.optOutLock) {
/*     */       
/* 247 */       if (!isOptOut()) {
/* 248 */         this.configuration.set("opt-out", Boolean.valueOf(true));
/* 249 */         this.configuration.save(this.configurationFile);
/*     */       } 
/*     */ 
/*     */       
/* 253 */       if (this.task != null) {
/* 254 */         this.task.cancel();
/* 255 */         this.task = null;
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
/*     */ 
/*     */   
/*     */   public File getConfigFile() {
/* 271 */     File pluginsFolder = this.plugin.getDataFolder().getParentFile();
/*     */ 
/*     */     
/* 274 */     return new File(new File(pluginsFolder, "PluginMetrics"), "config.yml");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void postPlugin(boolean isPing) throws IOException {
/*     */     URLConnection connection;
/* 282 */     PluginDescriptionFile description = this.plugin.getDescription();
/* 283 */     String pluginName = description.getName();
/* 284 */     boolean onlineMode = Bukkit.getServer().getOnlineMode();
/* 285 */     String pluginVersion = description.getVersion();
/* 286 */     String serverVersion = Bukkit.getVersion();
/*     */     
/* 288 */     int playersOnline = 20;
/*     */     try {
/* 290 */       playersOnline = Bukkit.getServer().getOnlinePlayers().size();
/* 291 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     StringBuilder json = new StringBuilder(1024);
/* 297 */     json.append('{');
/*     */ 
/*     */     
/* 300 */     appendJSONPair(json, "guid", this.guid);
/* 301 */     appendJSONPair(json, "plugin_version", pluginVersion);
/* 302 */     appendJSONPair(json, "server_version", serverVersion);
/* 303 */     appendJSONPair(json, "players_online", Integer.toString(playersOnline));
/*     */ 
/*     */     
/* 306 */     String osname = System.getProperty("os.name");
/* 307 */     String osarch = System.getProperty("os.arch");
/* 308 */     String osversion = System.getProperty("os.version");
/* 309 */     String java_version = System.getProperty("java.version");
/* 310 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */ 
/*     */     
/* 313 */     if (osarch.equals("amd64")) {
/* 314 */       osarch = "x86_64";
/*     */     }
/*     */     
/* 317 */     appendJSONPair(json, "osname", osname);
/* 318 */     appendJSONPair(json, "osarch", osarch);
/* 319 */     appendJSONPair(json, "osversion", osversion);
/* 320 */     appendJSONPair(json, "cores", Integer.toString(coreCount));
/* 321 */     appendJSONPair(json, "auth_mode", onlineMode ? "1" : "0");
/* 322 */     appendJSONPair(json, "java_version", java_version);
/*     */ 
/*     */     
/* 325 */     if (isPing) {
/* 326 */       appendJSONPair(json, "ping", "1");
/*     */     }
/*     */ 
/*     */     
/* 330 */     json.append('}');
/*     */ 
/*     */     
/* 333 */     URL url = new URL("http://report.mcstats.org" + String.format("/plugin/%s", new Object[] { urlEncode(pluginName) }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 340 */     if (isMineshafterPresent()) {
/* 341 */       connection = url.openConnection(Proxy.NO_PROXY);
/*     */     } else {
/* 343 */       connection = url.openConnection();
/*     */     } 
/*     */ 
/*     */     
/* 347 */     byte[] uncompressed = json.toString().getBytes();
/* 348 */     byte[] compressed = gzip(json.toString());
/*     */ 
/*     */     
/* 351 */     connection.addRequestProperty("User-Agent", "MCStats/7");
/* 352 */     connection.addRequestProperty("Content-Type", "application/json");
/* 353 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 354 */     connection.addRequestProperty("Content-Length", Integer.toString(compressed.length));
/* 355 */     connection.addRequestProperty("Accept", "application/json");
/* 356 */     connection.addRequestProperty("Connection", "close");
/*     */     
/* 358 */     connection.setDoOutput(true);
/*     */     
/* 360 */     if (this.debug) {
/* 361 */       System.out.println("[Metrics] Prepared request for " + pluginName + " uncompressed=" + uncompressed.length + " compressed=" + compressed.length);
/*     */     }
/*     */ 
/*     */     
/* 365 */     OutputStream os = connection.getOutputStream();
/* 366 */     os.write(compressed);
/* 367 */     os.flush();
/*     */ 
/*     */     
/* 370 */     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/* 371 */     String response = reader.readLine();
/*     */ 
/*     */     
/* 374 */     os.close();
/* 375 */     reader.close();
/*     */     
/* 377 */     if (response == null || response.startsWith("ERR") || response.startsWith("7")) {
/* 378 */       if (response == null) {
/* 379 */         response = "null";
/* 380 */       } else if (response.startsWith("7")) {
/* 381 */         response = response.substring(response.startsWith("7,") ? 2 : 1);
/*     */       } 
/*     */       
/* 384 */       throw new IOException(response);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static byte[] gzip(String input) {
/* 389 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 390 */     GZIPOutputStream gzos = null;
/*     */     
/*     */     try {
/* 393 */       gzos = new GZIPOutputStream(baos);
/* 394 */       gzos.write(input.getBytes("UTF-8"));
/* 395 */     } catch (IOException e) {
/* 396 */       e.printStackTrace();
/*     */     } finally {
/* 398 */       if (gzos != null) {
/* 399 */         try { gzos.close(); }
/* 400 */         catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */     
/* 404 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isMineshafterPresent() {
/*     */     try {
/* 414 */       Class.forName("mineshafter.MineServer");
/* 415 */       return true;
/* 416 */     } catch (Exception e) {
/* 417 */       return false;
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
/*     */   private static void appendJSONPair(StringBuilder json, String key, String value) throws UnsupportedEncodingException {
/* 430 */     boolean isValueNumeric = false;
/*     */     
/*     */     try {
/* 433 */       if (value.equals("0") || !value.endsWith("0")) {
/* 434 */         Double.parseDouble(value);
/* 435 */         isValueNumeric = true;
/*     */       } 
/* 437 */     } catch (NumberFormatException e) {
/* 438 */       isValueNumeric = false;
/*     */     } 
/*     */     
/* 441 */     if (json.charAt(json.length() - 1) != '{') {
/* 442 */       json.append(',');
/*     */     }
/*     */     
/* 445 */     json.append(escapeJSON(key));
/* 446 */     json.append(':');
/*     */     
/* 448 */     if (isValueNumeric) {
/* 449 */       json.append(value);
/*     */     } else {
/* 451 */       json.append(escapeJSON(value));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String escapeJSON(String text) {
/* 462 */     StringBuilder builder = new StringBuilder();
/*     */     
/* 464 */     builder.append('"');
/* 465 */     for (int index = 0; index < text.length(); index++) {
/* 466 */       char chr = text.charAt(index);
/*     */       
/* 468 */       switch (chr) {
/*     */         case '"':
/*     */         case '\\':
/* 471 */           builder.append('\\');
/* 472 */           builder.append(chr);
/*     */           break;
/*     */         case '\b':
/* 475 */           builder.append("\\b");
/*     */           break;
/*     */         case '\t':
/* 478 */           builder.append("\\t");
/*     */           break;
/*     */         case '\n':
/* 481 */           builder.append("\\n");
/*     */           break;
/*     */         case '\r':
/* 484 */           builder.append("\\r");
/*     */           break;
/*     */         default:
/* 487 */           if (chr < ' ') {
/* 488 */             String t = "000" + Integer.toHexString(chr);
/* 489 */             builder.append("\\u" + t.substring(t.length() - 4)); break;
/*     */           } 
/* 491 */           builder.append(chr);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 496 */     builder.append('"');
/*     */     
/* 498 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String urlEncode(String text) throws UnsupportedEncodingException {
/* 508 */     return URLEncoder.encode(text, "UTF-8");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\metrics\MetricsLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */