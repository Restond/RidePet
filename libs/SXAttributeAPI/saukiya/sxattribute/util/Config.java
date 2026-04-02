/*     */ package saukiya.sxattribute.util;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.io.File;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class Config {
/*     */   public static final String HOLOGRAPHIC_DISPLAY_TIME = "Holographic.DisplayTime";
/*     */   public static final String COMMAND_STATS_DISPLAY_SKULL_SKIN = "CommandStatsDisplaySkullSkin";
/*     */   public static final String HEALTH_NAME_VISIBLE_SIZE = "Health.NameVisible.Size";
/*     */   public static final String HEALTH_NAME_VISIBLE_CURRENT = "Health.NameVisible.Current";
/*     */   public static final String HEALTH_NAME_VISIBLE_LOSS = "Health.NameVisible.Loss";
/*     */   public static final String HEALTH_NAME_VISIBLE_PREFIX = "Health.NameVisible.Prefix";
/*     */   public static final String HEALTH_NAME_VISIBLE_SUFFIX = "Health.NameVisible.Suffix";
/*     */   public static final String HEALTH_NAME_VISIBLE_DISPLAY_TIME = "Health.NameVisible.DisplayTime";
/*     */   public static final String HEALTH_BOSS_BAR_FORMAT = "Health.BossBar.Format";
/*     */   public static final String HEALTH_BOSS_BAR_DISPLAY_TIME = "Health.BossBar.DisplayTime";
/*     */   public static final String HEALTH_SCALED_VALUE = "HealthScaled.Value";
/*     */   public static final String PRG_INVENTORY__WHITE_SLOT = "RPGInventory.WhiteSlot";
/*     */   public static final String REPAIR_ITEM_VALUE = "RepairItemValue";
/*     */   public static final String REGISTER_SLOTS_LIST = "RegisterSlots.List";
/*     */   public static final String REGISTER_SLOTS_LOCK_NAME = "RegisterSlots.Lock.Name";
/*     */   public static final String DEFAULT_STATS = "DefaultAttribute";
/*     */   public static final String NAME_HAND_MAIN = "Condition.Hand.InMain.Name";
/*     */   public static final String NAME_HAND_OFF = "Condition.Hand.InOff.Name";
/*     */   public static final String NAME_ARMOR = "Condition.Armor";
/*     */   public static final String NAME_ROLE = "Condition.Role.Name";
/*     */   public static final String NAME_LIMIT_LEVEL = "Condition.LimitLevel.Name";
/*     */   public static final String NAME_DURABILITY = "Condition.Durability.Name";
/*     */   public static final String CLEAR_ITEM_DURABILITY = "Condition.Durability.ClearItem";
/*     */   public static final String NAME_SELL = "Condition.Sell.Name";
/*     */   public static final String NAME_EXPIRY_TIME = "Condition.ExpiryTime.Name";
/*     */   public static final String FORMAT_EXPIRY_TIME = "Condition.ExpiryTime.Format";
/*     */   public static final String NAME_ATTACK_SPEED = "Condition.AttackSpeed.Name";
/*     */   public static final String NAME_EXP_ADDITION = "Attribute.ExpAddition.Name";
/*     */   public static final String NAME_SPEED = "Attribute.Speed.Name";
/*     */   public static final String NAME_HEALTH = "Attribute.Health.Name";
/*     */   public static final String NAME_HEALTH_REGEN = "Attribute.HealthRegen.Name";
/*     */   public static final String NAME_DODGE = "Attribute.Dodge.Name";
/*     */   public static final String NAME_DEFENSE = "Attribute.Defense.Name";
/*     */   public static final String NAME_PVP_DEFENSE = "Attribute.PVPDefense.Name";
/*     */   public static final String NAME_PVE_DEFENSE = "Attribute.PVEDefense.Name";
/*     */   public static final String NAME_TOUGHNESS = "Attribute.Toughness.Name";
/*     */   public static final String NAME_REFLECTION_RATE = "Attribute.ReflectionRate.Name";
/*     */   public static final String NAME_REFLECTION = "Attribute.Reflection.Name";
/*     */   public static final String NAME_BLOCK_RATE = "Attribute.BlockRate.Name";
/*     */   public static final String NAME_BLOCK = "Attribute.Block.Name";
/*     */   public static final String NAME_DAMAGE = "Attribute.Damage.Name";
/*     */   public static final String NAME_PVP_DAMAGE = "Attribute.PVPDamage.Name";
/*     */   public static final String NAME_PVE_DAMAGE = "Attribute.PVEDamage.Name";
/*     */   public static final String NAME_HIT_RATE = "Attribute.HitRate.Name";
/*     */   public static final String NAME_REAL = "Attribute.Real.Name";
/*     */   public static final String NAME_CRIT_RATE = "Attribute.Crit.Name";
/*     */   public static final String NAME_CRIT = "Attribute.CritDamage.Name";
/*     */   public static final String NAME_LIFE_STEAL = "Attribute.LifeSteal.Name";
/*     */   public static final String NAME_LIFE_STEAL_RATE = "Attribute.LifeStealRate.Name";
/*     */   public static final String NAME_IGNITION = "Attribute.Ignition.Name";
/*     */   public static final String NAME_WITHER = "Attribute.Wither.Name";
/*     */   public static final String NAME_POISON = "Attribute.Poison.Name";
/*     */   public static final String NAME_BLINDNESS = "Attribute.Blindness.Name";
/*     */   public static final String NAME_SLOWNESS = "Attribute.Slowness.Name";
/*     */   public static final String NAME_LIGHTNING = "Attribute.Lightning.Name";
/*     */   public static final String NAME_TEARING = "Attribute.Tearing.Name";
/*     */   public static final String VALUE_EXP_ADDITION = "Attribute.ExpAddition.Value";
/*     */   public static final String VALUE_SPEED = "Attribute.Speed.Value";
/*     */   public static final String VALUE_HEALTH = "Attribute.Health.Value";
/*     */   public static final String VALUE_HEALTH_REGEN = "Attribute.HealthRegen.Value";
/*     */   public static final String VALUE_DODGE = "Attribute.Dodge.Value";
/*     */   public static final String VALUE_DEFENSE = "Attribute.Defense.Value";
/*     */   public static final String VALUE_PVP_DEFENSE = "Attribute.PVPDefense.Value";
/*     */   public static final String VALUE_PVE_DEFENSE = "Attribute.PVEDefense.Value";
/*     */   public static final String VALUE_TOUGHNESS = "Attribute.Toughness.Value";
/*     */   public static final String VALUE_REFLECTION_RATE = "Attribute.ReflectionRate.Value";
/*     */   public static final String VALUE_REFLECTION = "Attribute.Reflection.Value";
/*     */   public static final String VALUE_BLOCK_RATE = "Attribute.BlockRate.Value";
/*     */   public static final String VALUE_BLOCK = "Attribute.Block.Value";
/*     */   public static final String VALUE_DAMAGE = "Attribute.Damage.Value";
/*     */   public static final String VALUE_PVE_DAMAGE = "Attribute.PVPDamage.Value";
/*     */   public static final String VALUE_PVP_DAMAGE = "Attribute.PVEDamage.Value";
/*     */   public static final String VALUE_HIT_RATE = "Attribute.HitRate.Value";
/*     */   public static final String VALUE_REAL = "Attribute.Real.Value";
/*     */   public static final String VALUE_CRIT_RATE = "Attribute.Crit.Value";
/*     */   public static final String VALUE_CRIT = "Attribute.CritDamage.Value";
/*     */   public static final String VALUE_LIFE_STEAL = "Attribute.LifeSteal.Value";
/*     */   public static final String VALUE_LIFE_STEAL_RATE = "Attribute.LifeStealRate.Value";
/*     */   public static final String VALUE_IGNITION = "Attribute.Ignition.Value";
/*     */   public static final String VALUE_WITHER = "Attribute.Wither.Value";
/*     */   public static final String VALUE_POISON = "Attribute.Poison.Value";
/*     */   public static final String VALUE_BLINDNESS = "Attribute.Blindness.Value";
/*     */   public static final String VALUE_SLOWNESS = "Attribute.Slowness.Value";
/*     */   public static final String VALUE_LIGHTNING = "Attribute.Lightning.Value";
/*     */   public static final String VALUE_TEARING = "Attribute.Tearing.Value";
/*     */   private static final String CONFIG_VERSION = "ConfigVersion";
/*     */   private static final String DECIMAL_FORMAT = "DecimalFormat";
/*     */   private static final String PRIORITY_EXP_ADDITION = "AttributePriority.ExpAddition";
/*     */   private static final String PRIORITY_SPEED = "AttributePriority.Speed";
/*     */   private static final String PRIORITY_MYTHICMOBS_DROP = "AttributePriority.MythicmobsDrop";
/*     */   private static final String PRIORITY_HEALTH = "AttributePriority.Health";
/*     */   private static final String PRIORITY_HEALTH_REGEN = "AttributePriority.HealthRegen";
/*     */   private static final String PRIORITY_DODGE = "AttributePriority.Dodge";
/*     */   private static final String PRIORITY_DEFENSE = "AttributePriority.Defense";
/*     */   private static final String PRIORITY_TOUGHNESS = "AttributePriority.Toughness";
/*     */   private static final String PRIORITY_REFLECTION = "AttributePriority.Reflection";
/*     */   private static final String PRIORITY_BLOCK = "AttributePriority.Block";
/*     */   private static final String PRIORITY_DAMAGE = "AttributePriority.Damage";
/*     */   private static final String PRIORITY_HIT_RATE = "AttributePriority.HitRate";
/*     */   private static final String PRIORITY_REAL = "AttributePriority.Real";
/*     */   private static final String PRIORITY_CRIT = "AttributePriority.Crit";
/*     */   private static final String PRIORITY_LIFE_STEAL = "AttributePriority.LifeSteal";
/*     */   private static final String PRIORITY_IGNITION = "AttributePriority.Ignition";
/*     */   private static final String PRIORITY_WITHER = "AttributePriority.Wither";
/*     */   private static final String PRIORITY_POISON = "AttributePriority.Poison";
/*     */   private static final String PRIORITY_BLINDNESS = "AttributePriority.Blindness";
/*     */   private static final String PRIORITY_SLOWNESS = "AttributePriority.Slowness";
/*     */   private static final String PRIORITY_LIGHTNING = "AttributePriority.Lightning";
/*     */   private static final String PRIORITY_TEARING = "AttributePriority.Tearing";
/*     */   private static final String PRIORITY_EVENT_MESSAGE = "AttributePriority.EventMessage";
/*     */   private static final String PRIORITY_DURABILITY = "ConditionPriority.Durability";
/*     */   private static final String PRIORITY_ATTACK_SPEED = "ConditionPriority.AttackSpeed";
/*     */   private static final String PRIORITY_EXPIRY_TIME = "ConditionPriority.ExpiryTime";
/*     */   private static final String PRIORITY_LIMIT_LEVEL = "ConditionPriority.LimitLevel";
/*     */   private static final String PRIORITY_MAIN_HAND = "ConditionPriority.MainHand";
/*     */   private static final String PRIORITY_OFF_HAND = "ConditionPriority.OffHand";
/*     */   private static final String PRIORITY_HAND = "ConditionPriority.Hand";
/*     */   private static final String PRIORITY_ROLE = "ConditionPriority.Role";
/*     */   private static final String REGISTER_SLOTS_ENABLED = "RegisterSlots.Enabled";
/*     */   private static final String REGISTER_SLOTS_LOCK_ENABLED = "RegisterSlots.Lock.Enabled";
/*     */   private static final String ITEM_UPDATE_ENABLED = "ItemUpdate.Enabled";
/*     */   private static final String HOLOGRAPHIC_ENABLED = "Holographic.Enabled";
/*     */   private static final String HOLOGRAPHIC_BLACK_CAUSE_LIST = "Holographic.BlackCauseList";
/*     */   private static final String HOLOGRAPHIC_HEALTH_TAKE_ENABLED = "Holographic.HealthOrTake.Enabled";
/*     */   private static final String HEALTH_NAME_VISIBLE_ENABLED = "Health.NameVisible.Enabled";
/*     */   private static final String HEALTH_BOSS_BAR_ENABLED = "Health.BossBar.Enabled";
/*     */   private static final String HEALTH_BOSS_BAR_BLACK_CAUSE_LIST = "Health.BossBar.BlackCauseList";
/*     */   private static final String HEALTH_SCALED_ENABLED = "HealthScaled.Enabled";
/*     */   private static final String ITEM_DISPLAY_NAME = "ItemDisplayName";
/*     */   private static final String DAMAGE_CALCULATION_TO_EVE = "DamageCalculationToEVE";
/*     */   private static final String DAMAGE_GAUGES = "DamageGauges";
/*     */   private static final String BAN_SHIELD_DEFENSE = "BanShieldDefense";
/*     */   private static final String CLEAR_DEFAULT_ATTRIBUTE_THIS_PLUGIN = "ClearDefaultAttribute.ThisPlugin";
/*     */   private static final String CLEAR_DEFAULT_ATTRIBUTE_ALL = "ClearDefaultAttribute.All";
/*     */   private static final String CLEAR_DEFAULT_ATTRIBUTE_RESET = "ClearDefaultAttribute.Reset";
/*     */   private static final String RANDOM_STRING = "RandomString";
/*     */   private static YamlConfiguration config;
/*     */   private static boolean commandStatsDisplaySkullSkin;
/*     */   private static boolean itemUpdate;
/*     */   private static boolean healthNameVisible;
/*     */   private static boolean healthBossBar;
/*     */   private static boolean healthScaled;
/*     */   private static boolean holographic;
/*     */   private static List<String> holographicBlackList;
/*     */   private static boolean holographicHealthTake;
/*     */   private static boolean itemDisplayName;
/*     */   private static boolean damageCalculationToEVE;
/*     */   private static boolean damageGauges;
/* 159 */   private static final File FILE = new File(SXAttribute.getPluginFile(), "Config.yml"); private static boolean banShieldDefense; private static boolean clearDefaultAttributePlugin; private static boolean clearDefaultAttributeAll; public static YamlConfiguration getConfig() {
/* 160 */     return config;
/*     */   } public static boolean isCommandStatsDisplaySkullSkin() {
/* 162 */     return commandStatsDisplaySkullSkin;
/*     */   } public static boolean isItemUpdate() {
/* 164 */     return itemUpdate;
/*     */   } public static boolean isHealthNameVisible() {
/* 166 */     return healthNameVisible;
/*     */   } public static boolean isHealthBossBar() {
/* 168 */     return healthBossBar;
/*     */   } public static boolean isHealthScaled() {
/* 170 */     return healthScaled;
/*     */   } public static boolean isHolographic() {
/* 172 */     return holographic;
/*     */   } public static List<String> getHolographicBlackList() {
/* 174 */     return holographicBlackList;
/*     */   } public static boolean isHolographicHealthTake() {
/* 176 */     return holographicHealthTake;
/*     */   } public static boolean isItemDisplayName() {
/* 178 */     return itemDisplayName;
/*     */   } public static boolean isDamageCalculationToEVE() {
/* 180 */     return damageCalculationToEVE;
/*     */   } public static boolean isDamageGauges() {
/* 182 */     return damageGauges;
/*     */   } public static boolean isBanShieldDefense() {
/* 184 */     return banShieldDefense;
/*     */   } public static boolean isClearDefaultAttributePlugin() {
/* 186 */     return clearDefaultAttributePlugin;
/*     */   } public static boolean isClearDefaultAttributeAll() {
/* 188 */     return clearDefaultAttributeAll;
/*     */   } public static boolean isClearDefaultAttributeReset() {
/* 190 */     return clearDefaultAttributeReset;
/*     */   } private static boolean clearDefaultAttributeReset = false; private static boolean randomString; private static boolean registerSlot; private static boolean registerSlotsLock; private static List<String> bossBarBlackCauseList; private static boolean clearItemDurability; public static boolean isRandomString() {
/* 192 */     return randomString;
/*     */   } public static boolean isRegisterSlot() {
/* 194 */     return registerSlot;
/*     */   } public static boolean isRegisterSlotsLock() {
/* 196 */     return registerSlotsLock;
/*     */   } public static List<String> getBossBarBlackCauseList() {
/* 198 */     return bossBarBlackCauseList;
/*     */   } public static boolean isClearItemDurability() {
/* 200 */     return clearItemDurability;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createDefaultConfig() {
/* 207 */     config.set("ConfigVersion", SXAttribute.getPluginVersion());
/* 208 */     config.set("CommandStatsDisplaySkullSkin", Boolean.valueOf(true));
/* 209 */     config.set("DecimalFormat", "#.##");
/*     */     
/* 211 */     config.set("ItemUpdate.Enabled", Boolean.valueOf(false));
/*     */     
/* 213 */     config.set("Holographic.Enabled", Boolean.valueOf(true));
/* 214 */     config.set("Holographic.DisplayTime", Integer.valueOf(40));
/* 215 */     config.set("Holographic.BlackCauseList", Arrays.asList(new String[] { "ENTITY_SWEEP_ATTACK", "POISON" }));
/* 216 */     config.set("Holographic.HealthOrTake.Enabled", Boolean.valueOf(false));
/*     */     
/* 218 */     config.set("Health.NameVisible.Enabled", Boolean.valueOf(true));
/* 219 */     config.set("Health.NameVisible.Size", Integer.valueOf(10));
/* 220 */     config.set("Health.NameVisible.Current", "❤");
/* 221 */     config.set("Health.NameVisible.Loss", "&7❤");
/* 222 */     config.set("Health.NameVisible.Prefix", "&8[&c");
/* 223 */     config.set("Health.NameVisible.Suffix", "&8] &7- &8[&c{0}&8]");
/* 224 */     config.set("Health.NameVisible.DisplayTime", Integer.valueOf(4));
/*     */     
/* 226 */     config.set("Health.BossBar.Enabled", Boolean.valueOf(true));
/* 227 */     config.set("Health.BossBar.Format", "&a&l{0}:&8&l[&a&l{1}&7&l/&c&l{2}&8&l]");
/* 228 */     config.set("Health.BossBar.DisplayTime", Integer.valueOf(4));
/* 229 */     config.set("Health.BossBar.BlackCauseList", Arrays.asList(new String[] { "ENTITY_SWEEP_ATTACK", "POISON" }));
/*     */     
/* 231 */     config.set("HealthScaled.Enabled", Boolean.valueOf(true));
/* 232 */     config.set("HealthScaled.Value", Integer.valueOf(40));
/*     */     
/* 234 */     config.set("ItemDisplayName", Boolean.valueOf(true));
/*     */     
/* 236 */     config.set("DamageCalculationToEVE", Boolean.valueOf(false));
/*     */     
/* 238 */     config.set("DamageGauges", Boolean.valueOf(true));
/*     */     
/* 240 */     config.set("DamageGauges", Boolean.valueOf(true));
/*     */     
/* 242 */     config.set("BanShieldDefense", Boolean.valueOf(false));
/*     */     
/* 244 */     config.set("ClearDefaultAttribute.ThisPlugin", Boolean.valueOf(true));
/* 245 */     config.set("ClearDefaultAttribute.All", Boolean.valueOf(false));
/* 246 */     config.set("ClearDefaultAttribute.Reset", Boolean.valueOf(false));
/*     */     
/* 248 */     config.set("RPGInventory.WhiteSlot", Arrays.asList(new Integer[] { Integer.valueOf(5), Integer.valueOf(12), Integer.valueOf(21) }));
/*     */     
/* 250 */     config.set("RandomString", Boolean.valueOf(true));
/*     */     
/* 252 */     config.set("RepairItemValue", Double.valueOf(3.5D));
/*     */     
/* 254 */     config.set("RegisterSlots.Enabled", Boolean.valueOf(false));
/* 255 */     config.set("RegisterSlots.List", Arrays.asList(new String[] { "17#戒指#421", "26#项链#421", "35#项链#421" }));
/*     */     
/* 257 */     config.set("RegisterSlots.Lock.Enabled", Boolean.valueOf(false));
/* 258 */     config.set("RegisterSlots.Lock.Name", "&7&o%SlotName%槽");
/*     */     
/* 260 */     config.set("DefaultAttribute", Arrays.asList(new String[] { "生命上限: 20", "暴击伤害: 100", "移动速度: 100" }));
/*     */     
/* 262 */     config.set("Condition.Hand.InMain.Name", "主武器");
/* 263 */     config.set("Condition.Hand.InOff.Name", "副武器");
/* 264 */     config.set("Condition.Armor", Arrays.asList(new String[] { "头盔", "盔甲", "护腿", "靴子" }));
/* 265 */     config.set("Condition.Role.Name", "限制职业");
/* 266 */     config.set("Condition.LimitLevel.Name", "限制等级");
/* 267 */     config.set("Attribute.ExpAddition.Name", "经验加成");
/* 268 */     config.set("Condition.Durability.Name", "耐久度");
/* 269 */     config.set("Condition.Durability.ClearItem", Boolean.valueOf(true));
/* 270 */     config.set("Condition.Sell.Name", "出售价格");
/* 271 */     config.set("Condition.ExpiryTime.Name", "到期时间");
/* 272 */     config.set("Condition.ExpiryTime.Format", "yyyy/MM/dd HH:mm");
/* 273 */     config.set("Attribute.Speed.Name", "移动速度");
/* 274 */     config.set("Condition.AttackSpeed.Name", "攻击速度");
/*     */     
/* 276 */     config.set("Attribute.Health.Name", "生命上限");
/* 277 */     config.set("Attribute.HealthRegen.Name", "生命恢复");
/* 278 */     config.set("Attribute.Dodge.Name", "闪避几率");
/* 279 */     config.set("Attribute.Defense.Name", "防御力");
/* 280 */     config.set("Attribute.PVPDefense.Name", "PVP防御力");
/* 281 */     config.set("Attribute.PVEDefense.Name", "PVE防御力");
/* 282 */     config.set("Attribute.Toughness.Name", "韧性");
/* 283 */     config.set("Attribute.ReflectionRate.Name", "反射几率");
/* 284 */     config.set("Attribute.Reflection.Name", "反射伤害");
/* 285 */     config.set("Attribute.BlockRate.Name", "格挡几率");
/* 286 */     config.set("Attribute.Block.Name", "格挡伤害");
/*     */     
/* 288 */     config.set("Attribute.Damage.Name", "攻击力");
/* 289 */     config.set("Attribute.PVPDamage.Name", "PVP攻击力");
/* 290 */     config.set("Attribute.PVEDamage.Name", "PVE攻击力");
/* 291 */     config.set("Attribute.HitRate.Name", "命中几率");
/* 292 */     config.set("Attribute.Real.Name", "破甲几率");
/* 293 */     config.set("Attribute.Crit.Name", "暴击几率");
/* 294 */     config.set("Attribute.CritDamage.Name", "暴击伤害");
/* 295 */     config.set("Attribute.LifeStealRate.Name", "吸血几率");
/* 296 */     config.set("Attribute.LifeSteal.Name", "吸血倍率");
/* 297 */     config.set("Attribute.Ignition.Name", "点燃几率");
/* 298 */     config.set("Attribute.Wither.Name", "凋零几率");
/* 299 */     config.set("Attribute.Poison.Name", "中毒几率");
/* 300 */     config.set("Attribute.Blindness.Name", "失明几率");
/* 301 */     config.set("Attribute.Slowness.Name", "缓慢几率");
/* 302 */     config.set("Attribute.Lightning.Name", "雷霆几率");
/* 303 */     config.set("Attribute.Tearing.Name", "撕裂几率");
/*     */     
/* 305 */     config.set("Attribute.ExpAddition.Value", Integer.valueOf(1));
/* 306 */     config.set("Attribute.Speed.Value", Integer.valueOf(1));
/*     */     
/* 308 */     config.set("Attribute.Health.Value", Integer.valueOf(1));
/* 309 */     config.set("Attribute.HealthRegen.Value", Integer.valueOf(1));
/* 310 */     config.set("Attribute.Dodge.Value", Integer.valueOf(1));
/* 311 */     config.set("Attribute.Defense.Value", Integer.valueOf(1));
/* 312 */     config.set("Attribute.PVPDefense.Value", Integer.valueOf(1));
/* 313 */     config.set("Attribute.PVEDefense.Value", Integer.valueOf(1));
/* 314 */     config.set("Attribute.Toughness.Value", Integer.valueOf(1));
/* 315 */     config.set("Attribute.ReflectionRate.Value", Integer.valueOf(1));
/* 316 */     config.set("Attribute.Reflection.Value", Integer.valueOf(1));
/* 317 */     config.set("Attribute.BlockRate.Value", Integer.valueOf(1));
/* 318 */     config.set("Attribute.Block.Value", Integer.valueOf(1));
/*     */     
/* 320 */     config.set("Attribute.Damage.Value", Integer.valueOf(1));
/* 321 */     config.set("Attribute.PVEDamage.Value", Integer.valueOf(1));
/* 322 */     config.set("Attribute.PVPDamage.Value", Integer.valueOf(1));
/* 323 */     config.set("Attribute.HitRate.Value", Integer.valueOf(1));
/* 324 */     config.set("Attribute.Real.Value", Integer.valueOf(1));
/* 325 */     config.set("Attribute.Crit.Value", Integer.valueOf(1));
/* 326 */     config.set("Attribute.CritDamage.Value", Integer.valueOf(1));
/* 327 */     config.set("Attribute.LifeSteal.Value", Integer.valueOf(1));
/* 328 */     config.set("Attribute.LifeStealRate.Value", Integer.valueOf(1));
/* 329 */     config.set("Attribute.Ignition.Value", Integer.valueOf(1));
/* 330 */     config.set("Attribute.Wither.Value", Integer.valueOf(1));
/* 331 */     config.set("Attribute.Poison.Value", Integer.valueOf(1));
/* 332 */     config.set("Attribute.Blindness.Value", Integer.valueOf(1));
/* 333 */     config.set("Attribute.Slowness.Value", Integer.valueOf(1));
/* 334 */     config.set("Attribute.Lightning.Value", Integer.valueOf(1));
/* 335 */     config.set("Attribute.Tearing.Value", Integer.valueOf(1));
/*     */     
/* 337 */     config.set("AttributePriority.Dodge", Integer.valueOf(10));
/* 338 */     config.set("AttributePriority.Damage", Integer.valueOf(20));
/* 339 */     config.set("AttributePriority.Crit", Integer.valueOf(30));
/* 340 */     config.set("AttributePriority.Real", Integer.valueOf(40));
/* 341 */     config.set("AttributePriority.Defense", Integer.valueOf(50));
/* 342 */     config.set("AttributePriority.Reflection", Integer.valueOf(60));
/* 343 */     config.set("AttributePriority.Block", Integer.valueOf(70));
/* 344 */     config.set("AttributePriority.LifeSteal", Integer.valueOf(80));
/* 345 */     config.set("AttributePriority.Ignition", Integer.valueOf(90));
/* 346 */     config.set("AttributePriority.Wither", Integer.valueOf(100));
/* 347 */     config.set("AttributePriority.Poison", Integer.valueOf(110));
/* 348 */     config.set("AttributePriority.Blindness", Integer.valueOf(120));
/* 349 */     config.set("AttributePriority.Slowness", Integer.valueOf(130));
/* 350 */     config.set("AttributePriority.Lightning", Integer.valueOf(140));
/* 351 */     config.set("AttributePriority.Tearing", Integer.valueOf(150));
/* 352 */     config.set("AttributePriority.Toughness", Integer.valueOf(200));
/* 353 */     config.set("AttributePriority.Health", Integer.valueOf(210));
/* 354 */     config.set("AttributePriority.HealthRegen", Integer.valueOf(220));
/* 355 */     config.set("AttributePriority.HitRate", Integer.valueOf(230));
/* 356 */     config.set("AttributePriority.ExpAddition", Integer.valueOf(240));
/* 357 */     config.set("AttributePriority.Speed", Integer.valueOf(250));
/* 358 */     config.set("AttributePriority.MythicmobsDrop", Integer.valueOf(260));
/* 359 */     config.set("AttributePriority.EventMessage", Integer.valueOf(270));
/*     */     
/* 361 */     config.set("ConditionPriority.Hand", Integer.valueOf(10));
/* 362 */     config.set("ConditionPriority.MainHand", Integer.valueOf(20));
/* 363 */     config.set("ConditionPriority.OffHand", Integer.valueOf(30));
/* 364 */     config.set("ConditionPriority.LimitLevel", Integer.valueOf(40));
/* 365 */     config.set("ConditionPriority.Role", Integer.valueOf(50));
/* 366 */     config.set("ConditionPriority.ExpiryTime", Integer.valueOf(60));
/* 367 */     config.set("ConditionPriority.AttackSpeed", Integer.valueOf(70));
/* 368 */     config.set("ConditionPriority.Durability", Integer.valueOf(80));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean detectionVersion() throws IOException {
/* 378 */     if (!config.getString("ConfigVersion", "").equals(SXAttribute.getPluginVersion())) {
/* 379 */       config.save(new File(FILE.toString().replace(".yml", "_" + config.getString("ConfigVersion") + ".yml")));
/* 380 */       config = new YamlConfiguration();
/* 381 */       createDefaultConfig();
/* 382 */       return true;
/*     */     } 
/* 384 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadConfig() throws IOException, InvalidConfigurationException {
/* 394 */     config = new YamlConfiguration();
/* 395 */     if (!FILE.exists()) {
/* 396 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cCreate Config.yml");
/* 397 */       createDefaultConfig();
/* 398 */       config.save(FILE);
/*     */     } else {
/* 400 */       config.load(FILE);
/* 401 */       if (detectionVersion()) {
/* 402 */         config.save(FILE);
/* 403 */         Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§eUpdate Config.yml");
/*     */       } else {
/* 405 */         Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Find Config.yml");
/*     */       } 
/*     */     } 
/* 408 */     SXAttribute.setDf(new DecimalFormat(config.getString("DecimalFormat")));
/* 409 */     commandStatsDisplaySkullSkin = config.getBoolean("CommandStatsDisplaySkullSkin");
/* 410 */     itemUpdate = config.getBoolean("ItemUpdate.Enabled");
/* 411 */     healthNameVisible = config.getBoolean("Health.NameVisible.Enabled");
/* 412 */     healthBossBar = config.getBoolean("Health.BossBar.Enabled");
/* 413 */     bossBarBlackCauseList = config.getStringList("Health.BossBar.BlackCauseList");
/* 414 */     healthScaled = config.getBoolean("HealthScaled.Enabled");
/* 415 */     holographic = config.getBoolean("Holographic.Enabled");
/* 416 */     holographicBlackList = config.getStringList("Holographic.BlackCauseList");
/* 417 */     holographicHealthTake = config.getBoolean("Holographic.HealthOrTake.Enabled");
/* 418 */     itemDisplayName = config.getBoolean("ItemDisplayName");
/* 419 */     damageCalculationToEVE = config.getBoolean("DamageCalculationToEVE");
/* 420 */     damageGauges = config.getBoolean("DamageGauges");
/* 421 */     clearDefaultAttributePlugin = config.getBoolean("ClearDefaultAttribute.ThisPlugin");
/* 422 */     clearDefaultAttributeAll = config.getBoolean("ClearDefaultAttribute.All");
/*     */ 
/*     */     
/* 425 */     clearDefaultAttributeReset = ((!clearDefaultAttributePlugin || !clearDefaultAttributeAll) && config.getBoolean("ClearDefaultAttribute.Reset"));
/* 426 */     banShieldDefense = config.getBoolean("BanShieldDefense");
/* 427 */     randomString = config.getBoolean("RandomString");
/* 428 */     registerSlot = config.getBoolean("RegisterSlots.Enabled");
/* 429 */     registerSlotsLock = (registerSlot && config.getBoolean("RegisterSlots.Lock.Enabled"));
/* 430 */     clearItemDurability = config.getBoolean("Condition.Durability.ClearItem", true);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribut\\util\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */