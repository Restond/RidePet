/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.SenderType;
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import github.saukiya.sxattribute.data.RegisterSlot;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class DisplaySlotCommand
/*    */   extends SubCommand
/*    */   implements Listener
/*    */ {
/*    */   public DisplaySlotCommand() {
/* 28 */     super("displaySlot", new SenderType[] { SenderType.PLAYER });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 33 */     super.onEnable();
/* 34 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)getPlugin());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/* 39 */     if (plugin.getRegisterSlotManager().getRegisterSlotMap().size() > 0) {
/* 40 */       openDisplaySlotInventory((Player)sender);
/*    */     } else {
/* 42 */       sender.sendMessage(Message.getMsg(Message.PLAYER__NO_REGISTER_SLOTS, new Object[0]));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void openDisplaySlotInventory(Player player) {
/* 47 */     Inventory inv = Bukkit.createInventory(null, 45, Message.getMsg(Message.INVENTORY__DISPLAY_SLOTS_NAME, new Object[0]));
/* 48 */     ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
/* 49 */     ItemMeta glassMeta = glass.getItemMeta();
/* 50 */     glassMeta.setDisplayName("§r");
/* 51 */     glass.setItemMeta(glassMeta);
/* 52 */     for (int i = 9; i < 18; i++) {
/* 53 */       inv.setItem(i, glass);
/*    */     }
/* 55 */     SXAttribute.getApi().getRegisterSlotMapEntrySet().forEach(entry -> inv.setItem((((Integer)entry.getKey()).intValue() > 8) ? (((Integer)entry.getKey()).intValue() + 9) : ((Integer)entry.getKey()).intValue(), ((RegisterSlot)entry.getValue()).getItem()));
/* 56 */     player.openInventory(inv);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   void onInventoryClickStatsEvent(InventoryClickEvent event) {
/* 61 */     if (event.getInventory().getName().equalsIgnoreCase(Message.getMsg(Message.INVENTORY__DISPLAY_SLOTS_NAME, new Object[0]))) {
/* 62 */       event.setCancelled(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 68 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\DisplaySlotCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */