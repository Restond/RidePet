/*    */ package saukiya.sxattribute.data.condition.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionReturnType;
/*    */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpiryTimeCondition
/*    */   extends SubCondition
/*    */ {
/*    */   public ExpiryTimeCondition() {
/* 22 */     super("ExpiryTime");
/*    */   }
/*    */ 
/*    */   
/*    */   public SXConditionReturnType determine(LivingEntity entity, ItemStack item, String lore) {
/* 27 */     if (lore.contains(Config.getConfig().getString("Condition.ExpiryTime.Name"))) {
/* 28 */       String timeStr = getTime(lore);
/*    */       try {
/* 30 */         if (System.currentTimeMillis() > SXAttribute.getSdf().parse(timeStr).getTime()) {
/* 31 */           if (item != null) Message.send(entity, Message.PLAYER__OVERDUE_ITEM, new Object[] { getItemName(item), timeStr }); 
/* 32 */           return SXConditionReturnType.ITEM;
/*    */         } 
/* 34 */       } catch (Exception e) {
/* 35 */         Bukkit.getConsoleSender().sendMessage("[" + SXAttribute.getPluginName() + "] §cException: §4" + e.getClass().getSimpleName());
/* 36 */         Bukkit.getConsoleSender().sendMessage("[" + SXAttribute.getPluginName() + "] §cItem §4" + getItemName(item) + "§c Time Format Error: §r'§4" + lore + "§r' §7-> §r'§c" + timeStr + "§r'");
/* 37 */         if (entity != null) {
/* 38 */           Location loc = entity.getLocation();
/* 39 */           Bukkit.getConsoleSender().sendMessage("[" + SXAttribute.getPluginName() + "] §cEntity: §4" + ((entity.getCustomName() != null) ? entity.getCustomName() : entity.getName()) + "§c To Location: §4[" + loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "]");
/*    */         } 
/* 41 */         return SXConditionReturnType.ITEM;
/*    */       } 
/*    */     } 
/* 44 */     return SXConditionReturnType.NULL;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String getTime(String lore) {
/* 54 */     String str = lore.replace(Config.getConfig().getString("Condition.ExpiryTime.Name"), "").replaceAll("§+[a-z0-9]", "");
/* 55 */     if (str.contains(": ") || str.contains("： ")) {
/* 56 */       str = str.replace("： ", ": ");
/* 57 */       str = str.replace(str.split(":")[0] + ": ", "");
/* 58 */       return str;
/*    */     } 
/* 60 */     return str;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\sub\ExpiryTimeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */