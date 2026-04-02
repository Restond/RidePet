/*     */ package saukiya.sxattribute.data;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import github.saukiya.sxattribute.util.SimpleDateFormatUtils;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class RandomStringManager {
/*  19 */   private final File file = new File(SXAttribute.getPluginFile(), "RandomString");
/*  20 */   private final File file1 = new File(this.file, "DefaultRandom.yml");
/*  21 */   private final File file2 = new File(this.file, "10Level" + File.separator + "Random.yml");
/*     */   
/*  23 */   private final Map<String, List<String>> map = new HashMap<>();
/*     */   private final SXAttribute plugin;
/*     */   
/*     */   public RandomStringManager(SXAttribute plugin) throws IOException, InvalidConfigurationException {
/*  27 */     this.plugin = plugin;
/*  28 */     loadData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<String, List<String>>> entrySet() {
/*  37 */     return this.map.entrySet();
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
/*     */   public String processRandomString(String itemName, String string, Map<String, String> lockMap) {
/*  49 */     if (string != null) {
/*  50 */       if (Config.isRandomString()) {
/*  51 */         List<String> replaceLockStringList = getStringList("<l:", ">", string);
/*  52 */         for (String str : replaceLockStringList) {
/*  53 */           if (lockMap.containsKey(str)) {
/*  54 */             string = string.replace("<l:" + str + ">", lockMap.get(str)); continue;
/*     */           } 
/*  56 */           String randomString = getRandomString(itemName, str, lockMap);
/*  57 */           if (!randomString.equals("%DeleteLore%")) {
/*  58 */             string = string.replace("<l:" + str + ">", randomString);
/*     */             
/*  60 */             lockMap.put(str, randomString); continue;
/*     */           } 
/*  62 */           string = string.replace("<l:" + str + ">", "%DeleteLore%");
/*  63 */           Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c物品 §4" + itemName + "§c 名字中的随机字符串 §4" + str + "§c 不存在!");
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  68 */         List<String> replaceStringList = getStringList("<s:", ">", string);
/*  69 */         for (String str : replaceStringList) {
/*  70 */           String randomString = this.plugin.getRandomStringManager().getRandomString(itemName, str, lockMap);
/*  71 */           string = string.replaceFirst("<s:" + str + ">", randomString);
/*     */         } 
/*     */       } 
/*     */       
/*  75 */       List<String> replaceIntList = getStringList("<r:", ">", string);
/*  76 */       for (String str : replaceIntList) {
/*  77 */         String[] strSplit = str.split("_");
/*  78 */         if (strSplit.length > 1) {
/*  79 */           int i1 = Integer.valueOf(strSplit[0].replaceAll("[^0-9]", "")).intValue();
/*  80 */           int i2 = Integer.valueOf(strSplit[1].replaceAll("[^0-9]", "")).intValue() + 1;
/*  81 */           string = string.replaceFirst("<r:" + str + ">", String.valueOf(SXAttribute.getRandom().nextInt((i2 - i1 < 1) ? 1 : (i2 - i1)) + i1));
/*     */         } 
/*     */       } 
/*     */       
/*  85 */       List<String> replaceDoubleList = getStringList("<d:", ">", string);
/*  86 */       for (String str : replaceDoubleList) {
/*  87 */         String[] strSplit = str.split("_");
/*  88 */         if (strSplit.length > 1) {
/*  89 */           double d1 = Double.valueOf(strSplit[0].replaceAll("[^.0-9]", "")).doubleValue();
/*  90 */           double d2 = Double.valueOf(strSplit[1].replaceAll("[^.0-9]", "")).doubleValue() + 1.0D;
/*  91 */           string = string.replaceFirst("<d:" + str + ">", SXAttribute.getDf().format(SXAttribute.getRandom().nextDouble() * (d2 - d1) + d1));
/*     */         } 
/*     */       } 
/*     */       
/*  95 */       List<String> replaceTimeList = getStringList("<t:", ">", string);
/*  96 */       if (replaceTimeList.size() > 0) {
/*  97 */         SimpleDateFormatUtils ft = SXAttribute.getSdf();
/*  98 */         for (String str : replaceTimeList) {
/*  99 */           String addTime = str.replaceAll("[^0-9]", "") + "000";
/* 100 */           long time = System.currentTimeMillis() + Long.valueOf(addTime).longValue();
/* 101 */           string = string.replaceFirst("<t:" + str + ">", ft.format(Long.valueOf(time)));
/*     */         } 
/*     */       } 
/*     */     } 
/* 105 */     return string;
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
/*     */   private String getRandomString(String itemName, String name, Map<String, String> lockMap) {
/* 117 */     List<String> randomList = this.map.get(name);
/* 118 */     if (randomList != null) {
/* 119 */       String str1 = randomList.get(SXAttribute.getRandom().nextInt(randomList.size()));
/* 120 */       if (lockMap != null) {
/* 121 */         List<String> replaceLockStringList = getStringList("<l:", ">", str1);
/* 122 */         for (String str : replaceLockStringList) {
/* 123 */           if (lockMap.containsKey(str)) {
/* 124 */             str1 = str1.replace("<l:" + str + ">", lockMap.get(str)); continue;
/*     */           } 
/* 126 */           String randomString = getRandomString(itemName, str, lockMap);
/* 127 */           if (randomString != null) {
/* 128 */             str1 = str1.replace("<l:" + str + ">", randomString);
/* 129 */             lockMap.put(str, randomString); continue;
/*     */           } 
/* 131 */           str1 = str1.replace("<l:" + str + ">", "");
/* 132 */           Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c物品 §4" + itemName + "§c 名字中的随机字符串 §4" + str + "§c 不存在!");
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 137 */       List<String> replaceStringList = getStringList("<s:", ">", str1);
/* 138 */       for (String str2 : replaceStringList) {
/* 139 */         if (str1.equals(str2)) {
/* 140 */           Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c请不要造成无限循环 本插件不承担相应责任!");
/*     */           continue;
/*     */         } 
/* 143 */         str1 = str1.replaceFirst("<s:" + str2 + ">", getRandomString(itemName, str2, lockMap));
/*     */       } 
/* 145 */       return str1;
/*     */     } 
/* 147 */     return "%DeleteLore%";
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
/*     */   public List<String> getStringList(String prefix, String suffix, String string) {
/* 160 */     List<String> stringList = new ArrayList<>();
/* 161 */     if (string.contains(prefix)) {
/* 162 */       String[] args = string.split(prefix);
/* 163 */       if (args.length > 1 && args[1].contains(suffix)) {
/* 164 */         for (int i = 1; i < args.length && args[i].contains(suffix); i++) {
/* 165 */           stringList.add(args[i].split(suffix)[0]);
/*     */         }
/*     */       }
/*     */     } 
/* 169 */     return stringList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData() throws IOException, InvalidConfigurationException {
/* 179 */     if (Config.isRandomString()) {
/* 180 */       this.map.clear();
/* 181 */       if (!this.file.exists() || ((File[])Objects.requireNonNull((T)this.file.listFiles())).length == 0) {
/* 182 */         createDefaultRandom();
/*     */       }
/* 184 */       loadRandom(this.file);
/* 185 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load §c" + this.map.size() + " §rRandomString");
/*     */     } else {
/* 187 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§4Disable RandomString");
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
/*     */   private void loadRandom(File files) throws IOException, InvalidConfigurationException {
/* 199 */     for (File file : (File[])Objects.<File[]>requireNonNull(files.listFiles())) {
/* 200 */       if (file.isDirectory()) {
/* 201 */         loadRandom(file);
/*     */       } else {
/* 203 */         YamlConfiguration yml = new YamlConfiguration();
/* 204 */         yml.load(file);
/* 205 */         for (String name : yml.getKeys(false)) {
/* 206 */           if (this.map.containsKey(name)) {
/* 207 */             Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c不要重复随机字符组名:§4 " + file.getName().replace("plugins" + File.separator + SXAttribute.getPluginName() + File.separator, "") + File.separator + name + "§c !");
/*     */           }
/* 209 */           if (yml.get(name) instanceof String) {
/* 210 */             this.map.put(name, Collections.singletonList(yml.getString(name))); continue;
/* 211 */           }  if (yml.get(name) instanceof List) {
/* 212 */             List<String> list = yml.getStringList(name);
/* 213 */             this.map.put(name, list);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createDefaultRandom() throws IOException {
/* 226 */     YamlConfiguration yml = new YamlConfiguration();
/* 227 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cCreate Item/Default.yml");
/* 228 */     yml.set("DefaultLore", Arrays.asList(new String[] { "&7&o他是由什么材质做成的呢?", "&7&o握着它，有不好的预感呢", "&7&o据说夜幕曾经带它攻略沙场" }));
/* 229 */     yml.set("DefaultPrefix", Arrays.asList(new String[] { "&c令人兴奋之", "&c煞胁之", "&e兴趣使然之", "&e初心者之", "&e丝质之", "&e精灵之" }));
/* 230 */     yml.set("DefaultSuffix", Arrays.asList(new String[] { "&e淦", "&e武", "&e衡" }));
/* 231 */     yml.set("品质", Arrays.asList(new String[] { "普通", "普通", "普通", "普通", "普通", "普通", "普通", "优秀", "优秀", "史诗" }));
/* 232 */     yml.set("职业", Arrays.asList(new String[] { "射手", "战士", "剑士" }));
/* 233 */     yml.set("射手附魔", Arrays.asList(new String[] { "ARROW_DAMAGE:<r:0_3>\nARROW_INFINITE:<r:0_1>", "ARROW_DAMAGE:<r:0_3>\nARROW_FIRE:<r:0_2>", "ARROW_DAMAGE:<r:0_3>\nDURABILITY:<r:0_1>" }));
/* 234 */     yml.set("战士附魔", Arrays.asList(new String[] { "DAMAGE_ALL:<r:0_3>\nFIRE_ASPECT:<r:0_1>", "DAMAGE_ARTHROPODS:<r:0_3>\nKNOCKBACK:<r:0_1>", "DAMAGE_UNDEAD:<r:0_3>\nLOOT_BONUS_MOBS:<r:0_1>" }));
/* 235 */     yml.set("剑士附魔", Arrays.asList(new String[] { "DAMAGE_ALL:<r:0_3>\nFIRE_ASPECT:<r:0_1>", "DAMAGE_ALL:<r:0_3>\nKNOCKBACK:<r:0_1>", "DAMAGE_ALL:<r:0_3>\nLOOT_BONUS_MOBS:<r:0_1>" }));
/* 236 */     yml.set("射手ID", "261");
/* 237 */     yml.set("战士ID", "<s:战士<l:品质>ID>");
/* 238 */     yml.set("剑士ID", "<s:剑士<l:品质>ID>");
/* 239 */     yml.set("战士普通ID", "258");
/* 240 */     yml.set("战士优秀ID", "286");
/* 241 */     yml.set("战士史诗ID", "279");
/* 242 */     yml.set("剑士普通ID", "267");
/* 243 */     yml.set("剑士优秀ID", "283");
/* 244 */     yml.set("剑士史诗ID", "276");
/* 245 */     yml.set("材质", Arrays.asList(new String[] { "&01", "&01", "&02", "&03", "&04", "&05", "&06", "&07", "&08", "&09", "&010", "&011" }));
/* 246 */     yml.set("优秀职业", "&6限制职业: <l:职业>");
/* 247 */     yml.set("史诗职业", "&6限制职业: <l:职业>");
/* 248 */     yml.set("普通耐久", "<r:200_300>");
/* 249 */     yml.set("优秀耐久", "<r:500_600>");
/* 250 */     yml.set("史诗耐久", "<r:800_900>");
/* 251 */     yml.set("普通耐久最低", "200");
/* 252 */     yml.set("优秀耐久最低", "500");
/* 253 */     yml.set("史诗耐久最低", "800");
/* 254 */     yml.set("普通Color", "&7");
/* 255 */     yml.set("优秀Color", "&a");
/* 256 */     yml.set("史诗Color", "&5");
/* 257 */     yml.set("普通宝石孔", "&a&l『&7武石槽&a&l』");
/* 258 */     yml.set("优秀宝石孔", "&a&l『&7武石槽&a&l』&a&l『&7武石槽&a&l』");
/* 259 */     yml.set("史诗宝石孔", "&a&l『&7武石槽&a&l』&a&l『&7武石槽&a&l』&a&l『&7武石槽&a&l』");
/* 260 */     yml.set("史诗绑定", "&c已绑定");
/* 261 */     yml.set("好看Color", Arrays.asList(new String[] { "&a", "&b", "&c", "&4", "&d", "&1", "&3", "&9" }));
/* 262 */     yml.set("好丑Color", Arrays.asList(new String[] { "&1", "&8", "&7", "&5", "&3", "&2" }));
/* 263 */     yml.set("攻随一", Arrays.asList(new String[] { "命中几率", "失明几率", "缓慢几率", "凋零几率" }));
/* 264 */     yml.set("攻随二", Arrays.asList(new String[] { "雷霆几率", "破甲几率", "撕裂几率" }));
/* 265 */     yml.set("防随一", Arrays.asList(new String[] { "反射几率", "格挡几率", "韧性", "移动速度" }));
/* 266 */     yml.set("防随二", Arrays.asList(new String[] { "反射伤害", "格挡伤害", "闪避几率" }));
/* 267 */     yml.set("防随三", Arrays.asList(new String[] { "生命恢复", "生命上限", "PVP防御力", "PVE防御力" }));
/* 268 */     yml.save(this.file1);
/* 269 */     yml = new YamlConfiguration();
/* 270 */     yml.set("普通攻击-10", "20 - 30");
/* 271 */     yml.set("优秀攻击-10", "25 - 35");
/* 272 */     yml.set("史诗攻击-10", "30 - 40");
/* 273 */     yml.set("普通等级-10", "10");
/* 274 */     yml.set("优秀等级-10", "13");
/* 275 */     yml.set("史诗等级-10", "15");
/* 276 */     yml.set("普通攻一-10", Arrays.asList(new String[] { "<s:好丑Color>暴击几率: +<r:10_30>%\n<s:好丑Color>暴击伤害: +<r:10_30>%", "<s:好丑Color>攻击速度: +<r:10_30>%\n<s:好丑Color>点燃几率: +<r:10_30>%", "<s:好丑Color>吸血几率: +<r:10_30>%\n<s:好丑Color>吸血倍率: +<r:10_30>%" }));
/* 277 */     yml.set("普通攻二-10", Arrays.asList(new String[] { "<s:好丑Color><s:攻随一>: +<r:0_7>.<r:0_99>%", "%DeleteLore%" }));
/* 278 */     yml.set("普通攻三-10", Arrays.asList(new String[] { "<s:好丑Color><s:攻随二>: +<r:0_4>.<r:0_99>%", "%DeleteLore%", "%DeleteLore%" }));
/* 279 */     yml.set("优秀攻一-10", Arrays.asList(new String[] { "<s:好看Color>暴击几率: +<r:20_40>%\n<s:好看Color>暴击伤害: +<r:20_40>%", "<s:好看Color>攻击速度: +<r:20_40>%\n<s:好看Color>点燃几率: +<r:20_40>%", "<s:好看Color>吸血几率: +<r:20_40>%\n<s:好看Color>吸血倍率: +<r:20_40>%" }));
/* 280 */     yml.set("优秀攻二-10", "<s:好丑Color><s:攻随一>: +<r:8_15>.<r:0_99>%");
/* 281 */     yml.set("优秀攻三-10", Arrays.asList(new String[] { "<s:好看Color><s:攻随二>: +<r:5_9>.<r:0_99>%", "%DeleteLore%" }));
/* 282 */     yml.set("史诗攻一-10", Arrays.asList(new String[] { "<s:好看Color>暴击几率: +<r:30_50>%\n<s:好看Color>暴击伤害: +<r:30_50>%", "<s:好看Color>攻击速度: +<r:30_50>%\n<s:好看Color>点燃几率: +<r:30_50>%", "<s:好看Color>吸血几率: +<r:30_50>%\n<s:好看Color>吸血倍率: +<r:30_50>%" }));
/* 283 */     yml.set("史诗攻二-10", "<s:好看Color><s:攻随一>: +<r:16_23>.<r:0_99>%");
/* 284 */     yml.set("史诗攻三-10", "<s:好看Color><s:攻随二>: +<r:10_14>.<r:0_99>%");
/* 285 */     yml.set("普通防御-10", "10 - 15");
/* 286 */     yml.set("优秀防御-10", "13 - 18");
/* 287 */     yml.set("史诗防御-10", "16 - 21");
/* 288 */     yml.save(this.file2);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\RandomStringManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */