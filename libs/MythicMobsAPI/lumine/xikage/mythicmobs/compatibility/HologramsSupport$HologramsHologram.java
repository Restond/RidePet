/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.sainttx.holograms.api.Hologram;
/*    */ import com.sainttx.holograms.api.line.HologramLine;
/*    */ import com.sainttx.holograms.api.line.TextLine;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.HologramsSupport;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
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
/*    */ public class HologramsHologram
/*    */   implements IHologram
/*    */ {
/*    */   private Hologram hologram;
/*    */   
/*    */   public HologramsHologram(String name, AbstractLocation location, String text) {
/* 52 */     this.hologram = new Hologram(name, BukkitAdapter.adapt(location));
/* 53 */     this.hologram.setPersistent(false);
/* 54 */     this.hologram.addLine((HologramLine)new TextLine(this.hologram, text));
/* 55 */     HologramsSupport.access$000(HologramsSupport.this).addActiveHologram(this.hologram);
/*    */   }
/*    */   
/*    */   public boolean terminate() {
/* 59 */     this.hologram.despawn();
/* 60 */     HologramsSupport.access$000(HologramsSupport.this).removeActiveHologram(this.hologram);
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void teleport(AbstractLocation location) {
/* 66 */     this.hologram.teleport(BukkitAdapter.adapt(location));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 71 */     ((TextLine)this.hologram.getLine(0)).setText(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String[] text, String linePrefix) {
/* 76 */     for (int i = 0; i < this.hologram.getLines().size(); i++) {
/* 77 */       this.hologram.removeLine(this.hologram.getLine(i));
/*    */     }
/*    */     
/* 80 */     for (String string : text)
/* 81 */       this.hologram.addLine((HologramLine)new TextLine(this.hologram, linePrefix + string)); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HologramsSupport$HologramsHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */