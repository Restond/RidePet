/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*    */ import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.holograms.HologramProvider;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HolographicDisplaysSupport
/*    */   implements HologramProvider, Listener
/*    */ {
/*    */   private final MythicMobs core;
/*    */   
/*    */   public HolographicDisplaysSupport(MythicMobs mm) {
/* 20 */     this.core = mm;
/*    */   }
/*    */ 
/*    */   
/*    */   public void cleanup() {
/* 25 */     HologramsAPI.getHolograms((Plugin)this.core).forEach(hologram -> hologram.delete());
/*    */   }
/*    */ 
/*    */   
/*    */   public IHologram createHologram(String name, AbstractLocation location, String text) {
/* 30 */     return (IHologram)new HolographicDisplays(this, name, location, text);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HolographicDisplaysSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */