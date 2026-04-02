/*    */ package saukiya.sxattribute.data.condition.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*    */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MainHandCondition
/*    */   extends SubCondition
/*    */ {
/*    */   public MainHandCondition() {
/* 19 */     super("MainHand", new SXConditionType[] { SXConditionType.MAIN_HAND });
/*    */   }
/*    */ 
/*    */   
/*    */   public SXConditionReturnType determine(LivingEntity entity, ItemStack item, String lore) {
/* 24 */     if (lore.contains(Config.getConfig().getString("Condition.Hand.InOff.Name"))) {
/* 25 */       if (item != null)
/* 26 */         Message.send(entity, Message.PLAYER__NO_USE_SLOT, new Object[] { getItemName(item), Config.getConfig().getString("Condition.Hand.InOff.Name") }); 
/* 27 */       return SXConditionReturnType.ITEM;
/*    */     } 
/* 29 */     return SXConditionReturnType.NULL;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\sub\MainHandCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */