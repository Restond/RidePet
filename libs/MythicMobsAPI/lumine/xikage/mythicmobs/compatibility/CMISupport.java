/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.Zrips.CMI.CMI;
/*    */ import com.Zrips.CMI.Modules.Holograms.CMIHologram;
/*    */ import com.Zrips.CMI.Modules.Holograms.HologramManager;
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.holograms.HologramProvider;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CMISupport
/*    */   implements HologramProvider, Listener
/*    */ {
/*    */   private final MythicMobs core;
/*    */   private final CMI cmi;
/*    */   private HologramManager hologramManager;
/*    */   
/*    */   public CMISupport(MythicMobs mm) {
/* 31 */     this.core = mm;
/* 32 */     this.cmi = CMI.getInstance();
/* 33 */     this.hologramManager = this.cmi.getHologramManager();
/*    */   }
/*    */ 
/*    */   
/*    */   public void cleanup() {
/* 38 */     this.hologramManager.getHolograms().values().forEach(hologram -> {
/*    */           if (hologram.getName().startsWith("#Temp")) {
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
/* 50 */     return (IHologram)new CMIHologramImpl(this, name, location, text);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\CMISupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */