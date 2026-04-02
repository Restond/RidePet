/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.sainttx.holograms.HologramPlugin;
/*    */ import com.sainttx.holograms.api.Hologram;
/*    */ import com.sainttx.holograms.api.HologramManager;
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.holograms.HologramProvider;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HologramsSupport
/*    */   implements HologramProvider, Listener
/*    */ {
/*    */   private final MythicMobs core;
/*    */   private HologramManager hologramManager;
/*    */   
/*    */   public HologramsSupport(MythicMobs mm) {
/* 25 */     this.core = mm;
/* 26 */     this.hologramManager = ((HologramPlugin)JavaPlugin.getPlugin(HologramPlugin.class)).getHologramManager();
/* 27 */     if (this.hologramManager == null)
/*    */       return; 
/*    */   }
/*    */   
/*    */   public void cleanup() {
/* 32 */     this.hologramManager.getActiveHolograms().values().forEach(hologram -> {
/*    */           if (hologram.getId().startsWith("#Temp")) {
/*    */             Schedulers.sync().runLater((), 1L);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IHologram createHologram(String name, AbstractLocation location, String text) {
/* 44 */     return (IHologram)new HologramsHologram(this, name, location, text);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HologramsSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */