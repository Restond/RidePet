/*    */ package saukiya.sxattribute.data.condition.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
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
/*    */ public class LimitLevelCondition
/*    */   extends SubCondition
/*    */ {
/*    */   public LimitLevelCondition() {
/* 18 */     super("LimitLevel");
/*    */   }
/*    */ 
/*    */   
/*    */   public SXConditionReturnType determine(LivingEntity entity, ItemStack item, String lore) {
/* 23 */     if (lore.contains(Config.getConfig().getString("Condition.LimitLevel.Name")) && Integer.valueOf(getNumber(lore)).intValue() > getLevel(entity)) {
/* 24 */       if (item != null) Message.send(entity, Message.PLAYER__NO_LEVEL_USE, new Object[] { getItemName(item) }); 
/* 25 */       return SXConditionReturnType.ITEM;
/*    */     } 
/* 27 */     return SXConditionReturnType.NULL;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\sub\LimitLevelCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */