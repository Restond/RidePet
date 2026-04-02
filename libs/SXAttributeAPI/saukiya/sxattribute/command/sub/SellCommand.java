/*     */ package saukiya.sxattribute.command.sub;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.command.SenderType;
/*     */ import github.saukiya.sxattribute.command.SubCommand;
/*     */ import github.saukiya.sxattribute.data.ItemDataManager;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import github.saukiya.sxattribute.util.MoneyUtil;
/*     */ import java.util.ArrayList;
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
/*     */ public class SellCommand
/*     */   extends SubCommand
/*     */   implements Listener
/*     */ {
/*     */   public SellCommand() {
/*  34 */     super("sell", new SenderType[] { SenderType.PLAYER });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void openSellInventory(Player player) {
/*  43 */     Inventory inv = Bukkit.createInventory(null, 27, Message.getMsg(Message.INVENTORY__SELL__NAME, new Object[0]));
/*  44 */     ItemStack stainedGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
/*  45 */     ItemMeta glassMeta = stainedGlass.getItemMeta();
/*  46 */     glassMeta.setDisplayName("§r");
/*  47 */     stainedGlass.setItemMeta(glassMeta);
/*  48 */     for (int i = 18; i < 27; i++) {
/*  49 */       inv.setItem(i, stainedGlass);
/*     */     }
/*     */     
/*  52 */     ItemStack enterItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
/*  53 */     ItemMeta enterMeta = enterItem.getItemMeta();
/*  54 */     enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__SELL__SELL, new Object[0]));
/*  55 */     enterMeta.setLore(Message.getStringList(Message.INVENTORY__SELL__LORE__DEFAULT, new Object[0]));
/*  56 */     enterMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
/*  57 */     enterItem.setItemMeta(enterMeta);
/*  58 */     inv.setItem(22, enterItem);
/*     */     
/*  60 */     player.openInventory(inv);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  65 */     super.onEnable();
/*  66 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)getPlugin());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/*  71 */     if (SXAttribute.isVault()) {
/*  72 */       openSellInventory((Player)sender);
/*     */     } else {
/*  74 */       sender.sendMessage(Message.getMsg(Message.PLAYER__NO_VAULT, new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hide() {
/*  80 */     return !SXAttribute.isVault();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onInventoryClickSellEvent(InventoryClickEvent event) {
/*  90 */     if (event.getInventory().getName().equals(Message.getMsg(Message.INVENTORY__SELL__NAME, new Object[0]))) {
/*  91 */       if (event.getRawSlot() < 0) {
/*  92 */         event.getView().getPlayer().closeInventory();
/*     */         return;
/*     */       } 
/*  95 */       Inventory inv = event.getInventory();
/*  96 */       ItemStack enterItem = inv.getItem(22);
/*  97 */       ItemMeta enterMeta = enterItem.getItemMeta();
/*  98 */       Player player = (Player)event.getView().getPlayer();
/*  99 */       if (event.getRawSlot() >= 18 && event.getRawSlot() < 27) {
/* 100 */         event.setCancelled(true);
/* 101 */         if (event.getRawSlot() == 22) {
/* 102 */           double sell = 0.0D;
/* 103 */           int size = 0;
/* 104 */           List<String> loreList = new ArrayList<>();
/* 105 */           for (int i = 0; i < 18; i++) {
/* 106 */             ItemStack item = inv.getItem(i);
/* 107 */             if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta().hasLore()) {
/* 108 */               double value = ItemDataManager.getSellValue(item) * item.getAmount();
/* 109 */               sell += value;
/* 110 */               if (!enterMeta.hasEnchants()) {
/* 111 */                 String itemName = item.getItemMeta().getDisplayName();
/* 112 */                 if (itemName == null) {
/* 113 */                   itemName = item.getType().name();
/*     */                 }
/* 115 */                 if (value > 0.0D) {
/* 116 */                   loreList.add(Message.getMsg(Message.INVENTORY__SELL__LORE__FORMAT, new Object[] { Integer.valueOf(i + 1), itemName, Double.valueOf(value) }));
/*     */                 } else {
/* 118 */                   loreList.add(Message.getMsg(Message.INVENTORY__SELL__LORE__NO_SELL, new Object[] { Integer.valueOf(i + 1) }));
/*     */                 }
/*     */               
/* 121 */               } else if (value > 0.0D) {
/* 122 */                 size++;
/* 123 */                 inv.setItem(i, null);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 128 */           if (!enterMeta.hasEnchants()) {
/* 129 */             loreList.add(Message.getMsg(Message.INVENTORY__SELL__LORE__ALL_SELL, new Object[] { Double.valueOf(sell) }));
/* 130 */             enterMeta.setLore(loreList);
/* 131 */             if (sell == 0.0D) {
/* 132 */               enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__SELL__NO_SELL, new Object[0]));
/*     */             } else {
/* 134 */               enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__SELL__ENTER, new Object[0]));
/* 135 */               enterMeta.addEnchant(Enchantment.DURABILITY, 1, true);
/*     */             } 
/*     */           } else {
/* 138 */             enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__SELL__OUT, new Object[] { Double.valueOf(sell) }));
/* 139 */             enterMeta.setLore(new ArrayList());
/* 140 */             enterMeta.removeEnchant(Enchantment.DURABILITY);
/* 141 */             MoneyUtil.give((OfflinePlayer)player, sell);
/* 142 */             Message.send(player, Message.getMsg(Message.PLAYER__SELL, new Object[] { Integer.valueOf(size), Double.valueOf(sell) }));
/*     */           } 
/* 144 */           enterItem.setItemMeta(enterMeta);
/*     */           return;
/*     */         } 
/*     */       } 
/* 148 */       enterMeta.removeEnchant(Enchantment.DURABILITY);
/* 149 */       enterMeta.setDisplayName(Message.getMsg(Message.INVENTORY__SELL__SELL, new Object[0]));
/* 150 */       enterMeta.setLore(Message.getStringList(Message.INVENTORY__SELL__LORE__DEFAULT, new Object[0]));
/* 151 */       enterItem.setItemMeta(enterMeta);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onInventoryCloseSellEvent(InventoryCloseEvent event) {
/* 157 */     if (event.getInventory().getName().equals(Message.getMsg(Message.INVENTORY__SELL__NAME, new Object[0]))) {
/* 158 */       Player player = (Player)event.getView().getPlayer();
/* 159 */       Inventory inv = event.getInventory();
/* 160 */       for (int i = 0; i < 18; i++) {
/* 161 */         ItemStack item = inv.getItem(i);
/* 162 */         if (item != null && !item.getType().equals(Material.AIR)) {
/* 163 */           player.getInventory().addItem(new ItemStack[] { item });
/*     */         }
/*     */       } 
/* 166 */       inv.clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\SellCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */