/*     */ package saukiya.sxattribute.listener;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemHeldEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerSwapHandItemsEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class OnUpdateStatsListener implements Listener {
/*     */   public OnUpdateStatsListener(SXAttribute plugin) {
/*  21 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final SXAttribute plugin;
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateHandData(Player player, ItemStack... itemList) {
/*  31 */     if (itemList.length > 0) {
/*  32 */       int i = 0;
/*  33 */       for (ItemStack item : itemList) {
/*  34 */         if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) {
/*  35 */           i++;
/*     */         }
/*     */       } 
/*  38 */       if (i == itemList.length)
/*     */         return; 
/*  40 */     }  (new Object(this, player))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  46 */       .runTaskAsynchronously((Plugin)this.plugin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateEquipmentData(Player player) {
/*  55 */     (new Object(this, player))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  64 */       .runTaskAsynchronously((Plugin)this.plugin);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
/*  69 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/*  72 */     Player player = event.getPlayer();
/*  73 */     PlayerInventory playerInventory = player.getInventory();
/*  74 */     ItemStack oldItem = playerInventory.getItem(event.getPreviousSlot());
/*  75 */     ItemStack newItem = playerInventory.getItem(event.getNewSlot());
/*  76 */     updateHandData(player, new ItemStack[] { oldItem, newItem });
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
/*  81 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/*  84 */     Player player = event.getPlayer();
/*  85 */     ItemStack oldItem = event.getMainHandItem();
/*  86 */     ItemStack newItem = event.getOffHandItem();
/*  87 */     updateHandData(player, new ItemStack[] { oldItem, newItem });
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onInventoryCloseEvent(InventoryCloseEvent event) {
/*  92 */     Player player = (Player)event.getPlayer();
/*  93 */     Inventory inv = event.getInventory();
/*  94 */     if (SXAttribute.isRpgInventory()) {
/*  95 */       updateEquipmentData(player);
/*     */     }
/*  97 */     else if (inv.getName().contains("container") || inv.getName().contains("Repair")) {
/*  98 */       updateEquipmentData(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   void onPlayerDropEvent(PlayerDropItemEvent event) {
/* 105 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/* 108 */     Player player = event.getPlayer();
/* 109 */     ItemStack item = event.getItemDrop().getItemStack();
/* 110 */     updateHandData(player, new ItemStack[] { item });
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
/* 115 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/* 118 */     Player player = event.getPlayer();
/* 119 */     ItemStack item = event.getItem().getItemStack();
/* 120 */     updateHandData(player, new ItemStack[] { item });
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onPlayerInteractEvent(PlayerInteractEvent event) {
/* 125 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/* 128 */     Player player = event.getPlayer();
/* 129 */     if ((event.getAction() + "").contains("RIGHT") && 
/* 130 */       event.getItem() != null) {
/* 131 */       String name = event.getItem().getType().toString();
/* 132 */       if (name.contains("HELMET") || name.contains("CHESTPLATE") || name.contains("LEGGINGS") || name.contains("BOOTS")) {
/* 133 */         updateEquipmentData(player);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   void onPlayerJoinEvent(PlayerJoinEvent event) {
/* 141 */     Player player = event.getPlayer();
/* 142 */     updateEquipmentData(player);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onPlayerQuitEvent(PlayerQuitEvent event) {
/* 147 */     Player player = event.getPlayer();
/* 148 */     this.plugin.getAttributeManager().clearEntityData(player.getUniqueId());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onEntitySpawnEvent(CreatureSpawnEvent event) {
/* 153 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/* 156 */     LivingEntity entity = event.getEntity();
/* 157 */     entity.setInvulnerable(true);
/* 158 */     (new Object(this, entity))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 168 */       .runTaskLaterAsynchronously((Plugin)this.plugin, 16L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   void onEntityDeathEvent(EntityDeathEvent event) {
/* 173 */     if (!(event.getEntity() instanceof Player))
/* 174 */       this.plugin.getAttributeManager().clearEntityData(event.getEntity().getUniqueId()); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnUpdateStatsListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */