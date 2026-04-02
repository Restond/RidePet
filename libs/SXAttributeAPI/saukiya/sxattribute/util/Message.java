/*     */ package saukiya.sxattribute.util;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.stream.IntStream;
/*     */ import net.md_5.bungee.api.ChatMessageType;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public enum Message
/*     */ {
/*  28 */   MESSAGE_VERSION,
/*  29 */   PLAYER__NO_REGISTER_SLOTS,
/*  30 */   PLAYER__NO_LEVEL_USE,
/*  31 */   PLAYER__NO_ROLE,
/*  32 */   PLAYER__NO_USE_SLOT,
/*  33 */   PLAYER__OVERDUE_ITEM,
/*  34 */   PLAYER__EXP_ADDITION,
/*  35 */   PLAYER__NO_VAULT,
/*  36 */   PLAYER__NO_DURABILITY,
/*  37 */   PLAYER__SELL,
/*  38 */   PLAYER__BATTLE__FIRST_PERSON,
/*  39 */   PLAYER__BATTLE__CRIT,
/*  40 */   PLAYER__BATTLE__IGNITION,
/*  41 */   PLAYER__BATTLE__WITHER,
/*  42 */   PLAYER__BATTLE__POISON,
/*  43 */   PLAYER__BATTLE__BLINDNESS,
/*  44 */   PLAYER__BATTLE__SLOWNESS,
/*  45 */   PLAYER__BATTLE__LIGHTNING,
/*  46 */   PLAYER__BATTLE__REAL,
/*  47 */   PLAYER__BATTLE__TEARING,
/*  48 */   PLAYER__BATTLE__REFLECTION,
/*  49 */   PLAYER__BATTLE__BLOCK,
/*  50 */   PLAYER__BATTLE__DODGE,
/*  51 */   PLAYER__BATTLE__LIFE_STEAL,
/*  52 */   PLAYER__HOLOGRAPHIC__CRIT,
/*  53 */   PLAYER__HOLOGRAPHIC__IGNITION,
/*  54 */   PLAYER__HOLOGRAPHIC__WITHER,
/*  55 */   PLAYER__HOLOGRAPHIC__POISON,
/*  56 */   PLAYER__HOLOGRAPHIC__BLINDNESS,
/*  57 */   PLAYER__HOLOGRAPHIC__SLOWNESS,
/*  58 */   PLAYER__HOLOGRAPHIC__LIGHTNING,
/*  59 */   PLAYER__HOLOGRAPHIC__REAL,
/*  60 */   PLAYER__HOLOGRAPHIC__TEARING,
/*  61 */   PLAYER__HOLOGRAPHIC__REFLECTION,
/*  62 */   PLAYER__HOLOGRAPHIC__BLOCK,
/*  63 */   PLAYER__HOLOGRAPHIC__DODGE,
/*  64 */   PLAYER__HOLOGRAPHIC__LIFE_STEAL,
/*  65 */   PLAYER__HOLOGRAPHIC__DAMAGE,
/*  66 */   PLAYER__HOLOGRAPHIC__HEALTH,
/*  67 */   PLAYER__HOLOGRAPHIC__TAKE,
/*  68 */   INVENTORY__STATS__NAME,
/*  69 */   INVENTORY__STATS__HIDE_ON,
/*  70 */   INVENTORY__STATS__HIDE_OFF,
/*  71 */   INVENTORY__STATS__SKULL_NAME,
/*  72 */   INVENTORY__STATS__SKULL_LORE,
/*  73 */   INVENTORY__STATS__ATTACK,
/*  74 */   INVENTORY__STATS__ATTACK_LORE,
/*  75 */   INVENTORY__STATS__DEFENSE,
/*  76 */   INVENTORY__STATS__DEFENSE_LORE,
/*  77 */   INVENTORY__STATS__BASE,
/*  78 */   INVENTORY__STATS__BASE_LORE,
/*  79 */   INVENTORY__SELL__NAME,
/*  80 */   INVENTORY__SELL__SELL,
/*  81 */   INVENTORY__SELL__ENTER,
/*  82 */   INVENTORY__SELL__OUT,
/*  83 */   INVENTORY__SELL__NO_SELL,
/*  84 */   INVENTORY__SELL__LORE__DEFAULT,
/*  85 */   INVENTORY__SELL__LORE__FORMAT,
/*  86 */   INVENTORY__SELL__LORE__NO_SELL,
/*  87 */   INVENTORY__SELL__LORE__ALL_SELL,
/*  88 */   INVENTORY__REPAIR__NAME,
/*  89 */   INVENTORY__REPAIR__GUIDE,
/*  90 */   INVENTORY__REPAIR__ENTER,
/*  91 */   INVENTORY__REPAIR__MONEY,
/*  92 */   INVENTORY__REPAIR__NO_MONEY,
/*  93 */   INVENTORY__REPAIR__UNSUITED,
/*  94 */   INVENTORY__REPAIR__REPAIR,
/*  95 */   INVENTORY__REPAIR__LORE__ENTER,
/*  96 */   INVENTORY__REPAIR__LORE__MONEY,
/*  97 */   INVENTORY__DISPLAY_SLOTS_NAME,
/*  98 */   ADMIN__CLEAR_ENTITY_DATA,
/*  99 */   ADMIN__NO_ITEM,
/* 100 */   ADMIN__HAS_ITEM,
/* 101 */   ADMIN__GIVE_ITEM,
/* 102 */   ADMIN__SAVE_ITEM,
/* 103 */   ADMIN__SAVE_ITEM_ERROR,
/* 104 */   ADMIN__NO_PERMISSION_CMD,
/* 105 */   ADMIN__NO_CMD,
/* 106 */   ADMIN__NO_FORMAT,
/* 107 */   ADMIN__NO_CONSOLE,
/* 108 */   ADMIN__PLUGIN_RELOAD,
/* 109 */   ADMIN__NO_ONLINE,
/* 110 */   COMMAND__STATS,
/* 111 */   COMMAND__SELL,
/* 112 */   COMMAND__REPAIR,
/* 113 */   COMMAND__GIVE,
/* 114 */   COMMAND__SAVE,
/* 115 */   COMMAND__NBT,
/* 116 */   COMMAND__DISPLAYSLOT,
/* 117 */   COMMAND__ATTRIBUTELIST,
/* 118 */   COMMAND__CONDITIONLIST,
/* 119 */   COMMAND__RELOAD,
/* 120 */   REPLACE_LIST; private static final File FILE; private static final String messagePrefix;
/*     */   private static YamlConfiguration messages;
/*     */   
/* 123 */   static { FILE = new File(SXAttribute.getPluginFile(), "Message.yml");
/*     */ 
/*     */     
/* 126 */     messagePrefix = "[" + SXAttribute.getPluginName() + "] "; }
/*     */    public static YamlConfiguration getMessages() {
/* 128 */     return messages;
/*     */   } public static String getMessagePrefix() {
/*     */     return messagePrefix;
/*     */   } private static void createDefaultMessage() {
/* 132 */     messages.set(MESSAGE_VERSION.toString(), SXAttribute.getPluginVersion());
/*     */     
/* 134 */     messages.set(PLAYER__NO_REGISTER_SLOTS.toString(), getMessagePrefix() + "&c服务器没有开启额外的槽位识别");
/* 135 */     messages.set(PLAYER__NO_LEVEL_USE.toString(), getMessagePrefix() + "&c你没有达到使用 &a{0} &c的等级要求!");
/* 136 */     messages.set(PLAYER__NO_ROLE.toString(), getMessagePrefix() + "&c你没有达到使用 &a{0} &c的职业要求!");
/* 137 */     messages.set(PLAYER__NO_USE_SLOT.toString(), getMessagePrefix() + "&7物品 &a{0} &7属于 &a{1}&7 类型!");
/* 138 */     messages.set(PLAYER__OVERDUE_ITEM.toString(), getMessagePrefix() + "&c物品 &a{0}&c 已经过期了:&a{1}");
/* 139 */     messages.set(PLAYER__EXP_ADDITION.toString(), getMessagePrefix() + "&7你的经验增加了 &6{0}&7! [&a+{1}%&7]");
/* 140 */     messages.set(PLAYER__NO_VAULT.toString(), getMessagePrefix() + "&c服务器没有启用经济系统: Vault-Economy null");
/* 141 */     messages.set(PLAYER__NO_DURABILITY.toString(), getMessagePrefix() + "&c物品 &a{0}&c 耐久度已经为零了!");
/* 142 */     messages.set(PLAYER__SELL.toString(), getMessagePrefix() + "&7出售成功! 一共出售了 &6{0}&7 个物品，总价 &6{1}&7 金币!");
/*     */     
/* 144 */     List<String> attackLoreList = new ArrayList<>();
/*     */     
/* 146 */     attackLoreList.add("&c攻击力:&b %sx_damage%");
/* 147 */     attackLoreList.add("&cPVP攻击力:&b %sx_pvpDamage%");
/* 148 */     attackLoreList.add("&cPVE攻击力:&b %sx_pveDamage%");
/* 149 */     attackLoreList.add("&a命中几率:&b %sx_hitRate%%");
/* 150 */     attackLoreList.add("&6破甲几率:&b %sx_real%%");
/* 151 */     attackLoreList.add("&c暴击几率:&b %sx_critRate%%");
/* 152 */     attackLoreList.add("&4暴击伤害:&b %sx_crit%%");
/* 153 */     attackLoreList.add("&6吸血几率:&b %sx_lifeStealRate%%");
/* 154 */     attackLoreList.add("&6吸血倍率:&b %sx_lifeSteal%%");
/* 155 */     attackLoreList.add("&c点燃几率:&b %sx_ignition%%");
/* 156 */     attackLoreList.add("&9凋零几率:&b %sx_wither%%");
/* 157 */     attackLoreList.add("&d中毒几率:&b %sx_poison%%");
/* 158 */     attackLoreList.add("&3失明几率:&b %sx_blindness%%");
/* 159 */     attackLoreList.add("&3缓慢几率:&b %sx_slowness%%");
/* 160 */     attackLoreList.add("&e雷霆几率:&b %sx_lightning%%");
/* 161 */     attackLoreList.add("&c撕裂几率:&b %sx_tearing%%");
/*     */     
/* 163 */     List<String> defenseLoreList = new ArrayList<>();
/*     */     
/* 165 */     defenseLoreList.add("&6防御力:&b %sx_defense%");
/* 166 */     defenseLoreList.add("&6PVP防御力:&b %sx_pvpDefense%");
/* 167 */     defenseLoreList.add("&6PVE防御力:&b %sx_pveDefense%");
/* 168 */     defenseLoreList.add("&a生命上限:&b %sx_health%/%sx_maxHealth%");
/* 169 */     defenseLoreList.add("&a生命恢复:&b %sx_healthRegen%");
/* 170 */     defenseLoreList.add("&d闪避几率:&b %sx_dodge%%");
/* 171 */     defenseLoreList.add("&9韧性:&b %sx_toughness%%");
/* 172 */     defenseLoreList.add("&c反射几率:&b %sx_reflectionRate%%");
/* 173 */     defenseLoreList.add("&c反射伤害:&b %sx_reflection%%");
/* 174 */     defenseLoreList.add("&2格挡几率:&b %sx_blockRate%%");
/* 175 */     defenseLoreList.add("&2格挡伤害:&b %sx_block%%");
/*     */     
/* 177 */     messages.set(INVENTORY__STATS__NAME.toString(), "&d&l&oSX-Attribute");
/* 178 */     messages.set(INVENTORY__STATS__HIDE_ON.toString(), "&a点击显示更多属性");
/* 179 */     messages.set(INVENTORY__STATS__HIDE_OFF.toString(), "&c点击隐藏更多属性");
/* 180 */     messages.set(INVENTORY__STATS__SKULL_NAME.toString(), "&6&l&o{0} 的属性");
/* 181 */     messages.set(INVENTORY__STATS__SKULL_LORE.toString(), Collections.singletonList("&d战斗力:&b %sx_value%"));
/* 182 */     messages.set(INVENTORY__STATS__ATTACK.toString(), "&a&l&o攻击属性");
/* 183 */     messages.set(INVENTORY__STATS__ATTACK_LORE.toString(), attackLoreList);
/* 184 */     messages.set(INVENTORY__STATS__DEFENSE.toString(), "&9&l&o防御属性");
/* 185 */     messages.set(INVENTORY__STATS__DEFENSE_LORE.toString(), defenseLoreList);
/* 186 */     messages.set(INVENTORY__STATS__BASE.toString(), "&9&l&o其他属性");
/* 187 */     messages.set(INVENTORY__STATS__BASE_LORE.toString(), Arrays.asList(new String[] { "&e经验加成:&b %sx_expAddition%%", "&b速度:&b %sx_speed%%" }));
/*     */     
/* 189 */     messages.set(INVENTORY__SELL__NAME.toString(), "&6&l出售物品");
/* 190 */     messages.set(INVENTORY__SELL__SELL.toString(), "&e&l点击出售");
/* 191 */     messages.set(INVENTORY__SELL__ENTER.toString(), "&c&l确认出售");
/* 192 */     messages.set(INVENTORY__SELL__OUT.toString(), "&6出售完毕:&e {0} 金币");
/* 193 */     messages.set(INVENTORY__SELL__NO_SELL.toString(), "&c&l不可出售");
/* 194 */     messages.set(INVENTORY__SELL__LORE__DEFAULT.toString(), Collections.singletonList("&7&o请放入你要出售的物品"));
/* 195 */     messages.set(INVENTORY__SELL__LORE__FORMAT.toString(), "&b[{0}] &a{1}&7 - &7{2}&e 金币");
/* 196 */     messages.set(INVENTORY__SELL__LORE__NO_SELL.toString(), "&b[{0}] &4不可出售");
/* 197 */     messages.set(INVENTORY__SELL__LORE__ALL_SELL.toString(), "&e总金额: {0}");
/*     */     
/* 199 */     messages.set(INVENTORY__REPAIR__NAME.toString(), "&9&l修理物品");
/* 200 */     messages.set(INVENTORY__REPAIR__GUIDE.toString(), "&7&o待修理物品放入凹槽");
/* 201 */     messages.set(INVENTORY__REPAIR__ENTER.toString(), "&e&l点击修理");
/* 202 */     messages.set(INVENTORY__REPAIR__MONEY.toString(), "&c&l确认修理");
/* 203 */     messages.set(INVENTORY__REPAIR__NO_MONEY.toString(), "&c&l金额不足");
/* 204 */     messages.set(INVENTORY__REPAIR__UNSUITED.toString(), "&4&l不可修理");
/* 205 */     messages.set(INVENTORY__REPAIR__REPAIR.toString(), "&6修理成功:&e {0} 金币");
/* 206 */     messages.set(INVENTORY__REPAIR__LORE__ENTER.toString(), Collections.singletonList("&7&o价格: {0}/破损值"));
/* 207 */     messages.set(INVENTORY__REPAIR__LORE__MONEY.toString(), Arrays.asList(new String[] { "&c破损值: {0} 耐久", "&e价格: {1} 金币", "&7&o价格: {2}/破损值" }));
/*     */     
/* 209 */     messages.set(INVENTORY__DISPLAY_SLOTS_NAME.toString(), "&9&l槽位展示");
/*     */     
/* 211 */     messages.set(PLAYER__BATTLE__FIRST_PERSON.toString(), "你");
/* 212 */     messages.set(PLAYER__BATTLE__CRIT.toString(), "[ACTIONBAR]&c{0}&6 对 &c{1}&6 造成了暴击!");
/* 213 */     messages.set(PLAYER__BATTLE__IGNITION.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 点燃了!");
/* 214 */     messages.set(PLAYER__BATTLE__WITHER.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 凋零了!");
/* 215 */     messages.set(PLAYER__BATTLE__POISON.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 中毒了!");
/* 216 */     messages.set(PLAYER__BATTLE__BLINDNESS.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 致盲了!");
/* 217 */     messages.set(PLAYER__BATTLE__SLOWNESS.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 减速了!");
/* 218 */     messages.set(PLAYER__BATTLE__LIGHTNING.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 用雷电击中了!");
/* 219 */     messages.set(PLAYER__BATTLE__REAL.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 破甲了!");
/* 220 */     messages.set(PLAYER__BATTLE__TEARING.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 撕裂了!");
/* 221 */     messages.set(PLAYER__BATTLE__REFLECTION.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 反弹伤害了!");
/* 222 */     messages.set(PLAYER__BATTLE__BLOCK.toString(), "[ACTIONBAR]&c{0}&6 格挡了 &c{1}&6 的部分伤害!");
/* 223 */     messages.set(PLAYER__BATTLE__DODGE.toString(), "[ACTIONBAR]&c{0}&6 躲开了 &c{1}&6 的攻击!");
/* 224 */     messages.set(PLAYER__BATTLE__LIFE_STEAL.toString(), "[ACTIONBAR]&c{0}&6 被 &c{1}&6 偷取生命了!");
/*     */     
/* 226 */     messages.set(PLAYER__HOLOGRAPHIC__CRIT.toString(), "&a&o暴击: &b&o+{0}");
/* 227 */     messages.set(PLAYER__HOLOGRAPHIC__IGNITION.toString(), "&c&o点燃: &b&o{0}s");
/* 228 */     messages.set(PLAYER__HOLOGRAPHIC__WITHER.toString(), "&7&o凋零: &b&o{0}s");
/* 229 */     messages.set(PLAYER__HOLOGRAPHIC__POISON.toString(), "&5&o中毒: &b&o{0}s");
/* 230 */     messages.set(PLAYER__HOLOGRAPHIC__BLINDNESS.toString(), "&8&o致盲: &b&o{0}s");
/* 231 */     messages.set(PLAYER__HOLOGRAPHIC__SLOWNESS.toString(), "&b&o减速: &b&o{0}s");
/* 232 */     messages.set(PLAYER__HOLOGRAPHIC__LIGHTNING.toString(), "&e&o雷霆: &b&o{0}");
/* 233 */     messages.set(PLAYER__HOLOGRAPHIC__REAL.toString(), "&c&o破甲");
/* 234 */     messages.set(PLAYER__HOLOGRAPHIC__TEARING.toString(), "&c&o撕裂: &b{0}");
/* 235 */     messages.set(PLAYER__HOLOGRAPHIC__REFLECTION.toString(), "&6&o反伤: &b&o{0}");
/* 236 */     messages.set(PLAYER__HOLOGRAPHIC__BLOCK.toString(), "&2&o格挡: &b&o{0}");
/* 237 */     messages.set(PLAYER__HOLOGRAPHIC__DODGE.toString(), "&a&o闪避");
/* 238 */     messages.set(PLAYER__HOLOGRAPHIC__LIFE_STEAL.toString(), "&c&o吸取: &b&o{0}");
/* 239 */     messages.set(PLAYER__HOLOGRAPHIC__DAMAGE.toString(), "&c&o伤害: &b&o{0}");
/* 240 */     messages.set(PLAYER__HOLOGRAPHIC__TAKE.toString(), "&c&o- {0}");
/* 241 */     messages.set(PLAYER__HOLOGRAPHIC__HEALTH.toString(), "&e&o+ {0}");
/*     */     
/* 243 */     messages.set(ADMIN__CLEAR_ENTITY_DATA.toString(), getMessagePrefix() + "&c清理了 &6{0}&c 个多余的生物属性数据!");
/* 244 */     messages.set(ADMIN__NO_ITEM.toString(), getMessagePrefix() + "&c物品不存在!");
/* 245 */     messages.set(ADMIN__HAS_ITEM.toString(), getMessagePrefix() + "&c已经存在名字为  &6{0}&c的物品!");
/* 246 */     messages.set(ADMIN__GIVE_ITEM.toString(), getMessagePrefix() + "&c给予 &6{0} &a{1}&c个 &6{2}&c 物品!");
/* 247 */     messages.set(ADMIN__SAVE_ITEM.toString(), getMessagePrefix() + "&a物品 &6{0} &a成功保存! 编号为: &6{1}&a!");
/* 248 */     messages.set(ADMIN__SAVE_ITEM_ERROR.toString(), getMessagePrefix() + "&c物品 &4{0} &c保存出现不可预知的错误 [&4{1}&c]");
/* 249 */     messages.set(ADMIN__NO_PERMISSION_CMD.toString(), getMessagePrefix() + "&c你没有权限执行此指令");
/* 250 */     messages.set(ADMIN__NO_CMD.toString(), getMessagePrefix() + "&c未找到此子指令:{0}");
/* 251 */     messages.set(ADMIN__NO_FORMAT.toString(), getMessagePrefix() + "&c格式错误!");
/* 252 */     messages.set(ADMIN__NO_ONLINE.toString(), getMessagePrefix() + "&c玩家不在线或玩家不存在!");
/* 253 */     messages.set(ADMIN__NO_CONSOLE.toString(), getMessagePrefix() + "&c控制台不允许执行此指令!");
/* 254 */     messages.set(ADMIN__PLUGIN_RELOAD.toString(), getMessagePrefix() + "§c插件已重载");
/*     */     
/* 256 */     messages.set(COMMAND__STATS.toString(), "查看属性");
/* 257 */     messages.set(COMMAND__SELL.toString(), "打开出售界面");
/* 258 */     messages.set(COMMAND__REPAIR.toString(), "打开修理界面");
/* 259 */     messages.set(COMMAND__GIVE.toString(), "给予玩家RPG物品");
/* 260 */     messages.set(COMMAND__SAVE.toString(), "保存当前的物品到配置文件 加[-a]完全保存");
/* 261 */     messages.set(COMMAND__NBT.toString(), "查看当前手持物品的NBT数据");
/* 262 */     messages.set(COMMAND__DISPLAYSLOT.toString(), "显示可装载物品的槽位");
/* 263 */     messages.set(COMMAND__ATTRIBUTELIST.toString(), "查看当前属性列表");
/* 264 */     messages.set(COMMAND__CONDITIONLIST.toString(), "查看当前条件列表");
/* 265 */     messages.set(COMMAND__RELOAD.toString(), "重新加载这个插件的配置");
/*     */     
/* 267 */     messages.set(REPLACE_LIST.toString() + ".Pig", "猪猪");
/* 268 */     messages.set(REPLACE_LIST.toString() + ".Sheep", "羊羊");
/* 269 */     messages.set(REPLACE_LIST.toString() + ".Rabbit", "兔兔");
/* 270 */     messages.set(REPLACE_LIST.toString() + ".Mule", "骡骡");
/* 271 */     messages.set(REPLACE_LIST.toString() + ".Skeleton", "骷髅");
/* 272 */     messages.set(REPLACE_LIST.toString() + ".Zombie", "僵尸");
/* 273 */     messages.set(REPLACE_LIST.toString() + ".Silverfish", "蠢虫");
/* 274 */     messages.set(REPLACE_LIST.toString() + ".Horse", "马马");
/* 275 */     messages.set(REPLACE_LIST.toString() + ".Cow", "牛牛");
/* 276 */     messages.set(REPLACE_LIST.toString() + ".Chicken", "鸡鸡");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean detectionVersion() throws IOException {
/* 286 */     if (!messages.getString(MESSAGE_VERSION.toString(), "").equals(SXAttribute.getPluginVersion())) {
/* 287 */       messages.save(new File(FILE.toString().replace(".yml", "_" + messages.getString(MESSAGE_VERSION.toString()) + ".yml")));
/* 288 */       messages = new YamlConfiguration();
/* 289 */       createDefaultMessage();
/* 290 */       return true;
/*     */     } 
/* 292 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadMessage() throws IOException, InvalidConfigurationException {
/* 302 */     messages = new YamlConfiguration();
/* 303 */     if (!FILE.exists()) {
/* 304 */       Bukkit.getConsoleSender().sendMessage(messagePrefix + "§cCreate Message.yml");
/* 305 */       createDefaultMessage();
/* 306 */       messages.save(FILE);
/*     */     } else {
/* 308 */       messages.load(FILE);
/* 309 */       if (detectionVersion()) {
/* 310 */         Bukkit.getConsoleSender().sendMessage(messagePrefix + "§eUpdate Message.yml");
/* 311 */         messages.save(FILE);
/*     */       } else {
/* 313 */         Bukkit.getConsoleSender().sendMessage(messagePrefix + "Find Message.yml");
/*     */       } 
/*     */     } 
/* 316 */     SubAttribute.setFirstPerson(getMsg(PLAYER__BATTLE__FIRST_PERSON, new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replace(String str) {
/* 326 */     if (str == null) {
/* 327 */       return null;
/*     */     }
/* 329 */     if (messages.contains(REPLACE_LIST.toString())) {
/* 330 */       for (String replaceName : messages.getConfigurationSection(REPLACE_LIST.toString()).getKeys(false)) {
/* 331 */         str = str.replace(replaceName, messages.getString(REPLACE_LIST.toString() + "." + replaceName));
/*     */       }
/*     */     }
/* 334 */     return ChatColor.translateAlternateColorCodes('&', str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMsg(github.saukiya.sxattribute.util.Message loc, Object... args) {
/* 345 */     return ChatColor.translateAlternateColorCodes('&', MessageFormat.format(messages.getString(loc.toString(), "Null Message: " + loc), args));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getStringList(github.saukiya.sxattribute.util.Message loc, Object... args) {
/* 356 */     List<String> list = messages.getStringList(loc.toString());
/* 357 */     if (list.size() == 0) return Collections.singletonList("Null Message: " + loc); 
/* 358 */     int bound = list.size();
/* 359 */     IntStream.range(0, bound).forEach(i -> (String)list.set(i, ChatColor.translateAlternateColorCodes('&', MessageFormat.format(list.get(i), args))));
/* 360 */     return list;
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
/*     */   public static TextComponent getTextComponent(String message, String command, List<String> stringList) {
/* 372 */     TextComponent tcMessage = new TextComponent(message);
/* 373 */     if (stringList != null && stringList.size() > 0) {
/* 374 */       ComponentBuilder bc = new ComponentBuilder("§7" + ((String)stringList.get(0)).replace("&", "§"));
/* 375 */       IntStream.range(1, stringList.size()).mapToObj(i -> "\n§7" + ((String)stringList.get(i)).replace("&", "§")).forEach(bc::append);
/* 376 */       tcMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, bc.create()));
/*     */     } 
/* 378 */     if (command != null) {
/* 379 */       tcMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
/*     */     }
/* 381 */     return tcMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void send(LivingEntity entity, github.saukiya.sxattribute.util.Message loc, Object... args) {
/* 392 */     if (entity instanceof Player) {
/* 393 */       send((Player)entity, getMsg(loc, args));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void send(Player player, String message) {
/* 404 */     if (message.contains("Null Message"))
/* 405 */       return;  if (message.contains("[ACTIONBAR]")) {
/* 406 */       message = message.replace("[ACTIONBAR]", "");
/* 407 */       player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(message));
/* 408 */     } else if (message.contains("[TITLE]")) {
/* 409 */       message = message.replace("[TITLE]", "");
/* 410 */       if (message.contains(":")) {
/* 411 */         String title = message.split(":")[0];
/* 412 */         String subTitle = message.split(":")[1];
/* 413 */         player.sendTitle(title, subTitle, 5, 20, 5);
/*     */       } else {
/* 415 */         player.sendTitle(message, null, 5, 20, 5);
/*     */       } 
/*     */     } else {
/* 418 */       player.sendMessage(message);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 424 */     return name().replace("__", ".");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribut\\util\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */