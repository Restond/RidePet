/*     */ package saukiya.sxattribute.data.condition.sub;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.ItemDataManager;
/*     */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
/*     */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerItemDamageEvent;
/*     */ import org.bukkit.inventory.EntityEquipment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DurabilityCondition
/*     */   extends SubCondition
/*     */   implements Listener
/*     */ {
/*     */   public DurabilityCondition() {
/*  33 */     super("Durability");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  38 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)getPlugin());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearItem(Player player, ItemStack item) {
/*  48 */     EntityEquipment eq = player.getEquipment();
/*  49 */     if (item.equals(eq.getBoots())) {
/*  50 */       eq.setBoots(new ItemStack(Material.AIR));
/*  51 */     } else if (item.equals(eq.getChestplate())) {
/*  52 */       eq.setChestplate(new ItemStack(Material.AIR));
/*  53 */     } else if (item.equals(eq.getHelmet())) {
/*  54 */       eq.setHelmet(new ItemStack(Material.AIR));
/*  55 */     } else if (item.equals(eq.getLeggings())) {
/*  56 */       eq.setLeggings(new ItemStack(Material.AIR));
/*  57 */     } else if (item.equals(eq.getItemInMainHand())) {
/*  58 */       eq.setItemInMainHand(new ItemStack(Material.AIR));
/*  59 */     } else if (item.equals(eq.getItemInOffHand())) {
/*  60 */       eq.setItemInOffHand(new ItemStack(Material.AIR));
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
/*     */   private boolean takeDurability(Player player, ItemStack item, int takeDurability) {
/*  73 */     if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
/*  74 */       ItemMeta meta = item.getItemMeta();
/*  75 */       List<String> loreList = meta.getLore();
/*  76 */       takeDurability = (item.getType().toString().contains("_") && "SPADE|PICKAXE|AXE|HDE".contains(item.getType().toString().split("_")[1])) ? 1 : takeDurability;
/*  77 */       for (int i = 0; i < loreList.size(); i++) {
/*  78 */         String lore = loreList.get(i);
/*  79 */         if (lore.contains(Config.getConfig().getString("Condition.Durability.Name"))) {
/*     */           
/*  81 */           int durability = getDurability(lore) - takeDurability;
/*  82 */           if (durability < 0) durability = 0; 
/*  83 */           int maxDurability = getMaxDurability(lore);
/*  84 */           if (durability > maxDurability) {
/*  85 */             durability = maxDurability;
/*     */           }
/*     */           
/*  88 */           lore = ItemDataManager.replaceColor(ItemDataManager.clearColor(lore).replaceFirst(String.valueOf(getDurability(lore)), String.valueOf(durability)));
/*  89 */           loreList.set(i, lore);
/*  90 */           meta.setLore(loreList);
/*  91 */           item.setItemMeta(meta);
/*  92 */           if (meta instanceof Repairable) {
/*     */             
/*  94 */             Repairable repairable = (Repairable)meta;
/*  95 */             if (repairable.getRepairCost() != 999) {
/*  96 */               repairable.setRepairCost(999);
/*  97 */               item.setItemMeta((ItemMeta)repairable);
/*     */             } 
/*     */           } 
/*     */           
/* 101 */           if (durability <= 0) {
/* 102 */             if (Config.isClearItemDurability()) {
/*     */               
/* 104 */               if (SXAttribute.getVersionSplit()[1] > 10) {
/* 105 */                 item.setAmount(0);
/* 106 */                 player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 1.0F);
/*     */               } else {
/* 108 */                 clearItem(player, item);
/* 109 */                 player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 1.0F);
/*     */               } 
/* 111 */               updateAllDataAndStats(player);
/* 112 */               return true;
/*     */             } 
/* 114 */             updateAllDataAndStats(player);
/*     */           } 
/*     */           
/* 117 */           if (item.getType().getMaxDurability() != 0 && !getUnbreakable(meta)) {
/* 118 */             int maxDefaultDurability = item.getType().getMaxDurability();
/* 119 */             int defaultDurability = (int)(durability / maxDurability * maxDefaultDurability);
/* 120 */             item.setDurability((short)(maxDefaultDurability - defaultDurability));
/*     */           } 
/* 122 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateAllDataAndStats(Player player) {
/* 135 */     (new Object(this, player))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       .runTaskAsynchronously((Plugin)getPlugin());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onItemDurabilityEvent(PlayerItemDamageEvent event) {
/* 148 */     Player player = event.getPlayer();
/* 149 */     ItemStack item = event.getItem();
/*     */     
/* 151 */     if (takeDurability(player, item, event.getDamage())) {
/* 152 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public SXConditionReturnType determine(LivingEntity entity, ItemStack item, String lore) {
/* 158 */     if (lore.contains(Config.getConfig().getString("Condition.Durability.Name")) && 
/* 159 */       getDurability(lore) <= 0) {
/* 160 */       if (entity instanceof Player && item != null) {
/* 161 */         Message.send((Player)entity, Message.getMsg(Message.PLAYER__NO_DURABILITY, new Object[] { getItemName(item) }));
/*     */       }
/* 163 */       return SXConditionReturnType.ITEM;
/*     */     } 
/*     */     
/* 166 */     return SXConditionReturnType.NULL;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\sub\DurabilityCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */