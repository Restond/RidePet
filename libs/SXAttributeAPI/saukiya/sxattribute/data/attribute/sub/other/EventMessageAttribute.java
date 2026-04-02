/*    */ package saukiya.sxattribute.data.attribute.sub.other;
/*    */ 
/*    */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*    */ import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*    */ import github.saukiya.sxattribute.event.SXDamageEvent;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EventMessageAttribute
/*    */   extends SubAttribute
/*    */   implements Listener
/*    */ {
/*    */   public EventMessageAttribute() {
/* 29 */     super("EventMessage", 0, new SXAttributeType[] { SXAttributeType.OTHER });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 34 */     super.onEnable();
/* 35 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)getPlugin());
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   void onSXDamageEvent(SXDamageEvent e) {
/* 40 */     DamageEventData damageEventData = e.getData();
/* 41 */     EntityDamageByEntityEvent event = damageEventData.getEvent();
/*    */     
/* 43 */     if (!event.isCancelled()) {
/* 44 */       damageEventData.sendHolo(Message.getMsg((damageEventData.isCrit() && event.getDamage() > 0.0D) ? Message.PLAYER__HOLOGRAPHIC__CRIT : Message.PLAYER__HOLOGRAPHIC__DAMAGE, new Object[] { SXAttribute.getDf().format(event.getFinalDamage()) }));
/*    */     }
/*    */     
/* 47 */     if (Config.isHolographic() && SXAttribute.isHolographic() && !Config.getHolographicBlackList().contains(event.getCause().name())) {
/* 48 */       Location loc = damageEventData.getEntity().getEyeLocation().clone().add(0.0D, 0.6D - SXAttribute.getRandom().nextDouble() / 2.0D, 0.0D);
/* 49 */       loc.setYaw(damageEventData.getDamager().getLocation().getYaw() + 90.0F);
/* 50 */       loc.add(loc.getDirection().multiply(0.8D));
/* 51 */       Hologram hologram = HologramsAPI.createHologram((Plugin)getPlugin(), loc);
/* 52 */       for (String message : damageEventData.getHoloList()) {
/* 53 */         hologram.appendTextLine(message);
/*    */       }
/* 55 */       SXAttribute.getApi().getHologramsList().add(hologram);
/* 56 */       (new Object(this, hologram))
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 62 */         .runTaskLater((Plugin)getPlugin(), Config.getConfig().getInt("Holographic.DisplayTime"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 73 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 78 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 83 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 88 */     return 0.0D;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\other\EventMessageAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */