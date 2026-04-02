/*     */ package lumine.xikage.mythicmobs.compatibility;
/*     */ 
/*     */ import com.sainttx.holograms.api.Hologram;
/*     */ import com.sainttx.holograms.api.line.HologramLine;
/*     */ import com.sainttx.holograms.api.line.TextLine;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.HologramsSupport2;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import org.bukkit.Location;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Nameplate
/*     */ {
/*     */   private final ActiveMob activemob;
/*     */   private Hologram hologram;
/*     */   private double yOffset;
/*     */   
/*     */   public Nameplate(ActiveMob am) {
/* 112 */     this.activemob = am;
/* 113 */     this.yOffset = am.getType().getConfig().getDouble("Disguise.NameplateOffset", 0.5249999761581421D);
/* 114 */     this.yOffset = am.getType().getConfig().getDouble("Nameplate.Offset", this.yOffset);
/*     */     
/* 116 */     this.hologram = new Hologram("#TempNameplate" + this.activemob.getUniqueId().toString(), getLocation());
/* 117 */     this.hologram.setPersistent(false);
/* 118 */     this.hologram.addLine((HologramLine)new TextLine(this.hologram, getLine()));
/* 119 */     HologramsSupport2.access$000(paramHologramsSupport2).addActiveHologram(this.hologram);
/*     */   }
/*     */   
/*     */   public boolean check() {
/* 123 */     if (this.activemob.isDead() || !this.activemob.getEntity().isValid()) {
/* 124 */       remove();
/* 125 */       return false;
/*     */     } 
/* 127 */     return true;
/*     */   }
/*     */   
/*     */   public void teleport() {
/* 131 */     Location location = getLocation();
/*     */     
/* 133 */     if (location == null) {
/* 134 */       remove();
/*     */     } else {
/* 136 */       this.hologram.getLine(0).setLocation(getLocation());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() {
/* 141 */     this.hologram.removeLine(this.hologram.getLine(0));
/* 142 */     HologramsSupport2.access$000(HologramsSupport2.this).removeActiveHologram(this.hologram);
/*     */   }
/*     */   
/*     */   private Location getLocation() {
/* 146 */     return BukkitAdapter.adapt(this.activemob.getEntity().getEyeLocation().add(0.0D, this.yOffset, 0.0D));
/*     */   }
/*     */   
/*     */   private String getLine() {
/* 150 */     return SkillString.parseMobVariables(this.activemob.getDisplayName(), (SkillCaster)this.activemob, null, null);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HologramsSupport2$Nameplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */