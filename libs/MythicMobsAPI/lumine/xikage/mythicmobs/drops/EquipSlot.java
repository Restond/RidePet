/*    */ package lumine.xikage.mythicmobs.drops;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public enum EquipSlot
/*    */ {
/*  9 */   HEAD(new String[] { "4", "HELMET", "HELM" }),
/* 10 */   CHEST(new String[] { "3", "CHESTPIECE", "CHESTPLATE", "BODY" }),
/* 11 */   LEGS(new String[] { "2", "LEGGINGS", "PANTS" }),
/* 12 */   FEET(new String[] { "1", "BOOTS", "SHOES" }),
/* 13 */   HAND(new String[] { "0", "MAINHAND", "WEAPON" }),
/* 14 */   OFFHAND(new String[] { "5", "SHIELD" }),
/* 15 */   NONE(new String[0]);
/*    */ 
/*    */   
/* 18 */   private HashSet<String> slotAliases = new HashSet<>();
/*    */   
/*    */   EquipSlot(String... aliases) {
/* 21 */     this.slotAliases.add(toString());
/*    */     
/* 23 */     for (String alias : aliases) {
/* 24 */       this.slotAliases.add(alias);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean matches(String slot) {
/* 29 */     for (String alias : this.slotAliases) {
/* 30 */       if (alias.equalsIgnoreCase(slot)) {
/* 31 */         return true;
/*    */       }
/*    */     } 
/* 34 */     return false;
/*    */   }
/*    */   
/*    */   public void equip(AbstractEntity entity, AbstractItemStack item) {
/* 38 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$drops$EquipSlot[ordinal()]) {
/*    */       case 1:
/* 40 */         entity.equipItemHead(item);
/*    */         break;
/*    */       
/*    */       case 2:
/* 44 */         entity.equipItemChest(item);
/*    */         break;
/*    */       
/*    */       case 3:
/* 48 */         entity.equipItemLegs(item);
/*    */         break;
/*    */       
/*    */       case 4:
/* 52 */         entity.equipItemFeet(item);
/*    */         break;
/*    */       
/*    */       case 5:
/* 56 */         entity.equipItemMainHand(item);
/*    */         break;
/*    */       
/*    */       case 6:
/* 60 */         entity.equipItemOffHand(item);
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static io.lumine.xikage.mythicmobs.drops.EquipSlot of(String key) {
/* 70 */     for (io.lumine.xikage.mythicmobs.drops.EquipSlot slot : values()) {
/* 71 */       if (slot.matches(key)) {
/* 72 */         return slot;
/*    */       }
/*    */     } 
/* 75 */     return NONE;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\EquipSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */