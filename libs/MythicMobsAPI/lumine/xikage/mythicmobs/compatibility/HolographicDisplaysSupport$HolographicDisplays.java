/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*    */ import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
/*    */ import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.HolographicDisplaysSupport;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*    */ import org.bukkit.plugin.Plugin;
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
/*    */ public class HolographicDisplays
/*    */   implements IHologram
/*    */ {
/*    */   private Hologram hologram;
/*    */   
/*    */   public HolographicDisplays(String name, AbstractLocation location, String text) {
/* 38 */     Scheduler.runSync(() -> {
/*    */           this.hologram = HologramsAPI.createHologram((Plugin)HolographicDisplaysSupport.access$000(HolographicDisplaysSupport.this), BukkitAdapter.adapt(paramAbstractLocation));
/*    */           this.hologram.setAllowPlaceholders(false);
/*    */           this.hologram.appendTextLine(paramString);
/*    */         });
/*    */   }
/*    */   
/*    */   public boolean terminate() {
/* 46 */     Scheduler.runSync(() -> {
/*    */           this.hologram.clearLines();
/*    */           this.hologram.delete();
/*    */         });
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void teleport(AbstractLocation location) {
/* 55 */     Scheduler.runSync(() -> this.hologram.teleport(BukkitAdapter.adapt(paramAbstractLocation).add(0.0D, 0.38D, 0.0D)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 62 */     Scheduler.runSync(() -> ((TextLine)this.hologram.getLine(0)).setText(paramString));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setText(String[] text, String linePrefix) {
/* 69 */     Scheduler.runSync(() -> {
/*    */           this.hologram.clearLines();
/*    */           for (String string : paramArrayOfString)
/*    */             this.hologram.appendTextLine(paramString + string); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HolographicDisplaysSupport$HolographicDisplays.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */