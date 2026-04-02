/*    */ package saukiya.sxattribute.data.condition.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.RegisterSlot;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*    */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import java.util.Map;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HandCondition
/*    */   extends SubCondition
/*    */ {
/*    */   public HandCondition() {
/* 19 */     super("Hand", new SXConditionType[] { SXConditionType.HAND });
/*    */   }
/*    */ 
/*    */   
/*    */   public SXConditionReturnType determine(LivingEntity entity, ItemStack item, String lore) {
/* 24 */     return (Config.getConfig().getStringList("Condition.Armor").stream().anyMatch(lore::contains) || SXAttribute.getApi().getRegisterSlotMapEntrySet().stream().anyMatch(entry -> lore.contains(((RegisterSlot)entry.getValue()).getName()))) ? SXConditionReturnType.ITEM : SXConditionReturnType.NULL;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\sub\HandCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */