/*     */ package saukiya.sxattribute.data.condition;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.condition.ConditionMap;
/*     */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
/*     */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import github.saukiya.sxlevel.SXLevel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SubCondition
/*     */ {
/*  25 */   static final ConditionMap conditionMap = new ConditionMap();
/*     */   
/*     */   private final String name;
/*     */   
/*  29 */   private JavaPlugin plugin = null;
/*     */   
/*  31 */   private SXConditionType[] updateTypes = new SXConditionType[] { SXConditionType.ALL };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubCondition(String name, SXConditionType... type) {
/*  40 */     this.name = name;
/*  41 */     this.updateTypes = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubCondition(String name) {
/*  50 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getItemName(ItemStack item) {
/*  60 */     return (item == null) ? "N/A" : (item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : item.getType().name());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getItemLevel(ItemStack item) {
/*  70 */     if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
/*  71 */       return ((Integer)item.getItemMeta().getLore().stream().filter(lore -> lore.contains(Config.getConfig().getString("Condition.LimitLevel.Name"))).findFirst().map(lore -> Integer.valueOf(getNumber(lore).replace(".", ""))).orElse(Integer.valueOf(-1))).intValue();
/*     */     }
/*  73 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getLevel(LivingEntity entity) {
/*  83 */     return (entity instanceof Player) ? (SXAttribute.isSxLevel() ? SXLevel.getApi().getPlayerData((Player)entity).getLevel() : ((Player)entity).getLevel()) : 10000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNumber(String lore) {
/*  93 */     String str = lore.replaceAll("§+[a-z0-9]", "").replaceAll("[^-0-9.]", "");
/*  94 */     return (str.length() == 0) ? "0" : str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDurability(String lore) {
/* 105 */     return (lore.contains("/") && (lore.split("/")).length > 1) ? Integer.valueOf(lore.replaceAll("§+[0-9]", "").split("/")[0].replaceAll("[^0-9]", "")).intValue() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMaxDurability(String lore) {
/* 115 */     return (lore.contains("/") && (lore.split("/")).length > 1) ? Integer.valueOf(lore.replaceAll("§+[0-9]", "").split("/")[1].replaceAll("[^0-9]", "")).intValue() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getUnbreakable(ItemMeta meta) {
/* 125 */     return (SXAttribute.getVersionSplit()[1] >= 11) ? meta.isUnbreakable() : meta.spigot().isUnbreakable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getName() {
/* 134 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final SXConditionType[] getType() {
/* 143 */     return (SXConditionType[])this.updateTypes.clone();
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
/*     */   public final boolean containsType(SXConditionType type, boolean strContains) {
/* 164 */     return (type != null && Arrays.<SXConditionType>stream(this.updateTypes).anyMatch(updateType -> (updateType.equals(SXConditionType.ALL) || (strContains ? type.getName().contains(updateType.getName()) : updateType.equals(type)))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void registerCondition(JavaPlugin plugin) {
/* 174 */     if (plugin == null) {
/* 175 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cCondition >> §4" + getName() + " §cNull Plugin!");
/* 176 */     } else if (getPriority() < 0) {
/* 177 */       Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] §8Condition >> Disable §4" + getName() + " §8!");
/* 178 */     } else if (SXAttribute.isPluginEnabled()) {
/* 179 */       Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] §cCondition >> §cSXAttribute is Enabled §4" + getName() + "§r !");
/*     */     } else {
/* 181 */       this.plugin = plugin;
/* 182 */       github.saukiya.sxattribute.data.condition.SubCondition condition = conditionMap.put(Integer.valueOf(getPriority()), this);
/* 183 */       if (condition == null) {
/* 184 */         Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] Condition >> Register §c" + getName() + " §rTo Priority §c" + getPriority() + " §r!");
/*     */       } else {
/* 186 */         Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] Condition >> §cThe §4" + getName() + " §cCover To §4" + condition.getName() + " §7[§c" + condition.getPlugin() + "§7]§r !");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> introduction() {
/* 211 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getPriority() {
/* 220 */     return Config.getConfig().getInt("ConditionPriority." + getName(), -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JavaPlugin getPlugin() {
/* 229 */     return this.plugin;
/*     */   }
/*     */   
/*     */   public abstract SXConditionReturnType determine(LivingEntity paramLivingEntity, ItemStack paramItemStack, String paramString);
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\SubCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */