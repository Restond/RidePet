/*    */ package saukiya.sxattribute.data.condition.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
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
/*    */ 
/*    */ public class RoleCondition
/*    */   extends SubCondition
/*    */ {
/*    */   public RoleCondition() {
/* 20 */     super("Role");
/*    */   }
/*    */ 
/*    */   
/*    */   public SXConditionReturnType determine(LivingEntity entity, ItemStack item, String lore) {
/* 25 */     if (lore.contains(Config.getConfig().getString("Condition.Role.Name")) && entity instanceof org.bukkit.entity.Player && !entity.hasPermission(SXAttribute.getPluginName() + "." + getText(lore))) {
/* 26 */       if (item != null) Message.send(entity, Message.PLAYER__NO_ROLE, new Object[] { getItemName(item) }); 
/* 27 */       return SXConditionReturnType.ITEM;
/*    */     } 
/* 29 */     return SXConditionReturnType.NULL;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String getText(String lore) {
/* 39 */     String str = lore.replaceAll("[^Α-￥]", "");
/* 40 */     if (lore.contains(":") || lore.contains("：")) {
/* 41 */       str = lore.replace("：", ":");
/* 42 */       str = str.replace(str.split(":")[0] + ":", "").replaceAll("[^Α-￥]", "");
/*    */     } 
/* 44 */     return str;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\sub\RoleCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */