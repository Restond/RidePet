/*     */ package lumine.xikage.mythicmobs.compatibility;
/*     */ 
/*     */ import com.sainttx.holograms.api.Hologram;
/*     */ import com.sainttx.holograms.api.line.HologramLine;
/*     */ import com.sainttx.holograms.api.line.TextLine;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.HologramsSupport2;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import org.bukkit.ChatColor;
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
/*     */ class HealthBar
/*     */ {
/*     */   private final ActiveMob activemob;
/*     */   private Hologram hologram;
/*     */   private double yOffset;
/*     */   
/*     */   public HealthBar(ActiveMob am) {
/* 161 */     this.activemob = am;
/* 162 */     this.yOffset = am.getType().getConfig().getDouble("HealthBar.Offset", am.getType().isFakePlayer() ? 0.30000001192092896D : am.getType().getMythicEntity().getHealthbarOffset());
/*     */     
/* 164 */     this.hologram = new Hologram("#TempHealthBar" + this.activemob.getUniqueId().toString(), getLocation());
/* 165 */     this.hologram.setPersistent(false);
/* 166 */     this.hologram.addLine((HologramLine)new TextLine(this.hologram, getLine()));
/* 167 */     HologramsSupport2.access$000(paramHologramsSupport2).addActiveHologram(this.hologram);
/*     */   }
/*     */   
/*     */   private Location getLocation() {
/* 171 */     return BukkitAdapter.adapt(this.activemob.getEntity().getEyeLocation().add(0.0D, this.yOffset, 0.0D));
/*     */   }
/*     */   
/*     */   public boolean check() {
/* 175 */     if (this.activemob.isDead() || !this.activemob.getEntity().isValid() || this.activemob.getEntity().getHealth() == this.activemob.getEntity().getMaxHealth()) {
/* 176 */       remove();
/* 177 */       return false;
/*     */     } 
/* 179 */     return true;
/*     */   }
/*     */   
/*     */   public void teleport() {
/* 183 */     this.hologram.getLine(0).setLocation(getLocation());
/*     */   }
/*     */   
/*     */   public void update() {
/* 187 */     ((TextLine)this.hologram.getLine(0)).setText(getLine());
/*     */   }
/*     */   
/*     */   public void remove() {
/* 191 */     this.hologram.removeLine(this.hologram.getLine(0));
/* 192 */     HologramsSupport2.access$000(HologramsSupport2.this).removeActiveHologram(this.hologram);
/*     */   }
/*     */   
/*     */   private String getLine() {
/* 196 */     int hp = (int)this.activemob.getEntity().getHealth();
/*     */     
/* 198 */     double percent = this.activemob.getEntity().getHealth() / this.activemob.getEntity().getMaxHealth();
/* 199 */     String sHP = String.valueOf(hp);
/* 200 */     int hplength = sHP.length();
/* 201 */     int length = 10 + hplength;
/* 202 */     int gray = (int)Math.floor(percent * length);
/*     */ 
/*     */ 
/*     */     
/* 206 */     StringBuilder line = (new StringBuilder()).append(ChatColor.DARK_RED).append("[");
/*     */     
/* 208 */     boolean passed = false;
/* 209 */     for (int i = 0; i < length; i++) {
/* 210 */       if (!passed && i > gray) {
/* 211 */         passed = true;
/*     */       }
/* 213 */       if (i < 5) {
/* 214 */         line.append(passed ? ChatColor.DARK_GRAY : ChatColor.RED);
/* 215 */         line.append("|");
/* 216 */       } else if (i < 5 + hplength) {
/* 217 */         line.append(passed ? ChatColor.GRAY : ChatColor.DARK_RED);
/*     */         try {
/* 219 */           line.append(sHP.substring(i - 5, i - 4));
/* 220 */         } catch (Exception exception) {}
/*     */       } else {
/* 222 */         if (i == hplength + 2 && !passed) {
/* 223 */           line.append(ChatColor.RED);
/*     */         }
/* 225 */         line.append(passed ? ChatColor.DARK_GRAY : ChatColor.RED);
/* 226 */         line.append("|");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     return line.append(ChatColor.DARK_RED).append("]").toString();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HologramsSupport2$HealthBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */