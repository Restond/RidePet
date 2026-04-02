/*    */ package lumine.xikage.mythicmobs.items;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class AttributeHandler
/*    */ {
/*    */   public static ItemStack addSpeed(String itemName, ItemStack s, double i) {
/* 10 */     ItemStack item = MythicMobs.inst().getVolatileCodeHandler().setItemAttribute(itemName, "MOVEMENT_SPEED", i, s);
/* 11 */     return item;
/*    */   }
/*    */   
/*    */   public static ItemStack addHealth(String itemName, ItemStack s, double i) {
/* 15 */     ItemStack item = MythicMobs.inst().getVolatileCodeHandler().setItemAttribute(itemName, "MAX_HEALTH", i, s);
/* 16 */     return item;
/*    */   }
/*    */   
/*    */   public static ItemStack addKnockBackRes(String itemName, ItemStack s, double i) {
/* 20 */     ItemStack item = MythicMobs.inst().getVolatileCodeHandler().setItemAttribute(itemName, "KNOCKBACK_RESISTANCE", i, s);
/* 21 */     return item;
/*    */   }
/*    */   
/*    */   public static ItemStack addDamage(String itemName, ItemStack s, double i) {
/* 25 */     ItemStack item = MythicMobs.inst().getVolatileCodeHandler().setItemAttribute(itemName, "DAMAGE", i, s);
/* 26 */     return item;
/*    */   }
/*    */   
/*    */   public static ItemStack addFollowRange(String itemName, ItemStack s, double i) {
/* 30 */     ItemStack item = MythicMobs.inst().getVolatileCodeHandler().setItemAttribute(itemName, "FOLLOW_RANGE", i, s);
/* 31 */     return item;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\items\AttributeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */