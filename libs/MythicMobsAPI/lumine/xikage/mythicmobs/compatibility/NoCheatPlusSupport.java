/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
/*    */ import io.lumine.utils.events.Events;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoCheatPlusSupport
/*    */ {
/*    */   public NoCheatPlusSupport() {
/* 18 */     Events.subscribe(PlayerChangedWorldEvent.class).handler(event -> NCPExemptionManager.unexempt(event.getPlayer()));
/* 19 */     Events.subscribe(PlayerQuitEvent.class).handler(event -> NCPExemptionManager.unexempt(event.getPlayer()));
/*    */   }
/*    */   
/*    */   public void exemptPlayer(UUID uuid) {
/* 23 */     NCPExemptionManager.exemptPermanently(uuid);
/*    */   }
/*    */   
/*    */   public void unexemptPlayer(UUID uuid) {
/* 27 */     NCPExemptionManager.unexempt(uuid);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\NoCheatPlusSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */