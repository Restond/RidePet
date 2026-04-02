/*    */ package saukiya.sxattribute.data.condition.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*    */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttackSpeedCondition
/*    */   extends SubCondition
/*    */ {
/*    */   public AttackSpeedCondition() {
/* 19 */     super("AttackSpeed", new SXConditionType[] { SXConditionType.MAIN_HAND });
/*    */   }
/*    */ 
/*    */   
/*    */   public SXConditionReturnType determine(LivingEntity entity, ItemStack item, String lore) {
/* 24 */     if (lore.contains(Config.getConfig().getString("Condition.AttackSpeed.Name")) && 
/* 25 */       item != null) SXAttribute.getApi().getItemUtil().setAttackSpeed(item, new double[] { Double.valueOf(getNumber(lore)).doubleValue() });
/*    */     
/* 27 */     return SXConditionReturnType.NULL;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\sub\AttackSpeedCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */