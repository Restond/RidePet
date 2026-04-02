/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.Zrips.CMI.Modules.Holograms.CMIHologram;
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CMISupport;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CMIHologramImpl
/*    */   implements IHologram
/*    */ {
/*    */   private CMIHologram hologram;
/*    */   
/*    */   public CMIHologramImpl(String name, AbstractLocation location, String text) {
/* 58 */     this.hologram = new CMIHologram(name, BukkitAdapter.adapt(location));
/* 59 */     this.hologram.setLines(Arrays.asList(new String[] { text }));
/* 60 */     this.hologram.setSaveToFile(false);
/* 61 */     this.hologram.setInteractable(false);
/* 62 */     this.hologram.setUpdateIntervalSec(0.05D);
/* 63 */     CMISupport.access$000(CMISupport.this).addHologram(this.hologram);
/* 64 */     this.hologram.enable();
/* 65 */     this.hologram.update();
/*    */   }
/*    */   
/*    */   public boolean terminate() {
/* 69 */     MythicLogger.log("Terminated hologram");
/* 70 */     this.hologram.disable();
/* 71 */     this.hologram.refresh();
/* 72 */     Scheduler.runSync(() -> CMISupport.access$000(CMISupport.this).removeHolo(this.hologram));
/*    */ 
/*    */     
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void teleport(AbstractLocation location) {
/* 80 */     MythicLogger.log("Teleported hologram");
/* 81 */     this.hologram.setLoc(BukkitAdapter.adapt(location));
/* 82 */     this.hologram.refresh();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 87 */     this.hologram.setLines(Arrays.asList(new String[] { text }));
/* 88 */     this.hologram.refresh();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String[] text, String linePrefix) {
/* 93 */     List<String> list = new ArrayList<>();
/*    */     
/* 95 */     for (String string : text) {
/* 96 */       list.add(linePrefix + string);
/*    */     }
/* 98 */     this.hologram.setLines(list);
/* 99 */     this.hologram.refresh();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\CMISupport$CMIHologramImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */