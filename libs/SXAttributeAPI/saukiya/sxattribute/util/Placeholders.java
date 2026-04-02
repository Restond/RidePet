/*    */ package saukiya.sxattribute.util;
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.util.MoneyUtil;
/*    */ import me.clip.placeholderapi.external.EZPlaceholderHook;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class Placeholders extends EZPlaceholderHook {
/*    */   public Placeholders(SXAttribute plugin) {
/* 14 */     super((Plugin)plugin, "sx");
/* 15 */     this.plugin = plugin;
/* 16 */     hook();
/*    */   }
/*    */   private final SXAttribute plugin;
/*    */   
/*    */   public String onPlaceholderRequest(Player player, String string) {
/* 21 */     SXAttributeData attributeData = this.plugin.getAttributeManager().getEntityData((LivingEntity)player, new SXAttributeData[0]);
/* 22 */     if (string.equalsIgnoreCase("Money") && SXAttribute.isVault())
/* 23 */       return SXAttribute.getDf().format(MoneyUtil.get((OfflinePlayer)player)); 
/* 24 */     if (string.equalsIgnoreCase("Value")) return SXAttribute.getDf().format(attributeData.getValue()); 
/* 25 */     for (SubAttribute attribute : attributeData.getAttributeMap().values()) {
/* 26 */       String str = attribute.getPlaceholder(player, string);
/* 27 */       if (str != null) return str; 
/*    */     } 
/* 29 */     return "N/A";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribut\\util\Placeholders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */