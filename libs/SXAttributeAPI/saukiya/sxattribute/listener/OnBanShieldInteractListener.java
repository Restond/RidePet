/*    */ package saukiya.sxattribute.listener;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OnBanShieldInteractListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   void onPlayerClickEvent(PlayerInteractEvent event) {
/* 19 */     if (SXAttribute.getVersionSplit()[1] >= 9 && 
/* 20 */       Config.isBanShieldDefense() && event.getItem() != null && event.getItem().getType().equals(Material.SHIELD))
/* 21 */       event.setCancelled(true); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnBanShieldInteractListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */