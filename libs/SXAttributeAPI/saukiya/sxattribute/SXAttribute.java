/*     */ package saukiya.sxattribute;
/*     */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*     */ import github.saukiya.sxattribute.api.SXAttributeAPI;
/*     */ import github.saukiya.sxattribute.command.MainCommand;
/*     */ import github.saukiya.sxattribute.data.BossBarData;
/*     */ import github.saukiya.sxattribute.data.ItemDataManager;
/*     */ import github.saukiya.sxattribute.data.NameData;
/*     */ import github.saukiya.sxattribute.data.RandomStringManager;
/*     */ import github.saukiya.sxattribute.data.RegisterSlotManager;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeManager;
/*     */ import github.saukiya.sxattribute.data.condition.SXConditionManager;
/*     */ import github.saukiya.sxattribute.listener.OnDamageListener;
/*     */ import github.saukiya.sxattribute.listener.OnHealthChangeDisplayListener;
/*     */ import github.saukiya.sxattribute.listener.OnUpdateStatsListener;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.ItemUtil;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import github.saukiya.sxattribute.util.SimpleDateFormatUtils;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class SXAttribute extends JavaPlugin {
/*     */   public static int[] getVersionSplit() {
/*  31 */     return versionSplit;
/*  32 */   } private static final int[] versionSplit = new int[3]; public static Random getRandom() {
/*  33 */     return random;
/*  34 */   } private static final Random random = new Random(); private static String pluginName; private static String pluginVersion; private static File pluginFile;
/*     */   public static String getPluginName() {
/*  36 */     return pluginName;
/*     */   } public static String getPluginVersion() {
/*  38 */     return pluginVersion;
/*     */   } public static File getPluginFile() {
/*  40 */     return pluginFile;
/*     */   }
/*  42 */   public static DecimalFormat getDf() { return df; } public static void setDf(DecimalFormat df) {
/*  43 */     github.saukiya.sxattribute.SXAttribute.df = df;
/*  44 */   } private static DecimalFormat df = new DecimalFormat("#.##"); private static SimpleDateFormatUtils sdf; private static SXAttributeAPI api;
/*  45 */   public static SimpleDateFormatUtils getSdf() { return sdf; } public static void setSdf(SimpleDateFormatUtils sdf) {
/*  46 */     github.saukiya.sxattribute.SXAttribute.sdf = sdf;
/*     */   }
/*     */   public static SXAttributeAPI getApi() {
/*  49 */     return api;
/*     */   }
/*     */   public static boolean isPluginEnabled() {
/*  52 */     return pluginEnabled;
/*     */   } private static boolean pluginEnabled = false;
/*     */   public static boolean isPlaceholder() {
/*  55 */     return placeholder;
/*     */   } private static boolean placeholder = false;
/*     */   public static boolean isHolographic() {
/*  58 */     return holographic;
/*     */   } private static boolean holographic = false;
/*     */   public static boolean isVault() {
/*  61 */     return vault;
/*     */   } private static boolean vault = false;
/*     */   public static boolean isRpgInventory() {
/*  64 */     return rpgInventory;
/*     */   } private static boolean rpgInventory = false;
/*     */   public static boolean isSxLevel() {
/*  67 */     return sxLevel;
/*     */   } private static boolean sxLevel = false; private ItemUtil itemUtil; private MainCommand mainCommand; private SXAttributeManager attributeManager; private SXConditionManager conditionManager;
/*     */   public ItemUtil getItemUtil() {
/*  70 */     return this.itemUtil;
/*     */   } private RandomStringManager randomStringManager; private ItemDataManager itemDataManager; private RegisterSlotManager registerSlotManager; private OnUpdateStatsListener onUpdateStatsListener; private OnDamageListener onDamageListener; private OnHealthChangeDisplayListener onHealthChangeDisplayListener;
/*     */   public MainCommand getMainCommand() {
/*  73 */     return this.mainCommand;
/*     */   }
/*     */   public SXAttributeManager getAttributeManager() {
/*  76 */     return this.attributeManager;
/*     */   }
/*     */   public SXConditionManager getConditionManager() {
/*  79 */     return this.conditionManager;
/*     */   }
/*     */   public RandomStringManager getRandomStringManager() {
/*  82 */     return this.randomStringManager;
/*     */   }
/*     */   public ItemDataManager getItemDataManager() {
/*  85 */     return this.itemDataManager;
/*     */   }
/*     */   public RegisterSlotManager getRegisterSlotManager() {
/*  88 */     return this.registerSlotManager;
/*     */   }
/*     */   public OnUpdateStatsListener getOnUpdateStatsListener() {
/*  91 */     return this.onUpdateStatsListener;
/*     */   }
/*     */   public OnDamageListener getOnDamageListener() {
/*  94 */     return this.onDamageListener;
/*     */   }
/*     */   public OnHealthChangeDisplayListener getOnHealthChangeDisplayListener() {
/*  97 */     return this.onHealthChangeDisplayListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLoad() {
/* 102 */     super.onLoad();
/* 103 */     pluginFile = getDataFolder();
/* 104 */     pluginName = getName();
/* 105 */     pluginVersion = getDescription().getVersion();
/* 106 */     api = new SXAttributeAPI(this);
/*     */     try {
/* 108 */       Config.loadConfig();
/* 109 */       Message.loadMessage();
/* 110 */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/* 111 */       e.printStackTrace();
/* 112 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cIO Error!");
/*     */     } 
/* 114 */     sdf = new SimpleDateFormatUtils();
/* 115 */     this.attributeManager = new SXAttributeManager(this);
/* 116 */     this.conditionManager = new SXConditionManager(this);
/* 117 */     this.mainCommand = new MainCommand(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 122 */     pluginEnabled = true;
/* 123 */     long oldTimes = System.currentTimeMillis();
/* 124 */     String version = Bukkit.getBukkitVersion().split("-")[0].replace(" ", "");
/* 125 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "ServerVersion: " + version);
/* 126 */     String[] strSplit = version.split("[.]");
/* 127 */     IntStream.range(0, strSplit.length).forEachOrdered(i -> versionSplit[i] = Integer.valueOf(strSplit[i]).intValue());
/* 128 */     new Metrics(this);
/*     */     try {
/* 130 */       this.itemUtil = new ItemUtil(this);
/* 131 */       this.randomStringManager = new RandomStringManager(this);
/* 132 */       this.itemDataManager = new ItemDataManager(this);
/* 133 */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/* 134 */       e.printStackTrace();
/* 135 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cIO Error!");
/* 136 */       setEnabled(false);
/*     */       return;
/* 138 */     } catch (NoSuchMethodException|ClassNotFoundException e) {
/* 139 */       e.printStackTrace();
/* 140 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cReflection Error!");
/* 141 */       setEnabled(false);
/*     */       
/*     */       return;
/*     */     } 
/* 145 */     if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
/* 146 */       placeholder = true;
/* 147 */       new Placeholders(this);
/* 148 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Find Placeholders");
/*     */     } else {
/* 150 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cNo Find PlaceholderAPI!");
/*     */     } 
/*     */     
/* 153 */     if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
/*     */       try {
/* 155 */         MoneyUtil.setup();
/* 156 */         vault = true;
/* 157 */         Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Find Vault");
/* 158 */       } catch (NullPointerException e) {
/* 159 */         Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cNo Find Vault-Economy!");
/*     */       } 
/*     */     } else {
/* 162 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cNo Find Vault!");
/*     */     } 
/*     */     
/* 165 */     if (Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
/* 166 */       holographic = true;
/* 167 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Find HolographicDisplays");
/*     */     } else {
/* 169 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cNo Find HolographicDisplays!");
/*     */     } 
/*     */     
/* 172 */     if (Bukkit.getPluginManager().isPluginEnabled("MythicMobs")) {
/* 173 */       Bukkit.getPluginManager().registerEvents((Listener)new OnMythicmobsSpawnListener(this), (Plugin)this);
/* 174 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Find MythicMobs");
/*     */     } else {
/* 176 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cNo Find MythicMobs!");
/*     */     } 
/*     */     
/* 179 */     if (Bukkit.getPluginManager().isPluginEnabled("RPGInventory")) {
/* 180 */       rpgInventory = true;
/* 181 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Find RPGInventory");
/*     */     } else {
/* 183 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cNo Find RPGInventory!");
/*     */     } 
/*     */     
/* 186 */     if (Bukkit.getPluginManager().isPluginEnabled("SX-Level")) {
/* 187 */       sxLevel = true;
/* 188 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Find SX-Level");
/*     */     } else {
/* 190 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cNo Find SX-Level!");
/*     */     } 
/*     */     
/* 193 */     this.attributeManager.loadDefaultAttributeData();
/* 194 */     this.attributeManager.onAttributeEnable();
/* 195 */     SXAttributeData attributeData = new SXAttributeData();
/* 196 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load §c" + attributeData.getAttributeMap().size() + "§r Attributes");
/*     */     
/* 198 */     this.conditionManager.onConditionEnable();
/* 199 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load §c" + this.conditionManager.getConditionMap().size() + "§r Condition");
/*     */     
/* 201 */     this.registerSlotManager = new RegisterSlotManager(this);
/* 202 */     this.onUpdateStatsListener = new OnUpdateStatsListener(this);
/* 203 */     this.onDamageListener = new OnDamageListener(this);
/* 204 */     this.onHealthChangeDisplayListener = new OnHealthChangeDisplayListener(this);
/* 205 */     Bukkit.getPluginManager().registerEvents((Listener)new OnBanShieldInteractListener(), (Plugin)this);
/* 206 */     Bukkit.getPluginManager().registerEvents((Listener)this.onUpdateStatsListener, (Plugin)this);
/* 207 */     Bukkit.getPluginManager().registerEvents((Listener)this.onDamageListener, (Plugin)this);
/* 208 */     Bukkit.getPluginManager().registerEvents((Listener)this.onHealthChangeDisplayListener, (Plugin)this);
/* 209 */     Bukkit.getPluginManager().registerEvents((Listener)new OnItemSpawnListener(), (Plugin)this);
/* 210 */     this.mainCommand.setUp("sxAttribute");
/* 211 */     this.mainCommand.onCommandEnable();
/* 212 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load Time: §c" + (System.currentTimeMillis() - oldTimes) + "§r ms");
/* 213 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cAuthor: Saukiya QQ: 1940208750");
/* 214 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cThis plugin was first launched on www.mcbbs.net!");
/* 215 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§4Reprint is prohibited without permission!");
/* 216 */     Bukkit.getConsoleSender().sendMessage("§c");
/* 217 */     Bukkit.getConsoleSender().sendMessage("§c   ______  __             ___   __  __       _ __          __");
/* 218 */     Bukkit.getConsoleSender().sendMessage("§c  / ___/ |/ /            /   | / /_/ /______(_) /_  __  __/ /____");
/* 219 */     Bukkit.getConsoleSender().sendMessage("§c  \\__ \\|   /   ______   / /| |/ __/ __/ ___/ / __ \\/ / / / __/ _ \\");
/* 220 */     Bukkit.getConsoleSender().sendMessage("§c ___/ /   |   /_____/  / ___ / /_/ /_/ /  / / /_/ / /_/ / /_/  __/");
/* 221 */     Bukkit.getConsoleSender().sendMessage("§c/____/_/|_|           /_/  |_\\__/\\__/_/  /_/_.___/\\__,_/\\__/\\___/");
/* 222 */     Bukkit.getConsoleSender().sendMessage("§c");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 227 */     this.attributeManager.onAttributeDisable();
/* 228 */     this.conditionManager.onConditionDisable();
/* 229 */     this.mainCommand.onCommandDisable();
/* 230 */     if (isHolographic() && this.onDamageListener.getHologramsList().size() > 0) {
/* 231 */       this.onDamageListener.getHologramsList().forEach(Hologram::delete);
/*     */     }
/* 233 */     if (Config.isHealthBossBar() && this.onHealthChangeDisplayListener.getBossList().size() > 0) {
/* 234 */       this.onHealthChangeDisplayListener.getBossList().forEach(bossBarData -> bossBarData.getBossBar().removeAll());
/*     */     }
/* 236 */     if (Config.isHealthNameVisible() && this.onHealthChangeDisplayListener.getNameList().size() > 0)
/* 237 */       this.onHealthChangeDisplayListener.getNameList().forEach(nameData -> {
/*     */             if (nameData.getEntity() != null && !nameData.getEntity().isDead()) {
/*     */               nameData.getEntity().setCustomName(nameData.getName());
/*     */               nameData.getEntity().setCustomNameVisible(nameData.isVisible());
/*     */             } 
/*     */           }); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\SXAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */