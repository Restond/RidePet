/*     */ package saukiya.sxattribute.command.sub;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.command.SenderType;
/*     */ import github.saukiya.sxattribute.command.SubCommand;
/*     */ import github.saukiya.sxattribute.data.ItemDataManager;
/*     */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import github.saukiya.sxattribute.util.MoneyUtil;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RepairCommand
/*     */   extends SubCommand
/*     */   implements Listener
/*     */ {
/*     */   public RepairCommand() {
/*  35 */     super("repair", new SenderType[] { SenderType.PLAYER });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void openRepairInventory(Player player) {
/*  44 */     Inventory inv = Bukkit.createInventory(null, 45, Message.getMsg(Message.INVENTORY__REPAIR__NAME, new Object[0]));
/*  45 */     ItemStack glassItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
/*  46 */     ItemMeta glassMeta = glassItem.getItemMeta();
/*  47 */     glassMeta.setDisplayName("§r");
/*  48 */     glassItem.setItemMeta(glassMeta); int i;
/*  49 */     for (i = 0; i < 9; i++) {
/*  50 */       inv.setItem(i, glassItem);
/*  51 */       inv.setItem(36 + i, glassItem);
/*     */     } 
/*  53 */     glassItem.setDurability((short)9);
/*  54 */     for (i = 0; i < 5; i++) {
/*  55 */       if (i != 2) {
/*  56 */         inv.setItem(9 + i, glassItem);
/*  57 */         inv.setItem(18 + i, glassItem);
/*  58 */         inv.setItem(27 + i, glassItem);
/*     */       } 
/*  60 */     }  glassItem.setDurability((short)7);
/*  61 */     for (i = 6; i < 9; i++) {
/*  62 */       inv.setItem(9 + i, glassItem);
/*  63 */       inv.setItem(18 + i, glassItem);
/*  64 */       inv.setItem(27 + i, glassItem);
/*     */     } 
/*  66 */     glassItem.setDurability((short)0);
/*  67 */     glassItem.setType(Material.IRON_FENCE);
/*  68 */     for (i = 1; i < 4; i++) {
/*  69 */       inv.setItem(5 + i * 9, glassItem);
/*     */     }
/*  71 */     glassItem.setType(Material.THIN_GLASS);
/*  72 */     glassMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__GUIDE, new Object[0]));
/*  73 */     glassItem.setItemMeta(glassMeta);
/*  74 */     inv.setItem(11, glassItem);
/*  75 */     inv.setItem(19, glassItem);
/*  76 */     inv.setItem(21, glassItem);
/*  77 */     inv.setItem(29, glassItem);
/*  78 */     ItemStack enterItem = new ItemStack(Material.ANVIL);
/*  79 */     ItemMeta enterMeta = enterItem.getItemMeta();
/*  80 */     enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__ENTER, new Object[0]));
/*  81 */     enterMeta.setLore(Message.getStringList(Message.INVENTORY__REPAIR__LORE__ENTER, new Object[] { Double.valueOf(Config.getConfig().getDouble("RepairItemValue")) }));
/*  82 */     enterMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
/*  83 */     enterItem.setItemMeta(enterMeta);
/*  84 */     inv.setItem(25, enterItem);
/*     */     
/*  86 */     player.openInventory(inv);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  91 */     super.onEnable();
/*  92 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)getPlugin());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/*  97 */     if (SXAttribute.isVault()) {
/*  98 */       openRepairInventory((Player)sender);
/*     */     } else {
/* 100 */       sender.sendMessage(Message.getMsg(Message.PLAYER__NO_VAULT, new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hide() {
/* 106 */     return !SXAttribute.isVault();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 111 */     return null;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onInventoryClickRepairEvent(InventoryClickEvent event) {
/* 116 */     if (event.getInventory().getName().equals(Message.getMsg(Message.INVENTORY__REPAIR__NAME, new Object[0]))) {
/* 117 */       if (event.getRawSlot() < 0) {
/* 118 */         event.getView().getPlayer().closeInventory();
/*     */         return;
/*     */       } 
/* 121 */       Inventory inv = event.getInventory();
/* 122 */       ItemStack enterItem = inv.getItem(25);
/* 123 */       ItemMeta enterMeta = enterItem.getItemMeta();
/* 124 */       Player player = (Player)event.getView().getPlayer();
/* 125 */       double value = Config.getConfig().getDouble("RepairItemValue");
/* 126 */       if (event.getRawSlot() != 20 && event.getRawSlot() < 45) {
/* 127 */         event.setCancelled(true);
/* 128 */         if (event.getRawSlot() == 25) {
/* 129 */           ItemStack item = inv.getItem(20);
/* 130 */           if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta().hasLore() && item.getAmount() == 1) {
/* 131 */             ItemMeta meta = item.getItemMeta();
/* 132 */             List<String> loreList = meta.getLore();
/* 133 */             for (int i = 0; i < loreList.size(); i++) {
/* 134 */               String lore = loreList.get(i);
/* 135 */               if (lore.contains(Config.getConfig().getString("Condition.Durability.Name"))) {
/* 136 */                 int durability = SubCondition.getDurability(lore);
/* 137 */                 int maxDurability = SubCondition.getMaxDurability(lore);
/* 138 */                 if (maxDurability != 0 && durability < maxDurability) {
/* 139 */                   double money = (maxDurability - durability) * value;
/* 140 */                   if (!enterMeta.hasEnchants()) {
/* 141 */                     if (MoneyUtil.has((OfflinePlayer)player, money)) {
/* 142 */                       enterMeta.addEnchant(Enchantment.DURABILITY, 1, true);
/* 143 */                       enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__MONEY, new Object[0]));
/*     */                     } else {
/* 145 */                       enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__NO_MONEY, new Object[0]));
/*     */                     } 
/* 147 */                     enterMeta.setLore(Message.getStringList(Message.INVENTORY__REPAIR__LORE__MONEY, new Object[] { Integer.valueOf(maxDurability - durability), Double.valueOf(money), Double.valueOf(value) }));
/*     */                   } else {
/* 149 */                     enterMeta.removeEnchant(Enchantment.DURABILITY);
/* 150 */                     if (MoneyUtil.has((OfflinePlayer)player, money)) {
/* 151 */                       MoneyUtil.take((OfflinePlayer)player, money);
/* 152 */                       enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__REPAIR, new Object[] { Double.valueOf(money) }));
/* 153 */                       enterMeta.setLore(Message.getStringList(Message.INVENTORY__REPAIR__LORE__ENTER, new Object[] { Double.valueOf(value) }));
/*     */                       
/* 155 */                       lore = ItemDataManager.replaceColor(ItemDataManager.clearColor(lore).replaceFirst(String.valueOf(durability), String.valueOf(maxDurability)));
/* 156 */                       loreList.set(i, lore);
/* 157 */                       meta.setLore(loreList);
/* 158 */                       if (!SubCondition.getUnbreakable(meta) && item.getType().getMaxDurability() != 0) {
/* 159 */                         item.setDurability((short)0);
/*     */                       }
/* 161 */                       item.setItemMeta(meta);
/*     */                     } else {
/* 163 */                       enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__NO_MONEY, new Object[0]));
/*     */                     } 
/*     */                   } 
/* 166 */                   enterItem.setItemMeta(enterMeta);
/*     */                   return;
/*     */                 } 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 173 */           enterMeta.removeEnchant(Enchantment.DURABILITY);
/* 174 */           enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__UNSUITED, new Object[0]));
/* 175 */           enterMeta.setLore(Message.getStringList(Message.INVENTORY__REPAIR__LORE__ENTER, new Object[] { Double.valueOf(value) }));
/* 176 */           enterItem.setItemMeta(enterMeta);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 181 */       enterMeta.removeEnchant(Enchantment.DURABILITY);
/* 182 */       enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__REPAIR__ENTER, new Object[0]));
/* 183 */       enterMeta.setLore(Message.getStringList(Message.INVENTORY__REPAIR__LORE__ENTER, new Object[] { Double.valueOf(value) }));
/* 184 */       enterItem.setItemMeta(enterMeta);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onInventoryCloseRepairEvent(InventoryCloseEvent event) {
/* 190 */     if (event.getInventory().getName().equals(Message.getMsg(Message.INVENTORY__REPAIR__NAME, new Object[0]))) {
/* 191 */       Player player = (Player)event.getView().getPlayer();
/* 192 */       Inventory inv = event.getInventory();
/* 193 */       ItemStack item = inv.getItem(20);
/* 194 */       if (item != null && !item.getType().equals(Material.AIR)) {
/* 195 */         player.getInventory().addItem(new ItemStack[] { item });
/*     */       }
/* 197 */       inv.clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\RepairCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */