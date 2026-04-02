/*     */ package lumine.xikage.mythicmobs.io;
/*     */ 
/*     */ import io.lumine.utils.config.file.FileConfiguration;
/*     */ import io.lumine.utils.config.file.YamlConfiguration;
/*     */ import io.lumine.xikage.mythicmobs.io.GenericConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class MythicConfig
/*     */   implements GenericConfig, Cloneable {
/*     */   private String configName;
/*     */   
/*     */   public File getFile() {
/*  23 */     return this.file;
/*     */   }
/*     */   private File file; private FileConfiguration fc;
/*     */   public MythicConfig(String name, FileConfiguration fc) {
/*  27 */     this.configName = name;
/*  28 */     this.fc = fc;
/*     */   }
/*     */   
/*     */   public MythicConfig(String name, File file, FileConfiguration fc) {
/*  32 */     this.configName = name;
/*  33 */     this.file = file;
/*  34 */     this.fc = fc;
/*     */   }
/*     */   
/*     */   public MythicConfig(String name, File file) {
/*  38 */     this.configName = name;
/*  39 */     this.file = file;
/*  40 */     this.fc = (FileConfiguration)new YamlConfiguration();
/*  41 */     this.fc.createSection(this.configName);
/*     */   }
/*     */   
/*     */   public void setKey(String key) {
/*  45 */     this.configName = key;
/*     */   }
/*     */   
/*     */   public String getKey() {
/*  49 */     return this.configName;
/*     */   }
/*     */   
/*     */   public FileConfiguration getFileConfiguration() {
/*  53 */     return this.fc;
/*     */   }
/*     */   
/*     */   public boolean isSet(String field) {
/*  57 */     return this.fc.isSet(this.configName + "." + field);
/*     */   }
/*     */   
/*     */   public void set(String key, Object value) {
/*  61 */     this.fc.set(this.configName + "." + key, value);
/*     */   }
/*     */   
/*     */   public void load() {
/*  65 */     this.fc = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
/*     */   }
/*     */   
/*     */   public void save() {
/*     */     try {
/*  70 */       this.fc.save(this.file);
/*  71 */     } catch (IOException e) {
/*  72 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.io.MythicConfig getNestedConfig(String field) {
/*  77 */     return new io.lumine.xikage.mythicmobs.io.MythicConfig(this.configName + "." + field, this.fc);
/*     */   }
/*     */   
/*     */   public Map<String, io.lumine.xikage.mythicmobs.io.MythicConfig> getNestedConfigs(String key) {
/*  81 */     Map<String, io.lumine.xikage.mythicmobs.io.MythicConfig> map = new HashMap<>();
/*     */     
/*  83 */     if (!isSet(key)) {
/*  84 */       return map;
/*     */     }
/*  86 */     for (String k : getKeys(key)) {
/*  87 */       map.put(k, new io.lumine.xikage.mythicmobs.io.MythicConfig(this.configName + "." + key + "." + k, this.fc));
/*     */     }
/*  89 */     return map;
/*     */   }
/*     */   
/*     */   public String getString(String field) {
/*  93 */     String key = this.configName + "." + field;
/*  94 */     return this.fc.getString(key, this.fc.getString(key.toLowerCase()));
/*     */   }
/*     */   
/*     */   public String getString(String field, String def) {
/*  98 */     String key = this.configName + "." + field;
/*  99 */     return this.fc.getString(key, this.fc.getString(key.toLowerCase(), def));
/*     */   }
/*     */   
/*     */   public PlaceholderString getPlaceholderString(String field) {
/* 103 */     String key = this.configName + "." + field;
/*     */     
/* 105 */     String s = this.fc.getString(key);
/*     */     
/* 107 */     if (s == null) {
/* 108 */       return null;
/*     */     }
/* 110 */     return PlaceholderString.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderString getPlaceholderString(String field, String def) {
/* 114 */     String key = this.configName + "." + field;
/*     */     
/* 116 */     String s = this.fc.getString(key, def);
/*     */     
/* 118 */     if (s == null) {
/* 119 */       return null;
/*     */     }
/* 121 */     return PlaceholderString.of(s);
/*     */   }
/*     */   
/*     */   public String getColorString(String field) {
/* 125 */     String key = this.configName + "." + field;
/* 126 */     String s = this.fc.getString(key);
/* 127 */     if (s != null) {
/* 128 */       s = ChatColor.translateAlternateColorCodes('&', s);
/*     */     }
/* 130 */     return s;
/*     */   }
/*     */   
/*     */   public String getColorString(String field, String def) {
/* 134 */     String key = this.configName + "." + field;
/* 135 */     String s = this.fc.getString(key, def);
/* 136 */     if (s != null) {
/* 137 */       s = ChatColor.translateAlternateColorCodes('&', s);
/*     */     }
/* 139 */     return s;
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String field) {
/* 143 */     String key = this.configName + "." + field;
/* 144 */     return this.fc.getBoolean(this.configName + "." + field);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String field, boolean def) {
/* 148 */     return this.fc.getBoolean(this.configName + "." + field, def);
/*     */   }
/*     */   
/*     */   public int getInteger(String field) {
/* 152 */     String key = this.configName + "." + field;
/* 153 */     return this.fc.getInt(key, this.fc.getInt(key.toLowerCase()));
/*     */   }
/*     */   
/*     */   public int getInteger(String field, int def) {
/* 157 */     String key = this.configName + "." + field;
/* 158 */     return this.fc.getInt(key, this.fc.getInt(key.toLowerCase(), def));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getInt(String field) {
/* 163 */     String key = this.configName + "." + field;
/* 164 */     return this.fc.getInt(this.configName + "." + field);
/*     */   }
/*     */   @Deprecated
/*     */   public int getInt(String field, int def) {
/* 168 */     String key = this.configName + "." + field;
/* 169 */     return this.fc.getInt(this.configName + "." + field, def);
/*     */   }
/*     */   public double getDouble(String field) {
/* 172 */     String key = this.configName + "." + field;
/* 173 */     return this.fc.getDouble(this.configName + "." + field);
/*     */   }
/*     */   public double getDouble(String field, double def) {
/* 176 */     String key = this.configName + "." + field;
/* 177 */     return this.fc.getDouble(this.configName + "." + field, def);
/*     */   }
/*     */   public List<String> getStringList(String field) {
/* 180 */     String key = this.configName + "." + field;
/* 181 */     return this.fc.getStringList(this.configName + "." + field);
/*     */   }
/*     */   public List<String> getColorStringList(String field) {
/* 184 */     String key = this.configName + "." + field;
/*     */     
/* 186 */     List<String> list = this.fc.getStringList(this.configName + "." + field);
/* 187 */     List<String> parsed = new ArrayList<>();
/*     */     
/* 189 */     if (list != null) {
/* 190 */       for (String str : list) {
/* 191 */         parsed.add(ChatColor.translateAlternateColorCodes('&', str));
/*     */       }
/*     */     }
/* 194 */     return parsed;
/*     */   }
/*     */   public List<PlaceholderString> getPlaceholderStringList(String field) {
/* 197 */     String key = this.configName + "." + field;
/*     */     
/* 199 */     List<String> list = this.fc.getStringList(this.configName + "." + field);
/* 200 */     List<PlaceholderString> parsed = new ArrayList<>();
/*     */     
/* 202 */     if (list != null) {
/* 203 */       for (String str : list) {
/* 204 */         parsed.add(PlaceholderString.of(str));
/*     */       }
/*     */     }
/* 207 */     return parsed;
/*     */   }
/*     */   public List<Map<?, ?>> getMapList(String field) {
/* 210 */     String key = this.configName + "." + field;
/* 211 */     return this.fc.getMapList(this.configName + "." + field);
/*     */   }
/*     */   public List<?> getList(String field) {
/* 214 */     String key = this.configName + "." + field;
/* 215 */     if (this.fc.isSet(key)) {
/* 216 */       return this.fc.getList(key);
/*     */     }
/* 218 */     if (this.fc.isSet(key.toLowerCase())) {
/* 219 */       return this.fc.getList(key.toLowerCase());
/*     */     }
/* 221 */     return null;
/*     */   }
/*     */   public List<Byte> getByteList(String field) {
/* 224 */     String key = this.configName + "." + field;
/* 225 */     if (this.fc.isSet(key)) {
/* 226 */       return this.fc.getByteList(key);
/*     */     }
/* 228 */     if (this.fc.isSet(key.toLowerCase())) {
/* 229 */       return this.fc.getByteList(key.toLowerCase());
/*     */     }
/* 231 */     return null;
/*     */   }
/*     */   public ItemStack getItemStack(String field, String def) {
/* 234 */     String key = this.configName + "." + field;
/* 235 */     if (this.fc.isSet(key)) {
/* 236 */       return this.fc.getItemStack(key);
/*     */     }
/* 238 */     if (this.fc.isSet(key.toLowerCase())) {
/* 239 */       return this.fc.getItemStack(key.toLowerCase());
/*     */     }
/*     */     try {
/* 242 */       return new ItemStack(Material.valueOf(def));
/* 243 */     } catch (Exception ex) {
/* 244 */       return null;
/*     */     } 
/*     */   }
/*     */   public boolean isConfigurationSection(String section) {
/* 248 */     String key = this.configName + "." + section;
/* 249 */     return this.fc.isConfigurationSection(this.configName + "." + section);
/*     */   }
/*     */   public Set<String> getKeys(String section) {
/* 252 */     String key = this.configName + "." + section;
/* 253 */     return this.fc.getConfigurationSection(this.configName + "." + section).getKeys(false);
/*     */   }
/*     */   public boolean isList(String section) {
/* 256 */     String key = this.configName + "." + section;
/* 257 */     return this.fc.isList(this.configName + "." + section);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\io\MythicConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */