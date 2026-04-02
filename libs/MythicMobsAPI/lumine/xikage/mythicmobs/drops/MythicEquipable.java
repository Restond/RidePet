/*    */ package lumine.xikage.mythicmobs.drops;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MythicEquipable
/*    */ {
/*    */   private ItemStack itemstack;
/*  8 */   private short slot = 5;
/*  9 */   private float chance = 1.0F;
/*    */   
/*    */   public MythicEquipable(ItemStack is, short slot) {
/* 12 */     this.itemstack = is;
/* 13 */     this.slot = slot;
/* 14 */     this.chance = 1.0F;
/*    */   }
/*    */   public MythicEquipable(ItemStack is, short slot, float chance) {
/* 17 */     this.itemstack = is;
/* 18 */     this.slot = slot;
/* 19 */     this.chance = chance;
/*    */   }
/*    */   public ItemStack getItemStack() {
/* 22 */     return this.itemstack;
/*    */   }
/*    */   public short getSlot() {
/* 25 */     return this.slot;
/*    */   }
/*    */   public float getChance() {
/* 28 */     return this.chance;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\MythicEquipable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */