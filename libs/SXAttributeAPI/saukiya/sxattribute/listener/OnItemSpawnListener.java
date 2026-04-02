/*    */ package saukiya.sxattribute.listener;
/*    */ 
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.ItemMergeEvent;
/*    */ import org.bukkit.event.entity.ItemSpawnEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class OnItemSpawnListener
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   void onItemSpawnEvent(ItemSpawnEvent event) {
/* 15 */     if (!Config.isItemDisplayName())
/* 16 */       return;  Item item = event.getEntity();
/* 17 */     ItemStack itemStack = item.getItemStack();
/* 18 */     if (!event.isCancelled() && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
/* 19 */       item.setCustomNameVisible(true);
/* 20 */       if (itemStack.getAmount() > 1) {
/* 21 */         item.setCustomName(itemStack.getItemMeta().getDisplayName() + " §b*" + itemStack.getAmount());
/*    */       } else {
/* 23 */         item.setCustomName(itemStack.getItemMeta().getDisplayName());
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   void onItemMergeEvent(ItemMergeEvent event) {
/* 30 */     if (!Config.isItemDisplayName())
/* 31 */       return;  Item item = event.getTarget();
/* 32 */     Item oldItem = event.getEntity();
/* 33 */     if (item.isCustomNameVisible())
/* 34 */       item.setCustomName(item.getItemStack().getItemMeta().getDisplayName() + " §b*" + (item.getItemStack().getAmount() + oldItem.getItemStack().getAmount())); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnItemSpawnListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */