/*     */ package saukiya.sxattribute.command.sub;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.command.SenderType;
/*     */ import github.saukiya.sxattribute.command.SubCommand;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import github.saukiya.sxattribute.util.MoneyUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.clip.placeholderapi.PlaceholderAPI;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatsCommand
/*     */   extends SubCommand
/*     */   implements Listener
/*     */ {
/*  37 */   private final List<UUID> hideList = new ArrayList<>(); public List<UUID> getHideList() { return this.hideList; }
/*     */ 
/*     */   
/*     */   public StatsCommand() {
/*  41 */     super("stats", new SenderType[] { SenderType.PLAYER });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  46 */     super.onEnable();
/*  47 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)getPlugin());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/*  52 */     if (args.length > 1 && sender.hasPermission(plugin.getName() + ".admin")) {
/*  53 */       Player player = Bukkit.getPlayerExact(args[1]);
/*  54 */       if (player != null) {
/*  55 */         openStatsInventory(player, new Player[] { (Player)sender });
/*     */       } else {
/*  57 */         sender.sendMessage(Message.getMsg(Message.ADMIN__NO_ONLINE, new Object[0]));
/*     */       } 
/*     */     } 
/*  60 */     openStatsInventory((Player)sender, new Player[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/*  65 */     return null;
/*     */   }
/*     */   
/*     */   public void openStatsInventory(Player player, Player... openInvPlayer) {
/*  69 */     SXAttributeData attributeData = SXAttribute.getApi().getEntityAllData((LivingEntity)player, new SXAttributeData[0]);
/*  70 */     Inventory inv = Bukkit.createInventory(null, 27, Message.getMsg(Message.INVENTORY__STATS__NAME, new Object[0]));
/*  71 */     ItemStack stainedGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
/*  72 */     ItemMeta glassMeta = stainedGlass.getItemMeta();
/*  73 */     glassMeta.setDisplayName("§c");
/*  74 */     stainedGlass.setItemMeta(glassMeta);
/*  75 */     ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/*  76 */     ItemMeta skullmeta = skull.getItemMeta();
/*  77 */     List<String> skullLoreList = new ArrayList<>();
/*  78 */     if (this.hideList.contains(player.getUniqueId())) {
/*  79 */       skullLoreList.add(Message.getMsg(Message.INVENTORY__STATS__HIDE_OFF, new Object[0]));
/*     */     } else {
/*  81 */       skullLoreList.add(Message.getMsg(Message.INVENTORY__STATS__HIDE_ON, new Object[0]));
/*     */     } 
/*  83 */     skullLoreList.addAll(setPlaceholders(player, attributeData, Message.getStringList(Message.INVENTORY__STATS__SKULL_LORE, new Object[0])));
/*  84 */     if (SXAttribute.isPlaceholder()) {
/*  85 */       skullLoreList = PlaceholderAPI.setPlaceholders(player, skullLoreList);
/*     */     }
/*  87 */     skullmeta.setLore(skullLoreList);
/*  88 */     skullmeta.setDisplayName(Message.getMsg(Message.INVENTORY__STATS__SKULL_NAME, new Object[] { player.getDisplayName() }));
/*  89 */     if (Config.isCommandStatsDisplaySkullSkin()) {
/*  90 */       ((SkullMeta)skullmeta).setOwner(player.getName());
/*     */     }
/*  92 */     skull.setItemMeta(skullmeta); int i;
/*  93 */     for (i = 0; i < 9; i++) {
/*  94 */       if (i == 4) {
/*  95 */         inv.setItem(i, skull);
/*     */       } else {
/*  97 */         inv.setItem(i, stainedGlass);
/*     */       } 
/*     */     } 
/* 100 */     for (i = 18; i < 27; i++) {
/* 101 */       inv.setItem(i, stainedGlass);
/*     */     }
/* 103 */     inv.setItem(10, getAttackUI(player, attributeData));
/* 104 */     inv.setItem(13, getDefenseUI(player, attributeData));
/* 105 */     inv.setItem(16, getBaseUI(player, attributeData));
/* 106 */     if (openInvPlayer.length > 0) {
/* 107 */       openInvPlayer[0].openInventory(inv);
/*     */     } else {
/* 109 */       player.openInventory(inv);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ItemStack getAttackUI(Player player, SXAttributeData data) {
/* 114 */     ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
/* 115 */     ItemMeta meta = item.getItemMeta();
/* 116 */     meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
/* 117 */     meta.setDisplayName(Message.getMsg(Message.INVENTORY__STATS__ATTACK, new Object[0]));
/* 118 */     List<String> loreList = setPlaceholders(player, data, Message.getStringList(Message.INVENTORY__STATS__ATTACK_LORE, new Object[0]));
/* 119 */     if (SXAttribute.isPlaceholder()) {
/* 120 */       loreList = PlaceholderAPI.setPlaceholders(player, loreList);
/*     */     }
/* 122 */     if (!this.hideList.contains(player.getUniqueId())) {
/* 123 */       for (int i = loreList.size() - 1; i >= 0; i--) {
/* 124 */         if (Double.valueOf(SubAttribute.getNumber(loreList.get(i)).replace("-", "")).doubleValue() == 0.0D) {
/* 125 */           loreList.remove(i);
/*     */         }
/*     */       } 
/*     */     }
/* 129 */     meta.setLore(loreList);
/* 130 */     item.setItemMeta(meta);
/* 131 */     return item;
/*     */   }
/*     */   
/*     */   private ItemStack getDefenseUI(Player player, SXAttributeData data) {
/* 135 */     ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
/* 136 */     ItemMeta meta = item.getItemMeta();
/* 137 */     meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
/* 138 */     meta.setDisplayName(Message.getMsg(Message.INVENTORY__STATS__DEFENSE, new Object[0]));
/* 139 */     List<String> loreList = setPlaceholders(player, data, Message.getStringList(Message.INVENTORY__STATS__DEFENSE_LORE, new Object[0]));
/* 140 */     if (SXAttribute.isPlaceholder()) {
/* 141 */       loreList = PlaceholderAPI.setPlaceholders(player, loreList);
/*     */     }
/* 143 */     if (!this.hideList.contains(player.getUniqueId())) {
/* 144 */       for (int i = loreList.size() - 1; i >= 0; i--) {
/* 145 */         if (Double.valueOf(SubAttribute.getNumber(loreList.get(i)).replace("-", "")).doubleValue() == 0.0D) {
/* 146 */           loreList.remove(i);
/*     */         }
/*     */       } 
/*     */     }
/* 150 */     meta.setLore(loreList);
/* 151 */     item.setItemMeta(meta);
/* 152 */     return item;
/*     */   }
/*     */   
/*     */   private ItemStack getBaseUI(Player player, SXAttributeData data) {
/* 156 */     ItemStack item = new ItemStack(Material.BOOK);
/* 157 */     ItemMeta meta = item.getItemMeta();
/* 158 */     meta.setDisplayName(Message.getMsg(Message.INVENTORY__STATS__BASE, new Object[0]));
/* 159 */     List<String> loreList = setPlaceholders(player, data, Message.getStringList(Message.INVENTORY__STATS__BASE_LORE, new Object[0]));
/* 160 */     if (SXAttribute.isPlaceholder()) {
/* 161 */       loreList = PlaceholderAPI.setPlaceholders(player, loreList);
/*     */     }
/* 163 */     if (!this.hideList.contains(player.getUniqueId())) {
/* 164 */       for (int i = loreList.size() - 1; i >= 0; i--) {
/* 165 */         if (Double.valueOf(SubAttribute.getNumber(loreList.get(i)).replace("-", "")).doubleValue() == 0.0D) {
/* 166 */           loreList.remove(i);
/*     */         }
/*     */       } 
/*     */     }
/* 170 */     meta.setLore(loreList);
/* 171 */     item.setItemMeta(meta);
/* 172 */     return item;
/*     */   }
/*     */   
/*     */   private List<String> setPlaceholders(Player player, SXAttributeData data, List<String> list) {
/* 176 */     for (int i = 0; i < list.size(); i++) {
/* 177 */       String lore = list.get(i);
/* 178 */       while (lore.contains("%") && (lore.split("%")).length > 1 && lore.split("%")[1].contains("sx_") && (lore.split("%")[1].split("_")).length > 1) {
/* 179 */         String[] loreSplit = lore.split("%");
/* 180 */         String string = loreSplit[1].split("_")[1];
/* 181 */         String str = null;
/* 182 */         if (string.equalsIgnoreCase("Money") && SXAttribute.isVault()) {
/* 183 */           str = SXAttribute.getDf().format(MoneyUtil.get((OfflinePlayer)player));
/* 184 */         } else if (string.equalsIgnoreCase("value")) {
/* 185 */           str = SXAttribute.getDf().format(data.getValue());
/*     */         } else {
/* 187 */           for (SubAttribute attribute : data.getAttributeMap().values()) {
/* 188 */             str = attribute.getPlaceholder(player, string);
/* 189 */             if (str != null)
/*     */               break; 
/*     */           } 
/* 192 */         }  if (str != null) {
/* 193 */           lore = lore.replaceFirst("%" + loreSplit[1] + "%", str); continue;
/*     */         } 
/* 195 */         lore = lore.replaceFirst("%" + loreSplit[1] + "%", "N/A");
/*     */       } 
/*     */       
/* 198 */       list.set(i, lore);
/*     */     } 
/* 200 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   void onInventoryClickStatsEvent(InventoryClickEvent event) {
/* 206 */     if (event.getInventory().getName().equals(Message.getMsg(Message.INVENTORY__STATS__NAME, new Object[0]))) {
/* 207 */       if (event.getRawSlot() < 0) {
/* 208 */         event.getView().getPlayer().closeInventory();
/*     */         return;
/*     */       } 
/* 211 */       event.setCancelled(true);
/* 212 */       if (event.getRawSlot() == 4) {
/* 213 */         Player player = (Player)event.getView().getPlayer();
/* 214 */         if (getHideList().contains(player.getUniqueId())) {
/* 215 */           getHideList().remove(player.getUniqueId());
/*     */         } else {
/* 217 */           getHideList().add(player.getUniqueId());
/*     */         } 
/* 219 */         openStatsInventory(player, new Player[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\StatsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */