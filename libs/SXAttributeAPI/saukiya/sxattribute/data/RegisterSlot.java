/*    */ package saukiya.sxattribute.data;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.ItemUtil;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class RegisterSlot
/*    */ {
/*    */   private String name;
/*    */   private ItemStack item;
/*    */   private int slot;
/*    */   
/*    */   public String getName() {
/* 17 */     return this.name;
/*    */   } public ItemStack getItem() {
/* 19 */     return this.item;
/*    */   } public int getSlot() {
/* 21 */     return this.slot;
/*    */   }
/*    */   
/*    */   public RegisterSlot(int slot, String name, String id, ItemUtil itemUtil) {
/* 25 */     this.slot = slot;
/* 26 */     this.name = name.replace("&", "§");
/* 27 */     int itemMaterial = 160, itemDurability = (id == null) ? 15 : 0;
/* 28 */     if (id != null) {
/* 29 */       if (id.contains(":")) {
/* 30 */         String[] idSplit = id.split(":");
/* 31 */         itemMaterial = Integer.valueOf(idSplit[0]).intValue();
/* 32 */         itemDurability = Integer.valueOf(idSplit[1]).intValue();
/*    */       } else {
/* 34 */         itemMaterial = Integer.valueOf(id).intValue();
/*    */       } 
/*    */     }
/* 37 */     this.item = itemUtil.setNBT(new ItemStack(itemMaterial, 1, (short)itemDurability), "Slot", this.name);
/* 38 */     ItemMeta meta = this.item.getItemMeta();
/* 39 */     meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
/* 40 */     if (SXAttribute.getVersionSplit()[1] >= 11) {
/*    */       
/* 42 */       meta.setUnbreakable(true);
/*    */     } else {
/*    */       
/* 45 */       meta.spigot().setUnbreakable(true);
/*    */     } 
/* 47 */     String itemName = Config.getConfig().getString("RegisterSlots.Lock.Name");
/* 48 */     if (itemName != null) {
/* 49 */       meta.setDisplayName(itemName.replace("&", "§").replace("%SlotName%", this.name));
/*    */     }
/* 51 */     this.item.setItemMeta(meta);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\RegisterSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */