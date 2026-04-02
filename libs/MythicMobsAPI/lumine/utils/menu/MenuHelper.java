/*     */ package lumine.utils.menu;
/*     */ import io.lumine.utils.menu.Icon;
/*     */ import io.lumine.utils.menu.Menu;
/*     */ import io.lumine.utils.menu.MonitoredState;
/*     */ import io.lumine.utils.plugin.LoaderUtils;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.BiConsumer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class MenuHelper implements Listener {
/*  27 */   private static io.lumine.utils.menu.MenuHelper _instance = new io.lumine.utils.menu.MenuHelper();
/*  28 */   private final Map<UUID, OpenMenu<?>> openMenus = new HashMap<>();
/*     */   
/*     */   protected static <T> void registerOpen(Player player, Menu<T> menu, T state) {
/*  31 */     _instance.openMenus.put(player.getUniqueId(), new OpenMenu(menu, state));
/*     */   }
/*     */   
/*     */   private MenuHelper() {
/*  35 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)LoaderUtils.getPlugin());
/*  36 */     Bukkit.getScheduler().runTaskTimer((Plugin)LoaderUtils.getPlugin(), this::updateMenus, 10L, 10L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onClose(InventoryCloseEvent event) {
/*  45 */     this.openMenus.remove(event.getPlayer().getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onClick(InventoryClickEvent event) {
/*  54 */     OpenMenu<?> openMenu = this.openMenus.get(event.getWhoClicked().getUniqueId());
/*     */     
/*  56 */     if (openMenu == null) {
/*     */       return;
/*     */     }
/*     */     
/*  60 */     event.setCancelled(true);
/*     */ 
/*     */     
/*  63 */     if (event.getClick() == ClickType.RIGHT) {
/*  64 */       handleRightClick(openMenu, (Player)event.getWhoClicked(), event.getRawSlot());
/*     */     } else {
/*  66 */       handleClick(openMenu, (Player)event.getWhoClicked(), event.getRawSlot());
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T> void handleClick(OpenMenu<T> openMenu, Player player, int slot) {
/*  71 */     OpenMenu.access$000(openMenu).getIcon(slot).ifPresent(icon -> icon.getClickFunc().ifPresent(()));
/*     */   }
/*     */   
/*     */   private <T> void handleRightClick(OpenMenu<T> openMenu, Player player, int slot) {
/*  75 */     OpenMenu.access$000(openMenu).getIcon(slot).ifPresent(icon -> icon.getRightClickFunc().ifPresent(()));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onDrag(InventoryDragEvent event) {
/*  80 */     if (this.openMenus.containsKey(event.getWhoClicked().getUniqueId())) {
/*  81 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateMenus() {
/*  87 */     this.openMenus.forEach((uuid, menu) -> updateMenu(uuid, menu));
/*     */   }
/*     */   
/*     */   private <T> void updateMenu(UUID uuid, OpenMenu<T> openMenu) {
/*     */     Inventory inv;
/*     */     try {
/*  93 */       Player player = Bukkit.getPlayer(uuid);
/*     */       
/*  95 */       if (player == null) {
/*  96 */         Scheduler.runLaterSync(() -> (OpenMenu)this.openMenus.remove(paramUUID), 1L);
/*     */         return;
/*     */       } 
/*  99 */       inv = Bukkit.getPlayer(uuid).getOpenInventory().getTopInventory();
/* 100 */     } catch (Exception ex) {
/* 101 */       Player player = Bukkit.getPlayer(uuid);
/*     */       
/* 103 */       if (player == null) {
/* 104 */         player.closeInventory();
/* 105 */         Scheduler.runLaterSync(() -> (OpenMenu)this.openMenus.remove(paramUUID), 1L);
/*     */       } 
/*     */       return;
/*     */     } 
/* 109 */     Menu<T> menu = OpenMenu.access$000(openMenu);
/*     */ 
/*     */     
/* 112 */     Optional<MonitoredState> maybeMonitor = menu.getMonitoredState(OpenMenu.access$100(openMenu));
/* 113 */     if (maybeMonitor.isPresent()) {
/* 114 */       if (!maybeMonitor.filter(monitor -> (monitor.getMonitorTimestamp() != OpenMenu.access$200(paramOpenMenu))).isPresent()) {
/*     */ 
/*     */ 
/*     */         
/* 118 */         if (menu.getBlinkingSlots().isPresent()) {
/* 119 */           ((List)menu.getBlinkingSlots().get()).stream().forEach(slot -> {
/*     */                 Icon icon = paramMenu.getIcon(slot.intValue()).get();
/*     */                 ItemStack stack = icon.build(OpenMenu.access$100(paramOpenMenu));
/*     */                 ItemStack comp = paramInventory.getItem(slot.intValue());
/*     */                 if ((comp == null && stack.getType() != Material.AIR) || (comp != null && !comp.equals(stack))) {
/*     */                   paramInventory.setItem(slot.intValue(), stack);
/*     */                 }
/*     */               });
/*     */         }
/*     */         return;
/*     */       } 
/* 130 */       OpenMenu.access$202(openMenu, ((MonitoredState)maybeMonitor.get()).getMonitorTimestamp());
/*     */     } 
/*     */     
/* 133 */     menu.sort(OpenMenu.access$100(openMenu));
/*     */     
/* 135 */     menu.getIcons().forEach((slot, icon) -> {
/*     */           ItemStack stack = icon.build(OpenMenu.access$100(paramOpenMenu));
/*     */           ItemStack comp = paramInventory.getItem(slot.intValue());
/*     */           if ((comp == null && stack.getType() != Material.AIR) || (comp != null && !comp.equals(stack)))
/*     */             paramInventory.setItem(slot.intValue(), stack); 
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\menu\MenuHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */